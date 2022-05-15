import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class MailClient extends Application {
    static User user;
    private Label toLabel;
    private Label subject;
    private TextField toEmail;
    private TextField subjectTextField;
    private TextArea emailMessage;
    private Label emailSent;
    Stage primaryStage;
    Scene emailSendScene;
    Scene loginScene;
    Scene inboxScene;
    private TextField emailTextField;
    private TextField passwordTextField;
    private Label errorLabel;
    public Button butInbox;
    public Button butCompose;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        /** EMAIL SEND SCENE */
        //Create Labels, TextFields, TextArea, and Button
        toLabel = new Label("To: ");
        subject = new Label("Subject: ");

        toEmail = new TextField();
        toEmail.setPrefWidth(430.0);
        subjectTextField = new TextField();
        subjectTextField.setPrefWidth(430.0);
        emailMessage = new TextArea();
        emailSent = new Label();

        Button sendButton = new Button("Send");
        sendButton.setOnAction(new SendButtonHandler()); //Event handler of sendButton

        //Create VBox of Labels and TextFields
        VBox vboxLabel = new VBox(20, toLabel, subject);
        VBox vboxTextField = new VBox(10, toEmail, subjectTextField);
        //Create HBox
        HBox hbox = new HBox(10, vboxLabel, vboxTextField);
        //Create VBox
        VBox vbox = new VBox(10, hbox, emailMessage, sendButton, emailSent);
        //Set alignment of VBox
        vbox.setAlignment(Pos.CENTER);
        //Set padding of VBox
        vbox.setPadding(new Insets(10));
        emailSendScene = new Scene(vbox);

        /** LOGIN SCENE */
        //Create Labels, TextFields, and Button
        Label loginLabel = new Label("Login");
        Label emailLabel = new Label("email: ");
        Label passwordLabel = new Label("password: ");

        emailTextField = new TextField();
        emailTextField.setPrefWidth(300.0);

        passwordTextField = new TextField();
        passwordTextField.setPrefWidth(300.0);

        errorLabel = new Label();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new LoginButtonHandler()); //Event handler of loginButton

        //Create VBox2 of Labels and TextFields
        VBox vboxLabel2 = new VBox(30, emailLabel, passwordLabel);
        VBox vboxTextField2 = new VBox(20, emailTextField, passwordTextField);
        //Create HBox2
        HBox hbox2 = new HBox(10, vboxLabel2, vboxTextField2);
        //Create VBox2
        VBox vbox2 = new VBox(10, loginLabel, hbox2, errorLabel, loginButton);
        //Set alignment of VBox2
        vbox2.setAlignment(Pos.CENTER);
        //Set padding of VBox2
        vbox2.setPadding(new Insets(20));

        //Create Login Scene
        loginScene = new Scene(vbox2, 410, 200);

        //START
        primaryStage.setTitle("Mail Client");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    class SendButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String recipient = toEmail.getText();
            String subject = subjectTextField.getText();
            String message = emailMessage.getText();
            EmailDraft emailDraft = new EmailDraft(user.getUsername(), recipient, subject, message);

            SendEmail.send(user, emailDraft);
            
            emailSent.setText("Successfully emailed");
        }
    }

    class LoginButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String email = emailTextField.getText();
            String password = passwordTextField.getText();
            user = new User(email, password);

            if (!email.equals("") && !password.equals("")) {
                //primaryStage.setScene(inboxScene);
                primaryStage.setScene(emailSendScene);
            }
            else if (email.equals("") && password.equals("")) {
                errorLabel.setText("Enter email and password to login");
            }
            else if (email.equals("")) {
                errorLabel.setText("Enter email to login");
            }
            else if (password.equals("")) {
                errorLabel.setText("Enter password to login");
            }
        }
    }
}
