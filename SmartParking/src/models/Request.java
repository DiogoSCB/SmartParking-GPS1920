package models;

import java.sql.Date;

public class Request {

    private Integer idRequest;
    private Date requestDate;
    private int state;
    private Integer idUser;

    public Request(Integer idRequest, Date requestDate, int state, Integer idUser) {
        this.idRequest = idRequest;
        this.requestDate = requestDate;
        this.state = state;
        this.idUser = idUser;
    }

   //Construtor sem IdRequest
    public Request(Date requestDate, int state, Integer idUser) {
        this.requestDate = requestDate;
        this.state = state;
        this.idUser = idUser;
    }

    public Integer getIdRequest() {
        return idRequest;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public int getState() {
        return state;
    }

    public Integer getIdUser() {
        return idUser;
    }
}