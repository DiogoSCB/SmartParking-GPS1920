package models;

public class Park {

    private Integer idPark;
    private String namePark;

    public Park(Integer idPark, String namePark) {
        this.idPark = idPark;
        this.namePark = namePark;
    }

    public Integer getIdPark() {
        return idPark;
    }

    public void setIdPark(Integer idPark) {
        this.idPark = idPark;
    }

    public String getNamePark() {
        return namePark;
    }

    public void setNamePark(String namePark) {
        this.namePark = namePark;
    }
}

