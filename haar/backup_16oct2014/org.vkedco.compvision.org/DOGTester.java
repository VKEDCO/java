package org.vkedco.compvision.dog;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;


public class DOGTester {

	static String HOME="";
	
	static{	    
	    HOME=System.getProperty("user.home");
    	System.load(HOME+"/opencv_2.4.4/bin/libopencv_java244.so");
    }
 
	
	public static void main(String[] args) {
	  
		String img_path = DOGConstants.PATH + "line_image.jpg";
		Mat mat=Highgui.imread(img_path);
		//DOGDetector.getDOG(mat, 0, 0, 64, img_path);
		//DOGDetector.getDOG(mat, 64, 192, 64, img_path);
		//DOGDetector.getDOG(mat, 192, 256, 64, img_path);
		/////DOGDetector.getDOG(mat, 192, 320, 64, img_path);
		//DOGDetector.getDOG(mat, 256, 256, 64, img_path);
		//DOGDetector.getDOG(mat, 128, 192, 64, img_path);
		//DOGDetector.getDOG(mat, 128, 256, 64, img_path);
		DOG.getDOG(mat, 80, 80, 64, img_path);
	}

}
