package Application;

import DataBase.DataBinomotroon;
import Modele.Groupes;
import Modele.Projets;
import Modele.Utilisateurs;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class TBapprenantScreen {
    @FXML
    private ComboBox<String> promotionComboBox;

    @FXML
    private TextField nomApprenantTextField;

    @FXML
    private TableView<Object> projetsTableView;

    @FXML
    private TableColumn<Projets, String> nomProjetColumn;

    @FXML
    private TableColumn<Groupes, String> groupesProjetColumn;
    @FXML
    private TableColumn<Utilisateurs, String> groupesApprenantsColumn;

    public void initialize() {
        // Récupérer les apprenants et leurs groupes de la base de données
        ArrayList<Modele.TableView> apprenantsGroupes = DataBinomotroon.getApprenantsGroupes();

        // Lier les colonnes de votre TableView aux propriétés des objets correspondants
        groupesApprenantsColumn.setCellValueFactory(new PropertyValueFactory<>("nomApprenant"));
        groupesProjetColumn.setCellValueFactory(new PropertyValueFactory<>("nomGroupe"));
        nomProjetColumn.setCellValueFactory(new PropertyValueFactory<>("nomProjet"));

        // Ajouter les données à votre TableView
        projetsTableView.setItems(FXCollections.observableArrayList(apprenantsGroupes));
    }


    public void rechercherApprenantAction() {
        String texteRecherche = nomApprenantTextField.getText().trim();
        if (texteRecherche.isEmpty()) {
            // Si le champ de recherche est vide, afficher tous les apprenants et leurs groupes
            projetsTableView.setItems(FXCollections.observableArrayList(DataBinomotroon.getApprenantsGroupes()));
        } else {
            // Si le champ de recherche n'est pas vide, effectuer une recherche filtrée sur les apprenants en utilisant le prénom
            List<Modele.TableView> apprenantsGroupes = DataBinomotroon.searchApprenantsByPrenom(texteRecherche);

            // Afficher les résultats de la recherche filtrée dans la table view
            projetsTableView.setItems(FXCollections.observableArrayList(apprenantsGroupes));
        }

    }
}


