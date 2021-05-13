package Sample.sampleIG;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("welcome scene");
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
