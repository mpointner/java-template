package at.ac.tgm.view;

import at.ac.tgm.controller.GameController;
import at.ac.tgm.model.GameModel;
import at.ac.tgm.model.items.BaseGameItemModel;
import at.ac.tgm.model.items.CornModel;

import javax.swing.*;
import java.awt.*;

import static at.ac.tgm.consts.Settings.buttonSize;
import static at.ac.tgm.consts.Settings.iconSize;
import static at.ac.tgm.view.util.ImageUtil.loadIcon;

public class GameWindow extends JFrame {
    private final GameController controller;
    private JLabel messageLabel;
    private JPanel wrapper;
    private JPanel grid;
    private CellPane[][] cell;
    
    public GameWindow(GameController controller) {
        this.controller = controller;
        GameModel model = controller.getModel();
        setTitle("Hamster Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(300, 300));
        
        addMenuBar();
        addScrollPane();
        addMessageBar();
        
        drawGame(model);
        
        setFocusable(true);
        addKeyListener(controller);
        setIconImage(Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/info.gif")));
        pack();
        setVisible(true);
    }
    
    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(newButton("newGrid", "Terrain24.gif", "New Grid"));
        menuBar.add(newButton("positionHamster", "hamstereast.png", "Position Hamster"));
        menuBar.add(newButton("addCorn", "corn24.gif", "Add Corn"));
        menuBar.add(newButton("deleteGameItem", "Delete24.gif", "Delete Item"));
        this.setJMenuBar(menuBar);
    }
    
    private void addMessageBar() {
        messageLabel = new JLabel("Welcome to the Hamster-Game!");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(null);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(messageLabel, BorderLayout.SOUTH);
    }
    
    public void setMessage(String message) {
        messageLabel.setText(message != null && !message.isEmpty() ? message : " ");
    }
    
    private JButton newButton(String actionCommand, String iconResourcePath, String toolTipText) {
        JButton button = new JButton(loadIcon(iconResourcePath));
        button.setActionCommand(actionCommand);
        button.setMaximumSize(new Dimension(buttonSize, buttonSize));
        button.setToolTipText(toolTipText);
        button.setFocusPainted(false);
        button.addActionListener(controller);
        button.addKeyListener(controller);
        return button;
    }
    
    private void addScrollPane() {
        wrapper = new JPanel(new GridBagLayout());
        wrapper.addKeyListener(controller);
        createGrid();
        
        JScrollPane scrollPane = new JScrollPane(wrapper);
        scrollPane.addKeyListener(controller);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    /**
     * Adds or resizes the grid view
     */
    private void createGrid() {
        int rows = controller.getModel().getRowsGrid();
        int cols = controller.getModel().getColsGrid();
        
        grid = new JPanel();
        grid.addKeyListener(controller);
        grid.setLayout(new GridLayout(rows, cols));
        grid.setPreferredSize(new Dimension(iconSize * cols, iconSize * rows));
        cell = new CellPane[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                CellPane cellPane = new CellPane(controller, row, col);
                grid.add(cellPane);
                cell[row][col] = cellPane;
            }
        }
        wrapper.removeAll();
        wrapper.add(grid);
        
        setSize(Math.max(cols * iconSize + 20, 200), rows * iconSize + 95);
    }
    
    public void drawGame(GameModel model) {
        if (model.getRowsGrid() != cell.length || model.getColsGrid() != cell[0].length) {
            createGrid();
        }
        // Clear
        for (int row = 0; row < cell.length; row++) {
            for (int col = 0; col < cell[row].length; col++) {
                cell[row][col].removeAll();
            }
        }
        drawGameItem(model.getHamster());
        CornModel[][] corns = model.getCorns();
        for (int row = 0; row < cell.length; row++) {
            for (int col = 0; col < cell[row].length; col++) {
                drawGameItem(corns[row][col]);
            }
        }
        grid.repaint();
    }
    
    private void drawGameItem(BaseGameItemModel model) {
        if (model != null) {
            this.cell[model.getRow()][model.getCol()].drawGameItem(model);
        }
    }
}
