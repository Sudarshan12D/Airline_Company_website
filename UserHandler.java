import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserHandler {

    public static long handleRegistration(String email, char[] password, String firstName, String lastName, String address) {
        String SQL = "INSERT INTO Users(FName, LName, User_Address, Email, User_Password) VALUES (?, ?, ?, ?, ?)";

        long id = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, address);
            pstmt.setString(4, email);
            pstmt.setString(5, new String(password)); // Convert char array to String

            int affectedRows = pstmt.executeUpdate();

            // Check the affected rows
            if (affectedRows > 0) {
                // Get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
    public static long handleLogin(String email, char[] password){
        String SQL = "INSERT INTO Users(FName, LName, User_Address, Email, User_Password) VALUES (?, ?, ?, ?, ?)";

        

        return 0;
    }

}