import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.persistence.*;
import java.util.List;

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

        //Button actions
        bSubmitQuery.setOnAction(event -> {
            bReadTable(resultArea);
        });

        //Vi visar vår scene
        primaryStage.show();
    }
    //Vår knapp för att läsa av ett table
    private void bReadTable(TextArea resultArea){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query query = entityManager.createNativeQuery("SELECT name FROM category");
            List<String> result = query.getResultList();
            resultArea.clear();

            for(String s : result){
                resultArea.appendText(s + "\n");
            }
            transaction.commit();

        }
        catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally{
            entityManager.close();
        }
    }
}
