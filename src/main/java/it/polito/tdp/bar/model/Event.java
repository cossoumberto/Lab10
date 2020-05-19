package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;

public class Event implements Comparable<Event> {
	
	public enum EventType {
		ARRIVO_GRUPPO_CLIENTI, TAVOLO_LIBERATO
	}
	
	private LocalTime time;
	private EventType type;
	private int numPersone;
	private Duration durata;
	private double tollerenza;
	private Tavolo tavolo;
	
	public Event(LocalTime time, EventType type, int numPersone, Duration durata, double tollerenza) {
		super();
		this.time = time;
		this.type = type;
		this.numPersone = numPersone;
		this.durata = durata;
		this.tollerenza = tollerenza;
		this.tavolo = null;
	}
	
	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int getNumPerosone() {
		return numPersone;
	}

	public void setNumPerosone(int numPerosone) {
		this.numPersone = numPerosone;
	}

	public Duration getDurata() {
		return durata;
	}

	public void setDurata(Duration durata) {
		this.durata = durata;
	}

	public double getTollerenza() {
		return tollerenza;
	}

	public void setTollerenza(double tollerenza) {
		this.tollerenza = tollerenza;
	}
	
	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}


	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + ", numPersone=" + numPersone + ", durata=" + durata
				+ ", tollerenza=" + tollerenza + ", tavolo=" + tavolo + "]";
	}

	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.time);
	}

}
