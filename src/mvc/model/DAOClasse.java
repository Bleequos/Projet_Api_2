package mvc.model;

import Ecole.metier.*;

import mvc.observer.Subject;

import java.util.List;

public abstract class DAOClasse extends Subject {
    public abstract Classe addClasse(Classe classe);

    public abstract boolean removeClasse(Classe classe);

    public abstract Classe updateClasse(Classe classe);

    public abstract Classe readClasse(int idClasse);

    public abstract List<Classe> getClasses();

    public abstract List<EnseignantsEtHeures> listeEnseignantsEtHeures(Classe classe);

    public abstract List<SallesetHeures> listeSallesetHeures(Classe classe);

    public abstract List<CoursEtHeures> listeCoursEtHeures(Classe classe);

    public abstract List<Infos> listeInfos(Classe classe);

}
