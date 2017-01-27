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

/**
 *
 * @author Thomas-Gaming
 */
public class startUp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GlobalMaxID gmid = new GlobalMaxID();
        Gameboard g = new Gameboard(createDefaultContent(), 0, gmid);
        MiniMax miniMax = new MiniMax();
        
        do {
            System.out.println("-------------------------------------------------");
            try {
                int i = miniMax.minimaxDescision(g);
                Gameboard gameboardChosenByAI = g.getValueByID(i);
                System.out.println(gameboardChosenByAI.toString());
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
//                System.out.println(g.toString());

            } catch (Exception ex) {
                Logger.getLogger(startUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(!g.terminaltest());
        try {
            System.out.println(g.whoWins());
        } catch (Exception ex) {
            Logger.getLogger(startUp.class.getName()).log(Level.SEVERE, null, ex);
        }
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

//    private static void printContent(char[][] c) {
//        String output = "";
//        for (char[] rij : c) {
//            for (char vakje : rij) {
//                output += String.format(" %s ", vakje);
//            }
//            output += "\n";
//        }
//        System.out.println(output);
//    }
    private static char[][] duplicateGameboard(char[][] g) {
        char[][] c = new char[g.length][g[0].length];

        for (int i = 0; i < g.length; i++) {
            System.arraycopy(g[i], 0, c[i], 0, g[i].length);
        }
        return c;
    }
}
