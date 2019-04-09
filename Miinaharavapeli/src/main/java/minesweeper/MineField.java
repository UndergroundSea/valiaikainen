package minesweeper;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MineField {
    
    private Pane[][] grid;
    private boolean alive;
    private int turnedPanes;
    private ArrayList<Integer> mines;
    private boolean turning;
    
    public MineField(int x, int y) {
        
        this.grid = new Pane[x][y];
        this.alive = true;
        this.turnedPanes = 0;
        this.mines = new ArrayList<>();
        this.turning = true;
        
    }
    
    public void setPane(Pane pane, int x, int y) {
        grid[x][y] = pane; 
    }
    
    public Pane getPane(int x, int y) {
        return grid[x][y];
    }
    
    public void endGame() {
        this.alive = false;
    }
    
    public boolean getAlive() {
        return this.alive;
    }
    
    public ArrayList<Integer> getMines() {
        return this.mines;
    } 
    
    public void addTurnedPane() {
        this.turnedPanes++;
    }
    
    public int getTurnedPanes() {
        return this.turnedPanes;
    }
    
    public void setTurnedPanes(int turnedPanes) {
        this.turnedPanes = turnedPanes;
    }
    
    public void setTurning(boolean turning) {
        this.turning = turning;
    }
    
    public boolean getTurning() {
        return this.turning;
    }
    
    public int countValue(Pane pane) {
        int x = pane.getX();
        int y = pane.getY();
        int mines = 0;
        
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                
                if (i < 0 || j < 0 || i > 9 || j > 4) {
                    continue;
                }                
                if (grid[i][j].getMine()) {
                    mines++;
                }               
            }
        }
        
        pane.setColor(mines);
        
        return mines;
//        grid[x][y].setValue(mines);
        
    }
    
    public void placeMines() {
        int mines = 0;
        Random random = new Random();

        while (mines < 7) {
            int placedMine = random.nextInt(50);
            if (!this.mines.contains(placedMine)) {
                this.mines.add(placedMine);
                mines++;
            }
        }
    }
    
    public void placeButtons(GridPane grid, Label gameState) {
        int timeForAMine = 0;

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 5; y++) {

                Button button = new Button();
                button.setPrefWidth(50);
                button.setPrefHeight(50);

                grid.add(button, x, y);

                Pane pane = new Pane(x, y, button);
                this.setPane(pane, x, y);
                if (this.getMines().contains(timeForAMine)) {
                    pane.setMine(true);
                }

                pane.setOnAction(this, gameState);

                timeForAMine++;

            }
        }
    }
    
}









//        if (mines == 0) {
//            pane.setColor(Color.WHITE);
//        } else if (mines == 1) {
//            pane.setColor(Color.GREEN);
//        } else if (mines == 2) {
//            pane.setColor(Color.BLUE);
//        } else if (mines == 3) {
//            pane.setColor(Color.RED);
//        } else {
//            pane.setColor(Color.DARKRED);
//        }