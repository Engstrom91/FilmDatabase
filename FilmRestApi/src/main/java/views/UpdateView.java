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

public class UpdateView {
    //Hboxar
    private HBox hBTop = new HBox();
    private HBox hBCenter = new HBox();
    private HBox hBActor = new HBox();
    private HBox hBLanguage = new HBox();

    //Knappar
    private Button bUpdateActor = new Button("Update Actor");
    private Button bUpdateLanguage = new Button("Update Language");
    private Button bSwitchToCreateView = new Button("Make An Entry");
    private Button bSwitchToDeleteView = new Button("Delete Menu");
    private BorderPane borderPane = new BorderPane();

    //Scen
    private Scene updateScene = new Scene(borderPane, 1200, 700);

    //Text Fields
    private TextField tf_actor_id = new TextField();
    private TextField tf_actor_firstName = new TextField();
    private TextField tf_actor_lastName = new TextField();
    private TextField tf_language_id = new TextField();
    private TextField tf_language_name = new TextField();

    //Lägger till knappar och text fields i vår borderpane
    public void renderUpdateView(){
        tf_actor_id.setPromptText("Actor id");
        tf_actor_firstName.setPromptText("First Name");
        tf_actor_lastName.setPromptText("Last Name");

        tf_language_id.setPromptText("Language Id");
        tf_language_name.setPromptText("Language Name");

        hBActor.getChildren().addAll(tf_actor_id, tf_actor_firstName, tf_actor_lastName, bUpdateActor);
        hBLanguage.getChildren().addAll(tf_language_id, tf_language_name, bUpdateLanguage);
        hBTop.getChildren().addAll(bSwitchToCreateView, bSwitchToDeleteView);
        hBCenter.getChildren().addAll(hBActor, hBLanguage);

        borderPane.setCenter(hBCenter);
        borderPane.setTop(hBTop);


        //Kopplar metoder till actions på knappar

        bUpdateActor.setOnAction(event -> {
            updateActor();
            tf_actor_id.clear();
            tf_actor_firstName.clear();
            tf_actor_lastName.clear();
        });

        bSwitchToCreateView.setOnAction(event -> {
            controllers.Application.getPrimaryStage().setScene(Application.logInView.getCreateView().getCreateScene());
            Application.logInView.getCreateView().renderCreateView();
        });

        bSwitchToDeleteView.setOnAction(event -> {
            controllers.Application.getPrimaryStage().setScene(views.CreateView.deleteView.getDeleteScene());
            views.CreateView.deleteView.renderDeleteView();
        });
    }

    //Låter oss uppdatera en actor genom att skicka in dess Id
    private void updateActor() {
        EntityManager entityManager = TableController.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        int enteredId = Integer.parseInt(tf_actor_id.getText());
        String firstName = tf_actor_firstName.getText();
        String lastName = tf_actor_lastName.getText();

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query updateActorQuery = entityManager.createNativeQuery("UPDATE actor SET first_name = '" + firstName + "', last_name = '" + lastName + "' WHERE actor_id=" + enteredId);
            updateActorQuery.executeUpdate();
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

    public UpdateView() {
    }

    public HBox gethBCenter() {
        return hBCenter;
    }

    public void sethBCenter(HBox hBCenter) {
        this.hBCenter = hBCenter;
    }

    public HBox gethBActor() {
        return hBActor;
    }

    public void sethBActor(HBox hBActor) {
        this.hBActor = hBActor;
    }

    public HBox gethBLanguage() {
        return hBLanguage;
    }

    public void sethBLanguage(HBox hBLanguage) {
        this.hBLanguage = hBLanguage;
    }

    public Button getbUpdateActor() {
        return bUpdateActor;
    }

    public void setbUpdateActor(Button bUpdateActor) {
        this.bUpdateActor = bUpdateActor;
    }

    public Button getbUpdateLanguage() {
        return bUpdateLanguage;
    }

    public void setbUpdateLanguage(Button bUpdateLanguage) {
        this.bUpdateLanguage = bUpdateLanguage;
    }

    public Button getbSwitchToCreateView() {
        return bSwitchToCreateView;
    }

    public void setbSwitchToCreateView(Button bSwitchToCreateView) {
        this.bSwitchToCreateView = bSwitchToCreateView;
    }

    public Button getbSwitchToDeleteView() {
        return bSwitchToDeleteView;
    }

    public void setbSwitchToDeleteView(Button bSwitchToDeleteView) {
        this.bSwitchToDeleteView = bSwitchToDeleteView;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public Scene getUpdateScene() {
        return updateScene;
    }

    public void setUpdateScene(Scene updateScene) {
        this.updateScene = updateScene;
    }

    public TextField getTf_actor_id() {
        return tf_actor_id;
    }

    public void setTf_actor_id(TextField tf_actor_id) {
        this.tf_actor_id = tf_actor_id;
    }

    public TextField getTf_actor_firstName() {
        return tf_actor_firstName;
    }

    public void setTf_actor_firstName(TextField tf_actor_firstName) {
        this.tf_actor_firstName = tf_actor_firstName;
    }

    public TextField getTf_actor_lastName() {
        return tf_actor_lastName;
    }

    public void setTf_actor_lastName(TextField tf_actor_lastName) {
        this.tf_actor_lastName = tf_actor_lastName;
    }

    public TextField getTf_language_id() {
        return tf_language_id;
    }

    public void setTf_language_id(TextField tf_language_id) {
        this.tf_language_id = tf_language_id;
    }

    public TextField getTf_language_name() {
        return tf_language_name;
    }

    public void setTf_language_name(TextField tf_language_name) {
        this.tf_language_name = tf_language_name;
    }
}
