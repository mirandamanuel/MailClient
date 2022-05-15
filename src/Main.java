
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.mail.Address;

//https://youtu.be/jm_gVp12h0s?t=1223
//need to create observable list to add data row to table
//https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
public class Main extends Application {

    public Button butInbox;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Mail App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private TableView<EmailMessage> emTable;

    @FXML
    private TableColumn<EmailMessage, String> emDate;

    @FXML
    private TableColumn<EmailMessage, String> emSubject;


    @FXML
    private TableColumn<EmailMessage, String> emFrom;


    @FXML
    private TableColumn<EmailMessage, ArrayList<Addresses>> emTo;


    @FXML
    private TableColumn<EmailMessage, String> emMessage;


    public void inbox(ActionEvent e){

}
public void compose(ActionEvent e){

}

    public static void main(String[] args) {
        launch(args);
    }

    public void handle(MouseEvent mouseEvent) {
    }

    public void inboxButtClicked(MouseEvent mouseEvent) {
    }

    public void composeButtClicked(MouseEvent mouseEvent) {
    }
}
