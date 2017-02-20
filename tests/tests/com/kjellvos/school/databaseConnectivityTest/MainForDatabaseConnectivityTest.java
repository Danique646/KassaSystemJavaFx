package tests.com.kjellvos.school.databaseConnectivityTest;

import javafx.stage.Stage;

/**
 * Created by kjevo on 2/20/17.
 */
public class MainForDatabaseConnectivityTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new SceneForDatabaseConnectivityTest();

        /*
        We set up the primary stage
         */
        primaryStage.setTitle("Hello World");
        primaryStage.setMinWidth(800);
        primaryStage.setWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
