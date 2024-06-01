package mvc.model;

import Ecole.metier.*;

import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class ClasseModelHyb extends  DAOClasse  {
    protected Connection dbConnect;


    public ClasseModelHyb(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }

    }

    @Override
    public Classe addClasse(Classe classe) {
        String query1 = "insert into APICLASSE(sigle,annee,specialite,nbreeleves,sallepardefault) values(?,?,?,?,?)";
        String query2 = "select IDCLASSE from APICLASSE where sigle= ? ";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,classe.getSigle());
            pstm1.setInt(2,classe.getAnnee());
            pstm1.setString(3,classe.getSpecialite());
            pstm1.setInt(4,classe.getNbreEleves());
            pstm1.setInt(5,classe.getSalleParDefault().getIdSalle());
            int n = pstm1.executeUpdate();
            if(n==1){
                pstm2.setString(1,classe.getSigle());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idclasse= rs.getInt(1);
                    classe.setIdClasse(idclasse);
                    notifyObservers();
                    return classe;
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
    public boolean removeClasse(Classe classe) {
        String query = "delete from APICLASSE where IDCLASSE = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,classe.getIdClasse());
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
    public Classe updateClasse(Classe classe) {
        String query = "update APICLASSE set sigle =?,annee=?,specialite=?,nbreeleves=?,sallepardefault=? where IDCLASSE = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,classe.getSigle());
            pstm.setInt(2,classe.getAnnee());
            pstm.setString(3,classe.getSpecialite());
            pstm.setInt(4,classe.getNbreEleves());
            pstm.setInt(5,classe.getSalleParDefault().getIdSalle());
            pstm.setInt(6,classe.getIdClasse());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readClasse(classe.getIdClasse());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Classe readClasse(int idClasse) {
        String query = "select * from APICLASSE_VIEW where IDCLASSE = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idClasse);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String sigle = rs.getString("SIGLE");
                int annee = rs.getInt("ANNEE");
                String specialite = rs.getString("SPECIALITE");
                int nbreeleves = rs.getInt("NBREELEVES");
                int idSalleParDefault = rs.getInt("IDSALLE");
                String sigleSalle = rs.getString("SIGLESA");
                int capacite = rs.getInt("CAPACITE");
                Salle salleParDefault = new Salle(idSalleParDefault, sigleSalle, capacite);
                Classe cl = new Classe(idClasse, sigle, annee, specialite, nbreeleves, salleParDefault);
                return cl;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public List<Classe> getClasses() {
        List<Classe> lc = new ArrayList<>();
        String query="select * from APICLASSE_VIEW";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idclasse = rs.getInt("IDCLASSE");
                String sigle = rs.getString("SIGLE");
                int annee = rs.getInt("ANNEE");
                String specialite = rs.getString("SPECIALITE");
                int nbreeleves = rs.getInt("NBREELEVES");
                int idsalle = rs.getInt("IDSALLE");
                String siglesa = rs.getString("SIGLESA");
                int capacite = rs.getInt("CAPACITE");
                Salle salleParDefault = new Salle(idsalle, siglesa, capacite);
                Classe cl = new Classe(idclasse, sigle, annee, specialite, nbreeleves, salleParDefault);
                lc.add(cl);
            }
            return lc;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }
    @Override
    public List<EnseignantsEtHeures> listeEnseignantsEtHeures(Classe classe) {
        return classe.listeEnseignantsEtHeures();
    }
    @Override
    public List<SallesetHeures> listeSallesetHeures(Classe classe) {
        return classe.listeSallesetHeures();
    }

    @Override
    public List<CoursEtHeures> listeCoursEtHeures(Classe classe) {
        return classe.listeCoursEtHeures();
    }
    @Override
    public List<Infos> listeInfos(Classe classe) {
        return classe.getInfos();
    }

    public int nbreHeuresTot(Classe classe){
        return classe.nbreHeuresTot();
    }

    public boolean salleCapaciteOK(Classe classe,Salle salle){
        return classe.salleCapaciteOK(salle);
    }

    public boolean addCours(Classe classe, Cours cours, Enseignant enseignant, int heure){
        String query = "INSERT INTO APIInfos(nbreHeures, idCours, idSalle, idEnseignant, idClasse) VALUES(?,?,?,?,?)";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, heure);
            pstm.setInt(2, cours.getIdCours());
            pstm.setInt(3, classe.getSalleParDefault().getIdSalle());
            pstm.setInt(4, enseignant.getIdEnseignant());
            pstm.setInt(5, classe.getIdClasse());
            int n = pstm.executeUpdate();
            notifyObservers();
            return n != 0;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return false;
        }
    }

    public boolean modifCours(Classe classe, Cours cours, int heure){
        String query = "UPDATE APIInfos SET nbreHeures = ? WHERE idCours = ? AND idClasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,heure);
            pstm.setInt(2,cours.getIdCours());
            pstm.setInt(3,classe.getIdClasse());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return false;
        }
    }

    public boolean modifCours(Classe classe, Cours cours, Enseignant enseignant){
        String query = "UPDATE APIInfos SET idEnseignant = ? WHERE idCours = ? AND idClasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,enseignant.getIdEnseignant());
            pstm.setInt(2,cours.getIdCours());
            pstm.setInt(3,classe.getIdClasse());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return false;
        }
    }

    public boolean modifCours(Classe classe, Cours cours, Salle salle){
        String query = "UPDATE APIInfos SET idSalle = ? WHERE idCours = ? AND idClasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,salle.getIdSalle());
            pstm.setInt(2,cours.getIdCours());
            pstm.setInt(3,classe.getIdClasse());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return false;
        }
    }

    public boolean suppCours(Classe classe, Cours cours){
        String query = "DELETE FROM APIInfos WHERE idCours = ? AND idClasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,cours.getIdCours());
            pstm.setInt(2,classe.getIdClasse());
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
    public List getNotification() {
        return getClasses();
    }
}
