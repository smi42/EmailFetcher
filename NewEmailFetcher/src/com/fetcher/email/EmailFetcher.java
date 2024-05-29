package com.fetcher.email;

import java.util.Properties;
import java.util.Vector;
import jakarta.mail.*;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * This class is responsible for fetching emails from a mail server.
 */

public class EmailFetcher {
    private String host;
    private String mailStoreType;
    private String username;
    private String password;

    /**
     * Constructor for the EmailFetcher class.
     *
     * @param host         The host of the mail server.
     * @param mailStoreType The type of the mail store.
     * @param username     The username for the mail server.
     * @param password     The password for the mail server.
     */
    
    
    public EmailFetcher(String host, String mailStoreType, String username, String password) {
        this.host = host;
        this.mailStoreType = mailStoreType;
        this.username = username;
        this.password = password;
    }

    /**
     * Fetches emails from the mail server.
     *
     * @return A vector of Mail objects, each representing an email.
     */
    
    
    public Vector<Mail> fetchEmails() {
        Vector<Mail> emails = new Vector<>();
        try {
            Properties properties = new Properties();
            properties.put("mail.pop3.host", "pop3.mailtrap.io");
            properties.put("mail.pop3.port", "1100");
            properties.put("mail.pop3.starttls.enable", "true");

            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("pop3");
            store.connect(host, username, password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();

            for (Message message : messages) {
                String subject = message.getSubject();
                String body = getTextFromMessage(message);
                Mail mail = new Mail(subject, body);

                if (message.isMimeType("multipart/*")) {
                    Multipart multipart = (Multipart) message.getContent();
                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);
                        if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                            String filename = bodyPart.getFileName();
                            String filetype = bodyPart.getContentType();
                            InputStream is = bodyPart.getInputStream();
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] buffer = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = is.read(buffer)) != -1) {
                                byteArrayOutputStream.write(buffer, 0, bytesRead);
                            }
                            byte[] data = byteArrayOutputStream.toByteArray();
                            mail.addAttachment(filename, data, filetype);
                        }
                    }
                }

                emails.add(mail);
            }

            emailFolder.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emails;
    }

    /**
     * Extracts the text content from a Message object.
     *
     * @param message The Message object to extract text from.
     * @return The text content of the message.
     * @throws Exception If an error occurs during message processing.
     */
    
    
    private String getTextFromMessage(Message message) throws Exception {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    /**
     * Extracts the text content from a MimeMultipart object.
     *
     * @param mimeMultipart The MimeMultipart object to extract text from.
     * @return The text content of the multipart.
     * @throws Exception If an error occurs during multipart processing.
     */
    
    
    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent().toString());
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result.append(html);
            }
        }
        return result.toString();
    }
}
