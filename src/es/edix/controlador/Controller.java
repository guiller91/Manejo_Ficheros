package es.edix.controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import es.edix.modelo.Coche;

public class Controller implements Serializable{
	
	
	private static final long serialVersionUID = -6944691566078896150L;
	public ArrayList<Coche> almacen;
	private int acumulador = 0;
	
	public Controller() {
		super();
		almacen = new ArrayList<Coche>();
	}
	
	public String rellenarAlmacen(File file){
		try (FileInputStream fis = new FileInputStream(file);
			 ObjectInputStream ois = new ObjectInputStream(fis);) {
								
				@SuppressWarnings("unchecked")
				ArrayList<Coche> lista = (ArrayList<Coche>)ois.readObject();
				almacen = lista;
				return "Archivo cargado con exito";				
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
		return null; 
	}
	
	public void exportarAlmacenData() {

		// exportamos codificado
		try (FileOutputStream fos = new FileOutputStream(new File("Coches.dat"));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			oos.writeObject(almacen);
			System.out.println("Archivo codificado en Coches.dat");
		} catch (IOException e) {
			System.out.println("Error al exportar");
			e.printStackTrace();
		}

	}

	public void exportarAlmacenTexto() {
		// exportamos en formato texto
		try (FileWriter fw = new FileWriter(new File("Almacen.txt")); 
			 BufferedWriter bw = new BufferedWriter(fw);) {

			for (int i = 0; i < almacen.size(); i++) {
				bw.write(almacen.get(i).toString());
				bw.newLine();
			}
			bw.flush();
			System.out.println("Archivo exportado a Almacen.txt");
		} catch (IOException e) {
			System.out.println("Error al exportar");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Comienza los metodos utiles dentro del ArrayList
	 */
	
	public String añadirCoche(Coche coche) {
		if(buscarCocheMatricula(coche.getMatricula()) != null) {
			return "La matricula ya esta registrada, no puede registrar este coche";
		}else {			
			Collections.sort(almacen);
			coche.setId(comprobador(acumulador));
			almacen.add(coche);
			return "Coche añadido";
		}
		
	}
	
	public String buscarCocheMatricula(String matricula) {
		Coche coche;
		for (int i = 0; i < almacen.size(); i++) {
			coche = almacen.get(i);

			if (coche.getMatricula().equalsIgnoreCase(matricula)) {
				return coche.toString();
			}
		}
		
		return null;
	}
	
	public Coche cocheId(int id) {
		for (Coche c : almacen) {
			if (c.getId() == id) {
				return c;
			}
		}
		System.out.println("ID no encontrada");
		return null;
	}
	
	public Coche delete(int id) {
		try {
			Coche c = cocheId(id);
			int n = almacen.indexOf(c);			
			return almacen.remove(n);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("delete -> No existe esa id en memoria");
			return null;
		}
	}
	
	public void list(){
		if(!almacen.isEmpty()) {
			Collections.sort(almacen);
			for (Coche c : almacen) {
				System.out.println(c.toString());
			}
		}else {
			System.out.println("No hay ningun coche en el almacen");			
		}
	}
	
	public int comprobador(int id) {
		for(int i=0;i<almacen.size();i++) {
			if(almacen.get(i).getId()==id) {
				++id;
			}
		}
		return id;	
	}
		
		
	
}

