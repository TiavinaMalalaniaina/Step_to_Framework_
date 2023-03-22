/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu2025.framework.servlet;

import etu2025.framework.annotation.url;
import etu2025.framework.util.Utils;
import etu2025.framework.Mapping;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tiavi
 */
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;

    public HashMap<String, Mapping> getMappingUrls() {
        return mappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        this.mappingUrls = mappingUrls;
    }
    
    public void setMappingUrls(String path) {
        try {
            List<Class> lc = Utils.getClassFrom(path);
            setMappingUrls(new HashMap<String, Mapping>());
            for (Class c : lc) {
                for (Method m : c.getDeclaredMethods()) {
                    url u = m.getAnnotation(url.class);
                    if (u  != null) {
                       getMappingUrls().put(u.value() , new Mapping(c.getSimpleName(), m.getName()));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        setMappingUrls("etu2025.model");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try  {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> <u>Servlet FrontServlet</u> at " + request.getContextPath() + "</h1>");
            out.println("<h1><u>RequestURI</u> at " + request.getRequestURI()+ "</h1>");
            out.println("<h1><u> Url </u>at " + getUrl(request) + "</h1>");
            out.println("</body>");
            out.println("</html>");
            Method m = getMethodFromUrl(getUrl(request));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String getUrl(HttpServletRequest request) {
        String result;
        String contextPath = request.getContextPath();
        String url = request.getRequestURI();
        result = url.split(contextPath)[1];
        String query = request.getQueryString();
        return result;
    }
    
    public Method getMethodFromUrl(String url) throws Exception {
        
        List<Class> lc = Utils.getClassFrom("etu2025.model");
        for (Class c : lc) {
            if (c.getSimpleName()==getMappingUrls().get(url).getClassName()) {
                for (Method m : c.getDeclaredMethods()) {
                    if (m.getName()==getMappingUrls().get(url).getMethod()){
                        return m;
                    }
                }
            }
        }
        throw new Exception("Method not found");
    }
    
    
    
    
}
