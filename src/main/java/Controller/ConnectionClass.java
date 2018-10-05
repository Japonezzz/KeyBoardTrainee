package Controller;

import Model.*;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionClass {

    public static User user = new User();
    public static Language language = new Language();
    //public static Level level = new Level();
    public static Difficulty difficulty = new Difficulty();


//    public static Language getLanguage() {
//        return language;
//    }
//
//    public static void setLanguage(Language language) {
//        ConnectionClass.language = language;
//    }

    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    

    private  <T> void AddType (T type)
    {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(type);
        session.getTransaction().commit();
        session.close();
    }

    public void CreateUser(User user) {AddType(user);}

    public User Login (String nick, String pass)
    {
        User user ;
        Session session = factory.openSession();
        try {
            user = session.createQuery("from User one where one.nickname like '%"+nick+"%' and one.password like '%"+pass+"%'" , User.class).getSingleResult();
        }
        catch (Exception ex)
        {
            user = null;
        }

        session.close();

        this.user = user;
        return user;
    }

    public List<String> Get_languages ()
    {
        List<Language> langs;
        Session session = factory.openSession();
        langs = session.createQuery("from Language ", Language.class).list();

        session.close();

        List<String> lang = new ArrayList<>();
        for (Language lng: langs
             ) {
            lang.add(lng.getLangName());
        }
        return lang;
    }

    public List<String> Get_difficulties ()
    {
        List<Difficulty> diffs;
        Session session = factory.openSession();
        diffs = session.createQuery("from Difficulty ", Difficulty.class).list();

        session.close();

        List<String> diff = new ArrayList<>();
        for (Difficulty d: diffs
                ) {
            diff.add(d.getDiffName());
        }
        return diff;
    }


    public int get_level_id ( String path)
    {
        Level level;
        Session session = factory.openSession();
        level = session.createQuery("from Level lvl where lvl.lvlFilePath like '%"+path+"%'" , Level.class).getSingleResult();

        session.close();

        return level.getId();
    }

    public void get_language_by_name (String name)
    {
        Session session = factory.openSession();
        language = session.createQuery("from Language lng where lng.langName like '%"+name+"%'" , Language.class).getSingleResult();

        session.close();
    }

    public void get_difficulty_by_name (String name)
    {
        Session session = factory.openSession();
        difficulty = session.createQuery("from Difficulty dif where dif.diffName like '%"+name+"%'" , Difficulty.class).getSingleResult();

        session.close();
    }

    public void Add_profile(Profile profile)
    {
        AddType(profile);
    }


    public void start_lang_frm(JFXButton buttn) {
        Stage stage = (Stage) buttn.getScene().getWindow();
        stage.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXMLLanguageForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);


        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Language");
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
