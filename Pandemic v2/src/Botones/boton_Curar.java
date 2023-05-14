package Botones;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Paneles.audio;

public class boton_Curar extends JButton {
	// Se crea un constructor para la clase "boton_Curar".
	public boton_Curar(String ciudad) {
		// Se llama al constructor de la superclase "JButton" y se le pasa el texto que
		// tendrá el botón.
		super("Curar");
		// Se crea un objeto de la clase "audio".
		audio reproductor = new audio();
		// Se añade un ActionListener al botón para manejar el evento de clic.
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cuando se hace clic en el botón, se imprime un mensaje con la ciudad que se
				// ha curado.
				System.out.println("Has curado " + ciudad);
				// Se ajusta el volumen del reproductor de audio.
				reproductor.ajustarVolumen(20);
				// Se reproduce un sonido de curación.
				reproductor.sonido(".\\src\\curar.wav");
			}
		});
		// Se establece que el botón es opaco.
		this.setOpaque(true);
		// Se establece el color de fondo del botón como verde.
		this.setBackground(Color.GREEN);
	}

}
