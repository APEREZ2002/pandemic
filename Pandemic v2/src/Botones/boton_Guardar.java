package Botones;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Paneles.audio;

public class boton_Guardar extends JButton {
	public boton_Guardar(String string) {
		// Si el botón es de GUARDAR, se carga la imagen correspondiente
		if (string == "GUARDAR") {
			// Se carga la imagen original desde el archivo
			ImageIcon iconoOriginal = new ImageIcon(".\\src\\Botones\\guardar.png");
			Image imagenOriginal = iconoOriginal.getImage();
			// Se obtiene el ancho y alto del botón
			int anchoBoton = this.getPreferredSize().width;
			int altoBoton = this.getPreferredSize().height;
			// Se redimensiona la imagen al tamaño del botón
			Image imagenRedimensionada = imagenOriginal.getScaledInstance(anchoBoton, altoBoton, Image.SCALE_SMOOTH);
			// Se crea un nuevo ImageIcon con la imagen redimensionada
			ImageIcon icono = new ImageIcon(imagenRedimensionada);
			// Se establece el icono del botón con el ImageIcon creado
			this.setIcon(icono);

			// Se crea un objeto audio para reproducir el sonido cuando se presiona el botón
			audio reproductor = new audio();
			// Se agrega un ActionListener al botón que reproduce el sonido cuando se
			// presiona
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					reproductor.sonido(".\\src\\Select1.wav");
				}
			});
		}
		// Si el botón es de SALIR, se carga la imagen correspondiente
		else {
			// Se carga la imagen original desde el archivo
			ImageIcon iconoOriginal = new ImageIcon(".\\src\\Botones\\salir1.png");
			Image imagenOriginal = iconoOriginal.getImage();
			// Se obtiene el ancho y alto del botón
			int anchoBoton = this.getPreferredSize().width;
			int altoBoton = this.getPreferredSize().height;
			// Se redimensiona la imagen al tamaño del botón
			Image imagenRedimensionada = imagenOriginal.getScaledInstance(anchoBoton, altoBoton, Image.SCALE_SMOOTH);
			// Se crea un nuevo ImageIcon con la imagen redimensionada
			ImageIcon icono = new ImageIcon(imagenRedimensionada);
			// Se establece el icono del botón con el ImageIcon creado
			this.setIcon(icono);

			// Se crea un objeto audio para reproducir el sonido cuando se presiona el botón
			audio reproductor = new audio();
			// Se agrega un ActionListener al botón que reproduce el sonido cuando se
			// presiona
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					reproductor.sonido(".\\src\\Select1.wav");
				}
			});
		}

		// Se establecen algunas propiedades del botón
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(80, 30);
	}
}
