/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import minesweeper.MineField;
import minesweeper.Pane;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ebingerv
 */
public class NewEmptyJUnitTest {
    
    MineField minefield;
//    Pane pane;
    
    public NewEmptyJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }  
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        minefield = new MineField(10, 5);
//        pane = new Pane(0, 0, new Button());
//        minefield.setPane(pane, 0, 0);
        
    }
    
    @After
    public void tearDown() {
    }

//     TODO add test methods here.
//     The methods must be annotated with annotation @Test. For example:
    
//     @Test
//     public void hello() {}
     
//     @Test
//     public void setAndGetValue() {
////         Pane pane = new Pane(10, 1, new Button("miina"));
//         pane.setMine(true);
//         assertEquals(true, pane.getMine());
//     }
    
    @Test
    public void endTheGame() {
//        MineField minefield = new MineField(10, 5);
        minefield.endGame();
        assertEquals(false, minefield.getAlive());
    }
//    
    @Test
    public void placeMines() {
        minefield.placeMines();
        assertEquals(7, minefield.getMines().size());
        
    }
    
//    @Test
//    public void winningTheGame() {
//        Label gameState = new Label("Varo miinoja!");
//        for (int i = 0; i < 42; i++) {
//            minefield.addTurnedPane();
//        }
//        pane.turnPane(minefield, gameState);
//        assertEquals("Hi hi hiii, kutittaa! Voitit pelin!", gameState.getText());
//    }
    
//    kortti.lataaRahaa(100);
//        assertEquals("saldo: 1.10", kortti.toString());
    
}
