package it.polito.tdp.PremierLeague.db;

import it.polito.tdp.PremierLeague.model.Model;

public class TestDao {

	public static void main(String[] args) {
		TestDao testDao = new TestDao();
		testDao.run();
	}
	
	public void run() {
		PremierLeagueDAO dao = new PremierLeagueDAO();
		Model model = new Model();
		System.out.println("Players:");
		System.out.println(dao.listAllPlayers().size());
		System.out.println(dao.getVertici(0.5, model.getIdMap()).size());
		//System.out.println(dao.listAllActions());
	}

}
