package org.vkedco.compvision.dog;

import java.util.HashMap;
import java.util.Map;

import org.opencv.core.Mat;

public class Gradients {

	HashMap<Double, Integer> mThetaToFreqMap = new HashMap<Double, Integer>();

	public RGOT computeRGOT(Mat image, double theta_thresh, double magn_thresh) {
		int height = image.height();
		int width = image.width();
		double dx;
		double dy;
		double gradient_magnitude;
		double gradient_theta;
		int freq = 0;
		mThetaToFreqMap.clear();

		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {

				dx = rgb_pix_dx(r, c, image);
				dy = rgb_pix_dy(r, c, image);

				gradient_magnitude = gradientMagnitude(dx, dy);
				gradient_theta = gradientOrientation(dx, dy);

				if ((Math.abs(gradient_theta) <= theta_thresh)
						&& (Math.abs(gradient_magnitude) >= magn_thresh)) {
					if (mThetaToFreqMap.containsKey(gradient_theta)) {
						freq = mThetaToFreqMap.get(gradient_theta);
						mThetaToFreqMap.put(gradient_theta, ++freq);
					} else
						mThetaToFreqMap.put(gradient_theta, 1);
				}

			}
		}
		return sorted(mThetaToFreqMap);
	}

	private RGOT sorted(HashMap<Double, Integer> rGOT) {

		int freq = 0;
		for (int val : rGOT.values()) {
			if (freq < val)
				freq = val;
		}
		for (double key : rGOT.keySet()) {
			if (rGOT.get(key) == freq) {
				return new RGOT(key, freq);
			}
		}
		return new RGOT(0, 0);
	}
	
	

	public Boolean isInRange(int r, int c, Mat image) {
		if ((c > 0 && c < (image.width() - 1))
				&& (r > 0 && r < (image.height() - 1)))
			return true;
		else
			return false;
	}

	// pass column,row,image
	public double rgb_pix_dy(int r, int c, Mat image) {
		double default_delta = 1;
		double dy;
		if (!isInRange(r, c, image))
			return default_delta;
		else {
			dy = image.get(r - 1, c)[0] - image.get(r + 1, c)[0];
			if (dy == 0)
				return default_delta;
			else
				return dy;
		}
	}
	
	public double rgb_pix_dx(int r, int c, Mat image) {
		double default_delta = 1;
		double dx;
		if (!isInRange(r, c, image))
			return default_delta;
		else {
			dx = image.get(r, c + 1)[0] - image.get(r, c - 1)[0];
			if (dx == 0)
				return default_delta;
			else
				return dx;
		}

	}

	public double gradientMagnitude(double dx, double dy) {
		return Math.sqrt(Math.pow(dx, 2) + (Math.pow(dy, 2)));
	}

	public double gradientOrientation(double dx, double dy) {
		// double default_theta=-400;
		double th;

		th = (Math.atan2(dy, dx) * 180 / Math.PI);

		if (th < 0)
			th = th + 360;

		if (th < 0)
			th = Math.floor(th);
		else if (th > 0) {
			if (th < 1)
				th = 0;
			else
				th = Math.ceil(th);
		}

		if (th == 0)
			th += 90 * 3;
		else
			th -= 90;

		return th;
	}
	
	// VK: 26sept14
	// pass column,row,image
	static HashMap<Double, Integer> mStaticThetaToFreqMap = new HashMap<Double, Integer>();
	public static HashMap<Double, Integer> computeRGOTTableForMat(Mat image, double theta_thresh, double magn_thresh) {
		mStaticThetaToFreqMap.clear();
		final int height = image.height();
		final int width  = image.width();
		double dx; double dy;
		double gradient_magnitude;
		double gradient_theta;
		int freq = 0;

		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {

				dx = rgbPixDX(r, c, image);
				dy = rgbPixDY(r, c, image);

				gradient_magnitude = gradientMagn(dx, dy);
				gradient_theta = gradientOrient(dx, dy);

				if ((Math.abs(gradient_theta) <= theta_thresh)
						&& (Math.abs(gradient_magnitude) >= magn_thresh)) {
					if (mStaticThetaToFreqMap.containsKey(gradient_theta)) {
						freq = mStaticThetaToFreqMap.get(gradient_theta);
						mStaticThetaToFreqMap.put(gradient_theta, ++freq);
					} else
						mStaticThetaToFreqMap.put(gradient_theta, 1);
				}
			}
		}
		return mStaticThetaToFreqMap;
	}
	
	public static RGOT getMostFrequentRGOT(HashMap<Double, Integer> theta_to_freq_map) {
		
		int max_freq = 0;
		Map.Entry<Double, Integer> highest_frequency_entry = null;
		for(Map.Entry<Double, Integer> theta_to_freq : theta_to_freq_map.entrySet()) {
			if ( theta_to_freq.getValue() > max_freq ) {
				highest_frequency_entry = theta_to_freq;
				max_freq = theta_to_freq.getValue().intValue();
			}
		}
		if ( highest_frequency_entry != null ) {
			return new RGOT(highest_frequency_entry.getKey().doubleValue(), highest_frequency_entry.getValue().intValue());
		}
		else {
			return null;
		}
	}
	
	
	
	static boolean areRowAndColInRange(int r, int c, Mat image) {
		if ((c > 0 && c < (image.width() - 1))
				&& (r > 0 && r < (image.height() - 1)))
			return true;
		else
			return false;
	}
	
	static double rgbPixDY(int r, int c, Mat image) {
			double default_delta = 1;
			double dy;
			if (!Gradients.areRowAndColInRange(r, c, image) )
				return default_delta;
			else {
				dy = image.get(r - 1, c)[0] - image.get(r + 1, c)[0];
				if (dy == 0)
					return default_delta;
				else
					return dy;
			}
	}
		
	static double rgbPixDX(int r, int c, Mat image) {
			double default_delta = 1;
			double dx;
			if (!Gradients.areRowAndColInRange(r, c, image) )
				return default_delta;
			else {
				dx = image.get(r, c + 1)[0] - image.get(r, c - 1)[0];
				if (dx == 0)
					return default_delta;
				else
					return dx;
			}
		}

	static double gradientMagn(double dx, double dy) {
			return Math.sqrt(Math.pow(dx, 2) + (Math.pow(dy, 2)));
		}

	static double gradientOrient(double dx, double dy) {
			// double default_theta=-400;
			double th;

			th = (Math.atan2(dy, dx) * 180 / Math.PI);

			if (th < 0)
				th = th + 360;

			if (th < 0)
				th = Math.floor(th);
			else if (th > 0) {
				if (th < 1)
					th = 0;
				else
					th = Math.ceil(th);
			}

			if (th == 0)
				th += 90 * 3;
			else
				th -= 90;

			return th;
		}

}
