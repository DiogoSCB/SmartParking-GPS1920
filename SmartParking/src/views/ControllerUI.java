package views;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.AccessibleAttribute;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import models.Data;
import models.User;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
    private TableView<User> condutoresTable;

    // Columns
    @FXML
    private TableColumn<User, Integer> columnIDParque;
    @FXML
    private TableColumn<User, Integer> columnIDCondutor;
    @FXML
    private TableColumn<User, String> columnNome;
    @FXML
    private TableColumn<User, String> columnMatricula;
    @FXML
    private TableColumn<User, Integer> columnIDLugar;
    @FXML
    private TableColumn<User, Date> columnDataEntrada;
    @FXML
    private TableColumn<User, Date> columnDataSaida;
    @FXML
    private TableColumn<User, String> columnEmail;
    @FXML
    private TableColumn<User, Void> columnOpcoes;

    /* ComboBoxes */
    @FXML
    private ComboBox idParqueCondutores;

    /* Testing */
    private ArrayList<Cell> editingCells;

    public ControllerUI(Data data) {
        this.data = data;
        editingCells = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        initializeColumns();
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

        /* Others */



        /* Setup editable columns */
        columnIDParque.setEditable(true);
        columnIDCondutor.setEditable(true);
        columnNome.setEditable(true);
        columnMatricula.setEditable(true);
        columnIDLugar.setEditable(true);
        columnDataEntrada.setEditable(true);
        columnDataSaida.setEditable(true);
        columnEmail.setEditable(true);
        columnOpcoes.setEditable(true);

        condutoresTable.getSelectionModel().setCellSelectionEnabled(true);
    }

    public void initializeColumns() {

        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory = tableColumn -> new EditingCell();

        columnIDParque.setCellValueFactory(new PropertyValueFactory<>("idPark"));
        columnIDCondutor.setCellValueFactory(new PropertyValueFactory<>("idUser"));


        columnNome.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnNome.setCellFactory(cellFactory);

        columnMatricula.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        columnMatricula.setCellFactory(cellFactory);

        columnIDLugar.setCellValueFactory(new PropertyValueFactory<>("idParkingSpace"));
        // TODO combobox editable

        columnDataEntrada.setCellValueFactory(new PropertyValueFactory<>("entryDate"));

        columnDataSaida.setCellValueFactory(new PropertyValueFactory<>("departureDate"));

        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnEmail.setCellFactory(cellFactory);

        columnOpcoes.setCellFactory(col -> new TableCell<>() {
            private final HBox hBox;
            private final Button editButton;
            private final Button removeButton;

            {
                hBox = new HBox();
                editButton = new Button("Editar");
                removeButton = new Button("Remover");

                hBox.setAlignment(Pos.CENTER);
                hBox.getChildren().addAll(editButton, removeButton);

                removeButton.setOnAction(evt -> {
                    User user = getTableRow().getItem();
                    removeUser(user);
                });

                editButton.setOnAction(evt -> {
                    if (editingCells.size() > 1) return;

                    for (int i = 0; i < 8; ++i) {
                        Cell c = ((Cell) getTableView().queryAccessibleAttribute(AccessibleAttribute.CELL_AT_ROW_COLUMN, getTableRow().getIndex(), i));
                        c.startEdit();
                        editingCells.add(c);
                    }

                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hBox);
            }
        });

    }

    public void removeUser(User user) {
        data.removeUser(user);
        updateTable();
    }

    void updateParkComboBox() {
        idParqueCondutores.getItems().clear();

        idParqueCondutores.setPromptText("ID do Parque");
        for (Integer i : data.getParkIdAsIntegers())
            idParqueCondutores.getItems().add(i);
    }


    void setupCondutoresTabLayout() {

        condutoresTable.setItems(null);
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


    public void updateTable() {
        condutoresTable.setItems(data.getUsersByParkID((Integer)idParqueCondutores.getValue()));
    }

    /* CALL BACKS*/
    class NewParkSelectedCallBack implements EventHandler<ActionEvent> {
        public void handle(ActionEvent actionEvent) {
            if (idParqueCondutores.getValue() != null)
                updateTable();
        }
    }

    class EditButtonPressed implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent)
        {
            condutoresTable.setEditable(true);

            System.out.println("Row: " + (condutoresTable.getSelectionModel().selectedItemProperty().get()));

            System.out.println("EditButtonPressed");

            Button btn = (Button)actionEvent.getSource();
            System.out.println("User ID: " + btn.getId()) ;
        }
    }

    class RemoveButtonPressed implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent)
        {
            System.out.println("RemoveButtonPressed");
            System.out.println(actionEvent.getSource());
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
                for (Cell c :
                        editingCells) {
                    c.commitEdit(c.getText());
                }
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

    class EditingCell extends TableCell<User, String> {

        private TextField textField;

        public EditingCell() {

        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(item);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
//                        setGraphic(null);
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnAction((e) -> commitEdit(textField.getText()));
            textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
                    System.out.println("Commiting " + textField.getText());
                    commitEdit(textField.getText());
                    // TODO data.modifyUser()
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }
}