package Paneles;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.AbstractBorder;

public class BORDES extends AbstractBorder {

	//Se define un identificador único para la clase
	private static final long serialVersionUID = 1L;

	//Se declaran las variables de instancia para el color y radio del borde
	private Color color;
	private int radius;

	//Constructor que recibe el color y radio del borde
	public BORDES(Color color, int radius) {
	    this.color = color;
	    this.radius = radius;
	}

	//Método para pintar el borde
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	    //Se establece el color del borde
	    g.setColor(color);
	    //Se dibuja el borde redondeado
	    g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
	}

	//Método para obtener los márgenes del borde
	public Insets getBorderInsets(Component c) {
	    return new Insets(radius + 1, radius + 1, radius + 2, radius);
	}

	//Método para obtener los márgenes del borde, utilizando un objeto Insets preexistente
	public Insets getBorderInsets(Component c, Insets insets) {
	    insets.left = insets.right = radius + 1;
	    insets.top = radius + 1;
	    insets.bottom = radius + 2;
	    return insets;
	}

	//Método que indica si el borde es opaco
	public boolean isBorderOpaque() {
	    return true;
	}

}
