/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khatristeamdb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author divyak
 */
public class Warning extends JFrame implements ActionListener
{
    //declare image
    private final java.net.URL IMAGE_PATH = getClass().getResource("Warning.png");
    private final ImageIcon WARNING_IMAGE = new ImageIcon(new 
        ImageIcon(IMAGE_PATH).getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT));
    
    //decalre variabels
    private JButton homeButton;
    private JButton returnButton;
    private JLabel warningLabel;
    private JLabel imageLabel;
    private JPanel buttonPanel;
    private JPanel warningPanel;

    //constructor
    public Warning(String message)
    {
        //construct frame
        super("Warning!");
        this.setBounds(350, 200, 800, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        //construct label
        this.warningLabel = new JLabel("Warning: " + message, SwingConstants.CENTER);
        this.warningLabel.setFont(Welcome.GREAT_FONT);
        this.imageLabel = new JLabel(WARNING_IMAGE);

        //construct buttons
        this.homeButton = new JButton("Home");
        this.homeButton.addActionListener(this);
        this.homeButton.setFont(Welcome.SMALL_FONT);
        this.returnButton = new JButton("Return");
        this.returnButton.addActionListener(this);
        this.returnButton.setFont(Welcome.SMALL_FONT);

        //construct Panel
        this.buttonPanel = new JPanel(new FlowLayout());
        this.buttonPanel.add(homeButton);
        this.buttonPanel.add(returnButton);
        this.buttonPanel.setBackground(Welcome.REDDISH);
        this.warningPanel = new JPanel(new FlowLayout());
        this.warningPanel.setBackground(Welcome.REDDISH);
        this.warningPanel.add(warningLabel);

        //add components to frame
        this.add(warningPanel, BorderLayout.NORTH);
        this.add(imageLabel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        
        if(command.equals("Home"))
        {
            this.dispose();
            new Welcome();
        }
        
        if(command.equals("Return"))
        {
            this.dispose();
        }
    }

    public static void main(String[] args)
    {
        new Warning("IMPORTANT!");
    }

}
