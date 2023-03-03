package Modele;

public class TableView {
        private String nomApprenant;
        private String nomGroupe;
        private String nomProjet;

        public TableView(String nomApprenant, String nomGroupe, String nomProjet) {
            this.nomApprenant = nomApprenant;
            this.nomGroupe = nomGroupe;
            this.nomProjet = nomProjet;
        }

        public String getNomApprenant() {
            return nomApprenant;
        }

        public String getNomGroupe() {
            return nomGroupe;
        }

        public String getNomProjet() {
            return nomProjet;
        }


    }


