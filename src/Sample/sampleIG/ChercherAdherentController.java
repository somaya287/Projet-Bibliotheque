package Sample.sampleIG;
import Sample.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ChercherAdherentController implements Initializable {


    // methods
    /*



    public void getLivreByType(String Type) throws IOException,SQLException {
        // Apres avoir mettre a jour la base de donnees des documents
    }



    public boolean SupprimerEtudiant(String CNE) throws IOException, SQLException {
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat = conn.prepareStatement("delete from etudiant where CNE=?");
        stat.setString(1, CNE);
        int rows = stat.executeUpdate();
        if (rows > 0) return true;
        else return false;
    }

    public boolean SupprimerProfesseur(String CIN) throws IOException, SQLException {
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat = conn.prepareStatement("delete from professeur where CIN=?");
        stat.setString(1, CIN);
        int rows = stat.executeUpdate();
        if (rows > 0) return true;
        else return false;
    }

    public boolean AjouterDocument(Document doc) throws IOException, SQLException {
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat0 = conn.prepareStatement("select * from documents where ISBN=?");
        stat0.setInt(1, doc.getISBN());
        ResultSet result = stat0.executeQuery();
        boolean value = result.next();
        if (value == true) {
            doc.incrémenter();
            PreparedStatement stat = conn.prepareStatement("update documents set nombre_d_exemplaires=nombre_d_exemplaires+1 where ISBN=?");
            stat.setInt(1,doc.getISBN());
            int rows = stat.executeUpdate();
            if (rows > 0) return true;
            else return false;
        }
        else {
            PreparedStatement stat = conn.prepareStatement("insert into documents(ISBN,titre,auteur1,auteur2,auteur3,auteur4,auteur5,editeur,edit_year,nombre_d_exemplaires) values(?,?,?,?,?,?,?,?,?,?)");
            stat.setInt(1, doc.getISBN());
            stat.setString(2, doc.gettitre());
            stat.setString(3, doc.getauteurs()[0]);
            stat.setString(4, doc.getauteurs()[1]);
            stat.setString(5, doc.getauteurs()[2]);
            stat.setString(6, doc.getauteurs()[3]);
            stat.setString(7, doc.getauteurs()[4]);
            stat.setString(8, doc.getediteur());
            stat.setInt(9, doc.getedit_year());
            stat.setInt(10, doc.getnb_exmp());
            int rows = stat.executeUpdate();
            if (rows > 0) return true;
            else return false;
        }
    }


    public void TrierAnneeASC() throws IOException,SQLException{
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat = conn.prepareStatement("select ISBN,titre from documents order by edit_year asc");
        ResultSet result = stat.executeQuery();
        while (result.next())
            System.out.println(result.getInt("ISBN") + " , " + result.getString("titre"));
    }
    public void TrierAnneeDESC() throws IOException,SQLException{
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat = conn.prepareStatement("select ISBN,titre from documents order by edit_year desc");
        ResultSet result = stat.executeQuery();
        while (result.next())
            System.out.println(result.getInt("ISBN") + " , " + result.getString("titre"));
    }*/




    // des fct utiles pour l'IG
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Chercher Adherent Scene");
    }
    @FXML private TextField inputCINprof;
    @FXML private TableColumn CINcell;
    @FXML private TableColumn prenomcell;
    @FXML private TableColumn nomcell;
    @FXML private TableColumn agecell;
    @FXML private TableColumn villecell;
    @FXML private TableView proftable;
    @FXML
    protected void getProfesseurByCIN(javafx.event.ActionEvent e) throws IOException,SQLException{
        if(CINcell.getCellValueFactory()!=null) proftable.getItems().clear();
        String CIN=inputCINprof.getText();
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat = conn.prepareStatement("select * from professeur where CIN=?");
        stat.setString(1, CIN);
        ResultSet result = stat.executeQuery();
        boolean value = result.next();
        while(value) {
            Professeur prof = new Professeur(result.getString("CIN"), result.getString("prenom"), result.getString("nom"), result.getInt("age"), result.getString("ville"));
            CINcell.setCellValueFactory(new PropertyValueFactory<Professeur,String>("CIN"));
            prenomcell.setCellValueFactory(new PropertyValueFactory<Professeur,String>("prenom"));
            nomcell.setCellValueFactory(new PropertyValueFactory<Professeur,String>("nom"));
            agecell.setCellValueFactory(new PropertyValueFactory<Professeur,String>("age"));
            villecell.setCellValueFactory(new PropertyValueFactory<Professeur,String>("ville"));
            proftable.getItems().add(prof);
            value = result.next();
        }
        inputCINprof.setText("");
    }

    @FXML private TextField inputCNEetud;
    @FXML private TableColumn CNEcell;
    @FXML private TableColumn prenomcell2;
    @FXML private TableColumn nomcell2;
    @FXML private TableColumn agecell2;
    @FXML private TableColumn villecell2;
    @FXML private TableView etudtable;
    @FXML
    protected void getEtudiantByCNE(javafx.event.ActionEvent e) throws IOException, SQLException {
        if(CNEcell.getCellValueFactory()!=null) etudtable.getItems().clear();
        String CNE=inputCNEetud.getText();
        Connection conn = DriverManager.getConnection(" ", " ", " ");
        PreparedStatement stat = conn.prepareStatement("select * from etudiant where CNE=?");
        stat.setString(1, CNE);
        ResultSet result = stat.executeQuery();
        boolean value = result.next();
        while (value) {
            Etudiant etud = new Etudiant(result.getString("CNE"),"_", result.getString("prenom"), result.getString("nom"), result.getInt("age"),result.getString("ville"));
            CNEcell.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("CNE"));
            prenomcell2.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("prenom"));
            nomcell2.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("nom"));
            agecell2.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("age"));
            villecell2.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("ville"));
            etudtable.getItems().add(etud);
            value = result.next();
        }
        inputCNEetud.setText("");
    }
    @FXML
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