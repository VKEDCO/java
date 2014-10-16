package org.vkedco.compvision.dog;

import java.util.ArrayList;

public class RGOTNeighborhood {

	public int bin_Number;
	public double bin_Theta;
	public ArrayList<RGOT> membersList=new ArrayList<RGOT>();
	
	public RGOTNeighborhood(double theta,int num){
		this.bin_Theta=theta;
		this.bin_Number=num;
	}
}
