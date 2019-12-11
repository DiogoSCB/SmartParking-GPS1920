package models;


import java.sql.Date;

public class RequestRow {

    Request request;
    User user;

    public RequestRow(Request request, User user) {
        this.request = request;
        this.user = user;
    }

    public Integer getIdRequest() {
        return request.getIdRequest();
    }

    public String getNomeRequest() {
        return user.getName();
    }

    public String getMatriculaRequest() {
        return user.getLicensePlate();
    }

    public Date getDataRequest() {
        return request.getRequestDate();
    }

    public Request getRequest() {
        return request;
    }

    public User getUser() {
        return user;
    }

    public String getEstadoRequest() {
        int s = request.getState();

        switch (s) {
            case -1:
                return "Recusado";
            case 0:
                return "Pendente";
            case 1:
                return "Aceite";
        }
        return null;
    }

    public String getEmailRequest() {
        return user.getEmail();
    }
}
