package Sample.sampleIG;

import Sample.classes.Livre;
import Sample.classes.Magazine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AjouterMagazineController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ajouter magazine ss scene");
    }
    @FXML private TextField ISBNfield;
    @FXML private TextField titrefield;
    @FXML private TextField auteur1field;
    @FXML private TextField periodefield;
    @FXML private TextField moiseditfield;
    @FXML private TextField joureditfield;
    @FXML private TextField edityearfield;
    @FXML private TextField editeurfield;
    @FXML private TextField auteur2field;
    public void AjouterMagazine(javafx.event.ActionEvent e) throws IOException, SQLException {
        if(ISBNfield.getText()=="" || titrefield.getText()=="") {
            JOptionPane.showMessageDialog(null, " Des champs obligatoires sont encore vides! ");
        }
        else{
            Magazine mag = new Magazine(Double.parseDouble(periodefield.getText()),Integer.parseInt(moiseditfield.getText()),Integer.parseInt(joureditfield.getText()),Integer.parseInt(ISBNfield.getText()),titrefield.getText(),auteur1field.getText(),auteur2field.getText(),editeurfield.getText(),Integer.parseInt(edityearfield.getText()));
            Connection conn = DriverManager.getConnection(" ", " ", " ");
            PreparedStatement stat0 = conn.prepareStatement("select * from documents where ISBN=?");
            stat0.setInt(1, mag.getISBN());
            ResultSet result = stat0.executeQuery();
            boolean value = result.next();
            if (value) {
                PreparedStatement stat = conn.prepareStatement("update documents set nombre_d_exemplaires=nombre_d_exemplaires+1 where ISBN=?");
                stat.setInt(1,mag.getISBN());
                int rows = stat.executeUpdate();
                if (rows > 0) JOptionPane.showMessageDialog(null, "Document ajouté avec succès ! ");
                else JOptionPane.showMessageDialog(null, "Erreur Réessayer plus tard!! :( ");
            }
            else {
                PreparedStatement stat = conn.prepareStatement("insert into documents(ISBN,titre,auteur1,auteur2,editeur,edit_year,nombre_d_exemplaires,type_document,periode,mois_edit,jour_edit) values(?,?,?,?,?,?,?,?,?,?,?)");
                stat.setInt(1, mag.getISBN());
                stat.setString(2, mag.getTitre());
                stat.setString(3, mag.getAuteur1());
                stat.setString(4, mag.getAuteur2());
                stat.setString(5, mag.getEditeur());
                stat.setInt(6, mag.getEdit_year());
                stat.setInt(7, mag.getNb_exmp());
                stat.setString(8, "magazine");
                stat.setDouble(9, mag.getPeriod());
                stat.setInt(10, mag.getMoisEdit());
                stat.setInt(11,mag.getJourEdit());
                int rows = stat.executeUpdate();
                PreparedStatement stat2 = conn.prepareStatement("update documents set nombre_d_exemplaires=1 where ISBN=?");
                stat2.setInt(1,mag.getISBN());
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
        periodefield.setText("");
        moiseditfield.setText("");
        joureditfield.setText("");
    }
}
