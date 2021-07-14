package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	PremierLeagueDAO dao;
	Map<Integer,Player> idMap;
	Graph<Player, DefaultWeightedEdge> grafo;
	
	public Model() {
	    dao = new PremierLeagueDAO();
		this.idMap = new HashMap<Integer, Player>();
		this.dao.listAllPlayers(idMap);
		
	}
	public String creaGrafo(double x) {
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		// aggiungo vertici
		
		Graphs.addAllVertices(this.grafo, dao.getVertici(x, this.idMap));
		
		
		//aggiungo archi 
		
		for(Adiacenza a : dao.getAdiacenze(this.idMap)){
			
			if(this.grafo.containsVertex(a.getP1()) && this.grafo.containsVertex(a.getP2())) {
			
				if(a.getPeso() < 0) {   //arco da p2 a p1
				Graphs.addEdgeWithVertices(this.grafo, a.getP2(), a.getP1(), - a.getPeso());
				
			} else if(a.getPeso() > 0) {    //arco da p1 a p2
				Graphs.addEdgeWithVertices(this.grafo, a.getP1(), a.getP2(), a.getPeso());
			}
		  }
		
       }
		String msg = ("Grafo creato con "+ this.grafo.vertexSet().size()+" vertici e "+this.grafo.edgeSet().size()+" archi!");
		return msg;
	}
	public Map<Integer, Player> getIdMap() {
		return idMap;
	}
	public void setIdMap(Map<Integer, Player> idMap) {
		this.idMap = idMap;
	}
	
	public TopPlayer best() {
		
			
			if(this.grafo == null)
				return null;
			
			Player best = null;
			int numUscenti=  Integer.MIN_VALUE;
			
			for(Player p : this.grafo.vertexSet()) {
				
				
				if(grafo.outgoingEdgesOf(p).size() > numUscenti) { //  outDegreeOf
					best = p;
					numUscenti= grafo.outgoingEdgesOf(p).size();
				}
			}
			
			return new TopPlayer (best, numUscenti);
		}
	
	
		
	public List<TopPlayer> avversari(){
	
	    List<TopPlayer> avversari = new ArrayList<>();
		
		TopPlayer best = this.best();
		
		for(DefaultWeightedEdge arco: this.grafo.outgoingEdgesOf(best.getTop())) {
			TopPlayer opponent = new TopPlayer(grafo.getEdgeTarget(arco), (int) this.grafo.getEdgeWeight(arco));
			avversari.add(opponent);
			
		}
		
		Collections.sort(avversari);
		return avversari;
	}
	
	
}
