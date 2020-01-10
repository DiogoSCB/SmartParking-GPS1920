package views;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import models.Data;

import java.sql.SQLException;

import static models.Constants.*;

public class MainUI extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_FILE_NAME));

        try {
            fxmlLoader.setController(new ControllerUI(new Data()));
        } catch (SQLException e)
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Base de Dados não encontrada.");
            a.setContentText("Certifique-se de que a base de dados está em funcionamento.");
            a.show();
            return;
        }

        Parent root = fxmlLoader.load();

        primaryStage.setTitle(WINDOW_NAME);
        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
