import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import java.io.FileWriter;
import Botones.boton_Ciudad;
import Botones.boton_Curar;
import Botones.boton_Guardar;
import Paneles.panel_InsertarNombre;
import Paneles.Bienvenido;
import Paneles.VacunaProgressBar;
import Paneles.audio;

public class mapa extends JFrame {
	//Inciiarlizamos la variantes que se van a utilizar a lo largo del programa
	public String Datos;
	public String ciudad;
	public Juego juego = new Juego(1, " ", "Delta", "Alfa", "Beta", "Gamma", 9, 0, 0);
	public Vacuna vDelta = new Vacuna(0);
	public Vacuna vBeta = new Vacuna(0);
	public Vacuna vAlfa = new Vacuna(0);
	public Vacuna vGamma = new Vacuna(0);
	public static audio reproductor = new audio();
	public int ejecuciones = 0;
	public boolean ronda = false;
	public String[] colindantesArray;
	private boolean fin = false;

	// NuevaRonda();
	public mapa() {
		super("Mapa Pandemic");
		setResizable(false); // La ventana no se puede redimensionar
		setUndecorated(true); // La ventana no tiene barra superior
		// Obtener la lista de ubicaciones
		ArrayList<UBICACION> ubicaciones = UBICACION_MANAGER.crearUbicaciones();
		reproductor.musica(".\\src\\menu_Principal.wav");
		juego.setJugador(nombre_Jugador());//pedimos el nombre del jugador a traves de la funcion nombre_Jugador
		Bienvenido bienvenida = new Bienvenido(juego.getJugador());//inicializamos el panel Bienvenido
		bienvenida.mostrarPopup();//mostramos el popup
		reproductor.detenerMusica();
		reproductor.musica(".\\src\\partida.wav");//rerpoduce la musica de la partida
		reproductor.ajustarVolumen(20);
		// Crear el panel donde pondremos el mapa
		JPanel panelImagen = new JPanel(null) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					Image imagen = ImageIO.read(new File(".\\src\\mapa_mundo.png"));
					g.drawImage(imagen, 0, 0, this);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		};
		panelImagen.setBackground(new Color(88, 159, 185)); // Esto es para añadirle un color al fondo del panel

		JPanel panelInferior = new JPanel();
		JLabel labelResultado = new JLabel("");
		panelInferior.add(labelResultado);
		panelInferior.setBackground(new Color(88, 159, 185)); // Esto es para añadirle un color al fondo del panel inferior
		add(panelInferior, BorderLayout.CENTER);
		//añadimos un panel superor donde se mostraran los datos(nombre del jugador, la ronda y las acciones actuales)
		JLabel labelSuperior = new JLabel("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
		+ "| Acciones realizadas=" + juego.getAccion() + "/4");
		labelSuperior.setFont(new Font("Helvetica", Font.BOLD, 24));//añadimos una fuente y un tamaño de letra
		labelSuperior.setBackground(new Color(88, 159, 185)); // Esto es para añadirle un color al fondo del panel inferior
		labelSuperior.setForeground(new Color(88, 159, 185));	// Esto es para añadirle un color al foreground del panel inferior

		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0)); // Agrega un espacio en blanco de 20
																				// píxeles en la parte superior y 50
																				// píxeles a la izquierda
		panelSuperior.add(labelSuperior, BorderLayout.CENTER);

		add(panelSuperior, BorderLayout.NORTH);

		// Crea los paneles arriba y abajo de la parte derecha del juego
		JPanel panelARRIBA = new JPanel();
		JPanel panelABAJO = new JPanel();

		// Crea el contenido del panel superior y agregarlo a un JScrollPane
		JTextArea texto = new JTextArea(40, 20);
		texto.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(texto);
		panelARRIBA.add(scrollPane);
		// Crea los botones que utilizaremos y agregarlos al panel inferior
		JLabel texto_botones = new JLabel("PANDEMIC");
		boton_Guardar boton_1 = new boton_Guardar("GUARDAR");
		boton_Guardar boton_2 = new boton_Guardar("SALIR");
		panelABAJO.add(texto_botones);
		panelABAJO.add(boton_1);
		panelABAJO.add(boton_2);
		
		// Hacer que el JTextArea baje automáticamente al final del contenido
		texto.setCaretPosition(texto.getDocument().getLength());
		
		//añade la funcion al boton de guardar
		boton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reproductor.sonido(".\\src\\Select1.wav");
				int opcion = JOptionPane.showConfirmDialog(null,
						"Jugador: " + juego.getJugador() + " ¿Estás seguro que quieres guardar?", "Confirmación",//Preguntamos al jugador si esta seguro con su accion
						JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					try {
						reproductor.sonido(".\\src\\Guardar.wav");
						ConexionBD.Cargar(juego, ubicaciones, vDelta, vAlfa, vBeta, vGamma);//iniciamos el guardado de los datos mas importantes
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});
		
		//añade la funcion al boton de salir
		boton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reproductor.sonido(".\\src\\Select1.wav");
				reproductor.detenerMusica();
				dispose(); //cerramos la ventana actual
				new menu_Principal(); // crea una instancia de la clase menu_Principal

			}

		});

		// Add a gray border to the top panel
		panelARRIBA.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(88, 159, 185)));

		// Crear el JSplitPane y agregar los paneles
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, /* true, */ panelARRIBA, panelABAJO);
		splitPane.setDividerLocation(0.8);
		splitPane.setDividerSize(0);

		// Crear el panel de los botones y los progresos
		JPanel panelBotones = new JPanel(new GridLayout(2, 4)); // dos filas y cuatro columnas
		panelBotones.setBackground(new Color(88, 159, 185));
		VacunaProgressBar delta = new VacunaProgressBar(1, "delta", vDelta.getEstadoVacuna());
		VacunaProgressBar alfa = new VacunaProgressBar(1, "alfa", vAlfa.getEstadoVacuna());
		VacunaProgressBar beta = new VacunaProgressBar(1, "beta", vBeta.getEstadoVacuna());
		VacunaProgressBar gamma = new VacunaProgressBar(1, "gamma", vGamma.getEstadoVacuna());

		// Establecer el valor inicial de cada barra de progreso
		delta.getProgressBar().setValue(vDelta.getEstadoVacuna());
		alfa.getProgressBar().setValue(vAlfa.getEstadoVacuna());
		beta.getProgressBar().setValue(vBeta.getEstadoVacuna());
		gamma.getProgressBar().setValue(vGamma.getEstadoVacuna());

		// Agregar los botones y las barras de progreso al panel
		panelBotones.add(delta);
		panelBotones.add(alfa);
		panelBotones.add(beta);
		panelBotones.add(gamma);
		
		//Le damos funcion al boton de la vacuna delta
		delta.getBotonVacunar().addActionListener(e -> {
			vDelta.aumentarEstadoVacuna(); // aumentar el valor actual de la barra de progreso en el numero que tenga asignado
			delta.getProgressBar().setValue(vDelta.getEstadoVacuna()); // actualizar visualmente la barra de progreso
			reproductor.sonido(".\\src\\Select1.wav");
			texto.append("Desarrolla la vacuna Delta" + "\n");						//Manda el texto al panel creado anteriormente
			texto.append("Vacuna Delta->" + vDelta.getEstadoVacuna() + "%" + "\n"); //

			if (vDelta.getEstadoVacuna() >= 100) {//Si la vacuna es de 100 o mas, se realiza el interior del if
				nueva_Ronda(texto);

				labelSuperior.setText("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
						+ "| Acciones realizadas=" + juego.getAccion() + "/4");
				reproductor.sonido(".\\src\\Select2.wav");
				reproductor.ajustarVolumen(20);
				delta.getProgressBar().setForeground(Color.BLUE);
				JOptionPane.showMessageDialog(this, "Delta ha completado su progreso", "Vacuna Delta completada",
						JOptionPane.INFORMATION_MESSAGE);
				comprobarEstado(delta.getProgressBar(), alfa.getProgressBar(), beta.getProgressBar(),
						gamma.getProgressBar());
				delta.getBotonVacunar().setEnabled(false);//se deshabilita el boton
				vDelta.comprobarEstadoVacuna();

			} else { //si no lo es, todo continua con normalidad
				nueva_Ronda(texto); // aumentar el número de ronda en 1
				labelSuperior.setText("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
						+ "| Acciones realizadas=" + juego.getAccion() + "/4");
				vDelta.comprobarEstadoVacuna();
			}
			ronda = true;
			actualizarBotones(panelImagen, ubicaciones, labelSuperior, texto);
		});
		//Le damos funcion al boton de la vacuna alfa
		alfa.getBotonVacunar().addActionListener(e -> {
			vAlfa.aumentarEstadoVacuna(); // aumentar el valor actual de la barra de progreso en el numero que tenga asignado
			alfa.getProgressBar().setValue(vAlfa.getEstadoVacuna()); // actualizar visualmente la barra de progreso
			reproductor.sonido(".\\src\\Select1.wav");
			
			texto.append("Desarrolla la vacuna Alfa" + "\n");
			texto.append("Vacuna Alfa->" + vAlfa.getEstadoVacuna() + "%" + "\n");
			
			if (vAlfa.getEstadoVacuna() >= 100) {
				nueva_Ronda(texto);
				labelSuperior.setText("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
				+ "| Acciones realizadas=" + juego.getAccion() + "/4");
				reproductor.sonido(".\\src\\Select2.wav");
				reproductor.ajustarVolumen(20);
				alfa.getProgressBar().setForeground(Color.RED);
				JOptionPane.showMessageDialog(this, "Alfa ha completado su progreso", "Vacuna Alfa completada",
						JOptionPane.INFORMATION_MESSAGE);

				comprobarEstado(delta.getProgressBar(), alfa.getProgressBar(), beta.getProgressBar(),
						gamma.getProgressBar());
				alfa.getBotonVacunar().setEnabled(false);
				vAlfa.comprobarEstadoVacuna();
			} else {
				nueva_Ronda(texto);
				labelSuperior.setText("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
						+ "| Acciones realizadas=" + juego.getAccion() + "/4");
				vAlfa.comprobarEstadoVacuna();
			}

			ronda = true;
			actualizarBotones(panelImagen, ubicaciones, labelSuperior, texto);//Ejecuta la funcion actualizarBotones
		});
		//Le damos funcion al boton de la vacuna beta
		beta.getBotonVacunar().addActionListener(e -> {
			vBeta.aumentarEstadoVacuna(); // aumentar el valor actual de la barra de progreso en el numero que tenga asignado
			beta.getProgressBar().setValue(vBeta.getEstadoVacuna()); // actualizar visualmente la barra de progreso
			reproductor.sonido(".\\src\\Select1.wav");
			texto.append("Desarrolla la vacuna Beta" + "\n");
			texto.append("Vacuna Beta->" + vBeta.getEstadoVacuna() + "%" + "\n");
			if (vBeta.getEstadoVacuna() >= 100) {

				nueva_Ronda(texto);
				labelSuperior.setText("Numero de Ronda " + juego.getTurnoActual());
				reproductor.sonido(".\\src\\Select2.wav");
				reproductor.ajustarVolumen(20);
				beta.getProgressBar().setForeground(Color.GREEN);
				JOptionPane.showMessageDialog(this, "Beta ha completado su progreso", "Vacuna Beta completada",
						JOptionPane.INFORMATION_MESSAGE);

				comprobarEstado(delta.getProgressBar(), alfa.getProgressBar(), beta.getProgressBar(),
						gamma.getProgressBar());
				beta.getBotonVacunar().setEnabled(false);
				vBeta.comprobarEstadoVacuna();
			} else {
				nueva_Ronda(texto); // aumentar el número de ronda en 1
				labelSuperior.setText("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
				+ "| Acciones realizadas=" + juego.getAccion() + "/4");
				vBeta.comprobarEstadoVacuna();
			}

			ronda = true;
			actualizarBotones(panelImagen, ubicaciones, labelSuperior, texto);//Ejecuta la funcion actualizarBotones
		});
		//Le damos funcion al boton de la vacuna gamma
		gamma.getBotonVacunar().addActionListener(e -> {
			vGamma.aumentarEstadoVacuna(); // aumentar el valor actual de la barra de progreso en el numero que tenga asignado
			gamma.getProgressBar().setValue(vGamma.getEstadoVacuna()); // actualizar visualmente la barra de progreso
			reproductor.sonido(".\\src\\Select1.wav");
			texto.append("Desarrolla la vacuna Gamma" + "\n");
			texto.append("Vacuna Gamma->" + vGamma.getEstadoVacuna() + "%" + "\n");
			if (vGamma.getEstadoVacuna() >= 100) {

				nueva_Ronda(texto);

				labelSuperior.setText("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
						+ "| Acciones realizadas=" + juego.getAccion() + "/4");
				reproductor.sonido(".\\src\\Select2.wav");
				reproductor.ajustarVolumen(20);
				gamma.getProgressBar().setForeground(Color.YELLOW);
				JOptionPane.showMessageDialog(this, "La vacuna Gamma ha completado su progreso",
						"Vacuna Gamma completada", JOptionPane.INFORMATION_MESSAGE);
				comprobarEstado(delta.getProgressBar(), alfa.getProgressBar(), beta.getProgressBar(),
						gamma.getProgressBar());
				gamma.getBotonVacunar().setEnabled(false);
				vGamma.comprobarEstadoVacuna();
			} else {
				nueva_Ronda(texto); // aumentar el número de ronda en 1
				labelSuperior.setText("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
						+ "| Acciones realizadas=" + juego.getAccion() + "/4");
				vGamma.comprobarEstadoVacuna();
			}

			ronda = true;
			actualizarBotones(panelImagen, ubicaciones, labelSuperior, texto);//Ejecuta la funcion actualizarBotones
		});


		// Agregar los paneles al JFrame
		getContentPane().add(panelImagen, BorderLayout.WEST);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		getContentPane().add(splitPane, BorderLayout.EAST);

		////////////

		infectarRonda(ubicaciones, panelImagen, labelSuperior, texto);

		// Configurar el frame

		getContentPane().add(panelImagen, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Abrir en pantalla completa
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);

		//añade el textoo de ronda 1 al panel del lateral derecho
		texto.append("Ronda "+juego.getTurnoActual() + "\n");
		texto.append("................" + "\n");
		
	}

	//Esta funcion se encarga de sumar una ronda añadir el texto de nueva ronda el panel lateral
	private void nueva_Ronda(JTextArea texto) {
		// Incrementar la ronda del juego
		juego.NuevaRonda();
		texto.append("Ronda "+juego.getTurnoActual() + "\n");
		texto.append("................" + "\n");
		// Hacer que el JTextArea baje automáticamente al final del contenido
		texto.setCaretPosition(texto.getDocument().getLength());
	}
	//Esta funcion se encarga de elimnar todo y rehacerlo para mostrar los botones actualizados al momento de la partida en el que esten
	public void actualizarBotones(JPanel panelImagen, ArrayList<UBICACION> ubicaciones, JLabel labelSuperior,
			JTextArea texto) { // Remove all the buttons from thepanelImagen
		panelImagen.removeAll(); // Repaint the panelImagen to reflect thechanges
		panelImagen.repaint();
		if (ronda == true) { //Si es la primera ronda entrara aqui, ya que el valor por defecto de ronda es true, y se hara false para que no entre en ningun momento de la partida
			infectarRonda(ubicaciones, panelImagen, labelSuperior, texto);
			ronda = false;
		} else { // a partir de la prmera ronda entrara aqui
			infectarRonda(ubicaciones, panelImagen, labelSuperior, texto);

		}

	}
	//Esta funcion se encarga de la creacon de los botones y de sus funcionalidades y tambien de todo el proceso del juego en si
	private void infectarRonda(ArrayList<UBICACION> ubicaciones, JPanel panelImagen, JLabel labelSuperior,
			JTextArea texto) {
		// TODO Auto-generated method stub
		int ciudades_Curadas = 0;
		int ciudades_Infectadas = 0;
		int suma = 0;
		int brotes_Derrota = 0;
		int contador=1;
		
		for (UBICACION ubicacion : ubicaciones) {//inicia un bucle que pasa por toda la arrayList de ubicacion para crear los botones
			// Crear el panel de la ciudad y agregarlo al JPopupMenu
			JPanel panelCiudad = new JPanel(new GridLayout(2, 1));
			panelCiudad.setPreferredSize(new Dimension(300, 200));
			JLabel labelCiudad = new JLabel();
			labelCiudad.setFont(new Font("Arial", Font.PLAIN, 12));
			labelCiudad.setHorizontalAlignment(JLabel.CENTER);
			labelCiudad.setVerticalAlignment(JLabel.CENTER);
			panelCiudad.add(labelCiudad);
			boton_Curar botonCurar = new boton_Curar(ubicacion.getNombreCiudad());
			panelCiudad.add(botonCurar);
			JPopupMenu popupMenu = new JPopupMenu();
			popupMenu.add(panelCiudad);

			int x = ubicacion.getX();
			int y = ubicacion.getY();
			int enf = ubicacion.getNivelEnfermedad();
	
			if (ronda == false) {//si es la primera ronda, entrara en este if, que es para que se realice la primera infeccion, con los parametos insertados en los ajustes
				if (ejecuciones < ubicacion.getParametros()[0]) {
					boolean inf = ubicacion.generarEstado(ejecuciones);//utiliza la funcion para generar un estado aleatorio

					if (inf == true && ejecuciones < ubicacion.getParametros()[0]) {// si el estado es true y no se a superado el numero de infecciones pedidas por el usuario, se infectara esa ciudad
						ejecuciones++;
						ubicacion.setNivelEnfermedad(ubicacion.sumarNivelEnfermedad(enf));
					}
					
					if((48-contador)==ubicacion.parametros[0]-ciudades_Infectadas) {
						ubicacion.setNivelEnfermedad(ubicacion.sumarNivelEnfermedad(enf));	
					}

				}

			} else { //si ya a pasado la primera fase de infeccion entrara aqui omitiendo el anterior
				if (ubicacion.getEstado() == false) {//si la ciudad del bucle no esta infectada, entrara aqui
					if (suma < ubicacion.getParametros()[1]) {//si el numero de infeccion por ronda aum no se a superado se generara un estado aleatorio
						boolean inf = ubicacion.generarEstado1(suma);
						if (inf == true && suma < ubicacion.getParametros()[1]) {
							suma++;
							ubicacion.setNivelEnfermedad(ubicacion.sumarNivelEnfermedad(enf));
							ciudades_Infectadas++;
						}
					}
				} else if (ubicacion.getEstado() == true && ubicacion.broteRealizado() == false) {//a este if entrara si la ciudad ya esta infectada y no a realizado ningun brote, es decir, que es o 3 o inferior
					ciudades_Infectadas++;//suma al int de ciduades infectadas para comprobar mas adelante si la cantidad supera la insertdad en los ajustes
					if (suma < ubicacion.getParametros()[1]) {
						suma++;
						ubicacion.sumarNivelEnfermedad(enf);
					}
					if (ciudades_Infectadas >= ubicacion.getParametros()[2] && fin == false) {// a este if entrara si la cantidad de ciudades infectadas supera la pedidad por el usuario

						JOptionPane.showMessageDialog(null, "Demasiadas ciudades infectadas ¡Fin del juego!");//muestra el mensaje de que perdiste
						// Crear una instancia de la nueva clase
						reproductor.detenerMusica(); //detiene la musica
						Final.inciar_Fin("BadEndingInfeccion.txt", ".\\src\\BadEnding.wav");//inicia la funcion de final, con el texto y cancion correspondiente
						dispose();// cierra la ventana actual
						fin = true;//hace que el fin sea true por lo que no puede volver a entrar(al ser un bucle entra la cantidad de veces que le resten al bucle, y esto arreglaba ese error)
						Ranking(false);//inicia la funcion ranking añadiendo que se perdio la partida
					}
				} else if (ubicacion.getEstado() == true && ubicacion.broteRealizado() == true) {// sel brote es true de la ciudad que esta en ese momento y esta infectada, solo suma 1 y reaiza la misma comprovacion
					ciudades_Infectadas++;

					if (ciudades_Infectadas > ubicacion.getParametros()[2] && fin == false) {
						JOptionPane.showMessageDialog(null, "Demasiadas ciudades infectadas ¡Fin del juego!");
						reproductor.detenerMusica();
						dispose();
						Final.inciar_Fin("BanEndingInfeccion.txt", ".\\src\\BadEnding.wav");
						fin = true;
						Ranking(false);
					}
				}

			}
			//Comienza la creacion de los botones con sus respectivos colores
			if (ubicacion.getNum() == 0 && ubicacion.getEstado() == true) { //si el numero de enferemedad asignado es 0(Delta) y esta activa la enfermedad entra aqui
				boton_Ciudad botonCiudadAzul = new boton_Ciudad();
				botonCiudadAzul.setBounds(x, y, 50, 30);
				botonCiudadAzul.setText("Ciudad");
				botonCiudadAzul.setBackground(Color.BLUE);

				// Cargar el icono correspondiente
				ImageIcon icono = new ImageIcon(".\\src\\virus_1.png");
				botonCiudadAzul.setIcon(icono); // Asignar el icono al botón

				botonCiudadAzul.setVerticalTextPosition(JButton.CENTER);
				botonCiudadAzul.setHorizontalTextPosition(JButton.CENTER);
				panelImagen.add(botonCiudadAzul);
				
				//Cuando clicas sobre la ciudad se abre el PopUp con los datos de la ciudad
				botonCiudadAzul.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Datos = GestionCiudades.mostrarDatos(ubicacion.getNombreCiudad(),
								ubicacion.getNivelEnfermedad(), ubicacion.getNombre());
						labelCiudad.setText("<html>" + Datos.replaceAll("\n", "<br>") + "</html>");
						popupMenu.show(botonCiudadAzul, 0, botonCiudadAzul.getHeight());
					}
				});
				// Configurar el ActionListener del botón de curar 
				botonCurar.addActionListener(e -> {
					juego.sumarAccion(); //suma una accion

					texto.append("Has curado " + ubicacion.getNombreCiudad() + "\n");//añade el texto del lateral
					if (comprobarAcciones(juego.getAccion(), labelSuperior, texto) == true) {//si comprobar acciones es true entra en el if
						ronda = true;
					}

					if (vDelta.getVacunaCompleta() == true) {// si la vacuna esta completa, curara la enfermedad por completo
						ubicacion.curarEnfermedadVacuna();
					} else {// si no esta completa le restara 1
						ubicacion.curarEnfermedad(1);
					}

					if (ubicacion.getNivelEnfermedad() == 0) {//si la enfermedad es 0, se desactivara la enfermedad de la misma
						ubicacion.setEstado(false);
					}

					actualizarBotones(panelImagen, ubicaciones, labelSuperior, texto);//inicia la actualizacon de botones
					if (popupMenu.isVisible()) {
						popupMenu.setVisible(false);//al clicar en curar te cierra el panel PopUp
					}

				});
				panelImagen.add(botonCiudadAzul);
			} else if (ubicacion.getNum() == 1 && ubicacion.getEstado() == true) {//si el numero de enferemedad asignado es 1(Alfa) y esta activa la enfermedad entra aqui
				boton_Ciudad botonCiudadRojo = new boton_Ciudad();
				botonCiudadRojo.setBounds(x, y, 50, 30);
				botonCiudadRojo.setText("Ciudad");
				botonCiudadRojo.setBackground(Color.RED);
				// Cargar el icono correspondiente
				ImageIcon icono = new ImageIcon(".\\src\\virus_2.png");
				botonCiudadRojo.setIcon(icono); // Asignar el icono al botón

				botonCiudadRojo.setVerticalTextPosition(JButton.CENTER);
				botonCiudadRojo.setHorizontalTextPosition(JButton.CENTER);
				panelImagen.add(botonCiudadRojo);

				botonCiudadRojo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Datos = GestionCiudades.mostrarDatos(ubicacion.getNombreCiudad(),
								ubicacion.getNivelEnfermedad(), ubicacion.getNombre());
						labelCiudad.setText("<html>" + Datos.replaceAll("\n", "<br>") + "</html>");
						popupMenu.show(botonCiudadRojo, 0, botonCiudadRojo.getHeight());
					}
				});
				// Configurar el ActionListener del botón de curar para hacer algo
				botonCurar.addActionListener(e -> {
					juego.sumarAccion();
					if (comprobarAcciones(juego.getAccion(), labelSuperior,texto) == true) {
						ronda = true;
						actualizarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					}

					if (vAlfa.getVacunaCompleta() == true) {
						ubicacion.curarEnfermedadVacuna();
					} else {
						ubicacion.curarEnfermedad(1);
					}

					if (ubicacion.getNivelEnfermedad() == 0) {
						ubicacion.setEstado(false);
					}

					actualizarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					if (popupMenu.isVisible()) {
						popupMenu.setVisible(false);
					}
				});
				panelImagen.add(botonCiudadRojo);
			} else if (ubicacion.getNum() == 2 && ubicacion.getEstado() == true) {//si el numero de enferemedad asignado es 2(Beta) y esta activa la enfermedad entra aqui
				boton_Ciudad botonCiudadVerde = new boton_Ciudad();
				botonCiudadVerde.setBounds(x, y, 50, 30);
				botonCiudadVerde.setText("Ciudad");
				botonCiudadVerde.setBackground(Color.GREEN);

				// Cargar el icono correspondiente
				ImageIcon icono = new ImageIcon(".\\src\\virus_3.png");
				botonCiudadVerde.setIcon(icono); // Asignar el icono al botón

				botonCiudadVerde.setVerticalTextPosition(JButton.CENTER);
				botonCiudadVerde.setHorizontalTextPosition(JButton.CENTER);
				panelImagen.add(botonCiudadVerde);

				botonCiudadVerde.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Datos = GestionCiudades.mostrarDatos(ubicacion.getNombreCiudad(),
								ubicacion.getNivelEnfermedad(), ubicacion.getNombre());
						labelCiudad.setText("<html>" + Datos.replaceAll("\n", "<br>") + "</html>");
						popupMenu.show(botonCiudadVerde, 0, botonCiudadVerde.getHeight());
					}
				});
				// Configurar el ActionListener del botón de curar para hacer algo
				botonCurar.addActionListener(e -> {
					juego.sumarAccion();
					texto.append("Has curado " + ubicacion.getNombreCiudad() + "\n");

					if (comprobarAcciones(juego.getAccion(), labelSuperior,texto) == true) {
						ronda = true;
						actualizarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					}

					if (vBeta.getVacunaCompleta() == true) {
						ubicacion.curarEnfermedadVacuna();
					} else {
						ubicacion.curarEnfermedad(1);
					}

					if (ubicacion.getNivelEnfermedad() == 0) {
						ubicacion.setEstado(false);
					}
					actualizarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					if (popupMenu.isVisible()) {
						popupMenu.setVisible(false);
					}
				});
				panelImagen.add(botonCiudadVerde);
			} else if (ubicacion.getNum() == 3 && ubicacion.getEstado() == true) {//si el numero de enferemedad asignado es 3(Gamma) y esta activa la enfermedad entra aqui
				boton_Ciudad botonCiudadAmarillo = new boton_Ciudad();
				botonCiudadAmarillo.setBounds(x, y, 50, 30);
				botonCiudadAmarillo.setText("Ciudad");
				botonCiudadAmarillo.setBackground(Color.YELLOW);

				// Cargar el icono correspondiente
				ImageIcon icono = new ImageIcon(".\\src\\virus_4.png");
				botonCiudadAmarillo.setIcon(icono); // Asignar el icono al botón

				botonCiudadAmarillo.setVerticalTextPosition(JButton.CENTER);
				botonCiudadAmarillo.setHorizontalTextPosition(JButton.CENTER);
				panelImagen.add(botonCiudadAmarillo);

				botonCiudadAmarillo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Datos = GestionCiudades.mostrarDatos(ubicacion.getNombreCiudad(),
								ubicacion.getNivelEnfermedad(), ubicacion.getNombre());
						labelCiudad.setText("<html>" + Datos.replaceAll("\n", "<br>") + "</html>");
						popupMenu.show(botonCiudadAmarillo, 0, botonCiudadAmarillo.getHeight());
					}
				});
				// Configurar el ActionListener del botón de curar para hacer algo
				botonCurar.addActionListener(e -> {
					juego.sumarAccion();
					texto.append("Has curado " + ubicacion.getNombreCiudad() + "\n");

					if (comprobarAcciones(juego.getAccion(), labelSuperior,texto) == true) {
						ronda = true;
						actualizarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					}

					if (vGamma.getVacunaCompleta() == true) {
						ubicacion.curarEnfermedadVacuna();
					} else {
						ubicacion.curarEnfermedad(1);
					}
					if (ubicacion.getNivelEnfermedad() == 0) {
						ubicacion.setEstado(false);
					}
					actualizarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					if (popupMenu.isVisible()) {
						popupMenu.setVisible(false);
					}

				});
				panelImagen.add(botonCiudadAmarillo);
			} else {//Ya que todas las ciudades tienen un numero asignado, si esta desactivada la enfermedad entrara aqui
				boton_Ciudad botonCiudadNeutro = new boton_Ciudad();
				botonCiudadNeutro.setBounds(x, y, 50, 30);
				botonCiudadNeutro.setText("Ciudad");
				botonCiudadNeutro.setBackground(Color.YELLOW);
				botonCiudadNeutro.setOpaque(false);

				// Cargar el icono correspondiente
				ImageIcon icono = new ImageIcon(".\\src\\virus_5.png");
				botonCiudadNeutro.setIcon(icono); // Asignar el icono al botón

				botonCiudadNeutro.setVerticalTextPosition(JButton.CENTER);
				botonCiudadNeutro.setHorizontalTextPosition(JButton.CENTER);
				panelImagen.add(botonCiudadNeutro);

				botonCiudadNeutro.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Datos = GestionCiudades.mostrarDatos(ubicacion.getNombreCiudad(),
								ubicacion.getNivelEnfermedad(), ubicacion.getNombre());
						labelCiudad.setText("<html>" + Datos.replaceAll("\n", "<br>") + "</html>");
						popupMenu.show(botonCiudadNeutro, 0, botonCiudadNeutro.getHeight());
					}
				});
				// Configurar el ActionListener del botón de curar para hacer algo
				botonCurar.addActionListener(e -> {

					if (ubicacion.getNivelEnfermedad() == 0) {
						ubicacion.setEstado(false);
					}
				});
				panelImagen.add(botonCiudadNeutro);
			}
			//si una ciudad brota por primera vez entrara aqui y mostrarar el siguiente mensaje, para seguidamente infectar las colindantes
			if (ubicacion.getAumentar() == false) {
				if (ubicacion.broteRealizado() == true) {

				} else {
					juego.aumentarBrotes();
					texto.append("La ciudad " + ubicacion.getNombreCiudad() + " a brotado" + "\n");
					texto.append("La ciudad " + ubicacion.getNombreCiudad() + " brote-> " + juego.getBrotesActuales()
							+ "\n");
					String colindantes = ubicacion.getColindantes(ubicacion.getNombreCiudad());

					colindantesArray = colindantes.split(",");
					aumentarEnfBrote(colindantesArray, ubicaciones, texto);
					actualizarBotones(panelImagen, ubicaciones, labelSuperior, texto);

				}

			}

			if (ubicacion.getEstado() == false) {// este if cuenta las ciudades curadas, si estan todas curadas ganas la partida
				ciudades_Curadas++;
				if (ciudades_Curadas == 48) {
					JOptionPane.showMessageDialog(null, "¡Felicidades! Has curado todas las ciudades. ¡Fin del juego!");
					// Crear una instancia de la nueva clase
					reproductor.detenerMusica();
					dispose();
					Final.inciar_Fin("Fin.txt", ".\\src\\Final.wav");
					Ranking(true);
				}
			}
			brotes_Derrota = ubicacion.getParametros()[3]; //asigna el valor necesario para perder por brotes 
		}
		if (juego.getBrotesActuales() >= brotes_Derrota && fin == false) {//si hay mas btrotes de los permitdos perdes la partida
			JOptionPane.showMessageDialog(null, "Brotaron demasiadas ciudades ¡Fin del juego!");
			// Crear una instancia de la nueva clase
			reproductor.detenerMusica();
			dispose();
			Final.inciar_Fin("BadEndingBrotes.txt", ".\\src\\BadEnding.wav");
			fin = true;
			Ranking(false);
		}
		contador++;
	}
	//Esta funcion se encarga de guardar tu partida en el ranking cuando finaliza asignando una puntuacion
	private void Ranking(boolean condicion) {
		// TODO Auto-generated method stub
		int puntuacion = 0;
		String nombre = juego.getJugador();
		if (condicion == false) {
			puntuacion = ((100 + juego.getTurnoActual()) - juego.getBrotesActuales());
		} else if (condicion == true) {
			puntuacion = ((100 + juego.getTurnoActual()) - juego.getBrotesActuales());
			puntuacion = puntuacion + 100;
		}

		try {
			ConexionBD.Ranking(juego, nombre, puntuacion, condicion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//Esta funcion se encarga de infectar las colindantes y activarlas en el caso de que no lo esten, cuando se realiza un brote
	private void aumentarEnfBrote(String[] colindantesArray2, ArrayList<UBICACION> ubicaciones, JTextArea texto) {
		// TODO Auto-generated method stub
		for (UBICACION ubicacion : ubicaciones) {
			for (String colindante : colindantesArray) {
				if (colindante.trim().equals(ubicacion.getNombreCiudad())) {
					ubicacion.setEstado(true);
					ubicacion.setNivelEnfermedad(1);

				}
			}
		}

	}
	//e esta funcon comprueba si las acciones superan las 4 suma ua ronda y se iguala a 0
	private boolean comprobarAcciones(int accion, JLabel labelSuperior, JTextArea texto) {
		// TODO Auto-generated method stub
		
		if (juego.getAccion() >= 4) {
			nueva_Ronda(texto);
			labelSuperior.setText("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
					+ "| Acciones realizadas=" + juego.getAccion() + "/4");
			juego.setAccion(0);
			labelSuperior.setText("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
					+ "| Acciones realizadas=" + juego.getAccion() + "/4");
			return true;
		} else {
			labelSuperior.setText("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
					+ "| Acciones realizadas=" + juego.getAccion() + "/4");
			return false;
		}

	}
	//esta funcion comprueaba el estado de las vacunas
	private void comprobarEstado(JProgressBar progressBar1, JProgressBar progressBar2, JProgressBar progressBar3,
			JProgressBar progressBar4) {
		// TODO Auto-generated method stub
		// Verificar si todas las barras de progreso han llegado al 100%
		if (progressBar1.getValue() == 100 && progressBar2.getValue() == 100 && progressBar3.getValue() == 100
				&& progressBar4.getValue() == 100) {
			// Finalizar el juego
			JOptionPane.showMessageDialog(null, "¡Felicidades! Has completado todas las vacunas. ¡Fin del juego!");
			// Crear una instancia de la nueva clase
			String nombre = juego.getJugador();
			int puntuacion = ((100 + juego.getTurnoActual()) - juego.getBrotesActuales());
			puntuacion = puntuacion + 100;
			System.out.println(juego.getJugador() + "Puntunacion-->" + puntuacion);
			try {
				ConexionBD.Ranking(juego, nombre, puntuacion, true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reproductor.detenerMusica();
			dispose();
			Final.inciar_Fin("Final.txt", ".\\src\\Final.wav");
		}
	}
	//esta funcion incia el panel de insertar el nombre del jugador
	String nombre_Jugador() {
		// crear el JDialog modal
		JDialog dialogo = new JDialog();
		dialogo.setModal(true);

		// crear el panel de ingreso de nombre
		panel_InsertarNombre panelNombre = new panel_InsertarNombre();

		// agregar el panel al diálogo
		dialogo.getContentPane().add(panelNombre);
		dialogo.pack();

		// deshabilitar la opción de cerrar
		dialogo.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		// centrar el diálogo en la pantalla
		dialogo.setLocationRelativeTo(null);

		// mostrar el diálogo y esperar a que el usuario lo cierre
		dialogo.setVisible(true);

		// obtener el nombre ingresado por el usuario
		String nombreJugador = panelNombre.getNombre();
		return nombreJugador;
	}

	public static void main(String[] args) {
		new mapa();
	}
}
