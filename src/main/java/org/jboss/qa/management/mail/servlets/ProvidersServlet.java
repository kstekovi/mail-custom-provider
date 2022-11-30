package org.jboss.qa.management.mail.servlets;

import org.jboss.qa.management.mail.MailTestConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.mail.Provider;
import jakarta.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Jan Bliznak <jbliznak@redhat.com>
 */
@WebServlet("/" + MailTestConstants.JSP_MAIL_PROVIDERS)
public class ProvidersServlet extends HttpServlet {

    public static final Logger log = LoggerFactory.getLogger(ProvidersServlet.class);
    private Session mailSession;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/plain");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();

        try {

            String jndiName = req.getParameter("jndiName");

            Context initCtx = new InitialContext();
            mailSession = (Session) initCtx.lookup(jndiName);

            Provider[] providers = mailSession.getProviders();

            for (Provider p : providers) {
                out.println(p);
            }

        } catch (Exception ex) {
            out.println("FAIL");
            log.error("Reading mail providers failed.", ex);
        }
    }
}
