package views;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.collections.ObservableList;

import models.Data;
import models.UserRow;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ControllerUI implements Initializable
{
    private Data data;

    /* Buttons */
    @FXML
    private Button aceitarBtn;
    @FXML
    private Button rejeitarBtn;
    @FXML
    private Button gravarBtn;
    @FXML
    private Button sairBtn;

    /* Tabs */
    @FXML
    private Tab condutoresTab;
    @FXML
    private Tab pedidosTab;
    @FXML
    private Tab estatisticasTab;

    /* Tables */
    @FXML
    private TableView condutoresTable;

    // Columns
    @FXML
    private TableColumn columnIDParque;
    @FXML
    private TableColumn columnIDCondutor;
    @FXML
    private TableColumn columnNome;
    @FXML
    private TableColumn columnMatricula;
    @FXML
    private TableColumn columnIDLugar;
    @FXML
    private TableColumn columnDataEntrada;
    @FXML
    private TableColumn columnDataSaida;
    @FXML
    private TableColumn columnEmail;
    @FXML
    private TableColumn columnOpcoes;

    /* ComboBoxes */
    @FXML
    private ComboBox idParqueCondutores;

    public ControllerUI(Data data) {
        this.data = data;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        updateParkComboBox();

        /* Setup Listeners */
        idParqueCondutores.setOnAction(new NewParkSelectedCallBack());
        condutoresTab.setOnSelectionChanged(new TabSelectionChanged());
        pedidosTab.setOnSelectionChanged(new TabSelectionChanged());
        estatisticasTab.setOnSelectionChanged(new TabSelectionChanged());
        aceitarBtn.setOnAction(new ButtonPressed());
        rejeitarBtn.setOnAction(new ButtonPressed());
        gravarBtn.setOnAction(new ButtonPressed());
        sairBtn.setOnAction(new ButtonPressed());
    }



    void updateParkComboBox() {
        idParqueCondutores.getItems().clear();
        idParqueCondutores.setPromptText("ID do Parque");
        for (Integer i : data.getParkIdAsIntegers())
            idParqueCondutores.getItems().add(i);
    }


    void setupCondutoresTabLayout() {

        condutoresTable.setPlaceholder(new Label("Selecione o ID do Parque."));

        /* Show/hide corresponding buttons */
        aceitarBtn.setVisible(false);
        rejeitarBtn.setVisible(false);
        gravarBtn.setVisible(true);
    }
    void setupPedidosTabLayout() {
        /* Show/hide corresponding buttons */
        gravarBtn.setVisible(false);
        aceitarBtn.setVisible(true);
        rejeitarBtn.setVisible(true);
    }
    void setupEstatisticasTabLayout() {
        /* Show/hide corresponding buttons */
        aceitarBtn.setVisible(false);
        rejeitarBtn.setVisible(false);
        gravarBtn.setVisible(false);
        sairBtn.setVisible(true);
    }



    /* CALL BACKS*/
    class NewParkSelectedCallBack implements EventHandler<ActionEvent> {
        NewParkSelectedCallBack() {}

        public void handle(ActionEvent actionEvent) {
            condutoresTable.setItems(data.getUsersByParkID((Integer)idParqueCondutores.getValue()));
        }
    }

    class ButtonPressed implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(aceitarBtn)) {
                System.out.println("Aceitar Button Pressed.");
            }
            else if (actionEvent.getSource().equals(rejeitarBtn)) {
                System.out.println("Rejeitar Button Pressed.");
            }
            else if (actionEvent.getSource().equals(gravarBtn)) {
                System.out.println("Gravar Button Pressed.");
            }
            else if (actionEvent.getSource().equals(sairBtn)) {
                System.out.println("Sair Button Pressed.");
                Platform.exit();
            }
        }
    }

    class TabSelectionChanged implements EventHandler<Event> {

        @Override
        public void handle(Event event)
        {
            if (condutoresTab.isSelected())
            {
                System.out.println("Condutores Tab Selected.");
                setupCondutoresTabLayout();
                updateParkComboBox();
            }
            else if (pedidosTab.isSelected())
            {
                System.out.println("Pedidos Tab Selected.");
                setupPedidosTabLayout();
            }
            else if (estatisticasTab.isSelected())
            {
                System.out.println("Estatísticas Tab Selected.");
                setupEstatisticasTabLayout();
            }
        }
    }


}