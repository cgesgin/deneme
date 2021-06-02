/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controller.DocumentsBean;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(name = "FileServlet", urlPatterns = {"/file/*"})
public class FileServlet extends HttpServlet {

    @Inject
    private DocumentsBean documentsBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
           String file= request.getPathInfo();
           File f=new File(documentsBean.getUploadTo()+file);
           
           Files.copy(f.toPath(),response.getOutputStream());
           
    }

    
}
