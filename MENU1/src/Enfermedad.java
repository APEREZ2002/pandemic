// Crear una clase en Java llamada Enfermedad
public class Enfermedad {

  // Declarar las variables de instancia
  private String nombre;
  private String color;
  private String ciudadInfectada;
  private int bvlActual;
  private Juego juego;
  private int estadoCura;

  // Crear un constructor que reciba el nombre, el color, la ciudad infectada y el juego al que pertenece la enfermedad
  public Enfermedad(String nombre, String color, String ciudadInfectada,int bvlActual,
		  Juego juego,int estadoCura) {
    this.nombre = nombre;
    this.color = color;
    this.ciudadInfectada = ciudadInfectada;
    this.juego = juego;
    // Asignar el valor del nivel de enfermedad del juego al bvlActual
    this.bvlActual = bvlActual;
    this.estadoCura = estadoCura;
  }


//Crear un método get que devuelva el valor de la variable estadoCura
public int getEstadoCura() {
return estadoCura;
}
public int getNvl() {
return bvlActual;
}
public String getNombre() {
return nombre;
}
public String getColor() {
return color;
}
public String getCiudad() {
return ciudadInfectada;
}
  // Crear un método para aumentar el nivel de la enfermedad
  public void aumentarNivel() {
	if(bvlActual!=3) {
	  bvlActual++;
	  System.out.println("El nivel de la enfermedad " + nombre + " ha aumentado a " + bvlActual + ".");
	}else if(bvlActual==3) {
		  System.out.println("El nivel de la enfermedad " + nombre + "es de 3, no puede aumentar.");
	}
  }

  
  public void inicioEnfermedad() {
	if(bvlActual!=3) {
	  bvlActual++;
	  System.out.println("El nivel de la enfermedad " + nombre + " ha aumentado a " + bvlActual + ".");
	}else if(bvlActual==3) {
		  System.out.println("El nivel de la enfermedad " + nombre + "es de 3, no puede aumentar.");
	}
  }
 
  
  public boolean comprovarNvl() {
	  boolean llave=false;
	  if(bvlActual==3) {
		  System.out.println("Se expande la enfermedad");
		  bvlActual=0;
		  llave=true;
	  }
	  return llave;
	  
	  }

  public boolean comprobarCura() {
	  boolean cura=false;
	  if(estadoCura==100) {
		  cura=true;
		  System.out.println("Completaste la cura de "+nombre);
	  }
	  return cura;
	  }
  
  public int desarrollarCura() {
	  bvlActual--;
	    System.out.println("El estado de la nivel es de " + bvlActual);
	    return bvlActual;
	  }
  
  
  // Crear un método para aumentar la infección de la enfermedad
  public void aumentarInfeccion() {
    // Aquí puedes poner la lógica para aumentar la infección de la enfermedad, como comprobar si se supera el límite, provocar brotes, etc.
    // Llamar al método expandirEnfermedad() del objeto juego
    juego.expandirEnfermedad();
    System.out.println("La infección de la enfermedad " + nombre + " ha aumentado en la ciudad " + ciudadInfectada + ".");
  }
}