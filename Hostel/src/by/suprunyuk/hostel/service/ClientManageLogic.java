package by.suprunyuk.hostel.service;

import java.util.List;

import by.suprunyuk.hostel.dao.ClientDAO;
import by.suprunyuk.hostel.entity.Client;

/**
 * class represents all client logic needed by the application. Can read all clients, update discounts, ban and unban clients.
 * 
 * @author Anton Suprunyuk
 */
public class ClientManageLogic {

	/**
	 * Read all clients registred on the hostel site using dao layer
	 * 
	 * @return List of clients read from the database 
	 */
	public static List<Client> readAllClients() {
		ClientDAO clientDAO = new ClientDAO();
		List<Client> clients = clientDAO.readAll();
		return clients;
	}

	/**
	 * updates client discount using dao layer
	 * 
	 * @param currentClient id of a client whose discount amount will be changed
	 * @param newDiscount new amount of discount for client
	 */
	public static void updateDiscount(long currentClient, int newDiscount) {
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.updateDiscount(currentClient, newDiscount);
		
	}

	/**
	 *  bans or unbans client using dao layer 
	 * 
	 * @param currentClient id of the client that is going to be ban or unban
	 * @param toBan boolean variable for option wheather to ban or to unban client
	 */
	public static void ban(long currentClient, boolean toBan) {
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.updateBanStatus(currentClient, toBan);
	}

}
