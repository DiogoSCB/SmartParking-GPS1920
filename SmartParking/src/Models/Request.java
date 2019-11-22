package Models;

import java.sql.Date;

public class Request {

    private int idRequest;
    private Date requestDate;
    private int state;
    private int idUser;

    public Request(int idRequest, Date requestDate, int state, int idUser) {
        idRequest = idRequest;
        requestDate = requestDate;
        state = state;
        idUser = idUser;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        idRequest = idRequest;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        requestDate = requestDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        state = state;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        idUser = idUser;
    }
}