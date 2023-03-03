package Modele;

import java.util.Date;

public class Promotions {


    protected int id_promo;

    protected String nom_promo;

    protected Date date_promo;

    public Promotions(int id_promo, String nom_promo, Date date_promo) {
        this.id_promo = id_promo;
        this.nom_promo = nom_promo;
        this.date_promo = date_promo;
    }

    public int getId_promo() {
        return id_promo;
    }

    public String getNom_promo() {
        return nom_promo;
    }

    public void setNom_promo(String nom_promo) {
        this.nom_promo = nom_promo;
    }

    public Date getDate_promo() {
        return date_promo;
    }

    public void setDate_promo(Date date_promo) {
        this.date_promo = date_promo;
    }
}
