package Controller;

import Model.Profile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    ConnectionClass conn;
    StringBuilder sb;
    private int curr_level = 0;
    String path;
    long time_start = 0;
    long time_finish = 0;



    @FXML
    private Label label_nickname;

    @FXML
    private Label label_level;

    @FXML
    private JFXTextArea text_area;

    @FXML
    private JFXButton btn_a;

    @FXML
    private JFXButton btn_s;

    @FXML
    private JFXButton btn_d;

    @FXML
    private JFXButton btn_f;

    @FXML
    private JFXButton btn_g;

    @FXML
    private JFXButton btn_h;

    @FXML
    private JFXButton btn_j;

    @FXML
    private JFXButton btn_k;

    @FXML
    private JFXButton btn_l;

    @FXML
    void Push_letter_button(ActionEvent event) {

    }


    @FXML
    void On_key_pressed(KeyEvent event) throws IOException {

         String toArea = text_area.getText();


        if(toArea.length() == 0 || (toArea.charAt(0)=='\n' && toArea.length()==1))
        {
            time_finish = System.currentTimeMillis();

            add_profile(get_speed(time_start, time_finish, sb.toString()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations!");
            alert.setContentText("You passed this level!");
            alert.setHeaderText(null);
            alert.showAndWait();
            curr_level++;
            if (curr_level == 4)
            {
               conn.start_lang_frm(btn_a);
               return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            init_level();
        }
        try {
            if (event.getText().toCharArray()[0] == toArea.charAt(0)) {

                String s = toArea.substring(1, toArea.length());
                text_area.setText(s);
            }
       }
        catch (Exception ex)
        {}

        if(toArea.charAt(0)=='\n'&& event.getCode()==KeyCode.ENTER)
        {
            String s = toArea.substring(1, toArea.length());
            text_area.setText(s);
        }

    }


    private void add_profile (Double speed)
    {
        Profile profile = new Profile();
        profile.setUserId(ConnectionClass.user.getId());
        profile.setLevelId(conn.get_level_id(path));
        profile.setLanguageId(ConnectionClass.language.getId());
        profile.setDifficultyId(ConnectionClass.difficulty.getId());
        profile.setTypeSpeed(speed);
        conn.Add_profile(profile);
    }




    public void initialize(URL location, ResourceBundle resources) {

        conn = new ConnectionClass();
        label_nickname.setText(ConnectionClass.user.getNickname());
        curr_level = 1;
        try {
            init_level();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   public StringBuilder parse_file(String level_path) throws IOException {

       sb = new StringBuilder();
       BufferedReader br = new BufferedReader(new FileReader(level_path));
       try {

           String line = br.readLine();
           String [] values = line.split(";");
           Integer quantity = Integer.valueOf(values[0]);
           Integer rows = Integer.valueOf(values[1]);

               line = br.readLine();

           for( int i = 0; i<rows; i++) {
               for (int j = 0; j < quantity; j++) {
                   sb.append(line);
                   sb.append(" ");
               }
               sb.append(System.getProperty("line.separator"));
           }

       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           br.close();
       }
       return sb;
   }

   public void fill_text_area (String text)
   {
       text_area.setText(text);
   }

   public void init_level () throws IOException {

        label_level.setText(String.valueOf(curr_level));

        path = "src/main/resources/"+ String.valueOf(curr_level) + ConnectionClass.language.getLangName().toLowerCase() +
                ConnectionClass.difficulty.getDiffName().toLowerCase() + ".txt";

            fill_text_area(parse_file(path).toString());

            time_start = System.currentTimeMillis();
   }


   private Double get_speed (long start, long finish, String text)
   {
       Double speed ;
       speed = (double) (text.toCharArray().length/((finish - start)/1000))*60;
       return speed;
   }

}
