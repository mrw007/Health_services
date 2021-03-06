package com.wcompany.mrwah.health_services.Entities;

import java.sql.Date;
import java.util.List;

/**
 * Created by mac on 22/04/2018.
 */

public class Reponse {


    private Long id;
    private String message;
    private Date dateRep;
    private Publication publication;
    private Medecin medecin;


    public Reponse(Long id, String message, Date dateRep,  Medecin medecin ) {
        this.id = id;
        this.message = message;
        this.dateRep = dateRep;
        this.medecin = medecin;
    }

    public Reponse(String message, Date dateRep, Publication publication, Medecin medecin ) {
        this.id = id;
        this.message = message;
        this.dateRep = dateRep;
        this.publication=publication;
        this.medecin = medecin;
    }

    public Reponse(String message, Date dateRep,  Publication publication) {
        this.id = id;
        this.message = message;
        this.dateRep = dateRep;
        this.publication=publication;
    }
    public Reponse(Long id) {
        this.id = id;
    }

    public Reponse() {

    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateRep() {
        return dateRep;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateRep(Date dateRep) {
        this.dateRep = dateRep;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }


    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", medecin='" + medecin + '\'' +
                ", publication='" + publication + '\'' +
                '}';
    }


}
