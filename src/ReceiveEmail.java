import java.io.IOException;
import java.util.*;  
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ReceiveEmail { 
    //boolean textIsHtml = false;
public static ArrayList<EmailMessage> receiveEmail(User user){
    ArrayList<EmailMessage> msgs = new ArrayList<EmailMessage>();
    
    String hostType = "imap.gmail.com";
    String storeType = "imaps";

	Properties props = new Properties();
    props.put("mail.imap.host", hostType);
	props.put("mail.imap.port", "993");
	props.put("mail.imap.starttls.enable", "true");
	props.put("mail.store.protocol", "imap");
    
    Session session = Session.getInstance(props);	
    try {  
        Store mailStore = session.getStore(storeType);
        mailStore.connect(hostType, user.getUsername(), user.getPassword());
 
        Folder folder = mailStore.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
 
        Message[] emailMessages = folder.getMessages();
 
        //Iterate the messages
         for (int i = 0; i < emailMessages.length; i++) {
            Message message = emailMessages[i];
            Address[] toAddress = message.getRecipients(Message.RecipientType.TO);
  
            String subject = message.getSubject();  
            String from = message.getFrom()[0].toString();
            ArrayList<Address> to = new ArrayList<Address>();
            for(int j = 0; j < toAddress.length; j++){
                to.add(toAddress[j]);
            }
            String text = "";
            if (message.isMimeType("text/plain")) {
               text = message.getContent().toString();
            } else if (message.isMimeType("multipart/*")) {
                MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
                text = getTextFromMimeMultipart(mimeMultipart);
            }
            String date = message.getSentDate().toString();

            msgs.add(new EmailMessage(subject, from, to, text, date));
        }
 
   folder.close(false);
   mailStore.close();

} catch (Exception e) {
        e.printStackTrace();
        System.err.println("Error in receiving email.");
    }
    return msgs;        
}
 
    public static void recieve(String[] args) {
    User user = new User("javamailsender3800@gmail.com", "Chapter6!Skirt!Palace");
    ArrayList<EmailMessage> emails = new ArrayList<EmailMessage>();
    
    //call receiveEmail
    emails = receiveEmail(user);

    for (int i = 0; i < emails.size(); i++){
        System.out.println("\nSubject: " + emails.get(i).subject);
        System.out.println("From: " + emails.get(i).from);
        System.out.print("To: ");
        for (int j = 0; j < emails.get(i).to.size(); j++){
            System.out.print(emails.get(i).to.get(j) + " ");
        } 
        System.out.print("\nText: " + emails.get(i).text);
        System.out.println("Date: " + emails.get(i).date);
    }
    }

   /**
     * Return the primary text content of the message.
     */
    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart)  throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + html;
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }
}
