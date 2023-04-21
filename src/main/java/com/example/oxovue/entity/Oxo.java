package com.example.oxovue.entity;


import java.time.LocalDate;

public class Oxo {


    private Long id;


    private String description;


    private String question;


    private LocalDate dateCreation = LocalDate.now();


    private LocalDate dateCloture;


    private String createur;


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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate creation) {
        this.dateCreation = creation;
    }

    public LocalDate getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(LocalDate cloture) {
        this.dateCloture = cloture;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }


}
