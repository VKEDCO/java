package org.vkedco.wavelets.cv;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.vkedco.wavelets.utils.Utils;

/**
 * @author Vladimir Kulyukin
 */
public class CreateImagesWithLines {
    
    static final String OPENCV_DLL_PATH =
            "PATH TO opencv_java249.dll";

    // Use a static code block to load the dll/so;
    static {
        System.load(OPENCV_DLL_PATH);
    }
    
    public static void drawLineInBlackMat(Mat mat, int start_x, int start_y,
            int end_x, int end_y, Scalar color, int line_width) {
        Point start_point = new Point(start_x, start_y);
        Point end_point = new Point(end_x, end_y);
        Core.line(mat, start_point, end_point, color, line_width);
    }
    
    public static Mat createBlackMat(int num_rows, int num_cols) {
        Mat mat = new Mat(num_rows, num_cols, CvType.CV_8UC1);
        double[] color = {0, 0, 0};
        for(int row = 0; row < num_rows; row++) {
            for(int col = 0; col < num_cols; col++) {
                mat.put(row, col, color);
            }
        }
        return mat;
    }
    
    public static void drawLineInBlackMat(Mat mat, Point start_point, Point end_point,
            Scalar color, int line_width) {
        Core.line(mat, start_point, end_point, color, line_width);
    }
    
    public static void createBlackMatWithWhiteLine(String img_path, int num_rows, int num_cols, int start_x, int start_y,
            int end_x, int end_y, int line_width) {
        Mat mat = createBlackMat(num_rows, num_cols);
        drawLineInBlackMat(mat, start_x, start_y, end_x, end_y, new Scalar(255, 255, 255), line_width);
        Highgui.imwrite(img_path, mat);
        mat.release();
    }
    
    public static double[][] convertGrayscaleMatTo2DArray(String img_path) {
        Mat mat = Highgui.imread(img_path);
        double[][] ary = new double[mat.rows()][mat.cols()];
        for(int row = 0; row < mat.rows(); row++) {
            for(int col = 0; col < mat.cols(); col++) {
                ary[row][col] = mat.get(row, col)[0];
            }
        }
        mat.release();
        return ary;
    }
    
    static final String IMAGE_SOURCE_DIR =
            "C:/Users/vladimir/research/images/hwt_lines/";
    
    public static void createBlackMatsWithWhiteHorLines(int img_size) {
        // hor lines: y is a row
        for(int y = 0; y < img_size; y++) {
            final String imgPath = IMAGE_SOURCE_DIR + "hor_lines/hor_line_img" + img_size +
                    "x" + img_size + "_" + y + ".JPG";
            createBlackMatWithWhiteLine(imgPath,
                    img_size, img_size, 0, y, img_size-1, y, 1);
            double[][] orig = convertGrayscaleMatTo2DArray(imgPath);
            System.out.println("original mat");
            Utils.display2DArray(orig, img_size, img_size);
            System.out.println("=======");
        }
        
    }
    
    public static void createBlackMatsWithWhiteVerLines(int img_size) {
        // hor lines: x is a row
        for(int x = 0; x < img_size; x++) {
            final String imgPath = IMAGE_SOURCE_DIR + "ver_lines/ver_line_img" + img_size +
                    "x" + img_size + "_" + x + ".JPG";
            createBlackMatWithWhiteLine(imgPath,
                    img_size, img_size, x, 0, x, img_size-1, 1);
            double[][] orig = convertGrayscaleMatTo2DArray(imgPath);
            System.out.println("original mat");
            Utils.display2DArray(orig, img_size, img_size);
            System.out.println("=======");
        }
    }
    
    public static void createBlackMatsWithWhiteTopLeftBotRightDigLines(int img_size) {
        for(int x = 0; x < img_size; x++) {
            int start_x = x, start_y = 0, end_x = img_size-1, end_y = img_size-x-1;
            final String imgPath = IMAGE_SOURCE_DIR + 
                    "dig_lines/dig_top_left_bot_right_line_img" + img_size +
                    "x" + img_size + "_" + "_" + start_x + "_" + start_y + 
                    "_" + end_x + "_" + end_y + ".JPG";
            createBlackMatWithWhiteLine(imgPath,
                    img_size, img_size, start_x, start_y, end_x, end_y, 1);
            double[][] orig = convertGrayscaleMatTo2DArray(imgPath);
            System.out.println("original mat");
            Utils.display2DArray(orig, img_size, img_size);
            System.out.println("=======");
        }
        for(int y = 0; y < img_size; y++) {
            int start_x = 0, start_y = y, end_x = img_size-y-1, end_y = img_size-1;
            final String imgPath = IMAGE_SOURCE_DIR + 
                    "dig_lines/dig_top_left_bot_right_line_img" + img_size +
                    "x" + img_size + "_" + "_" + start_x + "_" + start_y + 
                    "_" + end_x + "_" + end_y + ".JPG";
            createBlackMatWithWhiteLine(imgPath,
                    img_size, img_size, start_x, start_y, end_x, end_y, 1);
            double[][] orig = convertGrayscaleMatTo2DArray(imgPath);
            System.out.println("original mat");
            Utils.display2DArray(orig, img_size, img_size);
            System.out.println("=======");
        }
    }
    
    public static void main(String[] args) {
        createBlackMatsWithWhiteTopLeftBotRightDigLines(8);
        createBlackMatsWithWhiteTopLeftBotRightDigLines(16);
        createBlackMatsWithWhiteTopLeftBotRightDigLines(32);

        
        
        //createBlackMatWithWhiteLine(horfilePath2, 8, 8, 0, , 15, 8, 1);
        //createBlackMatWithWhiteLine(horfilePath3, 16, 16, 0, 12, 15, 12, 1);
        //double[][] ary1 = convertGrayscaleMatTo2DArray(horfilePath1);
        //Utils.display2DArray(ary1, 8, 8);
        //double[][] ary2 = convertGrayscaleMatTo2DArray(horfilePath2);
        //Utils.display2DArray(ary2, 16, 16);
        //double[][] ary3 = convertGrayscaleMatTo2DArray(horfilePath3);
        //Utils.display2DArray(ary3, 16, 16);
        //createScaledImageOf2DHWT(IMAGE_SOURCE_DIR + "hor_line_img8x8_1.JPG",
        //        IMAGE_SOURCE_DIR + "hor_line_img8x8_1_1iter.JPG", 1);
        //double[][] hwt_1_1 = convertGrayscaleMatTo2DArray(IMAGE_SOURCE_DIR +
        //        "hor_line_img8x8_1_1iter.JPG");
        //Utils.display2DArray(hwt_1_1, 8, 8);
        
        //createScaledImageOf2DHWT(IMAGE_SOURCE_DIR + "hor_line_img8x8_2.JPG",
        //        IMAGE_SOURCE_DIR + "hor_line_img8x8_1_1iter.JPG", 2);
        //double[][] hwt_1_2 = convertGrayscaleMatTo2DArray(IMAGE_SOURCE_DIR +
        //        "hor_line_img8x8_1_2iters.JPG");
        //Utils.display2DArray(hwt_1_2, 8, 8);
        
        /*
        createScaledImageOf2DHWT(IMAGE_SOURCE_DIR + "hor_line_img16x16_1.JPG",
                IMAGE_SOURCE_DIR + "hor_line_img16x16_1_2iters.JPG", 2);
        double[][] hwt_1_2 = convertGrayscaleMatTo2DArray(IMAGE_SOURCE_DIR +
                "hor_line_img16x16_1_2iters.JPG");
        Utils.display2DArray(hwt_1_2, 16, 16);
        
        createScaledImageOf2DHWT(IMAGE_SOURCE_DIR + "hor_line_img16x16_1.JPG",
                IMAGE_SOURCE_DIR + "hor_line_img16x16_1_3iters.JPG", 3);
        double[][] hwt_1_3 = convertGrayscaleMatTo2DArray(IMAGE_SOURCE_DIR +
                "hor_line_img16x16_1_3iters.JPG");
        Utils.display2DArray(hwt_1_3, 16, 16);
        
        createScaledImageOf2DHWT(IMAGE_SOURCE_DIR + "hor_line_img16x16_1.JPG",
                IMAGE_SOURCE_DIR + "hor_line_img16x16_1_4iters.JPG", 4);
        double[][] hwt_1_4 = convertGrayscaleMatTo2DArray(IMAGE_SOURCE_DIR +
                "hor_line_img16x16_1_4iters.JPG");
        Utils.display2DArray(hwt_1_4, 16, 16);
        */
    }
    
}
