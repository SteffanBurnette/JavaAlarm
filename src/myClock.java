import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class myClock {
    public static void main(String [] args){


        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime = null;
        String filePath = "/home/frentr/IdeaProjects/JavaAlarm/src/Boogie Down - The Grey Room _ Golden Palms.wav";

        while(alarmTime == null){
            try {
                System.out.print("Enter alarm time (HH:MM:SS): ");
                String inputTime = scanner.nextLine();

                alarmTime = LocalTime.parse(inputTime, formatter);

                System.out.println("Alarm set for " + alarmTime);
            }catch(DateTimeParseException e){
                System.out.println("Invalid format, please use HH:MM:SS");

            }
        }

        AlarmClock alarmClock = new AlarmClock(alarmTime, filePath, scanner);
        Thread alarmThread = new Thread(alarmClock);
        //Will execute the run() method
        alarmThread.start();


    }

}
