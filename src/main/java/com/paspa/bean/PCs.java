package com.paspa.bean;

public class PCs {

    private String nome, tag, serial, id_modello, assigned_type;
    private Utente utente;
    private Location location;

    public String getId_modello() {
        return id_modello;
    }

    public String getNome() {
        return nome;
    }

    public String getSerial() {
        return serial;
    }

    public String getTag() {
        return tag;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setId_modello(String id_modello) {
        this.id_modello = id_modello;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void setAssigned_type(String assigned_type) {
        this.assigned_type = assigned_type;
    }

    public String getAssigned_type() {
        return assigned_type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
