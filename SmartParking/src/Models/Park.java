package models;

public class Park {

        private int idPark;
        private int totalParkingSpace;
        private int freeParkingSpace;

    public Park(int idPark, int totalParkingSpace, int freeParkingSpace) {
        idPark = idPark;
        totalParkingSpace = totalParkingSpace;
        freeParkingSpace = freeParkingSpace;
    }

    public int getIdPark() {
        return idPark;
    }

    public int getTotalParkingSpace() {
        return totalParkingSpace;
    }

    public int getFreeParkingSpace() {
        return freeParkingSpace;
    }

    public void setIdPark(int idPark) {
        idPark = idPark;
    }

    public void setTotalParkingSpace(int totalParkingSpace) {
        totalParkingSpace = totalParkingSpace;
    }

    public void setFreeParkingSpace(int freeParkingSpace) {
        freeParkingSpace = freeParkingSpace;
    }
}

