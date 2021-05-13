package Sample.sampleIG;

import Sample.classes.Dictionnaire;
import Sample.classes.Livre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AjouterDictionnaireController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ajouter dictionnaire ss scene");
    }
    @FXML private TextField ISBNfield;
    @FXML private TextField titrefield;
    @FXML private TextField auteur1field;
    @FXML private TextField languefield;
    @FXML private TextField nbtomesfield;
    @FXML private TextField edityearfield;
    @FXML private TextField editeurfield;
    @FXML private TextField auteur2field;
    @FXML
    public void AjouterDictionnaire(javafx.event.ActionEvent e)throws SQLException {
        if(ISBNfield.getText()=="" || titrefield.getText()=="") {
            JOptionPane.showMessageDialog(null, " Des champs obligatoires sont encore vides! ");
        }
        else{
            Dictionnaire dict = new Dictionnaire(languefield.getText(),Integer.parseInt(nbtomesfield.getText()),Integer.parseInt(ISBNfield.getText()),titrefield.getText(),auteur1field.getText(),auteur2field.getText(),editeurfield.getText(),Integer.parseInt(edityearfield.getText()));
            Connection conn = DriverManager.getConnection(" ", " ", " ");
            PreparedStatement stat0 = conn.prepareStatement("select * from documents where ISBN=?");
            stat0.setInt(1, dict.getISBN());
            ResultSet result = stat0.executeQuery();
            boolean value = result.next();
            if (value) {
                PreparedStatement stat = conn.prepareStatement("update documents set nombre_d_exemplaires=nombre_d_exemplaires+1 where ISBN=?");
                stat.setInt(1,dict.getISBN());
                int rows = stat.executeUpdate();
                if (rows > 0) JOptionPane.showMessageDialog(null, "Document ajouté avec succès ! ");
                else JOptionPane.showMessageDialog(null, "Erreur Réessayer plus tard!! :( ");
            }
            else {
                PreparedStatement stat = conn.prepareStatement("insert into documents(ISBN,titre,auteur1,auteur2,editeur,edit_year,nombre_d_exemplaires,type_document,langue,nb_tomes) values(?,?,?,?,?,?,?,?,?,?)");
                stat.setInt(1, dict.getISBN());
                stat.setString(2, dict.getTitre());
                stat.setString(3, dict.getAuteur1());
                stat.setString(4, dict.getAuteur2());
                stat.setString(5, dict.getEditeur());
                stat.setInt(6, dict.getEdit_year());
                stat.setInt(7, dict.getNb_exmp());
                stat.setString(8, "dictionnaire");
                stat.setString(9, dict.getLangue());
                stat.setInt(10,dict.getNbTomes());
                int rows = stat.executeUpdate();
                PreparedStatement stat2 = conn.prepareStatement("update documents set nombre_d_exemplaires=1 where ISBN=?");
                stat2.setInt(1,dict.getISBN());
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
        languefield.setText("");
        nbtomesfield.setText("");
    }


}
