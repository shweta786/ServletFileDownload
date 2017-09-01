/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiber.servletfiledownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SHWETA
 */
public class DownloadServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filePath = "C:\\Users\\SHWETA\\Desktop\\IP.png";
        File file = new File(filePath);
        FileInputStream fin = new FileInputStream(file);
        ServletContext context = getServletContext();
        String mimeType = context.getMimeType(filePath);
        if(mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int)file.length());
        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int byteRead = -1;
        while((byteRead = fin.read(buffer)) != -1){
            outStream.write(buffer,0,byteRead);            
        }
        fin.close();
        outStream.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
