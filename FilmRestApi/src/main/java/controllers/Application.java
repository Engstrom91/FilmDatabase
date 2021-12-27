package controllers;

import javafx.stage.Stage;
import views.CreateView;
import views.LogInView;

public class Application extends javafx.application.Application {

    private static Stage primaryStage;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        CreateView createView = new CreateView();
        LogInView logInView = new LogInView();
        //Ska senare aktiveras med en knapp
        logInView.renderLogInView();

        primaryStage.setScene(logInView.getLogInScene());
        primaryStage.show();

    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

}
