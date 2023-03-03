package DataBase;


import Modele.Projets;
import Modele.Promotions;
import Modele.TableView;
import Modele.Utilisateurs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBinomotroon {
    /**
     * Cette méthode récupère tous les apprenants d'une promotion donnée à partir du nom de la promotion.
     * Elle se connecte à la base de données, exécute une requête SQL pour récupérer les apprenants et leurs informations,
     * crée des objets Utilisateurs à partir des résultats et les ajoute à une liste.
     **/
    public static ArrayList<Utilisateurs> getApprenantsByPromotion(String nomPromotion) {
        ArrayList<Utilisateurs> apprenantsList = new ArrayList<>();
        try {
            Connection connection = DataAccess.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM utilisateurs WHERE id_promotion = (SELECT id FROM promotions WHERE nom = ?)");
            preparedStatement.setString(1, nomPromotion);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Utilisateurs apprenant = new Utilisateurs(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("mail"), resultSet.getInt("id_role"), resultSet.getInt("id_promotion"));
                apprenantsList.add(apprenant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.close();
        }
        return apprenantsList;
    }
    /**
     * Cette méthode récupère un utilisateur à partir de son adresse mail et son nom.
     * Elle se connecte à la base de données, exécute une requête SQL pour récupérer
     * les informations de l'utilisateur et crée un objet Utilisateurs à partir des résultats.
    **/
    public static Utilisateurs getUser(String mail, String nom) {
        Utilisateurs user = null;

        try {
            Statement requete = DataAccess.getInstance().createStatement();
            String sql = "SELECT * FROM utilisateurs WHERE mail = '" + mail + "' AND nom = '" + nom + "'";
            ResultSet lecture = requete.executeQuery(sql);

            if (lecture.next()) {
                user = new Utilisateurs(lecture.getInt("id"), lecture.getString("nom"), lecture.getString("prenom"), lecture.getString("mail"), lecture.getInt("id_role"), lecture.getInt("id_promotion"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.close();
        }

        return user;
    }
    /**
     * Cette méthode récupère le rôle d'un utilisateur à partir de son ID.
     * Elle se connecte à la base de données, exécute une requête SQL pour
     * récupérer le rôle de l'utilisateur et retourne le résultat.
    **/
    public static int getRoleId(int userId) {
        int roleId = -1;

        try {
            Statement requete = DataAccess.getInstance().createStatement();
            String sql = "SELECT id_role FROM utilisateurs WHERE id_role = " + userId;
            ResultSet lecture = requete.executeQuery(sql);

            if (lecture.next()) {
                roleId = lecture.getInt("id_role");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.close();
        }

        return roleId;
    }

    /**
     * Cette méthode récupère toutes les promotions à partir de la base de données.
     * Elle se connecte à la base de données, exécute une requête SQL pour récupérer
     * les informations de chaque promotion et crée des objets Promotions à partir des résultats.
     */

    public static List<Promotions> getPromotions() {
        List<Promotions> promotionsList = new ArrayList<>();
        try {
            Statement statement = DataAccess.getInstance().createStatement();
            String sql = "SELECT promotions.id AS id_promot, nom, date_promotion FROM promotions WHERE nom != 'AUTRE'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Promotions promotion = new Promotions(resultSet.getInt("id_promot"), resultSet.getString("nom"), resultSet.getDate("date_promotion"));
                promotionsList.add(promotion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.close();
        }
        return promotionsList;
    }

    /**
     * Cette méthode récupère toutes les promotions pour lesquelles un professeur a un projet.
     * Elle se connecte à la base de données, exécute une requête SQL pour récupérer les informations
     * de chaque promotion et crée des objets Promotions à partir des résultats.
     */

    public static ArrayList<Promotions> getProfPromotions() {
        ArrayList<Promotions> profPromotions = new ArrayList<>();

        try {
            Statement requete = DataAccess.getInstance().createStatement();
            String sql = "SELECT * FROM promotions WHERE id != 2 ORDER BY date_promotion ASC";
            ResultSet lecture = requete.executeQuery(sql);

            while (lecture.next()) {
                Promotions profPromotionsLue = new Promotions(
                        lecture.getInt("id"),
                        lecture.getString("nom"),
                        lecture.getDate("date_promotion")
                );
                profPromotions.add(profPromotionsLue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.close();
        }

        return profPromotions;
    }

    /**
     *  Cette méthode récupère tous les groupes de projets et les apprenants associés à partir de la base de données.
     *  Elle se connecte à la base de données, exécute une requête SQL pour récupérer les informations de chaque groupe de
     *  projet et crée des objets TableView à partir des résultats.
     */

    public static ArrayList<TableView> getApprenantsGroupes() {
        ArrayList<TableView> apprenantsGroupes = new ArrayList<>();

        try {
            Statement requete = DataAccess.getInstance().createStatement();
            String sql = "SELECT * FROM `projet_groupe`";
            ResultSet lecture = requete.executeQuery(sql);

            while (lecture.next()) {
                TableView apprenantGroupeLue = new TableView(
                        lecture.getString("membres"),
                        lecture.getString("nom_groupe"),
                        lecture.getString("nom_projet")
                );
                apprenantsGroupes.add(apprenantGroupeLue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.close();
        }

        return apprenantsGroupes;
    }

    /**
     * Cette méthode effectue une recherche filtrée sur les apprenants en fonction du prénom.
     * Elle se connecte à la base de données, exécute une requête SQL pour récupérer les informations
     * de chaque apprenant correspondant au prénom et crée des objets TableView à partir des résultats.
     * @param prenom
     * @return
     */
    public static List<TableView> searchApprenantsByPrenom(String prenom) {
        List<TableView> resultats = new ArrayList<>();

        try {
            Statement requete = DataAccess.getInstance().createStatement();
            String sql = "SELECT nom_projet, nom_groupe, membres FROM `projet_groupe` WHERE membres LIKE '%" + prenom + "%'";

            ResultSet lecture = requete.executeQuery(sql);

            while (lecture.next()) {
                TableView apprenantGroupeLue = new TableView(
                        lecture.getString("membres"),
                        lecture.getString("nom_groupe"),
                        lecture.getString("nom_projet")
                );
                resultats.add(apprenantGroupeLue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.close();
        }

        return resultats;
    }

    /**
     * Cette méthode récupère tous les projets créés par un professeur à partir de la base de données.
     * Elle se connecte à la base de données, exécute une requête SQL pour récupérer les informations
     * de chaque projet et crée des objets Projets à partir des résultats.
     * @return
     */

    public static ArrayList<Projets> getProfProjets() {
        ArrayList<Projets> profProjets = new ArrayList<>();

        try {
            Statement requete = DataAccess.getInstance().createStatement();
            String sql = "SELECT * FROM `userstory`";
            ResultSet lecture = requete.executeQuery(sql);

            while (lecture.next()) {
                Projets profProjetsLue = new Projets(
                        lecture.getInt("id"),
                        lecture.getString("nom_projet"),
                        lecture.getString("createur")
                );
                profProjets.add(profProjetsLue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.close();
        }

        return profProjets;
    }

    /**
     * Cette méthode récupère tous les professeurs à partir de la base de données.
     * Elle se connecte à la base de données, exécute une requête SQL pour récupérer
     * les informations de chaque professeur et crée des objets Utilisateurs à partir des résultats.
     * @return
     */

    public static ArrayList<Utilisateurs> getProfs() {
        ArrayList<Utilisateurs> prof = new ArrayList<>();

        try {
            Statement requete = DataAccess.getInstance().createStatement();
            String sql = "SELECT * FROM utilisateurs INNER JOIN roles ON utilisateurs.id_role = roles.id WHERE roles.role = 'professeur'";
            ResultSet lecture = requete.executeQuery(sql);

            while (lecture.next()) {
                Utilisateurs profLue = new Utilisateurs(
                        lecture.getInt("id"),
                        lecture.getString("nom"),
                        lecture.getString("prenom"),
                        lecture.getString("mail"),
                        lecture.getInt("id_role"),
                        lecture.getInt("id_promotion")
                );
                prof.add(profLue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.close();
        }

        return prof;

    }

    /**
     *  Cette méthode récupère tous les nouveaux projets créés par un utilisateur à partir de la base de données.
     *  Elle se connecte à la base de données, exécute une requête SQL pour récupérer les informations de chaque
     *  projet et crée des objets Projets à partir des résultats.
     * @return
     */

    public static ArrayList<Projets> newProjects() {
        ArrayList<Projets> newProjects = new ArrayList<>();

        try {
            Statement requete = DataAccess.getInstance().createStatement();
            String sql = "SELECT * FROM `userstory`";
            ResultSet lecture = requete.executeQuery(sql);

            while (lecture.next()) {
                Projets profProjetsLue = new Projets(
                        lecture.getInt("id"),
                        lecture.getString("nom_projet"),
                        lecture.getString("createur")
                );
                newProjects().add(profProjetsLue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.close();
        }

        return newProjects();
    }
    //private static void insertProjet(Projets projet) {


}