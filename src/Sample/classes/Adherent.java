package Sample.classes;

public class Adherent {
    private final int seuilDocuments=1;
    private String CIN;
    private String prenom;
    private String nom;
    private int age;
    private String ville;
    // constructeur
    public Adherent(String CIN,String prenom, String nom, int age, String ville){
        this.CIN=CIN;
        this.prenom=prenom;
        this.nom=nom;
        this.age=age;
        this.ville=ville;
    }
    // la méthode toString
    @Override
    public String toString() {
        return "Personne{" + "CIN='" + CIN + '\'' + ", prenom='" + prenom + '\'' + ", nom='" + nom + '\'' + ", age=" + age + ", ville='" + ville + '\'' + '}';
    }

    // les getters et les setters
    public int getSeuilDocuments() {
        return seuilDocuments;
    }
    public String getCIN() {
        return CIN;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getNom() {
        return nom;
    }
    public int getAge() {
        return age;
    }
    public String getVille() {
        return ville;
    }
    public void setCIN(String CIN) {
        this.CIN = CIN;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

}
