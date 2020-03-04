package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] URIes = request.getRequestURI().substring(1).split("\\+");
        File[] files = new File[URIes.length];
        for (int i = 0; i < URIes.length; i++) {
            files[i] = getFile(URIes[i]);
            if (!files[i].isFile()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        }
        response.setContentType(getContentTypeFromName(URIes[0]));
        OutputStream outputStream = response.getOutputStream();
        for (File file : files) {
            Files.copy(file.toPath(), outputStream);
        }
        outputStream.flush();
    }

    File getFile(String uri) {
        File file = new File(getServletContext().getRealPath("."),
                Paths.get("../../src/main/webapp/static", uri).toString());
        if (!file.isFile()) {
            file = new File(getServletContext().getRealPath("/static/" + uri));
        }
        return file;
    }

    private String getContentTypeFromName(String name) {
        name = name.toLowerCase();

        if (name.endsWith(".png")) {
            return "image/png";
        }

        if (name.endsWith(".jpg")) {
            return "image/jpeg";
        }

        if (name.endsWith(".html")) {
            return "text/html";
        }

        if (name.endsWith(".css")) {
            return "text/css";
        }

        if (name.endsWith(".js")) {
            return "application/javascript";
        }

        throw new IllegalArgumentException("Can't find content type for '" + name + "'.");
    }
}
