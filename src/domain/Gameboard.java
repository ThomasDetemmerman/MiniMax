/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.sun.javafx.scene.web.Debugger;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas-Gaming
 */
final class Gameboard {

    private char[][] content;
    private HashMap<Integer, Gameboard> successors = new HashMap<Integer, Gameboard>();
    private final int ID;
    private final int parentID;
    private GlobalMaxID globalmaxid;
    private int counter;

    public Gameboard(char[][] content, int parentID, GlobalMaxID gmid) {
        this.globalmaxid = gmid;
        this.ID = gmid.getNewGlobalID();
        this.parentID = parentID;
        setContent(content);
    }

    public Gameboard getValueByID(int ID) {
        return successors.get(ID);
    }

    public GlobalMaxID getGlobalmaxidObject() {
        return globalmaxid;
    }

    public int getParentID() {
        return ID;
    }

    public int getCurrentID() {
        return ID;
    }

    public void setContent(char[][] content) {
        this.content = content;
    }

    public char[][] getContent() {
        return content;
    }

    public HashMap<Integer, Gameboard> getSuccessors() {
        return successors;
    }

    public void setSuccessors(char player) {

        for (int i = 0; i < getContent().length; i++) {
            for (int j = 0; j < getContent()[i].length; j++) {
                char current = getContent()[i][j];
                if (current == '.') {

                    char[][] childContent = duplicateGameboard(content);
                    childContent[i][j] = player;
                    successors.put(globalmaxid.getCurrentGlobalMaxID() + 1, new Gameboard(childContent, this.ID, globalmaxid));

                }

            }
        }
        
    }

   
    public boolean terminaltest() {
        /* check gameboard full*/
        if(gameboardFull()){
            return true;
        }
        /* checking diagonalen*/
        String dia1 = "";
        String dia2 = "";

        for (int i = 0; i < content.length; i++) {
            dia1 += content[i][i];
            dia2 += content[i][content.length - 1 - i];

        }
        if ("ooo".equals(dia1) || "ooo".equals(dia2) || "xxx".equals(dia1) || "xxx".equals(dia2)) {

            return true;
        }
        /* checking colom and row */
        String colom = "";
        String row = "";

        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[i].length; j++) {
                colom += content[i][j];
                row += content[j][i];
            }

            if ("ooo".equals(colom) || "ooo".equals(row) || "xxx".equals(colom) || "xxx".equals(row)) {

                return true;
            }
            row = "";
            colom = "";
        }
        return false;
    }

    private char[][] duplicateGameboard(char[][] g) {
        char[][] c = new char[g.length][g[0].length];

        for (int i = 0; i < g.length; i++) {
            System.arraycopy(g[i], 0, c[i], 0, g[i].length);
        }
        return c;
    }

    

    public int utility() {
        int points = 0;
        String dia1 = "";
        String dia2 = "";
        for (int i = 0; i < content.length; i++) {
            dia1 += content[i][i];
            dia2 += content[i][content.length - 1 - i];

        }
        if ("ooo".equals(dia1) || "ooo".equals(dia2)) {

            points -= 1;
        }
        if ("xxx".equals(dia1) || "xxx".equals(dia2)) {

            points += 1;
        }
        /* checking colom and row */
        String colom = "";
        String row = "";
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[i].length; j++) {
                colom += content[i][j];

                row += content[j][i];

            }

            if ("ooo".equals(colom) || "ooo".equals(row)) {

                points -= 1;
            }
            if ("xxx".equals(colom) || "xxx".equals(row)) {

                points += 1;
            }
            row = "";
            colom = "";
        }
        return points;
    }

    private boolean gameboardFull() {
        for (char[] row : content) {
            for (char vak : row) {
                if (vak == '.') {
                    return false;
                }

            }
        }
        return true;
    }
    
     @Override
    public String toString() {
        String output = String.format("Gameboard ID: %d%nterminaltest: %s%nutility: %d", this.getCurrentID(), this.terminaltest(), utility());
        output += "\n";
        output += "   0  1  2\n";
        int i = 0;
        for (char[] rij : getContent()) {
            output += i + " ";
            i++;
            for (char vakje : rij) {
                output += String.format(" %s ", vakje);
            }
            output += "\n";
        }

        return output;

    }
}
