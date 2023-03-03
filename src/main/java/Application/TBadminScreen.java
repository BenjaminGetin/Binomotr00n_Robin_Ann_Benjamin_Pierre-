package Application;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class TBadminScreen {

    @FXML
    private Button gestionUtilisateursButton;

    @FXML
    private Button gestionProjetsButton;

    @FXML
    private TableView<String> utilisateursTableView;

    @FXML
    private TableColumn<String, String> utilisateursColumn;

    @FXML
    private TableColumn<String, String> rolesColumn;

    @FXML
    private TableView<String> projetsTableView;

    @FXML
    private TableColumn<String, String> projetsColumn;

    @FXML
    private Button supprimerProjetButton;

   /* public void initialize() {
        // Initialise les colonnes des TableView
        utilisateursColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        rolesColumn.setCellValueFactory(data -> new SimpleStringProperty("Rôles"));
        projetsColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
    } */

    @FXML
    private void handleGestionUtilisateurs() {
        // Code pour la gestion des utilisateurs
    }

    @FXML
    private void handleGestionProjets() {
        // Code pour la gestion des projets
    }

    @FXML
    private void handleSupprimerProjet() {
        // Code pour la suppression d'un projet et de ses groupes
    }
}
