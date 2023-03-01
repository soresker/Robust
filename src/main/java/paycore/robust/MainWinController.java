package paycore.robust;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWinController {

    private final static String url = "jdbc:postgresql://LSR_IST4PSQLOCN.cardtek.local:5432/OCEANDEV";
    private final static String user = "ocn_app";
    private final static String password = "ocn_app";
    static DatabaseConnection connDb = null;

    @FXML
    private Button tcpButton;
    @FXML
    private Button connectDbButton;

    @FXML
    private Button connectGateButton;

    @FXML
    private Button connectTcpButton;

    @FXML
    private Button disconnectDbButton;

    @FXML
    private Button disconnectGateButton;

    @FXML
    private Button disconnectTcpButton;

    @FXML
    private Button exitMainWinButton;

    @FXML
    private Button minimizeMainWinButton;
    @FXML
    private Button posButton;

    @FXML
    private Button homeButton;
    @FXML
    private TextField textFiledGateStatus;

    @FXML
    private TextField textFiledStatus;

    @FXML
    private TextField textFiledTcpStatus;

    @FXML
    private TextField labelDbUrl;

    @FXML
    private TextField labelDbUser;

    @FXML
    private PasswordField labelDbPass;

    @FXML
    private TextField labelTcpHost;

    @FXML
    private TextField labelTcpPort;
    @FXML
    private AnchorPane posForm;

    @FXML
    private AnchorPane homeForm;
    @FXML
    private AnchorPane tcpForm;

    @FXML
    private TextArea textFiledPosReceiveMessages;

    @FXML
    private TextArea textFiledPosSendMessages;

    @FXML
    void connectDbButtonOnAction(ActionEvent event) {

        if(labelDbUrl.getText().isBlank() || labelDbUser.getText().isBlank() || labelDbPass.getText().isBlank())
        {
            textFiledStatus.setText("Host or User or Pass Null !!");
        }else {
            connDb = new DatabaseConnection();
            connDb.connect(url,user,password);
            textFiledStatus.setText("Db Connectted !!");
        }
    }

    @FXML
    void connectGateButtonOnAction(ActionEvent event) {

    }

    @FXML
    void connectTcpButtonOnAction(ActionEvent event) throws IOException {

        if(labelTcpHost.getText().isBlank() || labelTcpPort.getText().isBlank())
        {
            textFiledTcpStatus.setText("Host or User or Pass Null !!");
        }else {

            TcpClient client = new TcpClient();
            client.startConnection("127.0.0.1", 6666);
            String response = client.sendMessage("hello server");
            System.out.println("response:"+response);

            textFiledTcpStatus.setText("Tcp Connectted !!");
        }

    }

    @FXML
    void disconnectDbButtonOnAction(ActionEvent event) {
        DatabaseConnection db = new DatabaseConnection();
        db.connectClose();
        textFiledStatus.setText("Db Connectting Closed !!");

    }

    @FXML
    void disconnectGateButtonOnAction(ActionEvent event) {

    }

    @FXML
    void disconnectTcpButtonOnAction(ActionEvent event) {

    }

    @FXML
    void exitMainWinButtonOnAction(ActionEvent event) {

        DatabaseConnection db = new DatabaseConnection();
        db.connectClose();

        Stage stage = (Stage) exitMainWinButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void minimizeMainWinButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) minimizeMainWinButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void startTransactionButtonOnAction(ActionEvent event) {

    }
    public void switchForm(ActionEvent event)
    {
        if(event.getSource() == posButton)
        {
            posForm.setVisible(true);
            homeForm.setVisible(false);
            tcpForm.setVisible(true);


        } else if(event.getSource() == homeButton )
        {
            posForm.setVisible(false);
            homeForm.setVisible(true);
            tcpForm.setVisible(false);
        }

        else if(event.getSource() == tcpButton )
        {
            posForm.setVisible(false);
            homeForm.setVisible(false);
            tcpForm.setVisible(true);
        }
    }
}


