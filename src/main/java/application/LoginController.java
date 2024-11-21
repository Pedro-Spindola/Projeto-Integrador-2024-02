/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pedro Spindola
 */
public class LoginController implements Initializable {

    @FXML
    private Button buttonLogin;
    @FXML
    private Button buttonFechar;
    @FXML
    private TextField  usernameField;
    @FXML
    private TextField  passwordField;
    @FXML
    private Label loginMessage;
    @FXML
    private void loginButtonClicked(ActionEvent event){
        try{
            String usuarioInput = usernameField.getText();
            String senhaInput = passwordField.getText();
            boolean autenticado = Conexao.validarLogin(usuarioInput, senhaInput);
            if (autenticado){
                loginMessage.setText("Login autenticado, entrando...");
                Stage stage = (Stage) loginMessage.getScene().getWindow();
                App.setRoot("home", stage);
            }
            else loginMessage.setText("Usuário ou Senha inválidos.");
        } catch(Exception erro){
            System.out.println(erro.getMessage());
        }
        /*
        @FXML
        private void fecharJanelaButtonClicked(ActionEvent event){

            try{
                Stage stage = (Stage) buttonFechar.getScene().getWindow();
                stage.close();
            } catch(Exception erro){
                System.out.println(erro.getMessage());
            }
        }*/
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    } 
}
