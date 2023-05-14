package Botones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Paneles.audio;

public class boton_Ciudad extends JButton {

	// Constructor de la clase
	public boton_Ciudad() {
		// Se llama al constructor de la clase padre con el texto del botón
		super("Botón de Ciudad");
		// Se crea un objeto de la clase "audio"
		audio reproductor = new audio();
		// Se agrega un ActionListener al botón
		this.addActionListener(new ActionListener() {
			// Método que se ejecuta al presionar el botón
			@Override
			public void actionPerformed(ActionEvent e) {
				// Se reproduce un sonido utilizando el objeto "reproductor"
				reproductor.sonido(".\\src\\Select1.wav");
			}
		});
		// Se configura la opacidad del botón y se quita el borde
		this.setOpaque(false);
		this.setBorderPainted(false);
	}

}
