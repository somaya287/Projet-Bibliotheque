package Sample.classes;

import javafx.beans.property.SimpleStringProperty;

public class Document {
    private static int number=-1;
    private final int ISBN; // ISBN ne doit pas etre changé , on lui rajoute l'attribut final 
    private String titre;
    private String auteur1;
    private String auteur2;
    private String editeur;
    private int edit_year;
    private int nb_exmp;
    
    // constructeur par initiation     
    public Document(int ISBN, String titre,String auteur1,String auteur2,String editeur, int edit_year) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.auteur1 = auteur1;
        this.auteur2 = auteur2;
        this.editeur = editeur;
        this.edit_year = edit_year;
        number++;
    }
    // la méthode toString
    @Override
    public String toString(){
        return "    " + this.ISBN + "    " + this.titre + "    " + this.auteur1 + "    " + this.auteur2 + "    " + this.editeur + "    " + this.edit_year + "    " + this.nb_exmp;
    }
    // la méthode incrémenter et décrémenter 
    public void incrémenter(){
        this.nb_exmp++;
    }
    public void décrémenter(){
        if(this.nb_exmp>0) this.nb_exmp--;
    }
    // destructeur 
    @Override
    protected void finalize() throws Throwable {
        System.out.println(this.toString());
        System.out.println("Document supprimé!!");
    }
    
    //getters  
    public int getnumber(){
        return number;
    }
    public int getISBN(){
        return ISBN;
    }
    public String getTitre(){
        return titre;
    }
    public String getAuteur1(){
        return auteur1;
    }
    public String getAuteur2(){
        return auteur2;
    }
    public String getEditeur(){
        return editeur;
    }
    public int getEdit_year(){
        return edit_year;
    }
    public int getNb_exmp(){
        return nb_exmp;
    }
    // setters 
    public void setTitre(String titre){
        this.titre=titre;
    }
    public void setAuteur1(String auteur1){
        this.auteur1=auteur1;
    }
    public void setAuteur2(String auteur2){
        this.auteur2=auteur2;
    }
    public void setEditeur(String editeur){
        this.editeur=editeur;
    }
    public void setEdit_year(int edit_year){
        this.edit_year=edit_year;
    }
    public void setNb_exmp(int nb_exmp){
        this.nb_exmp=nb_exmp;
    }       
}
