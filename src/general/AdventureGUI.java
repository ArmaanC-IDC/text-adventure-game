package general;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.StrokeBorder;
import java.awt.event.ActionEvent;

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
        //create the main jframe
        frame = new JFrame("Text Adventure Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
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
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.WHITE);
        outputArea.setCaretColor(Color.WHITE);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 20)); // 20 is the font size
        outputArea.setMargin(new Insets(10, 20, 10, 20));


        frame.add(scrollPane, BorderLayout.CENTER);

        // Map panel
        Room[][] roomGrid = game.getRoomGrid();
        //populate a hashmap showing which color each room should be
        HashMap<String, Color> roomToColor = new HashMap<String, Color>();
        roomToColor.put("startingRoom", Color.CYAN);
        roomToColor.put("knightBossRoom", new Color(106, 13, 173));
        roomToColor.put("rangerBossRoom", Color.GREEN);
        roomToColor.put("minotaurBossRoom", new Color(204, 102, 0));
        roomToColor.put("mobRoom", new Color(123, 63, 0));
        roomToColor.put("trapRoom", Color.ORANGE);
        roomToColor.put("treasureRoom", Color.YELLOW);
        roomToColor.put("corridor", Color.GRAY);

        int cellSize = 50;
        //create a new jpannel
        mapPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                //get player coords
                int pRow = game.getPlayerCoords()[0];
                int pCol = game.getPlayerCoords()[1];

                //loop through each row and col in roomGrid
                for (int row = 0; row < roomGrid.length; row++) {
                    for (int col = 0; col < roomGrid[0].length; col++) {
                        //set the color to dark grey
                        g.setColor(Color.DARK_GRAY);
                        
                        //if visited, set it to it's color
                        if (roomGrid[row][col].getVisited()) {
                            g.setColor(roomToColor.get(roomGrid[row][col].getType()));
                        }

                        g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);

                        //draw black outline around each room
                        g.setColor(Color.black);
                        ((Graphics2D) g).setStroke(new BasicStroke(3));
                        g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
                    }
                }

                //draw red outline around the current room
                g.setColor(Color.red);
                ((Graphics2D) g).setStroke(new BasicStroke(3));
                g.drawRect(pCol * cellSize, pRow * cellSize, cellSize, cellSize);
            }
        };

        //add pannel to main frame
        mapPanel.setBackground(Color.BLACK);
        mapPanel.setPreferredSize(new Dimension(cellSize * roomGrid.length, cellSize * roomGrid[0].length));
        frame.add(mapPanel, BorderLayout.WEST);

        // Input field
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton submitButton = new JButton("Submit");

        inputPanel.setBackground(Color.BLACK);
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(Color.WHITE);
        inputField.setCaretColor(Color.WHITE);
        inputField.setPreferredSize(new Dimension(inputField.getPreferredSize().width, 40));
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 20));

        //submit button
        submitButton.setBackground(Color.DARK_GRAY);
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(100, 40));
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 18));

        submitButton.addActionListener(e -> handleInput());
        inputField.addActionListener(e -> handleInput());

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);
        // Maximize to full screen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Add ESC key binding to exit full screen and restore window
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ESCAPE"), "exitFullScreen");
        frame.getRootPane().getActionMap().put("exitFullScreen", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame.setUndecorated(false);
                frame.setExtendedState(JFrame.NORMAL);
                frame.setVisible(true);
            }
        });

        frame.setVisible(true);
        printText(game.getCurrentRoom().getLongDescription());
        updateRoomDisplay();
    }

    private void handleInput() {
        //if game is over, do nothing and print game over
        if (!game.getRunning()) {
            if (Game.getPlayer().getHp() <= 0) {
                printText("Game Over. You Died.");
                return;
            } else {
                printText("Game Over. You Won.");
                return;
            }
        }

        //get the input than process it
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
        //scroll to bottom of output text area
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