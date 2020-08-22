package minesweeper;

import java.util.Scanner;
import java.util.TimerTask;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Timer {

    public static int timerInterval;
    public static boolean timerWindow;
    public static String seconds;
    public static java.util.Timer timer;

    public static final int timerInterval() {
        if (timerInterval == 1) {
            timer.cancel();
        }
        return timerInterval = timerInterval - 1;
    }

    public static boolean display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Timer");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("Set the number of seconds you want the timer to count down from and close the timer window");

        Button startTimerButton = new Button("Start timer");

        startTimerButton.setOnAction(e -> {
            Scanner input = new Scanner(System.in);
            System.out.print("Input the number of seconds the timer will count down from : ");
            seconds = input.nextLine();
            int timerPeriod = 1000;
            int timerDelay = 1000;
            timerInterval = Integer.parseInt(seconds);
            timer = new java.util.Timer();
            System.out.println(seconds);

            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    System.out.println(timerInterval());

                }
            }, timerDelay, timerPeriod);

        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, startTimerButton);
        layout.setAlignment(Pos.CENTER);

        window.setAlwaysOnTop(true);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

        return timerWindow;
    }

}
