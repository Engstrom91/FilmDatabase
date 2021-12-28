package views;

import controllers.Application;
import controllers.TableController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.util.List;

import static views.CreateView.deleteView;
import static views.CreateView.updateView;

public class ReadView {
    private HBox hBTop = new HBox();
    private VBox vBLeft = new VBox();
    private HBox hBBottom = new HBox();

    private TextArea resultArea = new TextArea();

    private Button bAllActor = new Button("Show All Actors");
    private Button bAllLanguage = new Button("Show All Languages");
    private Button bSwitchToCreateView = new Button("Make An Entry");
    private Button bSwitchToUpdateView = new Button("Make An Update");
    private Button bSwitchToDeleteView = new Button("Delete Menu");
    private BorderPane borderPane = new BorderPane();

    private Scene readScene = new Scene(borderPane, 1200, 700);

    public void renderReadView(){

        hBTop.getChildren().addAll(bSwitchToCreateView, bSwitchToUpdateView, bSwitchToDeleteView);
        vBLeft.getChildren().addAll(bAllActor, bAllLanguage);
        hBBottom.getChildren().addAll(resultArea);

        borderPane.setTop(hBTop);
        borderPane.setBottom(hBBottom);
        borderPane.setLeft(vBLeft);

        //Kopplar metoder till actions på knappar
        bAllActor.setOnAction(event -> {
            bReadActorTable();
        });

        bSwitchToCreateView.setOnAction(event -> {
            controllers.Application.getPrimaryStage().setScene(Application.logInView.getCreateView().getCreateScene());
            Application.logInView.getCreateView();
        });

        bSwitchToUpdateView.setOnAction(event -> {
            controllers.Application.getPrimaryStage().setScene(updateView.getUpdateScene());
            updateView.renderUpdateView();
        });

        bSwitchToDeleteView.setOnAction(event -> {
            controllers.Application.getPrimaryStage().setScene(deleteView.getDeleteScene());
            deleteView.renderDeleteView();
        });
    }

    //Metoden låter oss hämta all data från Actor table
    private void bReadActorTable() {
        EntityManager entityManager = TableController.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query idQuery = entityManager.createNativeQuery("SELECT actor_id FROM actor ORDER BY actor_id ASC");
            Query firstNameQuery = entityManager.createNativeQuery("SELECT first_name FROM actor");
            Query lastNameQuery = entityManager.createNativeQuery("SELECT last_name FROM actor");

            List<Integer> ids = idQuery.getResultList();
            List<String> firstNames = firstNameQuery.getResultList();
            List<String> lastNames = lastNameQuery.getResultList();

            resultArea.clear();

            //Prints out all lists to resultArea
            for (int i = 0; i < ids.size(); i++) {
                resultArea.appendText(ids.get(i)  + " ! " + firstNames.get(i) + " ! " + lastNames.get(i) + "\n");
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

    public ReadView() {

    }

    public HBox gethBTop() {
        return hBTop;
    }

    public void sethBTop(HBox hBTop) {
        this.hBTop = hBTop;
    }

    public VBox getvBLeft() {
        return vBLeft;
    }

    public void setvBLeft(VBox vBLeft) {
        this.vBLeft = vBLeft;
    }


    public Button getbAllActor() {
        return bAllActor;
    }

    public void setbAllActor(Button bAllActor) {
        this.bAllActor = bAllActor;
    }

    public Button getbAllLanguage() {
        return bAllLanguage;
    }

    public void setbAllLanguage(Button bAllLanguage) {
        this.bAllLanguage = bAllLanguage;
    }

    public Button getbSwitchToCreateView() {
        return bSwitchToCreateView;
    }

    public void setbSwitchToCreateView(Button bSwitchToCreateView) {
        this.bSwitchToCreateView = bSwitchToCreateView;
    }

    public Button getbSwitchToUpdateView() {
        return bSwitchToUpdateView;
    }

    public void setbSwitchToUpdateView(Button bSwitchToUpdateView) {
        this.bSwitchToUpdateView = bSwitchToUpdateView;
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

    public Scene getReadScene() {
        return readScene;
    }

    public void setReadScene(Scene readScene) {
        this.readScene = readScene;
    }
}
