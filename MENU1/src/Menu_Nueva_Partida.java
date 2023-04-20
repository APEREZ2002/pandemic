import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

public class Menu_Nueva_Partida {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		resetDatos();
		inicio();
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("La demo a finalizado");
	
	}
	
	
	
	///////////////////////////////////////////////////////////////
	//	Esta funcion sirve borrar los datos de otras partidas al iniciar//////        
	///////////////////////////////////////////////////////////////
	private static void resetDatos() {
		// TODO Auto-generated method stub
		// Crear objeto File con el nombre del archivo
	    File archivo = new File("ciudades_infectadas.txt");
	    
	    try {
	        // Crear objeto PrintWriter para escribir en el archivo
	        PrintWriter writer = new PrintWriter(archivo);
	        // Escribir una cadena vacía para borrar todos los datos
	        writer.print("");
	        // Cerrar el objeto PrintWriter
	        writer.close();
	        System.out.println("Bienvenido a la demo PANDEMIC");
	    } catch (IOException e) {
	        System.out.println("Error al borrar los datos del archivo.");
	        e.printStackTrace();
	    }}

	///////////////////////////////////////////////////////////////
	//	Esta funcion sirve para iniciar toda la demo         //////        
	///////////////////////////////////////////////////////////////
	private static void inicio() {
		Scanner teclado = new Scanner(System.in);
		Scanner teclado2 = new Scanner(System.in);

		System.out.println("¿Cual es tu nombre?");
		String jugador1 = teclado.next();
		Juego juego = new Juego(0, jugador1, "Delta", "Alfa", "Beta", "Gamma", 9, 0);
		Jugador jugador = new Jugador(jugador1, juego);
		Enfermedad Delta = new Enfermedad("Delta", "Rojo", "Nueva York", 1, juego, 0);
		Enfermedad Alfa = new Enfermedad("Alfa", "Azul", "Madrid", 0, juego, 0);
		Enfermedad Beta = new Enfermedad("Beta", "Amarillo", "Corea del Norte", 0, juego, 0);
		Enfermedad Gamma = new Enfermedad("Gamma", "Verde", "Paris", 0, juego, 0);
		Vacuna v_Delta= new Vacuna(0);
		Vacuna v_Alfa= new Vacuna(0);
		Vacuna v_Beta= new Vacuna(0);
		Vacuna v_Gamma= new Vacuna(0);
		
		int[] brotes = new int[1]; 
		int game = 0;
		boolean[] llave = new boolean[5]; 
		llave[0] = false;
		llave[1] = false;

		
		String ciudad;

		do {
			System.out.println("¿que enfermedad quieres tratar?");
			System.out.println("1-Delta");
			game = teclado.nextInt();

			ciudad = Delta.getCiudad();

			do {
				mostrarDatosRonda(Delta,juego,ciudad,v_Delta,llave);

				
				
				if(juego.getBrotesActuales()>=8) {
					
					System.out.println("Brotes maximos alcanzados ");
					
				}
				
			
				do {
					System.out.println("1-CURAR, 2-pasar de ronda, 3-GuardarDatosActuales, 4-Elegir una ciudad para curar");
				game = teclado.nextInt();
				if(game==4) {
					if(mostrarCiudadesInfectadas()==false) {
						
					}else {
						if (llave[1]) {
							System.out.println("------------------------");
							System.out.println("que ciudad quiere curar");
							System.out.println("------------------------");
							ciudad=teclado2.nextLine();
							mostrarDatosRonda(Delta,juego,ciudad,v_Delta, llave);
					}
					}
				}else if(game==3) {
					guardar(Delta,ciudad, juego);
					}
					
				}while(game==4 || game==3);
				
				
				if (game == 1) {
					System.out.println("1-vacuna  2-curar ciudad");
					game=teclado.nextInt();
					if(game == 1) {
					v_Delta.aumentarEstadoVacuna();	
					}else {
					System.out.println("La ciudad "+ciudad+" a reducido el nivel de enfermedad");
					Delta.desarrollarCura();
					}
					
				}
				comprobaciones(Delta,llave,juego, v_Delta);
				
			
				if (juego.getTurnoActual() % 2 == 0) {
					Delta.aumentarNivel();
				}

				
				
				
				juego.finalizarRonda();
				if (llave[1] == true) {
				buscarCiudad(ciudad,juego);	
				}

			} while (llave[0] != true);
			llave[1] = true;
		} while (llave[0] != true);
		
	}

	///////////////////////////////////////////////////////////////
	//	Esta funcion sirve para mostrar los datos de la ronda actual//////        
	///////////////////////////////////////////////////////////////
    private static void mostrarDatosRonda(Enfermedad Delta, Juego juego, String ciudad,Vacuna v_Delta, boolean[] llave) {
		// TODO Auto-generated method stub
    	System.out.println("___________________________________________________");
		System.out.println("Jugador "+juego.getJugador());
    	System.out.println("...................................................");
		System.out.println("Enfermedad " + Delta.getNombre());
		System.out.println("Color " + Delta.getColor());
		System.out.println("Nvl enfermedad " + Delta.getNvl());
    	System.out.println("...................................................");
		System.out.println("Estado de vacuna de "+Delta.getNombre()+" es de " + v_Delta.getEstadoVacuna()+"%");
		System.out.println("Ciudad actual " + ciudad);
		System.out.println("Brotes actuales "+juego.getBrotesActuales());
		System.out.println("");
		System.out.println("___________________________________________________");
		
		System.out.println("Ciudades infectadas por "+Delta.getNombre());
		System.out.println(llave[1]);
		if(llave[1]) {
			llave[4]=true;	
		}
		if(llave[4]) {
			mostrarCiudadesInfectadas();
		}

		
	}

	///////////////////////////////////////////////////////////////
	//	Esta funcion sirve para mostrar la lista de ciudades infectadas//////        
	///////////////////////////////////////////////////////////////
	public static boolean mostrarCiudadesInfectadas() {
        try (BufferedReader br = new BufferedReader(new FileReader("ciudades_infectadas.txt"))) {
            String linea;
            boolean archivoVacio = true;
            while ((linea = br.readLine()) != null) {
                archivoVacio = false;
                System.out.println(linea);
            }
            if (archivoVacio) {
                System.out.println("No hay ciudades infectadas");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return true;
		
    }

	///////////////////////////////////////////////////////////////
	//	Esta funcion realiza las difetentes comprobaciones   //////
	///////////////////////////////////////////////////////////////
	private static void comprobaciones(Enfermedad Delta, boolean[] llave, Juego juego,Vacuna v_Delta) {
		// TODO Auto-generated method stub
		if (juego.getBrotesActuales()>=8) {
			llave[0]=true;
			System.out.println("BROTES MAXIMOS,FINAL DEL JUEGO");
		}else {
		llave[0] = v_Delta.comprovarEstadoVacuna();	
		}
		llave[1] = Delta.comprovarNvl();
		
	}

	///////////////////////////////////////////////////////////////
	//	Esta funcion sirve para guardar partida, te deja sobreescrivir y guardar debajo
	///////////////////////////////////////////////////////////////
	private static void guardar(Enfermedad Delta, String ciudad, Juego juego) {
	    String filename = "guardado.txt";

	    // Comprobar si el archivo ya existe
	    boolean fileExists = new File(filename).exists();

	    // Mostrar un menú de opciones
	    System.out.println("¿Qué acción desea realizar?");
	    System.out.println("1. Sobrescribir el archivo existente");
	    System.out.println("2. Guardar debajo del archivo existente");
	    Scanner scanner = new Scanner(System.in);
	    int opcion = scanner.nextInt();

	    // Abrir el archivo en el modo correcto según la opción seleccionada
	    FileWriter writer;
	    try {
	        if (opcion == 1) {
	            writer = new FileWriter(filename, false); // Sobrescribir el archivo
	        } else {
	            writer = new FileWriter(filename, true); // Guardar debajo del archivo
	        }

	        // Escribir los datos en el archivo
	        writer.write("Jugador "+juego.getJugador()+ "\n");
	        writer.write("Enfermedad " + Delta.getNombre() + "\n");
	        writer.write("Color " + Delta.getColor() + "\n");
	        writer.write("Nvl enfermedad " + Delta.getNvl() + "\n");
	        writer.write("Estado de cura " + Delta.getEstadoCura() + "\n");
	        writer.write("Ciudad actual " + ciudad + "\n");
	        writer.write("\n");
	        writer.close();

	        // Mostrar un mensaje de confirmación
	        if (fileExists && opcion == 1) {
	            System.out.println("El archivo ha sido sobrescrito.");
	        } else {
	            System.out.println("Los datos han sido guardados en el archivo.");
	        }
	    } catch (IOException e) {
	        System.out.println("Ocurrió un error al guardar los datos en el archivo.");
	        e.printStackTrace();
	    }
	}

	
	///////////////////////////////////////////////////////////////
	//	Esta funcion sirve para guardar las colindantes infectadas cuando hay un brote, en un archivo de .txt
	///////////////////////////////////////////////////////////////
	public static void buscarCiudad(String ciudadPrincipal,Juego juego) {
	    String archivoEntrada = "ciudades.txt";
	    String archivoSalida = "ciudades_infectadas.txt";
	    String linea;
	    try {
	        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida));
	        BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
	        while ((linea = lector.readLine()) != null) {
	            String[] datosCiudad = linea.split(";");
	            String nombreCiudad = datosCiudad[0];
	            if (nombreCiudad.equals(ciudadPrincipal)) {
	                String[] colindantesArray = datosCiudad[3].split(",");
	                for (int i = 0; i < colindantesArray.length; i++) {
	                	juego.aumentarBrotes();
	                    String ciudadInfectada = colindantesArray[i];
	                    escritor.write(ciudadInfectada);
	                    escritor.newLine();
	                }
	            }
	        }
	        lector.close();
	        escritor.close();
	        System.out.println("Nuevas ciudades infectadas registradas en " + archivoSalida);
	    } catch (IOException e) {
	        System.out.println("Ocurrió un error al leer o escribir en el archivo.");
	        e.printStackTrace();
	    } 
	}
}
