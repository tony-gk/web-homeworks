package ru.itmo.wp.servlet;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

public class CaptchaFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (request.getRequestURI().equals("/favicon.ico") ||
                "y".equals(session.getAttribute("captchaPassed"))) {
            chain.doFilter(request, response);
            return;
        }

        if ("GET".equals(request.getMethod())) {
            session.setAttribute("uri", request.getRequestURI());
            if (session.getAttribute("captchaCode") == null) {
                updateCaptchaCode(session);
            }
            captchaResponse(request, response, request.getRequestURI());
        } else if ("POST".equals(request.getMethod())) {
            if (request.getParameter("captcha").equals(session.getAttribute("captchaCode")) ||
                    "y".equals(session.getAttribute("captchaPassed"))) {
                session.setAttribute("captchaPassed", "y");
                response.sendRedirect(request.getParameter("uri"));
            } else {
                updateCaptchaCode(session);
                captchaResponse(request, response, request.getParameter("uri"));
            }
        }
    }

    private void updateCaptchaCode(HttpSession session) {
        int captchaCode = ThreadLocalRandom.current().nextInt(100, 1000);
        session.setAttribute("captchaCode", Integer.toString(captchaCode));
    }

    private void captchaResponse(HttpServletRequest request, HttpServletResponse response,
                                 String returnAddress) throws IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        String body = getBody(session.getAttribute("captchaCode").toString(), returnAddress);
        response.getWriter().write(body);
        response.getWriter().flush();
    }

    String getBody(String captchaCode, String uri) {
        byte[] captchaImage = ImageUtils.toPng(captchaCode);
        byte[] captchaImageBase64 = Base64.getEncoder().encode(captchaImage);

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Captcha</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <a href=\"#\"><img src=\"data:image/png;base64," +
                new String(captchaImageBase64) +
                "\" alt=\"Captcha\" /></a>\n" +
                "    <form action=\"captcha\" method=\"post\">\n" +
                "        <input type=\"hidden\" name=\"uri\" value=\"" + uri + "\">\n" +
                "        <input name=\"captcha\" type=\"text\">\n" +
                "        <input type=\"submit\">" +
                "    </form>\n" +
                "</body>\n" +
                "</html>";
    }
}
