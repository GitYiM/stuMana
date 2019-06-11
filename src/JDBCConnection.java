import java.sql.*;

public class JDBCConnection {
    private static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/javawork";
    private static final String DBUSER = "GitYiM";
    private static final String DBPASS = "langzihuitou0";
    private static final String TIMEAREA = "?serverTimezone=GMT%2B8"; //设置时区

    /**
     * 静态代码块随类的加载而加载  加载驱动
     * 但只能执行一次
     */
    static {
        try {
            Class.forName(DBDRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(DBURL + TIMEAREA, DBUSER, DBPASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(con);
        return con;
    }

    public static void close(ResultSet rs, Statement statement, Connection connection) {  //关闭数据库方法  （越先开的越后关）
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
