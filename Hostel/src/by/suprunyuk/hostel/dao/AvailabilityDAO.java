package by.suprunyuk.hostel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.List;

import by.suprunyuk.hostel.dbconnection.ConnectionPool;
import by.suprunyuk.hostel.dbconnection.ProxyConnection;
import by.suprunyuk.hostel.entity.Availability;
import by.suprunyuk.hostel.entity.Entity;
import by.suprunyuk.hostel.entity.Hostel;
import by.suprunyuk.hostel.entity.User;
import by.suprunyuk.hostel.entity.UserRole;
import by.suprunyuk.hostel.exception.ConnectionPoolCreationException;
import by.suprunyuk.hostel.exception.ConnectionPoolWorkException;

public class AvailabilityDAO extends AbstractDAO<Availability>{

	private static final String SELECT_FREE_PLACES = "SELECT FreePlace FROM Availability WHERE Date=? AND HostelID=?";
	private static final String CREATE_AVAILABILITY_RECORD = "INSERT INTO Availability (Date, HostelID, FreePlace) VALUES (?, ?, ?)";
	private static final String UPDATE_AVAILABILITY_RECORD = "UPDATE Availability SET FreePlace=? WHERE Date=? AND HostelID=?";
	private static final int NON_EXIST_ROW_NUMBER = -1;
	@Override
	public List readAll() throws ConnectionPoolWorkException, ConnectionPoolCreationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Availability availability) {
		boolean flag = false;
		ConnectionPool pool = null;
		ProxyConnection cn = null;
		PreparedStatement st = null;
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(CREATE_AVAILABILITY_RECORD);
			st.setDate(1, Date.valueOf(availability.getDate()));
			st.setLong(2, availability.getHostelId());
			st.setInt(3, availability.getFreePlace());
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
	public Availability read(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Availability update(Availability availability) {
		ConnectionPool pool = null;
		ProxyConnection cn = null;
		PreparedStatement st = null;
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(UPDATE_AVAILABILITY_RECORD);
			st.setInt(1, availability.getFreePlace());
			st.setDate(2, Date.valueOf(availability.getDate()));
			st.setLong(3, availability.getHostelId());
			st.executeUpdate();
			
		} catch (SQLException e) {
			LOGGER.error("sql error while creating new availability", e);
		} finally {
			close(st);
			cn.close();
		}
		return availability;
	}

	@Override
	public boolean delete(Availability availability) {
		// TODO Auto-generated method stub
		return false;
	}

	public int readPlacesForHostel(LocalDate date, long hostelId) {
		int places = 0;
		ProxyConnection cn = null;
		PreparedStatement st = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(SELECT_FREE_PLACES);
			Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
			st.setTimestamp(1, timestamp); 
			st.setLong(2, hostelId);
			ResultSet rs = st.executeQuery();
			if (!rs.next()) {
				places = NON_EXIST_ROW_NUMBER;
			} else {
				places = Integer.parseInt(rs.getString("FreePlace"));
			}
		} catch (SQLException e) {
			LOGGER.error("request or table failed during SQL query", e);
		} finally {
			close(st);
			cn.close();
		}
		return places;
	}

}
