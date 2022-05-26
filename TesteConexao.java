import java.sql.Connection;
import java.sql.DriverManager;

/**
 * TesteConexao
 */
public class TesteConexao {

    public static void main(String[] args)throws Exception {
        //Conexão com o banco
        //Prestar atenção no fuso horario
        //?→parametros da string de conexão
        //&→separa chave valor
        //jdbc:mysql://localhost:3306/aulaPOO?useTimezone=true&serverTimezone=UTC
        String host = "localhost";
        String port = "3306";
        String db = "db_pessoas";
        String user = "root";
        // String password = "123456";
        String password = "1597534862";
        String stringDeConexao = String.format(
            "jdbc:mysql://%s:%s/%s?useTimezone=true&serverTimezone=UTC",host,port,db
        );
        
        Connection conexão = DriverManager.getConnection(stringDeConexao, user, password);    
        
        if(conexão == null)
            System.out.println("Deu ruim");
        else
            System.out.println("Deu bom");
    }
}