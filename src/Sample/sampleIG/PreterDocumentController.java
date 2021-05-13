package Sample.sampleIG;

import Sample.classes.Adherent;
import Sample.classes.Emprunt;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class PreterDocumentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("preter document ");
    }
    @FXML private TextField ISBNfield;
    @FXML private TextField titrefield;
    @FXML private TextField nomfield;
    @FXML private TextField CINfield;
    @FXML private TextField prenomfield;
    @FXML private DatePicker datefield;
    @FXML private TableView emprunttable;
    @FXML private TableColumn CINcell;
    @FXML private TableColumn nomcell;
    @FXML private TableColumn prenomcell;
    @FXML private TableColumn ISBNcell;
    @FXML private TableColumn titrecell;
    @FXML private TableColumn datecell;

    @FXML
    void afficherEmprunteurs(javafx.event.ActionEvent e) throws SQLException {
        emprunttable.getItems().clear();
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat = conn.prepareStatement("select * from emprunteurs");
        ResultSet result = stat.executeQuery();
        boolean value = result.next();
        while(value) {
            Emprunt emp = new Emprunt(result.getString("CIN"), result.getString("nom"), result.getString("prenom"), result.getInt("ISBN"), result.getString("titre"),result.getString("date_d_emprunt"));
            CINcell.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("CIN"));
            nomcell.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("nom"));
            prenomcell.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("prenom"));
            ISBNcell.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("ISBN"));
            titrecell.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("titre"));
            datecell.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("date"));
            emprunttable.getItems().add(emp);
            value = result.next();
        }
    }

    @FXML
    void emprunter(javafx.event.ActionEvent e) throws SQLException {
        boolean CINexists=false;
        boolean ISBNexists=false;
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        if(CINfield.getText().equals("") || ISBNfield.getText().equals("")) JOptionPane.showMessageDialog(null, "Des champs obligatoires sont encore vides!");
        PreparedStatement stat0 = conn.prepareStatement("select CIN from adherent");
        ResultSet result0 = stat0.executeQuery();
        boolean value=result0.next();
        while(value){
            if(result0.getString("CIN").equals(CINfield.getText())) {
                CINexists=true;
                break;
            }
            value= result0.next();
        }
        PreparedStatement stat00 = conn.prepareStatement("select ISBN from documents where nombre_d_exemplaires>=?");
        stat00.setInt(1,1);
        ResultSet result00 = stat00.executeQuery();
        boolean value2=result00.next();
        while(value2){
            if(result00.getString("ISBN").equals(ISBNfield.getText())) {
                ISBNexists=true;
                break;
            }
            value2= result00.next();
        }
        if(!CINexists || !ISBNexists)  JOptionPane.showMessageDialog(null, "vérifier l'ISBN et le CIN Que vous avez tapé");
        else {
            Emprunt emp = new Emprunt(CINfield.getText(),nomfield.getText(),prenomfield.getText(),Integer.parseInt(ISBNfield.getText()),titrefield.getText(),datefield.getAccessibleText());
            PreparedStatement stat = conn.prepareStatement("insert into emprunteurs(CIN,nom,prenom,ISBN,titre,date_d_emprunt) values(?,?,?,?,?,?)");
            stat.setString(1, emp.getCIN());
            stat.setString(2, emp.getNom());
            stat.setString(3, emp.getPrenom());
            stat.setInt(4, emp.getISBN());
            stat.setString(5, emp.getTitre());
            stat.setString(6,emp.getDate());
            int rows = stat.executeUpdate();
            if (rows > 0)  {
                JOptionPane.showMessageDialog(null, "Emprunté !");
                CINfield.setText("");
                nomfield.setText("");
                prenomfield.setText("");
                ISBNfield.setText("");
                titrefield.setText("");
                datefield.getEditor().clear();
            } else JOptionPane.showMessageDialog(null, "Erreur, réessayez plus tard!! ");
        }
    }


    @FXML
    protected void switchToChercherAdherent(javafx.event.ActionEvent e) throws IOException {
        Parent ChercherAdherent = FXMLLoader.load(getClass().getResource("ChercherAdherent.fxml"));
        Scene chercherAdherentScene = new Scene(ChercherAdherent, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(chercherAdherentScene);
        stage.show();
    }
    @FXML
    public void switchToChercherDocument(javafx.event.ActionEvent e)throws IOException {
        Parent ChercherDocument = FXMLLoader.load(getClass().getResource("ChercherDocument.fxml"));
        Scene chercherDocumentScene = new Scene(ChercherDocument, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(chercherDocumentScene);
        stage.show();
    }
    @FXML
    public void switchToAjouterAdherent(javafx.event.ActionEvent e)throws IOException {
        Parent AjouterAdherent = FXMLLoader.load(getClass().getResource("AjouterAdherent.fxml"));
        Scene AjouterAdherentScene = new Scene(AjouterAdherent, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(AjouterAdherentScene);
        stage.show();
    }
    @FXML
    public void switchToSupprimerDocument(javafx.event.ActionEvent e)throws IOException {
        Parent SupprimerDocument = FXMLLoader.load(getClass().getResource("SupprimerDocument.fxml"));
        Scene SupprimerDocumentScene = new Scene(SupprimerDocument, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(SupprimerDocumentScene);
        stage.show();
    }
    @FXML
    public void switchToAjouterDocument(javafx.event.ActionEvent e)throws IOException {
        Parent AjouterDocument = FXMLLoader.load(getClass().getResource("AjouterDocument.fxml"));
        Scene AjouterDocumentScene = new Scene(AjouterDocument, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(AjouterDocumentScene);
        stage.show();
    }
    @FXML
    public void Deconnecter(javafx.event.ActionEvent e) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("LOGINandSIGNUP.fxml"));
        Scene loginScene = new Scene(login, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(loginScene);
        stage.show();
    }
}
