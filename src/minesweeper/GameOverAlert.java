package minesweeper;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author amuse11
 */
public class GameOverAlert {
    public static boolean answer;
    
    public static boolean display(String title, String message){
        Stage window = new Stage();
        
        /**
         * Will not allow any user interaction until alert box is closed
         * Block events to other windows
         */
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Start the game again");
        closeButton.setOnAction(e -> {
            window.close();
            answer = true;
        });
        Button endProgramButton = new Button("End the program");
        endProgramButton.setOnAction(e -> {
            answer = false;
            window.close();
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton, endProgramButton);
        layout.setAlignment(Pos.CENTER);
        
        
        
        /** 
         * Shows this stage and waits for it to be hidden (closed) before
         * returning to caller
         * Essentially, it's going to display this window and before you go back
         * to the main3 class, it needs to be closed
         * Display window and wait for it to close before returning
         */
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return answer;
    }
}
