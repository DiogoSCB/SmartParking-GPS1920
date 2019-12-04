package views;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.collections.ObservableList;

import models.Data;
import models.User;
import models.UserRow;

import java.sql.Date;

public class ControllerUI {

    private ObservableList<UserRow> usersData;

    public void setData(Data data) {
        this.data = data;
    }

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

    /* Buttons */
    @FXML
    public void aceitarBtnClicked() {
        System.out.println("Aceitar Button clicked.");
    }

    @FXML
    public void rejeitarBtnClicked() {
        System.out.println("Rejeitar Button clicked.");
    }

    @FXML
    public void gravarBtnClicked() {
        System.out.println("Gravar Button clicked.");
    }

    @FXML
    public void sairBtnClicked() {
        System.out.println("Sair Button clicked.");
        /* Save everything */
        Platform.exit();
    }

    /* Tab Selection */
    @FXML
    public void condutoresTabSelected() {

        /* Tabela */
        condutoresTable.setPlaceholder(new Label("Selecione o ID do Parque."));


        /* Atualizar lista dos parques */
        idParqueCondutores.getItems().clear();
        idParqueCondutores.setPromptText("ID do Parque");

        //idParqueCondutores.getItems().add(1); // TODO REMOVE
        //idParqueCondutores.getItems().add(2); // TODO REMOVE

        idParqueCondutores.setOnAction(new NewParkSelectedCallBack());

        // TODO UNCOMMENT WHEN DATABASE IS RUNNING
        for (Integer i : data.getParkIdAsIntegers())
            idParqueCondutores.getItems().add(i);

        System.out.println("Condutores tab selected."); // TODO REMOVE

        /* Hide aceitar e rejeitar */
        aceitarBtn.setVisible(false);
        rejeitarBtn.setVisible(false);

        /* Show gravar */
        gravarBtn.setVisible(true);

    }

    @FXML
    public void pedidosTabSelected() {
        System.out.println("Pedidos tab selected.");
        /* Hide gravar */
        gravarBtn.setVisible(false);

        /* Show aceitar e rejeitar */
        aceitarBtn.setVisible(true);
        rejeitarBtn.setVisible(true);
    }

    @FXML
    public void estatisticasTabSelected() {
        System.out.println("Estatísticas tab selected.");
        /* Hide aceitar, rejeitar, gravar */
        aceitarBtn.setVisible(false);
        rejeitarBtn.setVisible(false);
        gravarBtn.setVisible(false);

        /* Show sair */
        sairBtn.setVisible(true);
    }


    /* CALL BACKS*/
    class NewParkSelectedCallBack implements EventHandler<ActionEvent> {
        NewParkSelectedCallBack() {
        }

        public void handle(ActionEvent actionEvent) {
            System.out.println("Parque selecionado: " + idParqueCondutores.getValue()); // TODO REMOVE
            usersData = data.getUsersByParkID((Integer)idParqueCondutores.getValue()); // TODO UNCOMMENT WHEN DATABASE IS RUNNING

            // User a = new User(1, "joao", "00-AA-00", new Date(0), new Date(500000000), "example@mail.pt", 10, 10);
            //User b = new User(2, "maria", "00-AA-00", new Date(0), new Date(500000000), "example2@mail.pt", 10, 10);

            //usersData = FXCollections.observableArrayList(new UserRow(a), new UserRow(b)); // TODO REMOVE
            condutoresTable.setItems(usersData);
        }
    }

}