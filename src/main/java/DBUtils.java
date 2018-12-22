import java.sql.*;
import java.util.Calendar;
import java.util.Date;

class DBUtils {

    private static Connection dbc = Log.makeConnection();

    static int deleteSpending(int spendingId) {
        String sql = "DELETE FROM waitingSpending WHERE spending_id = ? ";
        PreparedStatement ps;
        try {
            ps = dbc.prepareStatement(sql);
            ps.setInt(1, spendingId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    static String[] getUserInfo(String username) {
        try {
            String[] userInfo = new String[4];
            String sql = "SELECT name,surname,email,phone FROM personnel WHERE user_name = ? ";
            PreparedStatement ps = dbc.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (getUserBundle(rs, userInfo)) return userInfo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean getUserBundle(ResultSet rs, String[] userInfo2) throws SQLException {
        if (rs.next()) {
            userInfo2[0] = rs.getString("name");
            userInfo2[1] = rs.getString("surname");
            userInfo2[2] = rs.getString("email");
            userInfo2[3] = rs.getString("phone");
            return true;
        }
        return false;
    }

    static int getUserDep(String username) {
        try {
            int userDep = 0;
            String sql = "SELECT dep_id FROM personnel WHERE user_name = ? ";
            PreparedStatement ps = dbc.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userDep = rs.getInt("dep_id");
            }
            return userDep;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    static String[] getDepInfo(String username) {
        try {
            String[] depInfo = new String[4];
            String sql = "SELECT dep_name, dep_limit, name, surname FROM personnel p, department d"
                    + " WHERE p.position = 'Yönetici' AND d.dep_id = ? AND d.dep_id = p.dep_id ";
            PreparedStatement ps = dbc.prepareStatement(sql);
            ps.setInt(1, DBUtils.getUserDep(username));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                depInfo[0] = rs.getString("dep_name");
                depInfo[1] = Integer.toString(rs.getInt("dep_limit"));
                depInfo[2] = rs.getString("name") + " " + rs.getString("surname");
                return depInfo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String[] getManInfo(String username) {
        try {
            String[] userInfo = new String[4];
            String sql = "SELECT name,surname,email,phone FROM personnel WHERE dep_id = ? AND position = 'Yönetici'";
            PreparedStatement ps = dbc.prepareStatement(sql);
            ps.setInt(1, DBUtils.getUserDep(username));
            ResultSet rs = ps.executeQuery();
            if (DBUtils.getUserBundle(rs, userInfo)) return userInfo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static int calculateLimitLeft(String username) {
        try {
            Date d = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            int month = cal.get(Calendar.MONTH) + 1;
            Statement st = dbc.createStatement();
            String sql = "SELECT sum(s_amount) FROM spending s, department d  "
                    + "WHERE d.dep_id = " + DBUtils.getUserDep(username) + " AND d.dep_id = s.dep_id AND EXTRACT(month FROM \"s_date\") = " + month;
            ResultSet rs = st.executeQuery(sql);
            int num = 0, lim = 0;
            while (rs.next()) {
                num = rs.getInt(1);
            }

            sql = "SELECT dep_limit FROM department d  "
                    + "WHERE d.dep_id = " + DBUtils.getUserDep(username);
            rs = st.executeQuery(sql);
            if (rs.next()) {
                lim = rs.getInt(1);
            }
            return lim - num;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
