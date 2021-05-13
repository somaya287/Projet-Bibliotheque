package Sample.classes;

public class Etudiant extends Adherent {
    private final int seuilDocuments=3;
    private String CNE;
    // le constructeur
    public Etudiant(String CNE,String CIN, String prenom, String nom, int age, String ville){
        super(CIN,prenom,nom,age,ville);
        this.CNE=CNE;
    }
    //la méthode toString
    @Override
    public String toString() {
        return super.toString() + "Etudiant{" + "CNE='" + CNE + '\'' + '}';
    }
    // getter et setter
    public int getSeuilDocuments() {
        return seuilDocuments;
    }
    public String getCNE() {
        return CNE;
    }
    public void setCNE(String CNE) {
        this.CNE = CNE;
    }
}
