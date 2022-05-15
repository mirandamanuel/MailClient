import java.util.*;  
import javax.mail.*;
 
public class ReceiveEmail { 
public static ArrayList<EmailMessage> receiveEmail(String user, String password){
    ArrayList<EmailMessage> msgs = new ArrayList<EmailMessage>();
    EmailMessage em;
    
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
        mailStore.connect(hostType, user, password);
 
        Folder folder = mailStore.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
 
        Message[] emailMessages = folder.getMessages();
 
        //Iterate the messages
         for (int i = 0; i < emailMessages.length; i++) {
            em = new EmailMessage();
            Message message = emailMessages[i];
            Address[] toAddress = message.getRecipients(Message.RecipientType.TO);
  
            em.subject = message.getSubject();  
            em.from = message.getFrom()[0].toString();
            for(int j = 0; j < toAddress.length; j++){
                em.to.add(toAddress[j]);
            }
            em.text = message.getContent().toString();
            em.date = message.getSentDate().toString();

            msgs.add(em);
        }
 
   folder.close(false);
   mailStore.close();

} catch (Exception e) {
    e.printStackTrace();
    System.err.println("Error in receiving email.");
    }
return msgs;        
}
 
public static void main(String[] args) {
 String userName = "javamailsender3800@gmail.com";
 String password = "Chapter6!Skirt!Palace";
 ArrayList<EmailMessage> emails = new ArrayList<EmailMessage>();
 
 //call receiveEmail
 emails = receiveEmail(userName, password);

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
}
