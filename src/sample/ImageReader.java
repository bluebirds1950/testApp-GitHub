package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

//reading image class
public class ImageReader {



    public static void readImage(String path){

        System.out.println(System.getProperty("java.library.path"));
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat Image = Imgcodecs.imread(path);
        Mat grayScaleImg = new Mat();
        Image.copyTo(grayScaleImg);

        Imgproc.cvtColor(grayScaleImg,grayScaleImg,Imgproc.COLOR_BGR2GRAY);
    }
}
