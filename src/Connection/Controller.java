package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    @FXML
    private TextField firstNameField;
    
    @FXML
    private TextField lastNameField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private TextField numberField;
    
    @FXML
    private Button quiz;
    
    @FXML
    private ImageView loadingImageView;
    
    // Créer des tooltips pour les champs vides
    Tooltip firstNameTooltip = new Tooltip("First Name is required");
    Tooltip lastNameTooltip = new Tooltip("Last Name is required");
    Tooltip emailTooltip = new Tooltip("Email is required");
    Tooltip numberTooltip = new Tooltip("Number is required");
    Tooltip userNameTooltip = new Tooltip("UserName is required");
    Tooltip wrong = new Tooltip("Wrong Username/Password");
    Tooltip success = new Tooltip("Information Message");
    
    // Définir les informations de connexion à la base de données MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/inscriptions";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
  
    private double x = 0;
    private double y = 0;
    
    @FXML
    public void handle() throws InterruptedException {
        boolean isValid = true;

        // Vérifier si les champs sont vides et afficher les tooltips appropriés
        if (firstNameField.getText().isEmpty()) {
        	firstNameTooltip.setAutoHide(true);
        	
        	// Obtention de la position du centre de la fenêtre
        	Stage stage = (Stage) main_form.getScene().getWindow();
        	double windowX = stage.getX() - 25 + stage.getWidth() / 2;
        	double windowY = stage.getY() - 25 + stage.getHeight() / 2;

        	// Afficher le tooltip au centre de la fenêtre
        	firstNameTooltip.show(firstNameField, windowX, windowY);

        	 // Créer une transition de pause de 5 secondes
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> firstNameTooltip.hide()); // Masquer le tooltip après la pause
            pause.play();
            isValid = false;
        }else

        if (lastNameField.getText().isEmpty()) {
        	lastNameTooltip.setAutoHide(true);
        	
        	// Obtention de la position du centre de la fenêtre
        	Stage stage = (Stage) main_form.getScene().getWindow();
        	double windowX = stage.getX() - 25 + stage.getWidth() / 2;
        	double windowY = stage.getY() - 25 + stage.getHeight() / 2;

        	// Afficher le tooltip au centre de la fenêtre
        	lastNameTooltip.show(lastNameField, windowX, windowY);

        	 // Créer une transition de pause de 5 secondes
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> lastNameTooltip.hide()); // Masquer le tooltip après la pause
            pause.play();
            isValid = false;
        }else

        if (emailField.getText().isEmpty()) {
        	emailTooltip.setAutoHide(true);
        	
        	// Obtention de la position du centre de la fenêtre
        	Stage stage = (Stage) main_form.getScene().getWindow();
        	double windowX = stage.getX() - 25 + stage.getWidth() / 2;
        	double windowY = stage.getY() - 25 + stage.getHeight() / 2;

        	// Afficher le tooltip au centre de la fenêtre
        	emailTooltip.show(emailField, windowX, windowY);

        	 // Créer une transition de pause de 5 secondes
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> emailTooltip.hide()); // Masquer le tooltip après la pause
            pause.play();
            isValid = false;
        }else

        if (numberField.getText().isEmpty()) {
        	numberTooltip.setAutoHide(true);
        	
        	// Obtention de la position du centre de la fenêtre
        	Stage stage = (Stage) main_form.getScene().getWindow();
        	double windowX = stage.getX() - 25 + stage.getWidth() / 2;
        	double windowY = stage.getY() - 25 + stage.getHeight() / 2;

        	// Afficher le tooltip au centre de la fenêtre
        	numberTooltip.show(numberField, windowX, windowY);

        	 // Créer une transition de pause de 5 secondes
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> numberTooltip.hide()); // Masquer le tooltip après la pause
            pause.play();
            isValid = false;
        }

        // Ajouter l'utilisateur seulement si tous les champs sont remplis
        if (isValid) {
            addUser(firstNameField.getText(), lastNameField.getText(), emailField.getText(), numberField.getText());
        }
    }
    

    // Méthode pour ajouter un utilisateur à la base de données
    private void addUser(String firstName, String lastName, String email, String number) {

    	try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
    		String sql = "INSERT INTO Users (FirstName, LastName, Email, Number) VALUES (?, ?, ?, ?)";
    		PreparedStatement statement = connection.prepareStatement(sql);
    		statement.setString(1, firstName);
    		statement.setString(2, lastName);
    		statement.setString(3, email);
    		statement.setString(4, number);
    		statement.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
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
          
          if(username.getText().isEmpty()){
        	  success.setAutoHide(true);
          	
          		// Obtention de la position du centre de la fenêtre
          		Stage stage = (Stage) main_form.getScene().getWindow();
          		double windowX = stage.getX() - 25 + stage.getWidth() / 2;
          		double windowY = stage.getY() - 25 + stage.getHeight() / 2;

          		// Afficher le tooltip au centre de la fenêtre
          		success	.show(username, windowX, windowY);

          		// Créer une transition de pause de 5 secondes
          		PauseTransition pause = new PauseTransition(Duration.seconds(3));
          		pause.setOnFinished(event -> success.hide()); // Masquer le tooltip après la pause;
                username.setText("");
                password.setText("");
          		pause.play();
          }else{
              if(isValidAdmin(usernameS, passwordS)){
            	  
            	  GetData.username = username.getText();
                  
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
            	  	wrong.setAutoHide(true);
                	
            		// Obtention de la position du centre de la fenêtre
            		Stage stage = (Stage) main_form.getScene().getWindow();
            		double windowX = stage.getX() - 25 + stage.getWidth() / 2;
            		double windowY = stage.getY() - 25 + stage.getHeight() / 2;

            		// Afficher le tooltip au centre de la fenêtre
            		wrong.show(username, windowX, windowY);
            		// Créer une transition de pause de 5 secondes
            	  PauseTransition pause = new PauseTransition(Duration.seconds(3));
            	  pause.setOnFinished(event -> wrong.hide()); // Masquer le tooltip après la pause;
                  username.setText("");
                  password.setText("");
                  pause.play();
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
