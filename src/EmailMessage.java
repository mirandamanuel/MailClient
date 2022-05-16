import java.util.*;  
import javax.mail.*;

public class EmailMessage {
    public String subject;
    public String from;
    public ArrayList<Address> to;
    public String text;
    public String date;

    public EmailMessage(String subject, String from, ArrayList<Address> to, String text, String date){
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.text = text;
        this.date = date;
    }

    public String getSubject(){
        return subject;
    }

    public String getFrom(){
        return from;
    }

    public ArrayList<Address> getTo(){
        return to;
    }

    public String getText(){
        return text;
    }
    
    public String getDate(){
        return date;
    }
}
