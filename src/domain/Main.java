/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import domain.*;
import static java.time.Clock.system;

/**
 *
 * @author Thomas-Gaming
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("3-in-a-row");
        System.out.println("***********");
        System.out.printf("This game is only created to try out the MiniMax algorithm. Basic functionalities like input verification are not suported. %n"
                + "Class MiniMax contains the boolean value showBuildTree. Once toggled to true, it will show the entire build tree while the program is calculating all possibilities. It looks cool but not pracitcal since this will take forever to build the tree. %n"
                + "Kind regards, Thomas Detemmerman");
        System.out.println("");
        System.out.println("Rules:");
        System.out.println("The computer exepects coorindates from the grid. First rownumber, then colomnumber. Both values sepratated with a character in between. This can be any character. For example: 0.1");
        GlobalMaxID gmid = new GlobalMaxID();
        Gameboard g = new Gameboard(createDefaultContent(), 0, gmid);
        MiniMax miniMax = new MiniMax();
        boolean end = false;
        do{
            System.out.println("-------------------------------------------------");
            try {
                int i = miniMax.minimaxDescision(g);
                Gameboard gameboardChosenByAI = g.getValueByID(i);
                System.out.println(gameboardChosenByAI.toString());
                endOfGameCheck(gameboardChosenByAI);
                Scanner r = new Scanner(System.in);
                System.out.print("Enter coordinates x,y: ");
                String input = r.next();
                
                /*user*/
                Gameboard gameboardChosenByUser = convertcoordinatesToGameboard(gameboardChosenByAI, input);
                System.out.println("new gameboard after user input");
                System.out.println(gameboardChosenByUser.toString());
                int keyTogameboard = findKeybyGameboardContent(gameboardChosenByAI, gameboardChosenByUser);
                System.out.println("id of new Gameboard is" + keyTogameboard);
               g = gameboardChosenByAI.getValueByID(keyTogameboard);
               System.out.println(g.toString());
                
               endOfGameCheck(g);
            }catch (EndOfGameException eog){
                System.out.println("End of game: "+ eog.getMessage()); 
                end = true;
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                end = true;
            }
            
        } while(!end);
    }

    private static Gameboard convertcoordinatesToGameboard(Gameboard parent, String coordinates) {
        Gameboard newGameboard = new Gameboard(duplicateGameboard(parent.getContent()), parent.getCurrentID(), parent.getGlobalmaxidObject());
        int x = Character.getNumericValue(coordinates.charAt(0));
        int y = Character.getNumericValue(coordinates.charAt(2));

        char[][] t = newGameboard.getContent();
        t[x][y] = 'o';
        newGameboard.setContent(t);
        return newGameboard;
    }

    private static int findKeybyGameboardContent(Gameboard parent, Gameboard newCreated) throws Exception {
        for (Map.Entry<Integer, Gameboard> entry : parent.getSuccessors().entrySet()) {
            if (Arrays.deepEquals(newCreated.getContent(), entry.getValue().getContent())) {
                return entry.getKey();
            }

        }
        throw new Exception("new created gameboardcontent not found in successorlist");

    }

    private static char[][] createDefaultContent() {
        char[][] defaultBoard = new char[3][3];
        for (char[] content1 : defaultBoard) {
            for (int j = 0; j < content1.length; j++) {
                content1[j] = '.';
            }
        }
        return defaultBoard;

    }
/*
    This game will verify who won. If someone has won it will throw an EndOfGameException
    */
private static void endOfGameCheck(Gameboard g) throws EndOfGameException{
   if(g.terminaltest()){
                    
                        if(g.utility() == 0){
                            throw new EndOfGameException("Draw");
                        }
                        if(g.utility() < 0){
                            throw new EndOfGameException("You won!");
                        }
                        if(g.utility() > 0){
                            throw new EndOfGameException("You lost");
                        }
                            
                    
                } 
}
    private static char[][] duplicateGameboard(char[][] g) {
        char[][] c = new char[g.length][g[0].length];

        for (int i = 0; i < g.length; i++) {
            System.arraycopy(g[i], 0, c[i], 0, g[i].length);
        }
        return c;
    }
}
