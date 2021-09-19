package com.starlink.starlink_backend.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starlink.starlink_backend.entity.SatelliteObserver;
import com.starlink.starlink_backend.entity.SatellitePositionList;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;

public class N2yoClient {
    private static final String SAT_API_KEY = "896NY5-58U7YW-V3RVP9-4RZ7";

    private static final String STARLINK_CATEGORY = "52";

    private static final String NEARBY_SATELLITE_URL = "https://api.n2yo.com/api/rest/v1/satellite/above/%s/%s/%s/%s/%s/&apiKey=%s";

    private static final String SATELLITE_POSITION_URL = "https://api.n2yo.com/api/rest/v1/satellite/positions/%s/%s/%s/%s/%s/&apiKey=%s";

    // build url for satellite observer
    // e.g.: https://api.n2yo.com/rest/v1/satellite/above/41.702/-76.014/0/70/18/&apiKey=589P8Q-SDRYX8-L842ZD-5Z9
    private String buildSatelliteAboveURL(String latitude, String longitude, String elevation, String altitude) {
        return String.format(NEARBY_SATELLITE_URL, latitude, longitude, elevation, altitude, STARLINK_CATEGORY, SAT_API_KEY);
    }

    // build url for satellite position
    // e.g.: https://api.n2yo.com/rest/v1/satellite/positions/25544/41.702/-76.014/0/2/&apiKey=589P8Q-SDRYX8-L842ZD-5Z9
    private String buildSatellitePositionURL(String satid, String latitude, String longitude, String elevation, String endTime) {
        return String.format(SATELLITE_POSITION_URL, satid, latitude, longitude, elevation, endTime, SAT_API_KEY);
    }

    private String searchN2yoSatellite(String url) throws N2yoException {
        // Define the response handler to parse and return HTTP
        // response body returned from Ny2o
        ResponseHandler<String> responseHandler = response -> {
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                System.out.println("Response status: " +
                        response.getStatusLine().getReasonPhrase());
                throw new N2yoException(
                        "Failed to get result from N2yo API" +
                                response.getStatusLine().getReasonPhrase());
            }

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                throw new N2yoException(
                        "Failed to get result from ny2o API: failed to get entity.");
            }

            // n2yo can return null
            String entityStr = EntityUtils.toString(entity);
            // System.out.println("entry string:" + entityStr);
            if(entityStr.equals("\r\nnull")) {
                return null;
            }

            JSONObject obj = new JSONObject(entityStr);
            return obj.toString();
        };

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            return httpClient.execute(request, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
            throw new N2yoException(
                    "Failed to get result from ny2o API: failed to get entity.");
        }
    }

    // Convert JSON format data returned from Ny2o to an SatelliteObserver object
    private SatelliteObserver getSatelliteObserver(String data) throws N2yoException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(data, SatelliteObserver.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new N2yoException("Failed to parse above data from N2yo API: " + data);
        }
    }

    // Convert JSON format data returned from Ny2o to an SatellitePosition object
    private SatellitePositionList getSatellitePosition(String data) throws N2yoException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(data, SatellitePositionList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new N2yoException("Failed to parse above data from N2yo API: " + data);
        }
    }

    public SatelliteObserver searchSatelliteAbove(String latitude, String longitude, String elevation, String altitude) {
        String requestData = searchN2yoSatellite(buildSatelliteAboveURL(latitude, longitude, elevation, altitude));
        if (requestData == null) {
            return null;
        }
        return getSatelliteObserver(requestData);
    }

    public SatellitePositionList searchSatellitePosition(String satid, String latitude, String longitude, String elevation, String endTime) {
        String requestData = searchN2yoSatellite(buildSatellitePositionURL(satid, latitude, longitude, elevation, endTime));
        if (requestData == null) {
            return null;
        }
        return getSatellitePosition(requestData);
    }
}
