package models;

public class Park {

    private Integer idPark;
    private String name;

    public Park(Integer idPark, String name) {
        this.idPark = idPark;
        this.name = name;
    }

    public Integer getIdPark() {
        return idPark;
    }

    public void setIdPark(Integer idPark) {
        this.idPark = idPark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

