
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Paneles.audio;

public class PanelPartidas extends JFrame {
 
	private JTextArea textArea;
	private JTextField textField;
	audio reproductor = new audio();
	public int contadorKonami = 0;

	// Constructor de la clase PanelPartidas
	public PanelPartidas() {
	    // Inicialización del reproductor de audio y de la variable contadorKonami
	    reproductor.musica(".\\src\\menu_Principal.wav");
	    contadorKonami = 0;

	    // Configuración de la ventana
	    setTitle("Buscar nombre en archivo");
	    setSize(600, 600);
	    setLocationRelativeTo(null); // Centrar ventana en la pantalla
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
	    setUndecorated(true); // Eliminar bordes y barra de título

	    // Cargar imagen de fondo y crear JLabel para contenerla
	    try {
	        Image imagen = ImageIO.read(new File(".\\src\\fondo_ranking.jpg"));
	        JLabel fondo = new JLabel(new ImageIcon(imagen.getScaledInstance(600, 600, Image.SCALE_SMOOTH)));
	        add(fondo, BorderLayout.CENTER);
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        getContentPane().setBackground(Color.GRAY); // Establecer color de fondo por defecto si la imagen no se carga
	    }

	    // Configuración del panel superior
	    JPanel panelSuperior = new JPanel(new BorderLayout()); 
	    JLabel label = new JLabel("Escriba el nombre a buscar: ");
	    textField = new JTextField(20);
	    JButton button = new JButton("Buscar");
	    panelSuperior.add(label, BorderLayout.WEST);
	    panelSuperior.add(textField, BorderLayout.CENTER);
	    panelSuperior.add(button, BorderLayout.EAST);
	    // Botón para borrar contenido del archivo
	    JButton borrarButton = new JButton("Borrar");
	    panelSuperior.add(borrarButton, BorderLayout.WEST);

	    // Configuración del panel inferior
	    JPanel panelInferior = new JPanel(new BorderLayout());
	    panelInferior.setBackground(Color.GRAY);
	    panelInferior.setOpaque(false);
	    JTextArea textArea = new JTextArea();
	    textArea.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(textArea);

	    // Lectura del archivo "Partidas.txt" y muestra de su contenido en el panel inferior
	    try {
	        BufferedReader br = new BufferedReader(new FileReader("Partidas.txt"));
	        String line;
	        while ((line = br.readLine()) != null) {
	            textArea.append(line + "\n");
	        }
	        br.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    textArea.setBackground(Color.lightGray);

	    panelInferior.add(scrollPane);
	    
	    // Añadir paneles a la ventana
	    add(panelSuperior, BorderLayout.NORTH);
	    add(panelInferior, BorderLayout.SOUTH);


     // Acción del botón de buscar
        button.addActionListener(e -> {
			reproductor.sonido(".\\src\\Select1.wav");
            String nombre = null;
            boolean encontrado = false; // bandera para indicar si se ha encontrado el nombre
            try (BufferedReader br = new BufferedReader(new FileReader("Partidas.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null && !encontrado) { // se detiene cuando se encuentra el nombre
                    if (linea.contains("-")) {
                        String[] partes = linea.split("-");
                        if (partes[1].trim().equals(textField.getText())) {
                            nombre = partes[1].trim();
                            encontrado = true; // se ha encontrado el nombre, se cambia el valor de la bandera
                        }
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (!encontrado) { // si no se encontró el nombre, se muestra un mensaje de error
                JOptionPane.showMessageDialog(this, "El nombre no se encontró en el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                reproductor.sonido(".\\src\\Select1.wav");
            } else { // si se encontró el nombre, se muestra un mensaje de éxito y se realizan otras acciones
            	reproductor.sonido(".\\src\\Egg.wav");
            	JOptionPane.showMessageDialog(this, "El nombre '" + nombre + "' fue encontrado en el archivo", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                String Select=".\\src\\Select1.wav";
                reproductor.sonido(Select);

                try {
                    ConexionBD.Cargar(nombre);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                dispose(); // Cierra la ventana actual
                reproductor.detenerMusica();
            }
        });

   
        borrarButton.addActionListener(e -> {
        	reproductor.sonido(".\\src\\Select1.wav");
            String nombre = textField.getText();
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea borrar todo el contenido del archivo?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                try {
    				ConexionBD.Borrar(nombre);
    			} catch (SQLException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}	
        		dispose(); // Cierra la ventana actual
        		reproductor.detenerMusica();            
        		new PanelPartidas();
            }
        	reproductor.sonido(".\\src\\Select1.wav");



        });
       
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Verificar si el Konami code se ha ingresado
                if (e.getKeyCode() == KeyEvent.VK_UP && contadorKonami == 0) { 
                    contadorKonami++;
                } else if (e.getKeyCode() == KeyEvent.VK_UP && contadorKonami == 1) {
                    contadorKonami++;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && contadorKonami == 2) {
                    contadorKonami++;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && contadorKonami == 3) {
                    contadorKonami++;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT && contadorKonami == 4) {
                    contadorKonami++;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && contadorKonami == 5) {
                    contadorKonami++;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT && contadorKonami == 6) {
                    contadorKonami++;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && contadorKonami == 7) {
                    contadorKonami++;
                } else if (e.getKeyCode() == KeyEvent.VK_B && contadorKonami == 8) {
                    contadorKonami++;
                } else if (e.getKeyCode() == KeyEvent.VK_A && contadorKonami == 9) {
                    contadorKonami++;
                } else {
                    contadorKonami = 0; // Reiniciar el contador si se ingresó un código incorrecto
                }

                // Verificar si se ha ingresado el código Konami
                if (contadorKonami == 10) {
                	reproductor.sonido(".\\src\\Egg.wav");
                    JOptionPane.showMessageDialog(null, "¡Código Konami ingresado con éxito!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    reproductor.detenerMusica();
                    try {
                        Runtime.getRuntime().exec("cmd /c start .\\src\\Egg.exe"); //Ejecuta el secreto
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    contadorKonami = 0; // Reiniciar el contador
                }

            }
        });

        
        // Botón de volver
        JButton volverButton = new JButton("Volver");
        panelSuperior.add(volverButton, BorderLayout.SOUTH);
        volverButton.addActionListener(e -> {
        	reproductor.sonido(".\\src\\Select1.wav");
            dispose(); // Cierra la ventana actual
            reproductor.detenerMusica();
            new menu_Principal();
        });
        
        // Mostrar ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        new PanelPartidas();
    }
}

