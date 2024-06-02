package mvc.model;

import Ecole.metier.*;

import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CoursModelDB extends DAOCours {
    private Connection dbConnect;

    public CoursModelDB(){
        dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
               System.err.println("erreur de connexion");
                  System.exit(1);
            }

    }


    @Override
    public Cours addCours(Cours cours) {
        String query1 = "INSERT INTO APICOURS(CODE, INTITULE) VALUES(?, ?)";
        String query2 = "SELECT max(IDCOURS) FROM APICOURS WHERE CODE = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            pstm1.setString(1, cours.getCode());
            pstm1.setString(2, cours.getIntitule());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, cours.getCode());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int IDCOURS = rs.getInt(1);
                    cours.setIdCours(IDCOURS);
                    notifyObservers();
                    return cours;
                } else {
                    System.err.println("record introuvable");
                    return null;
                }
            } else return null;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public boolean removeCours(Cours cours) {
        String query = "delete from APICOURS where IDCOURS = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,cours.getIdCours());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return false;
        }
    }

    @Override
    public Cours updateCours(Cours cours) {
        String query = "update APICOURS set code =?,intitule=? where IDCOURS = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,cours.getCode());
            pstm.setString(2,cours.getIntitule());
            pstm.setInt(3,cours.getIdCours());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readCours(cours.getIdCours());
            else return null;

        } catch (SQLException e) {
           System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<Cours> getCoursesByClass(Classe cl) {
        List<Cours> lc = new ArrayList<>();
        String query = "SELECT c.* FROM APICOURS c JOIN APIInfos i ON c.idCours = i.idCours WHERE i.idClasse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, cl.getIdClasse());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idCours = rs.getInt("IDCOURS");
                String code = rs.getString("CODE");
                String intitule = rs.getString("INTITULE");
                Cours cours = new Cours(idCours, code, intitule);
                lc.add(cours);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return lc;
    }

    @Override
    public Cours readCours(int idCours) {
        String query = "SELECT * FROM APICours WHERE IDCOURS = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idCours);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String code = rs.getString("Code");
                String intitule = rs.getString("Intitule");
                Cours cours = new Cours(idCours, code, intitule);
                return cours;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<Cours> getCours() {
        List<Cours> lcs = new ArrayList<>();
        String query = "SELECT * FROM APICOURS";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int idCours = rs.getInt("IdCours");
                String code = rs.getString("Code");
                String intitule = rs.getString("Intitule");
                Cours cours = new Cours(idCours, code, intitule);
                lcs.add(cours);
            }
            return lcs;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List getNotification() {
        return getCours();
    }

    public List<Infos> getListInfos() {
        List<Infos> li = new ArrayList<>();
        String query = "SELECT * FROM APIEnseignantSalleCours";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nbreHeures = rs.getInt("NBREHEURES");

                int idCours = rs.getInt("IDCOURS");
                String code = rs.getString("CODE");
                String intitule = rs.getString("INTITULE");
                Cours cours = new Cours(idCours, code, intitule);

                int idSalle = rs.getInt("IDSALLE");
                String sigle = rs.getString("SIGLE");
                int capacite = rs.getInt("CAPACITE");
                Salle salle = new Salle(idSalle, sigle, capacite);

                int idEnseignant = rs.getInt("IDENSEIGNANT");
                String matricule = rs.getString("MATRICULE");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String tel = rs.getString("TEL");
                int chargeSem = rs.getInt("CHARGESEM");
                BigDecimal salaireMensu = rs.getBigDecimal("SALAIREMENSU");
                LocalDate dateEngag = rs.getDate("DATEENGAG").toLocalDate();
                Enseignant enseignant = new Enseignant(idEnseignant, matricule, nom, prenom, tel, chargeSem, salaireMensu, dateEngag);

                Infos infos = new Infos(nbreHeures, cours, salle, enseignant);
                li.add(infos);
            }
            return li;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    public Boolean addCoursInfos(Classe classe, Cours cours, Enseignant enseignant, int heure){
        String query = "INSERT INTO APIInfos(nbreHeures, idCours, idSalle, idEnseignant, idClasse) VALUES(?,?,?,?,?)";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, heure);
            pstm.setInt(2, cours.getIdCours());
            pstm.setInt(3, classe.getSalleParDefault().getIdSalle());
            pstm.setInt(4, enseignant.getIdEnseignant()); // Use the idEnseignant from the Enseignant object
            pstm.setInt(5, classe.getIdClasse());
            int n = pstm.executeUpdate();
            notifyObservers();
            return n != 0;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return false;
        }
    }

public Boolean suppCoursInfos(Cours cours){
        String query = "DELETE FROM APIInfos WHERE idCours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, cours.getIdCours());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }

    public Boolean ModifierCoursInfos(Classe classe,Cours cours, Enseignant enseignant, int heure){
        String query = "UPDATE APIInfos SET nbreHeures = ?, idEnseignant = ?, idClasse = ? WHERE idCours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, heure);
            pstm.setInt(2, enseignant.getIdEnseignant());
            pstm.setInt(3, classe.getIdClasse());
            pstm.setInt(4, cours.getIdCours());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return false;
        }
    }

}
