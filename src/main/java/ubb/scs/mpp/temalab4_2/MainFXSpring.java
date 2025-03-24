package ubb.scs.mpp.temalab4_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ubb.scs.mpp.temalab4_2.ctrl.ComputerRepairShopController;
import ubb.scs.mpp.temalab4_2.services.ComputerRepairServices;
import ubb.scs.mpp.temalab4_2.services.ServicesException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainFXSpring extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RepairShopWindow.fxml"));
            Parent root = loader.load();
            ComputerRepairShopController ctrl = loader.getController();
            ctrl.setService(getService());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Computer Repairs Shop");
            primaryStage.show();
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error while starting app "+e);
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    static ComputerRepairServices getService() throws ServicesException {
        // pentru configurare folosind XML
           // ApplicationContext context=new ClassPathXmlApplicationContext("classpath:RepairShopConfig.xml");

            //pentru configurare folosind JavaConfig
            ApplicationContext context=new AnnotationConfigApplicationContext(RepairShopConfig.class);
            ComputerRepairServices services=context.getBean(ComputerRepairServices.class);
            return services;

    }
}
