package com.fetcher.email;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        String host = "pop3.mailserver";
        String mailStoreType = "pop3";
        String username = "Username";
        String password = "password";

        EmailFetcher emailFetcher = new EmailFetcher(host, mailStoreType, username, password);
        Vector<Mail> emails = emailFetcher.fetchEmails();

        for (Mail email : emails) {
            System.out.println("Subject: " + email.getSubject());
            System.out.println("Body: " + email.getBody());
            for (Mail.Attachment attachment : email.getAttachments()) {
                System.out.println("Attachment: " + attachment.getFilename());
                // Output attachment data to file...
            }
        }
    }
}
