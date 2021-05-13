package Sample.classes;

public class Comics extends Document {
    private int chaptersNumber;
    private String genre;
    // constructeur 
    public Comics(int chaptersNumber, String genre, int ISBN, String titre, String auteur1,String auteur2, String editeur, int edit_year) {
        super(ISBN, titre, auteur1,auteur2, editeur, edit_year);
        this.chaptersNumber = chaptersNumber;
        this.genre = genre;
    }
    // meth toString
    @Override
    public String toString() {
        return super.toString() + "    " + this.chaptersNumber + "    " + this.genre;
    }
    //getters et setters

    public int getChaptersNumber() {
        return chaptersNumber;
    }

    public String getGenre() {
        return genre;
    }

    public void setChaptersNumber(int chaptersNumber) {
        this.chaptersNumber = chaptersNumber;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
