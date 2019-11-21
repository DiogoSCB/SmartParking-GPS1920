package Models;

public class Park {

        private int IdPark;
        private int TotalParkingSpace;
        private int FreeParkingSpace;

    public Park(int idPark, int totalParkingSpace, int freeParkingSpace) {
        IdPark = idPark;
        TotalParkingSpace = totalParkingSpace;
        FreeParkingSpace = freeParkingSpace;
    }

    public int getIdPark() {
        return IdPark;
    }

    public int getTotalParkingSpace() {
        return TotalParkingSpace;
    }

    public int getFreeParkingSpace() {
        return FreeParkingSpace;
    }

    public void setIdPark(int idPark) {
        IdPark = idPark;
    }

    public void setTotalParkingSpace(int totalParkingSpace) {
        TotalParkingSpace = totalParkingSpace;
    }

    public void setFreeParkingSpace(int freeParkingSpace) {
        FreeParkingSpace = freeParkingSpace;
    }
}

