import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import views.CreateView;
import views.LogInView;

import javax.persistence.*;
import java.util.List;

public class Main extends Application{

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CreateView createView = new CreateView();
        LogInView logInView = new LogInView();
        //Ska senare aktiveras med en knapp
        logInView.renderLogInView();

        primaryStage.setScene(logInView.getLogInScene());

        primaryStage.show();
    }

}
