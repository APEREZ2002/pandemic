import java.util.ArrayList;
import java.util.List;

public class UBICACION_MANAGER {
    
    public static ArrayList<UBICACION> crearUbicaciones() {
        ArrayList<UBICACION> ubicaciones = new ArrayList<>();
        // UBICACION guarda en un arraylist los siguientes datos:Coordenada X e Y, nombre de la ciudad,enfermedad asignada,el estado de la enferemdad(por defecto false), el nivel enferemdad(por defecto 0)
        ubicaciones.add(new UBICACION(235, 315, "San Francisco", 0,false,0));
        ubicaciones.add(new UBICACION(300, 280, "Chicago", 0,false,0));
        ubicaciones.add(new UBICACION(320, 320, "Atlanta", 0,false,0));
        ubicaciones.add(new UBICACION(350, 280, "Montreal", 0,false,0));
        ubicaciones.add(new UBICACION(380, 290, "Nueva York", 0,false,0));
        ubicaciones.add(new UBICACION(360, 330, "Washington", 0,false,0));
        ubicaciones.add(new UBICACION(700,230, "Londres", 0,false,0));
        ubicaciones.add(new UBICACION(687,290, "Madrid", 0,false,0));
        ubicaciones.add(new UBICACION(727,250, "Paris", 0,false,0));
        ubicaciones.add(new UBICACION(755,190, "Essen", 0,false,0));
        ubicaciones.add(new UBICACION(755,235, "Milan", 0,false,0));
        ubicaciones.add(new UBICACION(815,210, "San Petersburgo", 0,false,0));
        ubicaciones.add(new UBICACION(275,355, "Los Angeles", 3,false,0));
        ubicaciones.add(new UBICACION(380,360, "Miami", 3,false,0));
        ubicaciones.add(new UBICACION(300,385, "Mexico DF", 3,false,0));
        ubicaciones.add(new UBICACION(400,460, "Bogota", 3,false,0));
        ubicaciones.add(new UBICACION(395,520, "Lima", 3,false,0));
        ubicaciones.add(new UBICACION(430,620, "Santiago de Chile", 3,false,0));
        ubicaciones.add(new UBICACION(453,670, "Buenos Aires", 3,false,0));
        ubicaciones.add(new UBICACION(520,570, "Sao Paulo", 3,false,0));
        ubicaciones.add(new UBICACION(710,450, "Lagos", 3,false,0));
        ubicaciones.add(new UBICACION(770,540, "Kinsasa", 3,false,0));
        ubicaciones.add(new UBICACION(815,450, "Jartum", 3,false,0));
        ubicaciones.add(new UBICACION(815,630, "Johannesburgo", 3,false,0));
        ubicaciones.add(new UBICACION(730,330, "Argel", 2,false,0));
        ubicaciones.add(new UBICACION(820,350, "El Cairo", 2,false,0));
        ubicaciones.add(new UBICACION(895,385, "Riad", 2,false,0));
        ubicaciones.add(new UBICACION(830,294, "Estambul", 2,false,0));
        ubicaciones.add(new UBICACION(880,320, "Bagdad", 2,false,0));
        ubicaciones.add(new UBICACION(890,230, "Moscu", 2,false,0));
        ubicaciones.add(new UBICACION(920,310, "Teheran", 2,false,0));
        ubicaciones.add(new UBICACION(980,355, "Karachi", 2,false,0));
        ubicaciones.add(new UBICACION(1005,395, "Bombay", 2,false,0));
        ubicaciones.add(new UBICACION(1025,330, "Nueva Delhi", 2,false,0));
        ubicaciones.add(new UBICACION(1070,370, "Calcuta", 2,false,0));
        ubicaciones.add(new UBICACION(1035,410, "Madras", 2,false,0));
        ubicaciones.add(new UBICACION(1150,525, "Yakarta", 1,false,0));
        ubicaciones.add(new UBICACION(1120,415, "Bangkok", 1,false,0));
        ubicaciones.add(new UBICACION(1165,370, "Hong Kong", 1,false,0));
        ubicaciones.add(new UBICACION(1195,355, "Shanghai", 1,false,0));
        ubicaciones.add(new UBICACION(1175,300, "Pekin", 1,false,0));
        ubicaciones.add(new UBICACION(1225,297, "Seul", 1,false,0));
        ubicaciones.add(new UBICACION(1280,290, "Tokio", 1,false,0));
        ubicaciones.add(new UBICACION(1255,320, "Osaka", 1,false,0)); 
        ubicaciones.add(new UBICACION(1205,375, "Taipei", 1,false,0));
        ubicaciones.add(new UBICACION(1148,429, "Ho Chi Minh", 1,false,0));
        ubicaciones.add(new UBICACION(1200,420, "Manila", 1,false,0));
        ubicaciones.add(new UBICACION(1320,645, "Sidney", 1,false,0));
        
        return ubicaciones;
    }
}