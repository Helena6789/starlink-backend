package com.starlink.starlink_backend.servlet;

import com.starlink.starlink_backend.external.N2yoClient;
import com.starlink.starlink_backend.external.N2yoException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SatelliteAboveServlet", value = "/above")
public class SatelliteAboveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String elevation = request.getParameter("elevation");
        String altitude = request.getParameter("altitude");

        if (latitude == null || longitude == null || elevation == null || altitude == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        N2yoClient client = new N2yoClient();
        try {
            ServletUtil.writeData(
                    response, client.searchSatelliteAbove(latitude, longitude, elevation, altitude)
            );
        } catch (N2yoException e) {
            throw new ServletException(e);
        }
    }
}
