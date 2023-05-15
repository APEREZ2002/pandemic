import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//Creamos la clase LeerYModificarXML
public class LeerYModificarXML {
public static int[] main() {
    	
    // Creamos un objeto Scanner para leer la entrada del usuario
    Scanner scanner = new Scanner(System.in);
    
    // Inicializamos un arreglo para almacenar los parámetros
    int[] parametros2 = new int[4];
    
    try {
        // Abrimos el archivo XML que contiene los parámetros
        File archivoXML = new File("parametros.xml");
        
        // Creamos un objeto DocumentBuilderFactory para obtener un DocumentBuilder
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        
        // Parseamos el archivo XML y obtenemos un objeto Document
        Document doc = dBuilder.parse(archivoXML);
        doc.getDocumentElement().normalize();

        // Creamos un ciclo que se ejecuta hasta que el usuario decide salir
        boolean salir = false;
        do {

            // Creamos una variable para almacenar la opción seleccionada por el usuario
            String opcion = "1"; 

            // Evaluamos la opción seleccionada por el usuario
            switch (opcion) {
                case "1":
                    // Obtenemos los nodos que contienen los parámetros y leemos su valor
                    NodeList nodeList = doc.getElementsByTagName("parametros");
                    Node node = nodeList.item(0);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                    	int numCiudadesInfectadasInicio = Integer.parseInt(node.getChildNodes().item(1).getTextContent());
                    	int numCuidadesInfectadasRonda = Integer.parseInt(node.getChildNodes().item(3).getTextContent());
                    	int numEnfermedadesActivasDerrota = Integer.parseInt(node.getChildNodes().item(5).getTextContent());
                    	int numBrotesDerrota = Integer.parseInt(node.getChildNodes().item(7).getTextContent());

                        // Almacenamos los parámetros en el arreglo parametros2
                        parametros2[0]=numCiudadesInfectadasInicio;
                        parametros2[1]=numCuidadesInfectadasRonda;
                        parametros2[2]=numEnfermedadesActivasDerrota;
                        parametros2[3]=numBrotesDerrota;

                    }
                    // Retornamos los parámetros
                    return parametros2;
			default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (!salir);
    } catch (Exception e) {
        // Imprimimos el stack trace en caso de que ocurra una excepción
        e.printStackTrace();
    }
    
    // Retornamos los parámetros
	return parametros2;
}

    }

