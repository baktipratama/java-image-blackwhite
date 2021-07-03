package com.bakti.image_processor;

import java.awt.image.BufferedImage;

public interface ImageConverter {

    BufferedImage convert(BufferedImage originaltImage);
}
