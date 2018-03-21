package Entities;

import java.sql.Date;

/**
 * Created by mrwah on 3/8/2018.
 */

public class Abonne {
    private Long id;
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String mail;
    private String tel;
    private String adresse;
    private Date dateNaissance;

    public Abonne() {
    }


    public Abonne(String login, String password, String nom, String prenom, String mail, String tel, String adresse, Date dateNaissance) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.tel = tel;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;

    }

    public Abonne(Long id, String login, String password, String nom, String prenom, String mail, String tel, String adresse, Date dateNaissance) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.tel = tel;
        this.adresse = adresse;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
