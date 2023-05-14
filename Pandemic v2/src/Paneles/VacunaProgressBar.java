package Paneles;
import javax.swing.*;
import java.awt.*;

public class VacunaProgressBar extends JPanel {
    private final JProgressBar progressBar;
    private final JButton botonVacunar;
	public static audio reproductor = new audio();

	//Crea la barra de cada vacuna del colo que le toca dependiendo del nombre de la enferemdad que tiene
    public VacunaProgressBar(int vacuna,String Nombre, int nVacuna) {
    	int valor;
    	if(Nombre=="delta") {
    	    progressBar = new JProgressBar(0, 100);
    	    progressBar.setValue(vacuna);
    	    progressBar.setPreferredSize(new Dimension(100, 10));
    	    progressBar.setForeground(Color.BLUE);
    	    progressBar.setBorder(BorderFactory.createLineBorder(Color.BLUE));

    	    botonVacunar = new JButton();
    	    botonVacunar.setPreferredSize(new Dimension(90, 40));
    	    botonVacunar.setLayout(new BorderLayout());
    	    botonVacunar.setBorder(BorderFactory.createLineBorder(Color.BLUE));

    	    ImageIcon icono = new ImageIcon(".\\src\\VACUNA_1.png");
    	    JLabel etiquetaIcono = new JLabel(icono);
    	    botonVacunar.add(etiquetaIcono, BorderLayout.CENTER);
 
    	    setLayout(new BorderLayout());
    	    add(progressBar, BorderLayout.CENTER);
    	    add(botonVacunar, BorderLayout.SOUTH);	
    	    valor=nVacuna;
    	}else if(Nombre=="alfa") {
    	    progressBar = new JProgressBar(0, 100);
    	    progressBar.setValue(vacuna);
    	    progressBar.setPreferredSize(new Dimension(100, 10));
    	    progressBar.setForeground(Color.RED);
    	    progressBar.setBorder(BorderFactory.createLineBorder(Color.RED));

    	    botonVacunar = new JButton();
    	    botonVacunar.setPreferredSize(new Dimension(90, 40));
    	    botonVacunar.setLayout(new BorderLayout());
    	    botonVacunar.setBorder(BorderFactory.createLineBorder(Color.RED));

    	    ImageIcon icono = new ImageIcon(".\\src\\VACUNA_2.png");
    	    JLabel etiquetaIcono = new JLabel(icono);
    	    botonVacunar.add(etiquetaIcono, BorderLayout.CENTER);

    	    setLayout(new BorderLayout());
    	    add(progressBar, BorderLayout.CENTER);
    	    add(botonVacunar, BorderLayout.SOUTH);	
    	    valor=nVacuna;

    	}else if(Nombre=="beta") {
    	    progressBar = new JProgressBar(0, 100);
    	    progressBar.setValue(vacuna);
    	    progressBar.setPreferredSize(new Dimension(100, 10));
    	    progressBar.setForeground(Color.GREEN);
    	    progressBar.setBorder(BorderFactory.createLineBorder(Color.GREEN));

    	    botonVacunar = new JButton();
    	    botonVacunar.setPreferredSize(new Dimension(90, 40));
    	    botonVacunar.setLayout(new BorderLayout());
    	    botonVacunar.setBorder(BorderFactory.createLineBorder(Color.GREEN));

    	    ImageIcon icono = new ImageIcon(".\\src\\VACUNA_3.png");
    	    JLabel etiquetaIcono = new JLabel(icono);
    	    botonVacunar.add(etiquetaIcono, BorderLayout.CENTER);

    	    setLayout(new BorderLayout());
    	    add(progressBar, BorderLayout.CENTER);
    	    add(botonVacunar, BorderLayout.SOUTH);	
    	    valor=nVacuna;

    	}else if(Nombre=="gamma") {
    	    progressBar = new JProgressBar(0, 100);
    	    progressBar.setValue(vacuna);
    	    progressBar.setPreferredSize(new Dimension(100, 10));
    	    progressBar.setForeground(Color.YELLOW);
    	    progressBar.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

    	    botonVacunar = new JButton();
    	    botonVacunar.setPreferredSize(new Dimension(90, 40));
    	    botonVacunar.setLayout(new BorderLayout());
    	    botonVacunar.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
    	    
    	    ImageIcon icono = new ImageIcon(".\\src\\VACUNA_4.png");
    	    JLabel etiquetaIcono = new JLabel(icono);
    	    botonVacunar.add(etiquetaIcono, BorderLayout.CENTER);

    	    setLayout(new BorderLayout());
    	    add(progressBar, BorderLayout.CENTER);
    	    add(botonVacunar, BorderLayout.SOUTH);	
    	    valor=nVacuna;

    	}else {
            progressBar = new JProgressBar(0, 100);
            progressBar.setValue(vacuna);
            progressBar.setPreferredSize(new Dimension(100, 10));
            progressBar.setForeground(Color.GRAY);

            botonVacunar = new JButton(Nombre);
            botonVacunar.setPreferredSize(new Dimension(90, 40));

            setLayout(new BorderLayout());
            add(progressBar, BorderLayout.CENTER);
            add(botonVacunar, BorderLayout.SOUTH);	
            valor=nVacuna;
    	}
    	

    	

    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JButton getBotonVacunar() {
        return botonVacunar;
    }
}
