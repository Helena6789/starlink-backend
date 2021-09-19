package com.starlink.starlink_backend.servlet;

import com.starlink.starlink_backend.external.N2yoClient;
import com.starlink.starlink_backend.external.N2yoException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SatellitePositionServlet", value = "/position")
public class SatellitePositionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String satid = request.getParameter("satid");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String elevation = request.getParameter("elevation");
        String endTime = request.getParameter("endTime");

        if (latitude == null || longitude == null || elevation == null || satid == null || endTime == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        N2yoClient client = new N2yoClient();
        try {
            ServletUtil.writeData(
                    response, client.searchSatellitePosition(satid, latitude, longitude, elevation, endTime)
            );
        } catch (N2yoException e) {
            throw new ServletException(e);
        }
    }
}
