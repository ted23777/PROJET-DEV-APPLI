package Connection;
	
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class Main extends Application {
	
	private double x = 0;
    private double y = 0;
    
	@Override
	public void start(Stage primaryStage) {
		
		try {
            primaryStage.initStyle(StageStyle.TRANSPARENT);

            // Charger le FXML
            Parent root = FXMLLoader.load(getClass().getResource("Sample2.fxml"));

            // Créer une scène
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            // Ajouter une feuille de style
            scene.getStylesheets().add(getClass().getResource("Design2.css").toExternalForm());

            // Configurer le déplacement de la fenêtre
            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                primaryStage.setX(event.getScreenX() - x);
                primaryStage.setY(event.getScreenY() - y);
            });

            // Créer une transition de fondu
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), root);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

            // Afficher la fenêtre avec une animation de fondu
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
