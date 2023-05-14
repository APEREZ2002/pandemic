package Paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.RoundRectangle2D.Double;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class Bienvenido {

    private String mensaje;
	audio reproductor = new audio();

    public Bienvenido(String mensaje) {
        this.mensaje = mensaje;
    }

    public void mostrarPopup() {
        // Crear el diálogo sin barra de título
        JDialog dialog = new JDialog((Window) null);
        dialog.setUndecorated(true);

        // Establecer el diálogo como modal
        dialog.setModal(true);

        // Crear el panel de contenido del popup
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLUE);

        // Añadir una etiqueta con la imagen de fondo
        JLabel fondoLabel = new JLabel(new ImageIcon("src/panel_1.png"));
        Dimension fondoDimensiones = fondoLabel.getPreferredSize();
        panel.setPreferredSize(fondoDimensiones);
        panel.add(fondoLabel, BorderLayout.CENTER);

        // Añadir una etiqueta con el mensaje
        JLabel mensajeLabel = new JLabel("Bienvenido " + mensaje);
        mensajeLabel.setForeground(java.awt.Color.BLACK);
        mensajeLabel.setHorizontalAlignment(JLabel.CENTER);
        mensajeLabel.setVerticalAlignment(JLabel.CENTER);
        mensajeLabel.setPreferredSize(new Dimension(400, 100));
        fondoLabel.setLayout(new BorderLayout());
        fondoLabel.add(mensajeLabel, BorderLayout.CENTER);

        // Añadir un botón de Aceptar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(e -> {
        	reproductor.sonido(".\\src\\Select1.wav");
            dialog.dispose();
        });
        panel.add(aceptarButton, BorderLayout.SOUTH);

        // Añadir el panel al diálogo y mostrarlo centrado en la pantalla
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
 
    }
    
    public void mostrarPopup2(String archivo) {
    	// Crear el diálogo sin barra de título
        JDialog dialog = new JDialog((Window) null);
        dialog.setUndecorated(true);

        // Establecer el diálogo como modal
        dialog.setModal(true);
 
        // Crear el panel de contenido del popup
        JPanel panel = new JPanel(new BorderLayout());

     // Crear un borde compuesto con un borde de línea y un borde de fondo
        Border bordeLinea = BorderFactory.createLineBorder(new Color(144, 238, 144), 5); // verde oscuro
        Border bordeFondo = BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(144, 238, 144)); // verde oscuro
        Border bordePersonalizado = new CompoundBorder(bordeLinea, bordeFondo);


        // Añadir el borde al panel
        panel.setBorder(bordePersonalizado);

        // Cargar el archivo GIF y ajustarlo al tamaño del popup
        ImageIcon imagenIcon = new ImageIcon("src/fondo_ranking.jpg");
        Image imagen = imagenIcon.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        imagenIcon = new ImageIcon(imagen);

        // Añadir una etiqueta con la imagen de fondo
        JLabel fondoLabel = new JLabel(imagenIcon);
        panel.add(fondoLabel, BorderLayout.CENTER);

        // Leer el archivo de texto y mostrarlo en una etiqueta de texto
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("<br>");
            }
            String contenido = sb.toString();
            JLabel mensajeLabel = new JLabel("<html>" + contenido + "</html>");
            Color custom = new Color(144, 238, 144);
            mensajeLabel.setForeground(custom);
            mensajeLabel.setHorizontalAlignment(JLabel.CENTER);
            mensajeLabel.setVerticalAlignment(JLabel.CENTER);
            Font font = new Font("Arial", Font.BOLD, 20);
            mensajeLabel.setFont(font);
            fondoLabel.setLayout(new BorderLayout());
            fondoLabel.add(mensajeLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            System.err.format("Error al leer el archivo: %s%n", e);
        }

        // Añadir un botón de Aceptar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(e -> dialog.dispose());
        
        panel.add(aceptarButton, BorderLayout.SOUTH);

        // Ajustar el tamaño del diálogo
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setSize(600, 600);

        // Ajustar el grosor del borde redondeado
        int grosor = 50;
        Double forma = new RoundRectangle2D.Double(0, 0, dialog.getWidth(), dialog.getHeight(), grosor, grosor);
        dialog.setShape(forma);

        // Añadir el panel al diálogo y mostrarlo centrado en la pantalla
        
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
}
