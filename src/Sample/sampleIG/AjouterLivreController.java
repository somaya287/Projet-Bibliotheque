package Sample.sampleIG;

import Sample.classes.Livre;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AjouterLivreController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Ajouter livre ss scene");
    }
    @FXML private TextField ISBNfield;
    @FXML private TextField titrefield;
    @FXML private TextField auteur1field;
    @FXML private TextField typefield;
    @FXML private TextField tomefield;
    @FXML private TextField nbpagesfield;
    @FXML private TextField edityearfield;
    @FXML private TextField editeurfield;
    @FXML private TextField auteur2field;
    @FXML
    void AjouterLivre(javafx.event.ActionEvent e) throws IOException, SQLException {
        System.out.println(auteur2field.getText());
        if(ISBNfield.getText()=="" || titrefield.getText()=="" || auteur1field.getText()=="") {
            JOptionPane.showMessageDialog(null, " Des champs obligatoires sont encore vides! ");
        }
        else{
            Livre liv = new Livre(Integer.parseInt(nbpagesfield.getText()),typefield.getText(),Integer.parseInt(tomefield.getText()),Integer.parseInt(ISBNfield.getText()),titrefield.getText(),auteur1field.getText(),auteur2field.getText(),editeurfield.getText(),Integer.parseInt(edityearfield.getText()));
            Connection conn = DriverManager.getConnection(" ", " ", " ");
            PreparedStatement stat0 = conn.prepareStatement("select * from documents where ISBN=?");
            stat0.setInt(1, liv.getISBN());
            ResultSet result = stat0.executeQuery();
            boolean value = result.next();
            if (value) {
                PreparedStatement stat = conn.prepareStatement("update documents set nombre_d_exemplaires=nombre_d_exemplaires+1 where ISBN=?");
                stat.setInt(1,liv.getISBN());
                int rows = stat.executeUpdate();
                if (rows > 0) JOptionPane.showMessageDialog(null, "Document ajouté avec succès ! ");
                else JOptionPane.showMessageDialog(null, "Erreur Réessayer plus tard!! :( ");
            }
            else {
                PreparedStatement stat = conn.prepareStatement("insert into documents(ISBN,titre,auteur1,auteur2,editeur,edit_year,nombre_d_exemplaires,type_document,nb_pages,type,tome) values(?,?,?,?,?,?,?,?,?,?,?)");
                stat.setInt(1, liv.getISBN());
                stat.setString(2, liv.getTitre());
                stat.setString(3, liv.getAuteur1());
                stat.setString(4, liv.getAuteur2());
                stat.setString(5, liv.getEditeur());
                stat.setInt(6, liv.getEdit_year());
                stat.setInt(7, liv.getNb_exmp());
                stat.setString(8, "livre");
                stat.setInt(9, liv.getNbPages());
                stat.setString(10, liv.getType());
                stat.setInt(11,liv.getTome());
                int rows = stat.executeUpdate();
                PreparedStatement stat2 = conn.prepareStatement("update documents set nombre_d_exemplaires=1 where ISBN=?");
                stat2.setInt(1,liv.getISBN());
                int rows2 = stat2.executeUpdate();
                if (rows >0 && rows2>0) JOptionPane.showMessageDialog(null, "Document ajouté avec succès ! ");
                else JOptionPane.showMessageDialog(null, "Erreur Réessayer plus tard!! :( ");
            }
        }
        ISBNfield.setText("");
        titrefield.setText("");
        auteur2field.setText("");
        auteur1field.setText("");
        editeurfield.setText("");
        edityearfield.setText("");
        typefield.setText("");
        tomefield.setText("");
        nbpagesfield.setText("");
    }
}



