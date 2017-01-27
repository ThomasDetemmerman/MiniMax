/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Thomas-Gaming
 */
public class GlobalMaxID {
    private int globalMaxID = 0;

    public GlobalMaxID() {
    }

    public int getCurrentGlobalMaxID() {
        return globalMaxID;
    }

    
    
    public int getNewGlobalID(){
        
        this.globalMaxID++;
        return globalMaxID;
    }
}
