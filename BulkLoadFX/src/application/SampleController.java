package application;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SampleController implements Initializable {
	

    @FXML
    private TextField srcServerHostName;

    @FXML
    private TextField srcServerCertName;

    @FXML
    private TextField srcServerUsername;

    @FXML
    private TextField srcServerPassword;

    @FXML
    private Button srcTestConnectionBtn;
    
    @FXML
    private ToggleGroup srcVersion;

    @FXML
    private ToggleGroup targetVersion;

    @FXML
    private TextField dbHostField;
    
    @FXML
    private TextField dbServiceName;

    @FXML
    private TextField dbUsername;

    @FXML
    private TextField dbPort;

    @FXML
    private TextField dbPassword;

    @FXML
    private Button dbTestConnection;

    @FXML
    private ToggleGroup outputFileFormat;

    @FXML
    private ToggleGroup extractionType;

    @FXML
    private ToggleGroup preLoadValidationSchema;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		// Event when onClick of Test DB Connection button
		dbTestConnection.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Test Connection button is clicked..!!!");
				boolean connectionResult = false;
				try {
					connectionResult = LoadDBConnection.verifyDBConnection(dbHostField.getText(),dbServiceName.getText(),dbPort.getText(),dbUsername.getText(),dbPassword.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("DB Connection Test");
				alert.setHeaderText("Windchill Migration Utility");
				if(connectionResult) {
					alert.setContentText("DB Connection success !!!");
				} else {
					alert.setContentText("DB Connection Failed.!! Check the input.");
				}
				alert.show();
			}
		});
		
		// Event when onClick of source windchill connection button
		srcTestConnectionBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				boolean wc_connectionResult = false;
				System.out.println("Test Connection button on source windchill test clicked");
				try {
					wc_connectionResult = LoadWindchillConnection.verifyWindchillConnection(srcServerHostName.getText(), srcServerUsername.getText(), srcServerPassword.getText());
				} catch (MalformedURLException | RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Windchill Connection Test");
				alert.setHeaderText("Windchill Migration Utility");
				if(wc_connectionResult) {
					alert.setContentText("Source Windchill Connection success !!!");
				} else {
					alert.setContentText("Source Windchill Connection Failed.!! Check the input.");
				}
				alert.show();
			}
		});
	}
	
}
