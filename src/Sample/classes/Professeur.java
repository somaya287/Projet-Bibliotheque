package Sample.classes;

public class Professeur extends Adherent {
    private final int seuilDocuments=5;
    // constructeur
    public Professeur(String CIN, String prenom, String nom , int age ,String ville){
        super(CIN,prenom, nom, age, ville);
    }
    // la méthode toString
    @Override
    public String toString() {
        return super.toString() + "Professeur" ;
    }
    //getter et setter
    public int getSeuilDocuments() {
        return seuilDocuments;
    }
}
