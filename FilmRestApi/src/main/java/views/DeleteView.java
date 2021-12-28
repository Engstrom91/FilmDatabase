package views;

import controllers.Application;
import controllers.TableController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import static views.CreateView.updateView;

public class DeleteView {

    //Hboxar
    private HBox hBTop = new HBox();
    private HBox hBCenter = new HBox();
    private HBox hBActor = new HBox();
    private HBox hBLanguage = new HBox();


    //Buttons
    private Button bDeleteActor = new Button("Delete Actor");
    private Button bDeleteLanguage = new Button("Delete Language");
    private Button bSwitchToCreateView = new Button("Make An Entry");
    private Button bSwitchToUpdateView = new Button("Make An Update");


    private BorderPane borderPane = new BorderPane();
    private Scene deleteScene = new Scene(borderPane, 1200, 700);

    //Text Fields
    private TextField tf_language_id = new TextField();
    private TextField tf_actor_id = new TextField();

    //Lägger till knappar och text fields i vår borderpane
    public void renderDeleteView(){

        tf_actor_id.setPromptText("Actor Id");
        tf_language_id.setPromptText("Language Id");

        hBActor.getChildren().addAll(tf_actor_id, bDeleteActor);
        hBLanguage.getChildren().addAll(tf_language_id, bDeleteLanguage);
        hBTop.getChildren().addAll(bSwitchToCreateView, bSwitchToUpdateView);
        hBCenter.getChildren().addAll(hBActor, hBLanguage);

        //Skapar vår border pane och scene
        borderPane.setCenter(hBCenter);
        borderPane.setTop(hBTop);

        //Kopplar metoder till actions på knappar
        bDeleteActor.setOnAction(event -> {
            deleteActor();
        });

        bDeleteLanguage.setOnAction(event -> {
            deleteLanguage();
        });

        bSwitchToCreateView.setOnAction(event -> {
            switchToCreateView();
            tf_language_id.clear();
        });

        bSwitchToUpdateView.setOnAction(event -> {
            controllers.Application.getPrimaryStage().setScene(updateView.getUpdateScene());
            updateView.renderUpdateView();
        });

    }

    //Låter oss ta bort en actor genom att skicka in dess Id
    private void deleteActor() {
        EntityManager entityManager = TableController.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        int enteredId = Integer.parseInt(tf_actor_id.getText());

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query deleteActorQuery = entityManager.createNativeQuery("DELETE FROM actor WHERE actor_id=" + enteredId);
            deleteActorQuery.executeUpdate();
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

    //Låter oss ta bort ett language genom att skicka in dess id
    private void deleteLanguage() {
        EntityManager entityManager = TableController.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        int enteredId = Integer.parseInt(tf_language_id.getText());

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query deleteLanguageQuery = entityManager.createNativeQuery("DELETE FROM language WHERE language_id=" + enteredId);
            deleteLanguageQuery.executeUpdate();
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

    public void switchToCreateView(){
        controllers.Application.getPrimaryStage().setScene(Application.logInView.getCreateView().getCreateScene());
        Application.logInView.getCreateView().renderCreateView();
    }

    public DeleteView() {
    }

    public HBox gethBCenter() {
        return hBCenter;
    }

    public void sethBCenter(HBox hBCenter) {
        this.hBCenter = hBCenter;
    }

    public HBox gethBLanguage() {
        return hBLanguage;
    }

    public void sethBLanguage(HBox hBLanguage) {
        this.hBLanguage = hBLanguage;
    }

    public HBox gethBActor() {
        return hBActor;
    }

    public void sethBActor(HBox hBActor) {
        this.hBActor = hBActor;
    }

    public Button getbDeleteLanguage() {
        return bDeleteLanguage;
    }

    public void setbDeleteLanguage(Button bDeleteLanguage) {
        this.bDeleteLanguage = bDeleteLanguage;
    }

    public TextField getTf_language_id() {
        return tf_language_id;
    }

    public void setTf_language_id(TextField tf_language_id) {
        this.tf_language_id = tf_language_id;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public Scene getDeleteScene() {
        return deleteScene;
    }

    public void setDeleteScene(Scene deleteScene) {
        this.deleteScene = deleteScene;
    }
}
