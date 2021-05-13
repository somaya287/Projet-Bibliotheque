package Sample.classes;

public class Emprunt {
    private String CIN;
    private String nom;
    private String prenom;
    private int ISBN;
    private String titre;
    private String date;
    //constructeur
    public Emprunt(String CIN,String nom,String prenom,int ISBN,String titre,String date) {
        this.CIN=CIN;
        this.nom=nom;
        this.prenom=prenom;
        this.ISBN=ISBN;
        this.titre=titre;
        this.date=date;
    }
    //getters et setters
    public String getCIN() {
        return CIN;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public int getISBN() {
        return ISBN;
    }
    public String getTitre() {
        return titre;
    }
    public String getDate() {
        return date;
    }
    public void setCIN(String CIN) {
        this.CIN = CIN;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
