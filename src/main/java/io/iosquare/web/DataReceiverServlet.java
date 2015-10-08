package io.iosquare.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mva on 08/10/15.
 */
public class DataReceiverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder jsonRequest = new StringBuilder();
        BufferedReader reader = request.getReader();
        for(;;) {
            String line = reader.readLine();
            if(line == null) {
                break;
            }
            System.out.println("Received: "+line);
            jsonRequest.append(line);
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println("{\"msg\":\"ok\"}");
        out.close();
        DataCollector.postData(jsonRequest.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println("{\"msg\":\"ok\"}");
        out.close();
    }
}
