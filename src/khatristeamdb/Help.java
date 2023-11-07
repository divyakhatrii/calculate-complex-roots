/*3/25/20 Divya Khatri
The Help frame will answer any questions the user may have about using the program
 */
package khatristeamdb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author divyak
 */
public class Help extends JFrame implements ActionListener
{

    //declare image
    private final java.net.URL IMAGE_PATH = getClass().getResource("ComplexDiagram.png");
    private final ImageIcon COMPLEX_IMAGE = new ImageIcon(new ImageIcon(
            IMAGE_PATH).getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT));

    //declare variables
    private JLabel titleLabel;
    private JLabel imageLabel;
    private JLabel introLabel;
    private JLabel explainLabel;
    private JLabel programUseLabel;
    private JButton closeButton;
    private JPanel closePanel;
    private JPanel imagePanel;
    private JPanel introPanel;
    private JPanel explainPanel;
    private JPanel introSectionPanel;
    private JPanel programUsePanel;

    private Box explainBox;

    //constructor
    public Help()
    {
        //construct frame
        super("Help Menu");
        this.setBounds(25, 100, 1300, 800);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.PINK);
        this.setLayout(new BorderLayout());

        //construct labels
        this.titleLabel = new JLabel("Help Page", SwingConstants.CENTER);
        this.titleLabel.setFont(Welcome.GREAT_FONT);
        this.introLabel = new JLabel("So you want to know how to use this program? Well, here are some answers:");
        this.introLabel.setFont(Welcome.SMALL_FONT);
        this.programUseLabel = new JLabel("How to Use the Program");
        this.programUseLabel.setFont(Welcome.MEDIUM_FONT);
        this.explainLabel = new JLabel("<html>When using the program, all you have to do is: <br> 1) Click "
                + "start at the botton of the Welcome page<br> 2) Click on the box and select either rectangular or "
                + "polar form based on what form your complex number is in.<br> 3a) If using rectangular form, select"
                + " either radians or degrees by clicking the button, depending on what mode you want your angles to be in the output. "
                + "<br> 3b) For polar form, you when you click radians or degrees, that should also be the form that you input the angle in,"
                + " or you may have an inaccurate result <br> 4) Finally, just input your values and click the calculate button."
                + "<br> *If you get any warning pop-ups, see what was done wrong, which will be shown to you by the writing at the top of the pop-up <html>");
        this.explainLabel.setFont(Welcome.SMALL_FONT);
        this.imageLabel = new JLabel(COMPLEX_IMAGE);

        //construct button
        this.closeButton = new JButton("Close");
        this.closeButton.addActionListener(this);
        this.closeButton.setFont(Welcome.SMALL_FONT);

        //construct Panels
        this.closePanel = new JPanel(new FlowLayout());
        this.closePanel.add(closeButton);
        this.closePanel.setBackground(Color.BLACK);
        this.imagePanel = new JPanel(new FlowLayout());
        this.imagePanel.add(imageLabel);
        this.imagePanel.setBackground(Color.PINK);
        this.introPanel = new JPanel(new FlowLayout());
        this.introPanel.add(introLabel);
        this.introPanel.setBackground(Color.PINK);
        this.explainPanel = new JPanel(new FlowLayout());
        this.explainPanel.add(explainLabel);
        this.explainPanel.setBackground(Color.PINK);
        this.introSectionPanel = new JPanel(new FlowLayout());
        this.introSectionPanel.setBackground(Color.PINK);
        this.programUsePanel = new JPanel(new FlowLayout());
        this.programUsePanel.add(programUseLabel);
        this.programUsePanel.setBackground(Color.PINK);

//        create box
        this.explainBox = Box.createVerticalBox();
        this.explainBox.add(introSectionPanel);
        this.explainBox.add(introPanel);
        this.explainBox.add(programUsePanel);
        this.explainBox.add(explainPanel);
        this.explainBox.add(imagePanel);

        //add to frame
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(explainBox, BorderLayout.CENTER);
        this.add(closePanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    //main method
    public static void main(String[] args)
    {
        new Help();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        //if close button is clicked, it closes the help menu
        if (command.equals("Close"))
        {
            this.dispose();
        }
    }
}
