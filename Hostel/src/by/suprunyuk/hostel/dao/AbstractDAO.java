package by.suprunyuk.hostel.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import by.suprunyuk.hostel.dbconnection.ConnectionPool;
import by.suprunyuk.hostel.entity.Entity;
import by.suprunyuk.hostel.exception.ConnectionPoolCreationException;
import by.suprunyuk.hostel.exception.ConnectionPoolWorkException;

public abstract class AbstractDAO<T extends Entity> {
	static final Logger LOGGER = Logger.getLogger(AbstractDAO.class);
	public abstract List<T> readAll()  throws ConnectionPoolWorkException, ConnectionPoolCreationException;
	public abstract boolean create(T entity);
	public abstract T read(long entityId);
	public abstract T update(T entity); // return type T or boolean ? 
	public abstract boolean delete(T entity);

	public void close(Statement st) { 
		 try {
			 if (st != null) {
				 st.close();
			 }
		 } catch (SQLException e) {
			 LOGGER.error(e);
		 }
	}
	
	
}
