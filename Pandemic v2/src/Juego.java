// Crear una clase en Java llamada Juego
public class Juego {

	// Declarar las variables de instancia
	private int turnoActual;
	private String jugador;

	private int brotesActuales;
	private int accion;

	// Crear un constructor que reciba los valores iniciales de las variables
	public Juego(int turnoActual, String jugador, String enfermedad, String enfermedad2, String enfermedad3,
			String enfermedad4, int infeccion, int brotesActuales, int accion) {
		this.turnoActual = turnoActual;
		this.jugador = jugador;
		this.brotesActuales = brotesActuales;
		this.accion = accion; 
	}

//Crea un método get que devuelva el valor de la variable turnoActual
	public int getTurnoActual() {
		return turnoActual;
	}
//Crea un metodo que suma una ronda
	public int NuevaRonda() {
		turnoActual++;
		return turnoActual;
	}
//Crea un metodo donde pudedes igualar la ronda al numero insertado
	public int setRonda(int Ronda) {
		turnoActual = Ronda;
		return turnoActual;
	}

//Crea un método get que devuelva el valor de la variable turnoActual
	public int getAccion() {
		return accion;
	}
//Crea un metodo que suma una accion
	public int sumarAccion() {
		accion++;
		return accion;

	}
//Crea un metodo que añades la accion que quierers que tenga
	public int setAccion(int accion1) {
		accion = accion1;
		return accion;
	}
	
	
	
//Crer un método get que devuelva el valor de la variable jugador
	public String getJugador() {
		return jugador;
	}
//Crea un metodo que guardas el nombre de jugador que quieres
	public String setJugador(String nuevoJugador) {
		jugador = nuevoJugador;
		return jugador;
	}

//Crea un método get que devuelva el valor de la variable brotesActuales
	public int getBrotesActuales() {
		return brotesActuales;
	}

//Crea un método get que devuelva el valor de la variable brotesActuales
	public int aumentarBrotes() {
		brotesActuales++;
		return brotesActuales;
	}

}