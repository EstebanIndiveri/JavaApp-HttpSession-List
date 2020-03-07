/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author estel
 */
@WebServlet(name = "CarritoServlet", urlPatterns = {"/CarritoServlet"})
public class CarritoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        //Procesamos el nuevo articulo
        String articuloNuevo=request.getParameter("articulo");
        
        //Creamos o recuperamos la sesión http:
        HttpSession sesion=request.getSession();
        
        //recuperamos la lista de articulos previos:
        //si es que existen en la sesion:
        //Lista generica:
        List<String> articulos=(List<String>)sesion.getAttribute("articulos");
        
        //verificamos si la lista de articulos existe:
        if(articulos==null){
            articulos=new ArrayList<>();
            //Map llave y valor:
            sesion.setAttribute("articulos",articulos);
        }
        //Ya tenemos la lista de articulos para trabajar
        //agregamos el nuevo articulo
        //agregamos por paso por referencia a la lista de articulos
        if(articuloNuevo!=null && !articuloNuevo.trim().equals("")){
            articulos.add(articuloNuevo);
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de articulos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de articulos</h1>");
            out.println("<br>");
            out.println("<ul>");
            //Iteramos todos los articulos incluyendo el nuevo FOR EACH:
            for(String articulo:articulos){
                out.println("<li>"+articulo+"</li>");
            }
            out.println("</ul>");
            out.println("<br>");
            out.println("<a href='/CarroCompras/'>Volver al inicio</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
