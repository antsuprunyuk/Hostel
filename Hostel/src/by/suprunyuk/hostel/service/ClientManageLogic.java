package by.suprunyuk.hostel.service;

import java.util.List;

import by.suprunyuk.hostel.dao.ClientDAO;
import by.suprunyuk.hostel.entity.Client;

public class ClientManageLogic {

	public static List<Client> readAllClients() {
		ClientDAO clientDAO = new ClientDAO();
		List<Client> clients = clientDAO.readAll();
		return clients;
	}

	public static void updateDiscount(long currentClient, int newDiscount) {
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.updateDiscount(currentClient, newDiscount);
		
	}

	public static void ban(long currentClient, boolean toBan) {
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.updateBanStatus(currentClient, toBan);
		
	}

}
