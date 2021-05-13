package Sample.sampleIG;

import Sample.classes.Adherent;
import Sample.classes.Etudiant;
import Sample.classes.Professeur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AjouterAdherentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ajouter adherent scene");
    }

    @FXML private Label catchChoice;
    @FXML private TextField CINfield;
    @FXML private TextField prenomfield;
    @FXML private TextField nomfield;
    @FXML private TextField agefield;
    @FXML private TextField villefield;
    @FXML private TextField CNEfield;
    // what if  he choose etud et prof au meme temps ??
    @FXML public void etudiantSelected(javafx.event.ActionEvent e){
        catchChoice.setText("etudiant");
        CNEfield.setEditable(true);
    }
    @FXML public void professeurSelected(javafx.event.ActionEvent e){
        catchChoice.setText("professeur");
        CNEfield.setEditable(false);
    }
    @FXML
    protected void AddAdherent(javafx.event.ActionEvent e) throws IOException, SQLException{
        switch (catchChoice.getText()){
            case "etudiant": this.ajouterEtudiant(e);
                             break;
            case "professeur": this.ajouterProfesseur(e);
                                break;
            case "Label": this.ajouterAdherent(e);
        }
    }
    @FXML
    protected void ajouterAdherent(javafx.event.ActionEvent e) throws IOException,SQLException{
        if(CINfield.getText()=="" || prenomfield.getText()=="" || nomfield.getText()=="") JOptionPane.showMessageDialog(null, "Des champs obligatoires sont encore vides!");
        else {
            Adherent adh = new Adherent(CINfield.getText(),prenomfield.getText(),nomfield.getText(),Integer.parseInt(agefield.getText()),villefield.getText());
            Connection conn = DriverManager.getConnection(" ", " ", " ");
            PreparedStatement stat0 = conn.prepareStatement("select * from adherent where CIN=?");
            stat0.setString(1, adh.getCIN());
            ResultSet result = stat0.executeQuery();
            boolean value = result.next();
            if (value == true) {
                JOptionPane.showMessageDialog(null, "Cet adhérent existe déja!! ");
                CINfield.setText("");
                nomfield.setText("");
                prenomfield.setText("");
                agefield.setText("");
                villefield.setText("");
            }
            else {
                PreparedStatement stat = conn.prepareStatement("insert into adherent(CIN,prenom,nom,age,ville) values(?,?,?,?,?)");
                stat.setString(1, adh.getCIN());
                stat.setString(2, adh.getPrenom());
                stat.setString(3, adh.getNom());
                stat.setInt(4, adh.getAge());
                stat.setString(5, adh.getVille());
                int rows = stat.executeUpdate();
                if (rows > 0)  {
                    JOptionPane.showMessageDialog(null, "Adhérent ajouté avec succès!! ");
                    CINfield.setText("");
                    nomfield.setText("");
                    prenomfield.setText("");
                    agefield.setText("");
                    villefield.setText("");
                }
                else JOptionPane.showMessageDialog(null, "Erreur, réessayez plus tard!! ");
            }
        }
    }
    @FXML
    protected void ajouterProfesseur(javafx.event.ActionEvent e) throws IOException,SQLException {
        if (CINfield.getText() == "" || prenomfield.getText() == "" || nomfield.getText() == "")
            JOptionPane.showMessageDialog(null, "Des champs obligatoires sont encore vides!");
        else {
            Professeur prof = new Professeur(CINfield.getText(), prenomfield.getText(), nomfield.getText(), Integer.parseInt(agefield.getText()), villefield.getText());
            Adherent adh = new Adherent(CINfield.getText(), prenomfield.getText(), nomfield.getText(), Integer.parseInt(agefield.getText()), villefield.getText());
            Connection conn = DriverManager.getConnection(" ", " ", " ");
            PreparedStatement stat0 = conn.prepareStatement("select * from professeur where CIN=?");
            stat0.setString(1, prof.getCIN());
            ResultSet result = stat0.executeQuery();
            boolean value = result.next();
            if (value == true) {
                JOptionPane.showMessageDialog(null, "Ce professeur existe déja!! ");
                CINfield.setText("");
                nomfield.setText("");
                prenomfield.setText("");
                agefield.setText("");
                villefield.setText("");
            } else {
                PreparedStatement stat00 = conn.prepareStatement("select * from adherent where CIN=?");
                stat00.setString(1, adh.getCIN());
                ResultSet result2 = stat00.executeQuery();
                boolean value2 = result2.next();
                if (value2 == false) {
                    PreparedStatement stat = conn.prepareStatement("insert into adherent(CIN,prenom,nom,age,ville) values(?,?,?,?,?)");
                    stat.setString(1, adh.getCIN());
                    stat.setString(2, adh.getPrenom());
                    stat.setString(3, adh.getNom());
                    stat.setInt(4, adh.getAge());
                    stat.setString(5, adh.getVille());
                    int rows2 = stat.executeUpdate();
                }
                PreparedStatement stat2 = conn.prepareStatement("insert into professeur(CIN,prenom,nom,age,ville) values(?,?,?,?,?)");
                stat2.setString(1, prof.getCIN());
                stat2.setString(2, prof.getPrenom());
                stat2.setString(3, prof.getNom());
                stat2.setInt(4, prof.getAge());
                stat2.setString(5, prof.getVille());
                int rows = stat2.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(null, "Professeur ajouté avec succès!! ");
                    CINfield.setText("");
                    nomfield.setText("");
                    prenomfield.setText("");
                    agefield.setText("");
                    villefield.setText("");
                }
                else JOptionPane.showMessageDialog(null, "Erreur, réessayez plus tard!! ");
            }
        }
    }
    @FXML
    protected void ajouterEtudiant(javafx.event.ActionEvent e) throws IOException,SQLException {
        if (CINfield.getText() == "" || prenomfield.getText() == "" || nomfield.getText() == "" || CNEfield.getText()=="")
            JOptionPane.showMessageDialog(null, "Des champs obligatoires sont encore vides!");
        else {
            Etudiant etud = new Etudiant(CNEfield.getText(),CINfield.getText(), prenomfield.getText(), nomfield.getText(), Integer.parseInt(agefield.getText()), villefield.getText());
            Adherent adh = new Adherent(CINfield.getText(), prenomfield.getText(), nomfield.getText(), Integer.parseInt(agefield.getText()), villefield.getText());
            Connection conn = DriverManager.getConnection(" ", " ", " ");
            PreparedStatement stat0 = conn.prepareStatement("select * from etudiant where CNE=?");
            stat0.setString(1, etud.getCNE());
            ResultSet result = stat0.executeQuery();
            boolean value = result.next();
            if (value == true) {
                JOptionPane.showMessageDialog(null, "Cet étudiant existe déja!! ");
                CNEfield.setText("");
                CINfield.setText("");
                nomfield.setText("");
                prenomfield.setText("");
                agefield.setText("");
                villefield.setText("");
            } else {
                PreparedStatement stat00 = conn.prepareStatement("select * from adherent where CIN=?");
                stat00.setString(1, adh.getCIN());
                ResultSet result2 = stat00.executeQuery();
                boolean value2 = result2.next();
                if (value2 == false) {
                    PreparedStatement stat = conn.prepareStatement("insert into adherent(CIN,prenom,nom,age,ville) values(?,?,?,?,?)");
                    stat.setString(1, adh.getCIN());
                    stat.setString(2, adh.getPrenom());
                    stat.setString(3, adh.getNom());
                    stat.setInt(4, adh.getAge());
                    stat.setString(5, adh.getVille());
                    int rows2 = stat.executeUpdate();
                }
                PreparedStatement stat2 = conn.prepareStatement("insert into etudiant(CNE,CIN,prenom,nom,age,ville) values(?,?,?,?,?,?)");
                stat2.setString(1,etud.getCNE());
                stat2.setString(2, etud.getCIN());
                stat2.setString(3, etud.getPrenom());
                stat2.setString(4, etud.getNom());
                stat2.setInt(5, etud.getAge());
                stat2.setString(6, etud.getVille());
                int rows = stat2.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(null, "Etudiant ajouté avec succès!! ");
                    CNEfield.setText("");
                    CINfield.setText("");
                    nomfield.setText("");
                    prenomfield.setText("");
                    agefield.setText("");
                    villefield.setText("");
                }
                else JOptionPane.showMessageDialog(null, "Erreur, réessayez plus tard!! ");
            }
        }
    }





    @FXML
    public void switchToChercherAdherent(ActionEvent e) throws IOException {
        Parent AjouterAdherent = FXMLLoader.load(getClass().getResource("ChercherAdherent.fxml"));
        Scene AjouterAdherentScene = new Scene(AjouterAdherent, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(AjouterAdherentScene);
        stage.show();
    }
    @FXML
    public void switchToChercherDocument(ActionEvent e)throws IOException {
        Parent ChercherDocument = FXMLLoader.load(getClass().getResource("ChercherDocument.fxml"));
        Scene chercherDocumentScene = new Scene(ChercherDocument, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(chercherDocumentScene);
        stage.show();
    }
    public void switchToSupprimerDocument(javafx.event.ActionEvent e)throws IOException {
        Parent SupprimerDocument = FXMLLoader.load(getClass().getResource("SupprimerDocument.fxml"));
        Scene SupprimerDocumentScene = new Scene(SupprimerDocument, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(SupprimerDocumentScene);
        stage.show();
    }
    public void switchToAjouterDocument(javafx.event.ActionEvent e)throws IOException {
        Parent AjouterDocument = FXMLLoader.load(getClass().getResource("AjouterDocument.fxml"));
        Scene AjouterDocumentScene = new Scene(AjouterDocument, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(AjouterDocumentScene);
        stage.show();
    }
    public void Deconnecter(javafx.event.ActionEvent e) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("LOGINandSIGNUP.fxml"));
        Scene loginScene = new Scene(login, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(loginScene);
        stage.show();
    }

    public void switchToPreterDocument(javafx.event.ActionEvent e) throws IOException {
        Parent PreterDocument = FXMLLoader.load(getClass().getResource("PreterDocument.fxml"));
        Scene PreterDocumentScene = new Scene(PreterDocument, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(PreterDocumentScene);
        stage.show();
    }
}
