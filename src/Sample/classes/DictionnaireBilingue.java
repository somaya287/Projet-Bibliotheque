package Sample.classes;

import Sample.classes.Dictionnaire;

public class DictionnaireBilingue extends Dictionnaire {
    private String langue2;
    // constructeur 
    public DictionnaireBilingue(String langue2, String langue, int nbTomes, int ISBN, String titre, String auteur1,String auteur2, String editeur, int edit_year) {
        super(langue, nbTomes, ISBN, titre, auteur1,auteur2, editeur, edit_year);
        this.langue2 = langue2;
    }
    // méth toString
    @Override
    public String toString() {
        return super.toString() + "    " + this.langue2;
    }
    //getters et setters


    public String getLangue2() {
        return langue2;
    }

    public void setLangue2(String langue2) {
        this.langue2 = langue2;
    }
}
