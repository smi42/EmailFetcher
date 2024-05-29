package com.fetcher.email;

import java.util.List;
import java.util.ArrayList;

/**
 * This class represents an email with a subject, body, and attachments.
 */

public class Mail {
    private String subject;
    private String body;
    private List<Attachment> attachments;

    
    /**
     * Constructor for the Mail class.
     *
     * @param subject The subject of the email.
     * @param body The body of the email.
     */
    
    public Mail(String subject, String body) {
        this.subject = subject;
        this.body = body;
        this.attachments = new ArrayList<>();
    }

    /**
     * Adds an attachment to the email.
     *
     * @param filename The name of the file to be attached.
     * @param data The data of the file to be attached.
     * @param filetype The type of the file to be attached.
     */
    
    
    public void addAttachment(String filename, byte[] data, String filetype) {
        this.attachments.add(new Attachment(filename, data, filetype));
    }

    /**
     * @return The subject of the email.
     */
        
    public String getSubject() {
        return subject;
    }

    /**
     * @return The body of the email.
     */
    
    public String getBody() {
        return body;
    }


    /**
     * @return The attachments of the email.
     */
    
    public List<Attachment> getAttachments() {
        return attachments;
    }


    /**
     * This class represents an attachment in an email.
     */
    
    
    public static class Attachment {
        private String filename;
        private byte[] data;
        private String filetype;

        
        /**
         * Constructor for the Attachment class.
         *
         * @param filename The name of the file to be attached.
         * @param data The data of the file to be attached.
         * @param filetype The type of the file to be attached.
         */
        
        
        public Attachment(String filename, byte[] data, String filetype) {
            this.filename = filename;
            this.data = data;
            this.filetype = filetype;
        }

        /**
         * @return The filename of the attachment.
         */
        
        public String getFilename() {
            return filename;
        }

        /**
         * @return The data of the attachment.
         */
                
        public byte[] getData() {
            return data;
        }

        /**
         * @return The filetype of the attachment.
         */
                
        public String getFiletype() {
            return filetype;
        }
    }
}
