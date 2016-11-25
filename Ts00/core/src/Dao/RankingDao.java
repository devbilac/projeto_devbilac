package Dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.mysql.jdbc.Connection;
import com.mysql.*;
import java.sql.*;
import com.mysql.jdbc.PreparedStatement;
import javax.swing.JOptionPane;
import Model.Ranking;

//Classe que é usada para pegar os dados do Arquivo Serializado.
public class RankingDao implements Serializable{
	//Lista de <Ranking>
    ArrayList<Ranking> dado = new ArrayList<Ranking>();
    
    //Classe que cria um objeto do Tipo <Ranking> e adiciona 3 Valores e depois adiciona em uma lista os objetos <Ranking>.
    protected void addDado(int a,int b,int c){
	  	Ranking user = new Ranking();
	  	user.setRa(a);
	  	user.setFase(b);
	  	user.setPontuacao(c);
      dado.add(user);
    }	
    public void addLista(ArrayList<Ranking> lista){
    	try {
			for(Ranking lista1 : lista){
				Ranking user = new Ranking();
				user.setRa(lista1.getRa());
				user.setFase(lista1.getFase());
				user.setPontuacao(lista1.getPontuacao());
				this.dado.add(user);
	        }
		} catch (Exception e) {}
    }
    //Retorna a Lista de <Ranking>
    //Futuramente: Adicionar um campo de recebimento no getDado para poder usar o Switch e poder escolher o tipo de filtro, Por fase, Total.
    @SuppressWarnings("unchecked")
	public ArrayList<Ranking> getDado(){
    	//Codigo ordenador do maior para o Menor
    	Collections.sort (dado, new Comparator() {
	        public int compare(Object o1, Object o2) {
	            Ranking p1 = (Ranking) o1;
	            Ranking p2 = (Ranking) o2;
	            return p1.pontuacao > p2.pontuacao ? -1 : (p1.pontuacao < p2.pontuacao ? +1 : 0);
	         }
        });
    	return dado;
    }
    public void buscarRankingsBD(){
    	ConexaoBD ConexaoBD = new ConexaoBD();
    	Ranking pontuacao;
    	try{
    		java.sql.Connection conex = ConexaoBD.getConexao();
            //where ra=?
            String sql = "select * from ranking";
            java.sql.PreparedStatement st = conex.prepareStatement(sql);
            //st.setInt(1, 1);
            ResultSet rs = st.executeQuery();
            while(rs.next() == true){
            	pontuacao = new Ranking();
            	pontuacao.setRa(rs.getInt(2));
            	pontuacao.setFase(rs.getInt(3));
            	pontuacao.setPontuacao(rs.getInt(4));
                
                this.dado.add(pontuacao);
            }
        }catch(Exception e){
        	e.printStackTrace();JOptionPane.showMessageDialog(null,"Metodo buscarRankingsBD:\n"+e);}
    }
    public int compare(Ranking r1, Ranking r2) {
        return Integer.valueOf(r1.getPontuacao()).compareTo(Integer.valueOf(r2.getPontuacao()));
    }
}
