package org.vkedco.compvision.dog;

import org.vkedco.compvision.dog.DOGWin.EDGE_TYPE;

public class DOGWinStats {
	public EDGE_TYPE mEdgeType = DOGWin.EDGE_TYPE.NONE;
	public double mDOGVal = 0.0; 
	
	public DOGWinStats(EDGE_TYPE et, double dog_val) {
		mEdgeType 	= et;
		mDOGVal 	= dog_val;
	}
}
