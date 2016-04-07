package org.vkedco.cs.dtw;

/**
 *
 * @author Vladimir Kulyukin
 */
import java.util.ArrayList;

public class PNodePath {
    private ArrayList<PNode> mPNodes = null;
    
    public PNodePath() {
        mPNodes = new ArrayList<PNode>();
    }
    
    public void addPathNode(PNode p) {
        mPNodes.add(0, p);
    }
    
    public ArrayList<PNode> getPNodes() {
        return mPNodes;
    }
    
    public PNode getHeadPNode() {
        if ( mPNodes.size() == 0 ) return null;
        return mPNodes.get(0);
    }
    
    public PNodePath(PNodePath pnp, PNode head) {
        mPNodes = new ArrayList<PNode>();
        for(PNode p: pnp.getPNodes()) {
            mPNodes.add(p);
        }
        mPNodes.add(0, head);
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(PNode p: mPNodes) {
            sb.append(p.toString());
        }
        return sb.toString();
    }
    
    public int length() {
        return mPNodes.size();
    }
}

