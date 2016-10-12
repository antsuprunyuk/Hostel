package by.suprunyuk.hostel.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.suprunyuk.hostel.command.ActionCommand;
import by.suprunyuk.hostel.command.ActionFactory;
import by.suprunyuk.hostel.resource.ConfigurationManager;

/**
 * Main servlet of the application realizing front controller enterpise pattern. Controller is an initial point of contact for handling request. 
 * The controller manages the handling of the request, managing the choice of an appropriate view. 
 * 
 * @author Anton Suprunyuk
 */
@WebServlet("/controller") 
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	/** 
	 * Default constructor
	 */
    public Controller() {
        super();
    }

    /**
     * Called by the server (via the <code>service</code> method) to
     * allow a servlet to handle a GET request. Calls processRequest to make actual work that is common for all methods
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/**
     * Called by the server (via the <code>service</code> method)
     * to allow a servlet to handle a POST request. Calls processRequest to make actual work that is common for all methods
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/**
	 * Makes all work of handling requests and managing the choice of view page. Dispatches to appropriate page.
	 * 
	 * @param request an object implementing HttpServletRequest interface
	 * @param response an object implementing HttpServletResponse interface
	 * @throws ServletException   a general exception a servlet can throw when it encounters difficulty
	 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions 
	 * produced by failed or interrupted I/O operations.
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		ActionFactory client =  new ActionFactory();
		ActionCommand command = client.defineCommand(request);		
		page = command.execute(request);
		if (page != null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
			page = ConfigurationManager.getProperty("path.page.index");
			response.sendRedirect(request.getContextPath() + page);
		}
	}
}
