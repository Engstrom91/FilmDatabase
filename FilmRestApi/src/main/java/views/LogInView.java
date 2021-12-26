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
import java.util.Arrays;
import java.util.List;

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

public class LogInView {


        private HBox hBSelectTable = new HBox();
        //Result area visar resultatet av queries
        private TextArea staffResultArea = new TextArea();
        private Button bReadQuery = new Button("Refresh");
        private BorderPane borderPane = new BorderPane();
        private Scene logInScene = new Scene(borderPane, 900, 700);

        //Lägger till knappen och textarea i vår HBox

        public void renderLogInView(){
            hBSelectTable.getChildren().addAll(bReadQuery, staffResultArea);
            //Skapar vår border pane och scene
            borderPane.setLeft(hBSelectTable);

            bReadQuery.setOnAction(event -> {
                bReadTable(staffResultArea);
            });
        }

        //Vår knapp för att läsa av ett table
        private void bReadTable(TextArea resultArea) {
            EntityManager entityManager = TableController.ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();

                Query usernameQuery = entityManager.createNativeQuery("SELECT username FROM staff");
                Query passwordQuery = entityManager.createNativeQuery("SELECT password FROM staff");

                List<String> usernames = usernameQuery.getResultList();
                List<String> passwords = passwordQuery.getResultList();

                resultArea.clear();

                //Prints out usernames list to resultArea
                for (String i : usernames) {
                    resultArea.appendText(i + "\n");
                }

                //Prints out passwords list to resultArea
                for (String j : passwords) {
                    resultArea.appendText(j + "\n");
                }


                transaction.commit();

            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                entityManager.close();
            }
        }

    public LogInView() {
    }

    public HBox gethBSelectTable() {
        return hBSelectTable;
    }

    public TextArea getStaffResultArea() {
        return staffResultArea;
    }

    public Button getbReadQuery() {
        return bReadQuery;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public Scene getLogInScene() {
        return logInScene;
    }

    public void sethBSelectTable(HBox hBSelectTable) {
        this.hBSelectTable = hBSelectTable;
    }

    public void setStaffResultArea(TextArea staffResultArea) {
        this.staffResultArea = staffResultArea;
    }

    public void setbReadQuery(Button bReadQuery) {
        this.bReadQuery = bReadQuery;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setLogInScene(Scene logInScene) {
        this.logInScene = logInScene;
    }
}
