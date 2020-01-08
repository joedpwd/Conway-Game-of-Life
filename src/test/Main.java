package test;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{

	final static int SCALE = 5;
	final static int SIZE = 128;
	
	protected Conway conway;
	
	protected boolean[] buffer = new boolean[SIZE*SIZE];
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Group root = new Group();
		
		Canvas canvas = new Canvas(SIZE * SCALE, SIZE * SCALE);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SCALE*SIZE, SCALE*SIZE);
		
		root.getChildren().add(canvas);
		
		
        Scene scene = new Scene(root, SIZE * SCALE, SIZE * SCALE);
        
		primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        conway = new Conway(SIZE);
        
        Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(Duration.seconds(0.1),
				e -> {
					conway.nextStep();
					drawGraphics(gc);
				});
		timeline.getKeyFrames().add(frame);
		timeline.play();
	}
	
	private void drawGraphics(GraphicsContext gc) {
		buffer = conway.getOldFrame();
		
		for(int i=0; i<SIZE; i++) {
			for (int j=0; j < SIZE; j++) {
					if(buffer[(i*SIZE) + j] == false)
						gc.setFill(Color.BLACK);
					else
						gc.setFill(Color.WHITE);
					gc.fillRect((j*SCALE), (i*SCALE), SCALE, SCALE);
			}
		}
	}

	public static void main(String[] args) {
        launch(args);
    }

}
