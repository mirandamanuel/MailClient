import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendEmail {
    /**
     * Sends an email draft using a TLS connection with Gmail
     * @param draft an EmailDraft that will be sent
     */
    public static void send(User user, EmailDraft draft){
        //configured for gmail
        String host = "smtp.gmail.com";
        String port = "587";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        //auth requires account authentication
        props.put("mail.smtp.auth", "true");
        //starttls.enable enforces a TLS connection for encrypting sent email
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        //javax.mail session where the authentication for the user happens
        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user.getUsername(), user.getPassword());
                    }
                });
        try {
            //following block turns draft in a MimeMessage, which is what is sent
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(draft.getSender()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(draft.getRecipients()));
            message.setSubject(draft.getSubject());
            message.setText(draft.getMessage());
            Transport.send(message);
            System.out.println("Email sent successfully");

        } catch (MessagingException e){
            System.out.println("Sending Email failed");
            throw new RuntimeException(e);
        }

    }
}
