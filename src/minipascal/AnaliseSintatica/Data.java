/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipascal.AnaliseSintatica;

/**
 *
 * @author Benevides
 */
public class Data {
    
    public static int tokenCount = -1;

    /**
     * Get the value of tokenCount
     *
     * @return the value of tokenCount
     */
    public static int getTokenCount() {
        return tokenCount;
    }

    /**
     * Set the value of tokenCount
     *
     * @param tokenCount new value of tokenCount
     */
    public static void setTokenCount() {
        Data.tokenCount = tokenCount + 1;
    }

    
}
