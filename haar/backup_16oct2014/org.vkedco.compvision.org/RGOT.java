package org.vkedco.compvision.dog;

public class RGOT {

	double dtheta;
	int freq;
	int row,column;
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public RGOT(double dtheta,int freq)
	{
		this.dtheta=dtheta;
		this.freq=freq;
	}
	
	public double getTheta() { return dtheta; }
	public int getFreq() { return freq; }
}
