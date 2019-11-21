package Models;

import java.sql.Date;

public class Request {

    private int IdRequest;
    private Date RequestDate;
    private int State;
    private int IdUser;

    public Request(int idRequest, Date requestDate, int state, int idUser) {
        IdRequest = idRequest;
        RequestDate = requestDate;
        State = state;
        IdUser = idUser;
    }

    public int getIdRequest() {
        return IdRequest;
    }

    public void setIdRequest(int idRequest) {
        IdRequest = idRequest;
    }

    public Date getRequestDate() {
        return RequestDate;
    }

    public void setRequestDate(Date requestDate) {
        RequestDate = requestDate;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }
}