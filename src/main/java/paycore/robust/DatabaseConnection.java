package paycore.robust;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseConnection {
    static Connection conn = null;

    public static Boolean checkUser(String username,String userPass) {

        System.out.println("Username:"+username+ " email:" +userPass);
        String query = "select *from all_user where user_name='" +username + "' and user_code='" +userPass + "'";
        Boolean setValue = false;
        System.out.println("checkUser:"+query);

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet queryResult = pstmt.executeQuery();

            while (queryResult.next()) {

                setValue = true;

                System.out.println(queryResult.getInt("is_open") + "\t"
                        + queryResult.getString("user_code") + "\t"
                        + queryResult.getString("user_name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return setValue;
    }

    public Connection connect( String url,String user,String password) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the DB successfully..");
            System.out.println("Con:"+conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connection");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        return conn;
    }

    public void connectClose() {
        try {
            if(conn != null)
            {
                conn.close();
                System.out.println("PostgreSQL closed successfully.");
                System.out.println("Closed Conn:"+conn);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
