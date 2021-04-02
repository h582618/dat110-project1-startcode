package com.example.GCE_Stands;

import Models.Stand;
import Wrappers.StandWrapper;
import com.google.gson.Gson;
import org.apache.tomcat.util.json.JSONParser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@WebServlet(name = "standsServlet", value = "/standsServlet")
public class StandsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        URL url = new URL("http://localhost:8080/stands/getStands");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setRequestMethod("GET");

        conn.connect();

        int responsecode = conn.getResponseCode();

        if(responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " +responsecode);
        else
        {
            String inline = "";
            Scanner sc = new Scanner(url.openStream());
            while(sc.hasNext())
            {
                inline+=sc.nextLine();
            }
           // System.out.println(inline);
            sc.close();

            StandWrapper standWrapper =  new Gson().fromJson(inline, StandWrapper.class);

          /*  for(Stand stand : standWrapper.getListOfStands()){
                System.out.println(stand.toString());
            }
           */

            HttpSession session = request.getSession();


            session.setAttribute("stands",standWrapper.getListOfStands());

            response.setContentType("text/html;charset=UTF-8");

            request.getRequestDispatcher("WEB-INF/Stands.jsp").forward(request, response);

        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
