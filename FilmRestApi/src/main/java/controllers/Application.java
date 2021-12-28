package controllers;

import javafx.stage.Stage;
import views.LogInView;

public class Application extends javafx.application.Application {

    private static Stage primaryStage;
    public static LogInView logInView = new LogInView();
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        logInView.renderLogInView();

        primaryStage.setScene(logInView.getLogInScene());
        primaryStage.show();

    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

}
