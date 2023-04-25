/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu2025.framework.servlet;

import etu2025.framework.annotation.url;
import etu2025.framework.util.Utils;
import etu2025.framework.Mapping;
import etu2025.framework.ModelView;
import etu2025.model.Personne;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tiavi
 */
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;
    private ArrayList<Class> classList;

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
                getClassList().add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<Class> getClassList() {
        return classList;
    }

    public void setClassList(ArrayList<Class> classList) {
        this.classList = classList;
    }
    
    
    
    
   
    

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        String packageModel = config.getInitParameter("model-package");
        setClassList(new ArrayList<Class>());
        setMappingUrls(packageModel);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try  {
            out.println("<h1>ERROR 404 : FILE NOT FOUND</h1>");
            Class c = getClassFromUrl(getUrl(request));
            out.println(c.getName() + "<br>");
            HashMap<String, Method> setter = Utils.getSetters(c);
            Map<String, String[]> param = request.getParameterMap();

            Method m = getMethodFromUrl(getUrl(request));
            Object temp = c.newInstance();
//            
            for (Map.Entry<String, String[]> entry : param.entrySet()) {
                String key = entry.getKey();
                String[] parameter = entry.getValue();

                Method setTemp = setter.get(key);
                out.println(setTemp.getName() + "<br>");
                setTemp.invoke(temp, (Object) parameter[0]);
            }

            
            Object o = m.invoke(temp, null);    
            if (o instanceof ModelView) {
                ModelView mv = (ModelView)o;
                mv.listAll();
                RequestDispatcher dispatcher = request.getRequestDispatcher(mv.getView());
                for (Map.Entry<String, Object> entry : mv.getData().entrySet()) {
                    String key = String.valueOf(entry.getKey());
                    Object val = entry.getValue();
                    request.setAttribute(key, val);
                }
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println(e);
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
        List<Class> lc = getClassList();
        for (Class c : lc) {
            if (c.getSimpleName().equals(getMappingUrls().get(url).getClassName())) {
                for (Method m : c.getDeclaredMethods()) {
                    if (m.getName().equals(getMappingUrls().get(url).getMethod())){
                        return m;
                    }
                }
            }
        }
        throw new Exception("Method not found");
    }
    
    public Class getClassFromUrl(String url) throws Exception {
        List<Class> lc = getClassList();
        for (Class c : lc) {
            if (c.getSimpleName().equals(getMappingUrls().get(url).getClassName())) {
                for (Method m : c.getDeclaredMethods()) {
                    if (m.getName().equals(getMappingUrls().get(url).getMethod())){
                        return c;
                    }
                }
            }
        }
        throw new Exception("Class not found");
    }
   
    
    
    
    
    
}
