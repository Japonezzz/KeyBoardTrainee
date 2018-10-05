package Controller;

import Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    ConnectionClass conn;

    @FXML
    private JFXTextField Textfield_Login;

    @FXML
    private JFXTextField Textfield_password;

    @FXML
    private JFXButton Apply_button;

    @FXML
    private JFXButton Register_button;

    @FXML
    void Apply_click(ActionEvent event) {

        User user = null;


        if(Textfield_Login.getText()!="" && Textfield_Login.getText() !="")
        { 
            conn = new ConnectionClass();
            user =  conn.Login(Textfield_Login.getText(), Textfield_Login.getText());
        }
        if(user!=null)
        {
           conn.start_lang_frm(Apply_button);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Login(nickname) or password is incorrect!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    void Registration_click(ActionEvent event) {

        Stage stage = (Stage) Register_button.getScene().getWindow();
        stage.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXMLRegisterForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);


        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registration");
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }


//    private void click( String resource, String title)
//    {
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource(resource));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Scene scene = new Scene(root);
//
//
//        Stage primaryStage = new Stage();
//        primaryStage.setScene(scene);
//        primaryStage.setTitle(title);
//        primaryStage.setResizable(false);
//        primaryStage.show();
//
//        primaryStage.setOnCloseRequest(e -> {
//            Platform.exit();
//            System.exit(0);
//        });
//    }


    public void initialize(URL location, ResourceBundle resources) {

    }
}
