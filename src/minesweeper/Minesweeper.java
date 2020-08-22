package minesweeper;

/**
 * @author amuse11
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Minesweeper extends Application {

    private static final int tileSize = 40;
    private static final int minefieldWidth = 800;
    private static final int minefieldLength = 600;
    private static double probabilityOfMines = 0.2;
    private static boolean sound = true;
    public boolean hasTimerEnded;

    private static final int numOfMinesHorizontal = minefieldWidth / tileSize;
    private static final int numOfMinesVertical = minefieldLength / tileSize;

    private Tile[][] grid = new Tile[numOfMinesHorizontal][numOfMinesVertical];
    private Scene scene;

    private Parent createLayout() {
        Pane root = new Pane();
        root.setPrefSize(minefieldWidth, minefieldLength);

        for (int y = 0; y < numOfMinesVertical; y++) {
            for (int x = 0; x < numOfMinesHorizontal; x++) {
                Tile tile = new Tile(x, y, Math.random() < probabilityOfMines);

                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }

        for (int y = 0; y < numOfMinesVertical; y++) {
            for (int x = 0; x < numOfMinesHorizontal; x++) {
                Tile tile = grid[x][y];

                if (tile.hasMine) {
                    continue;
                }
                /**
                 * Obtain a list of neighbours of the tile, Obtain a stream of
                 * elements (i.e. neighbours), then filter the neighbours if
                 * neighbour has a bomb and leave it in stream, if not, then
                 * discard it
                 */
                long mines = getNeighbors(tile).stream().filter(t -> t.hasMine).count();

                /**
                 * If there are zero mines around the tile, do not display
                 * otherwise, Set a numerical value to the text that the tile
                 * has
                 */
                if (mines > 0) {
                    tile.text.setText(String.valueOf(mines));
                }
            }
        }

        return root;
    }

    private List<Tile> getNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();

        int[] points = new int[]{
            -1, -1,
            -1, 0,
            -1, 1,
            0, -1,
            0, 1,
            1, -1,
            1, 0,
            1, 1
        };

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            int newX = tile.x + dx;
            int newY = tile.y + dy;

            if (newX >= 0 && newX < numOfMinesHorizontal
                    && newY >= 0 && newY < numOfMinesVertical) {
                neighbors.add(grid[newX][newY]);
            }
        }

        return neighbors;
    }

    private class Tile extends StackPane {

        private int x;
        private int y;
        private boolean hasMine;

        /**
         * Each tile is, by default, not revealed
         */
        private boolean isRevealed = false;

        private Rectangle border = new Rectangle(tileSize - 0.2, tileSize - 0.2, Color.GREEN);
        private Text text = new Text();

        public Tile(int x, int y, boolean hasMine) {
            this.x = x;
            this.y = y;
            this.hasMine = hasMine;

            border.setStroke(Color.LIGHTGRAY);

            text.setFont(Font.font(18));
            /**
             * If there is a mine, set the text to X, otherwise text is ""
             */
            text.setText(hasMine ? "X" : "");
            text.setVisible(false);

            getChildren().addAll(border, text);

            setTranslateX(x * tileSize);
            setTranslateY(y * tileSize);

            setOnMouseClicked(e -> reveal());
        }

        public void reveal() {
            if (isRevealed) {
                return;
            }

            /**
             * Game over, play explosion sound when hasMine set to true Allow
             * the user the options of starting the game again or end the
             * program
             */
            if (hasMine) {
                if (sound) {
                    String path = "src/minesweeper/explosion.wav";
                    Media mediaExplosion = new Media(new File(path).toURI().toString());
                    MediaPlayer mediaPlayerExplosion = new MediaPlayer(mediaExplosion);
                    mediaPlayerExplosion.setAutoPlay(true);
                }
                boolean result = GameOverAlert.display("Game Over", "Choose from the options below");
                /**
                 * If user wishes to play the game again, play main music again
                 */
                if (result == true) {

                    String path = "src/minesweeper/bensound-clearday.mp3";

                    /**
                     * Instantiate media class
                     */
                    Media media = new Media(new File(path).toURI().toString());

                    /**
                     * Instantiate mediaPlayer class
                     */
                    MediaPlayer mediaPlayer = new MediaPlayer(media);

                    /**
                     * Play audio
                     */
                    mediaPlayer.setAutoPlay(true);
                }
                if (result == false) {
                    System.exit(0);
                }
                scene.setRoot(createLayout());
                return;
            }

            isRevealed = true;
            text.setVisible(true);
            border.setFill(Color.WHITE);

            /**
             * Obtain of this tile that is empty and for each neighbours, reveal
             * the tile
             */
            if (text.getText().isEmpty()) {
                getNeighbors(this).forEach(Tile::reveal);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String[] args = {};
        String path = "src/minesweeper/bensound-clearday.mp3";

        /**
         * Instantiate media class
         */
        Media media = new Media(new File(path).toURI().toString());

        /**
         * Instantiate mediaPlayer class
         */
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        /**
         * Play audio
         */
        mediaPlayer.setAutoPlay(true);

        boolean timer = Timer.display();

        scene = new Scene(createLayout());

        stage.setScene(scene);
        stage.show();
    }

}
