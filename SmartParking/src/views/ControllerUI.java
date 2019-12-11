package views;

import javafx.application.Platform;
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
    @FXML
    private Tab estatisticasTab;

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
    @FXML
    private ComboBox idParqueEstatisticas;

    /* Testing */
    private ArrayList<Cell> editingCells;

    private ArrayList<EditingCell> editingTableCells;
    private EditingCellDrop editingCellDrop;

    public ControllerUI(Data data) {
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
        condutoresTab.setOnSelectionChanged(new TabSelectionChanged());
        pedidosTab.setOnSelectionChanged(new TabSelectionChanged());
        estatisticasTab.setOnSelectionChanged(new TabSelectionChanged());
        aceitarBtn.setOnAction(new ButtonPressed());
        rejeitarBtn.setOnAction(new ButtonPressed());
        gravarBtn.setOnAction(new GravarButtonPressed());
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

                if (request.getState() != Constants.pending) {
                    aceitarBtn.setDisable(true);
                    rejeitarBtn.setDisable(true);
                    return;
                }

                aceitarBtn.setDisable(false);
                rejeitarBtn.setDisable(false);
            }
        });
    }

    public void initializeColumns() {

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

    public void removeUser(User user) {
        data.removeUser(user);
        updateCondutoresTable();
    }

    void updateParkComboBox(ComboBox comboBox) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll(data.getParkIdAsIntegers());
        comboBox.getSelectionModel().selectFirst();
        updateTable();
    }

    void setupCondutoresTabLayout() {
        /* Show/hide corresponding buttons */
        aceitarBtn.setVisible(false);
        rejeitarBtn.setVisible(false);
        gravarBtn.setVisible(true);
        gravarBtn.setDisable(true);
    }

    void setupPedidosTabLayout() {
        /* Show/hide corresponding buttons */
        gravarBtn.setVisible(false);
        aceitarBtn.setVisible(true);
        aceitarBtn.setDisable(true);
        rejeitarBtn.setVisible(true);
        rejeitarBtn.setDisable(true);
    }

    void setupEstatisticasTabLayout() {
        /* Show/hide corresponding buttons */
        aceitarBtn.setVisible(false);
        rejeitarBtn.setVisible(false);
        gravarBtn.setVisible(false);
        sairBtn.setVisible(true);
    }

    public void updateCondutoresTable() {
        condutoresTable.setItems(data.getUsersByParkID((Integer)idParqueCondutores.getValue()));
    }

    public void updatePedidosTable() {
        pedidosTable.setItems(data.getRequests());
    }

    public void updateTable() {
        if (pedidosTab.isSelected())
            updatePedidosTable();
        else if (condutoresTab.isSelected())
            updateCondutoresTable();
    }

    private void acceptRequest() {

        RequestRow requestRow = pedidosTable.getSelectionModel().getSelectedItem();
        Request request = requestRow.getRequest();

        data.modifyRequest(request, Constants.accept);

        aceitarBtn.setDisable(true);
        rejeitarBtn.setDisable(true);

        updateTable();
    }

    private void refuseRequest() {
        RequestRow requestRow = pedidosTable.getSelectionModel().getSelectedItem();
        Request request = requestRow.getRequest();

        data.modifyRequest(request, Constants.refuse);

        aceitarBtn.setDisable(true);
        rejeitarBtn.setDisable(true);

        updateTable();
    }

    /* CALL BACKS*/
    class NewParkSelectedCallBack implements EventHandler<ActionEvent> {
        public void handle(ActionEvent actionEvent) {
            if (idParqueCondutores.getValue() != null)
                updateCondutoresTable();
        }
    }

    class ButtonPressed implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(aceitarBtn)) {
                System.out.println("Aceitar Button Pressed.");
                acceptRequest();
            } else if (actionEvent.getSource().equals(rejeitarBtn)) {
                System.out.println("Rejeitar Button Pressed.");
                refuseRequest();
            } else if (actionEvent.getSource().equals(gravarBtn)) {
                System.out.println("Gravar Button Pressed.");
            } else if (actionEvent.getSource().equals(sairBtn)) {
                System.out.println("Sair Button Pressed.");
                Platform.exit();
            }
        }
    }

    class GravarButtonPressed implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            if (!data.validateNome(editingTableCells.get(0).getTextField().getText())
                    || !data.validateLicensePlate(editingTableCells.get(1).getTextField().getText())
                    || !data.validateEmail(editingTableCells.get(2).getTextField().getText())) {
                for (EditingCell editingCell : editingTableCells) {
                    editingCell.cancelEdit();
                }
            } else {
                for (EditingCell editingCell : editingTableCells) {
                    editingCell.commitEdit(editingCell.getTextField().getText());
                    editingCell.updateItem(editingCell.getTextField().getText(), editingCell.getTextField().getText().isEmpty());
                }
                editingCellDrop.commitEdit(editingCellDrop.getItem());
                editingCellDrop.updateItem(editingCellDrop.getItem(), editingCellDrop.getItem() != null);
                if (!editingTableCells.isEmpty())
                    data.modifyUser(editingTableCells.get(0).getTableRow().getItem().getIdUser(),
                            editingTableCells.get(0).getTextField().getText(),
                            editingTableCells.get(1).getTextField().getText(),
                            editingTableCells.get(2).getTextField().getText(), editingCellDrop.getItem());
            }
            editingCells.clear();
            editingTableCells.clear();
            gravarBtn.setDisable(true);
            updateTable();
        }
    }

    class TabSelectionChanged implements EventHandler<Event> {

        @Override
        public void handle(Event event) {
            if (condutoresTab.isSelected()) {
                System.out.println("Condutores Tab Selected.");
                setupCondutoresTabLayout();
                updateParkComboBox(idParqueCondutores);
            } else if (pedidosTab.isSelected()) {
                System.out.println("Pedidos Tab Selected.");
                setupPedidosTabLayout();
                updateParkComboBox(idParquePedidos);
            } else if (estatisticasTab.isSelected()) {
                System.out.println("Estatísticas Tab Selected.");
                setupEstatisticasTabLayout();
            }
        }
    }

    class EditingCell extends TableCell<User, String> {

        private TextField textField;

        public TextField getTextField() {
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
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }

    class EditingCellDrop extends TableCell<User, Integer> {
        private ComboBox<Integer> comboBox;

        public ComboBox<Integer> getComboBox() {
            return comboBox;
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                comboBox.setItems(data.getParkingFreeSpacesById((Integer)idParqueCondutores.getValue()));
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
                    if (comboBox != null) {
                        comboBox.setValue(0);
                        comboBox.setItems(data.getParkingFreeSpacesById((Integer)idParqueCondutores.getValue()));
//                        setGraphic(null);
                    }
                    setText(null);
                    setGraphic(comboBox);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            comboBox = new ComboBox<>();
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}