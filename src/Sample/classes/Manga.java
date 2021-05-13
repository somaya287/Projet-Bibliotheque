package Sample.classes;

import Sample.classes.Comics;

public class Manga extends Comics {
    private String format; // s'il s'agit d'un OneShot ou Yonkoma ou Webcomic
    // constructeur 
    public Manga(String format, int chaptersNumber, String genre, int ISBN, String titre, String auteur1, String auteur2, String editeur, int edit_year) {
        super(chaptersNumber, genre, ISBN, titre, auteur1, auteur2, editeur, edit_year);
        this.format = format;
    }  
    // la méthode toString
    @Override
    public String toString() {
        return super.toString() + "    " + this.format;
    }
    // getter et setter

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
