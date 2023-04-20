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

public class menu2 extends JFrame implements ActionListener {

	private JButton btnExit;

	public menu2() {
 
		super("Nueva_Partida");
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


		btnExit = new JButton();
		ImageIcon icon6 = new ImageIcon(".\\src\\Salir.png");
		Image img6 = icon6.getImage().getScaledInstance(130, 26, Image.SCALE_SMOOTH);
		
		JButton btnNewButton = new JButton("Ronda+");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Menu_Nueva_Partida partida = new Menu_Nueva_Partida();
		        partida.main(new String[]{});
		    }
		});

		btnNewButton.setBounds(30, 29, 89, 23);
		panel.add(btnNewButton);
		btnExit.setIcon(new ImageIcon(img6));
		btnExit.setPreferredSize(new Dimension(130, 26));
		btnExit.setSize(new Dimension(141, 31));
		btnExit.setBorderPainted(false);
		btnExit.setOpaque(false);
		btnExit.setContentAreaFilled(false);
		panel.add(btnExit);
		btnExit.addActionListener(this);

		getContentPane().add(panel);

		// Agregar la imagen al JLabel
		ImageIcon imagen = new ImageIcon("PANDEMIC.png");
		getContentPane().add(panel);
		setVisible(true);

		// Setear las dimensiones y la posición de los botones


		Dimension exitButtonSize = new Dimension(103, 37);
		btnExit.setPreferredSize(exitButtonSize);
		btnExit.setSize(new Dimension(116, 45));
		btnExit.setLocation(549, 314);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Alumnat\\Downloads\\MENU1\\src\\cyber2.png"));
		lblNewLabel.setBounds(0, 0, 1280, 843);
		panel.add(lblNewLabel);

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


				// Actualizar tamaño y posición de los botones

				btnExit.setBounds(panelWidth - newButtonWidth - newButtonX, panelHeight - newButtonHeight - newButtonX,
						newButtonWidth, newButtonHeight);
			}
		});
		setExtendedState(Frame.MAXIMIZED_BOTH);
	}

	// Crear una variable de instancia para almacenar el clip actual
	private Clip clipActual;
	private JLabel lblNewLabel;

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
		
		
		if (e.getSource() == btnExit) {
		    dispose(); // Cerrar el menu2
		    menu1 menu = new menu1(); // Crear una instancia del menu1
		    menu.setVisible(true); // Mostrar el menu1
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
		new menu2();
	}
}
