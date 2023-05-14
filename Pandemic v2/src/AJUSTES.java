
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.Color;
import java.awt.Desktop;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Paneles.audio;

public class AJUSTES extends JFrame implements ActionListener {
	// iniciacion de variables
	private JTextField txtNumCiudadesInfectadasInicio;
	private JTextField txtNumCuidadesInfectadasRonda;
	private JTextField txtNumEnfermedadesActivasDerrota;
	private JTextField txtNumBrotesDerrota;
	private int[] parametros;
	audio reproductor = new audio();

	// el main abre el panel AJUSETES
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AJUSTES frame = new AJUSTES();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AJUSTES() {
		setIconImage(new ImageIcon(".\\src\\Logo1.png").getImage());
		reproductor.musica(".\\src\\menu_Principal.wav"); // Iniciar la cancion
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300); // aqui se asigna el tamaño y posicion de la ventana
		setResizable(false); // esto hace que no se pueda cambiar el tamaño de la ventana

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		setContentPane(panel);
		panel.setBackground(Color.GRAY);

		// Crea el panel de ciudades infectadas incio
		JPanel panel1 = new JPanel();
		JLabel lblNumCiudadesInfectadasInicio = new JLabel("Número de ciudades infectadas al inicio:");
		txtNumCiudadesInfectadasInicio = new JTextField(10);
		Color colorCustom = new Color(89, 200, 226);
		panel1.setBackground(colorCustom);
		panel1.add(lblNumCiudadesInfectadasInicio);
		panel1.add(txtNumCiudadesInfectadasInicio);
		panel.add(panel1);

		// Crea el panel de ciudades infectadas por ronda
		JPanel panel2 = new JPanel();
		JLabel lblNumCuidadesInfectadasRonda = new JLabel("Número de ciudades infectadas por ronda:");
		txtNumCuidadesInfectadasRonda = new JTextField(10);
		panel2.setBackground(colorCustom);
		panel2.add(lblNumCuidadesInfectadasRonda);
		panel2.add(txtNumCuidadesInfectadasRonda);
		panel.add(panel2);

		// Crea el panel de ciudades infectadas activas para perder
		JPanel panel3 = new JPanel();
		JLabel lblNumEnfermedadesActivasDerrota = new JLabel("Número de enfermedades activas para perder:");
		txtNumEnfermedadesActivasDerrota = new JTextField(10);
		panel3.setBackground(colorCustom);
		panel3.add(lblNumEnfermedadesActivasDerrota);
		panel3.add(txtNumEnfermedadesActivasDerrota);
		panel.add(panel3);

		// Crea el panel de brotes para perder
		JPanel panel4 = new JPanel();
		JLabel lblNumBrotesDerrota = new JLabel("Número de brotes para perder:");
		txtNumBrotesDerrota = new JTextField(10);
		panel4.setBackground(colorCustom);
		panel4.add(lblNumBrotesDerrota);
		panel4.add(txtNumBrotesDerrota);
		panel.add(panel4);
		// Crea el panel y el boton para ver los parametros actuales
		JPanel panel5 = new JPanel();
		JButton btnLeerParametros = new JButton("Ver Parámetros");
		btnLeerParametros.addActionListener(this);
		panel5.setBackground(colorCustom);
		panel5.add(btnLeerParametros);
		panel.add(panel5);
		// Crea el panel y el boton para guardar los parametros
		JPanel panel6 = new JPanel();
		JButton btnGuardarParametros = new JButton("Guardar Parámetros");
		btnGuardarParametros.addActionListener(this);
		panel6.setBackground(colorCustom);
		panel6.add(btnGuardarParametros);
		panel.add(panel6);
		// crea el background
		JPanel panel7 = new JPanel();
		JButton btnJugar = new JButton("JUGAR");
		// Añadir la funcionalidad al boton jugar
		btnJugar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				reproductor.sonido(".\\src\\Select1.wav");
				int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro que quieres jugar?", "Confirmación",
						JOptionPane.YES_NO_OPTION);// aparece un texto en pantalla para confirmar que quieres realizar
													// la accion
				if (opcion == JOptionPane.YES_OPTION) {
					reproductor.sonido(".\\src\\Select1.wav");
					reproductor.detenerMusica();
					dispose(); // Cierra la ventana
					new mapa();
				}
				reproductor.sonido(".\\src\\Select1.wav");

			}
		});
		panel7.setBackground(colorCustom);
		panel7.add(btnJugar);
		panel.add(panel7); // añade todo al panel principal

		// asigna el texto escrito al texto de cada uno de los apartados
		parametros = leerParametros();
		if (parametros != null) {
			txtNumCiudadesInfectadasInicio.setText(Integer.toString(parametros[0]));
			txtNumCuidadesInfectadasRonda.setText(Integer.toString(parametros[1]));
			txtNumEnfermedadesActivasDerrota.setText(Integer.toString(parametros[2]));
			txtNumBrotesDerrota.setText(Integer.toString(parametros[3]));
		}
	}

	public void actionPerformed(ActionEvent e) {
		reproductor.sonido(".\\src\\Select1.wav");
		if (e.getActionCommand().equals("Ver Parámetros")) {
			parametros = leerParametros();
			if (parametros != null) {
				txtNumCiudadesInfectadasInicio.setText(Integer.toString(parametros[0]));
				txtNumCuidadesInfectadasRonda.setText(Integer.toString(parametros[1]));
				txtNumEnfermedadesActivasDerrota.setText(Integer.toString(parametros[2]));
				txtNumBrotesDerrota.setText(Integer.toString(parametros[3]));
			}
		} else if (e.getActionCommand().equals("Guardar Parámetros")) {
			// inicia el guardado de parametros
			int numCiudadesInfectadasInicio = 0;
			int numCuidadesInfectadasRonda = 0;
			int numEnfermedadesActivasDerrota = 0;
			int numBrotesDerrota = 0;

			// Leer los valores de los campos de texto
			try {
				numCiudadesInfectadasInicio = Integer.parseInt(txtNumCiudadesInfectadasInicio.getText());
				numCuidadesInfectadasRonda = Integer.parseInt(txtNumCuidadesInfectadasRonda.getText());
				numEnfermedadesActivasDerrota = Integer.parseInt(txtNumEnfermedadesActivasDerrota.getText());
				numBrotesDerrota = Integer.parseInt(txtNumBrotesDerrota.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Por favor ingrese valores numéricos válidos.");
				reproductor.sonido(".\\src\\Select1.wav");
				return;
			}

			// Validar los valores ingresados, si no cumple las condiciones asignadas te
			// muestra un mensaje en pantalla para avisarte de que numeros estan mal
			if (numCiudadesInfectadasInicio < 1 || numCiudadesInfectadasInicio > 15) {
				JOptionPane.showMessageDialog(null,
						"El número de ciudades infectadas al inicio debe estar entre 1 y 15.");
				reproductor.sonido(".\\src\\Select1.wav");
				return;
			}

			if (numCuidadesInfectadasRonda < 1 || numCuidadesInfectadasRonda > 10) {
				JOptionPane.showMessageDialog(null,
						"El número de ciudades infectadas por ronda debe estar entre 1 y 10.");
				reproductor.sonido(".\\src\\Select1.wav");
				return;
			}

			if (numEnfermedadesActivasDerrota < 16 || numEnfermedadesActivasDerrota > 48) {
				JOptionPane.showMessageDialog(null,
						"El número de enfermedades activas para perder debe estar entre 16 y 48.");
				reproductor.sonido(".\\src\\Select1.wav");
				return;
			}

			if (numBrotesDerrota < 6 || numBrotesDerrota > 20) {
				JOptionPane.showMessageDialog(null, "El número de brotes para perder debe estar entre 6 y 20.");
				reproductor.sonido(".\\src\\Select1.wav");
				return;
			}

			// Guardar los valores ingresados en el archivo de configuración
			guardarParametros(numCiudadesInfectadasInicio, numCuidadesInfectadasRonda, numEnfermedadesActivasDerrota,
					numBrotesDerrota);
			JOptionPane.showMessageDialog(null, "Los parámetros se han guardado correctamente.");
			reproductor.sonido(".\\src\\Select1.wav");

		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///// /////
	///// La funcion leerParametros(), se encarga de leer el archivo parametros.xml
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// y
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// mostrarlo
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// en
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// el
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// textArea
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// asignado
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// a
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// dicho
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// parametro
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// /////
	///// /////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private int[] leerParametros() {
		int[] parametros2 = new int[4];
		try {
			File archivoXML = new File("parametros.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivoXML);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("parametros");
			Node node = nodeList.item(0);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				int numCiudadesInfectadasInicio = Integer.parseInt(node.getChildNodes().item(1).getTextContent());
				int numCuidadesInfectadasRonda = Integer.parseInt(node.getChildNodes().item(3).getTextContent());
				int numEnfermedadesActivasDerrota = Integer.parseInt(node.getChildNodes().item(5).getTextContent());
				int numBrotesDerrota = Integer.parseInt(node.getChildNodes().item(7).getTextContent());

				parametros2[0] = numCiudadesInfectadasInicio;
				parametros2[1] = numCuidadesInfectadasRonda;
				parametros2[2] = numEnfermedadesActivasDerrota;
				parametros2[3] = numBrotesDerrota;

				return parametros2;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///// /////
	///// La funcion guardarParametros(), se encarga de guardar los nuevos
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// parametros
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// /////
	///// /////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void guardarParametros(int numCiudadesInfectadasInicio, int numCuidadesInfectadasRonda,
			int numEnfermedadesActivasDerrota, int numBrotesDerrota) {
		try {
			File archivoXML = new File("parametros.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivoXML);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("parametros");
			Node node = nodeList.item(0);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				node.getChildNodes().item(1).setTextContent(Integer.toString(numCiudadesInfectadasInicio));
				node.getChildNodes().item(3).setTextContent(Integer.toString(numCuidadesInfectadasRonda));
				node.getChildNodes().item(5).setTextContent(Integer.toString(numEnfermedadesActivasDerrota));
				node.getChildNodes().item(7).setTextContent(Integer.toString(numBrotesDerrota));

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(archivoXML);
				transformer.transform(source, result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
