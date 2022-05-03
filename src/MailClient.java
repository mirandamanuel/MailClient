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
    private Label fromLabel;
    private Label toLabel;
    private Label subject;
    private TextField fromEmail;
    private TextField toEmail;
    private TextField subjectTextField;
    private TextArea emailMessage;
    private Label emailSent;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Create Labels, TextFields, TextArea, and Button
        fromLabel = new Label("From: ");
        toLabel = new Label("To: ");
        subject = new Label("Subject: ");
        fromEmail = new TextField();
        toEmail = new TextField();
        subjectTextField = new TextField();
        emailMessage = new TextArea();
        emailSent = new Label();
        Button sendButton = new Button("Send");

        //Event handler of sendButton
        sendButton.setOnAction(new SendButtonHandler());

        //Set width of TextFields
        fromEmail.setPrefWidth(430.0);
        toEmail.setPrefWidth(430.0);
        subjectTextField.setPrefWidth(430.0);

        //Create VBox of Labels and TextFields
        VBox vboxLabel = new VBox(20, fromLabel, toLabel, subject);
        VBox vboxTextField = new VBox(10, fromEmail, toEmail, subjectTextField);

        //Create HBox
        HBox hbox = new HBox(10, vboxLabel, vboxTextField);

        //Create VBox
        VBox vbox = new VBox(10, hbox, emailMessage, sendButton, emailSent);

        //Set alignment
        vbox.setAlignment(Pos.CENTER);

        //Set padding
        vbox.setPadding(new Insets(10));

        //Create Scene
        Scene scene = new Scene(vbox);

        //Set Title
        primaryStage.setTitle("Mail Client");

        //Set Scene
        primaryStage.setScene(scene);

        //Display
        primaryStage.show();
    }

    class SendButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            emailSent.setText("Successfully emailed");
        }
    }
}
