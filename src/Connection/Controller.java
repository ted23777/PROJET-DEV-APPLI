package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Controller {

	@FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;
    
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button logout;

    @FXML
    private Button logout1;
    
    @FXML
    private Label triangle;
    
    // Définir les informations de connexion à la base de données MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/inscriptions";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
  
    private double x = 0;
    private double y = 0;
    
    public void initialize() {
    	
    	Tooltip tooltip1 = new Tooltip("User Registration");
    	Tooltip tooltip = new Tooltip("Administrator");
    	
    	Tooltip.install(logout, tooltip);
    	Tooltip.install(logout1, tooltip1);
    }
  
    @FXML
    public void loginAdmin(){

        String usernameS = username.getText();
        String passwordS = password.getText();
      
      try{
          
          Alert alert;
          
          if(username.getText().isEmpty() || password.getText().isEmpty()){
              alert = new Alert(AlertType.ERROR);
              alert.setTitle("Error Message");
              alert.setHeaderText(null);
              alert.setContentText("Please fill all blank fields");
              alert.showAndWait();
          }else{
              if(isValidAdmin(usernameS, passwordS)){
                  GetData.username = username.getText();
                  
                  alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Message");
                  alert.setHeaderText(null);
                  alert.setContentText("Successfully Login");
                  alert.showAndWait();
                  
                  loginBtn.getScene().getWindow().hide();
                  StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("/Admin/Sample.fxml"));
                  Stage stage = new Stage();
                  Scene scene = new Scene(root);
                  
                  root.setOnMousePressed((MouseEvent event) ->{
                      x = event.getSceneX();
                      y = event.getSceneY();
                  });
                  
                  root.setOnMouseDragged((MouseEvent event) ->{
                      stage.setX(event.getScreenX() - x);
                      stage.setY(event.getScreenY() - y);
                  });
                  
                  stage.initStyle(StageStyle.TRANSPARENT);
                  stage.setScene(scene);
                  stage.show();
                  
              }else{
                  alert = new Alert(AlertType.ERROR);
                  alert.setTitle("Error Message");
                  alert.setHeaderText(null);
                  alert.setContentText("Wrong Username/Password");
                  alert.showAndWait();
              }
          }
          
      }catch(Exception e){e.printStackTrace();}
      
  }

    public void Sample2(MouseEvent event) {

    	try {
            // Créer une transition de fondu sortant
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), logout1.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                try {
                    // Charger la nouvelle fenêtre
                    Parent root = FXMLLoader.load(getClass().getResource("Sample2.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event1) ->{
                        x = event1.getSceneX();
                        y = event1.getSceneY();
                    });
                    
                    root.setOnMouseDragged((MouseEvent event1) ->{
                        stage.setX(event1.getScreenX() - x);
                        stage.setY(event1.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);
        			scene.setFill(Color.TRANSPARENT);
        			
                    stage.setScene(scene);

                    // Créer une transition de fondu entrant pour la nouvelle fenêtre
                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
                    fadeIn.setFromValue(0.0);
                    fadeIn.setToValue(1.0);
                    fadeIn.play();
        			
                    // Afficher la nouvelle fenêtre
                    stage.show();

                    // Fermer la fenêtre actuelle
                    logout1.getScene().getWindow().hide();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            // Démarrer la transition de fondu sortant
            fadeOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void Sample(MouseEvent event) {

    	try {
            // Créer une transition de fondu sortant
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), logout1.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                try {
                    // Charger la nouvelle fenêtre
                    Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event1) ->{
                        x = event1.getSceneX();
                        y = event1.getSceneY();
                    });
                    
                    root.setOnMouseDragged((MouseEvent event1) ->{
                        stage.setX(event1.getScreenX() - x);
                        stage.setY(event1.getScreenY() - y);
        	            
                        stage.setOpacity(.8);
                    });
        	        
        	        root.setOnMouseReleased((MouseEvent event1) ->{
        	        	stage.setOpacity(1);
        	        });

                    stage.initStyle(StageStyle.TRANSPARENT);
        			scene.setFill(Color.TRANSPARENT);
        			
                    stage.setScene(scene);

                    // Créer une transition de fondu entrant pour la nouvelle fenêtre
                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
                    fadeIn.setFromValue(0.0);
                    fadeIn.setToValue(1.0);
                    fadeIn.play();
        			
                    // Afficher la nouvelle fenêtre
                    stage.show();

                    // Fermer la fenêtre actuelle
                    logout1.getScene().getWindow().hide();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            // Démarrer la transition de fondu sortant
            fadeOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private boolean isValidAdmin(String username, String password) {
        try {
            // Connexion à la base de données
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Préparation de la requête SQL
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Exécution de la requête
            ResultSet resultSet = statement.executeQuery();

            // Vérification si l'administrateur existe
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
   @FXML 
    public void close(){
        System.exit(0);
    }
   
   @FXML
   public void minimize() {
       Stage stage = (Stage) main_form.getScene().getWindow();
       stage.setIconified(true);
   }
}
