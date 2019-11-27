package View;

import Models.Constants;
import Models.Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainUI extends Application implements Constants
{

    @Override

    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent root = (Parent)fxmlLoader.load();

        /*
         * A usar o fxml esta é a única forma de passar info para o Controller,
         * porque o contrutor é chamado pelo fxmlLoader e náo dá para lhe passar argumentos.
         */
        ControllerUI controllerUI = fxmlLoader.<ControllerUI>getController();
        controllerUI.setData(new Data());

        primaryStage.setTitle(windowName);
        primaryStage.setScene(new Scene(root, windowWidth, windowHeight));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}