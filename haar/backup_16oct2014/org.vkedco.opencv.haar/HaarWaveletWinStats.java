package org.vkedco.opencv.haar;

import org.vkedco.opencv.haar.TwoDMatHaar.EDGE_TYPE;

public class HaarWaveletWinStats {
	public EDGE_TYPE mEdgeType;
	public double mHAA; 
	public double mHRA;
	public double mVAA; 
	public double mVRA;
	public double mDAA; 
	public double mDRA;
	
	public HaarWaveletWinStats(EDGE_TYPE et, double haa, double hra, double vaa, double vra, double daa, double dra) {
		mEdgeType = et;
		mHAA = haa;
		mHRA = hra;
		mVAA = vaa;
		mVRA = vra;
		mDAA = daa;
		mDRA = dra;
	}
}
