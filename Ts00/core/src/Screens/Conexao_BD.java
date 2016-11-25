package Screens;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Aldeir
 */
public class Conexao_BD {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultset;
        String URL = "jdbc:mysql://localhost:3306/devbilac";
        String usuario= "root";
        String senha = "";
    
        public static void main(String[] args)   {  
            
        }
        
     public Conexao_BD() throws Exception{   ////M�todo para passar os par�metros de conex�o com o banco de dados
         
    	
       
        
       
        try {
        Class.forName("com.mysql.jdbc.Driver");
       
        connection = DriverManager.getConnection(URL, usuario, senha);
       // connection.setAutoCommit(false);
        
       // connection.close(); 
        
        }catch(ClassNotFoundException ex) { 
       JOptionPane.showMessageDialog(null, "Conex�o N�o efetuada");
           
        }catch(SQLException sql){  
         throw new Exception("Falha ocorrida: " + sql.getMessage()); 
                   
         
        
    }
        
     }
     
     public void addRanking(int ra,int fase,int pontuacao) throws Exception{
     	try{         // Estrutura para inser��o de dados
    		 
     		Conexao_BD conexao = new Conexao_BD();
			 conexao.getConexao();
    		 
               String sql= "INSERT INTO ranking (ra,fase,pontuacao) VALUES (?,?,?)";
              
    			PreparedStatement ps = Conexao_BD.connection.prepareStatement(sql);
               ps.setInt(1,ra);
               ps.setInt(2,fase);
               ps.setInt(3,pontuacao);
              
              
               ps.executeUpdate();
       }                                               
          catch(SQLException erro ){
        	  System.out.println(erro);
          }
     }
     
     public void Fechar_Conexao() throws Exception{ ////M�todo que para encerrar conex�o com o banco de dados
       

 {

 try 

 {
     
connection= DriverManager.getConnection(URL, usuario, senha);
 if (connection == null || connection.isClosed())

 return;

 connection.close();

 }

 catch (SQLException sql) 

 {

 throw new Exception("Falha ocorrida: " + sql.getMessage());

 }

 }
     
      
     } 
public void Confirmar_Transacao() throws Exception{ // //M�todo para efetuar a confirma��o de uma transa��o realizada no banco de dados
       

 {

 try

 {
      connection= DriverManager.getConnection(URL, usuario, senha);
 if (connection == null || connection.isClosed())

 if (connection == null || connection.isClosed())

 return;

 connection.commit();

 }

 catch (SQLException sql)

 {

 throw new Exception("Falha ocorrida: " + sql.getMessage());

 } 

 }}public void Cancelar_Transacao() throws Exception{ ////M�todo para efetuar o cancelamento de uma transa��o
       

 {

 try

 {
     connection = DriverManager.getConnection(URL, usuario, senha);

 if (connection == null || connection.isClosed())

 return;

 connection.rollback();

 }

 catch(SQLException sql)

 {

 throw new Exception("Falha ocorrida: " + sql.getMessage());

 }

}}public Connection getConexao()

 {

 return connection;

 }}
