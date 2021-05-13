package Sample.sampleIG;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LOGINandSIGNUPController implements Initializable {
    @FXML private TextField nomConn;
    @FXML private TextField mdpConn;
    @FXML private TextField nomIns;
    @FXML private TextField mdpIns;
    @FXML private TextField mdpconfirmIns;

    @FXML
    void Connecter(javafx.event.ActionEvent e) throws SQLException, IOException {
        boolean exist=false;
        if(nomConn.getText().equals("") || mdpConn.getText().equals("")) JOptionPane.showMessageDialog(null, "Prière de remplir les deux champs!");
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat0 = conn.prepareStatement("select nom from authentification");
        ResultSet result0 = stat0.executeQuery();
        boolean value = result0.next();
        while (value) {
            if(result0.getString("nom").equals(nomConn.getText())) {
                exist=true;
                break;
            }
            value=result0.next();
        }
        if(!exist) {
            JOptionPane.showMessageDialog(null, "Il semble que vous n'etes pas inscrits encore!");
            nomConn.setText("");
            mdpConn.setText("");
        }
        else{
            PreparedStatement stat = conn.prepareStatement("select mdp from authentification where nom=?");
            stat.setString(1, nomConn.getText());
            ResultSet result = stat.executeQuery();
            while(result.next()) {
                if(!result.getString("mdp").equals(mdpConn.getText())) {
                    JOptionPane.showMessageDialog(null, "Mot de passe incorrect");
                    mdpConn.setText("");
                }
                else {
                    Parent welcome = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
                    Scene welcomeScene = new Scene(welcome, 907,561);
                    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    stage.setScene(welcomeScene);
                    stage.show();
                }
                result.next();
            }
        }
    }

    @FXML
    void inscrire(javafx.event.ActionEvent e) throws SQLException, IOException {
        boolean exist=false;
        if(nomIns.getText().equals("") || mdpIns.getText().equals("") || mdpconfirmIns.getText().equals("")) JOptionPane.showMessageDialog(null, "Prière de remplir les trois champs!");
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat0 = conn.prepareStatement("select nom from authentification");
        ResultSet result0 = stat0.executeQuery();
        boolean value = result0.next();
        while (value) {
            if(result0.getString("nom").equals(nomIns.getText())) {
                exist=true;
                break;
            }
            value=result0.next();
        }
        if(exist) {
            JOptionPane.showMessageDialog(null, "Ce nom existe déja!");
            nomIns.setText("");
            mdpconfirmIns.setText("");
            mdpIns.setText("");
        }
        else{
            if (!mdpIns.getText().equals(mdpconfirmIns.getText())) {
                JOptionPane.showMessageDialog(null, "Confirmez votre mot de passe correctement!");
                mdpconfirmIns.setText("");
            }
            else{
                PreparedStatement stat= conn.prepareStatement("insert into authentification values(?,?)");
                stat.setString(1,nomIns.getText());
                stat.setString(2,mdpIns.getText());
                int rows = stat.executeUpdate();
                if(rows>0) {
                    Parent welcome = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
                    Scene welcomeScene = new Scene(welcome, 907,561);
                    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    stage.setScene(welcomeScene);
                    stage.show();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Réessayer plus tard!");
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("login scene");
    }
}
