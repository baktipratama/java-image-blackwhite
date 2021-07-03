package com.bakti.image_processor.binarization;

import com.bakti.image_processor.ImageConverter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AverageBinarization implements ImageConverter {
    public BufferedImage convert(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        long sum[] = new long[width];
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // retrieve color of all channels
                Color c = new Color(image.getRGB(x, y));
                int R = c.getRed();
                int G = c.getGreen();
                int B = c.getBlue();
                // take conversion up to one single value
                int a1 = (R + G + B) / 3;
                sum[x] = sum[x] + a1;
            }
            sum[x] = sum[x] / height;
        }

        BufferedImage convertedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // retrieve color of all channels
                Color c = new Color(image.getRGB(x, y));
                int R = c.getRed();
                int G = c.getGreen();
                int B = c.getBlue();
                // take conversion up to one single value
                int a1 = (R + G + B) / 3;
                if (a1 < sum[x])
                    a1 = 0;
                else
                    a1 = 255;
                Color BWColor = new Color(a1, a1, a1);
                // set new pixel color to output bitmap
                convertedImage.setRGB(x, y, BWColor.getRGB());
            }
        }
        return convertedImage;
    }
}
