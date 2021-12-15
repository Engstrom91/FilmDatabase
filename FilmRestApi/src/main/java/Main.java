import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main extends Application{
    //Skapar entity manager factory
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox hBSelectTable = new HBox();
        //Result area visar resultatet av queries
        TextArea resultArea = new TextArea();
        Button bSubmitQuery = new Button("Sök");

        //Lägger till knappen och textarea i vår HBox
        hBSelectTable.getChildren().addAll(bSubmitQuery, resultArea);

        //Skapar vår border pane och scene
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(hBSelectTable);
        //empScene står för Employee Scene, där employees kollar upp filmer
        Scene empScene = new Scene(borderPane, 900, 700);
        primaryStage.setScene(empScene);
        primaryStage.show();

    }
}
