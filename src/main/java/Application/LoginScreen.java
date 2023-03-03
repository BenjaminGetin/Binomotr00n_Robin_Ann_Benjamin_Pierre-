package Application;


import DataBase.DataBinomotroon;
import Modele.Utilisateurs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreen {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button connectButton;


    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();


        // Récupération des informations de l'utilisateur à partir de la source de données
        Utilisateurs user = DataBinomotroon.getUser(username, password);

        // Si l'utilisateur est trouvé dans la source de données
        if (user != null) {
            // Récupération du rôle de l'utilisateur
            int roleId = DataBinomotroon.getRoleId(user.getId_role());

            // Si le rôle de l'utilisateur est 1, on charge l'interface utilisateur "tb_apprenant.fxml"
            if (roleId == 1) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tb_apprenant.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
            // Si le rôle de l'utilisateur est 2, on charge l'interface utilisateur "tb_admin.fxml"
            else if (roleId == 2) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tb_admin.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
            // Si le rôle de l'utilisateur est 3, on charge l'interface utilisateur "tb_prof.fxml"
            else if (roleId == 3) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tb_prof.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        }
        // Si l'utilisateur n'est pas trouvé dans la source de données, on affiche un message d'erreur
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'authentification");
            alert.setHeaderText("Les informations d'identification sont incorrectes.");
            alert.showAndWait();
        }

    }
}
