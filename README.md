# Actividad 1 - Manejo de ficheros

## Video pruebas:

[https://www.youtube.com/watch?v=wej6ksmjBSA](https://www.youtube.com/watch?v=wej6ksmjBSA)

## Repositorio:

[GitHub - guiller91/Manejo_Ficheros: Ejercicio para el manejo de ficheros donde crearemos un almacén de coches.](https://github.com/guiller91/Manejo_Ficheros)

## Objetivos

Esta práctica consiste en la implementación de un programa Java para la gestión del almacén de coches en un concesionario.

El programa deberá leer el archivo Coche.dat si es que existe y de existir cargar la información en un ArrayList.

- No se podrá duplicar la ID ni la matrícula.
- Deberá guardar en el archivo Coche.dat al salir del programa.
- Exportara en formato texto si el usuario desea.

## Explicación Puntos Clave

No detallaré todo el código ya que gran parte del código es reusado de prácticas anteriores.

Para la asignación del ID, la idea principal es que se recorra el `ArrayList<Coches>` comprobando los ID y en el momento que vea que hay un número disponible, se lo asignara al coche. Para conseguir esto y que no duplique ID, tendremos controlado el `ArrayList` ordenándolo por ID, así de este modo nos aseguramos que si hay un hueco disponible, ese hueco no este más adelante.

<aside>
🚨 Para poder ordenar el `ArrayList` por un parámetro especifico debemos implementar la clase `Comparable` en `Coche.class` , añadir sus métodos y desarrollar el código de comparación.

</aside>

`public class Coche implements Serializable,Comparable<Coche>{}`

```java
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
```

Con esto ya podremos utilizar `Collection.sort(almacen)` para ordenar nuestro `ArrayList` y así controlarlo de la manera que buscamos.

Una vez realizado este control, ya podemos recorrer el `ArrayList`en busca del hueco al que asignar la ID.

```java
public int comprobador(int id) {
		for(int i=0;i<almacen.size();i++) {
			if(almacen.get(i).getId()==id) {
				++id;
			}
		}
		return id;	
	}
```

Toda esta idea es con el fin de evitar, que por ejemplo, tenemos un Array con las ID 0-1-2-3-4. Decidimos borrar la ID 1. El `comprobador(int id)` recorrerá el Array vera que hay el hueco del 1 disponible y se lo asignara. Pero el orden del Array sería el siguiente : 0-2-3-4-1. Si volvemos a llamar al `comprobador(int id)` , verá que después del 0 no viene el 1 y lo volverá a asignar, así que lo tendríamos duplicado.

Pasamos a la parte de gestión de archivos. Para la lectura de archivos usaremos las clases `FileInputStream` y `ObjectInputStream`. Leemos el archivo con `readObject()` y se lo asignamos a nuestro `ArrayList<Coche>` . Este método solo lo llamaremos si existe el archivo “Coches.dat” con `file.exists()` .

```java
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
```

```java
File file = new File("Coches.dat");
		
		if(file.exists()) {
			controlador.rellenarAlmacen(file);
			System.out.println("Almacen importado con éxito \n");
		}
```

Para la exportación de los archivos usaremos `FileOutputStream` y `ObjectOutputStream`, para la exportación de la lista completa. Para el formato texto usaremos `FileWritter` y `BufferedWritter`.

```java
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
```

```java
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
```