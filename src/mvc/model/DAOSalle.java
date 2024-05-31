package mvc.model;

import Ecole.metier.Classe;
import mvc.observer.Subject;
import Ecole.metier.Salle;

import java.util.List;

public abstract class DAOSalle extends Subject {
    public abstract Salle addSalle(Salle salle);

    public abstract boolean removeSalle(Salle salle);

    public abstract Salle updateSalle(Salle salle);

   public abstract Salle readSalle(int idSalle);

    public abstract List<Salle> getSalles();

    public abstract List<Classe> ClassesSalleDefaut(Salle salle);
}
