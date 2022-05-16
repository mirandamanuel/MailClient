import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("---Welcome to your E-Mail---");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your username:");
        String username = scanner.next();
        System.out.println("Input your password:");
        String password = scanner.next();
        User user = new User(username, password);

        boolean exit = false;
        while(!exit){
            System.out.println("\nSelect an option:");
            System.out.println("[1] View your inbox\n[2] Send an email\n[0] Exit program");
            int selection = scanner.nextInt();
            if(selection == 1){
                ArrayList<EmailMessage> inboxMsgs = ReceiveEmail.receiveEmail(user);
                for (int i = 0; i < inboxMsgs.size(); i++){
                    System.out.println("\nDate: " + inboxMsgs.get(i).date);
                    System.out.println("From: " + inboxMsgs.get(i).from);
                    System.out.print("To: ");
                    for (int j = 0; j < inboxMsgs.get(i).to.size(); j++){
                        System.out.print(inboxMsgs.get(i).to.get(j) + " ");
                    } 
                    System.out.println("\nSubject: " + inboxMsgs.get(i).subject);
                    System.out.print("Text: " + inboxMsgs.get(i).text);
                    
                }
            }
            else if(selection == 2){
                System.out.println("Reciever email:");
                String to = getLine();
                System.out.println("Subject line:");
                String subject = getLine();
                System.out.println("Enter email message:");
                String message = getLine();
                EmailDraft draft = new EmailDraft(user.getUsername(), to, subject, message);
                SendEmail.send(user, draft);
            }
            else{
                exit = true;
            }
    
        }
        scanner.close();
    }

    private static String getLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
}
