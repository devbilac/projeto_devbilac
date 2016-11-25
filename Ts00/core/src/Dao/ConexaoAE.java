package Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import Model.Ranking;
import Dao.RankingDao;

//Conexão com o Arquivo Externo
public class ConexaoAE {
	File arquivo = new File("C:\\Users\\luizo\\Desktop\\date");
    File arqui = new File(arquivo, "arqui.mrc");
    RankingDao t = new RankingDao();
    
    
    //Classe que cria o Arquivo Externo e já adiciona alguns dados do tipo Ranking.
    public void setArquivo() throws Exception{
    	try {
    	  	//Inicio Escrita
            arquivo.mkdir(); // Cria uma Pasta no local/nome de acordo com a Variavel <arquivo>
            FileOutputStream f = new FileOutputStream(arqui); // Cria o Arquivo no Local+Nome de acordo com a Variavel <arqui>
            ObjectOutputStream o = new ObjectOutputStream(f); // Abre o objeto para Escrita
            //(RA,FASE,PONTUACAO);
            t.addDado(1, 1,15000);
            t.addDado(1, 4,1000);
            t.addDado(1, 5,15300);
            t.addDado(1, 6,16004);
            t.addDado(1, 8,17680);
            t.addDado(1, 3,20400);
            t.addDado(1, 2,11400);
            t.addDado(1, 3,9400);
            t.addDado(1, 4,15000);
            o.writeObject(t); // Escreve...
            //Fim Escrita
		} catch (Exception e) {
			System.out.println(e);
		}
    }
    
    //Classe que obtem os dados do Ranking apartir do Arquivo Externo.
    //Retorno do tipo <Ranking> em ArrayList, caso ocorra algum erro o retorno é nullo.
    public ArrayList<Ranking> getArquivo() throws Exception{
        try {
        	FileInputStream fi = new FileInputStream(arqui);
            ObjectInputStream oi = new ObjectInputStream(fi);
            t = (RankingDao) oi.readObject();
            ArrayList<Model.Ranking> lista = t.getDado();
            return lista;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
    }
    
    
    
    

	
	
	
}
