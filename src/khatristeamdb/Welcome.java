/*2/20/20 Divya Khatri
This program finds the roots of complex numbers. The Welcome frame gives the user 
 */
package khatristeamdb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Welcome extends JFrame implements ActionListener
{

    //declare fonts
    public static final Font GREAT_FONT = new Font("Stencil", Font.BOLD, 50);
    public static final Font SMALL_FONT = new Font("Futura", Font.PLAIN, 18);
    public static final Font MEDIUM_FONT = new Font("Roboto", Font.BOLD, 40);
    public static final Font SMOLL_FONT = new Font("Times New Roman", Font.BOLD, 18);

    //declare custom colors
    public static final Color BRIGHT_PINK = new Color(235, 92, 118);
    public static final Color TINTED_WHITE = new Color(243, 235, 255);
    public static final Color ORANGE = new Color(255, 147, 29);
    public static final Color TURQUOISE = new Color(38, 219, 212);
    public static final Color DARK_BLUE = new Color(11, 56, 101);
    public static final Color REDDISH = new Color(159, 29, 47);

    //declare image
    private final java.net.URL IMAGE_PATH = getClass().getResource("welcome.png");
    private final ImageIcon WELCOME_IMAGE = new ImageIcon(new ImageIcon(
            IMAGE_PATH).getImage().getScaledInstance(700, 400, Image.SCALE_DEFAULT));

    //declare variables
    private JLabel topicLabel;
    private JLabel nameLabel;
    private JLabel imageLabel;
    private JPanel welcomePanel;
    private JButton startButton;
    private JPanel startPanel;
    private JMenuBar helpBar;
    private JMenuItem helpItem;

    public Welcome()
    {
        //constructing a frame
        super("Welcome!");
        this.setBounds(325, 150, 800, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(DARK_BLUE);
        this.setLayout(new BorderLayout());

        //create labels
        this.topicLabel = new JLabel("Solving for Complex Roots", SwingConstants.CENTER);
        this.topicLabel.setFont(GREAT_FONT);
        this.nameLabel = new JLabel("By Divya Khatri", SwingConstants.CENTER);
        this.nameLabel.setFont(SMALL_FONT);
        this.imageLabel = new JLabel(WELCOME_IMAGE);

        //create button
        this.startButton = new JButton("Start!");
        this.startButton.addActionListener(this);
        this.startButton.setFont(SMALL_FONT);

        //create help menu
        this.helpBar = new JMenuBar();
        this.helpBar.setBackground(TINTED_WHITE);
        this.helpItem = new JMenuItem("Confused? Click Here");
        this.helpItem.setFont(SMALL_FONT);
        this.helpItem.addActionListener(this);
        this.helpBar.add(helpItem);
        this.setJMenuBar(helpBar);

        //create panels
        this.welcomePanel = new JPanel(new BorderLayout());
        this.welcomePanel.add(topicLabel, BorderLayout.NORTH);
        this.welcomePanel.add(nameLabel, BorderLayout.SOUTH);
        this.welcomePanel.setBackground(BRIGHT_PINK);
        this.startPanel = new JPanel(new FlowLayout());
        this.startPanel.add(startButton);
        this.startPanel.setBackground(BRIGHT_PINK);

        this.add(startPanel, BorderLayout.SOUTH);
        this.add(imageLabel, BorderLayout.CENTER);
        this.add(welcomePanel, BorderLayout.NORTH);

        this.setVisible(true);

    }

    //main method 
    public static void main(String[] args)
    {
        Welcome welcomeObj = new Welcome();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        //when the user clicks start, it closes this frame and opens the input frame
        if (command.equals("Start!"))
        {
            new Input();
            this.dispose();
        }
        //when user clicks this, it takes them to the help menu
        if (command.equals("Confused? Click Here"))
        {
            new Help();
        }
    }

}
