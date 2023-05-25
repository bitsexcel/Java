package ch02.basic;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet(name = "RemovePhotoServlet", urlPatterns = {"/RemovePhotoServlet"})
public class RemovePhotoServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String indexString = request.getParameter("photo");        
        int index = (new Integer(indexString.trim())).intValue();
        PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(request.getServletContext());
        pa.removePhoto(index);
        RequestDispatcher rd = request.getRequestDispatcher("DisplayAlbumServlet");
        rd.forward(request, response); 
    }

}
