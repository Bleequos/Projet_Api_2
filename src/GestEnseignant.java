import metier.Enseignant;
import myconnections.DBConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Ahmed
 */
public class GestEnseignant {

    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        do {
            System.out.println("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.tous\n6.fin");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    tous();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }




    public void ajout() {
        System.out.print("matricule du professeur :");
        String matricule = sc.nextLine();
        System.out.print("nom du professeur :");
        String nom = sc.nextLine();
        System.out.print("prénom du professeur :");
        String prenom = sc.nextLine();
        System.out.print("télephone du professeur :");
        String tel = sc.nextLine();
        System.out.print("Charge sem :");
        int chargesem = sc.nextInt();
        System.out.print("Salaire mensuel :");
        BigDecimal salairemensuel = BigDecimal.valueOf(sc.nextInt());
        System.out.println("date d'engagemenet : ");
        System.out.println("annéee : ");
        int annee = sc.nextInt();
        System.out.println("mois : ");
        int mois = sc.nextInt();
        System.out.println("jour : ");
        int jour = sc.nextInt();
        LocalDate dateEngagement = LocalDate.of(annee, mois, jour);
        String query1 = "insert into APIENSEIGNANT(MATRICULE,NOM,PRENOM,TEL,CHARGESEM,SALAIREMENSU,DATEENGAG) values(?,?,?,?,?,?,?)";
        String query2 = "select IDENSEIGNANT from APIENSEIGNANT where MATRICULE= ?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,matricule);
            pstm1.setString(2,nom);
            pstm1.setString(3,prenom);
            pstm1.setString(4,tel);
            pstm1.setInt(5,chargesem);
            pstm1.setBigDecimal(6, salairemensuel);
            pstm1.setDate(7, Date.valueOf(dateEngagement));
            int n = pstm1.executeUpdate();
            System.out.println(n+" ligne insérée");
            if(n==1){
                pstm2.setString(1,matricule);
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int IDENSEIGNANT= rs.getInt(1);
                    System.out.println("IDENSEIGNANT = "+IDENSEIGNANT);
                }
                else System.out.println("record introuvable");
            }
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }



    public void recherche() {
        System.out.println("id du prof recherché ");
        int idrech = sc.nextInt();
        String query = "select * from APIENSEIGNANT where IDENSEIGNANT = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                int chargesem= rs.getInt(6);
                BigDecimal salaireMensu = BigDecimal.valueOf(rs.getInt(7));
                LocalDate dateEngagement= rs.getDate(8).toLocalDate();
                Enseignant Ens= new Enseignant(idrech,matricule,nom,prenom,tel,chargesem,salaireMensu,dateEngagement);
                System.out.println(Ens);
            }
            else System.out.println("record introuvable");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

    public void modification() {
        System.out.println("id du client recherché ");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("nouveau matricule ");
        String nmatricule = sc.nextLine();
        String query = "update APIENSEIGNANT set MATRICULE=? where IDENSEIGNANT = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,nmatricule);
            pstm.setInt(2,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne mise à jour");
            else System.out.println("record introuvable");
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }
    public void suppression() {
        System.out.println("id du prof recherché ");
        int idrech = sc.nextInt();
        String query = "delete from APIENSEIGNANT where IDENSEIGNANT = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne supprimée");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

    private void tous() {
        String query="select * from APIENSEIGNANT";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idEnseignant = rs.getInt(1);
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                int chargesem= rs.getInt(6);
                BigDecimal salaireMensu = BigDecimal.valueOf(rs.getInt(7));
                LocalDate dateEngagement= rs.getDate(8).toLocalDate();
                Enseignant Ens= new Enseignant(idEnseignant,matricule,nom,prenom,tel,chargesem,salaireMensu,dateEngagement);
                System.out.println(Ens);
            }
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

    public static void main(String[] args) {

        GestEnseignant g = new GestEnseignant();
        g.gestion();
    }

}

