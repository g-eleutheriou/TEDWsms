package ted.wsms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ted.wsms.model.MessageModel;
import ted.wsms.objects.MessageObject;

@WebServlet({"/messages/view", "/messages/create", "/messages/delete"})
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			ServletContext sc = getServletContext();
			RequestDispatcher rd;
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			String message = "", type = "";
			
			try {
				if( !MessageModel.delete(id) ) {
					message = "Could not delete this message!";
					type = "error";
					return;
				}
			} catch (SQLException e) {
				message = "Could not delete this message!";
				type = "error";
				e.printStackTrace();
				return;
			} finally {
				request.setAttribute("message", message);
				request.setAttribute("type", type);
				rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/messages/view_msg.jsp");
				rd.forward(request, response); 	
			}
			
		try {	
			List<MessageObject> messagesListObj = MessageModel.getMessages();
        
    	    rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/messages/view_messages.jsp");

    	    request.setAttribute("messagesListObj", messagesListObj);
    	    rd.forward(request, response); 	
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			ServletContext sc = getServletContext();
			
			if( request.getParameter("id") != null ) {
				MessageObject messageObj = MessageModel.getMessage(Integer.parseInt(request.getParameter("id")));
				RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/messages/view_msg.jsp");
				request.setAttribute("messageObj", messageObj);
	    	    rd.forward(request, response); 	
	    	    return;
			}
			
			List<MessageObject> messagesListObj = MessageModel.getMessages();
        
    	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/messages/view_messages.jsp");

    	    request.setAttribute("messagesListObj", messagesListObj);
    	    rd.forward(request, response); 	
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}	
}
