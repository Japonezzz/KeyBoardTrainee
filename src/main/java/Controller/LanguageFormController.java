package Controller;

import Model.Language;
import Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LanguageFormController implements Initializable {

    ConnectionClass conn;

    @FXML
    private Label label_nickname;

    @FXML
    private JFXComboBox<String> combo_diff;

    @FXML
    private JFXComboBox<String> combo_languages;

    @FXML
    private JFXButton button_GO;

    @FXML
    private JFXButton close_button;

    @FXML
    void On_GO(ActionEvent event) {

        conn = new ConnectionClass();

        next_form();

    }

    @FXML
    void On_close(ActionEvent event) {

        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
        System.exit(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboAndUser();
    }


    private void initComboAndUser ()
    {
        conn = new ConnectionClass();

        ObservableList<String> langs = FXCollections.observableArrayList(conn.Get_languages());
        combo_languages.setItems(langs);
        combo_languages.getSelectionModel().select(0);

        ObservableList<String> diffs = FXCollections.observableArrayList(conn.Get_difficulties());
        combo_diff.setItems(diffs);
        combo_diff.getSelectionModel().select(0);

        label_nickname.setText(conn.user.getNickname());
    }

    private void next_form ()
    {
        conn.get_language_by_name(combo_languages.getSelectionModel().getSelectedItem());
        conn.get_difficulty_by_name(combo_diff.getSelectionModel().getSelectedItem());

        String path = "";

        if(combo_languages.getSelectionModel().getSelectedIndex() == 0)
            path = "/FXMLMainFormeng.fxml";
        else if (combo_languages.getSelectionModel().getSelectedIndex() == 1)
            path = "/FXMLMainFormru.fxml";

        Stage stage = (Stage) button_GO.getScene().getWindow();
        stage.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);

        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("THE GAME");
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
