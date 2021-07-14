package it.polito.tdp.PremierLeague.model;

public class TopPlayer implements Comparable<TopPlayer>{
	
	private Player top;
	private int delta;
	
	@Override
	public String toString() {
		return "Il giocatore migliore è :"+top.getName();
	}

	public Player getTop() {
		return top;
	}

	public void setTop(Player top) {
		this.top = top;
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public TopPlayer(Player top, int delta) {
		super();
		this.top = top;
		this.delta = delta;
	}

	@Override
	public int compareTo(TopPlayer o) {
		// TODO Auto-generated method stub
		return -( this.delta-o.delta);
	}

}
