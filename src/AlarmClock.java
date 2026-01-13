import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

//Will run on a separate thread
public class AlarmClock implements Runnable{

    private final LocalTime alarmtime;
    private final String filePath;
    private final Scanner scanner;

    AlarmClock(LocalTime alarmtime, String filePath, Scanner scanner){
        this.alarmtime = alarmtime;
        this.filePath = filePath;
        this.scanner = scanner;
    }

    @Override
    public void run(){


        //Every second we will get the current time and compare it to the alarm time
        while(LocalTime.now().isBefore(alarmtime)) {
            try {
                Thread.sleep(1000);
                LocalTime now = LocalTime.now();

                // \r makes the values update in place
                System.out.printf("\r%02d:%02d:%02d",
                        now.getHour(),
                        now.getMinute(),
                        now.getSecond()
                );
            }
            catch (InterruptedException e) {
                System.out.println("Thread was interrupted");            }

        }

        System.out.println("\nAlarm Noise");
        playSound(filePath);
    }

    //Method responsible for playing the file
    private void playSound(String filePath){

        File audioFile = new File(filePath);

        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)){
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.print("Press *Enter* to stop the alarm: ");
            scanner.nextLine();
            clip.stop();
            scanner.close(); //This will close all scanners which is why it is here
        }
        catch(UnsupportedAudioFileException e){
            System.out.println("Audio file format is not supported");
        }
        catch(LineUnavailableException e){
            System.out.println("Audio is unavaliable");
        }
        catch(IOException e){
            System.out.println("Error reading audio file");
        }
    }

}
