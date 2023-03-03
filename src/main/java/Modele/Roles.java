package Modele;

public class Roles {

    public Roles(int id_roles, String nom_roles) {
        this.id_roles = id_roles;
        this.nom_roles = nom_roles;
    }

    protected int id_roles;

    protected String nom_roles;

    public int getId_roles() {
        return id_roles;
    }

    public void setId_roles(int id_roles) {
        this.id_roles = id_roles;
    }

    public String getNom_roles() {
        return nom_roles;
    }

    public void setNom_roles(String nom_roles) {
        this.nom_roles = nom_roles;
    }
}
