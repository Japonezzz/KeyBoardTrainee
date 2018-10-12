package Controller;

import Model.Profile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    private ConnectionClass conn;
    private StringBuilder sb;
    private int curr_level = 0;
    private String path;
    private long time_start = 0;
    private long time_finish = 0;
    private List<Button>  buttns;
    private List<Character> chars;



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

    private void btn_color_change()
    {
        String toArea = text_area.getText();

        for (Button b: buttns) {
            b.setBackground(new Background(new BackgroundFill(Color.web("#abd680"), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        try {
            if (chars.contains(toArea.toUpperCase().charAt(1))) {
                buttns.get(chars.indexOf(toArea.toUpperCase().charAt(1))).setBackground(new Background(new BackgroundFill(Color.web("#00ffff"), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        catch (Exception ignored)
        {}

    }


    @FXML
    void On_key_pressed(KeyEvent event) {

        btn_color_change();

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
        catch (Exception ignored)
        {}

        try {
            if (toArea.charAt(0) == '\n' && event.getCode() == KeyCode.ENTER) {
                String s = toArea.substring(1, toArea.length());
                text_area.setText(s);
            }

        }
        catch (Exception ignored)
        {}
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



        buttns = new ArrayList<>();
        chars = new ArrayList<>();

        buttns.add(btn_a);
        buttns.add(btn_d);
        buttns.add(btn_f);
        buttns.add(btn_g);
        buttns.add(btn_h);
        buttns.add(btn_j);
        buttns.add(btn_k);
        buttns.add(btn_l);
        buttns.add(btn_s);

        for (Button b: buttns
             ) {
            chars.add(b.getText().toUpperCase().charAt(0));
        }

        try {
            init_level();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        path = "file://C:/Users/Japonezzz/IdeaProjects/KeyBoardTrainee/src/main/resources/sound.wav";
//        Media media = new Media(new File(path).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
    }


   private StringBuilder parse_file(String level_path) {

       sb = new StringBuilder();
       try (BufferedReader br = new BufferedReader(new FileReader(level_path))) {

           String line = br.readLine();
           String[] values = line.split(";");
           Integer quantity = Integer.valueOf(values[0]);
           Integer rows = Integer.valueOf(values[1]);

           line = br.readLine();

           for (int i = 0; i < rows; i++) {
               for (int j = 0; j < quantity; j++) {
                   sb.append(line);
                   sb.append(" ");
               }
               sb.append(System.getProperty("line.separator"));
           }

       } catch (IOException e) {
           e.printStackTrace();
       }
       return sb;
   }

   private void fill_text_area(String text)
   {
       text_area.setText(text);
   }

   private void init_level() {

        label_level.setText(String.valueOf(curr_level));

        path = "src/main/resources/"+ String.valueOf(curr_level) + ConnectionClass.language.getLangName().toLowerCase() +
                ConnectionClass.difficulty.getDiffName().toLowerCase() + ".txt";

            fill_text_area(parse_file(path).toString());

            time_start = System.currentTimeMillis();
            btn_color_change();
  }


   private Double get_speed (long start, long finish, String text)
   {
       Double speed ;
       speed = (double) (text.toCharArray().length/((finish - start)/1000))*60;
       return speed;
   }

}
