package Sample.classes;

import Sample.classes.Document;

public class Magazine extends Document {
    private double period;
    private int moisEdit;
    private int jourEdit;
    // constructeur 
    public Magazine(double period, int moisEdit, int jourEdit, int ISBN, String titre, String auteur1,String auteur2, String editeur, int edit_year) {
        super(ISBN, titre, auteur1,auteur2, editeur, edit_year);
        this.period = period;
        this.moisEdit = moisEdit;
        this.jourEdit = jourEdit;
    }
    // la méthode toString
    @Override
    public String toString() {
        return super.toString() + "    " + this.period + "    " + this.moisEdit + "    " + this.jourEdit;
    }
    //setters et getters


    public double getPeriod() {
        return period;
    }

    public int getJourEdit() {
        return jourEdit;
    }

    public int getMoisEdit() {
        return moisEdit;
    }

    public void setJourEdit(int jourEdit) {
        this.jourEdit = jourEdit;
    }

    public void setMoisEdit(int moisEdit) {
        this.moisEdit = moisEdit;
    }

    public void setPeriod(double period) {
        this.period = period;
    }
}
