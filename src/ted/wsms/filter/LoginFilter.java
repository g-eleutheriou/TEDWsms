package ted.wsms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ted.wsms.objects.RoleObject;
import ted.wsms.objects.UserObject;

@WebFilter({"/messages/*", 
			"/products/*", 
			"/roles/*",
			"/suppliers/*",
			"/supplies/*",
			"/transactions/*",
			"/warehouses/*",
			"/users/*",
			"/transactions/*",
			"/type/*",
			"/welcome",
			"/profile"
		  })
public class LoginFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userLogin") == null) {
            
        	response.sendRedirect(request.getContextPath() + "/login"); // No logged-in user found, so redirect to login page.
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            
        } else if(session.getAttribute("userLogin") != null) {
        	
        	String path = request.getServletPath();
        	RoleObject role = ((UserObject)session.getAttribute("userLogin")).getRole();
        	HttpServletResponse myres = (HttpServletResponse)res;
        	
			if ( path.startsWith("/messages/") ) {
			
				if( role.getMessagesPermissions().equals("no_access") ) {
					myres.sendError(HttpServletResponse.SC_FORBIDDEN);
				} else {
	        		chain.doFilter(req, res); // Logged-in user found, so just continue request.
		        }
	        
			} else if ( path.startsWith("/products/") ) {
	        
				if( role.getProductsPermissions().equals("no_access") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else if ( path.startsWith("/products/create") && role.getProductsPermissions().equals("read") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else {
	        		chain.doFilter(req, res); // Logged-in user found, so just continue request.
		        }
			} else if ( path.startsWith("/type/") ) {
			        
				if( role.getProductsPermissions().equals("no_access") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else if ( path.startsWith("/type/create") && role.getProductsPermissions().equals("read") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else {
		       		chain.doFilter(req, res); // Logged-in user found, so just continue request.
	        	}	
	        		
	        } else if ( path.startsWith("/roles/") ) {
	        	
	        	if( role.getRolesPermissions().equals("no_access") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else if ( path.startsWith("/roles/create") && role.getRolesPermissions().equals("read") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else {
	        		chain.doFilter(req, res); // Logged-in user found, so just continue request.
		        }
	        	
	        } else if ( path.startsWith("/suppliers/") ) {
	        	
	        	if( role.getSuppliersPermissions().equals("no_access") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else if ( path.startsWith("/suppliers/create") && role.getSuppliersPermissions().equals("read") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else {
	        		chain.doFilter(req, res); // Logged-in user found, so just continue request.
		        }
	        	
	        } else if ( path.startsWith("/supplies/") ) {
	        	
	        	if( role.getSuppliersPermissions().equals("no_access") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else if ( path.startsWith("/supplies/create") && role.getSuppliersPermissions().equals("read") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else {
	        		chain.doFilter(req, res); // Logged-in user found, so just continue request.
		        }
	        
	        } else if ( path.startsWith("/transactions/") ) {
	        	
	        	if( role.getWarehousesPermissions().equals("no_access") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else if ( path.startsWith("/transactions/create") && role.getWarehousesPermissions().equals("read") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else {
	        		chain.doFilter(req, res); // Logged-in user found, so just continue request.
		        }
	        
	        } else if ( path.startsWith("/warehouses/") ) {
	        
	        	if( role.getWarehousesPermissions().equals("no_access") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else if ( path.startsWith("/warehouses/create") && role.getWarehousesPermissions().equals("read") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else {
	        		chain.doFilter(req, res); // Logged-in user found, so just continue request.
		        }
	        
	        } else if ( path.startsWith("/users/") ) {
	        	if( role.getUsersPermissions().equals("no_access") ) {
	        		myres.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	} else {
	        		chain.doFilter(req, res); // Logged-in user found, so just continue request.
		        }
	        } else {
        		chain.doFilter(req, res); // Logged-in user found, so just continue request.
	        }
		}
    }

    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }

}
