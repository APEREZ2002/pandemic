// Esta es una clase que representa una ciudad
public class Ciudad {

  // Estos son los atributos de cada ciudad
  private int idCiudad; // El id de la ciudad
  private String nombre; // El nombre de la ciudad
  private int[] posicion_ciudad; // La posición de la ciudad como un arreglo de dos enteros
  private String estadoEnfermedad; // El estado de la enfermedad en la ciudad

  // Este es el constructor de la ciudad
  public Ciudad(int idCiudad, String nombre, int x, int y) {
    this.idCiudad = idCiudad; // Asigna el id de la ciudad
    this.nombre = nombre; // Asigna el nombre de la ciudad
    this.posicion_ciudad = new int[]{x, y}; // Asigna la posición de la ciudad como un arreglo de dos enteros
    this.estadoEnfermedad = "Sin datos"; // Asigna el estado de la enfermedad como "Sin datos"
  }

  // Este es un método que actualiza el estado de la enfermedad en la ciudad
  public void actualizarEstadoEnfermedadEnEsaCiudad(String estadoEnfermedad) {
    this.estadoEnfermedad = estadoEnfermedad; // Asigna el nuevo estado de la enfermedad
  }

  // Este es un método que muestra el estado de la enfermedad en la ciudad
  public void mostrarEstadoEnfermedad() {
    System.out.println("El estado de la enfermedad en la ciudad " + this.nombre + " es: " + this.estadoEnfermedad); // Imprime el estado de la enfermedad en la ciudad
  }
}