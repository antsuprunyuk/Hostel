package by.suprunyuk.hostel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import by.suprunyuk.hostel.dbconnection.ConnectionPool;
import by.suprunyuk.hostel.dbconnection.ProxyConnection;
import by.suprunyuk.hostel.entity.Entity;
import by.suprunyuk.hostel.entity.Hostel;
import by.suprunyuk.hostel.exception.ConnectionPoolCreationException;
import by.suprunyuk.hostel.exception.ConnectionPoolWorkException;

public class HostelDAO extends AbstractDAO {

	private static final String READ_HOSTEL_BY_ID = "SELECT * FROM Hostel WHERE HostelID=?";
	
	@Override
	public List readAll() throws ConnectionPoolWorkException, ConnectionPoolCreationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Hostel read(long entityId) {
		Hostel hostel = new Hostel();
		ConnectionPool pool = null;
		ProxyConnection cn = null;
		PreparedStatement st = null;
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(READ_HOSTEL_BY_ID);
			st.setLong(1, entityId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				hostel.setHostelId(rs.getLong("HostelID"));
				hostel.setAddress(rs.getString("Address"));
				hostel.setCity(rs.getString("City"));
				hostel.setCountry(rs.getString("Country"));
				hostel.setPlace(rs.getInt("PlaceNumber"));
				hostel.setDayPrice(rs.getBigDecimal("DayPrice"));
			}
		} catch (SQLException e) {
			LOGGER.error("sql error while creating new client", e);
		} finally {
			close(st);
			cn.close();
		}
		return hostel;
	}

	@Override
	public Hostel update(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	public int readPlaceNumber(long hostelId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
