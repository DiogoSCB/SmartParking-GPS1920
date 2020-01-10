package views;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.AccessibleAttribute;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import models.*;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ControllerUI implements Initializable {
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

    /* Tables */
    @FXML
    private TableView<User> condutoresTable;
    @FXML
    private TableView<RequestRow> pedidosTable;

    // Columns condutoresTable
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

    // Columns pedidosTable
    @FXML
    private TableColumn<RequestRow, Integer> columnIDPedidos;
    @FXML
    private TableColumn<RequestRow, String> columnNomePedidos;
    @FXML
    private TableColumn<RequestRow, String> columnMatriculaPedidos;
    @FXML
    private TableColumn<RequestRow, Date> columnDataPedidos;
    @FXML
    private TableColumn<RequestRow, String> columnEstadoPedidos;
    @FXML
    private TableColumn<RequestRow, String> columnEmailPedidos;


    /* ComboBoxes */
    @FXML
    private ComboBox idParqueCondutores;
    @FXML
    private ComboBox idParquePedidos;

    /* Testing */
    private ArrayList<Cell> editingCells;

    private ArrayList<EditingCell> editingTableCells;
    private EditingCellDrop editingCellDrop;

    ControllerUI(Data data) {
        this.data = data;
        editingCells = new ArrayList<>();
        editingTableCells = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeColumns();
        updateParkComboBox(idParqueCondutores);
        setupCondutoresTabLayout();

        /* Setup Listeners */
        idParqueCondutores.setOnAction(new NewParkSelectedCallBack());
        idParquePedidos.setOnAction(new NewParkSelectedCallBack());
        condutoresTab.setOnSelectionChanged(new TabSelectionChanged());
        pedidosTab.setOnSelectionChanged(new TabSelectionChanged());

        /* Buttons */
        aceitarBtn.setOnAction(new ButtonPressed());
        rejeitarBtn.setOnAction(new ButtonPressed());
        gravarBtn.setOnAction(new ButtonPressed());
        sairBtn.setOnAction(new ButtonPressed());


        /* Setup editable columns */
        columnIDCondutor.setEditable(true);
        columnNome.setEditable(true);
        columnMatricula.setEditable(true);
        columnIDLugar.setEditable(true);
        columnDataEntrada.setEditable(true);
        columnDataSaida.setEditable(true);
        columnEmail.setEditable(true);
        columnOpcoes.setEditable(true);

        condutoresTable.getSelectionModel().setCellSelectionEnabled(true);

        pedidosTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                RequestRow requestRow = pedidosTable.getSelectionModel().getSelectedItem();
                Request request = requestRow.getRequest();

                if (request.getState() != Constants.PENDING) {
                    aceitarBtn.setDisable(true);
                    rejeitarBtn.setDisable(true);
                    return;
                }

                aceitarBtn.setDisable(false);
                rejeitarBtn.setDisable(false);
            }
        });
    }

    private void initializeColumns() {

        /* Table Condutores*/

        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory = tableColumn -> new EditingCell();
        Callback<TableColumn<User, Integer>, TableCell<User, Integer>> cellFactoryDrop = tableColumn -> new EditingCellDrop();

        columnIDCondutor.setCellValueFactory(new PropertyValueFactory<>("idUser"));

        columnNome.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnNome.setCellFactory(cellFactory);

        columnMatricula.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        columnMatricula.setCellFactory(cellFactory);

        columnIDLugar.setCellValueFactory(new PropertyValueFactory<>("idParkingSpace"));
        columnIDLugar.setCellFactory(cellFactoryDrop);

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

                    for (int i = 0; i < 7; ++i) {
                        Cell c = ((Cell) getTableView().queryAccessibleAttribute(AccessibleAttribute.CELL_AT_ROW_COLUMN,
                                getTableRow().getIndex(), i));
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

        /* Table Pedidos*/
        columnIDPedidos.setCellValueFactory(new PropertyValueFactory<>("idRequest"));
        columnNomePedidos.setCellValueFactory(new PropertyValueFactory<>("nomeRequest"));
        columnMatriculaPedidos.setCellValueFactory(new PropertyValueFactory<>("matriculaRequest"));
        columnDataPedidos.setCellValueFactory(new PropertyValueFactory<>("dataRequest"));
        columnEstadoPedidos.setCellValueFactory(new PropertyValueFactory<>("estadoRequest"));
        columnEmailPedidos.setCellValueFactory(new PropertyValueFactory<>("emailRequest"));

    }

    private void removeUser(User user) {
        data.removeUser(user);
        updateCondutoresTable();
    }

    private void updateParkComboBox(ComboBox comboBox) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll(data.getParkNames());
        comboBox.getSelectionModel().selectFirst();
        updateTable();
    }

    private void setupCondutoresTabLayout() {
        /* Show/hide corresponding buttons */
        aceitarBtn.setVisible(false);
        rejeitarBtn.setVisible(false);
        gravarBtn.setVisible(true);
        gravarBtn.setDisable(true);
    }

    private void setupPedidosTabLayout() {
        /* Show/hide corresponding buttons */
        data.importAllData();
        gravarBtn.setVisible(false);
        aceitarBtn.setVisible(true);
        aceitarBtn.setDisable(true);
        rejeitarBtn.setVisible(true);
        rejeitarBtn.setDisable(true);
    }

    private void updateCondutoresTable() {
        condutoresTable.setItems(data.getUserByParkName((String) idParqueCondutores.getValue()));
        condutoresTable.refresh();
    }

    private void updatePedidosTable() {
        pedidosTable.setItems(data.getRequestByParkName((String) idParquePedidos.getValue()));
        pedidosTable.refresh();
    }

    private void updateTable() {
        if (pedidosTab.isSelected())
            updatePedidosTable();
        else if (condutoresTab.isSelected())
            updateCondutoresTable();
    }

    /* CALL BACKS*/
    class NewParkSelectedCallBack implements EventHandler<ActionEvent> {
        public void handle(ActionEvent actionEvent) {
            cleanEdits();
            updateTable();
        }
    }

    class ButtonPressed implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(aceitarBtn)) {
                acceptRequest();
            } else if (actionEvent.getSource().equals(rejeitarBtn)) {
                refuseRequest();
            } else if (actionEvent.getSource().equals(gravarBtn)) {
                save();
            } else if (actionEvent.getSource().equals(sairBtn)) {
                Platform.exit();
            }
        }

        private void acceptRequest() {

            RequestRow requestRow = pedidosTable.getSelectionModel().getSelectedItem();
            Request request = requestRow.getRequest();

            data.modifyRequest(request, Constants.ACCEPT);

            aceitarBtn.setDisable(true);
            rejeitarBtn.setDisable(true);

            updateTable();
        }

        private void refuseRequest() {
            RequestRow requestRow = pedidosTable.getSelectionModel().getSelectedItem();
            Request request = requestRow.getRequest();

            data.modifyRequest(request, Constants.REFUSE);

            aceitarBtn.setDisable(true);
            rejeitarBtn.setDisable(true);

            updateTable();
        }

        private void save() {
            if (!data.validateNome(editingTableCells.get(0).getTextField().getText())
                    || !data.validateLicensePlate(editingTableCells.get(1).getTextField().getText())
                    || !data.validateEmail(editingTableCells.get(2).getTextField().getText())) {
                for (EditingCell editingCell : editingTableCells) {
                    editingCell.cancelEdit();
                }
                editingCellDrop.cancelEdit();
            } else {
                for (EditingCell editingCell : editingTableCells) {
                    editingCell.commitEdit(editingCell.getTextField().getText());
                    editingCell.updateItem(editingCell.getTextField().getText(), editingCell.getTextField().getText().isEmpty());
                }
                editingCellDrop.commitEdit(editingCellDrop.getComboBox().getValue());
                editingCellDrop.updateItem(editingCellDrop.getComboBox().getValue(), editingCellDrop.getComboBox().getValue() != null);
                if (!editingTableCells.isEmpty())
                    data.modifyUser(editingTableCells.get(0).getTableRow().getItem().getIdUser(),
                            editingTableCells.get(0).getTextField().getText(),
                            editingTableCells.get(1).getTextField().getText(),
                            editingTableCells.get(2).getTextField().getText(), editingCellDrop.getComboBox().getValue());
            }
            editingCells.clear();
            editingTableCells.clear();
            gravarBtn.setDisable(true);
            updateTable();
        }

    }

    private void cleanEdits() {
        for (EditingCell editingCell : editingTableCells)
            editingCell.cancelEdit();
        editingTableCells.clear();
        for (Cell cell : editingCells)
            cell.cancelEdit();
        editingCells.clear();
    }

    class TabSelectionChanged implements EventHandler<Event> {

        @Override
        public void handle(Event event) {
            cleanEdits();
            if (condutoresTab.isSelected()) {
                setupCondutoresTabLayout();
                if (idParqueCondutores.getItems().isEmpty())
                    updateParkComboBox(idParqueCondutores);
            } else if (pedidosTab.isSelected()) {
                setupPedidosTabLayout();
                if (idParquePedidos.getItems().isEmpty())
                    updateParkComboBox(idParquePedidos);
            }
        }
    }

    class EditingCell extends TableCell<User, String> {

        private TextField textField;

        TextField getTextField() {
            return textField;
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
                gravarBtn.setDisable(false);
                editingTableCells.add(this);
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
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }

    class EditingCellDrop extends TableCell<User, Integer> {
        private ComboBox<Integer> comboBox;

        ComboBox<Integer> getComboBox() {
            return comboBox;
        }

        @Override
        public void startEdit() {
            ObservableList<Integer> idPS = null;

            if (!isEmpty()) {
                super.startEdit();
                createComboBox();
                idPS = data.getParkingFreeSpacesByName((String) idParqueCondutores.getValue());
                idPS.add(Integer.parseInt(getString()));
                Collections.sort(idPS);
                comboBox.setItems(idPS);
                if (Integer.parseInt(getString()) == 0)
                    comboBox.getSelectionModel().selectFirst();
                else comboBox.getSelectionModel().select(idPS.indexOf(Integer.parseInt(getString())));
                setText(null);
                setGraphic(comboBox);
                editingCellDrop = this;
                gravarBtn.setDisable(false);
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setItem((Integer) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setItem(item);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    setText(null);
                    setGraphic(comboBox);
                } else {
                    setText(item.toString());
                    setGraphic(null);
                }
            }
        }

        private void createComboBox() {
            comboBox = new ComboBox<>();
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}
