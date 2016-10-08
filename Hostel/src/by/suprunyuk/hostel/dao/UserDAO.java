package by.suprunyuk.hostel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import by.suprunyuk.hostel.dbconnection.ConnectionPool;
import by.suprunyuk.hostel.dbconnection.ProxyConnection;
import by.suprunyuk.hostel.entity.User;
import by.suprunyuk.hostel.entity.UserRole;

public class UserDAO extends AbstractDAO<User>{
	private static final Logger LOGGER = Logger.getLogger(UserDAO.class);
	public static final String SELECT_ABONENT_BY_LOGIN = "SELECT * FROM User JOIN UserRole ON User.Username=UserRole.Username WHERE User.Username=?";
	public static final String CREATE_USER = "INSERT INTO User VALUES (?, ?, ?)";
	public static final String CREATE_USERROLE = "INSERT INTO UserRole (UserName) VALUES (?)";
	public static final String SQL_ERROR_MESSAGE = "sql error while creating new client";
	public static final String USERPASS_FIELD = "Userpass";
	public static final String USERROLE_FIELD = "UserRole";
	public static final String CLIENTID_FIELD = "ClientID";
	public static final String REQUEST_FAIL_MESSAGE = "request or table failed during SQL query";
	
	
	@Override
	public List<User> readAll()  {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean create(User user) {
		boolean flag = false;
		ConnectionPool pool = null;
		ProxyConnection cn = null;
		PreparedStatement stUser = null;
		PreparedStatement stRole = null;
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			cn.setPool(pool);
			stUser = cn.prepareStatement(CREATE_USER);
			stUser.setString(1, user.getUserName());
			stUser.setString(2, user.getUserPass());
			stUser.setLong(3, user.getClientId());
			stUser.executeUpdate();
			stRole = cn.prepareStatement(CREATE_USERROLE);
			stRole.setString(1, user.getUserName());
			stRole.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			LOGGER.error(SQL_ERROR_MESSAGE, e);
		} finally {
			close(stUser);
			close(stRole);
			cn.close();
		}
		return flag;
	}
	
	public boolean create(User user, long clientID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User read(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public User readUserByLogin(String login) {
		User user = null;
		ProxyConnection cn = null;
		PreparedStatement st = null; 
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(SELECT_ABONENT_BY_LOGIN);
			st.setString(1, login);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String storedPassword = rs.getString(USERPASS_FIELD);
				UserRole userRole = UserRole.valueOf(rs.getString(USERROLE_FIELD));
				long clientId = rs.getLong(CLIENTID_FIELD);
				user = new User(login, storedPassword, userRole, clientId);
				user.setUserName(login);
				user.setUserPass(storedPassword);
				user.setUserRole(userRole);
				user.setClientId(clientId);
			}	
		} catch (SQLException e) {
			LOGGER.error(REQUEST_FAIL_MESSAGE, e);
/*		} catch (ConnectionPoolWorkException e) {
			LOGGER.error("error during work with connection pool", e);
		} catch (ConnectionPoolCreationException e) {
			LOGGER.error("error during creating with connection pool", e);*/
		} finally {
			close(st);
			cn.close();
		}
		return user;
	}




}
