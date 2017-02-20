package tests.com.kjellvos.school.multipleScenesTest;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Created by kjevo on 2/18/17.
 */
public class SceneForMultipleScenesTest {
    private MainForMultipleScenesTest main;
    private Pane pane;
    private Scene scene;

    public SceneForMultipleScenesTest(MainForMultipleScenesTest main) {
        this.main = main;
    }

    public Scene getScene(int number){
        pane = new Pane();
        Button button = new Button("GO BACK! to " + (number-1));
        button.setOnMouseClicked(event -> {
            main.returnToPreviousScene();
        });
        pane.getChildren().add(button);
        button = new Button("CONTINUE to number " + (number+1));
        button.relocate(100, 100);
        button.setOnMouseClicked(event -> {
            main.changeScene(this.getScene(number+1));
        });
        pane.getChildren().add(button);
        scene = new Scene(pane);
        return scene;
    }
}
