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
        String query1 = "insert into APICOURS(code,intitule) values(?,?)";
        String query2 = "select max(IDCOURS) from APICOURS where IDCOURS = ?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,cours.getCode());
            pstm1.setString(2,cours.getIntitule());
            int n = pstm1.executeUpdate();
              if(n==1){
                pstm2.setInt(1, cours.getIdCours());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idcours = rs.getInt(1);
                     cours.setIdCours(idcours);
                    notifyObservers();
                     return cours;
                }
                else {

                   System.err.println("record introuvable");
                    return null;
                }
            }
              else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

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
    public Cours readCours(int idCours) {
        String query = "SELECT * FROM APIEnseignantSalleCours WHERE IDCOURS = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idCours);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String code = rs.getString("Code");
                String intitule = rs.getString("Intitule");
                Cours cours = new Cours(idCours, code, intitule);

                int idEnseignant = rs.getInt("idEnseignant");
                String matricule = rs.getString("Matricule");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prénom");
                String tel = rs.getString("Tel");
                int chargesem = rs.getInt("ChargeSem");
                BigDecimal salaireMensu = rs.getBigDecimal("SalMensu");
                LocalDate dateEngagement = rs.getDate("Date d'engagement").toLocalDate();
                Enseignant enseignant = new Enseignant(idEnseignant, matricule, nom, prenom, tel, chargesem, salaireMensu, dateEngagement);

                int idSalle = rs.getInt("IDSALLE");
                String sigle = rs.getString("SIGLE");
                int capacite = rs.getInt("CAPACITE");
                Salle salle = new Salle(idSalle, sigle, capacite);


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
    public boolean addEnseignant(Cours cs,int nbreheures,Enseignant ens,Classe cl,Salle sa) {
        String query = "insert into  APIINFOS(nbreheures,idcours,idsalle,idenseignant,idclasse) values(?,?,?,?,?)";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,nbreheures);
            pstm.setInt(2,cs.getIdCours());
            pstm.setInt(3,sa.getIdSalle());
            pstm.setInt(4,ens.getIdEnseignant());
            pstm.setInt(5,cl.getIdClasse());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
             System.err.println("erreur sql :" + e);

            return false;
        }
    }

    @Override
    public boolean modifEnseignant(Cours cs,int nbreheures,Enseignant ens,Classe cl,Salle sa) {
        String query = "update  APIINFOS set nbreheures = ? where idcours = ? AND idsalle = ? AND idenseignant = ? AND idclasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,nbreheures);
            pstm.setInt(2,cs.getIdCours());
            pstm.setInt(3,sa.getIdSalle());
            pstm.setInt(4,ens.getIdEnseignant());
            pstm.setInt(5,cl.getIdClasse());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public boolean supEnseignant(Cours cs,Enseignant ens,Classe cl,Salle sa) {
        String query = "DELETE FROM  APIINFOS where  idcours = ? AND idsalle = ? AND idenseignant = ? AND idclasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,cs.getIdCours());
            pstm.setInt(2,sa.getIdSalle());
            pstm.setInt(3,ens.getIdEnseignant());
            pstm.setInt(4,cl.getIdClasse());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public List<Infos> getEnseignants(Cours cs) {
        String query = "SELECT i.*, e.*, s.* FROM INFOSENSEIGNANT i JOIN APIENSEIGNANT e ON i.IDENSEIGNANT = e.IDENSEIGNANT JOIN APISALLE s ON i.IDSALLE = s.IDSALLE WHERE i.IDCOURS = ?";
        List<Infos> li = new ArrayList<>();
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, cs.getIdCours());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idInfos = rs.getInt("IdInfos");
                int nbreHeures = rs.getInt("NbHeures");
                int idSalle = rs.getInt("IdSalle");
                int idEnseignant = rs.getInt("IdEnseignant");
                String matricule = rs.getString("Matricule");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prénom");
                String tel = rs.getString("Tel");
                int chargesem = rs.getInt("ChargeSem");
                BigDecimal salaireMensu = rs.getBigDecimal("SalMensu");
                LocalDate dateEngagement = rs.getDate("DateEngagement").toLocalDate();
                String sigle = rs.getString("Sigle");
                int capacite = rs.getInt("Capacite");
                Enseignant enseignant = new Enseignant(idEnseignant, matricule, nom, prenom, tel, chargesem, salaireMensu, dateEngagement);
                Salle salle = new Salle(idSalle, sigle, capacite);
                Infos infos = new Infos(nbreHeures, cs, salle, enseignant);
                li.add(infos);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return li;
    }

    @Override
    public boolean addSalle(Cours cs, int nbreheures, Salle sa, Classe cl, Enseignant ens) {
        String query = "insert into APIINFOS(nbreheures, idcours, idsalle, idenseignant, idclasse) values(?,?,?,?,?)";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, nbreheures);
            pstm.setInt(2, cs.getIdCours());
            pstm.setInt(3, sa.getIdSalle());
            pstm.setInt(4, ens.getIdEnseignant());
            pstm.setInt(5, cl.getIdClasse());
            int n = pstm.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public boolean modifSalle(Cours cs, int nbreheures, Salle sa, Classe cl, Enseignant ens) {
        String query = "update APIINFOS set nbreheures = ? where idcours = ? AND idsalle = ? AND idenseignant = ? AND idclasse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, nbreheures);
            pstm.setInt(2, cs.getIdCours());
            pstm.setInt(3, sa.getIdSalle());
            pstm.setInt(4, ens.getIdEnseignant());
            pstm.setInt(5, cl.getIdClasse());
            int n = pstm.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public boolean supSalle(Cours cs, Salle sa, Classe cl, Enseignant ens) {
        String query = "DELETE FROM APIINFOS where idcours = ? AND idsalle = ? AND idenseignant = ? AND idclasse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, cs.getIdCours());
            pstm.setInt(2, sa.getIdSalle());
            pstm.setInt(3, ens.getIdEnseignant());
            pstm.setInt(4, cl.getIdClasse());
            int n = pstm.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    public List<Infos> getSalles(Cours cs) {
        String query = "SELECT * FROM APIEnseignantSalleCours WHERE IDCOURS = ?";
        List<Infos> li = new ArrayList<>();
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, cs.getIdCours());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idInfos = rs.getInt("IDENSEIGNANT");
                int nbreHeures = rs.getInt("NBREHEURES");
                int idSalle = rs.getInt("IDSALLE");
                int idEnseignant = rs.getInt("IDENSEIGNANT");
                String matricule = rs.getString("MATRICULE");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String tel = rs.getString("TEL");
                int chargesem = rs.getInt("CHARGESEM");
                BigDecimal salaireMensu = rs.getBigDecimal("SALAIREMENSU");
                LocalDate dateEngagement = rs.getDate("DATEENGAG").toLocalDate();
                String sigle = rs.getString("SIGLE");
                int capacite = rs.getInt("CAPACITE");
                Enseignant enseignant = new Enseignant(idEnseignant, matricule, nom, prenom, tel, chargesem, salaireMensu, dateEngagement);
                Salle salle = new Salle(idSalle, sigle, capacite);
                Infos infos = new Infos(nbreHeures, cs, salle, enseignant);
                li.add(infos);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return li;
    }

    @Override
    public List getNotification() {
        return getCours();
    }
}
