import java.util.Scanner;

public class Prueba_Clases_Nuevas {
        
	public static void Nuevas_clases(String[] args) {
		// TODO Auto-generated method stub
		    Scanner teclado = new Scanner(System.in);
			System.out.println("¿Cual es tu nombre?");
			String jugador1=teclado.next();
		    Juego juego = new Juego(0,jugador1,"Delta","Alfa","Beta","Gamma",9,0);
		    Jugador jugador = new Jugador(jugador1,juego);
			Enfermedad Delta = new Enfermedad("Delta", "Rojo", "Nueva York", 0, juego,0);
			Enfermedad Alfa = new Enfermedad("Alfa", "Azul", "Madrid", 0, juego,0);
			Enfermedad Beta = new Enfermedad("Beta", "Amarillo", "Corea del Norte", 0, juego,0);
			Enfermedad Gamma = new Enfermedad("Gamma", "Verde", "Paris", 0, juego,0);
			int game=0;
			
			System.out.println("¿que enfermedad quieres utiliar?");
			System.out.println("1-Delta, 2-Alfa, 3-Beta, 4-Gamma");
			game=teclado.nextInt();
			
			do {
			System.out.println(Delta.getNombre());
			}while(game!=0);
	}

}
 