package paycore.robust;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginButtonOnAction(ActionEvent event) throws IOException {
        Boolean result = false;
        MainApp mainWindow = new MainApp();

        if(userNameTextField.getText().isBlank() || passwordTextField.getText().isBlank())
        {
            loginMessageLabel.setText("Username or Pass is null !");
        }else{
            loginMessageLabel.setText("Login Trying !");
            result = DatabaseConnection.checkUser(userNameTextField.getText(),passwordTextField.getText());
            if(result)
            {
                loginMessageLabel.setText("Login Succesfull");
                mainWindow.changeScene("mainWin.fxml");
                DatabaseConnection db = new DatabaseConnection();
                db.connectClose();
            }
        }
    }
}