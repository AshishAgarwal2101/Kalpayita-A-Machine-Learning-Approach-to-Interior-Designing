/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalpayita1;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author Ashish
 */
public class SpeechToText1 {
    static final long RECORD_TIME = 7000;  // 1 minute
    File wavFile = new File("Record1.wav");
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    // the line from which audio data is captured
    TargetDataLine line;
 
    /**
     * Defines an audio format
     */
    AudioFormat getAudioFormat() {
        float sampleRate = 64000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        return format;
    }
 
    /**
     * Captures the sound and record into a WAV file
     */
    void start() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
 
            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing
 
            System.out.println("Start capturing...");
 
            AudioInputStream ais = new AudioInputStream(line);
 
            System.out.println("Start recording...");
 
            // start recording
            AudioSystem.write(ais, fileType, wavFile);
 
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    String finish() throws IOException, Exception {
        line.stop();
        line.close();
        System.out.println("Finished");
        System.out.println("CONVERSION STARTED");
        
        ArrayList<String> words = new ArrayList<>();
        words.add("table");
        words.add("chair");
        //IBM Speech to text part
        SpeechToText service = new SpeechToText();
        service.setUsernameAndPassword("2286d9e2-ebcb-4de3-8438-c6eb7bd44aa7", "XoBcONJExHfw");

        String resText = "";
        File audio = new File("Record1.wav");
        RecognizeOptions options = new RecognizeOptions.Builder()
        .audio(audio)
        .contentType(RecognizeOptions.ContentType.AUDIO_WAV)
        .build();
        SpeechRecognitionResults transcript = service.recognize(options).execute();
        List<SpeechRecognitionResult> r = transcript.getResults();
        for(SpeechRecognitionResult rr:r){
            resText += rr.getAlternatives().get(0).getTranscript()+". ";
        }
        //System.out.println("res="+r+"\n\n"+transcript);
        System.out.println("res: "+resText);
        return resText;
    }
    public static void main(String... args) throws Exception{
        final SpeechToText1 recorder = new SpeechToText1();
 
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(RECORD_TIME);
                    System.out.println("waiting");

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                try {
                    recorder.finish();
                } catch (Exception ex) {
                    Logger.getLogger(Kalpayita1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
 
        stopper.start();
 
        // start recording
        recorder.start();
    }
}
