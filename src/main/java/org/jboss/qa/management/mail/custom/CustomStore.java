package org.jboss.qa.management.mail.custom;

import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.URLName;

/**
 * Dummy implementation for test purpose.
 * <p/>
 *
 * @author Jan Bliznak <jbliznak@redhat.com>
 */
public class CustomStore extends Store {

    public CustomStore(Session session, URLName urlname) {
        super(session, urlname);
    }

    @Override
    public Folder getDefaultFolder() throws MessagingException {
        return new Folder(this) {
            @Override
            public String getName() {
                return "CustomStore folder";
            }

            @Override
            public String getFullName() {
                return "CustomStore folder";
            }

            @Override
            public Folder getParent() throws MessagingException {
                return null;
            }

            @Override
            public boolean exists() throws MessagingException {
                return true;
            }

            @Override
            public Folder[] list(String pattern) throws MessagingException {
                return new Folder[] {this};
            }

            @Override
            public char getSeparator() throws MessagingException {
                return '/';
            }

            @Override
            public int getType() throws MessagingException {
                return Folder.READ_ONLY;
            }

            @Override
            public boolean create(int type) throws MessagingException {
                return false;
            }

            @Override
            public boolean hasNewMessages() throws MessagingException {
                return true;
            }

            @Override
            public Folder getFolder(String name) throws MessagingException {
                return this;
            }

            @Override
            public boolean delete(boolean recurse) throws MessagingException {
                return false;
            }

            @Override
            public boolean renameTo(Folder f) throws MessagingException {
                return false;
            }

            @Override
            public void open(int mode) throws MessagingException {
                //do nothing
            }

            @Override
            public void close(boolean expunge) throws MessagingException {
                //do nothing
            }

            @Override
            public boolean isOpen() {
                return true;
            }

            @Override
            public Flags getPermanentFlags() {
                return new Flags();
            }

            @Override
            public int getMessageCount() throws MessagingException {
                return 0;
            }

            @Override
            public Message getMessage(int msgnum) throws MessagingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void appendMessages(Message[] msgs) throws MessagingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Message[] expunge() throws MessagingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    @Override
    public Folder getFolder(String name) throws MessagingException {
        return getDefaultFolder();
    }

    @Override
    public Folder getFolder(URLName url) throws MessagingException {
        return getDefaultFolder();
    }
}
