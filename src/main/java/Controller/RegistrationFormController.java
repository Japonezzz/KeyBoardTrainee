package Controller;

import Model.User;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationFormController implements Initializable {

    private ConnectionClass conn;
    final FileChooser fileChooser = new FileChooser();
    File file;


    @FXML
    private TextField textfield_nickname;

    @FXML
    private TextField textfield_password;

    @FXML
    private TextField textfield_email;

    @FXML
    private JFXButton choose_button;

    @FXML
    private JFXButton Register_button;

    @FXML
    private JFXButton Exit_button;

    @FXML
    private ImageView image_view;

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    private void Open_scene ()
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXMLLoginForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Keyboard Training");
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

    }

    @FXML
    void On_exit_click(ActionEvent event) {

        Stage stage = (Stage) Exit_button.getScene().getWindow();
        stage.close();
        Open_scene();

    }

    @FXML
    void onRegister_click(ActionEvent event) {
        conn = new ConnectionClass();

        if(textfield_nickname.getText()!="" && textfield_email.getText()!="" && textfield_password.getText()!="")
        {
            User user = new User();
            user.setNickname(textfield_nickname.getText());
            user.setEmail(textfield_email.getText());
            user.setPassword(textfield_password.getText());
            if(file!=null)
            {
                user.setAvatar(file.toURI().toString());
            }

            conn.CreateUser(user);
        }

        Stage stage = (Stage) Register_button.getScene().getWindow();
        stage.close();
        Open_scene();
    }

    @FXML
    void On_action_choose(ActionEvent event) {
        Stage stage = new Stage();
        configureFileChooser(fileChooser);
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
           Image image = new Image(file.toURI().toString());
                image_view.setImage(image);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
