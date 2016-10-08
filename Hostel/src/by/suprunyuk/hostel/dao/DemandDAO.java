package by.suprunyuk.hostel.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.suprunyuk.hostel.dbconnection.ConnectionPool;
import by.suprunyuk.hostel.dbconnection.ProxyConnection;
import by.suprunyuk.hostel.entity.Client;
import by.suprunyuk.hostel.entity.Decision;
import by.suprunyuk.hostel.entity.Demand;
import by.suprunyuk.hostel.entity.Entity;
import by.suprunyuk.hostel.entity.Purpose;
import by.suprunyuk.hostel.exception.ConnectionPoolCreationException;
import by.suprunyuk.hostel.exception.ConnectionPoolWorkException;

public class DemandDAO extends AbstractDAO<Demand> {

	private static final String INSERT_DEMAND = "INSERT INTO Demand (HostelID, ClientID, DateIN, DateOUT, PlaceNumber, Purpose) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String DELETE_DEMAND = "DELETE FROM Demand WHERE DemandID=?";
	private static final String UPDATE_DEMAND = "UPDATE Demand SET Decision=?  WHERE DemandID=?";
	private static final String READ_DEMANDS_BY_CLIENT = "SELECT * FROM Demand WHERE ClientID=?";
	private static final String READ_ALL_DEMANDS = "SELECT * FROM Demand";
	private static final String READ_DEMAND_BY_ID = "SELECT * FROM Demand WHERE DemandID=?";

	@Override
	public List<Demand> readAll()  {
		List<Demand> demands = new ArrayList<Demand>();
		ConnectionPool pool = ConnectionPool.getInstance();
		ProxyConnection cn = null;
		PreparedStatement st = null;
		try {
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(READ_ALL_DEMANDS);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Demand demand= new Demand();
				demand.setDemandId(rs.getLong("DemandID"));
				demand.setHostelId(rs.getLong("HostelID"));
				demand.setClientId(rs.getLong("ClientID"));
				demand.setDateIn(rs.getDate("DateIN").toLocalDate());
				demand.setDateOut(rs.getDate("DateOUT").toLocalDate());
				demand.setPlaceNumber(rs.getInt("PlaceNumber"));
				demand.setPurpose(Purpose.valueOf(rs.getString("Purpose")));
				demand.setDecision(Decision.valueOf(rs.getString("Decision")));
				demands.add(demand);
			}
		} catch (SQLException e) {
			LOGGER.error("sql error while getting demands from database");
		} finally {
			close(st);
			cn.close();
		}
		return demands;
	}

	@Override
	public boolean create(Demand demand) {
		boolean flag = false;
		ConnectionPool pool = null;
		ProxyConnection cn = null;
		PreparedStatement st = null;
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(INSERT_DEMAND);
			st.setLong(1, demand.getHostelId());
			st.setLong(2, demand.getClientId());
			st.setDate(3, Date.valueOf(demand.getDateIn()));
			st.setDate(4,  Date.valueOf(demand.getDateOut()));
			st.setInt(5, demand.getPlaceNumber());
			st.setString(6, demand.getPurpose().toString());
			st.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			LOGGER.error("sql error while creating new demand", e);
		} finally {
			close(st);
			cn.close();
		}
		return flag;
	}

	@Override
	public Demand read(long demandId) {
		Demand demand = new Demand();
		ConnectionPool pool = ConnectionPool.getInstance();
		ProxyConnection cn = null;
		PreparedStatement st = null;
		try {
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(READ_DEMAND_BY_ID);
			st.setLong(1, demandId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				demand.setDemandId(rs.getLong("DemandID"));
				demand.setHostelId(rs.getLong("HostelID"));
				demand.setClientId(rs.getLong("ClientID"));
				demand.setDateIn(rs.getDate("DateIN").toLocalDate());
				demand.setDateOut(rs.getDate("DateOUT").toLocalDate());
				demand.setPlaceNumber(rs.getInt("PlaceNumber"));
				demand.setPurpose(Purpose.valueOf(rs.getString("Purpose")));
				demand.setDecision(Decision.valueOf(rs.getString("Decision")));
			}
		} catch (SQLException e) {
			LOGGER.error("sql error while getting demand from database");
		} finally {
			close(st);
			cn.close();
		}
		return demand;
	}

	@Override
	public Demand update(Demand demand) {
		ConnectionPool pool = ConnectionPool.getInstance();
		ProxyConnection cn = null;
		PreparedStatement st = null;
		try {
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(UPDATE_DEMAND);
			st.setString(1, demand.getDecision().toString());
			st.setLong(2, demand.getDemandId());		
			st.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("sql error while updating demand");
		} finally {
			close(st);
			cn.close();
		}
		return demand;
	}

	@Override
	public boolean delete(Demand demand) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Demand> readDemandsByClient(long clientId) {
		List<Demand> demands = new ArrayList<Demand>();
		DemandDAO demandDAO = new DemandDAO();
		ConnectionPool pool = ConnectionPool.getInstance();
		ProxyConnection cn = null;
		PreparedStatement st = null;
		try {
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(READ_DEMANDS_BY_CLIENT);
			st.setLong(1, clientId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Demand demand= new Demand();
				demand.setDemandId(rs.getLong("DemandID"));
				demand.setHostelId(rs.getLong("HostelID"));
				demand.setClientId(rs.getLong("ClientID"));
				demand.setDateIn(rs.getDate("DateIN").toLocalDate());
				demand.setDateOut(rs.getDate("DateOUT").toLocalDate());
				demand.setPlaceNumber(rs.getInt("PlaceNumber"));
				demand.setPurpose(Purpose.valueOf(rs.getString("Purpose")));
				demand.setDecision(Decision.valueOf(rs.getString("Decision")));
				demands.add(demand);
			}
		} catch (SQLException e) {
			LOGGER.error("sql error while getting demands from database");
		} finally {
			close(st);
			cn.close();
		}
		return demands;
	}

	public boolean delete(long demandId) {
		boolean flag = false;
		ConnectionPool pool = null;
		ProxyConnection cn = null;
		PreparedStatement st = null;
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			cn.setPool(pool);
			st = cn.prepareStatement(DELETE_DEMAND);
			st.setLong(1, demandId);
			st.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			LOGGER.error("sql error while deleting demand", e);
		} finally {
			close(st);
			cn.close();
		}
		return flag;		
	}

}
