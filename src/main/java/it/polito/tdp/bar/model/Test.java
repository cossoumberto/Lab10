package it.polito.tdp.bar.model;

public class Test {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		//model.aggiungiTavoliDefault();
		
		model.azzeraTavoli();
		model.aggiungiTavoli(4, 6);
		model.aggiungiTavoli(6, 5);
		model.aggiungiTavoli(8, 4);
		model.aggiungiTavoli(10, 1);
		
		
		model.run();
		System.out.println("Sono arrivati " + (model.getNumClienti()+model.getNumInsoddisfatti()) + " clienti di cui:");
		System.out.println(model.getNumClienti() + " sono stati serviti e " + model.getNumInsoddisfatti() + " sono andati via insoddisfatti");

	}

}
