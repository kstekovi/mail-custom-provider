package org.jboss.qa.management.mail.custom;

import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.URLName;

/**
 * Dummy implementation for test purpose.
 * <p/>
 *
 * @author Jan Bliznak <jbliznak@redhat.com>
 */
public class CustomTransport extends Transport {

    public CustomTransport(Session session, URLName urlname) {
        super(session, urlname);
    }

    @Override
    public void sendMessage(Message msg, Address[] addresses) throws MessagingException {
        if (session.getDebug()) {
            System.out.println("CustomTransport sent a massage");
        }
    }
}
