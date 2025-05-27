package general;
import javax.swing.*;
import javax.swing.border.StrokeBorder;

import rooms.Room;

import java.awt.*;

import java.util.HashMap;
import java.util.Map;

public class AdventureGUI {
    private JFrame frame;
    private JTextArea outputArea;
    private JTextField inputField;
    private JLabel imageLabel;
    private Game game;

    private JPanel mapPanel;

    public AdventureGUI(Game game) {
        this.game = game;
        buildGUI();
    }

    private void buildGUI() {
        frame = new JFrame("Text Adventure Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600); // wider to fit map
        frame.setLayout(new BorderLayout());

        // Image display
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(imageLabel, BorderLayout.NORTH);

        // Output display
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Map panel
        Room[][] roomGrid = game.getRoomGrid();
        HashMap<String, Color> roomToColor = new HashMap<String, Color>();
        roomToColor.put("startingRoom", Color.CYAN);              
        roomToColor.put("knightBossRoom", new Color(106, 13, 173));
        roomToColor.put("rangerBossRoom", Color.GREEN);
        roomToColor.put("minotaurBossRoom", new Color(204, 102, 0));
        roomToColor.put("mobRoom", new Color(123, 63, 0));
        roomToColor.put("trapRoom", Color.ORANGE);
        roomToColor.put("treasureRoom", Color.YELLOW);
        roomToColor.put("corridor", Color.GRAY);

        int cellSize = 30;
        mapPanel = new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);

                int pRow = game.getPlayerCoords()[0], pCol = game.getPlayerCoords()[1];

                for (int row = 0; row < roomGrid.length; row++) {
                    for (int col = 0; col < roomGrid[0].length; col++) {
                        g.setColor(Color.DARK_GRAY);
                        if (roomGrid[row][col].getVisited()){
                            g.setColor(roomToColor.get(roomGrid[row][col].getType()));
                        }

                        g.fillRect(col*cellSize, row*cellSize, cellSize, cellSize);

                        g.setColor(Color.black);
                        ((Graphics2D)g).setStroke(new BasicStroke(3));
                        g.drawRect(col*cellSize, row*cellSize, cellSize, cellSize);
                    }
                }
                g.setColor(Color.red);
                ((Graphics2D)g).setStroke(new BasicStroke(3));
                g.drawRect(pCol*cellSize, pRow*cellSize, cellSize, cellSize);
            }
        };
        mapPanel.setPreferredSize(new Dimension(cellSize*roomGrid.length, cellSize*roomGrid[0].length));
        frame.add(mapPanel, BorderLayout.WEST);

        // mapPanel.setPreferredSize(new Dimension(180, 180));

        // Input field
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> handleInput());
        inputField.addActionListener(e -> handleInput());

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        printText(game.getCurrentRoom().getLongDescription());
        updateRoomDisplay();
    }


    private void handleInput() {
        if (game.getRunning()==false)
            Game.printText("Game Over. You Died.");
        String input = inputField.getText().trim();
        inputField.setText("");
        if (!input.isEmpty()) {
            printText("");
            printText("> " + input);
            updateRoomDisplay();
            game.processCommand(input);
            // Game.getGame().onPlayerTurn();
        }
    }

    public void printText(String text) {
        outputArea.append(text + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void updateRoomDisplay() {
        String roomId = game.getCurrentRoom().getId();
        ImageIcon icon = new ImageIcon("images/placeholder.png");
        Image img = icon.getImage().getScaledInstance(800, 200, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        mapPanel.repaint();
    }
}