package tests.com.kjellvos.school.multipleScenesTest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Stack;

/**
 * Created by kjevo on 2/18/17.
 */
public class MainForMultipleScenesTest extends Application {
    private Stage primaryStage;
    private Stack<Scene> scenes = new Stack<Scene>();
    private Scene scene;

    public void changeScene(Scene scene){
        this.scene = scene;
        scenes.push(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void returnToPreviousScene(){
        if (scenes.size() > 1) {
            scenes.pop();
            primaryStage.setScene(scenes.peek());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        scene = scenes.push(new SceneForMultipleScenesTest(this).getScene(1));

        /*
        We set up the primary stage
         */
        primaryStage.setTitle("Multiple Scenes Test");
        primaryStage.setMinWidth(800);
        primaryStage.setWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
