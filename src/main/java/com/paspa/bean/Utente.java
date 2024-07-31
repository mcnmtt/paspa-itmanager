package com.paspa.bean;

public class Utente {

    private String id, email, nome, cognome;

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
