package paycore.robust;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApp extends Application {
    private static Stage mainStage; //After Login This Page Loginpa

    /*Login User DB */
    private final static String url = "jdbc:postgresql://LSR_IST4PSQLOCN.cardtek.local:5432/OCEANDEV";
    private final static String user = "ocn_app";
    private final static String password = "ocn_app";
    @Override
    public void start(Stage loginStage) throws IOException {
        System.out.println("starttayiz");
        mainStage = loginStage;
        loginStage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login.fxml"));
        loginStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(fxmlLoader.load(), 600, 407);
        loginStage.setScene(scene);
        loginStage.show();
    }
    public void changeScene(String fxml) throws IOException {
        mainStage.setResizable(false);
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(pane);
        mainStage.setScene(scene);
        mainStage.centerOnScreen();
        mainStage.show();
    }
    public static void main(String[] args) {
        System.out.println("Maindeyiz");
        DatabaseConnection con = new DatabaseConnection();
        con.connect(url,user,password);
        launch();
    }
}