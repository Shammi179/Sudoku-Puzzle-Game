package sudokuGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public final class GameFrame extends javax.swing.JFrame implements ActionListener {

    private static boolean gameOver = false;

    private static JButton[][] grid = new JButton[9][9];
    private static JButton[] options = new JButton[9];
    private static int[][] solved = new int[9][9];

    private static ArrayList<JLabel> strikeArea = new ArrayList<>();
    private static ArrayList<JPanel> backgroundPanels = new ArrayList<>();
    private static ArrayList<JButton> clickedButtons = new ArrayList<>();

    private static Color gridColor = Color.decode("#70AD47");
    private static Color backgroundColor = Color.decode("#70AD47");
    private static Color highlightColor = Color.LIGHT_GRAY;
    private static Color borderColor = Color.BLACK;

    private static int strikeAmount = 0;
    private static int amountOfAttempts = 3;



    public GameFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 750));
        this.setMinimumSize(new Dimension(100, 150));
        this.setTitle("Sudoku Game");
        this.setResizable(true);
        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);


        setMenu();

        createGrid();
        easyPuzzle();

        this.setVisible(true);

    }

    public void setMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu gameMenu = new JMenu("New Game");

        JMenuItem[] lvlList = new JMenuItem[4];
        String lvl = "";
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    lvl = "Easy";
                    break;
                case 1:
                    lvl = "Medium";
                    break;
                case 2:
                    lvl = "Hard";
                    break;
                case 3:
                    lvl = "Expert";
                    break;
            }
            JMenuItem level = new JMenuItem(lvl);
            lvlList[i] = level;
            gameMenu.add(level);
        }

        lvlList[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOver = false;
                strikeAmount = 0;
                for (int i = 0; i < amountOfAttempts; i++) {
                    strikeArea.get(i).setText("");
                }
                easyPuzzle();
            }
        });
        lvlList[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOver = false;
                strikeAmount = 0;
                for (int i = 0; i < amountOfAttempts; i++) {
                    strikeArea.get(i).setText("");
                }
                mediumPuzzle();
            }
        });
        lvlList[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOver = false;
                strikeAmount = 0;
                for (int i = 0; i < amountOfAttempts; i++) {
                    strikeArea.get(i).setText("");
                }
                hardPuzzle();
            }
        });
        lvlList[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOver = false;
                strikeAmount = 0;
                for (int i = 0; i < amountOfAttempts; i++) {
                    strikeArea.get(i).setText("");
                }
                veryHardPuzzle();
            }
        });

        JMenu appearanceMenu = new JMenu("Appearance");

        JMenu gridMenu = new JMenu("Grid Color");
        JMenuItem lgreen = new JMenuItem("Green");
        JMenuItem black = new JMenuItem("Black");
        JMenuItem lyellow = new JMenuItem("Light Yellow");
        JMenuItem blue = new JMenuItem("Light Blue");
        JMenuItem gray = new JMenuItem("Gray");

        lgreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridColor = Color.decode("#70AD47");
                borderColor = Color.BLACK;
                setGridColor(gridColor);
            }
        });
        black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridColor = Color.BLACK;

                borderColor = Color.WHITE;
                setGridColor(gridColor);
            }
        });
        lyellow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridColor = Color.decode("#FFF59D");
                borderColor = Color.BLACK;
                setGridColor(gridColor);
            }
        });
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridColor = Color.decode("#BBDEFB");
                borderColor = Color.BLACK;
                setGridColor(gridColor);
            }
        });
        gray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridColor = Color.decode("#78909C");
                borderColor = Color.BLACK;
                setGridColor(gridColor);
            }
        });

        gridMenu.add(lgreen);
        gridMenu.add(black);
        gridMenu.add(lyellow);
        gridMenu.add(blue);
        gridMenu.add(gray);

        JMenu backgroundMenu = new JMenu("Background Color");
        JMenuItem lgreen1 = new JMenuItem("Green");
        JMenuItem black1 = new JMenuItem("Black");
        JMenuItem lyellow1 = new JMenuItem("Light Yellow");
        JMenuItem blue1 = new JMenuItem("Light Blue");
        JMenuItem gray1 = new JMenuItem("Gray");

        lgreen1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackgroundColor(Color.decode("#70AD47"));
            }
        });
        black1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackgroundColor(Color.BLACK);
            }
        });
        lyellow1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackgroundColor(Color.decode("#FFF59D"));
            }
        });
        blue1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackgroundColor(Color.decode("#BBDEFB"));
            }
        });
        gray1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackgroundColor(Color.decode("#78909C"));
            }
        });

        backgroundMenu.add(lgreen1);
        backgroundMenu.add(black1);
        backgroundMenu.add(lyellow1);
        backgroundMenu.add(blue1);
        backgroundMenu.add(gray1);

        JMenu fontMenu = new JMenu("Font Size");
        JMenuItem small = new JMenuItem("Small");
        JMenuItem medium = new JMenuItem("Medium");
        JMenuItem large = new JMenuItem("Large");

        small.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font f = new Font("Cambria Bold", Font.PLAIN, 14);

                setGameFont(f);
            }
        });

        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font f = new Font("Cambria Bold", Font.PLAIN, 30);

                setGameFont(f);
            }
        });

        large.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font f = new Font("Cambria Bold", Font.PLAIN, 40);

                setGameFont(f);
            }
        });

        fontMenu.add(small);
        fontMenu.add(medium);
        fontMenu.add(large);

        JMenu highlightMenu = new JMenu("Highlight Color");
        JMenuItem green2 = new JMenuItem("Green");
        JMenuItem lightGray2 = new JMenuItem("Gray");



        green2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightColor = Color.decode("#33691E");
                setHighlightColor(highlightColor);
            }
        });


        lightGray2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightColor = Color.LIGHT_GRAY;
                setHighlightColor(highlightColor);
            }
        });

        highlightMenu.add(lightGray2);
        highlightMenu.add(green2);


        appearanceMenu.add(highlightMenu);
        appearanceMenu.add(gridMenu);
        appearanceMenu.add(backgroundMenu);
        appearanceMenu.add(fontMenu);

        JMenu instrucMenu = new JMenu("Instruction");
        JMenuItem helpItem = new JMenuItem("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"1. There are 4 levels in this game & you can choose levels from the menu bar \"New Game\" option.\n\n" +
                        "\t2. In the \"Appearance\" button on the menu bar, you can change the Font Size, Grid Color, Highlight Color, Background Color.\n\n" +
                        "\t3. You can see the solution if you fail to solve 3 times.\n\n" +
                        "N.B. : If you choose Black color for your grid, you may change the highlight color for more comfort.\n","Help",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"\tSudoku Game\n\tVersion:1.01","About",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        instrucMenu.add(helpItem);
        instrucMenu.add(aboutItem);
        menu.add(gameMenu);
        menu.add(appearanceMenu);
        menu.add(instrucMenu);

        this.setJMenuBar(menu);
    }

    //Menu Bar End


    public static void setHighlightColor(Color color) {
        if (!clickedButtons.isEmpty()) {
            horAndVertSquares(grid, clickedButtons.get(clickedButtons.size() - 1));
        }
    }


    public static void setGameFont(Font font) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j].setFont(font);
            }
            options[i].setFont(font);
        }

        for (int i = 0; i < amountOfAttempts; i++) {
            strikeArea.get(i).setFont(font);
        }
    }


    public static void setGridColor(Color color) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setBackground(color);
                if (color == Color.BLACK || color == Color.BLUE) {
                    grid[i][j].setForeground(Color.WHITE);
                } else {
                    grid[i][j].setForeground(Color.BLACK);
                }
            }
        }

        Border b = (BorderFactory.createMatteBorder(1, 1, 1, 1, borderColor));
        int i1;
        for (int i = 0; i < 9; i++) {
            if (i == 2 || i == 5) {
                i1 = 5;
            } else {
                i1 = 1;
            }
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) {
                    b = BorderFactory.createMatteBorder(1, 1, i1, 5, borderColor);
                    grid[i][j].setBorder(b);
                } else {
                    b = (BorderFactory.createMatteBorder(1, 1, i1, 1, borderColor));
                    grid[i][j].setBorder(b);
                }
            }
        }
    }


    public static void setBackgroundColor(Color color) {
        for (int i = 0; i < backgroundPanels.size(); i++) {
            backgroundPanels.get(i).setBackground(color);
        }
    }


    public static JButton[][] easyPuzzle() {
        JButton[][] board = generateBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int random = (int) (Math.random() * 15) + 1; //1-15
                if (random == 1 || random == 2 || random == 3 || random == 4 || random == 5) {
                    board[i][j].setLabel("");
                }
            }
        }
        return board;
    }


    public static JButton[][] mediumPuzzle() {
        JButton[][] board = generateBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int random = (int) (Math.random() * 12) + 1; //1-12
                if (random == 1 || random == 2 || random == 3 || random == 4 || random == 5) {
                    board[i][j].setLabel("");
                }
            }
        }
        return board;
    }


    public static JButton[][] veryHardPuzzle() {
        JButton[][] board = generateBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int random = (int) (Math.random() * 8) + 1; //1-8
                if (random == 1 || random == 2 || random == 3 || random == 4 || random == 5) {
                    board[i][j].setLabel("");
                }
            }
        }
        return board;
    }


    public static JButton[][] hardPuzzle() {
        JButton[][] board = generateBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int random = (int) (Math.random() * 10) + 1; //1-10
                if (random == 1 || random == 2 || random == 3 || random == 4 || random == 5) {
                    board[i][j].setLabel("");
                }
            }
        }
        return board;
    }


    public static JButton[][] horAndVertSquares(JButton[][] list, JButton button) {
        int x = -1;
        int y = -1;
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (list[i][j] == button) {
                    x = j;
                    y = i;
                }
            }
        }

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (j == x || i == y) {
                    list[i][j].setBackground(highlightColor);
                } else {
                    list[i][j].setBackground(gridColor);
                }

            }
        }
        return list;

    }


    public static JButton[][] numLocater(JButton[][] list, JButton button) {
        int x = -1;
        int y = -1;
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (list[i][j] == button) {
                    x = j;
                    y = i;
                }
            }
        }

        String string = list[y][x].getText();

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (list[i][j].getText().equals(string)) {
                    list[i][j].setBackground(highlightColor);
                } else {
                    list[i][j].setBackground(gridColor);
                }
            }
        }

        return list;

    }

    public void createGrid() {


        JPanel p1 = new JPanel(new GridLayout(1, 3));

        JPanel p2 = new JPanel(new GridLayout(12, 1));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JButton button = new JButton();

                grid[i][j] = button;
                grid[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver == false) {
                            JButton button = (JButton) e.getSource();

                            if (button.getText().equals("")) {
                                clickedButtons.add(button);
                            }
                            if (clickedButtons.size() > 1 && button.getText().equals("")) {
                                button.setBackground(highlightColor);

                                clickedButtons.get(clickedButtons.size() - 2).setBackground(gridColor);

                                horAndVertSquares(grid, button);

                            } else if (clickedButtons.size() <= 1 && button.getText().equals("")) {
                                button.setBackground(highlightColor);

                                horAndVertSquares(grid, button);
                            }

                            if (!button.getText().equals("")) {
                                numLocater(grid, button);
                            }
                        }
                    }

                });

                p2.add(button);

            }
        }

        Border b = (BorderFactory.createMatteBorder(1, 1, 1, 1, borderColor));
        int i1;
        for (int i = 0; i < 9; i++) {
            if (i == 2 || i == 5) {
                i1 = 5;
            } else {
                i1 = 1;
            }
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) {
                    b = BorderFactory.createMatteBorder(1, 1, i1, 5, borderColor);
                    grid[i][j].setBorder(b);
                } else {
                    b = (BorderFactory.createMatteBorder(1, 1, i1, 1, borderColor));
                    grid[i][j].setBorder(b);
                }
            }
        }
        int index = 0;
        int x = 0;
        for (int i = 0; i < 27; i++) {
            if (i < 18) {
                JPanel p = new JPanel();

                switch (amountOfAttempts) {
                    case 3:
                        if (i == 12 || i == 13 || i == 14) {
                            p.setBackground(backgroundColor);
                            switch (i) {
                                case 12:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 0, borderColor));
                                    JLabel label = new JLabel();
                                    p.add(label);
                                    strikeArea.add(label);
                                    break;
                                case 13:

                                    JLabel la = new JLabel();
                                    strikeArea.add(la);
                                    p.add(la);
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    break;
                                case 14:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 2, borderColor));
                                    JLabel l = new JLabel();
                                    strikeArea.add(l);
                                    p.add(l);
                                    break;
                                default:
                                    break;
                            }

                            index++;
                        } else {
                            p.setBackground(backgroundColor);
                            backgroundPanels.add(p);
                        }
                        break;

                    case 5:
                        if (i == 11 || i == 12 || i == 13 || i == 14 || i == 15) {
                            p.setBackground(backgroundColor);
                            switch (i) {
                                case 11:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 0, borderColor));
                                    JLabel label1 = new JLabel();
                                    p.add(label1);
                                    strikeArea.add(label1);
                                    break;
                                case 12:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label = new JLabel();
                                    p.add(label);
                                    strikeArea.add(label);
                                    break;
                                case 13:
                                    //strikes = new JLabel();
                                    JLabel la = new JLabel();
                                    strikeArea.add(la);
                                    p.add(la);
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    break;
                                case 14:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l = new JLabel();
                                    strikeArea.add(l);
                                    p.add(l);
                                    break;
                                case 15:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 2, borderColor));
                                    JLabel l1 = new JLabel();
                                    strikeArea.add(l1);
                                    p.add(l1);
                                    break;
                                default:
                                    break;
                            }

                            index++;
                        } else {
                            p.setBackground(backgroundColor);
                            backgroundPanels.add(p);
                        }
                        break;
                    case 7:
                        if (i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16) {
                            p.setBackground(backgroundColor);
                            switch (i) {
                                case 10:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 0, borderColor));
                                    JLabel label2 = new JLabel();
                                    p.add(label2);
                                    strikeArea.add(label2);
                                    break;
                                case 11:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label1 = new JLabel();
                                    p.add(label1);
                                    strikeArea.add(label1);
                                    break;
                                case 12:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label = new JLabel();
                                    p.add(label);
                                    strikeArea.add(label);
                                    break;
                                case 13:

                                    JLabel la = new JLabel();
                                    strikeArea.add(la);
                                    p.add(la);
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    break;
                                case 14:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l = new JLabel();
                                    strikeArea.add(l);
                                    p.add(l);
                                    break;
                                case 15:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l1 = new JLabel();
                                    strikeArea.add(l1);
                                    p.add(l1);
                                    break;
                                case 16:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 2, borderColor));
                                    JLabel label4 = new JLabel();
                                    p.add(label4);
                                    strikeArea.add(label4);
                                    break;
                                default:
                                    break;
                            }

                            index++;
                        } else {
                            p.setBackground(backgroundColor);
                            backgroundPanels.add(p);
                        }
                        break;
                    case 9:
                        if (i == 9 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17) {
                            p.setBackground(backgroundColor);
                            switch (i) {
                                case 9:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 0, borderColor));
                                    JLabel label3 = new JLabel();
                                    p.add(label3);
                                    strikeArea.add(label3);
                                    break;
                                case 10:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label2 = new JLabel();
                                    p.add(label2);
                                    strikeArea.add(label2);
                                    break;
                                case 11:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label1 = new JLabel();
                                    p.add(label1);
                                    strikeArea.add(label1);
                                    break;
                                case 12:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label = new JLabel();
                                    p.add(label);
                                    strikeArea.add(label);
                                    break;
                                case 13:
                                    //strikes = new JLabel();
                                    JLabel la = new JLabel();
                                    strikeArea.add(la);
                                    p.add(la);
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    break;
                                case 14:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l = new JLabel();
                                    strikeArea.add(l);
                                    p.add(l);
                                    break;
                                case 15:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l1 = new JLabel();
                                    strikeArea.add(l1);
                                    p.add(l1);
                                    break;
                                case 16:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label4 = new JLabel();
                                    p.add(label4);
                                    strikeArea.add(label4);
                                    break;
                                case 17:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 2, borderColor));
                                    JLabel label5 = new JLabel();
                                    p.add(label5);
                                    strikeArea.add(label5);
                                    break;
                                default:
                                    break;
                            }

                            index++;
                        } else {
                            p.setBackground(backgroundColor);
                            backgroundPanels.add(p);
                        }
                        break;
                    default:
                        if (i == 9 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17) {
                            p.setBackground(backgroundColor);
                            switch (i) {
                                case 9:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 0, borderColor));
                                    JLabel label3 = new JLabel();
                                    p.add(label3);
                                    label3.setText("U");
                                    strikeArea.add(label3);
                                    break;
                                case 10:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label2 = new JLabel();
                                    p.add(label2);
                                    label2.setText("N");
                                    strikeArea.add(label2);
                                    break;
                                case 11:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label1 = new JLabel();
                                    p.add(label1);
                                    label1.setText("L");
                                    strikeArea.add(label1);
                                    break;
                                case 12:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label = new JLabel();
                                    p.add(label);
                                    label.setText("I");
                                    strikeArea.add(label);
                                    break;
                                case 13:
                                    //strikes = new JLabel();
                                    JLabel la = new JLabel();
                                    strikeArea.add(la);
                                    p.add(la);
                                    la.setText("M");
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    break;
                                case 14:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l = new JLabel();
                                    strikeArea.add(l);
                                    l.setText("I");
                                    p.add(l);
                                    break;
                                case 15:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel l1 = new JLabel();
                                    strikeArea.add(l1);
                                    l1.setText("T");
                                    p.add(l1);
                                    break;
                                case 16:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, borderColor));
                                    JLabel label4 = new JLabel();
                                    p.add(label4);
                                    label4.setText("E");
                                    strikeArea.add(label4);
                                    break;
                                case 17:
                                    p.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 2, borderColor));
                                    JLabel label5 = new JLabel();
                                    p.add(label5);
                                    label5.setText("D");
                                    strikeArea.add(label5);
                                    break;
                                default:
                                    break;
                            }

                            index++;
                        } else {
                            p.setBackground(backgroundColor);
                            backgroundPanels.add(p);
                        }
                        break;
                }

                p2.add(p);
            } else {
                JButton button = new JButton();
                button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, borderColor));
                int spot = x + 1;
                button.setText("" + spot);
                button.setBackground(gridColor);
                p2.add(button);
                options[x] = button;

                options[x].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver == false) {
                            JButton button = (JButton) e.getSource();

                            //set pink button to the number selected
                            if (clickedButtons.size() > 0) {
                                clickedButtons.get(clickedButtons.size() - 1).setText(button.getText());
                            }

                            int x = -1;
                            int y = -1;
                            //see if the number is in the correct spot
                            for (int i = 0; i < grid.length; i++) {
                                for (int j = 0; j < grid.length; j++) {
                                    String string = grid[i][j].getText();
                                    int value = -1;
                                    if (string.equals("")) {
                                        value = 0;
                                    } else {
                                        value = Integer.parseInt(string);
                                    }
                                    if (value != solved[i][j] && value != 0) {//it is in wrong spot
                                        clickedButtons.get(clickedButtons.size() - 1).setBackground(Color.decode("#d50000"));
                                        clickedButtons.get(clickedButtons.size() - 1).setText("");
                                        if (amountOfAttempts != -1) {
                                            strikeArea.get(strikeAmount).setText("X");
                                            strikeAmount++;
                                        }

                                        if (strikeAmount == amountOfAttempts) {

                                            grid = losingGrid(grid);

                                            gameOver = true;
                                            switch (amountOfAttempts) {
                                                case 3:
                                                    strikeArea.get(0).setText("R");
                                                    strikeArea.get(1).setText("I");
                                                    strikeArea.get(2).setText("P");
                                                    int response = JOptionPane.showConfirmDialog(null,"Uhh! Sorry.  You've to Learn from you mistakes.\n Do you want to see the Solution?","Solution",JOptionPane.YES_NO_OPTION);
                                                    if(response==JOptionPane.YES_OPTION){
                                                        grid = solutionGrid(grid);
                                                        return;
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                            strikeAmount = 0;
                                        }
                                    }
                                }
                            }




                            //check if the user won
                            boolean win = checkWin(grid);
                            if (win == true) {
                                grid = winningGrid(grid);
                                strikeArea.get(0).setText("WI");
                                strikeArea.get(1).setText("NN");
                                strikeArea.get(2).setText("ER");
                                strikeAmount = 0;
                                gameOver = true;
                                JOptionPane.showMessageDialog(null,"Congratulations! You just hack a Sudoku!","Winner",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }

                });
                x++;

            }
        }

        p1.add(p2);

        this.add(p1);

    }

    public static boolean checkWin(JButton[][] list) {

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (list[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static JButton[][] solutionGrid(JButton[][] list) {

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                list[i][j].setBackground(Color.decode("#d50000"));
                list[i][j].setText(String.valueOf(solved[i][j]));
            }
        }
        return list;
    }



    public static JButton[][] winningGrid(JButton[][] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                list[i][j].setText("");
                if ((i == 2 && j == 2)
                        || (i == 2 && j == 6)
                        || (i == 5 && j == 2)
                        || (i == 6 && j == 3)
                        || (i == 6 && j == 4)
                        || (i == 6 && j == 5)
                        || (i == 5 && j == 6)) {
                    list[i][j].setBackground(Color.black);
                } else {
                    list[i][j].setBackground(Color.GREEN);
                }
            }
        }
        return list;
    }


    public static JButton[][] losingGrid(JButton[][] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                list[i][j].setText("");
                if ((i == 2 && j == 2)
                        || (i == 2 && j == 6)
                        || (i == 6 && j == 2)
                        || (i == 5 && j == 3)
                        || (i == 5 && j == 4)
                        || (i == 5 && j == 5)
                        || (i == 6 && j == 6)
                        || (i == 6 && j == 6)) {
                    list[i][j].setBackground(Color.black);
                } else {
                    list[i][j].setBackground(Color.decode("#d50000"));
                }
            }
        }
        return list;
    }




    public static JButton[][] generateBoard() {
        int[][] board = new int[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }

        int j = 0;
        //insert a random number (1-9) in random spots then use the solve method
        for (int i = 0; i < board.length; i++) {
            int random = (int) (Math.random() * 9) + 1;
            board[i][j] = random;

            if (i > 0 && i % 3 != 0) {
                //check the previous spot for similarities and correct it if there is
                while (board[i - 1][j - 1] == board[i][j]) {
                    board[i][j] = (int) (Math.random() * 9) + 1;
                }
            }

            if (i == 2) {
                while (board[i][j] == board[0][0] || board[i][j] == board[i - 1][j - 1]) {
                    board[i][j] = (int) (Math.random() * 9) + 1;
                }

            }
            if (i == 5) {
                while (board[i][j] == board[3][3] || board[i][j] == board[i - 1][j - 1]) {
                    board[i][j] = (int) (Math.random() * 9) + 1;
                }
            }
            if (i == 8) {
                while (board[i][j] == board[6][6] || board[i][j] == board[i - 1][j - 1]) {
                    board[i][j] = (int) (Math.random() * 9) + 1;
                }
            }
            j++;
        }

        solve(board);

        for (int i = 0; i < grid.length; i++) {
            for (int k = 0; k < grid.length; k++) {
                String string = Integer.toString(board[i][k]);
                grid[i][k].setLabel(string);
                grid[i][k].setBackground(gridColor); //try this
                String s = grid[i][k].getText();
                int value = -1;
                if (s.equals("")) {
                    value = 0;
                } else {
                    value = Integer.parseInt(s);
                }
                solved[i][k] = value;
            }
        }

        return grid;
    }


    public static boolean solve(int[][] board) {
        int[] find = findEmpty(board);
        int col;
        int row;
        if (find == null) {
            return true;
        } else {
            col = find[1];
            row = find[0];
        }

        int temp[] = {row, col};

        for (int i = 1; i < 10; i++) {
            if (isValid(board, i, temp)) {
                board[row][col] = i;
                if (solve(board)) {
                    return true;
                }
                board[row][col] = 0;
            }

        }
        return false;
    }




    public static boolean isValid(int[][] board, int num, int pos[]) {
        //check row
        for (int i = 0; i < board.length; i++) {
            if (board[pos[0]][i] == num && pos[1] != i) {
                return false;
            }
        }
        //check columns
        for (int i = 0; i < board.length; i++) {
            if (board[i][pos[1]] == num && pos[0] != i) {
                return false;
            }
        }

        //check box
        int boxX = pos[1] / 3;
        int boxY = pos[0] / 3;

        for (int i = boxY * 3; i < boxY * 3 + 3; i++) {
            for (int j = boxX * 3; j < boxX * 3 + 3; j++) {
                int[] temp = {i, j};
                if (board[i][j] == num && temp != pos) {
                    return false;
                }
            }
        }

        return true;

    }



    public static int[] findEmpty(int[][] board) {
        int[] coordinate = new int[2]; //index 0 is y, index 1 is x
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    coordinate[0] = i; //j is y coordinate
                    coordinate[1] = j; //i is x coordinate
                    return coordinate;
                }
            }
        }
        return null;
    }





    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
