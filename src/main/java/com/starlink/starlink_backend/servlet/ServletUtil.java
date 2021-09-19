package com.starlink.starlink_backend.servlet;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ServletUtil {
    public static <T> void writeData(HttpServletResponse response,
                                     T data)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(
                new ObjectMapper().writeValueAsString(data)
        );
    }

    public static <T> T readRequestBody(
            Class<T> cl, HttpServletRequest request) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readValue(request.getReader(), cl);
        } catch (JsonParseException | JsonMappingException e) {
            return null;
        }
    }
}
