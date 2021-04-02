package com.example.GCE_Stands;

import Models.Stand;
import Wrappers.StandWrapper;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


@WebServlet(name = "FeedbackServlet", value = "/feedback")
public class FeedbackServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String id = request.getParameter("id");

        URL url = new URL("http://localhost:8080/stands/getStand/"+id);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        conn.connect();

        int responsecode = conn.getResponseCode();

        if (responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        else {
            String inline = "";
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                inline += sc.nextLine();
            }
            System.out.println(inline);
            sc.close();

            Stand thisStand = new Gson().fromJson(inline, Stand.class);


            HttpSession session = request.getSession();


            session.setAttribute("thisStand",thisStand);
        }

        request.getRequestDispatcher("WEB-INF/Feedback.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
