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
    private HashMap <Integer, Gameboard> successors = new HashMap <Integer, Gameboard>();
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
    
    public Gameboard getValueByID(int ID){
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

    public HashMap <Integer, Gameboard> getSuccessors() {
        return successors;
    }
    
    public void setSuccessors(char player) {


        for (int i = 0; i < getContent().length; i++) {
            for (int j = 0; j < getContent()[i].length; j++) {
                char current = getContent()[i][j];
                if (current == '.') {

                    char[][] childContent = duplicateGameboard(content);
                   // printContent(childContent);
                    childContent[i][j] = player;
                    successors.put(globalmaxid.getCurrentGlobalMaxID()+1,  new Gameboard(childContent, this.ID, globalmaxid));
                    
                    //System.out.println(successors.size());
                }
                
            }
        }//successors.keySet().forEach(key -> System.out.println(key + "->" + successors.get(key)));
        
    }

    

    public boolean terminaltest() {
        try {
            whoWins();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public int utility() throws Exception {
            if (whoWins() == 'x') {
                return 1;
            }
            if (whoWins() == 'o') {
                return 0;

            }
        throw new Exception("invalid user or invalid call of utility funciton");
    }

    @Override
    public String toString() {
            String output = String.format("Gameboard ID %d%nParent ID %d", this.getCurrentID(), this.getParentID());
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

    public char whoWins() throws Exception {
        /* checking diagonalen*/
        String dia1 = "";
        String dia2 = "";
        for (int i = 0; i < content.length; i++) {
            dia1 += content[i][i];
            dia2 += content[i][content.length - 1];

        }
        if ("ooo".equals(dia1) || "ooo".equals(dia2)) {

            return 'o';
        }
        if ("xxx".equals(dia1) || "xxx".equals(dia2)) {

            return 'x';
        }
        /* checking colom and row */
        String colom = "";
        String row = "";
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[i].length; j++) {
                colom += content[i][j];
                row += content[j][i];
            }
            row = "";
            colom = "";
            if ("ooo".equals(colom) || "ooo".equals(row)) {

                return 'o';
            }
            if ("xxx".equals(colom) || "xxx".equals(row)) {

                return 'x';
            }
        }
        throw new Exception("Nobody Wins");
    }
    
    private char[][] duplicateGameboard(char[][] g){
        char[][] c = new char[g.length][g[0].length];
        
        for (int i = 0; i < g.length; i++) {
            System.arraycopy(g[i], 0, c[i], 0, g[i].length);
        }
        return c;
    }
    
    private static void printContent(char[][] c) {
        String output = "";
        for (char[] rij : c) {
            for (char vakje : rij) {
                output += String.format(" %s ", vakje);
            }
            output += "\n";
        }
        System.out.println(output);
    }


}
