// Crear una clase en Java llamada Jugador
public class Jugador {

  // Declarar las variables de instancia
  private String nombre;
  private Juego juego;

  // Crear un constructor que reciba el nombre del jugador y el juego al que pertenece
  public Jugador(String nombre, Juego juego) {
    this.nombre = nombre;
    this.juego = juego;
  }

  // Crear un método para tratar la enfermedad
  public void tratarEnfermedad() {
    // Aquí puedes poner la lógica para tratar la enfermedad, como reducir la infección, usar cartas, etc.
    System.out.println("El jugador " + juego.getJugador() + " ha tratado la enfermedad.");
  }

  // Crear un método para descubrir la cura
//  public int descubrirCura() 
}