import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static String host = "localhost";
    private static String port = "3306";
    private static String db = "db_pessoas";
    private static String user = "root";
    private static String password = "1597534862";
    //private static String password = "123456";
    // private static String stringDeConexao = String.format(
    //     "jdbc:mysql://%s:%s/%s?useTimezone=true&serverTimezone=UTC",host,port,db
    // );
        
    public static  Connection getConnection() throws Exception{
            //DriveManager→String conexão, usuario, senha
        return DriverManager.getConnection(
             String.format("jdbc:mysql://%s:%s/%s?useTimezone=true&serverTimezone=UTC",
                host, 
                port, 
                db
            ),
             user,
            password
         );
    } 
}
