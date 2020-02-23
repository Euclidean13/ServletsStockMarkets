package com.app.repository.listener;

import com.app.repository.db.DBConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@WebListener
public class AppContextListener implements ServletContextListener {

    private final static String CREATE_TABLE1 = "CREATE TABLE IF NOT EXISTS " +
            "registers(id SERIAL, fecha date NOT NULL," +
            "hora time NOT NULL, apertura NUMERIC(6,2), " +
            "cierre NUMERIC(6,2), maximo NUMERIC(6,2), minimo NUMERIC(6,2))";

    private final static String CREATE_TABLE2 = "CREATE TABLE IF NOT EXISTS " +
            "summary(id SERIAL, fecha date NOT NULL, " +
            "primer_precio_apertura NUMERIC(6,2), ultimo_precio_cierre NUMERIC(6,2), " +
            "mayor_maximo NUMERIC(6,2), menor_minimo NUMERIC(6,2))";

    @Override
    public void contextInitialized (ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        // Initialize DB connection
        String dbURL = ctx.getInitParameter("dbURL");
        String user = ctx.getInitParameter("dbUser");
        String pwd = ctx.getInitParameter("dbPassword");

        DBConnectionManager connectionManager = new DBConnectionManager(dbURL, user, pwd);
        ctx.setAttribute("DBConnection", connectionManager.getConnection());
        System.out.println("DB Connection initialized successfully.");

        createTables(connectionManager.getConnection());
        System.out.println("Table created successfully.");
    }

    private void createTables(Connection con) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(CREATE_TABLE1);
            ps.executeUpdate();
            ps = con.prepareStatement(CREATE_TABLE2);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }finally{
            try {
                assert ps != null;
                ps.close();
            } catch (SQLException e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }

        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Connection con = (Connection) servletContextEvent.getServletContext().getAttribute("DBConnection");
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
