// Crea una clase java llamada control de parametros
public class ControlDeParametros {

  // Atributos de la clase
  private Tablero tablero; // El tablero del juego
  private int ronda; // La ronda actual del juego
  private Jugador jugador; // El jugador del juego
  private Enfermedad enfermedad; // La enfermedad del juego

  // Constructor de la clase
  public ControlDeParametros(Tablero tablero, Jugador jugador, Enfermedad enfermedad) {
    this.tablero = tablero;
    this.jugador = jugador;
    this.enfermedad = enfermedad;
    this.ronda = 1; // El juego empieza en la ronda 1
  }

  // Función para actualizar el tablero del juego
  public void actualizarTablero() {
    // Aquí va la lógica para actualizar el tablero según el estado del juego
    // Por ejemplo, se pueden propagar las infecciones, mover al jugador, etc.
  }

  // Función para iniciar una nueva ronda del juego
  public void nuevaRonda() {
    // Aquí va la lógica para iniciar una nueva ronda del juego
    // Por ejemplo, se pueden robar cartas, aumentar el nivel de alerta, etc.
    this.ronda++; // Se incrementa la ronda en uno
  }

  // Función para actualizar los datos del jugador
  public void actualizarDatosJugador() {
    // Aquí va la lógica para actualizar los datos del jugador según el estado del juego
    // Por ejemplo, se pueden modificar sus recursos, su ubicación, sus acciones, etc.
  }

  // Función para actualizar los datos de la enfermedad
  public void actualizarDatosEnfermedad() {
    // Aquí va la lógica para actualizar los datos de la enfermedad según el estado del juego
    // Por ejemplo, se pueden modificar sus brotes, su cura, su resistencia, etc.
  }

  // Función para finalizar el juego por derrota
  public void finalizarJuegoPorDerrota() {
    // Aquí va la lógica para finalizar el juego por derrota
    // Por ejemplo, se puede mostrar un mensaje de fin de juego, guardar los resultados, etc.
  }

  // Función para finalizar el juego por victoria
  public void finalizarJuegoPorVictoria() {
    // Aquí va la lógica para finalizar el juego por victoria
    // Por ejemplo, se puede mostrar un mensaje de fin de juego, guardar los resultados, etc.
  }
}