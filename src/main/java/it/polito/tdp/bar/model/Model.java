package it.polito.tdp.bar.model;

public class Model {

	private Simulator s = new Simulator();
	
	public void azzeraTavoli() {
		s.azzeraTavoli();
	}
	
	public void aggiungiTavoliDefault() {
		s.aggiungiTavoliDefault();
	}
	
	public void aggiungiTavoli(int idPosti, int numDisponibili) {
		s.aggiungiTavoli(idPosti, numDisponibili);
	}
	
	public void setTolleranzaAccettabile(double tolleranzaAccettabile) {
		s.setTolleranzaAccettabile(tolleranzaAccettabile);
	}
	
	public void run() {
		s.run();
	}
	
	public int getNumClienti() {
		return s.getClienti();
	}
	
	public int getNumInsoddisfatti() {
		return s.getInsoddisfatti();
	}
}
