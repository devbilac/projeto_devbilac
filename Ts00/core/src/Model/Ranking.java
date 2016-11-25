package Model;

import java.io.Serializable;

public class Ranking implements Serializable {
	public int ra;
	public int fase;
	public int pontuacao;
	
	public Ranking() {
	}
	public int getRa() {
		return ra;
	}
	public void setRa(int ra) {
		this.ra = ra;
	}
	public int getFase() {
		return fase;
	}
	public void setFase(int fase) {
		this.fase = fase;
	}
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	

}
