Email Fetcher
This project is a simple Java application that fetches emails from a mail server using the JavaMail API. It's designed to connect to a POP3 mail server, retrieve emails from the inbox, and store them as Mail objects for further processing.

Features
Connects to a POP3 mail server using host, username, and password.
Fetches all emails from the inbox.
Extracts the subject and body of each email.
Handles both plain text and HTML emails.
Extracts attachments from emails and stores them as part of the Mail object.
Classes
EmailFetcher: This class is responsible for fetching emails from a mail server. It connects to the server, opens the inbox, and retrieves all messages. For each message, it extracts the subject, body, and any attachments, and stores them in a Mail object.

Mail: This class represents an email with a subject, body, and attachments. It provides methods to get the subject, body, and attachments, and to add an attachment.

Usage
To use this application, you need to create an instance of the EmailFetcher class with your mail server details, and then call the fetchEmails method. This will return a Vector of Mail objects, each representing an email.


EmailFetcher fetcher = new EmailFetcher(host, mailStoreType, username, password);
Vector<Mail> emails = fetcher.fetchEmails();
Dependencies
This project uses the JavaMail API for handling emails. You need to have the jakarta.mail package in your classpath to run this application.

Note
This is a basic email fetching application and does not include any error handling or user interface. It's intended as a starting point for building more complex email handling applications.
using Jakarta.activation-2.0.1.jar and jakarta.mail-2.0.1.jar and JavaSE-17
