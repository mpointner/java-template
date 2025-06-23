package at.ac.tgm.view.items;

import at.ac.tgm.controller.GameController;
import at.ac.tgm.model.items.CornModel;

import java.awt.*;

import static at.ac.tgm.view.util.ImageUtil.loadImage;

public class CornPanel extends BaseGameItemPanel {
    private static final Image corn1 = loadImage("1Corn32.png");
    private static final Image corn2 = loadImage("2Corn32.png");
    private static final Image corn3 = loadImage("3Corn32.png");
    private static final Image corn4 = loadImage("4Corn32.png");
    private static final Image corn5 = loadImage("5Corn32.png");
    private static final Image corn6 = loadImage("6Corn32.png");
    private static final Image corn7 = loadImage("7Corn32.png");
    private static final Image corn8 = loadImage("8Corn32.png");
    private static final Image corn9 = loadImage("9Corn32.png");
    private static final Image corn10 = loadImage("10Corn32.png");
    private static final Image corn11 = loadImage("11Corn32.png");
    private static final Image corn12 = loadImage("12Corn32.png");
    
    private final CornModel model;
    
    public CornPanel(CornModel model, GameController controller) {
        super(controller);
        this.model = model;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = switch (model.getAmount()) {
            case 1 -> corn1;
            case 2 -> corn2;
            case 3 -> corn3;
            case 4 -> corn4;
            case 5 -> corn5;
            case 6 -> corn6;
            case 7 -> corn7;
            case 8 -> corn8;
            case 9 -> corn9;
            case 10 -> corn10;
            case 11 -> corn11;
            default -> corn12;
        };
        g.drawImage(image, 0, 0, this);
    }
}
