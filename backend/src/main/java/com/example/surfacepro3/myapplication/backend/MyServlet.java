/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.example.surfacepro3.myapplication.backend;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {
    int count = 0;
    public MyServlet(){
        super();
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.getOutputStream().println("WErkt");}

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try{
            int length = req.getContentLength();
            byte[] input = new byte[length];
            ServletInputStream sin = req.getInputStream();
            int c,count = 0;
            while((c=sin.read(input,count,input.length-count)) !=1){
                count +=c;
            }
            sin.close();

            String recievedString = new String(input);
            resp.setStatus(HttpServletResponse.SC_OK);
            OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream());

            String newString = "Nee jij bent een "+recievedString;
            writer.write(newString);
            writer.flush();
            writer.close();
        }
        catch (IOException e){
            try{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().print(e.getMessage());
                resp.getWriter().close();
        }catch(IOException ieo){

            }
    }
}}
