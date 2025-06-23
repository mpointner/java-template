package at.ac.tgm.view.items;

import at.ac.tgm.consts.Direction;
import at.ac.tgm.controller.GameController;
import at.ac.tgm.model.items.HamsterModel;

import java.awt.*;

import static at.ac.tgm.view.util.ImageUtil.loadImage;

public class HamsterPanel extends BaseGameItemPanel {
    private static final Image imageEast = loadImage("hamstereast.png");
    private static final Image imageWest = loadImage("hamsterwest.png");
    private static final Image imageNorth = loadImage("hamsternorth.png");
    private static final Image imageSouth = loadImage("hamstersouth.png");
    
    private HamsterModel model;
    
    public HamsterPanel(HamsterModel model, GameController controller) {
        super(controller);
        this.model = model;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = switch (model.getDirection()) {
            case Direction.WEST -> imageWest;
            case Direction.EAST -> imageEast;
            case Direction.NORTH -> imageNorth;
            case Direction.SOUTH -> imageSouth;
            default -> throw new IllegalStateException("Unexpected value: " + model.getDirection());
        };
        g.drawImage(image, 0, 0, this);
    }
}
