package com.app.repository.servlet;

import com.app.repository.dao.DBRepository;
import com.app.repository.model.*;
import com.app.repository.services.Calculations;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

@WebServlet(urlPatterns = { "/registry", "/allMedias", "/standardDeviation", "/variance", "/covariance", "/pearson"})
public class Servlet extends HttpServlet {


    public static final String ALL_MEDIAS = "/allMedias";
    public static final String STANDARD_DEVIATION = "/standardDeviation";
    public static final String VARIANCE = "/variance";
    public static final String COVARIANCE = "/covariance";
    public static final String PEARSON = "/pearson";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Connection con = (Connection) getServletContext().getAttribute("DBConnection");

        String path = request.getServletPath();
        if (path.equals("/registry")) {
            // Save entries into register table
            Entry[] entries = new Gson().fromJson(request.getReader(), Entry[].class);
            DBRepository dbRepository = new DBRepository(con);
            for (Entry entry : entries){
                dbRepository.saveEntryIntoRegisters(entry);
            }

            // Save entries calculations into summary table
            Calculations calculations = new Calculations(con);
            calculations.saveRegistriesCalculationsIntoSummary(new ArrayList<>(Arrays.asList(entries)), con);

            response.getWriter().write("Data saved");
        } else {
            response.getWriter().write("Wrong endpoint!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        processGetRequest(request, response, con);
    }

    private void processGetRequest(HttpServletRequest req, HttpServletResponse resp, Connection con) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        DBRepository dbRepository = new DBRepository(con);
        Calculations calculations = new Calculations(con);
        Gson gson = new Gson();

        String path = req.getServletPath();
        switch (path) {
            case ALL_MEDIAS:
                Medias mMaxMedia = dbRepository.getMedias();
                String json = gson.toJson(mMaxMedia);
                resp.getWriter().write(json);
                break;
            case STANDARD_DEVIATION:
                Summary summary = calculations.getStandardDeviation();
                String deviations = gson.toJson(summary);
                resp.getWriter().write(deviations);
                break;
            case VARIANCE:
                Summary variance = calculations.getVariance();
                String varianceJSON = gson.toJson(variance);
                resp.getWriter().write(varianceJSON);
                break;
            case COVARIANCE:
                Covariances covariances = calculations.getSummaryCovariances();
                String covarianceJSON = gson.toJson(covariances);
                resp.getWriter().write(covarianceJSON);
                break;
            case PEARSON:
                Pearson pearson = calculations.getSummaryPearson();
                String pearsonJSON = gson.toJson(pearson);
                resp.getWriter().write(pearsonJSON);
                break;
            default:
                break;
        }
    }
}
