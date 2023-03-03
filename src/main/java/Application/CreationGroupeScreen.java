package Application;

import DataBase.DataBinomotroon;
import Modele.Promotions;
import Modele.Utilisateurs;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreationGroupeScreen {

    @FXML
    private TextField groupSizeTextField;

    @FXML
    private ComboBox<String> promotionComboBox;

    @FXML
    private ComboBox<String> createurComboBox;

    @FXML
    private TextField projectNameTextField;

    @FXML
    private TableView<ArrayList<Utilisateurs>> projectTableView;

    @FXML
    private TableColumn<ArrayList<Utilisateurs>, Integer> idColumn;

    @FXML
    private TableColumn<ArrayList<Utilisateurs>, String> groupColumn;

    @FXML
    private Button launchButton;

    @FXML
    private Button validateButton;

    @FXML
    private DatePicker dateDebut;

    @FXML
    private DatePicker dateFin;

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        // Configuration de la colonne de groupe
        groupColumn.setCellValueFactory(cellData -> {
            // Récupération du groupe à partir des données de la cellule
            ArrayList<Utilisateurs> groupe = cellData.getValue();

            // Calcul du nom du groupe en fonction de l'indice de la ligne dans la table view
            String nomGroupe = "Groupe " + (projectTableView.getItems().indexOf(groupe) + 1);

            // Retourne le nom du groupe en tant que propriété simple
            return new SimpleStringProperty(nomGroupe);
        });

        // Remplissage de la liste des créateurs de projets dans la combobox s'ils sont vides
        if (createurComboBox.getItems().isEmpty()) {
            // Récupération de la liste des professeurs
            List<Utilisateurs> profs = DataBinomotroon.getProfs();

            // Ajout de chaque professeur dans la combobox sous forme de nom complet
            for (Utilisateurs prof : profs) {
                String nomComplet = prof.getNom_users() + " " + prof.getPrenom_users();
                createurComboBox.getItems().add(nomComplet);
            }
        }

        // Remplissage de la liste des promotions dans la combobox s'ils sont vides
        if (promotionComboBox.getItems().isEmpty()) {
            // Récupération de la liste des promotions
            List<Promotions> promotions = DataBinomotroon.getPromotions();

            // Ajout de chaque promotion dans la combobox sous forme de nom de promotion
            for (Promotions promo : promotions) {
                promotionComboBox.getItems().add(promo.getNom_promo());
            }
        }

    }


    @FXML
    public void handlePromotionComboBoxAction(ActionEvent event) {
        String nomPromotion = promotionComboBox.getValue();
        ArrayList<Utilisateurs> apprenants = DataBinomotroon.getApprenantsByPromotion(nomPromotion);
        int nbApprenants = apprenants.size();
        groupSizeTextField.setPromptText("Nombre d'apprenants : " + nbApprenants);

        // Afficher les apprenants dans le TableView
        ObservableList<ArrayList<Utilisateurs>> apprenantObservableList = FXCollections.observableArrayList();
        apprenantObservableList.addAll(apprenants);
        projectTableView.setItems(apprenantObservableList);
    }

    @FXML
    private void handleLaunchButtonAction(ActionEvent event) {

        String projectName = projectNameTextField.getText();
        LocalDate debut = dateDebut.getValue();
        LocalDate fin = dateFin.getValue();
        String createur = createurComboBox.getValue();
        int groupSize = Integer.parseInt(groupSizeTextField.getText());

        if (projectName.isEmpty() || createur.isEmpty() || fin.isBefore(debut) || groupSize <= 0) {
            // Afficher un message d'erreur et quitter la méthode
            return;
        }

        // Récupérer la liste des apprenants de la promotion sélectionnée dans la ComboBox
        String nomPromotion = promotionComboBox.getValue();
        List<Utilisateurs> apprenants = DataBinomotroon.getApprenantsByPromotion(nomPromotion);

        // Mélanger les apprenants de la liste obtenue
        Collections.shuffle(apprenants);

        // Créer les groupes en découpant la liste d'apprenants en groupes de la taille spécifiée
        ArrayList<ArrayList<Utilisateurs>> groupes = new ArrayList<>();
        int nbGroupes = (int) Math.ceil(apprenants.size() / (double) groupSize);
        for (int i = 0; i < nbGroupes; i++) {
            int debutIndex = i * groupSize;
            int finIndex = Math.min((i + 1) * groupSize, apprenants.size());
            ArrayList<Utilisateurs> groupe = new ArrayList<>(apprenants.subList(debutIndex, finIndex));
            groupes.add(groupe);
        }

        // Créer une liste observable des apprenants par groupe
        ObservableList<ArrayList<Utilisateurs>> apprenantsParGroupeObservableList = FXCollections.observableArrayList();

        // Boucle pour parcourir chaque groupe
        for (int i = 0; i < groupes.size(); i++) {
            String nomGroupe = "Groupe " + (i + 1);
            LocalDate dateCreation = LocalDate.now();
            ArrayList<Utilisateurs> apprenantsGroupe = groupes.get(i);

            // Créer une liste d'objets Utilisateurs pour les apprenants du groupe
            ArrayList<Utilisateurs> utilisateursGroupe = new ArrayList<>();

            // Boucle pour parcourir chaque apprenant dans le groupe et ajouter à la liste d'Utilisateurs du groupe
            for (int j = 0; j < apprenantsGroupe.size(); j++) {
                Utilisateurs apprenant = apprenantsGroupe.get(j);
                utilisateursGroupe.add(apprenant);
            }

            // Ajouter la liste d'Utilisateurs du groupe à la liste observable
            apprenantsParGroupeObservableList.add(utilisateursGroupe);
        }

        // Afficher les projets sur la TableView

        projectTableView.setItems(apprenantsParGroupeObservableList);


    }

}
