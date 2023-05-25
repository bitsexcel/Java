package ch01;

import java.io.IOException;

import javax.ejb.EJB;

import ch01.model.MessageException;
import ch01.model.ModelEJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet(name = "WriteServlet", urlPatterns = {"/WriteServlet"})
public class WriteServlet extends HttpServlet {
    @EJB
    private ModelEJB helloEJB = new ModelEJB();
    private static String PUT_MESSAGE = "put_message";

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = request.getParameter(PUT_MESSAGE);
        if ("".equals(message)) {
            helloEJB.deleteMessage();
        } else {
            try {
                helloEJB.putUserMessage(message);
            } catch (MessageException nme) {
                throw new ServletException(nme);
            }    
        } 
        response.sendRedirect("./DisplayServlet"); 
    }
    
}