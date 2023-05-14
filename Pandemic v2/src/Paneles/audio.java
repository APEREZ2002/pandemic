package Paneles;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class audio {

    private Clip clipActual;

    public void musica(String Select) {	
        try {
            // Detener el clip actual si está sonando
            if (clipActual != null && clipActual.isRunning()) {
                clipActual.stop();
            }
            
            // Cargar el archivo de audio
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Select));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Reproducir la música en bucle
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Actualizar la variable de instancia del clip actual
            clipActual = clip;
        } catch (Exception ex) {
            System.out.println("Error al reproducir la música: " + ex.getMessage());
        }
    }
    //Esta funcion detiene la musica
    public void detenerMusica() {
        if (clipActual != null && clipActual.isRunning()) {
            clipActual.stop();
            clipActual = null;
        }
    }
  //Esta funcion ajusta el volumen de la musica
    public void ajustarVolumen(int nivel) {
        if (clipActual != null) {
            FloatControl gainControl = (FloatControl) clipActual.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(nivel / 100.0) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }

  //Esta funcion reproduce un sonido (igual que la musica pero sin bucle)
    public void sonido(String Select) {
        try {
            // Cargar el archivo de audio
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Select));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Reproducir la música una vez
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error al reproducir la música: " + ex.getMessage());
        }
    }
}
