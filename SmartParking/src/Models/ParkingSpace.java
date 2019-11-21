package Models;

public class ParkingSpace {

    private int IdParkingSpace;
    private int Reserved;
    private int IdPark;

    public ParkingSpace(int idParkingSpace, int reserved, int idPark) {
        IdParkingSpace = idParkingSpace;
        Reserved = reserved;
        IdPark = idPark;
    }

    public int getIdParkingSpace() {
        return IdParkingSpace;
    }

    public void setIdParkingSpace(int idParkingSpace) {
        IdParkingSpace = idParkingSpace;
    }

    public int getReserved() {
        return Reserved;
    }

    public void setReserved(int reserved) {
        Reserved = reserved;
    }

    public int getIdPark() {
        return IdPark;
    }

    public void setIdPark(int idPark) {
        IdPark = idPark;
    }
}
