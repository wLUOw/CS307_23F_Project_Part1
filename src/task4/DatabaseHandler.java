package task4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseHandler implements DataHandler {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public void openDB() {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String ip = bundle.getString("ip");
        String port = bundle.getString("port");
        String db_name = bundle.getString("db_name");
        String user = bundle.getString("user");
        String pw = bundle.getString("password");

        String url = "jdbc:postgresql://" + ip + ":" + port + "/" + db_name;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> queryAllUsersName() {
        List<String> list = new ArrayList<>();
        String sql = "select name from project_user";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                list.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> queryNameByMIDRange(long min, long max) {
        List<String> list = new ArrayList<>();
        String sql = "select name from project_user where ? <= mid & mid <= ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, min);
            stmt.setLong(2, max);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                list.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> queryNameByLevel(int level) {
        List<String> list = new ArrayList<>();
        String sql = "select name from project_user where level = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, level);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                list.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> queryNameByMID2Digits(char c1, char c2) {
        List<String> list = new ArrayList<>();
        String sql = "select name from project_user where mid = ?";
        try {
            stmt = conn.prepareStatement(sql);
            String module = "'" + c1 + "" + c2 + "%'";
            stmt.setString(1, module);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                list.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> queryLongestName() {
        List<String> list = new ArrayList<>();
        String sql = "select name from project_user where length(name) = (select max(length(name)) from project_user)";
            try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                list.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int queryDistinctBirthdayCnt() {
        String sql = "select count(distinct name) from project_user";
        int cnt = 0;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.next();
            cnt = rs.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnt;
    }

    @Override
    public List<String> queryMostFollowersUserName() {
        return null;
    }

    @Override
    public void insertUser(long mid, String name, String sex, String birth,
                           int lv, String sign, List<Long> fol, String idt) {
        String sql_user = "insert into project_user values(?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql_user);
            stmt.setLong(1, mid);
            stmt.setString(2, name);
            stmt.setString(3, sex);
            stmt.setString(4, birth);
            stmt.setInt(5, lv);
            stmt.setString(6, sign);
            stmt.setString(7, idt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql_following = "insert into project_following values(?, ?)";
        try {
            stmt = conn.prepareStatement(sql_following);
            stmt.setLong(1, mid);
            for (int i = 0; i < fol.size(); i++) {
                stmt.setLong(2, fol.get(i));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertFollowersByMID(long up_mid, long fans_mid) {
        String sql = "insert into project_following values(?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, up_mid);
            stmt.setLong(2, fans_mid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSexByMID(long mid, String sex) {
        String sql = "update project_user set sex = ? where mid = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(2, mid);
            stmt.setString(1, sex);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserByMID(long mid) {
        String sql = "delete from project_user where mid = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, mid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}