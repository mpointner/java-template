package at.ac.tgm.view;

import at.ac.tgm.controller.GameController;
import at.ac.tgm.model.items.CornModel;
import at.ac.tgm.model.items.BaseGameItemModel;
import at.ac.tgm.model.GameModel;

import javax.swing.*;
import java.awt.*;

import static at.ac.tgm.consts.Settings.buttonSize;
import static at.ac.tgm.consts.Settings.iconSize;
import static at.ac.tgm.view.util.ImageUtil.*;

public class GameWindow extends JFrame {
    private final GameController controller;
    private JLabel messageLabel;
    private JPanel wrapper;
    private JPanel grid;
    private CellPane[][] cell;
    
    public GameWindow(GameController controller) {
        this.controller = controller;
        GameModel model = controller.getModel();
        setTitle("Hamster-Spiel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(300, 300));
        
        addMenuBar();
        addScrollPane();
        addMessageBar();
        
        setFocusable(true);
        addKeyListener(controller);
        setIconImage(Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/info.gif")));
        pack();
        setSize(Math.max(model.getColsGrid() * iconSize + 20, 200), model.getRowsGrid() * iconSize + 95);
        setVisible(true);
    }
    
    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(newButton("positionHamster", "hamstereast.png", "Hamster positionieren"));
        menuBar.add(newButton("addCorn", "corn24.gif", "Korn hinzufügen"));
        this.setJMenuBar(menuBar);
    }
    
    private void addMessageBar() {
        messageLabel = new JLabel("Willkommen im Hamster-Spiel!");
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
        addGrid();
        
        JScrollPane scrollPane = new JScrollPane(wrapper);
        scrollPane.addKeyListener(controller);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void addGrid() {
        grid = new JPanel();
        grid.addKeyListener(controller);
        int rows = controller.getModel().getRowsGrid(), cols = controller.getModel().getColsGrid();
        grid.setLayout(new GridLayout(rows, cols));
        grid.setPreferredSize(new Dimension(iconSize * cols, iconSize * rows));
        cell = new CellPane[rows][cols];
        for (int row = 0; row < rows; row++) {
            //cell[i] = new CellPanel[cols];
            for (int col = 0; col < cols; col++) {
                CellPane cellPane = new CellPane(controller, row, col);
                grid.add(cellPane);
                cell[row][col] = cellPane;
            }
        }
        drawGame(controller.getModel());
        wrapper.add(grid);
    }
    
    public void drawGame(GameModel gameModel) {
        // Clear
        for (int row = 0; row < cell.length; row++) {
            for (int col = 0; col < cell[row].length; col++) {
                cell[row][col].removeAll();
            }
        }
        drawGameItem(gameModel.getHamster());
        CornModel[][] corns = gameModel.getCorns();
        for (int row = 0; row < cell.length; row++) {
            for (int col = 0; col < cell[row].length; col++) {
                if (corns[row][col] != null) {
                    drawGameItem(corns[row][col]);
                }
            }
        }
        grid.repaint();
    }
    
    private void drawGameItem(BaseGameItemModel model) {
        this.cell[model.getRow()][model.getCol()].drawGameItem(model);
    }
}
