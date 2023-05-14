package Paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class panel_InsertarNombre extends JPanel {
	// Variables de clase
	private JTextField textField; // Campo de texto para insertar el nombre
	private JButton botonGuardar; // Botón para guardar el nombre
	private String Nombre; // Variable donde se guardará el nombre
	private JLabel labelResultado; // Etiqueta para mostrar el resultado
	private BufferedImage backgroundImage; // Imagen de fondo para el panel
	audio reproductor = new audio(); // Objeto para reproducir sonidos

	// Constructor de la clase
	public panel_InsertarNombre() { 
		
	    // Configuración del panel
	    setPreferredSize(new Dimension(300, 300));
	    setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

	    // Configuración del label
	    JLabel label = new JLabel("INSERTA TU NOMBRE:"); 
	    add(label);

	    // Configuración del textField
	    textField = new JTextField(20);
	    textField.setOpaque(false);
	    textField.setBackground(new Color(255, 255, 255, 200)); // 200 es la transparencia
	    add(textField);

	    // Configuración del botón
	    botonGuardar = new JButton("Guardar");
	    try {
	        BufferedImage image = ImageIO.read(new File("src/boton_Guardar.png"));
	        Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	        ImageIcon icon = new ImageIcon(scaledImage);

	        botonGuardar = new JButton("Guardar", icon);
	        botonGuardar.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
	        botonGuardar.setContentAreaFilled(false); // Hace que el botón sea transparente
	        botonGuardar.setHorizontalAlignment(JButton.CENTER); // Alinea la imagen al centro del botón
	    } catch (Exception ex) {
	        botonGuardar = new JButton("Guardar");
	    }
	    botonGuardar.addActionListener(e -> {
	    	reproductor.sonido(".\\src\\Select1.wav");
	        int opcion = JOptionPane.showConfirmDialog(null, "Ten en cuenta que solo puedes guardar 2 veces por nombre ¿Estás seguro que quieres continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
	        if (opcion == JOptionPane.YES_OPTION) {
	        	reproductor.sonido(".\\src\\Select1.wav");
	            guardar();	
	        }else {
	        	reproductor.sonido(".\\src\\Select1.wav");
	        }
	    });
	    botonGuardar.setBorder(null);
	    add(botonGuardar);

	    // Configuración del labelResultado
	    labelResultado = new JLabel();
	    add(labelResultado);

	    // Deshabilitar el panel
	    disablePanel();
	    
	    // Configuración del KeyListener para el textField
	    textField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {

	                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                	reproductor.sonido(".\\src\\Select1.wav");
			        	int opcion = JOptionPane.showConfirmDialog(null, "Ten en cuenta que solo puedes guardar 2 veces por nombre ¿Estás seguro que quieres continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
			        	if (opcion == JOptionPane.YES_OPTION) {
			        		reproductor.sonido(".\\src\\Select1.wav");
			        		guardar();	
			        	}else {
			        		reproductor.sonido(".\\src\\Select1.wav");
			        	}
	                    
	                }	
	        	}
	    });

	        try {
	            backgroundImage = ImageIO.read(new File("src/panel_1.png"));
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	    }
	// Método para deshabilitar el panel
    private void disablePanel() {
        setEnabled(false);
    }
 // Método para habilitar el panel
    private void enablePanel() {
        setEnabled(true);
    }
 // Método para obtener el nombre ingresado en un campo de texto
    public String getNombre() {
        return textField.getText();
    }
 // Método para guardar el nombre ingresado y cerrar el diálogo
    private void guardar() {
    	reproductor.sonido(".\\src\\Select1.wav");
        Nombre = textField.getText();
        if (Nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes insertar un nombre");
        } else {
            labelResultado.setText("Nombre: " + Nombre);

            // Obtener la ventana padre del panel
            JDialog dialogo = (JDialog) SwingUtilities.getWindowAncestor(this);

    		reproductor.sonido(".\\src\\Select1.wav");
    		
            // Cerrar el JDialog
            dialogo.dispose();
        }
    }



 // Método para pintar el componente, que dibuja la imagen de fondo si existe
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int width = getWidth();
            int height = getHeight();
            Image scaledImage = backgroundImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, null);
        }
    }
}
