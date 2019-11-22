package Models;

public class ParkingSpace {

    private int idParkingSpace;
    private int reserved;
    private int idPark;

    public ParkingSpace(int idParkingSpace, int reserved, int idPark) {
        idParkingSpace = idParkingSpace;
        reserved = reserved;
        idPark = idPark;
    }

    public int getIdParkingSpace() {
        return idParkingSpace;
    }

    public void setIdParkingSpace(int idParkingSpace) {
        idParkingSpace = idParkingSpace;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        reserved = reserved;
    }

    public int getIdPark() {
        return idPark;
    }

    public void setIdPark(int idPark) {
        idPark = idPark;
    }
}
