/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Thomas-Gaming
 */
public class MiniMax {
   
    private final boolean showBuildTree = false;
    public MiniMax() {
    }

    public int minimaxDescision(Gameboard g) throws Exception {
        System.out.println("Calculating possibilities....");
        int chosenAction = -1;
        int maxVal = Integer.MIN_VALUE;
        g.setSuccessors('x');
        for (Map.Entry<Integer, Gameboard> entry : g.getSuccessors().entrySet()) {

            int v = minValue(entry.getValue());
           //System.out.println("mmd v:" +v);
            if (v > maxVal) {
                //System.out.println("v > maxval");
                chosenAction = entry.getKey();
                maxVal = v;
            }
        }
                
        return chosenAction;
    }

    private int minValue(Gameboard g) throws Exception {
        if (g.terminaltest()) {
           // System.out.println("terminal test: TRUE");
            //System.out.println("eindwaarde: " + g.utility());
            return g.utility();
        //} else {System.out.println("terminal test: False");
        }
        int v = Integer.MAX_VALUE;
        g.setSuccessors('o');
        for (Map.Entry<Integer, Gameboard> entry : g.getSuccessors().entrySet()) {
            showBuildTree(g.toString(), showBuildTree, "Min");
            v = min(v, maxValue(entry.getValue()));
        }
        return v;
    }

    private int maxValue(Gameboard g) throws Exception {
        if (g.terminaltest()) {
            return g.utility();
        }
        int v = Integer.MIN_VALUE;
        g.setSuccessors('x');
        for (Map.Entry<Integer, Gameboard> entry : g.getSuccessors().entrySet()) {
            showBuildTree(g.toString(), showBuildTree, "Max");
            v = max(v, minValue(entry.getValue()));
        }
        return v;
    }
    
    
    private void showBuildTree(String content, boolean show, String functionname){
        if (show){
            System.out.println("---------"+ functionname);
            System.out.println(content);
            System.out.println("---------- einde " + functionname);
        }
    }
}
