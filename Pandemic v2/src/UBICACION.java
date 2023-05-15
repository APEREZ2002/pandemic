
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.Icon;

public class UBICACION {
	// Declaramos los atributos de la clase
	private int x;
	private int y;
	private String descripcion;
	private int num;
	private boolean estado;
	// Llamamos al método main de la clase LeerYModificarXML
	int[] parametros = LeerYModificarXML.main();
	// Declaramos el nivel de enfermedad de la ubicación
	int nivelEnfermedad;
	// Declaramos los valores iniciales de aumentar y brote
	public boolean aumentar=true;
	public boolean brote=false;
	
	public UBICACION(int x, int y, String descripcion, int num, boolean estado, int nivelEnfermedad) {
		this.x = x;
		this.y = y;
		this.descripcion = descripcion;
		this.num = num;
		this.estado = estado;
		this.parametros = parametros;
		this.nivelEnfermedad = nivelEnfermedad;
		this.aumentar=aumentar;
	}
	// Método para subir el nivel de enfermedad de la ubicación
	public void subirNivelEnfermedad(String nombreCiudad, int nivel) {
	    if (this.descripcion.equals(nombreCiudad)) {
	        this.nivelEnfermedad += nivel; 
	    }
	}

	// Método para obtener el nivel de enfermedad de la ubicación

	public int getNivelEnfermedad() {
		
		return nivelEnfermedad;
	}
	// Método para obtener las ubicaciones colindantes a una ubicación dada
	public String getColindantes(String ciudad) {
	    String colindantes = "";
	    String archivoCiudades = "ciudades.txt";
	    String separadorCampos = ";";
	    String separadorColindantes = ",";
	    boolean ciudadEncontrada = false;

	    try {
	        File archivo = new File(archivoCiudades);
	        Scanner scanner = new Scanner(archivo);

	        while (scanner.hasNextLine() && !ciudadEncontrada) {
	            String linea = scanner.nextLine();
	            String[] campos = linea.split(separadorCampos);

	            if (campos[0].equals(ciudad)) {
	                String[] ciudadesColindantes = campos[3].split(separadorColindantes);
	                for (int i = 0; i < ciudadesColindantes.length; i++) {
	                    colindantes += ciudadesColindantes[i];
	                    if (i != ciudadesColindantes.length - 1) {
	                        colindantes += ",";
	                    }
	                }
	                ciudadEncontrada = true;
	            }
	        }

	        scanner.close();
	    } catch (FileNotFoundException e) {
	    }
	    return colindantes;
	}



	// Método para verificar si se ha producido un brote en la ubicación
	public boolean broteRealizado() {
		if(brote==true) {
			return true;
		}
		if(aumentar==true) {
			return false;
		}else {
			brote=true;
			getColindantes(descripcion);
			return false;
			
		}

		
	}
	// Método getter para obtener el valor de aumentar

	public boolean getAumentar() {
		return aumentar;
	}
	// Método setter para asignar un valor a aumentar y devolver su valor
	public boolean setAumentar(boolean prueba) {
		aumentar = prueba;
		return aumentar;
	}
	// Método setter para asignar un valor al nivelEnfermedad sumándolo al valor anterior
	public int setNivelEnfermedad0(int nivelEnfermedad) {
		this.nivelEnfermedad += nivelEnfermedad;
		return this.nivelEnfermedad;
	}
	
	// Método setter para asignar un valor a nivelEnfermedad sumándolo al valor anterior pero si es mas de 3 lo iguala a tres
	public int setNivelEnfermedad(int nivelEnfermedad) {
		this.nivelEnfermedad += nivelEnfermedad;
		if(this.nivelEnfermedad>3) {
			this.nivelEnfermedad =3;
		}
		return this.nivelEnfermedad;
	}
	// Método para curar una cantidad de enfermedad, restando su valor a enfermedad
	public int curarEnfermedad(int nivelEnfermedad) {
		this.nivelEnfermedad -= nivelEnfermedad;
		return this.nivelEnfermedad;
	}
	// Método para curar una enfermedad cuando tienes la vacuna
	public int curarEnfermedadVacuna() {
		this.nivelEnfermedad =0 ;
		return this.nivelEnfermedad;
	}
	// Metodo para sumar un nivel de enferemedad pero si es mas que 3 y no a brotado aun
	//se desacriva la funcion brotar de esa ciudad y se iguala a 3 para ue no pueda subr a 4
	public int sumarNivelEnfermedad(int nivelEnfermedad) {
		if(nivelEnfermedad>3 && aumentar==true) {
			aumentar=false;
			setAumentar(false);
			this.nivelEnfermedad = 3;
			return 3;
		}else if(aumentar==false && nivelEnfermedad>=3){
			this.nivelEnfermedad = nivelEnfermedad;
			return nivelEnfermedad;
		}else {
			aumentar=true;
			this.nivelEnfermedad = nivelEnfermedad+1;
			return nivelEnfermedad;		
			}
		
	}
	// Método setter para asignar valores a un array de parámetros y devolverlo
	public int[] setParametros(int infectadasInicio1, int infectadasRonda1, int enfermedadesActivasDerrota1, int brotesDerrota1) {
		parametros[0]=infectadasInicio1;
		parametros[1]=infectadasRonda1;
		parametros[2]=enfermedadesActivasDerrota1;
		parametros[3]=brotesDerrota1;
		return parametros;
	}
	// Método getter para obtener el array de parámetros
	public int[] getParametros() {
		return parametros;
	}
	// Método getter para obtener numero de enfermedad asignada a la ciudad

	public int getNum() {
		return num;
	}
	// Método getter para obtener el nombre de la enferemdad dependiendo del numero asignado y si esta activa
	public String getNombre() {
		if (num == 0 && estado==true) {
			return "Delta";
		} else if (num == 1 && estado==true) {
			return "Alfa";
		} else if (num == 2 && estado==true) {
			return "Beta";
		} else if (num == 3 && estado==true) {
			return "Gamma";
		} else {
			return "Sin Enfermedad";
		}
	}
	//Metodo para retornar la posicion X
	public int getX() {
		return x;
	}
	//Metodo para asignar la posicion X
	public void setX(int x) {
		this.x = x;
	}
	//Metodo para retornar la posicion y
	public int getY() {
		return y;
	}
	//Metodo para retornar la posicion y
	public void setY(int y) {
		this.y = y;
	}
	//Asignar un nombre a la ciudad
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	//Retornar el nombre de ciduad
	public String getNombreCiudad() {
		// TODO Auto-generated method stub
		return descripcion;
	}
	//retorna el estado de la ciudad
	public boolean getEstado() {
		return estado;
	}
	//asigna el estado de la ciudad en base a lo que insertas
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	//genera un estado aleatorio en base a el valor de infectadas inicio y raliza un math random para asiganrlo aleatoriamente
	public boolean generarEstado(int ejecuciones) {//en el caso de que se ya no se pueda realizar lo devielve entrue
		int numAleatorio = (int) (Math.random() * 3) + 1;
		if (numAleatorio == 1 && ejecuciones <= getParametros()[0]) {
			setEstado(true);
			return true;

		} else {
			setEstado(false);
			return false;
		}
	}
	//genera un estado aleatorio en base a el valor de infectadas por ronda y raliza un math random para asiganrlo aleatoriamente
	public boolean generarEstado1(int suma) {
		int numAleatorio = (int) (Math.random() * 3) + 1;
		if (numAleatorio == 1 && suma <= parametros[1]) {
			setEstado(true);
			return true;
		} else {
			setEstado(false);
			return false;
		}
	}

	
}
