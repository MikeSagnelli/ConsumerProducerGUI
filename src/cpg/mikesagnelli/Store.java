/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpg.mikesagnelli;

import java.util.ArrayList;

/**
 *
 * @author sagnelli
 */
public class Store {
    
    private int size;
    private ArrayList<String> operations;
    
    public Store(int size) {
        this.size = size;
        this.operations = new ArrayList<> (size);
    }
    
    public synchronized String getOperation() throws InterruptedException {
        String operation = null;
        
        for (int i = 0; i < this.size; i++) {
            if (this.operations.get(i).compareTo("") != 0) {
                operation = this.operations.get(i);
                this.operations.set(i, "");
                break;
            }
        }
        
        if (operation != null) {
            return operation;
        } else {
            wait();  
        }
        
        return "";
    }
    
    public synchronized void setOperation(String operation) {
        int insertInIndex = this.operations.indexOf("");
        if (insertInIndex != -1) {
            this.operations.set(insertInIndex, operation);
        }
    }
}
