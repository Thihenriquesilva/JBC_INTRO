import java.sql.Connection;
import java.sql.PreparedStatement;

public class Pessoa {
    private int codigo;
    private String nome;
    private String fone;
    private String email;

    public Pessoa (String nome, String fone, String email){
        this(0, nome, fone, email);    
    }

    public Pessoa(int codigo, String nome, String fone, String email){
        setCodigo(codigo);
        setNome(nome);
        setFone(fone);
        setEmail(email);
    }

    public Pessoa(int codigo){
        this(codigo, null, null, null);
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
        // ConnectionFactory factory = new ConnectionFactory();
        Connection conexao = ConnectionFactory.getConnection();
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
        //ConnectionFactory factory = new ConnectionFactory();
        try(Connection conexao = ConnectionFactory.getConnection();//;→ dentro desse try, uso o ponto e virgula para separar os comandos 
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
        String sql = "DELETE tb_pessoa WHERE codigo = ?";
        //2. Abrir uma conexão com o MySql
        //ConnectionFactory factory = new ConnectionFactory();
        try(Connection conexao = ConnectionFactory.getConnection();//;→ dentro desse try, uso o ponto e virgula para separar os comandos 
            //3.Preparar o comando  
            var ps = conexao.prepareStatement(sql);){
             
            //4. Substituir os placeholders
            ps.setInt(1, codigo);
            //5. Executar o comando
            ps.execute();
            //6. Fechar os recursos: já está feito pelo try-with-resources
        }
    }

    //Exercicio ↓↓↓↓
    //Metodo estáticado, pois não utiliza variaveis de instancia.
    public static String listar() throws Exception{
        String sql = "SELECT * FROM tb_pessoa";
        String msg = "";
        try(
            Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();        
        ){
            while(rs.next()){
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String fone = rs.getString("fone");
                String email = rs.getString("email");
                
                msg += String.format("Codigo: %s |Nome: %s |Fone: %s |Email: %s\n", codigo, nome, fone, email);
            }    
            
        }
        return msg;
    }

    public static Pessoa buscarPorId(int codi) throws Exception{
        String sql = "SELECT * FROM tb_pessoa WHERE codigo = ?";
        Pessoa p = new Pessoa(null,null,null);
        try(
            Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql);            
        ){
            ps.setInt(1,codi);
            java.sql.ResultSet rs = ps.executeQuery();
            if(rs.next()){
                p.setNome(rs.getString("nome"));
                p.setFone(rs.getString("fone"));
                p.setEmail(rs.getString("email")); 
            
            System.out.println("\n"+  p.getNome() + "\n"+p.getFone() +"\n" + p.getEmail());
            }  
        }
        return p;        
        
    }
}
