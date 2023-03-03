package Modele;

public class Utilisateurs {

    protected int id_users;

    protected String nom_users;

    protected String prenom_users;

    protected String mail_apprenant;

    public Utilisateurs(String nom_users, String prenom_users) {
        this.nom_users = nom_users;
        this.prenom_users = prenom_users;
    }

    protected int id_role;

    protected int id_promo;

    public Utilisateurs(int id_users, String nom_users, String prenom_users, String mail_apprenant, int id_role, int id_promo) {
        this.id_users = id_users;
        this.nom_users = nom_users;
        this.prenom_users = prenom_users;
        this.mail_apprenant = mail_apprenant;
        this.id_role = id_role;
        this.id_promo = id_promo;
    }

    public int getId_users() {
        return id_users;
    }

    public int getId_role() {
        return id_role;
    }

    public int getId_promo() {
        return id_promo;
    }

    public String getNom_users() {
        return nom_users;
    }

    public void setNom_users(String nom_users) {
        this.nom_users = nom_users;
    }

    public String getPrenom_users() {
        return prenom_users;
    }

    public void setPrenom_users(String prenom_users) {
        this.prenom_users = prenom_users;
    }

    public String getMail_apprenant() {
        return mail_apprenant;
    }

    public void setMail_apprenant(String mail_apprenant) {
        this.mail_apprenant = mail_apprenant;
    }
}
