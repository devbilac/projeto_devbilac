package com.mygdx.game;

import java.io.Serializable;
import java.util.ArrayList;




public class Ranking implements Serializable{
    ArrayList<Ranking> dado = new ArrayList<Ranking>();
    public Ranking(int i, int d, int i2) {
		// TODO Auto-generated constructor stub
	}
	public Ranking(MyGdxGame myGdxGame) {
		// TODO Auto-generated constructor stub
	}
	protected void addDado(int d,int i){
  	Ranking user = new Ranking(i,d,i);
      dado.add(user);
    }	
    protected ArrayList<Ranking> getDado(){
      return dado;
    }
}