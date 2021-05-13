package Sample.classes;

import Sample.classes.Document;

public class Livre extends Document {
    private int nbPages;
    private String type;
    private int tome;
    // constructeur 
    public Livre(int nbPages, String type, int tome, int ISBN, String titre, String auteur1,String auteur2, String editeur, int edit_year) {
        super(ISBN, titre, auteur1,auteur2, editeur, edit_year);
        this.nbPages = nbPages;
        this.type = type;
        this.tome = tome;
    }
    //la méthode toString
    @Override
    public String toString() {
        return super.toString() + "    " + this.nbPages + "    " + this.type + "    " + this.tome;
    }
    // setters et getters


    public int getNbPages() {
        return nbPages;
    }

    public int getTome() {
        return tome;
    }

    public String getType() {
        return type;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTome(int tome) {
        this.tome = tome;
    }   
} 
