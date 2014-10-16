package org.vkedco.opencv.haar;

import java.text.DecimalFormat;
import java.util.Comparator;

public class HaarWaveletWin implements Comparable<HaarWaveletWin> {
	
	private int[] mWaveletWinCoords = null;
	private HaarWaveletWinStats mWaveletWinStats = null;
	
	public HaarWaveletWin(int[] wavelet_win_coords, HaarWaveletWinStats wavelet_win_stats) {
		this.mWaveletWinCoords = wavelet_win_coords;
		this.mWaveletWinStats  = wavelet_win_stats;
	}
	
	public int getWinRow() {
    	return this.mWaveletWinCoords[0];
    }
    
    public int getWinCol() {
    	return this.mWaveletWinCoords[1];
    }
    
    public int getWinSize() {
    	return this.mWaveletWinCoords[2];
    }
    
    public void setWinCoords(int[] edge_win_coords) {
    	this.mWaveletWinCoords = edge_win_coords;
    }
    
    public void setWinStats(HaarWaveletWinStats edge_win_stats) {
    	this.mWaveletWinStats = edge_win_stats;
    }
    
    public HaarWaveletWinStats getEdgeWinStats() {
    	return mWaveletWinStats;
    }
    
    public TwoDMatHaar.EDGE_TYPE getEdgeType() {
    	if ( mWaveletWinStats != null ) {
    		return mWaveletWinStats.mEdgeType;
    	}
    	else {
    		return TwoDMatHaar.EDGE_TYPE.NONE;
    	}
    }
    
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("[" + this.getWinRow() + ", " + this.getWinCol() + ", " + this.getWinSize() + "]\n");
    	HaarWaveletWinStats ew_stats = this.mWaveletWinStats;
    	final String edge_type = ew_stats.mEdgeType.toString();
    	DecimalFormat df = new DecimalFormat("#0.0");
    	final String haa = String.format(df.format(ew_stats.mHAA) + "|" + String.format(df.format(ew_stats.mHRA)));
    	final String vaa = String.format(df.format(ew_stats.mVAA) + "|" + String.format(df.format(ew_stats.mVRA)));
    	final String daa = String.format(df.format(ew_stats.mDAA) + "|" + String.format(df.format(ew_stats.mDRA)));
    	sb.append(edge_type + "\n");
    	sb.append(haa + "\n");
    	sb.append(vaa + "\n");
    	sb.append(daa + "\n");
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
    		HaarWaveletWin other_edge_win = (HaarWaveletWin) other;
    		return this.getWinRow() == other_edge_win.getWinRow() &&
    				this.getWinCol() == other_edge_win.getWinCol() &&
    				this.getWinSize() == other_edge_win.getWinSize();
    	}
    }

	@Override
	public int compareTo(HaarWaveletWin o) {
		int win_col = ((HaarWaveletWin) o).getWinCol();
		return this.getWinCol() - win_col;
	}
	
	public static Comparator<HaarWaveletWin> HaarWavletWinColComparator = new Comparator<HaarWaveletWin>() {
		
		public int compare(HaarWaveletWin hww01, HaarWaveletWin hww02) {
			
			return hww01.getWinCol() - hww02.getWinCol();
		}
	};
	
public static Comparator<HaarWaveletWin> HaarWavletWinRowComparator = new Comparator<HaarWaveletWin>() {
		
		public int compare(HaarWaveletWin hww01, HaarWaveletWin hww02) {
			return hww01.getWinRow() - hww02.getWinRow();
		}
	};
}
