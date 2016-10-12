package by.suprunyuk.hostel.dbconnection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

/**
 * Class for implementing convenient work with database connections. Instead of making new connection every time this pool 
 * uses distinct number of connections that are reusable. After using one in dao-method it is been returned
 * 
 * @author Anton Suprunyuk
 */
public class ConnectionPool {
	
	/**
	 * String identifier of properties file with database configuration
	 */
	private static final String DATABASE_PROPERTIES_FILE = "properties.database";
	
	/**
	 * Log4j logger instance for logging errors  
	 */
	private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
	
	/**
	 * Connection pool instance object
	 */
	private static ConnectionPool instance;
	
	/**
	 * Reentrant Lock object for making synchronization during singleton creation 
	 */
	private static ReentrantLock lock = new ReentrantLock();
	
	/**
	 * Atomic boolean variable for making "volatile" object
	 */
	private static AtomicBoolean instanceExists = new AtomicBoolean(false);
	
	/**
	 * Blocking queue is being used for thread safe holding of ProxyConnection objects 
	 */
	private BlockingQueue<ProxyConnection> connections;
	
	/**
	 * number that indicates the number of connections in pool
	 */
	private int poolSize;
	
	/**
	 * Private constructor that reads database configuration from file, register database driver,
	 * and create connection pool and fill it with proxy connections 
	 * @throws SQLException An exception that provides information on a database access
	 * error or other errors.
	 */
	private ConnectionPool() throws SQLException {
		ResourceBundle rb = ResourceBundle.getBundle(DATABASE_PROPERTIES_FILE);
		poolSize = Integer.parseInt(rb.getString("poolSize"));
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		connections = new ArrayBlockingQueue<ProxyConnection>(poolSize);
		for (int i = 0; i < poolSize; i++) {
			ProxyConnection connection = createConnection(rb);
			connections.offer(connection);
		}
	}
	
	/**
	 * creates and returns connection pool instance. Uses double checked locking pattern.
	 * 
	 * @return instance of connection pool
	 */
	public static ConnectionPool getInstance() {
		if (!instanceExists.get()) {
			lock.lock();
			try {
				if (instance == null) {
					try { 
						instance = new ConnectionPool();
						instanceExists.getAndSet(true);
					} catch (SQLException e) {
						LOGGER.fatal("Connection pool creation error occured");
						throw new RuntimeException(e);
					}
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}
	
	/**
	 * Creates ProxyConnection using configuration from database file
	 * 
	 * @param rb ResourceBundle 
	 * @return ProxyConnection object that was just created
	 * @throws SQLException An exception that provides information on a database access
	 */
	private ProxyConnection createConnection(ResourceBundle rb) throws SQLException {
		String url = rb.getString("url");
		Properties prop = initProperties(rb);
		ProxyConnection proxyConnection = null;
		proxyConnection = new ProxyConnection(DriverManager.getConnection(url, prop));
		return  proxyConnection;
	}

	/**
	 * initiate Properties object from database configuration file 
	 * 
	 * @param rb ResourceBundle
	 * @return initialized Properties object 
	 */
	private Properties initProperties(ResourceBundle rb) {
		Properties prop = new Properties();
		prop.put("user", rb.getString("user"));
		prop.put("password", rb.getString("password"));
		prop.put("autoReconnect", rb.getString("autoReconnect"));
		prop.put("characterEncoding", rb.getString("characterEncoding"));
		prop.put("useUnicode", rb.getString("useUnicode"));
		return prop;
	}

	/**
	 * provides connection obtained from the connection pool
	 * 
	 * @return ProxyConnection obtained from connection pool
	 */
	public ProxyConnection getConnection()  {
		ProxyConnection connection = null;
		try {
			connection = connections.take();
		} catch (InterruptedException e) {
			LOGGER.fatal("Connection pool work error occured");
			throw new RuntimeException(e); 
		}
		return connection;
	}
	
	/**
	 * returns connection to connection pool
	 * 
	 * @param connection ProxyConnection
	 */
	public void closeConnection(ProxyConnection connection) {
		connections.offer(connection);
	}
	
	/**
	 * closes all connections from the connection pool
	 * 
	 * @throws SQLException An exception that provides information on a database access
	 */
	public void shutDownPool() throws SQLException {
		for (int i = 0; i < poolSize; i++) {
			try {
				ProxyConnection proxyConnection;
				proxyConnection = connections.take();
				if (proxyConnection != null) {
					proxyConnection.realClose();
				}
			} catch (InterruptedException e) {
				LOGGER.error(e);
			}
		}
	}

}