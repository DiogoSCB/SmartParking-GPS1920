package models;

public class Park {

    private Integer idPark;
    private int totalParkingSpace;
    private int freeParkingSpace;

    public Park(Integer idPark, int totalParkingSpace, int freeParkingSpace) {
        this.idPark = idPark;
        this.totalParkingSpace = totalParkingSpace;
        this.freeParkingSpace = freeParkingSpace;
    }

    public Integer getIdPark() {
        return idPark;
    }

    public int getTotalParkingSpace() {
        return totalParkingSpace;
    }

    public int getFreeParkingSpace() {
        return freeParkingSpace;
    }
}

