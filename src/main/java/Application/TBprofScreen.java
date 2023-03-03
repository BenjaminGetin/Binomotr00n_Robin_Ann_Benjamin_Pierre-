package Application;

import DataBase.DataBinomotroon;
import Modele.Projets;
import Modele.Promotions;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import Modele.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TBprofScreen {

    @FXML
    private HBox listeProjetHbox;

    @FXML
    private HBox listePromoHbox;

    @FXML
    private Button listeProjetButton;

    @FXML
    private Button listePromoButton;

    @FXML
    private Button creerProjetButton;

    @FXML
    private TableView<Promotions> promoTableView;

    @FXML
    private TableColumn<Promotions, Integer> idPromoCol;

    @FXML
    private TableColumn<Promotions, String> nomPromoCol;

    @FXML
    private TableColumn<Promotions, LocalDate> dateDebutCol;

    @FXML
    private TableView<Projets> projetTableView;

    @FXML
    private TableColumn<Projets, Integer> idProjetCol;

    @FXML
    private TableColumn<Projets, String> nomProjetCol;

    @FXML
    private TableColumn<Projets, String> createurProjetCol;

    @FXML
    void initialize() {

        ArrayList<Promotions> profPromotions = DataBinomotroon.getProfPromotions();

        // Initialisation des colonnes de la table des promotions
        idPromoCol.setCellValueFactory(new PropertyValueFactory<>("id_promo"));
        nomPromoCol.setCellValueFactory(new PropertyValueFactory<>("nom_promo"));
        dateDebutCol.setCellValueFactory(new PropertyValueFactory<>("date_promo"));

        // Ajouter les données à votre TableView
        promoTableView.setItems(FXCollections.observableArrayList(profPromotions));

        ArrayList<Projets> profProjet = DataBinomotroon.getProfProjets();

        // Lier les colonnes de votre TableView aux propriétés des objets correspondants
        idProjetCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomProjetCol.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        createurProjetCol.setCellValueFactory(new PropertyValueFactory<>("createur"));

        // Ajouter les données à votre TableView
        projetTableView.setItems(FXCollections.observableArrayList(profProjet));

    }


    // Chargement de la fenêtre de création de projet à partir du fichier FXML
    @FXML
    private void handleCreerProjet() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("creation_groupe.fxml"));
        Parent root = loader.load();
        // Configuration de la scène et du stage
        Scene scene = new Scene(root);
        Stage stage = (Stage) creerProjetButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    // Affichage de la liste des projets et masquage de la liste des promotions
    @FXML
    public void afficherListeProjet() {
        listePromoHbox.setVisible(false);
        listeProjetHbox.setVisible(true);
    }

    // Affichage de la liste des promotions et masquage de la liste des projets
    @FXML
    public void afficherListePromo() {
        listeProjetHbox.setVisible(false);
        listePromoHbox.setVisible(true);
    }

}


