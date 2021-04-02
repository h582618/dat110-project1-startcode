package com.example.GCE_Stands;

import ExcelService.StandGenerator;
import Models.Stand;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;


@WebServlet(name = "UploadServlet", value = "/UploadServlet")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("standsAdded") != null) {
            request.setAttribute("responseMsg", "Stands successfully added");
        } else if(request.getParameter("standAdded") != null){
            request.setAttribute("responseMsg", "Stand successfully added");
        }

         request.getRequestDispatcher("WEB-INF/AdminPage.jsp").forward(request, response);



}



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String standName = request.getParameter("standName");


        if(standName != null){
            /*
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(
                    "http://localhost:8080/stands/addStand",
                    new Stand(),
                    ResponseEntity.class);
             */
                   response.sendRedirect("UploadServlet?standAdded");
                   return;
        } else  {

            StandGenerator standGenerator = new StandGenerator();

            DiskFileItemFactory factory = new DiskFileItemFactory();

            ServletFileUpload upload = new ServletFileUpload(factory);

            FileItemIterator iter = upload.getItemIterator(request);

            System.out.println(iter.hasNext());
            if (iter.hasNext()) {
                FileItemStream item = iter.next();
                if (item.getName().contains(".xlsx")) {
                    InputStream stream = item.openStream();
                    standGenerator.generateStands(stream);
                }
                response.sendRedirect("UploadServlet?standsAdded");
            }
                  return;
        }


            
    }
}
