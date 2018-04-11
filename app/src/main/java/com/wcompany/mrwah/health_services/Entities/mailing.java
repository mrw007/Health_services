package com.wcompany.mrwah.health_services.Entities;

/**
 * Created by mrwah on 4/11/2018.
 */

public class mailing {
    String to;
    String text;
    String subject;

    public mailing(String to, String text, String subject) {
        this.to = to;
        this.text = text;
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
