import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ConexionBD {

	private static final String USER = "DAW_PNDC22_23_ALAD";
	private static final String PWD = "AA123";
	// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";

	public static void main(String[] args) throws SQLException {
		Connection con = conectarBaseDatos();

		if (con != null) {
			System.out.println("DentroIF");
			insertarRanking(con);
			selectRanking(con);
			con.close();
		}

	}

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

	private static void selectRanking(Connection con) {
		String sql = "SELECT r.* FROM RANKING r ORDER BY r.ESTADO DESC, r.RONDAS_TOTALES ASC";

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
					
					bufferedWriter.write("ID de Partida: " + idPartida);
					bufferedWriter.newLine();
					bufferedWriter.write("Rondas Totales: " + rondasTotales);
					bufferedWriter.newLine();
					bufferedWriter.write("Nombre del Jugador: " + nombreJugador);
					bufferedWriter.newLine();
					bufferedWriter.write("Fecha: " + fecha); 
					bufferedWriter.newLine();
					bufferedWriter.write("Estado: " + estado);
					bufferedWriter.newLine();
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


private static void insertarRanking(Connection con) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Introduce el ID de la partida (número del 1 al 99):");
		int idPartida = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Introduce el número de rondas totales (número del 1 al 99):");
		int rondasTotales = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Introduce el nombre del jugador (máximo 100 caracteres):");
		String nombreJugador = scanner.nextLine();
		
		System.out.println("Introduce la fecha en formato 'YYYY-MM-DD':");
		String fecha = scanner.nextLine();
		
		System.out.println("Introduce el estado de la partida ('Victoria' o 'Derrota'):");
		String estado = scanner.nextLine();
		
		String sql = "INSERT INTO RANKING (ID_PARTIDA, RONDAS_TOTALES, NOMBRE_JUGADOR, FECHA, ESTADO) "
				+ "VALUES (" + idPartida + ", " + rondasTotales + ", '" + nombreJugador + "', TO_DATE('" + fecha + "', 'YYYY-MM-DD'), '" + estado + "')";
		
	try {
		Statement st = con.createStatement();
		st.execute(sql);
		
		System.out.println("Partida registrada correctamente en el ranking.");
	} catch (SQLException e) {
		System.out.println("Ha habido un error en el Insert " + e);
	}
	
	scanner.close();
}
}
		
		
