package views;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Actor;
import models.Language;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static controllers.TableController.ENTITY_MANAGER_FACTORY;

public class CreateView {

    public static DeleteView deleteView = new DeleteView();
    public static UpdateView updateView = new UpdateView();
    public static ReadView readView = new ReadView();

    private HBox hBTop = new HBox();
    private VBox vBCenter = new VBox();
    private HBox hBActor = new HBox();
    private HBox hBLanguage = new HBox();

    private Button bCreateActor = new Button("Add Actor");
    private Button bCreateLanguage = new Button("Add Language");
    private Button bSwitchToReadView = new Button("Search Data");
    private Button bSwitchToDeleteView = new Button("Delete Menu");
    private Button bSwitchToUpdateView = new Button("Make An Update");
    private BorderPane borderPane = new BorderPane();

    private Scene createScene = new Scene(borderPane, 1200, 700);

    //Text Fields
    private TextField tf_actor_firstName = new TextField();
    private TextField tf_actor_lastName = new TextField();
    private TextField tf_language_name = new TextField();

    //Lägger till knappar och text fields i vår borderpane
    public void renderCreateView(){
        tf_actor_firstName.setPromptText("First Name");
        tf_actor_lastName.setPromptText("Last Name");

        tf_language_name.setPromptText("Language Name");

        hBActor.getChildren().addAll(tf_actor_firstName, tf_actor_lastName, bCreateActor);
        hBLanguage.getChildren().addAll(tf_language_name, bCreateLanguage);
        vBCenter.getChildren().addAll(hBActor, hBLanguage);
        hBTop.getChildren().addAll(bSwitchToReadView, bSwitchToUpdateView, bSwitchToDeleteView);

        borderPane.setCenter(vBCenter);
        borderPane.setTop(hBTop);

        //Kopplar metoder till actions på knappar
        bCreateActor.setOnAction(event -> {
            createActor(tf_actor_firstName, tf_actor_lastName);
            tf_actor_firstName.clear();
            tf_actor_lastName.clear();
        });

        bCreateLanguage.setOnAction(event -> {
            createLanguage(tf_language_name);
            tf_language_name.clear();
        });

        bSwitchToReadView.setOnAction(event -> {
            controllers.Application.getPrimaryStage().setScene(readView.getReadScene());
            readView.renderReadView();
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

    //Lägger till en entry i Actor tabellen
    public void createActor(TextField tfFirstName, TextField tfLastName){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();

            Actor actor = new Actor();
            long millis = System.currentTimeMillis();
            LocalDateTime lastUpdate = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = lastUpdate.format(myFormatObj);

            actor.setFirstName(tfFirstName.getText());
            actor.setLastName(tfLastName.getText());
            actor.setLastUpdate(formattedDate);

            entityManager.persist(actor);
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

    //Lägger till en entry i Language tabellen
    public void createLanguage(TextField tfName){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();

            Language language = new Language();
            long millis = System.currentTimeMillis();
            LocalDateTime lastUpdate = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = lastUpdate.format(myFormatObj);

            language.setLanguageName(tfName.getText());
            language.setLastUpdate(formattedDate);

            entityManager.persist(language);
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

    public Scene getCreateScene() {
        return createScene;
    }

}
