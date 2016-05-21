/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author Brett
 */
@WebListener()
public class ViewerTracker implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String currentId = (String)se.getSession().getAttribute("productId");
        ServletContext context = se.getSession().getServletContext();

        int count = Integer.parseInt((String)context.getAttribute("viewerCount"));
        count++;
        context.setAttribute("viewerCount", new Integer(count));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
