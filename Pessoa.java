import java.sql.Connection;
import java.sql.PreparedStatement;

public class Pessoa {
    private int codigo;
    private String nome;
    private String fone;
    private String email;

    public Pessoa(String nome,String fone,String email) {
        setNome(nome);
        setFone(fone);
        setEmail(email);
    }
    public Pessoa(int codigo){
        setCodigo(codigo);
    }
    public Pessoa(int codigo, String nome, String fone, String email) {
        this(nome, fone, email);
        setCodigo(codigo);
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFone() {
        return fone;
    }
    public void setFone(String fone) {
        this.fone = fone;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Mapeamento objeto relacional
    //Hibernate
    public void inserir() throws Exception{
        //1. Definir o comando SQL
        String sql = "INSERT INTO tb_pessoa(nome,fone,email) VALUES (?, ?, ?)";
        //2.Abrir uma conexao com o MySQl Server
        ConnectionFactory factory = new ConnectionFactory();
        Connection conexao = factory.getConnection();
        //3. Preparar o comando (solicitar ao MySQL Server que compile o comando SQL previamente)
        PreparedStatement ps =conexao.prepareStatement(sql);
        //|| var ps =conexao.prepareStatement(sql);
        //4. Substituir os eventuais placeholders
        ps.setString(1, nome);
        ps.setString(2, fone);
        ps.setString(3, email);
        //5. Executar o comando
        ps.execute();
        //6. Fechar os recursos (conexão e o comando preparado)
        ps.close();
        conexao.close();
    }

    public void atualizar()throws Exception{
        //1. Especificar o comando SQL(UPDATE)
        String sql = "UPDATE tb_pessoa SET nome=?, fone = ?, email = ? WHERE codigo = ?";
        //2. Abrir uma conexão com o MySql
        ConnectionFactory factory = new ConnectionFactory();
        try(Connection conexao = factory.getConnection();//;→ dentro desse try, uso o ponto e virgula para separar os comandos 
            //3.Preparar o comando  
            var ps = conexao.prepareStatement(sql);){
             
            
            //4. Substituir os placeholders
            ps.setString(1, nome);
            ps.setString(2, fone);
            ps.setString(3, email);
            ps.setInt(4, codigo);
            //5. Executar o comando
            ps.execute();
            //6. Fechar os recursos: já está feito pelo try-with-resources
        }
 
    }

    public void apagar()throws Exception{
        //1. Especificar o comando SQL(UPDATE)
        String sql = "DELETE tb_pessoa SET  WHERE codigo = ?";
        //2. Abrir uma conexão com o MySql
        ConnectionFactory factory = new ConnectionFactory();
        try(Connection conexao = factory.getConnection();//;→ dentro desse try, uso o ponto e virgula para separar os comandos 
            //3.Preparar o comando  
            var ps = conexao.prepareStatement(sql);){
             
            //4. Substituir os placeholders
            ps.setInt(1, codigo);
            //5. Executar o comando
            ps.execute();
            //6. Fechar os recursos: já está feito pelo try-with-resources
        }
    }
}
