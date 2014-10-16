package org.vkedco.compvision.dog;

import java.text.DecimalFormat;

public class DOGWin {
	
	int[] mWinCoords 		= null;
	DOGWinStats mWinStats 	= null;
	
	public static enum EDGE_TYPE { HOR, VER, DIG, NONE };
	
	public DOGWin(int[] win_coords, DOGWinStats win_stats) {
		this.mWinCoords = win_coords;
		this.mWinStats  = win_stats;
	}
	
	public int getWinRow() {
    	return this.mWinCoords[0];
    }
    
    public int getWinCol() {
    	return this.mWinCoords[1];
    }
    
    public int getWinSize() {
    	return this.mWinCoords[2];
    }
    
    public void setWinCoords(int[] win_coords) {
    	this.mWinCoords = win_coords;
    }
    
    public void setWinStats(DOGWinStats win_stats) {
    	this.mWinStats = win_stats;
    }
    
    public double getDOGValue() {
    	return this.mWinStats.mDOGVal;
    }
    
    final static double DOG_VALUE_THRESH = 5.0;
    public boolean similarDOGValue(DOGWin dog_win) {
    	return Math.abs(this.getDOGValue() - dog_win.getDOGValue()) <= DOG_VALUE_THRESH;
    }
    
    
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("[" + this.getWinRow() + ", " + this.getWinCol() + ", " + this.getWinSize() + "]\n");
    	DOGWinStats win_stats = this.mWinStats;
    	final String edge_type = win_stats.mEdgeType.toString();
    	final double dog_val = this.getDOGValue();
    	DecimalFormat df = new DecimalFormat("#0.0");
    	final String dv = String.format(df.format(dog_val));
    	sb.append(edge_type + "\n");
    	sb.append(dv + "\n");
    	return sb.toString();
    }
    
    
    public boolean equals(Object other) {
    	
    	if ( other == null ) {
    		return false;
    	}
    	else if ( this == other ) {
    		return true;
    	}
    	else if ( this.getClass() != other.getClass() ) {
    		return false;
    	}
    	else {
    		DOGWin other_win = (DOGWin) other;
    		return this.getWinRow() == other_win.getWinRow() &&
    				this.getWinCol() == other_win.getWinCol() &&
    				this.getWinSize() == other_win.getWinSize();
    	}
    }
}
