// Crear una clase que se llame Vacuna
public class Vacuna {

  // Crear un atributo que almacene el estado de la vacuna como un int
  private int estadoVacuna;

  // Crear un constructor que reciba el estado de la vacuna como argumento
  public Vacuna(int estadoVacuna) {
    this.estadoVacuna = estadoVacuna; // Asigna el estado de la vacuna al atributo
  }

  // Crear un método que se llame comprobarEstadoVacuna() que no devuelva nada
  public void comprobarEstadoVacuna() {
    // Si el estado de la vacuna es 100
    if (this.estadoVacuna == 100) {
      // Mostrar por pantalla que la vacuna se ha completado
      System.out.println("La vacuna se ha completado.");
    } else {
      // Mostrar por pantalla el estado actual de la vacuna
      System.out.println("El estado actual de la vacuna es: " + this.estadoVacuna + "%");
    }
  }
  
  public int getEstadoVacuna() {
	  return estadoVacuna;  
  }

  public boolean comprovarEstadoVacuna() {
	  if(this.estadoVacuna >= 100) {
		  return true;   
	  }else {
		  return false;  

	  }
  }
  // Crear un método que se llame aumentarEstadoVacuna() que no devuelva nada
  public void aumentarEstadoVacuna() {
    // Si el estado de la vacuna es menor que 100
    if (this.estadoVacuna < 100) {
      // Incrementar el estado de la vacuna en 10
      this.estadoVacuna += 10;
      // Mostrar por pantalla el nuevo estado de la vacuna
      System.out.println("El nuevo estado de la vacuna es: " + this.estadoVacuna + "%");
    } else {
      // Mostrar por pantalla que la vacuna ya está completa
      System.out.println("La vacuna ya está completa.");
    }
  }
}