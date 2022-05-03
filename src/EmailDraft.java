public class EmailDraft {
    private String sender;
    private String recipients;
    private String subject;
    private String message;

    /**
     * Creates the draft of an email
     * @param sender the sender email address
     * @param recipients the recipients' email addresses, seperated by commas
     * @param subject the subject of the email
     * @param message the body of the message
     */
    public EmailDraft(String sender, String recipients, String subject, String message){
        this.sender = sender;
        this.recipients = recipients;
        this.subject = subject;
        this.message = message;
    }

    /**
     * Get method for the draft's sender address
     * @return the address of the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * Get method for draft's recipients' addresses
     * @return the address of the recipient
     */
    public String getRecipients() {
        return recipients;
    }

    /**
     * Get method for draft's subject
     * @return the subject of the email
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get method for draft's test
     * @return the message contained by the email
     */
    public String getMessage() {
        return message;
    }
}
