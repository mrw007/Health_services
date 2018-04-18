package com.wcompany.mrwah.health_services.Entities;

import java.sql.Date;
import java.util.List;

/**
 * Created by mrwah on 4/17/2018.
 */

public class Publication {
    private Long id;
    private String description;
    private String zone;
    private Date datePub;
    private String pub_mode;
    private boolean consultation_domicile;
    private float position_long;
    private float position_lat;
    private Abonne abonne;
    private List<String> reponses;

    public Publication(Long id, String description, String zone, Date datePub, String pub_mode, boolean consultation_domicile, Abonne abonne) {
        this.id = id;
        this.description = description;
        this.zone = zone;
        this.datePub = datePub;
        this.pub_mode = pub_mode;
        this.consultation_domicile = consultation_domicile;
        this.abonne = abonne;
    }

    public Publication(Long id, String description, String zone, Date datePub, String pub_mode, boolean consultation_domicile, float position_long, float position_lat, Abonne abonne) {
        this.id = id;
        this.description = description;
        this.zone = zone;
        this.datePub = datePub;
        this.pub_mode = pub_mode;
        this.consultation_domicile = consultation_domicile;
        this.position_long = position_long;
        this.position_lat = position_lat;
        this.abonne = abonne;
    }

    public Publication(String description, String zone, Date datePub, String pub_mode, boolean consultation_domicile, float position_long, float position_lat, Abonne abonne) {
        this.description = description;
        this.zone = zone;
        this.datePub = datePub;
        this.pub_mode = pub_mode;
        this.consultation_domicile = consultation_domicile;
        this.position_long = position_long;
        this.position_lat = position_lat;
        this.abonne = abonne;
    }

    public Publication(String description, String zone, Date datePub, String pub_mode, boolean consultation_domicile, Abonne abonne) {
        this.description = description;
        this.zone = zone;
        this.datePub = datePub;
        this.pub_mode = pub_mode;
        this.consultation_domicile = consultation_domicile;
        this.abonne = abonne;
    }

    public Publication(Long id, String description, String zone, Date datePub, String pub_mode, boolean consultation_domicile, float position_long, float position_lat, Abonne abonne, List<String> reponses) {
        this.id = id;
        this.description = description;
        this.zone = zone;
        this.datePub = datePub;
        this.pub_mode = pub_mode;
        this.consultation_domicile = consultation_domicile;
        this.position_long = position_long;
        this.position_lat = position_lat;
        this.abonne = abonne;
        this.reponses = reponses;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Date getDatePub() {
        return datePub;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }

    public String getPub_mode() {
        return pub_mode;
    }

    public void setPub_mode(String pub_mode) {
        this.pub_mode = pub_mode;
    }

    public boolean isConsultation_domicile() {
        return consultation_domicile;
    }

    public void setConsultation_domicile(boolean consultation_domicile) {
        this.consultation_domicile = consultation_domicile;
    }

    public float getPosition_long() {
        return position_long;
    }

    public void setPosition_long(float position_long) {
        this.position_long = position_long;
    }

    public float getPosition_lat() {
        return position_lat;
    }

    public void setPosition_lat(float position_lat) {
        this.position_lat = position_lat;
    }

    public Abonne getAbonne() {
        return abonne;
    }

    public void setAbonne(Abonne abonne) {
        this.abonne = abonne;
    }

    public List<String> getReponses() {
        return reponses;
    }

    public void setReponses(List<String> reponses) {
        this.reponses = reponses;
    }

}