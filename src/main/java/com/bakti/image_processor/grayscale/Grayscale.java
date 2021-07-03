package com.bakti.image_processor.grayscale;

import com.bakti.image_processor.ImageConverter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Grayscale implements ImageConverter {

    public BufferedImage convert(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage convertedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get one pixel color

                int pixel = image.getRGB(x, y);
                // retrieve color of all channels
                Color c = new Color(image.getRGB(x, y));
                int R = (int) (c.getRed() * 0.299);
                int G = (int) (c.getGreen() * 0.587);
                int B = (int) (c.getBlue() * 0.114);


                int sum = R + G + B;
                Color grayColor = new Color(sum, sum, sum);
                convertedImage.setRGB(x, y, grayColor.getRGB());

            }
        }
        return convertedImage;
    }
}
