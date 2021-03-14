package it.polito.tdp.lab00;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class FXMLController 
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label textLabel;
    
    @FXML
    private Label tentativiRimastiLabel;

    @FXML
    private Button clearButton;

    @FXML
    private Button accediButton;

    private final int MAX_ATTEMPTS = 3;
    private int attemptsLeft = MAX_ATTEMPTS;
    
    private final String PASSWORD_CHARS_TYPE_ERROR = 
    		"Errore: la password deve contenere almeno una cifra [0-9], una lettera maiuscola [A-Z] e almeno uno tra i caratteri speciali '?','!','@' e '#' !";
    private final String LOWER_PASSWORD_ERROR = "Errore: La password deve contenere almeno 7 caratteri!";
    private final String EMPTY_FIELD_ERROR = "Devi compilare entrambi i campi";
    private final String CORRECT_ACCESS = "Accesso effettuato correttamente! :)";
    private final String MAX_ATTEMPTS_ACHIEVED = String.format("Accesso negato: %d tentativi falliti. Riprova", MAX_ATTEMPTS);
    
    
    @FXML
    void handleAccedi(ActionEvent event) 
    {
    	if(this.usernameField.getLength() == 0 || this.passwordField.getLength() == 0)
    		showError(EMPTY_FIELD_ERROR);
    	
    	else if(this.passwordField.getLength() < 7)
    	{
    		this.attemptsLeft--;
    		if(this.attemptsLeft > 0)
    			disableUsernameFieldAndShowError(LOWER_PASSWORD_ERROR);
    		else
    			end(MAX_ATTEMPTS_ACHIEVED);
    	}  	  	
    	//se sono tutti non-numeri, tutti non-lettereMaiuscole o tutti diversi da '?','!','@' e '#'... non va bene
    	else if(this.passwordField.getText().matches("[^0-9]+|[^A-Z]+|[^?!@#]+"))	
    	{
    		this.attemptsLeft--;
    		if(this.attemptsLeft > 0)
    			disableUsernameFieldAndShowError(PASSWORD_CHARS_TYPE_ERROR);
    		else
    			end(MAX_ATTEMPTS_ACHIEVED);
    	}
    	else //passati tutti i controlli
    		end(CORRECT_ACCESS);	
    }
    
    private void end(String text)
    {
    	this.textLabel.setText(text);
    	this.textLabel.setTextFill(Color.BLACK);
		this.usernameField.setDisable(true);
		this.passwordField.setDisable(true);
		this.accediButton.setDisable(true);
		this.clearButton.setDisable(false);
		this.attemptsLeft = MAX_ATTEMPTS;
    	this.tentativiRimastiLabel.setTextFill(Color.BLACK);
		this.tentativiRimastiLabel.setVisible(false);
    }
    
    private void showError(String errorMessage)
    {
    	this.textLabel.setText(errorMessage);
		this.textLabel.setTextFill(Color.RED);
		this.tentativiRimastiLabel.setVisible(true);
		this.tentativiRimastiLabel.setText(String.format("Tentativi rimasti: %d", this.attemptsLeft));
    }
    
    private void disableUsernameFieldAndShowError(String errorMessage)
    {
    	this.usernameField.setDisable(true);
    	this.showError(errorMessage);
    }

    @FXML
    void handleClear(ActionEvent event) 
    {
    	this.usernameField.setDisable(false);
		this.passwordField.setDisable(false);
		this.accediButton.setDisable(false);
		this.clearButton.setDisable(true);
		this.textLabel.setText("Inserire username e password");
		this.usernameField.clear();
		this.passwordField.clear();
    }

    @FXML
    void initialize() 
    {
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'Scene_lab00.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'Scene_lab00.fxml'.";
        assert textLabel != null : "fx:id=\"textLabel\" was not injected: check your FXML file 'Scene_lab00.fxml'.";
        assert tentativiRimastiLabel != null : "fx:id=\"tentativiRimastiLabel\" was not injected: check your FXML file 'Scene_lab00.fxml'.";
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'Scene_lab00.fxml'.";
        assert accediButton != null : "fx:id=\"accediButton\" was not injected: check your FXML file 'Scene_lab00.fxml'.";
    }
}
