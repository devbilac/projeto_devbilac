package com.mygdx.game.telas;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;

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
        
     public Conexao_BD() throws Exception{   ////Método para passar os parâmetros de conexão com o banco de dados
         
    	
       
        
       
        try {
        Class.forName("com.mysql.jdbc.Driver");
       
        connection = DriverManager.getConnection(URL, usuario, senha);
       // connection.setAutoCommit(false);
        
       // connection.close(); 
        
        }catch(ClassNotFoundException ex) { 
       JOptionPane.showMessageDialog(null, "Conexão Não efetuada");
           
        }catch(SQLException sql){  
         throw new Exception("Falha ocorrida: " + sql.getMessage()); 
                   
         
        
    }
        
     }
     
     
     public void Fechar_Conexao() throws Exception{ ////Método que para encerrar conexão com o banco de dados
       

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
public void Confirmar_Transacao() throws Exception{ // //Método para efetuar a confirmação de uma transação realizada no banco de dados
       

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

 }}public void Cancelar_Transacao() throws Exception{ ////Método para efetuar o cancelamento de uma transação
       

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
