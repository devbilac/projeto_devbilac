package Dao;
import java.sql.Connection;
import java.sql.DriverManager;


//Conexão com o Banco de Dados
public class ConexaoBD {
    
    public Connection getConexao(){
        Connection con = null;
        
        String BD = "DevBilac";
        String usuario = "root";
        String senha = "";
        String URL = "jdbc:mysql://127.0.0.1:3306/"+BD;
        String driver = "com.mysql.jdbc.Driver";
    
        java.sql.Connection conexao = null;
        try{
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(URL, usuario, senha);
            
        }catch(Exception e){}
          return con;
    }

}
