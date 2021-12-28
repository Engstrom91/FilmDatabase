package views;

import controllers.TableController;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        private HBox hBTop = new HBox();
        private HBox hBBottom = new HBox();
        private BorderPane borderPane = new BorderPane();
        private Scene logInScene = new Scene(borderPane, 900, 700);

        //Log In sektionen
        private TextField tfUsername = new TextField();
        private TextField tfPassword = new TextField();
        private Label lUsername = new Label("Username");
        private Label lPassword = new Label("Password");

        private Button bAttemptLogIn = new Button("Log In");

        private CreateView createView = new CreateView();

        //Read sektionen
        private TextArea staffResultArea = new TextArea();
        private Button bReadQuery = new Button("Refresh");


        //Lägger till knappen och textarea i vår HBox

        public void renderLogInView(){
            hBTop.getChildren().addAll(lUsername, tfUsername, lPassword, tfPassword, bAttemptLogIn);
            hBBottom.getChildren().addAll(bReadQuery, staffResultArea);
            //Renderar vårde Hboxar och kopplar våra knappar till rätt metoder
            borderPane.setTop(hBTop);
            borderPane.setBottom(hBBottom);
            //Kopplar våra knappar till respektive metoder
            bAttemptLogIn.setOnAction(event -> {
                bAttemptLogIn(staffResultArea);
            });
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

                resultArea.appendText("Usernames\n");
                //Prints out usernames list to resultArea
                for (int i = 0; i < usernames.size(); i++) {
                    resultArea.appendText( i  + ". " + usernames.get(i) + "\n");
                }

                resultArea.appendText("\nPasswords\n");
                //Prints out passwords list to resultArea
                for (int j = 0; j < usernames.size(); j++) {
                    resultArea.appendText( j + ". " + usernames.get(j)  + "\n");
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

    //Vår knapp för att logga in
    private void bAttemptLogIn(TextArea resultArea) {
        EntityManager entityManager = TableController.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        String enteredUsername = tfUsername.getText();
        String enteredPassword = tfPassword.getText();
        boolean correctUsername = false;
        boolean correctPassword = false;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query usernameQuery = entityManager.createNativeQuery("SELECT username FROM staff");
            Query passwordQuery = entityManager.createNativeQuery("SELECT password FROM staff");

            List<String> usernames = usernameQuery.getResultList();
            List<String> passwords = passwordQuery.getResultList();

            resultArea.clear();


            for (String i : usernames) {
                if (enteredUsername.equals(i)){
                    correctUsername = true;
                }
            }

            resultArea.appendText("Passwords" + "\n");
            for (String j : passwords) {
                if (enteredPassword.equals(j)){
                    correctPassword = true;
                }
            }

            if(correctUsername && correctPassword){
                resultArea.clear();
                resultArea.appendText("Logging in");
                controllers.Application.getPrimaryStage().setScene(createView.getCreateScene());
                createView.renderCreateView();
            }
            else {
                resultArea.clear();
                resultArea.appendText("Incorrect username or password");
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

    public HBox gethBBottom() {
        return hBBottom;
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

    public void sethBBottom(HBox hBBottom) {
        this.hBBottom = hBBottom;
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

    public CreateView getCreateView() {
        return createView;
    }

    public void setCreateView(CreateView createView) {
        this.createView = createView;
    }
}
