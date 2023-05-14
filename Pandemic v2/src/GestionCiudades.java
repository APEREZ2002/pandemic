import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class GestionCiudades {
    public static void main(String[] args) {
    	String ciudad2 = null;////////////////////////////////////////////////////////////////////////////////
    	int key=0;/////////////////ESTAS 3 LINEAS SE INICIALIZAN 3 VARIABLES CON VALORES NULOS O CERIS////////
    	String enf=null;//////////////////////////////////////////////////////////////////////////////////////
    	mostrarDatos(ciudad2,key,enf); // Llamamos al metodo mostrardatos con los parametros inicializados
    	
    	
    }

    public static String prueba(String ciudad) {
		
    	return ciudad; ///// Metodo adicional recibe como parametro ciudad y devuelve la misma cadena de ciudad.
    }
    
    public static String mostrarDatos(String ciudad2, int key, String nombre) { //// Metodo pricipal que se encarga de mostrar los datos de las ciudades. Recibe como parametors "ciudad2", "key", "nombre".  
    																			//// devuelve una cadena de texto con la informacion de las ciudades que cumpla ciertas codiciones.
        // Crear una lista de nombres de ciudades a partir del archivo
		Scanner teclado = new Scanner(System.in); // creamos el objeto "Scanner" para leer los datos introducidos por el usurio.
        ArrayList<String> nombresCiudades = array(); //Llamamos al metodo array para obtener una lista de nombres de las ciudades. Los nombres de las ciudades se almacenan en un objeto "ArrayList".
        String[] nombresCiudadesArray = nombresCiudades.toArray(new String[0]); // Convierte un arraylist en un array de strings

        // Crear las ciudades
        Ciudad[] ciudades = new Ciudad[nombresCiudadesArray.length]; // crea un array de objetos con la misma longitud que el array de nombres de ciudades 
        for (int i = 0; i < nombresCiudadesArray.length; i++) { //El bucle inicializa cada objeto "ciudad" en el array "ciudades".
            ciudades[i] = new Ciudad(i + 1, new String[]{nombresCiudadesArray[i]}, new int[]{0}, new Ciudad[]{});// cada objeto ciudad tiene un identificador unico (i+1), un array con el nombre de la ciudad, un array con el estado de la enfermedad y un array con las ciudades conectadas.
        }

        // Conectar algunas ciudades
        ciudades[2].setCiudades(new Ciudad[]{ciudades[1]});
        ciudades[3].setCiudades(new Ciudad[]{ciudades[1]});

        // Agregar las ciudades a una lista
        List<Ciudad> listaCiudades = Arrays.asList(ciudades); // Convierte el array de objetos "Ciudad" en una lista de objetos "Ciudad".
        
        String ciudadABuscar = ciudad2;// Inicializa la variable 'ciudadABuscar' con el valor que se le pasa como argumento 'ciudad2' en la funcion 'mostrarDatos'.
        
        // Inicializar la variable DatosCiudad como un String vacío
        String DatosCiudad = "";
        // Imprimir la información de todas las ciudades
        int pregunta=key;
        if(pregunta==0) {
            for (Ciudad ciudad : listaCiudades) {													/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if (ciudad.getNombre().length > 0 && ciudadABuscar.equals(ciudad.getNombre()[0])) { /////////////////////		Se verifica si la variable "pregunta" es igual a cero. 					  ///////////////
                    DatosCiudad += ciudad.getNombre()[0] + "\n";									/////////////////////		Si es asi, se recorre la lista de ciudades y se verifica si el nombre 	  ///////////////
                   if(key==4) {																 		/////////////////////		coincide con "ciudadesABuscar". 										  ///////////////
                	   key=3;																		/////////////////////		Si se encuentra una coincidencia, se agrega la informacion de la ciudad   ///////////////
                	   DatosCiudad += "Nivel de enfermedad: " + key + "\n";   						/////////////////////		a la cadena "DatoCiudad"												  ///////////////
                   }else {																			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                	   DatosCiudad += "Nivel de enfermedad: " + key + "\n";   
                   }																				
                    DatosCiudad += "\n";															
                    DatosCiudad += nombre;																	
                    DatosCiudad += "\n";															
                }
            }	
        }else {
            for (Ciudad ciudad : listaCiudades) {													
                if (ciudad.getNombre().length > 0 && ciudadABuscar.equals(ciudad.getNombre()[0])) {	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                	int[] estadoEnfermedad = ciudad.getEstadoEnfermedad();							/////////////////////																				  ///////////////
                    estadoEnfermedad[0]=1;															/////////////////////		Si 'pregunta' no es igual a cero, se realiza una accion similar, 		  ///////////////
                	DatosCiudad += ciudad.getNombre()[0] + "\n";									/////////////////////		'DatosCiudad', establece el nivel de enfermedad a '1'. 					  ///////////////
                	if(key==4){																		/////////////////////																				  ///////////////
                	key=3;																			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                	DatosCiudad += "Nivel de enfermedad: " + key + "\n";							
                	}else {
                		DatosCiudad += "Nivel de enfermedad: " + key + "\n";	
                	}
                																					
                	DatosCiudad += "\n";																
                    DatosCiudad += nombre;															
                    DatosCiudad += "\n";																	
                																																		
                }																					
            }		
        }


		return DatosCiudad; /// la funcion devuelve la cadena 'DatosCiudad'.

	}


    public static int sumarElementos(int[] arr) {////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int suma = 0;							 ///////////																												  ///////////
        for (int i : arr) {						 ///////////	Toma un array de enteros como argumento y devuelve la suma de todos los elementos.							  ///////////
            suma += i;							 ///////////	Primero inicializa una variable 'suma' con valor 0 y luego utiliza un loop para iterar						  ///////////
        }										 ///////////	a traves de todos los elementos del array y sumarlos a la variable 'suma'. Al final devuelve la variable suma ///////////
        return suma;							 ///////////																												  ///////////
    }											 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	private static ArrayList<String> array() {
        ArrayList<String> ciudades = new ArrayList<String>();// Se inicializa una nueva instancia de la clase 'ArrayList<String> llamada 'ciudades'
        try {
            FileReader fr = new FileReader("ciudades.txt");	// Se utiliza un bloque try-catch para abrir un archivo de texto llamado "ciudades.txt" y leerlo linea por linea usando bufferedReader.
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {		/////////////////////////////////////////////////////////////////////////////////////////////
                String[] partes = linea.split(";");			///// Para cada linea, divide la cadena, en dos partes utilizando " ; " como separador //////
                ciudades.add(partes[0]);    				///// y agrega la primera parte a la lista de ciudades.								   //////
            }												/////																				   //////			
            												/////////////////////////////////////////////////////////////////////////////////////////////
            br.close();										
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ciudades; 
    }


}






