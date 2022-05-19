import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    String host = "localhost";
        String port = "3306";
        String db = "aulaPOO";
        String user = "root";
        String password = "1597534862";
        String stringDeConexao = String.format(
            "jdbc:mysql://%s:%s/%s?useTimezone=true&serverTimezone=UTC",host,port,db
        );
        
        public Connection getConnection() throws Exception{
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
