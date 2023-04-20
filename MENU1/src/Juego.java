// Crear una clase en Java llamada Juego
public class Juego {

  // Declarar las variables de instancia
  private int turnoActual;
  private String jugador;
  private String enfermedad;
  private String enfermedad2;
  private String enfermedad3;
  private String enfermedad4;
  private int infeccion;
  private int brotesActuales;

  // Crear un constructor que reciba los valores iniciales de las variables
  public Juego(int turnoActual, String jugador, String enfermedad,String enfermedad2,
	String enfermedad3,String enfermedad4,int infeccion,int brotesActuales) {
    this.turnoActual = turnoActual;
    this.jugador = jugador;
    this.enfermedad = enfermedad;
    this.enfermedad2 = enfermedad2;
    this.enfermedad3 = enfermedad3;
    this.enfermedad4 = enfermedad4;
    this.infeccion = infeccion;
    this.brotesActuales = brotesActuales;
  } 

  // Crear un método para iniciar el juego
  public void iniciarJuego() {
    // Aquí puedes poner la lógica para iniciar el juego, como mostrar el tablero, repartir las cartas, etc.
    System.out.println("El juego ha comenzado.");
  }

  // Crear un método para finalizar el juego
  public void finalizarJuego() {
    // Aquí puedes poner la lógica para finalizar el juego, como mostrar el resultado, guardar las estadísticas, etc.
    System.out.println("El juego ha terminado.");
  }

  // Crear un método para finalizar la ronda
  public boolean finalizarRonda() {
    // Aquí puedes poner la lógica para finalizar la ronda, como actualizar el turno, comprobar las condiciones de victoria o derrota, etc.
    // El método debe devolver un valor booleano que indique si el juego debe continuar o no
	 System.out.println("La ronda " + turnoActual + " ha finalizado.");
	 turnoActual++;
    return !derrota() && !victoria();
  }

  // Crear un método para expandir la enfermedad
  public void expandirEnfermedad() {
    // Aquí puedes poner la lógica para expandir la enfermedad, como aumentar la infección, provocar brotes, etc.
    infeccion++;
    System.out.println("La enfermedad se ha expandido.");
  }

  // Crear un método para guardar la partida
  public void guardarPartida() {
    // Aquí puedes poner la lógica para guardar la partida en un archivo de texto, como escribir los valores de las variables en un formato adecuado, etc.
    System.out.println("La partida se ha guardado.");
  }

  // Crear un método para comprobar si hay derrota
  public boolean derrota() {
    return false;
  }

  // Crear un método para comprobar si hay victoria
  public boolean victoria() {
    return false;
  }

  // Crear un método para comprar o barajar el estado de la partida
  public boolean compraobar_estado_partida() {
	  System.out.println("Se ha comparado el estado de la partida.");
    return true;
    
  }
  
//Crear un método get que devuelva el valor de la variable turnoActual
public int getTurnoActual() {
return turnoActual;
}

//Crear un método get que devuelva el valor de la variable jugador
public String getJugador() {
return jugador;
}

//Crear un método get que devuelva el valor de la variable enfermedad
public String getEnfermedad() {
return enfermedad;
}
//Crear un método get que devuelva el valor de la variable enfermedad
public String getEnfermedad2() {
return enfermedad2;
}
public String getEnfermedad3() {
return enfermedad3;
}
public String getEnfermedad4() {
return enfermedad4;
}
//Crear un método get que devuelva el valor de la variable infeccion
public int getInfeccion() {
return infeccion;
}

//Crear un método get que devuelva el valor de la variable brotesActuales
public int getBrotesActuales() {
return brotesActuales;
}

//Crear un método get que devuelva el valor de la variable brotesActuales
public int aumentarBrotes() {
brotesActuales++;
return brotesActuales;
}

}