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
import javax.swing.WindowConstants;
import java.io.FileWriter;
import Botones.boton_Ciudad;
import Botones.boton_Curar;
import Botones.boton_Guardar;
import Paneles.panel_InsertarNombre;
import Paneles.Bienvenido;
import Paneles.VacunaProgressBar;
import Paneles.audio;

public class cargar_Partida extends JFrame {
	public String Datos;
	public String ciudad;
	public Juego juego = new Juego(1, " ", Datos, Datos, Datos, Datos, 9, 0, 0);
	public Vacuna vDelta = new Vacuna(0);
	public Vacuna vBeta = new Vacuna(0);
	public Vacuna vAlfa = new Vacuna(0);
	public Vacuna vGamma = new Vacuna(0);
	public static audio reproductor = new audio();
	public int ejecuciones = 0;
	public boolean ronda = false;
	public String[] colindantesArray;
	private boolean fin = false;

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////                                                                                                                                     /////
	///// Es una copia de la clase mapa asi que no voy a comentar esste codigo, esta todo en el de mapa.java                                  /////
	/////                                                                                                                                     /////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public cargar_Partida(String nombre, int turnoActual1, int acciones1, int brotesActuales1, int estadoVacunaDelta1,
			int estadoVacunaAlfa1, int estadoVacunaBeta1, int estadoVacunaGamma1, int infectadasInicio1,
			int infectadasRonda1, int enfermedadesActivasDerrota1, int brotesDerrota1, String[] ciudades, int[] posX,
			int[] posY, int[] numEnfer, boolean[] estado1, int[] nivelEnfer, boolean[] aumentar1) {
		super("Mapa Pandemic");
		setResizable(false);
		// Obtener la lista de ubicaciones
		ArrayList<UBICACION> ubicaciones = UBICACION_MANAGER.crearUbicaciones();

		// esta es la unica funcion diferente
		actualizarDatos(nombre, turnoActual1, acciones1, brotesActuales1, estadoVacunaDelta1, estadoVacunaAlfa1,
				estadoVacunaBeta1, estadoVacunaGamma1, infectadasInicio1, infectadasRonda1, enfermedadesActivasDerrota1,
				brotesDerrota1, ciudades, posX, posY, numEnfer, estado1, nivelEnfer, aumentar1, ubicaciones);
		Bienvenido bienvenida = new Bienvenido(nombre);
		bienvenida.mostrarPopup();
		reproductor.musica(".\\src\\partida.wav");
		reproductor.ajustarVolumen(20);
		// Crear el panel de la imagen
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
		panelImagen.setBackground(new Color(88, 159, 185)); // Agregar este código para establecer el color de fondo

		// LABEL PARA MOSTRAR EL NOMBRE DEL JUGADOR

		JPanel panelInferior = new JPanel();
		JLabel labelResultado = new JLabel("Texto del Label");
		panelInferior.add(labelResultado);
		panelInferior.setBackground(new Color(88, 159, 185)); // Agregar este código para establecer el color de fondo
		add(panelInferior, BorderLayout.CENTER);

		JLabel labelSuperior = new JLabel("Jugador " + juego.getJugador() + " | Ronda " + juego.getTurnoActual()
				+ " | Acciones realizadas: " + juego.getAccion() + "/4");
		labelSuperior.setFont(new Font("Helvetica", Font.BOLD, 24));
		labelSuperior.setBackground(new Color(88, 159, 185)); // Agregar este código para establecer el color de fondo
		labelSuperior.setForeground(new Color(88, 159, 185));

		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0)); // Agrega un espacio en blanco de 20
																				// píxeles en la parte superior y 50
																				// píxeles a la izquierda
		panelSuperior.add(labelSuperior, BorderLayout.CENTER);

		add(panelSuperior, BorderLayout.NORTH);

		// Crear los paneles
		JPanel panelARRIBA = new JPanel();
		JPanel panelABAJO = new JPanel();

		// Crear el contenido del panel superior y agregarlo a un JScrollPane
		JTextArea texto = new JTextArea(20, 20);
		texto.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(texto);
		panelARRIBA.add(scrollPane);
		// Crear los botones y agregarlos al panel inferior
		JLabel texto_botones = new JLabel("PANDEMIC");
		boton_Guardar boton_1 = new boton_Guardar("GUARDAR");
		boton_Guardar boton_2 = new boton_Guardar("SALIR");
		panelABAJO.add(texto_botones);
		panelABAJO.add(boton_1);
		panelABAJO.add(boton_2);

		// Hacer que el JTextArea baje automáticamente al final del contenido
		texto.setCaretPosition(texto.getDocument().getLength());
		
		boton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reproductor.sonido(".\\src\\Select1.wav");
				reproductor.detenerMusica();
				dispose();
				new menu_Principal(); // crea una instancia de la clase menu_Principal

			}

		});

		// Add a gray border to the top panel
		panelARRIBA.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(88, 159, 185)));

		// Crear el JSplitPane y agregar los paneles
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, /* true, */ panelARRIBA, panelABAJO);
		splitPane.setDividerLocation(0.5);
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

		delta.getBotonVacunar().addActionListener(e -> {
			vDelta.aumentarEstadoVacuna(); // aumentar el valor actual de la barra de progreso en 10
			delta.getProgressBar().setValue(vDelta.getEstadoVacuna()); // actualizar visualmente la barra de progreso
			reproductor.sonido(".\\src\\Select1.wav");
			texto.append("Desarrolla la vacuna Delta" + "\n");
			texto.append("Vacuna Delta->" + vDelta.getEstadoVacuna() + "%" + "\n");

			if (vDelta.getEstadoVacuna() >= 100) {
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
				delta.getBotonVacunar().setEnabled(false);
				vDelta.comprobarEstadoVacuna();

			} else {
				nueva_Ronda(texto); // aumentar el número de ronda en 1
				labelSuperior.setText("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
						+ "| Acciones realizadas=" + juego.getAccion() + "/4");
				vDelta.comprobarEstadoVacuna();
			}
			System.out.println("Nueva Ronda");
			ronda = true;
			eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
		});

		alfa.getBotonVacunar().addActionListener(e -> {
			vAlfa.aumentarEstadoVacuna(); // aumentar el valor actual de la barra de progreso en 10
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

			System.out.println("Nueva Ronda");
			ronda = true;
			eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
		});

		beta.getBotonVacunar().addActionListener(e -> {
			vBeta.aumentarEstadoVacuna(); // aumentar el valor actual de la barra de progreso en 10
			beta.getProgressBar().setValue(vBeta.getEstadoVacuna()); // actualizar visualmente la barra de progreso
			reproductor.sonido(".\\src\\Select1.wav");
			texto.append("Desarrolla la vacuna Beta" + "\n");
			texto.append("Vacuna Gamma->" + vBeta.getEstadoVacuna() + "%" + "\n");
			if (vBeta.getEstadoVacuna() >= 100) {

				nueva_Ronda(texto);
				labelSuperior.setText("Jugador " + juego.getJugador() + " Numero de Ronda " + juego.getTurnoActual()
				+ "| Acciones realizadas=" + juego.getAccion() + "/4");
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

			System.out.println("Nueva Ronda");
			ronda = true;
			eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
		});

		gamma.getBotonVacunar().addActionListener(e -> {
			vGamma.aumentarEstadoVacuna(); // aumentar el valor actual de la barra de progreso en 10
			gamma.getProgressBar().setValue(vGamma.getEstadoVacuna()); // actualizar visualmente la barra de progreso
			reproductor.sonido(".\\src\\Select1.wav");
			texto.append("Desarrolla la vacuna Gamma" + "\n");
			texto.append("Vacuna Delta->" + vGamma.getEstadoVacuna() + "%" + "\n");
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

			System.out.println("Nueva Ronda");
			ronda = true;
			eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
		});

		///////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////

		boton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reproductor.sonido(".\\src\\Select1.wav");
				int opcion = JOptionPane.showConfirmDialog(null,
						"Jugador: " + juego.getJugador() + " ¿Estás seguro que quieres guardar?", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					try {
						reproductor.sonido(".\\src\\Guardar.wav");
						ConexionBD.Cargar(juego, ubicaciones, vDelta, vAlfa, vBeta, vGamma);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});

		///////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////

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

		texto.append("Ronda "+juego.getTurnoActual() + "\n");
		texto.append("................" + "\n");
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///// /////
	///// La funcion actualizarDatos(), se encarga de asignar todos los datos
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// sacados
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void actualizarDatos(String nombre, int turnoActual1, int acciones1, int brotesActuales1,
			int estadoVacunaDelta1, int estadoVacunaAlfa1, int estadoVacunaBeta1, int estadoVacunaGamma1,
			int infectadasInicio1, int infectadasRonda1, int enfermedadesActivasDerrota1, int brotesDerrota1,
			String[] ciudades, int[] posX, int[] posY, int[] numEnfer, boolean[] estado1, int[] nivelEnfer,
			boolean[] aumentar1, ArrayList<UBICACION> ubicaciones) {
		// TODO Auto-generated method stub
		vDelta.setEstadoVacuna(estadoVacunaDelta1);
		vAlfa.setEstadoVacuna(estadoVacunaAlfa1);
		vBeta.setEstadoVacuna(estadoVacunaBeta1);
		vGamma.setEstadoVacuna(estadoVacunaGamma1);
		juego.setJugador(nombre);
		juego.setRonda(turnoActual1);
		int i = 0;
		juego.setAccion(acciones1);
		for (UBICACION ubicacion : ubicaciones) {
			ubicacion.setDescripcion(ciudades[i]);
			;
			ubicacion.setX(posX[i]);
			ubicacion.setY(posY[i]);
			ubicacion.setNivelEnfermedad(nivelEnfer[i]);
			ubicacion.setAumentar(true);
			ubicacion.setEstado(estado1[i]);
			i++;
		}
		i = 0;
		for (UBICACION ubicacion : ubicaciones) {
			ubicacion.setParametros(infectadasInicio1, infectadasRonda1, enfermedadesActivasDerrota1, brotesDerrota1);
			i++;

		}

	}

	private void nueva_Ronda(JTextArea texto) {
		// Incrementar la ronda del juego

		juego.NuevaRonda();
		texto.append("Ronda "+juego.getTurnoActual() + "\n");
		texto.append("................" + "\n");
		// Hacer que el JTextArea baje automáticamente al final del contenido
		texto.setCaretPosition(texto.getDocument().getLength());
	}
	
	
	public void eliminarBotones(JPanel panelImagen, ArrayList<UBICACION> ubicaciones, JLabel labelSuperior,
			JTextArea texto) { // Remove all the buttons from thepanelImagen
		panelImagen.removeAll(); // Repaint the panelImagen to reflect thechanges
		panelImagen.repaint();
		if (ronda == true) {
			infectarRonda(ubicaciones, panelImagen, labelSuperior, texto);
			ronda = false;
		} else {
			infectarRonda(ubicaciones, panelImagen, labelSuperior, texto);

		}

	}



	private void infectarRonda(ArrayList<UBICACION> ubicaciones, JPanel panelImagen, JLabel labelSuperior,
			JTextArea texto) {
		// TODO Auto-generated method stub
		int ciudades_Curadas = 0;
		int ciudades_Infectadas = 0;
		int suma = 0;
		int brotes_Derrota = 0;
		

		for (UBICACION ubicacion : ubicaciones) {
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

			if (ronda == false) {

				if (ubicacion.getNivelEnfermedad() >= 1) {
					ubicacion.setEstado(true);
					ubicacion.setAumentar(false);
				}

			} else {
				if (ubicacion.getEstado() == false) {
					if (suma < ubicacion.getParametros()[1]) {
						boolean inf = ubicacion.generarEstado1(suma);
						if (inf == true && suma < ubicacion.getParametros()[1]) {
							suma++;
							ubicacion.setNivelEnfermedad(ubicacion.sumarNivelEnfermedad(enf));
						}
					}
				} else if (ubicacion.getEstado() == true && ubicacion.broteRealizado() == false && fin == false) {
					ciudades_Infectadas++;
					if (suma < ubicacion.getParametros()[1]) {
						suma++;
						ubicacion.sumarNivelEnfermedad(enf);
					}
					if (ciudades_Infectadas >= ubicacion.getParametros()[2]) {

						reproductor.detenerMusica();
						dispose();
						Final.inciar_Fin("BanEndingInfeccion.txt", ".\\src\\BadEnding.wav");
						fin = true;
						Ranking(false);
					}
				} else if (ubicacion.getEstado() == true && ubicacion.broteRealizado() == true) {
					ciudades_Infectadas++;

					if (ciudades_Infectadas >= ubicacion.getParametros()[2] && fin == false) {

						reproductor.detenerMusica();
						dispose();
						Final.inciar_Fin("BanEndingInfeccion.txt", ".\\src\\BadEnding.wav");
						fin = true;
						Ranking(false);
					}
				}

			}

			if (ubicacion.getNum() == 0 && ubicacion.getEstado() == true) {
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

				botonCiudadAzul.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Datos = GestionCiudades.mostrarDatos(ubicacion.getNombreCiudad(),
								ubicacion.getNivelEnfermedad(), ubicacion.getNombre());
						labelCiudad.setText("<html>" + Datos.replaceAll("\n", "<br>") + "</html>");
						popupMenu.show(botonCiudadAzul, 0, botonCiudadAzul.getHeight());
					}
				});
				// Configurar el ActionListener del botón de curar para hacer algo
				botonCurar.addActionListener(e -> {
					juego.sumarAccion();

					texto.append("Has curado " + ubicacion.getNombreCiudad() + "\n");

					if (comprobarAcciones(juego.getAccion(), labelSuperior,texto) == true) {
						System.out.println("Nueva Ronda");
						ronda = true;
						eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					}

					if (vDelta.getVacunaCompleta() == true) {
						ubicacion.curarEnfermedadVacuna();
					} else {
						ubicacion.curarEnfermedad(1);
					}

					if (ubicacion.getNivelEnfermedad() == 0) {
						ubicacion.setEstado(false);
					}

					eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					if (popupMenu.isVisible()) {
						popupMenu.setVisible(false);
					}

				});
				panelImagen.add(botonCiudadAzul);
			} else if (ubicacion.getNum() == 1 && ubicacion.getEstado() == true) {
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
						System.out.println("Nueva Ronda");
						ronda = true;
						eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					}

					if (vAlfa.getVacunaCompleta() == true) {
						ubicacion.curarEnfermedadVacuna();
					} else {
						ubicacion.curarEnfermedad(1);
					}

					if (ubicacion.getNivelEnfermedad() == 0) {
						ubicacion.setEstado(false);
					}

					eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					if (popupMenu.isVisible()) {
						popupMenu.setVisible(false);
					}
				});
				panelImagen.add(botonCiudadRojo);
			} else if (ubicacion.getNum() == 2 && ubicacion.getEstado() == true) {
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
						System.out.println("Nueva Ronda");
						ronda = true;
						eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					}

					if (vBeta.getVacunaCompleta() == true) {
						ubicacion.curarEnfermedadVacuna();
					} else {
						ubicacion.curarEnfermedad(1);
					}

					if (ubicacion.getNivelEnfermedad() == 0) {
						ubicacion.setEstado(false);
					}
					eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					if (popupMenu.isVisible()) {
						popupMenu.setVisible(false);
					}
				});
				panelImagen.add(botonCiudadVerde);
			} else if (ubicacion.getNum() == 3 && ubicacion.getEstado() == true) {
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
						System.out.println("Nueva Ronda");
						ronda = true;
						eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					}

					if (vGamma.getVacunaCompleta() == true) {
						ubicacion.curarEnfermedadVacuna();
					} else {
						ubicacion.curarEnfermedad(1);
					}
					if (ubicacion.getNivelEnfermedad() == 0) {
						ubicacion.setEstado(false);
					}
					eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
					if (popupMenu.isVisible()) {
						popupMenu.setVisible(false);
					}

				});
				panelImagen.add(botonCiudadAmarillo);
			} else {
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
					eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
				});
				panelImagen.add(botonCiudadNeutro);
			}

			if (ubicacion.getAumentar() == false) {
				if (ubicacion.broteRealizado() == true) {

				} else {
					if (ubicacion.getNivelEnfermedad() == 3) {

						juego.aumentarBrotes();
						texto.append("La ciudad " + ubicacion.getNombreCiudad() + " a brotado" + "\n");
						texto.append("La ciudad " + ubicacion.getNombreCiudad() + " brote-> " + juego.getBrotesActuales()
								+ "\n");
						String colindantes = ubicacion.getColindantes(ubicacion.getNombreCiudad());

						colindantesArray = colindantes.split(",");
						aumentarEnfBrote(colindantesArray, ubicaciones, texto);
						eliminarBotones(panelImagen, ubicaciones, labelSuperior, texto);
						}

				}

			}

			if (ubicacion.getEstado() == false) {
				ciudades_Curadas++;
				// System.out.println(ciudades_Curadas);
				if (ciudades_Curadas == 48) {
					JOptionPane.showMessageDialog(null, "¡Felicidades! Has curado todas las ciudades. ¡Fin del juego!");
					// Crear una instancia de la nueva clase
					reproductor.detenerMusica();
					dispose();
					Final.inciar_Fin("Fin.txt", ".\\src\\Final.wav");
					Ranking(true);
				}
			}
			brotes_Derrota = ubicacion.getParametros()[3];
		}
		if (juego.getBrotesActuales() >= brotes_Derrota && fin == false) {
			JOptionPane.showMessageDialog(null, "Brotaron demasiadas ciudades ¡Fin del juego!");
			// Crear una instancia de la nueva clase
			reproductor.detenerMusica();
			dispose();
			Final.inciar_Fin("BadEndingBrotes.txt", ".\\src\\BadEnding.wav");
			fin = true;
			Ranking(false);
		}
	}

	private void Ranking(boolean condicion) {
		// TODO Auto-generated method stub
		int puntuacion = 0;
		String nombre = juego.getJugador();
		if (condicion == false) {
			System.out.println("Perdiste");
			puntuacion = ((100 + juego.getTurnoActual()) - juego.getBrotesActuales());
			System.out.println(juego.getJugador() + "Puntunacion-->" + puntuacion);
		} else if (condicion == true) {
			System.out.println("ganaste");
			puntuacion = ((100 + juego.getTurnoActual()) - juego.getBrotesActuales());
			puntuacion = puntuacion + 100;
			System.out.println(juego.getJugador() + "Puntunacion-->" + puntuacion);
		}

		try {
			ConexionBD.Ranking(juego, nombre, puntuacion, condicion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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
//		new cargar_Partida();
	}
}
