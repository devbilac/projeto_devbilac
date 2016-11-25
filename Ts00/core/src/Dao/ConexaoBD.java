package Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import Screens.Conexao_BD;
import Screens.MainMenu;


//Conexão com o Banco de Dados
public class ConexaoBD {
    
    public Connection getConexao(){
        Connection con = null;
        /*
        String BD = "centraln_bilac";
        String usuario = "centraln_devb";
        String senha = "bilac123";
        String URL = "jdbc:mysql://177.234.151.98:3306/"+BD;
        String driver = "com.mysql.jdbc.Driver";
         */
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
