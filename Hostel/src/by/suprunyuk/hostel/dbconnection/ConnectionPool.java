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

public class ConnectionPool {
	private static final String DATABASE_PROPERTIES_FILE = "properties.database";
	private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
	private static ConnectionPool instance;
	private static ReentrantLock lock = new ReentrantLock();
	private static AtomicBoolean instanceExists = new AtomicBoolean(false);
	private BlockingQueue<ProxyConnection> connections;
	private int poolSize;
	
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
	
	public static ConnectionPool getInstance() {
		if (!instanceExists.get()) {
			lock.lock();
			try {
				if (instance == null) {
					try { //TODO ?get to know if the caught exception will be thrown after finally
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
	private ProxyConnection createConnection(ResourceBundle rb) throws SQLException {
		String url = rb.getString("url");
		Properties prop = initProperties(rb);
		ProxyConnection proxyConnection = null;
		proxyConnection = new ProxyConnection(DriverManager.getConnection(url, prop));
		return  proxyConnection;
	}

	private Properties initProperties(ResourceBundle rb) {
		Properties prop = new Properties();
		prop.put("user", rb.getString("user"));
		prop.put("password", rb.getString("password"));
		prop.put("autoReconnect", rb.getString("autoReconnect"));
		prop.put("characterEncoding", rb.getString("characterEncoding"));
		prop.put("useUnicode", rb.getString("useUnicode"));
		return prop;
	}

	public ProxyConnection getConnection()  {
		ProxyConnection connection = null;
		try {
			connection = connections.take();
		} catch (InterruptedException e) {
			LOGGER.fatal("Connection pool creation error occured");
			throw new RuntimeException(e); 
		}
		return connection;
	}
	
	public void closeConnection(ProxyConnection connection) {
		connections.offer(connection);
	}
	
	public void shutDownPool() {

		for (int i = 0; i < poolSize; i++) {
			try {
				ProxyConnection proxyConnection;
				proxyConnection = connections.take();
				if (proxyConnection != null) {
					proxyConnection.close();
				}
			} catch (InterruptedException e) {
				LOGGER.error(e);
			}
		}
	}

}