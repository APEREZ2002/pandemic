import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Paneles.audio;

public class Final extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;
    private String text;
    private int y = 600; // posición inicial de la línea de texto
    private Thread thread;
    private BufferedImage image; // la imagen a mostrar
    private BufferedImage image2; // la imagen a mostrar
    private BufferedImage image3; // la imagen a mostrar
    private ImageIcon icon; // el icono del GIF
    private ImageIcon icon2; // el icono del segundo GIF
 
	    public Final(String text) {
	        this.text = insertLineBreaks(text, 60);
	        setFont(new Font("Verdana", Font.BOLD, 30));
	        setForeground(Color.WHITE); 
	        setBackground(Color.BLACK);
	        thread = new Thread(this);
	
	        try {
	            image = ImageIO.read(new File(".\\src\\PANDEMIC.png"));
	            image2 = ImageIO.read(new File(".\\src\\cyber.png"));
	            image3 = ImageIO.read(new File(".\\src\\Logo1.png"));
	            icon = new ImageIcon(".\\src\\GIF.gif"); // carga el archivo GIF como un ImageIcon
	            icon2 = new ImageIcon(".\\src\\GIF.gif"); // carga el segundo archivo GIF como un ImageIcon
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	
	
	    }

	private String insertLineBreaks(String text, int maxLineLength) {
	    StringBuilder sb = new StringBuilder();
	    int i = 0;
	    while (i < text.length()) {
	        int nextLineBreak = Math.min(i + maxLineLength, text.length());
	        int lastSpace = text.lastIndexOf(' ', nextLineBreak);
	        if (lastSpace != -1 && lastSpace > i) {
	            nextLineBreak = lastSpace;
	        }
	        sb.append(text.substring(i, nextLineBreak));
	        if (nextLineBreak < text.length()) {
	            sb.append("\n");
	        }
	        i = nextLineBreak + 1;
	    }
	    return sb.toString();
	}

	public void start() {
		thread.start(); // inicia el hilo para el efecto de desplazamiento
	}

	@Override
	public void run() {
		while (y >= -5000) { // mientras la línea de texto no llegue al final de la pantalla
			try {
				Thread.sleep(10); // pausa para crear el efecto de desplazamiento
				y -= 1; // actualiza la posición de la línea de texto
				repaint(); // vuelve a pintar el panel
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static audio reproductor = new audio();

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    String[] lines = text.split("\n"); // divide el texto en líneas
	    int lineSpace = 50; // espacio entre líneas de texto
	    int imageSpacing = 50; // espacio entre imágenes
	    for (int i = 0; i < lines.length; i++) {
	        g.drawString(lines[i], 100, y + i * lineSpace); // dibuja cada línea en la posición correcta
	    }
	    g.drawImage(image, 100, y + lines.length * lineSpace + imageSpacing, null); // dibuja la primera imagen debajo del texto
	    g.drawImage(image2, 100, y + lines.length * lineSpace + imageSpacing * 2 + image.getHeight(), null); // dibuja la segunda imagen debajo de la primera
	    g.drawImage(image3, 100, y + lines.length * lineSpace + imageSpacing * 2 + image2.getHeight(), null); // dibuja la tercera imagen debajo de la segunda
	    g.drawImage(icon.getImage(), 100, y + lines.length * lineSpace + imageSpacing * 4 + image2.getHeight() + image3.getHeight(), null); // dibuja la cuarta imagen debajo de la tercera
	    g.drawImage(icon2.getImage(), 600, y + lines.length * lineSpace + imageSpacing * 4 + image2.getHeight() + image3.getHeight(), null); // dibuja la quinta imagen al lado de la cuarta
	}




	public static void main(String[] args) {
		String fin="Fin2.txt";
		String music=".\\src\\BadEnding.wav";
		inciar_Fin(fin,music);

	}

	public static void inciar_Fin(String fin,String music) {
	    // TODO Auto-generated method stub
	    reproductor.musica(music);
	    reproductor.ajustarVolumen(30);
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(fin));
	        String text = "";
	        String line = br.readLine();
	        while (line != null) {
	            text += line + "\n"; // lee todo el archivo y lo guarda como una cadena de texto
	            line = br.readLine();
	        }
	        br.close();
	        JFrame frame = new JFrame("Star Wars Scroll");
	        frame.setUndecorated(true); // oculta la barra de título y bordes
	        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // establece el tamaño a pantalla completa
	        Final panel = new Final(text);
	        frame.add(panel);
	        frame.setVisible(true);
	        panel.start(); // inicia el efecto de desplazamiento

	     // Agregar un KeyListener al JFrame
	        frame.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	                    reproductor.detenerMusica(); // detiene la reproducción de música al cerrar la ventana
	                    frame.dispose(); // cierra la ventana actual
	                    menu_Principal menu = new menu_Principal(); // crea una instancia de la clase menu_Principal
	                }
	            }
	        });

	        frame.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                reproductor.detenerMusica(); // detiene la reproducción de música al cerrar la ventana
	                System.exit(0); // cierra la aplicación
	            
	            }
	        });
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}

}
