package org.vkedco.cs.dtw;

/**
 *
 * @author Vladimir Kulyukin
 */
import java.util.ArrayList;


public class PNode {
    
    static int COUNTER = 0;
    private double mDTWCost;
    private ArrayList<PNode> mParents;
    private int mID = 0;
    private int mRow = 0;
    private int mCol = 0;
    
    
    public PNode(double dtw) {
        mDTWCost = dtw;
        mParents = new ArrayList<PNode>();
        mID = COUNTER++;
        mRow = 0;
        mCol = 0;
    }
    
    public PNode(double dtw, int row, int col) {
        mDTWCost = dtw;
        mRow = row;
        mCol = col;
        mParents = new ArrayList<PNode>();
        mID = COUNTER++;
        
    }
    
    public int getID() { return mID; }
    
    public double getDTWCost() {
        return mDTWCost;
    }
    
    public ArrayList<PNode> getParents() {
        return mParents;
    }
    
    public int getRow() { return mRow; }
    public int getCol() { return mCol; }
    
    public void setDTWCost(double dtw) {
        mDTWCost = dtw;
    }
    
    public void addParent(PNode p) {
        mParents.add(p);
    }
    
    static final String PNODE   = "PNode";
    static final String LP      = "(";
    static final String RP      = ")";
    static final String COMMA   = ",";
    static final String SPACE   = " ";
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        
        for(PNode p: mParents) {
            sb.append(Integer.toString(p.getID()) + SPACE);
        }
        
        return PNODE + LP + mID + COMMA + mRow + COMMA + mCol + COMMA + mDTWCost + COMMA + sb.toString() + RP; 
    }
}
