package Modele;

import java.time.LocalDate;

public class Groupes {
    protected int idGroupes;

    protected String nom_groupe;

    protected LocalDate date_creation;

    protected int id_projet;

    public Groupes(int id_groupes, String nom_groupe) {
        this.idGroupes = id_groupes;
        this.nom_groupe = nom_groupe;
        this.date_creation = date_creation;
        this.id_projet = id_projet;
    }

    public int getIdGroupes() {
        return idGroupes;
    }

    public void setIdGroupes(int idGroupes) {
        this.idGroupes = idGroupes;
    }

    public String getNom_groupe() {
        return nom_groupe;
    }

    public void setNom_groupe(String nom_groupe) {
        this.nom_groupe = nom_groupe;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public int getId_projet() {
        return id_projet;
    }

    public void setId_projet(int id_projet) {
        this.id_projet = id_projet;
    }
}
