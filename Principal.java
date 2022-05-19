import javax.swing.JOptionPane;
import static java.lang.Integer.parseInt;

public class Principal {
    public static void main(String[] args) {
        String menu = "1-Cadastrar\n2-Atualizar\n3-Apagar\n4-Listar\n0-Sair";
        int op = 0;
        
        do{
            try{
                op = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch(op){
                    case 1:
                    {
                        String nome = JOptionPane.showInputDialog("Digite o nome");
                        String fone = JOptionPane.showInputDialog("Digite o fone");
                        String email = JOptionPane.showInputDialog("Digite o e-mail");
                        Pessoa p = new Pessoa(nome,fone,email);
                        p.inserir();
                        break;
                    }
                    case 2:
                    {
                        
                        // String nome = JOptionPane.showInputDialog("Digite o nome");
                        int codigo = parseInt(JOptionPane.showInputDialog(Pessoa.listar() + "Digite o código"));
                        String pessoas = Pessoa.buscarPorId(codigo);
                        String nome = JOptionPane.showInputDialog( Pessoa.listar() + "Digite o nome", pessoas);
                        String fone = JOptionPane.showInputDialog(Pessoa.listar() + "Digite o fone");
                        String email = JOptionPane.showInputDialog(Pessoa.listar() + "Digite o e-mail");
                        Pessoa p = new Pessoa(codigo,nome,fone,email);
                        p.atualizar();
                        break;
                    }   
                    case 3:
                    {
                        new Pessoa(
                            parseInt(JOptionPane.showInputDialog("Digite o código"))
                        ).apagar();
                    }
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, Pessoa.listar());
                        break;
                    case 0:
                        op = 0;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida");
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "A operação falhou");
                System.out.println(e.getMessage());
            } 
        }while(op != 0);
    }

    public void menuPrincipal(){

    }
}
