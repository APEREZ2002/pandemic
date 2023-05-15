// Crear una clase que se llame Vacuna
public class Vacuna {

  // Crear un atributo que almacene el estado de la vacuna como un int
  private int estadoVacuna;
  private boolean completa=false;
  // Crear un constructor que reciba el estado de la vacuna como argumento
  public Vacuna(int estadoVacuna) {
    this.estadoVacuna = estadoVacuna; // Asigna el estado de la vacuna al atributo
    this.completa=completa;
  }


  //muestra si la vacuna esta completa
  public boolean getVacunaCompleta() {
	  return completa;  
  }
  
  //muestra el estado de la vacuna
  public int getEstadoVacuna() {
	  return estadoVacuna;  
  }
  //Asigna un estado de vacuna, para cargarlo en la partida
  public int setEstadoVacuna(int estadoNuevo) {
	  estadoVacuna=estadoNuevo;
	  return estadoVacuna;  
  }
  //comprueba si el estado de la vacuna se a completado, si esta completa cambia completa por true
  public boolean comprobarEstadoVacuna() {
	  if(this.estadoVacuna >= 100) {
		  completa=true;
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