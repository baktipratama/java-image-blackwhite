package com.bakti.image_processor;

import com.bakti.image_processor.binarization.AverageBinarization;
import com.bakti.image_processor.binarization.OtsuBinarization;
import com.bakti.image_processor.grayscale.Grayscale;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

public class Run {
    public static void main(String args[]) throws IOException {
        ImageConverter converter = null;
        if (args.length < 2) {
            System.out.println("Need 2 params, converterType and filename");
            throw new InvalidParameterException();
        }
        String converterType = args[0];
        File image = new File(args[1]);
        BufferedImage inputImg = ImageIO.read(image);

        switch (converterType){
            case "AverageBinarization":
                converter = new AverageBinarization();
                break;
            case "OtsuBinarization":
                converter = new OtsuBinarization();
                break;
            case "Grayscale":
                converter = new Grayscale();
                break;
        }
        if (converter == null) {
            System.out.println("coverter type not found");
            throw new InvalidParameterException();
        }
        BufferedImage outputImage = converter.convert(inputImg);
        String outputFilename = "output.jpg";
        if(!args[2].isEmpty()){
            outputFilename = args[2];
        }
        File outputfile = new File(outputFilename);
        ImageIO.write(outputImage, "jpg", outputfile);
    }
}
