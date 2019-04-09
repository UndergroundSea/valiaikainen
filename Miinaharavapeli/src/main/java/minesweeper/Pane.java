package minesweeper;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Pane {

    private int x;
    private int y;
    private boolean mine;
    private int value;
    private Button button;
    private boolean turned;
    private Color color;
    private boolean zeroTurned;

    public Pane(int x, int y, Button button) {

        this.x = x;
        this.y = y;
        this.mine = false;
        this.value = 99;
        this.button = button;
        this.turned = false;
        this.zeroTurned = false;
        this.color = Color.BLACK;

    }

    public void setValue(int v) {
        this.value = v;
    }

    public int getValue() {
        return this.value;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getMine() {
        return this.mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColor(int mines) {
        if (mines == 0) {
            this.setColor(Color.WHITE);
        } else if (mines == 1) {
            this.setColor(Color.GREEN);
        } else if (mines == 2) {
            this.setColor(Color.BLUE);
        } else if (mines == 3) {
            this.setColor(Color.RED);
        } else {
            this.setColor(Color.DARKRED);
        }
    }

    public Color getColor() {
        return this.color;
    }

    public Button getButton() {
        return this.button;
    }

    public void turn() {
        this.turned = true;
    }

    public boolean getTurned() {
        return this.turned;
    }

    public void zeroTurn() {
        this.zeroTurned = true;
    }

    public boolean getZeroTurned() {
        return this.zeroTurned;
    }

    public void setOnAction(MineField minefield, Label gameState) {
        button.setOnAction((event) -> {
            if (minefield.getTurning()) {
                if (minefield.getAlive()) {

                    this.turnPane(minefield, gameState);

                }

            } else {
                if (minefield.getAlive()) {
                    button.setText("X");
                }
            }

        });
    }

    public void turnPane(MineField minefield, Label gameState) {
        if (this.getMine()) {
            minefield.endGame();
            this.button.setText("¤");
            gameState.setText("Heh heh hee, hävisit pelin!");
        } else if (minefield.countValue(this) == 0) {

//            this.turnSafePane(minefield, gameState);
//            for (int i = this.x - 1; i < this.x + 2; i++) {
//                for (int j = this.y - 1; j < this.y + 2; j++) {
//                    if (i < 0 || j < 0 || i > 9 || j > 4) {
//                        continue;
//                    }
//                    if (i == this.x && j == this.y) {
//                        continue;
//                    }
//                    this.turnSafePane(minefield, gameState, minefield.getPane(i, j), minefield.getPane(i, j).getButton());
//
//                }
//            }
            this.turnZeroPane(minefield, gameState, this, this.button);

//            this.turnSafePane(minefield, gameState);
        } else {
            this.turnSafePane(minefield, gameState);
        }
    }

//    public void turnPane(MineField minefield, Label gameState, Pane pane, Button button) {
//        if (pane.getMine()) {
//            minefield.endGame();
//            button.setText("¤");
//            gameState.setText("Heh heh hee, hävisit pelin!");
//        } else if (minefield.countValue(pane) == 0) {
//
//            for (int i = this.x - 1; i < this.x + 2; i++) {
//                for (int j = this.y - 1; j < this.y + 2; j++) {
//                    if (i < 0 || j < 0 || i > 9 || j > 4) {
//                        continue;
//                    }
//                    pane.turnSafePane(minefield, gameState, minefield.getPane(i, j), minefield.getPane(i, j).getButton());
//                    pane.turnPane(minefield, gameState);
//                }
//            }
//
////            this.turnSafePane(minefield, gameState);
//
//        } else {
//            this.turnSafePane(minefield, gameState);
//        }
//    }
//    public void turnZeroPane(MineField minefield, Label gameState) {
//        if (minefield.countValue(this) == 0) {
//            this.turnSafePane(minefield, gameState);
//        }
//    }
    public void turnZeroPane(MineField minefield, Label gameState, Pane pane, Button button) {
        if (minefield.countValue(pane) == 0) {
            pane.zeroTurn();
            pane.turnSafePane(minefield, gameState, pane, button);

            pane.turnAll(minefield, gameState, pane, button);
            for (int i = pane.getX() - 1; i < pane.getX() + 2; i++) {
                for (int j = pane.getY() - 1; j < pane.getY() + 2; j++) {
                    if (i < 0 || j < 0 || i > 9 || j > 4) {
                        continue;
                    }
                    if (minefield.countValue(minefield.getPane(i, j)) == 0 && !minefield.getPane(i, j).getZeroTurned()) {
                        minefield.getPane(i, j).turnZeroPane(minefield, gameState, minefield.getPane(i, j), minefield.getPane(i, j).getButton());
//                        minefield.getPane(i, j).turnAll(minefield, gameState, minefield.getPane(i, j), minefield.getPane(i, j).getButton());
//                        for (int k = i - 1; k < i + 2; k++) {
//                            for (int l = j - 1; l < j + 2; l++) {
//                                if (k < 0 || l < 0 || k > 9 || l > 4) {
//                                    continue;
//                                }
//                                if (minefield.countValue(minefield.getPane(k, l)) == 0) {
//                                    minefield.getPane(k, l).turnAll(minefield, gameState, minefield.getPane(k, l), minefield.getPane(k, l).getButton());
//                                }
//
//                            }
//                        }
                    }
                }
            }
        }
    }    

    public void turnAll(MineField minefield, Label gameState, Pane pane, Button button) {
        for (int i = pane.getX() - 1; i < pane.getX() + 2; i++) {
            for (int j = pane.getY() - 1; j < pane.getY() + 2; j++) {
                if (i < 0 || j < 0 || i > 9 || j > 4) {
                    continue;
                }
                pane.turnSafePane(minefield, gameState, minefield.getPane(i, j), minefield.getPane(i, j).getButton());
//                    if (!minefield.getPane(i, j).getZeroTurned()) {
//                        pane.turnZeroPane(minefield, gameState, pane, button);
//                    }
            }
        }
    }

    public void turnSafePane(MineField minefield, Label gameState, Pane pane, Button button) {
        button.setText(Integer.toString(minefield.countValue(pane)));
        button.setTextFill(pane.getColor());
        if (!pane.getTurned()) {
            minefield.addTurnedPane();
            pane.turn();
        }
        if (minefield.getTurnedPanes() == 43) {
            gameState.setText("Hi hi hiii, kutittaa! Voitit pelin!");
//        } else if (minefield.countValue(pane) == 0) {
//            for (int i = pane.getX() - 1; i < pane.getX() + 2; i++) {
//                for (int j = pane.getY() - 1; j < pane.getY() + 2; j++) {
//                    if (i < 0 || j < 0 || i > 9 || j > 4) {
//                        continue;
//                    }
//                    pane.turnSafePane(minefield, gameState, minefield.getPane(i, j), minefield.getPane(i, j).getButton());
//                }
//            }
        }
    }

    public void turnSafePane(MineField minefield, Label gameState) {
        this.button.setText(Integer.toString(minefield.countValue(this)));
        this.button.setTextFill(this.color);
        this.turn();
        minefield.addTurnedPane();
        if (minefield.getTurnedPanes() == 43) {
            gameState.setText("Hi hi hiii, kutittaa! Voitit pelin!");
        }
    }

}

//                    if (this.getMine()) {
//                        minefield.endGame();
//                        button.setText("¤");
//                        gameState.setText("Heh heh hee, hävisit pelin!");
//                    } else {
//                        button.setText(Integer.toString(minefield.countValue(this)));
//                        button.setTextFill(this.color);
//                        this.turn();
//                        minefield.addTurnedPane();
//                        if (minefield.getTurnedPanes() == 43) {
//                            gameState.setText("Hi hi hiii, kutittaa! Voitit pelin!");
//                        }
//                    }
//            this.button.setText(Integer.toString(minefield.countValue(this)));
//            this.button.setTextFill(this.color);
//            this.turn();
//            minefield.addTurnedPane();
//            if (minefield.getTurnedPanes() == 43) {
//                gameState.setText("Hi hi hiii, kutittaa! Voitit pelin!");
//            }

