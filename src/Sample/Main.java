 package Sample;

 import javafx.application.Application;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.stage.Stage;

 public class Main extends Application {

     public static void main(String[] args) {
         launch(args);
     }

     @Override
     public void start(Stage stage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("sampleIG/LOGINandSIGNUP.fxml"));
         stage.setTitle("MYBibliotheque");
         Scene scene = new Scene(root, 907,561);
         stage.setScene(scene);
         scene.getStylesheets().add("Stylesheets/style.css");
         stage.show();
     }
 }
