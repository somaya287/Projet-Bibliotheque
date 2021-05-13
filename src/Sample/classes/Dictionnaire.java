package Sample.classes;

public class Dictionnaire extends Document {
    private String langue;
    private int nbTomes;
    //constructeur
    public Dictionnaire(String langue, int nbTomes, int ISBN, String titre, String auteur1,String auteur2, String editeur, int edit_year) {
        super(ISBN, titre, auteur1, auteur2 , editeur, edit_year);
        this.langue = langue;
        this.nbTomes = nbTomes;
    }
    // la méthode toString
    @Override
    public String toString() {
        return super.toString() + "    " + this.langue + "    " + this.nbTomes;
    }
    // getters et setters

    public int getNbTomes() {
        return nbTomes;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public void setNbTomes(int nbTomes) {
        this.nbTomes = nbTomes;
    }
}
