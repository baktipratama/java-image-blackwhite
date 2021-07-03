package com.bakti.image_processor.binarization;

import com.bakti.image_processor.ImageConverter;
import com.bakti.image_processor.grayscale.Grayscale;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OtsuBinarization implements ImageConverter {
    public BufferedImage convert(BufferedImage image) {
        Grayscale grayscale = new Grayscale();
        BufferedImage grayscaledImage = grayscale.convert(image);
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage convertedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int level = 256;
        int histogram[] = new int[256];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // retrieve color of all channels
                Color c = new Color(image.getRGB(x, y));
                int R = c.getRed();
                histogram[R]++;
            }

        }
        int sum = 0;
        for (int k = 0; k < level; k++)
            sum = sum + k * histogram[k];

        float sumB = 0;
        int wb = 0;
        int wf = 0;

        float varMax = 0;
        int treshold = 0;
        int total = width * height;

        for (int k = 0; k < level; k++) {
            wb += histogram[k];
            if (wb == 0)
                continue;

            wf = total - wb;
            if (wf == 0)
                break;

            sumB += (float) (k * histogram[k]);

            float mb = sumB / wb;
            float mf = (sum - sumB) / wf;

            float varBetween = (float) wb * (float) wf * (mb - mf) * (mb - mf);

            if (varBetween > varMax) {
                varMax = varBetween;
                treshold = k;
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // get one pixel color
                int pixel = grayscaledImage.getRGB(x, y);
                // retrieve color of all channels
                Color c = new Color(image.getRGB(x, y));
                int R = c.getRed();
                // take conversion up to one single value
                int a1=0;
                if (R >= treshold) a1 = 255;
                // set new pixel color to output bitmap
                Color BWColor = new Color(a1, a1, a1);
                convertedImage.setRGB(x, y, BWColor.getRGB());
            }
        }
        return convertedImage;
    }
}
