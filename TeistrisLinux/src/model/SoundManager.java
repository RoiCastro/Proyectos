package model;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundManager {

    private Clip clip;
    private AudioInputStream audioStream;
    private long pausePosition = 0; // Para pausar correctamente

    public void playBackgroundMusic(String filepath) {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                System.err.println("Error: Archivo no encontrado -> " + filepath);
                return;
            }

            audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Activar bucle infinito
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close(); // Cierra el clip para liberar recursos
        }
    }

    public void pauseMusic() {
        if (clip != null && clip.isRunning()) {
            pausePosition = clip.getMicrosecondPosition(); // Guarda posición antes de detener
            clip.stop();
        }
    }

    public void resumeMusic() {
        if (clip != null && !clip.isRunning()) {
            clip.setMicrosecondPosition(pausePosition); // Retoma desde la pausa
            clip.start();
        }
    }
}
