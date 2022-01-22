package es.edix.modelo;

import java.io.Serializable;

public class Coche implements Serializable,Comparable<Coche>{
	
	
	private static final long serialVersionUID = 6895363561283909268L;
	
	private int id;
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	
	public Coche(String matricula, String marca, String modelo, String color) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		
	}
	
	public Coche() {
		super();		
	}
	
	public void setId(int id) { 
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "ID: " + id +"\nMarca: " + marca + "\nModelo: " + modelo + "\nMatrícula: " + matricula + "\nColor: "+ color + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
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
		Coche other = (Coche) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		return true;
	}

	@Override
	public int compareTo(Coche o) {
		 if (o.getId() > this.getId()) {
			 return -1;
		 }else if(o.getId() < this.getId()) {
			 return 1;
		 }else {
			 return 0;
		 }
		  
	}



}

