package models;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.sql.Date;

public class UserRow
{
    private Integer idUser;
    private String name;
    private String licensePlate;
    private Date entryDate;
    private Date departureDate;
    private String email;
    private Integer idParkingSpace;
    private Integer idPark;

    private ComboBox parkDropdownList;



    private HBox options;

    private Button editButton;
    private Button removeButton;

    public UserRow(User user) {
        this.idUser = user.getIdUser();
        this.name = user.getName();
        this.licensePlate = user.getLicensePlate();
        this.entryDate = user.getEntryDate();
        this.departureDate = user.getDepartureDate();
        this.email = user.getEmail();
        this.idParkingSpace = user.getIdParkingSpace();
        this.idPark = user.getIdPark();

        this.parkDropdownList = new ComboBox();
        this.options = new HBox();

        this.editButton = new Button();
        editButton.setText("Editar");
        this.removeButton = new Button();
        removeButton.setText("Apagar");

        this.options.getChildren().addAll(editButton, removeButton);
    }

    public Integer getIdUser()
    {
        return idUser;
    }

    public String getName()
    {
        return name;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }

    public Date getEntryDate()
    {
        return entryDate;
    }

    public Date getDepartureDate()
    {
        return departureDate;
    }

    public String getEmail()
    {
        return email;
    }

    public Integer getIdParkingSpace()
    {
        return idParkingSpace;
    }

    public Integer getIdPark()
    {
        return idPark;
    }

    public ComboBox getParkDropdownList()
    {
        return parkDropdownList;
    }

    public HBox getOptions()
    {
        return options;
    }

    public Button getEditButton()
    {
        return editButton;
    }

    public Button getRemoveButton()
    {
        return removeButton;
    }
}
