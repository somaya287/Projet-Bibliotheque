package Sample.sampleIG;
import Sample.classes.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ChercherDocumentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Chercher document scene");
    }

    @FXML private MenuButton menubtn;
    @FXML private Label catchInput;
    @FXML private TextField inputDocument;
    @FXML private TableColumn ISBNcell;
    @FXML private TableColumn titrecell;
    @FXML private TableColumn auteur1cell;
    @FXML private TableColumn auteur2cell;
    @FXML private TableColumn editeurcell;
    @FXML private TableColumn anneecell;
    @FXML private TableColumn nbexempcell;
    @FXML private TableView doctable;
    @FXML
    public void ISBNselected(javafx.event.ActionEvent e) {
        catchInput.setText("ISBN");
        menubtn.setText("Par ISBN");
    }
    @FXML
    public void titreselected(javafx.event.ActionEvent e) {
        catchInput.setText("titre");
        menubtn.setText("Par Titre");
    }
    @FXML
    public void editeurselected(javafx.event.ActionEvent e) {
        catchInput.setText("editeur");
        menubtn.setText("Par éditeur");
    }
    @FXML
    public void auteurselected(javafx.event.ActionEvent e) {
        catchInput.setText("auteur");
        menubtn.setText("Par auteur");
    }

    @FXML
    public void getDocument(javafx.event.ActionEvent e) throws IOException, SQLException {
        switch (catchInput.getText()){
            case "ISBN": this.getDocumentByISBN(e);
                         break;
            case "titre": this.getDocumentByTitre(e);
                          break;
            case "editeur": this.getDocumentByEditeur(e);
                            break;
            case "auteur": this.getDocumentByAuteur(e);
                           break;
        }
    }

    @FXML
    protected void getDocumentByISBN(javafx.event.ActionEvent e)throws IOException,SQLException {
        if(ISBNcell.getCellValueFactory()!=null) doctable.getItems().clear();
        String ISBN= inputDocument.getText();
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat = conn.prepareStatement("select * from documents where ISBN=?");
        stat.setString(1, ISBN);
        ResultSet result = stat.executeQuery();
        boolean value = result.next();
        while (value) {
            Document doc = new Document(result.getInt("ISBN"),result.getString("titre"), result.getString("auteur1"),result.getString("auteur2"), result.getString("editeur"),result.getInt("edit_year"));
            ISBNcell.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titrecell.setCellValueFactory(new PropertyValueFactory<>("titre"));
            auteur1cell.setCellValueFactory(new PropertyValueFactory<>("auteur1"));
            auteur2cell.setCellValueFactory(new PropertyValueFactory<>("auteur2"));
            editeurcell.setCellValueFactory(new PropertyValueFactory<>("editeur"));
            anneecell.setCellValueFactory(new PropertyValueFactory<>("edit_year"));
            nbexempcell.setCellValueFactory(new PropertyValueFactory<>("nb_exmp"));
            doctable.getItems().add(doc);
            value = result.next();
        }
        inputDocument.setText("");
    }
    @FXML
    protected void getDocumentByTitre(javafx.event.ActionEvent e)throws IOException,SQLException {
        if(titrecell.getCellValueFactory()!=null) doctable.getItems().clear();
        String titre= inputDocument.getText();
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat = conn.prepareStatement("select * from documents where titre=?");
        stat.setString(1, titre);
        ResultSet result = stat.executeQuery();
        boolean value = result.next();
        while (value) {
            Document doc = new Document(result.getInt("ISBN"),result.getString("titre"), result.getString("auteur1"),result.getString("auteur2"), result.getString("editeur"),result.getInt("edit_year"));
            doc.setNb_exmp(result.getInt("nombre_d_exemplaires"));
            ISBNcell.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titrecell.setCellValueFactory(new PropertyValueFactory<>("titre"));
            auteur1cell.setCellValueFactory(new PropertyValueFactory<>("auteur1"));
            auteur2cell.setCellValueFactory(new PropertyValueFactory<>("auteur2"));
            editeurcell.setCellValueFactory(new PropertyValueFactory<>("editeur"));
            anneecell.setCellValueFactory(new PropertyValueFactory<>("edit_year"));
            nbexempcell.setCellValueFactory(new PropertyValueFactory<>("nb_exmp"));
            doctable.getItems().add(doc);
            value = result.next();
        }
        inputDocument.setText("");
    }

    public void getDocumentByEditeur(javafx.event.ActionEvent e) throws IOException,SQLException{
        if(ISBNcell.getCellValueFactory()!=null) doctable.getItems().clear();
        String editeur= inputDocument.getText();
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat = conn.prepareStatement("select * from documents where editeur=?");
        stat.setString(1, editeur);
        ResultSet result = stat.executeQuery();
        boolean value = result.next();
        while (value) {
            Document doc = new Document(result.getInt("ISBN"),result.getString("titre"), result.getString("auteur1"),result.getString("auteur2"), result.getString("editeur"),result.getInt("edit_year"));
            ISBNcell.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titrecell.setCellValueFactory(new PropertyValueFactory<>("titre"));
            auteur1cell.setCellValueFactory(new PropertyValueFactory<>("auteur1"));
            auteur2cell.setCellValueFactory(new PropertyValueFactory<>("auteur2"));
            editeurcell.setCellValueFactory(new PropertyValueFactory<>("editeur"));
            anneecell.setCellValueFactory(new PropertyValueFactory<>("edit_year"));
            nbexempcell.setCellValueFactory(new PropertyValueFactory<>("nb_exmp"));
            doctable.getItems().add(doc);
            value = result.next();
        }
        inputDocument.setText("");
    }

    public void getDocumentByAuteur(javafx.event.ActionEvent e) throws IOException,SQLException {
        if(ISBNcell.getCellValueFactory()!=null) doctable.getItems().clear();
        String auteur= inputDocument.getText();
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat = conn.prepareStatement("select * from documents where auteur1=? or auteur2=?");
        stat.setString(1, auteur);
        stat.setString(2, auteur);
        ResultSet result = stat.executeQuery();
        boolean value = result.next();
        while (value) {
            Document doc = new Document(result.getInt("ISBN"),result.getString("titre"), result.getString("auteur1"),result.getString("auteur2"), result.getString("editeur"),result.getInt("edit_year"));
            ISBNcell.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titrecell.setCellValueFactory(new PropertyValueFactory<>("titre"));
            auteur1cell.setCellValueFactory(new PropertyValueFactory<>("auteur1"));
            auteur2cell.setCellValueFactory(new PropertyValueFactory<>("auteur2"));
            editeurcell.setCellValueFactory(new PropertyValueFactory<>("editeur"));
            anneecell.setCellValueFactory(new PropertyValueFactory<>("edit_year"));
            nbexempcell.setCellValueFactory(new PropertyValueFactory<>("nb_exmp"));
            doctable.getItems().add(doc);
            value = result.next();
        }
        inputDocument.setText("");
    }

    @FXML
    protected void switchToChercherAdherent(javafx.event.ActionEvent e) throws IOException {
        Parent ChercherAdherent = FXMLLoader.load(getClass().getResource("ChercherAdherent.fxml"));
        Scene chercherAdherentScene = new Scene(ChercherAdherent, 907,561);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(chercherAdherentScene);
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
