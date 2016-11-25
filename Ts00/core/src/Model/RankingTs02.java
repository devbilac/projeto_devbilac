package Model;

import java.io.Serializable;
import java.util.ArrayList;
import com.mygdx.game.DevBilac;




public class RankingTs02 implements Serializable{
    ArrayList<RankingTs02> dado = new ArrayList<RankingTs02>();
    public RankingTs02(int i, int d, int i2) {
		// TODO Auto-generated constructor stub
	}
	public RankingTs02(DevBilac devbilac) {
		// TODO Auto-generated constructor stub
	}
	protected void addDado(int d,int i){
  	RankingTs02 user = new RankingTs02(i,d,i);
      dado.add(user);
    }	
    protected ArrayList<RankingTs02> getDado(){
      return dado;
    }
}