package br.com.egerton.connection;

/**
 *
 * @author Egerton Maciel
 */
public class ConexaoLocal extends Conexao {

    public ConexaoLocal() {

        USUARIO = "root";
        SENHA = "";
        URL = "jdbc:mysql://localhost:3306/gerenciador_nota_fiscal";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
