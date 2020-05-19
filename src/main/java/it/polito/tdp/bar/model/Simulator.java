package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {
	
		// CODA DEGLI EVENTI
		private PriorityQueue<Event> queue = new PriorityQueue<>();
		
		// PARAMETRI DI SIMULAZIONE
		private List<Tavolo> tavoli;
		private double tolleranzaAccettabile = 0.5;
		
		public void azzeraTavoli() {
			tavoli = new LinkedList<>();
		}
		
		public void aggiungiTavoliDefault() {
			tavoli = new LinkedList<>();
			tavoli.add(new Tavolo(10, 2));
			tavoli.add(new Tavolo(8, 4));
			tavoli.add(new Tavolo(6, 4));
			tavoli.add(new Tavolo(4, 5));
			Collections.sort(tavoli);
		}
		
		public void aggiungiTavoli(int idPosti, int numDisponibili) {
			Tavolo t = new Tavolo(idPosti, numDisponibili);
			if(!tavoli.contains(t))
				tavoli.add(t);
			else
				tavoli.get(tavoli.indexOf(t)).setNumDisponibili(tavoli.get(tavoli.indexOf(t)).getNumDisponibili() + t.getNumDisponibili());
			Collections.sort(tavoli);
		}
		
		public void setTolleranzaAccettabile(double tolleranzaAccettabile) {
			this.tolleranzaAccettabile = tolleranzaAccettabile;
		}
		
		private final LocalTime oraApertura = LocalTime.of(8, 00);
		private final LocalTime oraChiusura = LocalTime.of(23, 00);
		
		// VALORI DA CALCOLARE
		private int clienti;
		private int insoddisfatti;
		
		// METODI PER RESTITUIRE I RISULTATI
		public int getClienti() {
			return clienti;
		}
		public int getInsoddisfatti() {
			return insoddisfatti;
		}
		
		public void run() {
			this.clienti = this.insoddisfatti = 0;
			this.queue.clear();
			LocalTime oraArrivoCliente = this.oraApertura;
			System.out.println(tavoli);
			do {
				
				int numPersone = (int)(Math.random()*10+1);
				Duration durata = Duration.of(Math.round(((float)(Math.random()+1)*60)), ChronoUnit.MINUTES);
				double tolleranza = Math.random();
				
				Event e = new Event(oraArrivoCliente, EventType.ARRIVO_GRUPPO_CLIENTI, numPersone, durata, tolleranza);
				this.queue.add(e);
				
				oraArrivoCliente = oraArrivoCliente.plus(Duration.of((int)(Math.random()*10+1), ChronoUnit.MINUTES));

			} while (oraArrivoCliente.isBefore(oraChiusura));
			
			// esecuzione del ciclo di simulazione
			while(!this.queue.isEmpty()) {
				Event e = this.queue.poll();
				processEvent(e);
				System.out.println(e + " " + this.clienti + " " + this.insoddisfatti);
			}
		}
		
		private void processEvent(Event e) {
				switch(e.getType()) {
					case ARRIVO_GRUPPO_CLIENTI:
						
						boolean assegnato = false;
						for(Tavolo t : tavoli) {
							if(e.getNumPerosone()<=t.getIdPosti() && e.getNumPerosone()>=t.getIdPosti()/2 && t.getNumDisponibili()>0 ) {
								t.setNumDisponibili(t.getNumDisponibili()-1);
								e.setTavolo(t);
								clienti += e.getNumPerosone();
								assegnato = true;
								break;
							}
						}
						if(assegnato==false) {
							if(e.getTollerenza()>=this.tolleranzaAccettabile)
								clienti += e.getNumPerosone();
							else 
								insoddisfatti += e.getNumPerosone();
						}
						if(assegnato == true) {
							Event nuovo = new Event(e.getTime().plus(e.getDurata()), EventType.TAVOLO_LIBERATO, e.getNumPerosone(), e.getDurata(), e.getTollerenza());
							nuovo.setTavolo(e.getTavolo());
							queue.add(nuovo);
						}
						
						break;
					
					case TAVOLO_LIBERATO:

						for(Tavolo t : tavoli)
							if(e.getTavolo().equals(t))
								t.setNumDisponibili(t.getNumDisponibili()+1);
						
						break;
				}
				
		}
}
