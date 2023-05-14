
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ConexionBD {

	private static final String USER = "DAW_PNDC22_23_ALAD";
	private static final String PWD = "AA123";
	// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";

	public static void main(String[] args) throws SQLException {
		Connection con = conectarBaseDatos();

	}

	// esta funcion sirve para conectarte a la base de datos
	private static Connection conectarBaseDatos() {
		Connection con = null;

		System.out.println("Intentando conectarse a la base de datos");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PWD);
			System.out.println("Conectados a la base de datos");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}

		return con;
	}

	// esta funcion sirve para iniciar la secuencia de borrado de una partida
	// guardada
	public static void Borrar(String nombre) throws SQLException {
		Connection con = conectarBaseDatos();
		if (con != null) {

			System.out.println("DentroIF");
			borrarPartida(con, nombre);
			verPartidas(con);
			con.close();
		}

	}

	// Esta funcion elimina una partida guardada en base al nombre que le añadas al
	// ejecutarla
	private static void borrarPartida(Connection con, String nombre) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM cargar_Partida WHERE nombre_jugador='" + nombre + "'";
		try {
			Statement st = con.createStatement();
			st.execute(sql);

		} catch (SQLException e) {
			System.out.println("Ha habido un error en el Insert " + e);
		}

	}

	// Esta funcion sirve para iniciar la secuencia de Guardado
	public static void Cargar(Juego juego, ArrayList<UBICACION> ubicaciones, Vacuna vDelta, Vacuna vAlfa, Vacuna vBeta,
			Vacuna vGamma) throws SQLException {
		Connection con = conectarBaseDatos();
		if (con != null) {
			System.out.println("DentroIF");
			insertarGuardado(con, juego, ubicaciones, vDelta, vAlfa, vBeta, vGamma);
			verPartidas(con);
			con.close();
		}

	}

	// Esta funcion sirve para insertar una partida en la base de datos es decir
	// guardar la partida
	public static void insertarGuardado(Connection con, Juego juego, ArrayList<UBICACION> ubicaciones, Vacuna vDelta,
			Vacuna vAlfa, Vacuna vBeta, Vacuna vGamma) {
		Scanner scanner = new Scanner(System.in);

		int turnoActual1 = juego.getTurnoActual();

		int acciones1 = juego.getAccion();

		int brotesActuales1 = juego.getBrotesActuales();

		int estadoVacunaDelta1 = vDelta.getEstadoVacuna();

		int estadoVacunaAlfa1 = vAlfa.getEstadoVacuna();

		int estadoVacunaBeta1 = vBeta.getEstadoVacuna();

		int estadoVacunaGamma1 = vGamma.getEstadoVacuna();

		int infectadasInicio1 = 0;
		int infectadasRonda1 = 0;
		int enfermedadesActivasDerrota1 = 0;
		int brotesDerrota1 = 0;

		String[] ciudades = new String[48];
		int[] posX = new int[48];
		int[] posY = new int[48];
		int[] numEnfer = new int[48];
		boolean[] estado1 = new boolean[48];
		int[] nivelEnfer = new int[48];
		boolean[] aumentar1 = new boolean[48];
		int cont = 0;

		for (UBICACION ubicacion : ubicaciones) { // Pasa por el arraylist Ubicacion y va guardando en arrays la
													// informacion de dicha lista para despues utilizarla en el insert
			ciudades[cont] = ubicacion.getNombreCiudad();
			posX[cont] = ubicacion.getX();
			posY[cont] = ubicacion.getY();
			numEnfer[cont] = ubicacion.getNum();
			estado1[cont] = ubicacion.getEstado();
			nivelEnfer[cont] = ubicacion.getNivelEnfermedad();
			aumentar1[cont] = ubicacion.getAumentar();
			infectadasInicio1 = ubicacion.getParametros()[0];
			infectadasRonda1 = ubicacion.getParametros()[1];
			enfermedadesActivasDerrota1 = ubicacion.getParametros()[2];
			brotesDerrota1 = ubicacion.getParametros()[3];
			cont++;
		}

		String sql = "INSERT INTO cargar_Partida (nombre_jugador, ciudades, Partida) " + "VALUES ('"
				+ juego.getJugador() + "'," + "CiudadArray(";

		for (int i = 0; i < ciudades.length; i++) {
			sql += "Ciudad('" + ciudades[i] + "', " + posX[i] + "," + posY[i] + "," + numEnfer[i] + ",'" + estado1[i]
					+ "'," + nivelEnfer[i] + ",'" + aumentar1[i] + "')";
			if (i != ciudades.length - 1) {
				sql += ",";
			}
		}

		sql += "), Partida(" + turnoActual1 + "," + acciones1 + "," + brotesActuales1 + "," + estadoVacunaDelta1 + ","
				+ estadoVacunaAlfa1 + "," + estadoVacunaBeta1 + "," + estadoVacunaGamma1 + "," + infectadasInicio1 + ","
				+ infectadasRonda1 + "," + enfermedadesActivasDerrota1 + "," + brotesDerrota1 + "))";

		try {
			Statement st = con.createStatement();
			st.execute(sql);

			System.out.println("Partida guardada correctamente.");
			JOptionPane.showMessageDialog(null, "Partida guardada correctamente.", "Guardado de partida",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			System.out.println("Ha habido un error en el Insert " + e);
		}

		scanner.close();

	}

	// Esta funcion sirve para cargar una partida
	public static void cargarPartida(Connection con, String nombre) {
		String[] ciudades = new String[1000];
		int[] posX = new int[1000];
		int[] posY = new int[1000];
		int[] numEnfer = new int[1000];
		boolean[] estado1 = new boolean[1000];
		int[] nivelEnfer = new int[1000];
		boolean[] aumentar1 = new boolean[1000];

		String nombreJugador1 = nombre;
		int turnoActual1 = 0;
		int acciones1 = 0;
		int brotesActuales1 = 0;
		int estadoVacunaDelta1 = 0;
		int estadoVacunaAlfa1 = 0;
		int estadoVacunaBeta1 = 0;
		int estadoVacunaGamma1 = 0;
		int infectadasInicio1 = 0;
		int infectadasRonda1 = 0;
		int enfermedadesActivasDerrota1 = 0;
		int brotesDerrota1 = 0;
		// se ejecuta el select necesario para ver todas las partidas
		String sql = "SELECT t.nombre_jugador, t.Partida.turnoActual,t.Partida.acciones,t.Partida.brotesActuales,t.Partida.estadoVacunaDelta,t.Partida.estadoVacunaAlfa,t.Partida.estadoVacunaBeta,t.Partida.estadoVacunaGamma,t.Partida.InfectadasInicio,t.Partida.InfectadasRonda,t.Partida.EnfermedadesActivasDerrota,t.Partida.BrotesDerrota,c.nombreCiudad, c.posicionX, c.posicionY, c.numEnf, c.estado, c.nivelEnf, c.aumentar FROM cargar_Partida t, TABLE(t.ciudades) c";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.isBeforeFirst()) {
				FileWriter fileWriter = new FileWriter("Guardado.txt");
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				int cont = 0;
				while (rs.next()) {

					String nombreJugador = rs.getString("nombre_jugador");
					int turnoActual = rs.getInt("Partida.turnoActual");
					int acciones = rs.getInt("Partida.acciones");
					int brotesActuales = rs.getInt("Partida.brotesActuales");
					int estadoVacunaDelta = rs.getInt("Partida.estadoVacunaDelta");
					int estadoVacunaAlfa = rs.getInt("Partida.estadoVacunaAlfa");
					int estadoVacunaBeta = rs.getInt("Partida.estadoVacunaBeta");
					int estadoVacunaGamma = rs.getInt("Partida.estadoVacunaGamma");
					int infectadasInicio = rs.getInt("Partida.InfectadasInicio");
					int infectadasRonda = rs.getInt("Partida.InfectadasRonda");
					int enfermedadesActivasDerrota = rs.getInt("Partida.EnfermedadesActivasDerrota");
					int brotesDerrota = rs.getInt("Partida.BrotesDerrota");
					String nombreCiudad = rs.getString("nombreCiudad");
					int posicionX = rs.getInt("posicionX");
					int posicionY = rs.getInt("posicionY");
					int numEnf = rs.getInt("numEnf");
					String estado = rs.getString("estado");
					int nivelEnf = rs.getInt("nivelEnf");
					String aumentar = rs.getString("aumentar");
					// Si el nombre insertado al ejecutar la funcion es el que esta mirando en la
					// base de datos lo guarda y sino lo omite
					if (nombreJugador.equals(nombre)) {
						nombreJugador1 = nombreJugador;
						turnoActual1 = turnoActual;
						acciones1 = acciones;
						brotesActuales1 = brotesActuales;
						estadoVacunaDelta1 = estadoVacunaDelta;
						estadoVacunaAlfa1 = estadoVacunaAlfa;
						estadoVacunaBeta1 = estadoVacunaBeta;
						estadoVacunaGamma1 = estadoVacunaGamma;
						infectadasInicio1 = infectadasInicio;
						infectadasRonda1 = infectadasRonda;
						enfermedadesActivasDerrota1 = enfermedadesActivasDerrota;
						brotesDerrota1 = brotesDerrota;

						ciudades[cont] = nombreCiudad;
						posX[cont] = posicionX;
						posY[cont] = posicionY;
						numEnfer[cont] = numEnf;
						if (estado == "true") {
							estado1[cont] = true;
						} else {
							estado1[cont] = false;
						}

						nivelEnfer[cont] = nivelEnf;

						if (aumentar == "true") {
							aumentar1[cont] = true;
						} else {
							aumentar1[cont] = false;
						}
						cont++;
					}

				}
				bufferedWriter.write("-");
				bufferedWriter.newLine();

				bufferedWriter.write("Partida- " + nombreJugador1 + "-" + turnoActual1 + "-" + acciones1 + "-"
						+ brotesActuales1 + "-" + estadoVacunaDelta1 + "-" + estadoVacunaAlfa1 + "-" + estadoVacunaBeta1
						+ "-" + estadoVacunaGamma1);
				bufferedWriter.newLine();
				bufferedWriter.write("Parametros- " + infectadasInicio1 + "-" + infectadasRonda1 + "-"
						+ enfermedadesActivasDerrota1 + "-" + brotesDerrota1);

				for (int i = 0; i < 48; i++) {
					bufferedWriter.newLine();
					bufferedWriter.write("UBICACION " + ciudades[i] + "-" + posX[i] + "-" + posY[i] + "-" + numEnfer[i]
							+ "-" + estado1[i] + "-" + nivelEnfer[i] + "-" + aumentar1[i]);
				}
				cargar_Partida frame = new cargar_Partida(nombre, turnoActual1, acciones1, brotesActuales1,
						estadoVacunaDelta1, estadoVacunaAlfa1, estadoVacunaBeta1, estadoVacunaGamma1, infectadasInicio1,
						infectadasRonda1, enfermedadesActivasDerrota1, brotesDerrota1, ciudades, posX, posY, numEnfer,
						estado1, nivelEnfer, aumentar1);
				frame.setVisible(true);
				bufferedWriter.newLine();
				bufferedWriter.write("-");

				bufferedWriter.close();
				fileWriter.close();

				System.out.println("Los datos se han guardado correctamente en el archivo 'Guardado.txt'");
			} else {
				System.out.println("No he encontrado nada");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha habido un error al guardar los datos en el archivo 'ranking.txt'");
		}

	}
	//Esta funcion sirve para guardar en un txt las partidas disponibles para carrgar, es decir las que estan en la base de datos
	public static void verPartidas(Connection con) {

		String sql = "SELECT DISTINCT nombre_jugador FROM cargar_Partida";
		System.out.println(sql);

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.isBeforeFirst()) {
				FileWriter fileWriter = new FileWriter("Partidas.txt");
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				while (rs.next()) {
					String nombreJugador = rs.getString("nombre_jugador");
					System.out.println(nombreJugador);

					bufferedWriter.write("Partida- " + nombreJugador);
					bufferedWriter.newLine();
				}
				bufferedWriter.close();
				fileWriter.close();
			} else {
				FileWriter fileWriter = new FileWriter("Partidas.txt");
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write("No hay partidas guardadas.");
				bufferedWriter.close();
				fileWriter.close();
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	//Esta funcion inicia el procedimiento para guardar una partida finalizada y que se guarde en el ranking
	public static void Ranking(Juego juego, String nombre, int puntuacion, boolean victoria) throws SQLException {
		Connection con = conectarBaseDatos();
		if (con != null) {
			System.out.println("DentroIF");
			insertarRanking1(con, juego, nombre, puntuacion, victoria);
			selectRanking1(con, juego, nombre, puntuacion, victoria);
			con.close();
		}
	}
	//Esta funcion sirve para insertar los datos necesarios de una partida FINALIZADA para pasarlo a la base de datos
	public static void insertarRanking1(Connection con, Juego juego, String nombre, int puntuacion, boolean victoria) {
		Scanner scanner = new Scanner(System.in);
		int puntuaciones;
		String estado;

		puntuaciones = puntuacion;

		int rondasTotales = juego.getTurnoActual();

		String nombreJugador = nombre;

		String fecha = "01/02/03";

		if (victoria == true) {
			estado = "victoria";
		} else {
			estado = "derrota";
		}

		int idPartida = 999;

		String sql = "INSERT INTO RANKING (ID_PARTIDA, RONDAS_TOTALES, NOMBRE_JUGADOR, FECHA, ESTADO, PUNTUACIONES) "
				+ "VALUES (" + idPartida + ", " + rondasTotales + ", '" + nombreJugador + "', TO_DATE('" + fecha
				+ "', 'DD/MM/YY'), '" + estado + "', " + puntuaciones + ")";

		try {
			Statement st = con.createStatement();
			st.execute(sql);
			System.out.println("Partida registrada correctamente en el ranking.");
		} catch (SQLException e) {
			System.out.println("Ha habido un error en el Insert " + e);
		}
		scanner.close();

	}
	//Esa funcion guarda en un txt las 5 mejores partidas para que se muestren en el Ranking
	public static void selectRanking1(Connection con, Juego juego, String nombre, int puntuacion, boolean victoria) {
		String sql = "SELECT * FROM RANKING ORDER BY PUNTUACIONES DESC";
		int cont = 0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.isBeforeFirst()) {
				FileWriter fileWriter = new FileWriter("ranking.txt");
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				while (rs.next()) {
					int idPartida = rs.getInt("ID_PARTIDA");
					int rondasTotales = rs.getInt("RONDAS_TOTALES");
					String nombreJugador = rs.getString("NOMBRE_JUGADOR");
					String fecha = rs.getString("FECHA");
					String estado = rs.getString("ESTADO");
					int puntuaciones = rs.getInt("PUNTUACIONES");

					if (cont < 5) {
						cont++;
						bufferedWriter.write(cont + "º-" + nombreJugador + "-->" + puntuaciones + "(" + estado + ")");
						bufferedWriter.newLine();
						bufferedWriter.newLine();

					}

				}

				bufferedWriter.close();
				fileWriter.close();

				System.out.println("Los datos se han guardado correctamente en el archivo 'ranking.txt'");

			} else {
				System.out.println("No he encontrado nada");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha habido un error al guardar los datos en el archivo 'ranking.txt'");
		}

	}
	//Esta funcion inicia la secuencia de carga de una partida en base al nombre insertado
	public static void Cargar(String nombre) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = conectarBaseDatos();
		if (con != null) {

			System.out.println("DentroIF");
			verPartidas(con);
			cargarPartida(con, nombre);
			con.close();
		}
	}

}
