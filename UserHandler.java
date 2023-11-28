import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    
    public static RegisteredUser handleLogin(String email, String password){

        String sql = "SELECT User_Password, Email, UserID, User_Address, FName, LName FROM Users";
        
        ArrayList<String> passwordList = new ArrayList<String>();
        ArrayList<String> emailList = new ArrayList<String>();
        ArrayList<Integer> userIDList = new ArrayList<Integer>();
        ArrayList<String> addressList = new ArrayList<String>();
        ArrayList<String> fnameList = new ArrayList<String>();
        ArrayList<String> lnameList = new ArrayList<String>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                emailList.add(rs.getString("Email"));
                passwordList.add(rs.getString("User_Password"));
                userIDList.add(rs.getInt("UserID"));
                addressList.add(rs.getString("User_Address"));
                fnameList.add(rs.getString("FName"));
                lnameList.add(rs.getString("LName"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i=0; i < emailList.size(); i++)  {
            System.out.println("entered email is: " + email + "\n");
            System.out.println("List email is: " + emailList.get(i) + "\n");
            if (emailList.get(i).compareTo(email) == 0) {
                System.out.println("entered Password is: " + password + "\n");
                System.out.println("List Password is: " + passwordList.get(i) + "\n");
                if (passwordList.get(i).compareTo(password) == 0){

                    ArrayList<Integer> memberIDList = new ArrayList<Integer>();
                    ArrayList<Integer> memberUserIDList = new ArrayList<Integer>();
                    ArrayList<String> memberCreditCardList = new ArrayList<String>();

                    sql = "SELECT MemberID, UserID, CreditCardInfo FROM Members";

                    try (Connection conn = DatabaseConnection.getConnection();
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql)) {

                        while (rs.next()) {
                            memberIDList.add(rs.getInt("MemberID"));
                            memberUserIDList.add(rs.getInt("UserID"));
                            memberCreditCardList.add(rs.getString("CreditCardInfo"));
                      
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    for(int j = 0; j < memberIDList.size(); j++){
                        //If Member
                        if (userIDList.get(i) == memberIDList.get(j)){
                            RegisteredUser returnUser = new RegisteredUser(
                                emailList.get(i), fnameList.get(i), lnameList.get(i), 
                                memberCreditCardList.get(j), addressList.get(i), true);

                            return returnUser;
                        }
                    }

                    //Not member but still regitetred
                    RegisteredUser returnUser = new RegisteredUser(
                                emailList.get(i), fnameList.get(i), lnameList.get(i), 
                               "Not a member - no saved card", addressList.get(i), false);

                            return returnUser;
                }
            }
        }
        //Login Fail
        System.out.println("login fail");
        return null;
    }

    public static long handleMembership(String email, String creditcard){
        long id = 0;
        String sql = "SELECT Email, UserID FROM Users";
        
        ArrayList<String> emailList = new ArrayList<String>();
        ArrayList<Integer> userIDList = new ArrayList<Integer>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                emailList.add(rs.getString("Email"));
                userIDList.add(rs.getInt("UserID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    for (int i=0; i < emailList.size(); i++)  {
        if (emailList.get(i).compareTo(email) == 0) {
            String SQL = "INSERT INTO Members(UserID, CreditCardInfo) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, userIDList.get(i));
            pstmt.setString(2, creditcard);

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
        }
    }

        return id;
    }

}