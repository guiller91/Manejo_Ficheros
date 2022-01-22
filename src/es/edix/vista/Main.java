package es.edix.vista;

import java.io.File;
import java.util.Scanner;

import es.edix.controlador.Controller;
import es.edix.modelo.Coche;

public class Main {

	public static void main(String[] args) {

		Controller controlador = new Controller();
		File file = new File("Coches.dat");
		
		if(file.exists()) {
			controlador.rellenarAlmacen(file);
			System.out.println("Almacen importado con �xito \n");
		}
		
		try(Scanner sc = new Scanner(System.in)) {
			
			boolean continuar = true;
			int num;
			Coche coche;
			do {
				System.out.println("Elige un n�mero del men�:\n" + "1. A�adir nuevo coche\n"
						+ "2. Borrar coche por ID\n" + "3. Consultar coche por ID\n" + "4. Listado de coches\n" 
						+ "5. Exportar coches a archivo de texto\n" + "6. Salir de la aplicaci�n");
				num = Integer.parseInt(sc.nextLine());

				switch (num) {
				case 1:
					coche = new Coche();
					System.out.println("Introduzca marca del veh�culo");
					coche.setMarca(sc.nextLine());
					System.out.println("Introduzca modelo del veh�culo");
					coche.setModelo(sc.nextLine());
					System.out.println("Introduzca matr�cula del veh�culo");
					coche.setMatricula(sc.nextLine());
					System.out.println("Introduzca color del veh�culo");
					coche.setColor(sc.nextLine());
					controlador.a�adirCoche(coche);
					break;
				case 2:
					System.out.println("Introduzca ID del coche que desea borrar");
					controlador.delete(Integer.parseInt(sc.nextLine()));
					break;
				case 3:
					System.out.println("Introduzca ID del coche que desea buscar");
					coche = controlador.cocheId(Integer.parseInt(sc.nextLine()));
					System.out.println(coche.toString());
					break;
				case 4:
					System.out.println("Listado de coches:");
					controlador.list();
					break;
				case 5:
					controlador.exportarAlmacen();
					break;
				case 6:
					System.out.println("Saliendo de la aplicaci�n....");
					
					continuar = false;
					break;
				default:
					System.out.println("Introduzca un numero del menu");
				}

			} while (continuar);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
