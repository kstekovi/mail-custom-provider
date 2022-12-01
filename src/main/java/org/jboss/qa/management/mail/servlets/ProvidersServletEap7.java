package org.jboss.qa.management.mail.servlets;

import org.jboss.qa.management.mail.MailTestConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Provider;
import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Jan Bliznak <jbliznak@redhat.com>
 */
@WebServlet("/" + MailTestConstants.JSP_MAIL_PROVIDERS_EAP7)
public class ProvidersServletEap7 extends HttpServlet {

    public static final Logger log = LoggerFactory.getLogger(ProvidersServletEap7.class);
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
