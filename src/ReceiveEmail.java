import java.util.*;  
import javax.mail.*;
 
public class ReceiveEmail { 
public static ArrayList<EmailMessage> receiveEmail(User user){
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
            String text = message.getContent().toString();
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
 
public static void main(String[] args) {
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
}
