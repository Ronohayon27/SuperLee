package HR.DataAccess;

import DBConnect.Connect;

import java.sql.Connection;
import java.sql.SQLException;

//this will be singleton
public class ManagerPasswordMapper {
    private static ManagerPasswordMapper instance;
    private static String managerPassword;

    /**
     * we will take the managet password from the database
     */
    private ManagerPasswordMapper() {
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Passwords WHERE Role = 'HRManager'");
            while (rs.next()) {
                managerPassword = rs.getString("Pass");
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    public static ManagerPasswordMapper getInstance() {
        if (instance==null){
            instance=new ManagerPasswordMapper();
        }
        return instance;
    }

    /**
     * @return the current password
     */
    public String getManagerPassword() {
        return managerPassword;
    }

    /**
     * @param managerPassword the new password
     *                        we will update the database
     */
    public void setManagerPassword(String managerPassword) {
        ManagerPasswordMapper.managerPassword = managerPassword;
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE Passwords SET Pass = '"+managerPassword+"' WHERE Role = 'HRManager'");
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }

}
