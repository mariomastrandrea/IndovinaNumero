package it.polito.tdp.indovinaNumero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application 
{
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene_indovinaNumero.fxml"));
    	
    	/* instead, to export project...
        FXMLLoader loader = new FXMLLoader();
    	Parent root = loader.load(new FileInputStream(String.format("%s/Scene_indovinaNumero.fxml", new File("").getAbsolutePath())));
    	*/
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Indovina il numero! - Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();     
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }

}

