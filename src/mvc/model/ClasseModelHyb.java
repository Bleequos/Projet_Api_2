package mvc.model;

import Ecole.metier.*;

import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClasseModelHyb extends DAOClasse{

    protected Connection dbConnect;

    private DAOSalle daoSalle;

    private DAOEnseignant daoEnseignant;

    private DAOCours daoCours;
    public ClasseModelHyb() {
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
        String query = "SELECT c.*, s.* FROM APICLASSE c JOIN APISALLE s ON c.sallepardefault = s.idsalle WHERE c.idclasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idClasse);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String sigle = rs.getString(2);
                int annee = rs.getInt(3);
                String specialite = rs.getString(4);
                int nbreEleves = rs.getInt(5);
                int idSalle = rs.getInt(6);
                String sigleSalle = rs.getString(7);
                int capacite = rs.getInt(8);
                Salle salleParDefault = new Salle(idSalle, sigleSalle, capacite);
                Classe cl = new Classe(idClasse, sigle, annee, specialite, nbreEleves, salleParDefault);
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
        String query="select * from APICLASSE";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idclasse = rs.getInt(1);
                String sigle = rs.getString(2);
                int annee = rs.getInt(3);
                String specialite = rs.getString(4);
                int nbreeleves = rs.getInt(5);
                int idSalleParDefault = rs.getInt(6);
                Salle salleParDefault = daoSalle.readSalle(idSalleParDefault);
                Classe cl = new Classe(idclasse,sigle,annee,specialite,nbreeleves,salleParDefault);
                lc.add(cl);
            }
            return lc;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }
    @Override
    public List<EnseignantsEtHeures>  listeEnseignantsEtHeures(Classe  classe) {
        List<EnseignantsEtHeures> leh = new ArrayList<>();
        String query = "SELECT e.*, i.nbreHeures FROM APIEnseignant e JOIN APIInfos i ON e.idEnseignant = i.idEnseignant WHERE i.idClasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,classe.getIdClasse());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                int idEnseignant = rs.getInt(2);
                String matricule = rs.getString(3);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                String tel = rs.getString(6);
                int chargesem= rs.getInt(7);
                BigDecimal salaireMensu = BigDecimal.valueOf(rs.getInt(8));
                LocalDate dateEngagement= rs.getDate(9).toLocalDate();
                int nbreHeures = rs.getInt(10);
                Enseignant en = new Enseignant(idEnseignant,matricule,nom,prenom,tel,chargesem,salaireMensu,dateEngagement);
                EnseignantsEtHeures eh = new EnseignantsEtHeures(en,nbreHeures);
                leh.add(eh);
            }
            return leh;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }
    @Override
    public List<SallesetHeures> listeSallesetHeures(Classe classe) {
        List<SallesetHeures> lsh = new ArrayList<>();
        String query = "SELECT s.*, i.nbreHeures FROM APISalle s JOIN APIInfos i ON s.idSalle = i.idSalle WHERE i.idClasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,classe.getIdClasse());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                int idSalle = rs.getInt(1);
                String sigle = rs.getString(2);
                int capacite= rs.getInt(3);
                int nbreHeures = rs.getInt(4);
                Salle salle = new Salle(idSalle, sigle, capacite);
                SallesetHeures sh = new SallesetHeures(nbreHeures, salle);
                lsh.add(sh);
            }
            return lsh;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public List<CoursEtHeures> listeCoursEtHeures(Classe classe) {
        List<CoursEtHeures> lch = new ArrayList<>();
        String query = "SELECT c.*, i.nbreHeures FROM APICours c JOIN APIInfos i ON c.idCours = i.idCours WHERE i.idClasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,classe.getIdClasse());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                int idCours = rs.getInt(2);
                String code = rs.getString(3);
                String intitule = rs.getString(3);
                Cours cours = new Cours(idCours, code,intitule);
                int heures = rs.getInt(4);
                CoursEtHeures ch = new CoursEtHeures(heures, cours);
                lch.add(ch);
            }
            return lch;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }
    @Override
    public List<Infos> listeInfos(Classe classe) {
        List<Infos> li = new ArrayList<>();
        String query = "SELECT * FROM APIInfos WHERE idClasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,classe.getIdClasse());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                int nbreHeures = rs.getInt(2);
                int idCours = rs.getInt(3);
                int idSalle = rs.getInt(4);
                int idEnseignant = rs.getInt(5);

                Cours cours = daoCours.readCours(idCours);
                Salle salle = daoSalle.readSalle(idSalle);
                Enseignant enseignant = daoEnseignant.readEnseignant(idEnseignant);

                Infos infos = new Infos( nbreHeures, cours, salle, enseignant);
                li.add(infos);
            }
            return li;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }
    @Override
    public List getNotification() {
        return getClasses();
    }
}
