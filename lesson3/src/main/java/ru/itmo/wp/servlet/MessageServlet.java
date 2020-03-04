package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class MessageServlet extends HttpServlet {
    private static List<Message> messages;

    @Override
    public void init() throws ServletException {
        super.init();
        messages = new LinkedList<>();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI().substring("/message".length());
        HttpSession session = request.getSession();
        switch (uri) {
            case "/auth": {
                String userParam = request.getParameter("user");
                if (userParam != null) {
                    session.setAttribute("user", userParam);
                } else if (session.getAttribute("user") == null) {
                    session.setAttribute("user", "");
                }
                String json = new Gson().toJson(session.getAttribute("user"));
                response.getWriter().println(json);
                response.getWriter().flush();
                break;
            }
            case "/findAll": {
                String json = new Gson().toJson(messages);
                response.getWriter().println(json);
                response.getWriter().flush();
                break;
            }
            case "/add":
                String text = request.getParameter("text");
                messages.add(new Message((String) session.getAttribute("user"), text));
                break;
        }
    }

    private class Message {
        private String user;
        private String text;

        public String getUser() {
            return user;
        }

        public String getText() {
            return text;
        }

        Message(String user, String text) {
            this.text = text;
            this.user = user;
        }
    }
}
