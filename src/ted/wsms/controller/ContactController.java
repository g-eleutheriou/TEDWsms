package ted.wsms.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ted.wsms.model.MessageModel;
import ted.wsms.objects.MessageObject;

@WebServlet("/contact")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String msg = request.getParameter("message");
		
		MessageObject messageObj = new MessageObject();
		messageObj.setEmail(email);
		messageObj.setMessage(msg);
		messageObj.setName(name);
		messageObj.setSurname(surname);
		messageObj.setSubject(subject);
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/contact.jsp");
		
		String message = "Some error occured!", type = "error";
		try {
		
			MessageModel.create(messageObj);
			message = "Your message has been sent.";
			type = "success";
		
		} catch(SQLException e) {
			e.printStackTrace();
		
		} finally {
			request.setAttribute("message", message);
			request.setAttribute("type", type);
			rd.forward(request, response); 
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/contact.jsp");
	    rd.forward(request, response); 
	}
}
