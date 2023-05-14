public class Ciudad {
	private int idCiudad;
	private String[] nombre;
	private int[] estadoEnfermedad;
	private Ciudad[] ciudadesConectadas;

	public Ciudad(int idCiudad, String[] nombres, int[] estadoEnfermedad, Ciudad[] ciudadesConectadas) {
		this.idCiudad = idCiudad;
		this.nombre = nombres;
		this.estadoEnfermedad = estadoEnfermedad;
		this.ciudadesConectadas = ciudadesConectadas;
	}

	public int getIdCiudad() {// esta funcion retorna idCiudad
		return idCiudad;
	}

	public String[] getNombre() {// esta funcion retorna el nombre de la ciudad
		return nombre;
	}

	public int[] getEstadoEnfermedad() {// esta funcion retorna el estado de enferemedad de esa ciudad
		return estadoEnfermedad;
	}

	public Ciudad[] getCiudades() {
		return ciudadesConectadas; // esta funcion retorna las colindantes de esa ciudad
	}

	public void setCiudades(Ciudad[] ciudades) {// esta funcion añades las colindantes de esa ciudad
		this.ciudadesConectadas = ciudades;
	}

}
