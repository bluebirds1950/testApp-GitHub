package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

//reading image class
public class ImageReader {
    Mat image;
    Mat grayScaleImg;

    public ImageReader() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


        image = new Mat();
        grayScaleImg = new Mat();

        //reading image
        image = Imgcodecs.imread("src/sample/test_img/ecat-bubble-sheet.png");
//        image = Imgcodecs.imread("src/sample/test_img/test.jpeg");


        // Check if image is loaded fine
        if( image.empty() ) {
            System.out.println("Error opening image!");
            System.exit(-1);
        }

        //converting to gry scale
        grayScaleImg = new Mat();
        image.copyTo(grayScaleImg);
        Imgproc.cvtColor(grayScaleImg, grayScaleImg, Imgproc.COLOR_BGR2GRAY);

        //blurring image to remove noise <results down,if blur >
        Imgproc.medianBlur(grayScaleImg, grayScaleImg, 1);
//        HighGui.imshow("test",grayScaleImg);
        Mat circles = new Mat();

        Imgproc.HoughCircles(grayScaleImg, circles, Imgproc.HOUGH_GRADIENT, 1.0,
                (double)grayScaleImg.rows()/100, // change this value to detect circles with different distances to each other
                100.0, 20.0, 10, 30); // change the last two parameters
        // (min_radius & max_radius) to detect larger circles

        for (int x = 0; x < circles.cols(); x++) {
            double[] c = circles.get(0, x);
            Point center = new Point(Math.round(c[0]), Math.round(c[1]));
            // circle center
            Imgproc.circle(image, center, 1, new Scalar(0,100,100), 1, 8, 0 );
            // circle outline
            int radius = (int) Math.round(c[2]);
            Imgproc.circle(image, center, radius, new Scalar(255,0,255), 2, 8, 0 );
        }
        HighGui.imshow("detected circles", image);
        HighGui.waitKey();
        System.exit(0);

    }


    public void readImage(String path) {

    }


}
