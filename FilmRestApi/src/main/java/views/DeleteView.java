package views;

import controllers.Application;
import controllers.TableController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class DeleteView {

    private HBox hBCenter = new HBox();
    private HBox hBLanguage = new HBox();
    private HBox hBActor = new HBox();

    private Button bSwitchToCreateView = new Button("Make an entry");
    private Button bDeleteLanguage = new Button("Delete Language");
    private TextField tf_language_id = new TextField();

    private BorderPane borderPane = new BorderPane();

    private Scene deleteScene = new Scene(borderPane, 900, 700);

    //Lägger till knappar och text fields i vår borderpane
    public void renderDeleteView(){

        tf_language_id.setPromptText("Language Id");

        hBLanguage.getChildren().addAll(tf_language_id, bDeleteLanguage);
        hBCenter.getChildren().addAll(hBLanguage);

        //Skapar vår border pane och scene
        borderPane.setCenter(hBCenter);
        borderPane.setTop(bSwitchToCreateView);


        bSwitchToCreateView.setOnAction(event -> {
            switchToCreateView();
            tf_language_id.clear();
        });

        bDeleteLanguage.setOnAction(event -> {
            deleteLanguage();
        });

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
