package at.ac.tgm.view.util;

import at.ac.tgm.view.GameWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static at.ac.tgm.consts.Settings.iconSize;

public class ImageUtil {
    
    public static ImageIcon loadIcon(String resourcePath) {
        return loadIcon(resourcePath, iconSize);
    }
    
    public static ImageIcon loadIcon(String resourcePath, int maxSize) {
        return new ImageIcon(loadImage(resourcePath, maxSize));
    }
    
    public static Image loadImage(String resourcePath) {
        return loadImage(resourcePath, iconSize);
    }
    
    public static Image loadImage(String resourcePath, int maxSize) {
        if (resourcePath == null) {
            throw new IllegalArgumentException("resourcePath is null");
        }
        if (!resourcePath.startsWith("/")) {
            resourcePath = "/" + resourcePath;
        }
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(
                    Objects.requireNonNull(GameWindow.class.getResource(resourcePath))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        if (originalImage.getWidth() == maxSize && originalImage.getHeight() == maxSize) {
            return originalImage;
        }
        
        double widthRatio = (double) maxSize / originalImage.getWidth();
        double heightRatio = (double) maxSize / originalImage.getHeight();
        double scale = Math.min(widthRatio, heightRatio);
        
        int newWidth = (int) Math.round(originalImage.getWidth() * scale);
        int newHeight = (int) Math.round(originalImage.getHeight() * scale);
        
        return originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }
}
