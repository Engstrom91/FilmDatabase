package views;

import controllers.TableController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class CreateView {

    private HBox hBSelectTable = new HBox();
    //Result area visar resultatet av queries
    private TextArea resultArea = new TextArea();
    private Button bSubmitQuery = new Button("Refresh");
    private BorderPane borderPane = new BorderPane();
    private Scene createScene = new Scene(borderPane, 900, 700);

    //Lägger till knappen och textarea i vår HBox
    
    public void renderCreateView(){
        hBSelectTable.getChildren().addAll(bSubmitQuery, resultArea);
        //Skapar vår border pane och scene
        borderPane.setLeft(hBSelectTable);

        bSubmitQuery.setOnAction(event -> {
            bReadTable(resultArea);
        });
    }

    //Vår knapp för att läsa av ett table
    private void bReadTable(TextArea resultArea){
        EntityManager entityManager = TableController.ENTITY_MANAGER_FACTORY.createEntityManager();
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

    public CreateView(){
        
    }

    public HBox gethBSelectTable() {
        return hBSelectTable;
    }

    public TextArea getResultArea() {
        return resultArea;
    }

    public Button getbSubmitQuery() {
        return bSubmitQuery;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public Scene getCreateScene() {
        return createScene;
    }

    public void sethBSelectTable(HBox hBSelectTable) {
        this.hBSelectTable = hBSelectTable;
    }

    public void setResultArea(TextArea resultArea) {
        this.resultArea = resultArea;
    }

    public void setbSubmitQuery(Button bSubmitQuery) {
        this.bSubmitQuery = bSubmitQuery;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setCreateScene(Scene createScene) {
        this.createScene = createScene;
    }
}
