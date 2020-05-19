package it.polito.tdp.bar.model;

public class Tavolo implements Comparable<Tavolo>{
	
	private int idPosti;
	private int numDisponibili;
	
	public Tavolo(int idPosti, int numDisponibili) {
		super();
		this.idPosti = idPosti;
		this.numDisponibili = numDisponibili;
	}

	public int getIdPosti() {
		return idPosti;
	}

	public void setNumPosti(int idPosti) {
		this.idPosti = idPosti;
	}

	public int getNumDisponibili() {
		return numDisponibili;
	}

	public void setNumDisponibili(int numDisponibili) {
		this.numDisponibili = numDisponibili;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPosti;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tavolo other = (Tavolo) obj;
		if (idPosti != other.idPosti)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tavolo [idPosti=" + idPosti + ", numDisponibili=" + numDisponibili + "]";
	}

	@Override
	public int compareTo(Tavolo o) {
		return this.idPosti-o.idPosti;
	}
	
}
