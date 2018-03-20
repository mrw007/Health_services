package Entities;

import java.util.Date;

/**
 * Created by mrwah on 3/8/2018.
 */

public class Medecin {
    private Long id;
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String mail;
    private String tel;
    private String specialite;
    private String telCabinet;
    private String adresseCabinet;
    private String  dateNaissance;

    public Medecin() {
    }

    public Medecin(String login, String password, String nom, String prenom, String mail, String tel, String specialite, String telCabinet, String adresseCabinet, String dateNaissance) {

        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.tel = tel;
        this.specialite = specialite;
        this.telCabinet = telCabinet;
        this.adresseCabinet = adresseCabinet;
        this.dateNaissance = dateNaissance;

    }

    public Medecin(Long id, String login, String password, String nom, String prenom, String mail, String tel, String specialite, String telCabinet, String adresseCabinet, String dateNaissance) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.tel = tel;
        this.specialite = specialite;
        this.telCabinet = telCabinet;
        this.adresseCabinet = adresseCabinet;
        this.dateNaissance = dateNaissance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getTelCabinet() {
        return telCabinet;
    }

    public void setTelCabinet(String telCabinet) {
        this.telCabinet = telCabinet;
    }

    public String getAdresseCabinet() {
        return adresseCabinet;
    }

    public void setAdresseCabinet(String adresseCabinet) {
        this.adresseCabinet = adresseCabinet;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

}
