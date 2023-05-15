import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

import Paneles.Bienvenido;
import Paneles.audio;

public class menu_Principal extends JFrame implements ActionListener { //inicia la clase menu_Principal con un Action lisener

	private JButton btnNewGame, btnLoadGame, btnInfo, btnScores, btnAuthors, btnVersion, btnExit;//añadimos los JButton que utilizaremos
	audio reproductor = new audio(); //Añadimos el panel que se encarga de maneiar el audio

	public menu_Principal() {

		super("PANDEMIC"); //El nombre que se muestra arriba en la ventana
		setExtendedState(Frame.ICONIFIED); 
		setAutoRequestFocus(false);
		setBackground(UIManager.getColor("Button.focus"));
		setForeground(SystemColor.info);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\src\\Logo1.png")); //aqui se añade el icono de la parte inferior
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(749, 443); //aqui se asigna un tamaño de ventana
	    setResizable(false); // La ventana no se puede redimensionar
	    setUndecorated(true); // La ventana no tiene barra superior
		reproductor.musica(".\\src\\menu_NuevaPartida.wav"); // Inicia la reproduccion de la cancion del menu
		// Crear el panel del menú
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);

		// Crear los botones del menú
		btnNewGame = new JButton();
		ImageIcon icon = new ImageIcon(".\\src\\Botones\\Nueva-Partida.png");
		Image img = icon.getImage().getScaledInstance(161, 41, Image.SCALE_SMOOTH);
		btnNewGame.setIcon(new ImageIcon(img));
		btnNewGame.setBorderPainted(false);
		btnNewGame.setPreferredSize(new Dimension(161, 41));
		btnNewGame.setSize(new Dimension(161, 41));
		btnNewGame.setOpaque(false);
		btnNewGame.setContentAreaFilled(false);
		panel.add(btnNewGame);

		btnLoadGame = new JButton();
		ImageIcon icon1 = new ImageIcon(".\\src\\Botones\\Cargar-Partida.png");
		Image img1 = icon1.getImage().getScaledInstance(161, 41, Image.SCALE_SMOOTH);
		btnLoadGame.setIcon(new ImageIcon(img1));
		btnLoadGame.setPreferredSize(new Dimension(161, 41));
		btnLoadGame.setBorderPainted(false);
		btnLoadGame.setSize(new Dimension(161, 41));
		btnLoadGame.setOpaque(false);
		btnLoadGame.setContentAreaFilled(false);
		panel.add(btnLoadGame);

		btnInfo = new JButton();
		ImageIcon icon2 = new ImageIcon(".\\src\\Botones\\Informacion.png");
		Image img2 = icon2.getImage().getScaledInstance(161, 41, Image.SCALE_SMOOTH);
		btnInfo.setIcon(new ImageIcon(img2));
		btnInfo.setPreferredSize(new Dimension(161, 41));
		btnInfo.setBorderPainted(false);
		btnInfo.setSize(new Dimension(161, 41));
		btnInfo.setOpaque(false);
		btnInfo.setContentAreaFilled(false);
		panel.add(btnInfo);

		btnScores = new JButton();
		ImageIcon icon3 = new ImageIcon(".\\src\\Botones\\Puntuaciones.png");
		Image img3 = icon3.getImage().getScaledInstance(161, 41, Image.SCALE_SMOOTH);
		btnScores.setIcon(new ImageIcon(img3));
		btnScores.setPreferredSize(new Dimension(161, 41));
		btnScores.setSize(new Dimension(161, 41));
		btnScores.setBorderPainted(false);
		btnScores.setOpaque(false);
		btnScores.setContentAreaFilled(false);
		panel.add(btnScores);

		btnAuthors = new JButton();
		ImageIcon icon4 = new ImageIcon(".\\src\\Botones\\Autores.png");
		Image img4 = icon4.getImage().getScaledInstance(161, 41, Image.SCALE_SMOOTH);
		btnAuthors.setIcon(new ImageIcon(img4));
		btnAuthors.setPreferredSize(new Dimension(161, 41));
		btnAuthors.setSize(new Dimension(161, 41));
		btnAuthors.setBorderPainted(false);
		btnAuthors.setOpaque(false);
		btnAuthors.setContentAreaFilled(false);
		panel.add(btnAuthors);

		btnVersion = new JButton();
		ImageIcon icon5 = new ImageIcon(".\\src\\Botones\\Version.png");
		Image img5 = icon5.getImage().getScaledInstance(161, 41, Image.SCALE_SMOOTH);
		btnVersion.setIcon(new ImageIcon(img5));
		btnVersion.setPreferredSize(new Dimension(161, 41));
		btnVersion.setSize(new Dimension(161, 41));
		btnVersion.setBorderPainted(false);
		btnVersion.setOpaque(false);
		btnVersion.setContentAreaFilled(false);
		panel.add(btnVersion);

		btnExit = new JButton();
		ImageIcon icon6 = new ImageIcon(".\\src\\Botones\\Salir.png");
		Image img6 = icon6.getImage().getScaledInstance(161, 41, Image.SCALE_SMOOTH);
		btnExit.setIcon(new ImageIcon(img6));
		btnExit.setPreferredSize(new Dimension(161, 41));
		btnExit.setSize(new Dimension(161, 41));
		btnExit.setBorderPainted(false);
		btnExit.setOpaque(false);
		btnExit.setContentAreaFilled(false);
		panel.add(btnExit);

		// Agregar ActionListener a los botones
		btnNewGame.addActionListener(this);
		btnLoadGame.addActionListener(this); 
		btnInfo.addActionListener(this);
		btnScores.addActionListener(this);
		btnAuthors.addActionListener(this);
		btnVersion.addActionListener(this);
		btnExit.addActionListener(this);

		getContentPane().add(panel);

		// Agregar la imagen al JLabel
		getContentPane().add(panel);
		setVisible(true);

		// Setear las dimensiones y la posición de los botones
		Dimension buttonSize = new Dimension(130, 23);
		btnNewGame.setPreferredSize(buttonSize);
		btnNewGame.setSize(buttonSize);
		btnNewGame.setLocation(28, 115);

		btnLoadGame.setPreferredSize(buttonSize);
		btnLoadGame.setSize(buttonSize);
		btnLoadGame.setLocation(28, 148);
 
		btnInfo.setPreferredSize(buttonSize);
		btnInfo.setSize(new Dimension(163, 23));
		btnInfo.setLocation(28, 191);

		Dimension scoresButtonSize = new Dimension(190, 23);
		btnScores.setPreferredSize(scoresButtonSize); 
		btnScores.setSize(new Dimension(224, 23));
		btnScores.setLocation(28, 224);

		Dimension authorsButtonSize = new Dimension(103, 23);
		btnAuthors.setPreferredSize(authorsButtonSize);
		btnAuthors.setSize(authorsButtonSize);
		btnAuthors.setLocation(28, 258);

		btnVersion.setPreferredSize(authorsButtonSize);
		btnVersion.setSize(authorsButtonSize);
		btnVersion.setLocation(28, 286);

		Dimension exitButtonSize = new Dimension(103, 37);
		btnExit.setPreferredSize(exitButtonSize);
		btnExit.setSize(new Dimension(116, 45));
		btnExit.setLocation(549, 314);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBackground(SystemColor.text);
		lblNewLabel.setBounds(0, 11, 800, 300); // Modificar el tamaño de la etiqueta
		panel.add(lblNewLabel);

		ImageIcon icono = new ImageIcon(".\\src\\PANDEMIC.png");
		Image imagenOriginal = icono.getImage();
		Image imagenRedimensionada = imagenOriginal.getScaledInstance(600, 200, Image.SCALE_SMOOTH); // Redimensionar la imagen
		ImageIcon nuevoIcono = new ImageIcon(imagenRedimensionada);

		lblNewLabel.setIcon(nuevoIcono);


		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(".\\src\\fondo3.png"));

		// Obtener el tamaño de la pantalla
		Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();

		// Redimensionar la imagen
		Image imagenOriginal1 = ((ImageIcon) lblNewLabel_1.getIcon()).getImage();
		Image imagenRedimensionada1 = imagenOriginal1.getScaledInstance(tamañoPantalla.width, tamañoPantalla.height, Image.SCALE_SMOOTH);
		ImageIcon nuevoIcono1 = new ImageIcon(imagenRedimensionada1);

		lblNewLabel_1.setIcon(nuevoIcono1);
		lblNewLabel_1.setBounds(0, 0, tamañoPantalla.width, tamañoPantalla.height);
		panel.add(lblNewLabel_1);


		// Redimensionar los botones cuando la ventana cambie de tamaño
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// Calcularel nuevo tamaño de los botones y actualizar su posición y tamaño
				int panelWidth = panel.getWidth();
				int panelHeight = panel.getHeight();

				// Calcular nuevos tamaños y posiciones de los botones
				int newButtonWidth = (int) (panelWidth * 0.2);
				int newButtonHeight = (int) (panelHeight * 0.05);
				int newButtonX = (int) (panelWidth * 0.05);
				int newButtonY1 = (int) (panelHeight * 0.3);
				int newButtonY2 = (int) (panelHeight * 0.4);
				int newButtonY3 = (int) (panelHeight * 0.5);
				int newButtonY4 = (int) (panelHeight * 0.6);
				int newButtonY5 = (int) (panelHeight * 0.7);
				int newButtonY6 = (int) (panelHeight * 0.8);

				// Actualizar tamaño y posición de los botones
				btnNewGame.setBounds(newButtonX, newButtonY1, newButtonWidth, newButtonHeight);
				btnLoadGame.setBounds(newButtonX, newButtonY2, newButtonWidth, newButtonHeight);
				btnInfo.setBounds(newButtonX, newButtonY3, newButtonWidth, newButtonHeight);
				btnScores.setBounds(newButtonX, newButtonY4, newButtonWidth, newButtonHeight);
				btnAuthors.setBounds(newButtonX, newButtonY5, newButtonWidth, newButtonHeight);
				btnVersion.setBounds(newButtonX, newButtonY6, newButtonWidth, newButtonHeight);
				btnExit.setBounds(panelWidth - newButtonWidth - newButtonX, panelHeight - newButtonHeight - newButtonX,
						newButtonWidth, newButtonHeight);
			}
		});
		setExtendedState(Frame.MAXIMIZED_BOTH);
	}

	//Crea la accion para cuando pulsas los botones

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewGame) {//Cuando pulsas el boton de nueva partida
			reproductor.detenerMusica();// Detiene la musica del menu
			AJUSTES frame = new AJUSTES();//Abre el panel de AJUSTES donde se selecciona la dificultad y hace visible el panel
			frame.setVisible(true);
			reproductor.sonido(".\\src\\Select1.wav");//Reproduce el sonido de pulsar un boton
			dispose(); // Cierra la ventana actual

		} else if (e.getSource() == btnLoadGame) {
			reproductor.detenerMusica();
			dispose(); // Cierra la ventana actual
			new PanelPartidas();//abre la ventana panel partidas que es donde podras cargar o borrar las partidas
			reproductor.sonido(".\\src\\Select1.wav");
		} else if (e.getSource() == btnInfo) {
			dispose(); // Cierra la ventana actual
			reproductor.sonido(".\\src\\Select1.wav");
			reproductor.detenerMusica();
			Final.inciar_Fin("Info.txt", ".\\src\\Final1.wav"); //abre el panel donde se mostrara la informacion, añadiendo donde esta el texto y su respectiva cancion
		} else if (e.getSource() == btnScores) {
			reproductor.sonido(".\\src\\Select1.wav");
			Bienvenido bienvenida = new Bienvenido("Ranking");
			bienvenida.mostrarPopup2("ranking.txt");//abre el popup donde se muestra el ranking
			reproductor.sonido(".\\src\\Select1.wav");
		}
		if (e.getSource() == btnAuthors) {
			reproductor.sonido(".\\src\\Select1.wav");
			Bienvenido bienvenida = new Bienvenido("Autores");
			bienvenida.mostrarPopup2("Autores.txt");//abre el popup donde se muestra los autores
			reproductor.sonido(".\\src\\Select1.wav");
		}

		else if (e.getSource() == btnVersion) {
			reproductor.sonido(".\\src\\Select1.wav");
			Bienvenido bienvenida = new Bienvenido("Autores");
			bienvenida.mostrarPopup2("Version.txt");//abre el popup donde se muestra la version actual
			reproductor.sonido(".\\src\\Select1.wav");

		} else if (e.getSource() == btnExit) {

			System.exit(0); //cierra el programa
		}

	}

	public static void main(String[] args) {
		new menu_Principal();
	}

	public int getThisExtendedState() {
		return getExtendedState();
	}

	public void setThisExtendedState(int extendedState) {
		setExtendedState(extendedState);
	}
	
}
