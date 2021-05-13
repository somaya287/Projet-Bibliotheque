package Sample.sampleIG;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class SupprimerDocumentController implements Initializable {
    @FXML private TextField ISBNfield;
    @FXML private Label deleted2;
    @FXML private Label deleted3;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Supprimer document scene");
    }
    @FXML
    public void clearTheScene(MouseEvent mouseEvent) {
        deleted2.setVisible(false);
        deleted3.setVisible(false);
    }
    @FXML
    protected void SupprimerDocument(javafx.event.ActionEvent e) throws IOException, SQLException {
        String ISBN= ISBNfield.getText();
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat0 = conn.prepareStatement("select * from documents where ISBN = ? ");
        stat0.setString(1,ISBN);
        ResultSet result = stat0.executeQuery();
        boolean value = result.next();
        if(!value){
            JOptionPane.showMessageDialog(null, "Le document n'esxiste pas!! :) ");
            ISBNfield.setText("");
        }
        else {
            if (Integer.parseInt(result.getString("nombre_d_exemplaires")) == 1) {
                PreparedStatement stat1 = conn.prepareStatement("delete from documents where ISBN=?");
                stat1.setString(1, ISBN);
                int rows = stat1.executeUpdate();
                if (rows > 0) {
                    deleted2.setVisible(true);
                    deleted3.setVisible(true);
                    ISBNfield.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Erreur Réessayer plus tard!! :( ");
                    ISBNfield.setText("");
                }
            } else {
                PreparedStatement stat2 = conn.prepareStatement("update documents set nombre_d_exemplaires=nombre_d_exemplaires-1 where ISBN=?");
                stat2.setString(1, ISBN);
                int rows = stat2.executeUpdate();
                if (rows > 0) {
                    deleted2.setVisible(true);
                    deleted3.setVisible(true);
                    ISBNfield.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Erreur Réessayer plus tard!! :( ");
                    ISBNfield.setText("");
                }
            }
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

    public void switchToChercherDocument(javafx.event.ActionEvent e)throws IOException {
        Parent ChercherDocument = FXMLLoader.load(getClass().getResource("ChercherDocument.fxml"));
        Scene chercherDocumentScene = new Scene(ChercherDocument, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(chercherDocumentScene);
        stage.show();
    }
    public void switchToAjouterAdherent(javafx.event.ActionEvent e)throws IOException {
        Parent AjouterAdherent = FXMLLoader.load(getClass().getResource("AjouterAdherent.fxml"));
        Scene AjouterAdherentScene = new Scene(AjouterAdherent, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(AjouterAdherentScene);
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
