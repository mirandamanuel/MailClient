import java.util.*;  
import javax.mail.*;

public class EmailMessage {
    public String subject;
    public String from;
    public ArrayList<Address> to = new ArrayList<Address>();
    public String text;
    public String date;
}
