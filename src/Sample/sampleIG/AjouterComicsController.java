package Sample.sampleIG;

import Sample.classes.Comics;
import Sample.classes.Livre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AjouterComicsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Ajouter comics ss scene");
    }
    @FXML private TextField ISBNfield;
    @FXML private TextField titrefield;
    @FXML private TextField auteur1field;
    @FXML private TextField genrefield;
    @FXML private TextField chaptersnbfield;
    @FXML private TextField edityearfield;
    @FXML private TextField editeurfield;
    @FXML private TextField auteur2field;
    @FXML
    public void AjouterComics(javafx.event.ActionEvent e) throws SQLException {
        if(ISBNfield.getText()=="" || titrefield.getText()=="") {
            JOptionPane.showMessageDialog(null, " Des champs obligatoires sont encore vides! ");
        }
        else{
            Comics com = new Comics(Integer.parseInt(chaptersnbfield.getText()),genrefield.getText(),Integer.parseInt(ISBNfield.getText()),titrefield.getText(),auteur1field.getText(),auteur2field.getText(),editeurfield.getText(),Integer.parseInt(edityearfield.getText()));
            Connection conn = DriverManager.getConnection(" ", " ", " ");
            PreparedStatement stat0 = conn.prepareStatement("select * from documents where ISBN=?");
            stat0.setInt(1, com.getISBN());
            ResultSet result = stat0.executeQuery();
            boolean value = result.next();
            if (value) {
                PreparedStatement stat = conn.prepareStatement("update documents set nombre_d_exemplaires=nombre_d_exemplaires+1 where ISBN=?");
                stat.setInt(1,com.getISBN());
                int rows = stat.executeUpdate();
                if (rows > 0) JOptionPane.showMessageDialog(null, "Document ajouté avec succès ! ");
                else JOptionPane.showMessageDialog(null, "Erreur Réessayer plus tard!! :( ");
            }
            else {
                PreparedStatement stat = conn.prepareStatement("insert into documents(ISBN,titre,auteur1,auteur2,editeur,edit_year,nombre_d_exemplaires,type_document,chapters_nb,genre) values(?,?,?,?,?,?,?,?,?,?)");
                stat.setInt(1, com.getISBN());
                stat.setString(2, com.getTitre());
                stat.setString(3, com.getAuteur1());
                stat.setString(4, com.getAuteur2());
                stat.setString(5, com.getEditeur());
                stat.setInt(6, com.getEdit_year());
                stat.setInt(7, com.getNb_exmp());
                stat.setString(8, "comics");
                stat.setInt(9, com.getChaptersNumber());
                stat.setString(10, com.getGenre());
                int rows = stat.executeUpdate();
                PreparedStatement stat2 = conn.prepareStatement("update documents set nombre_d_exemplaires=1 where ISBN=?");
                stat2.setInt(1,com.getISBN());
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
        chaptersnbfield.setText("");
        genrefield.setText("");
    }
}
