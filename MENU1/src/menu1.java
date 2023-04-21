import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;


public class menu1 extends JFrame implements ActionListener {

	private JButton btnNewGame, btnLoadGame, btnInfo, btnScores, btnAuthors, btnVersion, btnExit;

	public menu1() {
 
		super("PANDEMIC");
		setExtendedState(Frame.ICONIFIED);
		setAutoRequestFocus(false);
		setBackground(UIManager.getColor("Button.focus"));
		setForeground(SystemColor.info);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\src\\Logo1.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(749, 443);
		String Select=".\\src\\menu2.wav";
		musica(Select);

		// Crear el panel del menú
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);

		// Crear los botones del menú
		btnNewGame = new JButton();
		ImageIcon icon = new ImageIcon(".\\src\\Nueva-Partida.png");
		Image img = icon.getImage().getScaledInstance(130, 26, Image.SCALE_SMOOTH);
		btnNewGame.setIcon(new ImageIcon(img));
		btnNewGame.setBorderPainted(false);
		btnNewGame.setPreferredSize(new Dimension(130, 26));
		btnNewGame.setSize(new Dimension(141, 31));
		btnNewGame.setOpaque(false);
		btnNewGame.setContentAreaFilled(false);
		panel.add(btnNewGame);

		btnLoadGame = new JButton();
		ImageIcon icon1 = new ImageIcon(".\\src\\Cargar-Partida.png");
		Image img1 = icon1.getImage().getScaledInstance(130, 26, Image.SCALE_SMOOTH);
		btnLoadGame.setIcon(new ImageIcon(img1));
		btnLoadGame.setPreferredSize(new Dimension(130, 26));
		btnLoadGame.setBorderPainted(false);
		btnLoadGame.setSize(new Dimension(141, 31));
		btnLoadGame.setOpaque(false);
		btnLoadGame.setContentAreaFilled(false);
		panel.add(btnLoadGame);

		btnInfo = new JButton();
		ImageIcon icon2 = new ImageIcon(".\\src\\Informacion.png");
		Image img2 = icon2.getImage().getScaledInstance(130, 26, Image.SCALE_SMOOTH);
		btnInfo.setIcon(new ImageIcon(img2));
		btnInfo.setPreferredSize(new Dimension(130, 26));
		btnInfo.setBorderPainted(false);
		btnInfo.setSize(new Dimension(141, 31));
		btnInfo.setOpaque(false);
		btnInfo.setContentAreaFilled(false);
		panel.add(btnInfo);

		btnScores = new JButton();
		ImageIcon icon3 = new ImageIcon(".\\src\\Puntuaciones.png");
		Image img3 = icon3.getImage().getScaledInstance(130, 26, Image.SCALE_SMOOTH);
		btnScores.setIcon(new ImageIcon(img3));
		btnScores.setPreferredSize(new Dimension(130, 26));
		btnScores.setSize(new Dimension(141, 31));
		btnScores.setBorderPainted(false);
		btnScores.setOpaque(false);
		btnScores.setContentAreaFilled(false);
		panel.add(btnScores);

		btnAuthors = new JButton();
		ImageIcon icon4 = new ImageIcon(".\\src\\Autores.png");
		Image img4 = icon4.getImage().getScaledInstance(130, 26, Image.SCALE_SMOOTH);
		btnAuthors.setIcon(new ImageIcon(img4));
		btnAuthors.setPreferredSize(new Dimension(130, 26));
		btnAuthors.setSize(new Dimension(141, 31));
		btnAuthors.setBorderPainted(false);
		btnAuthors.setOpaque(false);
		btnAuthors.setContentAreaFilled(false);
		panel.add(btnAuthors);

		btnVersion = new JButton();
		ImageIcon icon5 = new ImageIcon(".\\src\\Version.png");
		Image img5 = icon5.getImage().getScaledInstance(130, 26, Image.SCALE_SMOOTH);
		btnVersion.setIcon(new ImageIcon(img5));
		btnVersion.setPreferredSize(new Dimension(130, 26));
		btnVersion.setSize(new Dimension(141, 31));
		btnVersion.setBorderPainted(false);
		btnVersion.setOpaque(false);
		btnVersion.setContentAreaFilled(false);
		panel.add(btnVersion);

		btnExit = new JButton();
		ImageIcon icon6 = new ImageIcon(".\\src\\Salir.png");
		Image img6 = icon6.getImage().getScaledInstance(130, 26, Image.SCALE_SMOOTH);
		btnExit.setIcon(new ImageIcon(img6));
		btnExit.setPreferredSize(new Dimension(130, 26));
		btnExit.setSize(new Dimension(141, 31));
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
		ImageIcon imagen = new ImageIcon("PANDEMIC.png");
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
		lblNewLabel.setBounds(0, 11, 546, 136);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(".\\src\\PANDEMIC.png"));

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(".\\src\\fondo3.png"));
		lblNewLabel_1.setBounds(-14, 0, 1905, 1024);
		panel.add(lblNewLabel_1);

		ImageIcon imageIcon = new ImageIcon(".\\src\\Fondo.png");
		Image image = imageIcon.getImage();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

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

	// Crear una variable de instancia para almacenar el clip actual
	private Clip clipActual;

	private void musica(String Select) {
	    try {
	        // Detener el clip actual si está sonando
	        if (clipActual != null && clipActual.isRunning()) {
	            clipActual.stop();
	        }
	        
	        // Cargar el archivo de audio
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Select));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);

	        // Reproducir la música en bucle
	        clip.loop(Clip.LOOP_CONTINUOUSLY);

	        // Actualizar la variable de instancia del clip actual
	        clipActual = clip;
	    } catch (Exception ex) {
	        System.out.println("Error al reproducir la música: " + ex.getMessage());
	    }
	}

	
	private void sonido(String Select) {
		try {
			// Cargar el archivo de audio
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Select));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);

	        // Reproducir la música una vez
	        clip.start();
		} catch (Exception ex) {
			System.out.println("Error al reproducir la música: " + ex.getMessage());
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewGame) {
			
		    dispose(); // Cierra la ventana actual
		    menu2 frame = new menu2();
		    frame.setVisible(true);

			String Select=".\\src\\Select1.wav";
			String Select2=".\\src\\menu1.wav";
			sonido(Select);
			musica(Select2);

		} else if (e.getSource() == btnLoadGame) {
			JFrame newGameFrame = new JFrame("Cargar Partida");
			newGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			newGameFrame.addWindowListener(new WindowAdapter() {
			    @Override
			    public void windowClosing(WindowEvent e) {
			        String Select=".\\src\\menu2.wav";
			        musica(Select);
			        super.windowClosing(e);
			    }
			});
			String Select=".\\src\\Select1.wav";
			String Select2=".\\src\\menu1.wav";
			sonido(Select);
			musica(Select2);
			// Creamos un panel superior para el texto
			JPanel panelSuperior = new JPanel();
			panelSuperior.setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
			JLabel lblNewGame = new JLabel(
					"<html><div style='text-align: center; color: black;'>Partida cargada.</div></html>");
			lblNewGame.setFont(new Font("Tahoma", Font.PLAIN, 24));
			panelSuperior.add(lblNewGame);

			// Creamos el panel principal
			JPanel panelPrincipal = new JPanel(new BorderLayout());
			ImageIcon imageIcon = new ImageIcon(".\\src\\cyber2.png");
			JLabel imageLabel = new JLabel(imageIcon);
			panelPrincipal.add(imageLabel, BorderLayout.CENTER);

			// Agregamos los paneles al frame
			newGameFrame.getContentPane().add(panelSuperior, BorderLayout.NORTH);
			newGameFrame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);

			newGameFrame.pack(); 
			newGameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			newGameFrame.setLocationRelativeTo(null);
			newGameFrame.setVisible(true);
		} else if (e.getSource() == btnInfo) {
			String Select=".\\src\\Select1.wav";
			String Select2=".\\src\\menu1.wav";
			sonido(Select);
			musica(Select2);
			String nombre_txt = "Info.txt";
			cargar_texto(nombre_txt);
			
		} else if (e.getSource() == btnScores) {
			 
			
			
			
			
			String Select=".\\src\\Select1.wav";
			String Select2=".\\src\\menu1.wav";
			sonido(Select);
			musica(Select2);
			String nombre_txt = "ranking.txt";
			cargar_texto(nombre_txt);

		}
		if (e.getSource() == btnAuthors) {
			String Select=".\\src\\Select1.wav";
			String Select2=".\\src\\menu1.wav";
			sonido(Select);
			musica(Select2); 
			String nombre_txt = "Autores.txt";
			cargar_texto(nombre_txt);
		}
 
		else if (e.getSource() == btnVersion) {
			String Select=".\\src\\Select1.wav";
			String Select2=".\\src\\menu1.wav";
			sonido(Select);
			musica(Select2); 
			String nombre_txt = "Version.txt";
			cargar_texto(nombre_txt);
		} else if (e.getSource() == btnExit) {

			System.exit(0);
		}
		
	}

	private void cargar_texto(String nombre_txt) {
		// TODO Auto-generated method stub
		try {
			// Abrir el archivo Autores.txt utilizando FileReader y BufferedReader
			FileReader fr = new FileReader(nombre_txt);
			BufferedReader br = new BufferedReader(fr);

			// Leer todo el contenido del archivo y guardarlo en una variable de tipo String
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			String infoText = sb.toString();

			// Establecer el tamaño y la fuente del texto
			Font font = new Font("Comic Sans MS", Font.PLAIN, 20);
			UIManager.put("OptionPane.messageFont", font);

			// Mostrar el contenido del archivo en un JOptionPane
			ImageIcon icon = new ImageIcon();
			JOptionPane.showMessageDialog(null, infoText, "", JOptionPane.INFORMATION_MESSAGE, icon);


			// Cerrar el BufferedReader y el FileReader
			br.close();
			fr.close();
			String Select=".\\src\\Select1.wav";
			sonido(Select);

			String Select1=".\\src\\menu2.wav";
			musica(Select1);
			       
			    

		} catch (IOException ex) {
			// Si ocurre un error al abrir o leer el archivo, mostrar un mensaje de error
			JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo Autores.txt", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new menu1();
	}
	public int getThisExtendedState() {
		return getExtendedState();
	}
	public void setThisExtendedState(int extendedState) {
		setExtendedState(extendedState);
	}
}
