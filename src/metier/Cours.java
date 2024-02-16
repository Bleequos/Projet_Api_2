package metier;

public class Cours {

    protected int idCours ;

    protected String code ;

    protected String intitule;

    public void setCode(String code) {
        this.code = code;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getCode() {
        return code;
    }

    public String getIntitule() {
        return intitule;
    }

    public Cours(int idCours, String code, String intitule) {
        this.idCours = idCours;
        this.code = code;
        this.intitule = intitule;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public int getIdCours() {
        return idCours;
    }
}
