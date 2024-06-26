package mvc.model;


import Ecole.metier.Classe;
import Ecole.metier.Salle;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SalleModelDB extends DAOSalle {

    protected Connection dbConnect;

    public SalleModelDB(){
        dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
               System.err.println("erreur de connexion");

                System.exit(1);
            }

    }

    @Override
    public Salle addSalle(Salle salle) {
        String query1 = "insert into APISALLE(SIGLE,CAPACITE) values(?,?)";
        String query2 = "select IDSALLE from APISALLE where SIGLE= ?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1, salle.getSigle());
            pstm1.setInt(2, salle.getCapacite());
            int n = pstm1.executeUpdate();
              if(n==1){
                pstm2.setString(1,salle.getSigle());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int IDSALLE= rs.getInt(1);
                     salle.setIdSalle(IDSALLE);
                    notifyObservers();
                     return salle;
                }
                else {

                  System.err.println("record introuvable");
                    return null;
                }
            }
              else return null;

        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public boolean removeSalle(Salle salle) {
        String query = "delete from APISALLE where IDSALLE= ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,salle.getIdSalle());
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
    public Salle updateSalle(Salle salle) {
        String query = "UPDATE APISALLE SET SIGLE = ?, CAPACITE = ? WHERE IDSALLE = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, salle.getSigle());
            pstm.setInt(2, salle.getCapacite());
            pstm.setInt(3, salle.getIdSalle());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readSalle(salle.getIdSalle());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }
    @Override
    public Salle readSalle(int idSalle) {
        String query = "select * from APISALLE where IDSALLE = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idSalle);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String sigle = rs.getString(2);
                int capacite= rs.getInt(3);
                Salle sa= new Salle(idSalle,sigle,capacite);
                return  sa;

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
    public List<Salle> getSalles() {
        List<Salle> ls = new ArrayList<>();
        String query="select * from APISALLE";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idSalle = rs.getInt(1);
                String sigle = rs.getString(2);
                int capacite= rs.getInt(3);
                Salle sa= new Salle(idSalle,sigle,capacite);
                ls.add(sa);
            }
          return ls;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }


    @Override
    public List<Classe> ClassesSalleDefaut(Salle salle){
        List<Classe> classes = new ArrayList<>();
        String query = "SELECT * FROM APIClasses_Salles WHERE IDSALLE = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, salle.getIdSalle());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                int idClasse = rs.getInt("IDCLASSE");
                String sigle = rs.getString("SIGLE");
                int annee = rs.getInt("ANNEE");
                String specialite = rs.getString("SPECIALITE");
                int nbreEleves = rs.getInt("NBREELEVES");
                Classe classe = new Classe(idClasse, sigle, annee, specialite, nbreEleves, salle);
                classes.add(classe);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
        }
        return classes;
    }



    @Override
    public List getNotification() {
        return getSalles();
    }
}
