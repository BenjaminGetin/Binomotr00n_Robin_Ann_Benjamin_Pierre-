package Modele;

public class ApprenantGroupe {
    protected int idApprenant;

    protected int idGroupe;

    public ApprenantGroupe(int idApprenant, int id_groupe) {
        this.idApprenant = idApprenant;
        this.idGroupe = id_groupe;
    }

    public int getId_apprenant() {
        return idApprenant;
    }

    public void setId_apprenant(int id_apprenant) {
        this.idApprenant = id_apprenant;
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }
}
