package by.suprunyuk.hostel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.suprunyuk.hostel.dbconnection.ConnectionPool;
import by.suprunyuk.hostel.dbconnection.ProxyConnection;
import by.suprunyuk.hostel.entity.Client;
import by.suprunyuk.hostel.entity.Demand;
import by.suprunyuk.hostel.entity.User;
import by.suprunyuk.hostel.entity.UserRole;
import by.suprunyuk.hostel.exception.ConnectionPoolCreationException;
import by.suprunyuk.hostel.exception.ConnectionPoolWorkException;

public class ClientDAO extends AbstractDAO<Client> {

	private static final String READ_ALL_CLIENTS = "SELECT * FROM Client";
	private static final String INSERT_CLIENT = "INSERT INTO Client (Surname, Name, Email) VALUES (?, ?, ?)";
	private static final String READ_LAST_REGISTRED_CLIENT = "SELECT * FROM Client WHERE ClientID = (SELECT MAX(ClientID) FROM Client)";
	private static final String UPDATE_CLIENT_DISCOUNT = "UPDATE Client SET Discount=?  WHERE ClientID=?";
	private static final String UPDATE_BAN_STATUS = "UPDATE Client SET Banned=?  WHERE ClientID=?";

	@Override
	public List<Client> readAll() {
		List<Client> clients = new ArrayList<Client>();
		ConnectionPool pool = ConnectionPool.getInstance();
		ProxyConnection cn = null;
		Statement st = null;
		try {
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(READ_ALL_CLIENTS);
			while (rs.next()) {
				Client client = new Client();
				client.setClientId(rs.getLong("ClientID"));
				client.setSurname(rs.getString("Surname"));
				client.setName(rs.getString("Name"));
				client.setEmail(rs.getString("Email"));
				client.setDiscount(rs.getInt("Discount"));
				client.setBanned(rs.getBoolean("Banned"));
				clients.add(client);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			close(st);
			cn.close();
		}
		return clients;
	}

	@Override
	public boolean create(Client client) {
		boolean flag = false;
		ConnectionPool pool = null;
		ProxyConnection cn = null;
		PreparedStatement st = null;
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(INSERT_CLIENT);
			st.setString(1, client.getSurname());
			st.setString(2, client.getName());
			st.setString(3, client.getEmail());
			st.executeUpdate();
			

			flag = true;
		} catch (SQLException e) {
			LOGGER.error("sql error while creating new client", e);
		} finally {
			close(st);
			cn.close();
		}
		return flag;
	}

	@Override
	public Client read(long clientID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client update(Client entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Client entity) {
		// TODO Auto-generated method stub
		return false;
	}

	public Client readLastRegistredClient() {
		Client client = new Client();
		ConnectionPool pool = null;
		ProxyConnection cn = null;
		Statement st = null;
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(READ_LAST_REGISTRED_CLIENT);
			while (rs.next()) {
				client.setClientId(rs.getLong("ClientID"));
				client.setSurname(rs.getString("Surname"));
				client.setName(rs.getString("Name"));
				client.setEmail(rs.getString("Email"));
				client.setDiscount(rs.getInt("Discount"));
				client.setBanned(rs.getBoolean("Banned"));
			}
		} catch (SQLException e) {
			LOGGER.error("sql error while creating new client", e);
/*		} catch (ConnectionPoolCreationException e) {
			LOGGER.error("db error while creating new client", e);
		} catch (ConnectionPoolWorkException e) {
			LOGGER.error("db error while creating new client", e);*/
		} finally {
			close(st);
			cn.close();
		}
		return client;
	}

	public void updateDiscount(long currentClient, int newDiscount) {
			ConnectionPool pool = ConnectionPool.getInstance();
			ProxyConnection cn = null;
			PreparedStatement st = null;
			try {
				cn = pool.getConnection();
				cn.setPool(pool);
				st = cn.prepareStatement(UPDATE_CLIENT_DISCOUNT);
				st.setInt(1, newDiscount);
				st.setLong(2, currentClient);		
				st.executeUpdate();
			} catch (SQLException e) {
				LOGGER.error("sql error while updating demand");
			} finally {
				close(st);
				cn.close();
			}
		}

	public void updateBanStatus(long currentClient, boolean toBan) {
		ConnectionPool pool = ConnectionPool.getInstance();
		ProxyConnection cn = null;
		PreparedStatement st = null;
		try {
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(UPDATE_BAN_STATUS);
			st.setBoolean(1, toBan);
			st.setLong(2, currentClient);		
			st.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("sql error while updating demand");
		} finally {
			close(st);
			cn.close();
		}
	}	
}
