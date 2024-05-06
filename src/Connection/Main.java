package Connection;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	
	private double x = 0;
    private double y = 0;
    
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("SABV");
			
			StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("Sample2.FXML"));
			
			Scene scene = new Scene(root);
	        
	        root.setOnMousePressed((MouseEvent event) ->{
	            x = event.getSceneX();
	            y = event.getSceneY();
	        });
	        
	        root.setOnMouseDragged((MouseEvent event) ->{
	        	primaryStage.setX(event.getScreenX() - x);
	        	primaryStage.setY(event.getScreenY() - y);
	            
	        	primaryStage.setOpacity(.8);
	        });
	        
	        root.setOnMouseReleased((MouseEvent event) ->{
	        	primaryStage.setOpacity(1);
	        });
	        
	        
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			scene.setFill(Color.TRANSPARENT);
	        
			scene.getStylesheets().add(getClass().getResource("Design2.css").toExternalForm());
			
			primaryStage.setScene(scene);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
