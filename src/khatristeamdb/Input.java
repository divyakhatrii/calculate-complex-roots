/* 3/25/20 Divya Khatri
The input class will receives complex numbers and the power to send it to the computation class and sedn it to the output
 */
package khatristeamdb;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Input extends JFrame implements ActionListener
{

    public static final Font ITALIC_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25);
    private boolean angleRadian = false;
    private JLabel rectangularLabel;
    private JLabel polarLabel;
    private JButton chooseButton;
    private JButton returnButton;
    private JButton calculateButton;
    private ButtonGroup angleGroup;
    private JRadioButton radianButton;
    private JRadioButton degreeButton;
    private JTextField aField;
    private JTextField bField;
    private JTextField radiusField;
    private JTextField thetaField;
    private JTextField powerRectangularField;
    private JTextField powerPolarField;
    private JLabel angleLabel;
    private JLabel inputLabel;
    private JLabel plusLabel;
    private JLabel iLabel;
    private JLabel polarPlus;
    private JLabel openParenthesis;
    private JLabel closeParenthesis;
    private JLabel cosLabel;
    private JLabel sinLabel;
    private JLabel iPolarLabel;
    private JLabel openParenthesis2;
    private JLabel closeParenthesis2;
    private JLabel equalsLabel;
    private JPanel polarPanel;
    private JPanel rectangularPanel;
    private JPanel buttonPanel;
    private JPanel anglePanel;
    private JMenuBar helpBar;
    private JMenuItem helpItem;
    private Box inputBox;

    private JComboBox<String> formBox;
    private String[] formArray =
    {
        "Rectangular Form", "Polar Form"
    };
    private JPanel formPanel;

    //declare images
    private final java.net.URL MY_PATH = getClass().getResource("PolarFormDrawing.png");
    private final ImageIcon POLAR_IMAGE = new ImageIcon(new ImageIcon(
            MY_PATH).getImage().getScaledInstance(300, 100, Image.SCALE_DEFAULT));
    private final java.net.URL RECTANGULAR_PATH = getClass().getResource("RectangularFormDrawing.png");
    private final ImageIcon RECTANGULAR_IMAGE = new ImageIcon(new ImageIcon(
            RECTANGULAR_PATH).getImage().getScaledInstance(250, 100, Image.SCALE_DEFAULT));

    private JLabel polarImage;
    private JPanel polarImagePanel;
    private JLabel rectangleImage;
    private JPanel rectangleImagePanel;
    private ArrayList<ArrayList<String>> myData; // AM I SUPPOSED TO PUT THIS HERE? WHERE TO DECLARE? PRIVATE OR PUBLIC?
    

    public Input()
    {
        //construct frame
        super("Input Values");
        this.setBounds(100, 300, 1250, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Welcome.DARK_BLUE);
        this.setLayout(new BorderLayout());

        //create Radio buttons
        this.rectangularLabel = new JLabel("Rectangular Form: z^");
        this.rectangularLabel.setFont(Welcome.SMALL_FONT);
        this.polarLabel = new JLabel("Polar/Trigonometric Form: z^");
        this.polarLabel.setFont(Welcome.SMALL_FONT);
        this.angleLabel = new JLabel("Mode of your angle: ");
        this.angleLabel.setFont(Welcome.SMALL_FONT);

        //create Buttons
        this.chooseButton = new JButton("Choose Form");
        this.chooseButton.addActionListener(this);
        this.chooseButton.setFont(Welcome.SMALL_FONT);
        this.returnButton = new JButton("Return");
        this.returnButton.addActionListener(this);
        this.returnButton.setFont(Welcome.SMALL_FONT);
        this.calculateButton = new JButton("Calculate!");
        this.calculateButton.addActionListener(this);
        this.calculateButton.setFont(Welcome.SMALL_FONT);

        //create radio buttons
        this.radianButton = new JRadioButton("Radians");
        this.radianButton.setFont(Welcome.SMALL_FONT);
        this.degreeButton = new JRadioButton("Degrees");
        this.degreeButton.setFont(Welcome.SMALL_FONT);
        //create button group and add buttons to it
        this.angleGroup = new ButtonGroup();
        this.angleGroup.add(radianButton);
        this.angleGroup.add(degreeButton);

        //create help menu
        this.helpBar = new JMenuBar();
        this.helpBar.setForeground(Welcome.BRIGHT_PINK);
        this.helpItem = new JMenuItem("Confused? Click Here");
        this.helpItem.setFont(Welcome.SMALL_FONT);
        this.helpItem.addActionListener(this);
        this.helpBar.add(helpItem);
        this.setJMenuBar(helpBar);

        //create combo box
        this.formBox = new JComboBox<>(formArray);
        this.formBox.setFont(Welcome.SMALL_FONT);

        //create Label
        this.plusLabel = new JLabel(" + ");
        this.plusLabel.setFont(Welcome.SMALL_FONT);
        this.iLabel = new JLabel("i");
        this.iLabel.setFont(ITALIC_FONT);
        this.polarPlus = new JLabel(" + ");
        this.polarPlus.setFont(Welcome.SMALL_FONT);
        this.equalsLabel = new JLabel("=");
        this.equalsLabel.setFont(Welcome.SMALL_FONT);
        this.openParenthesis = new JLabel(" (");
        this.openParenthesis.setFont(Welcome.SMALL_FONT);
        this.closeParenthesis = new JLabel(")");
        this.closeParenthesis.setFont(Welcome.SMALL_FONT);
        this.openParenthesis2 = new JLabel("= (");
        this.openParenthesis2.setFont(Welcome.SMALL_FONT);
        this.closeParenthesis2 = new JLabel(")");
        this.closeParenthesis2.setFont(Welcome.SMALL_FONT);
        this.cosLabel = new JLabel("cos");
        this.cosLabel.setFont(Welcome.SMALL_FONT);
        this.iPolarLabel = new JLabel("i ");
        this.iPolarLabel.setFont(ITALIC_FONT);
        this.sinLabel = new JLabel("sin θ");
        this.sinLabel.setFont(Welcome.SMALL_FONT);
        this.inputLabel = new JLabel("Choose a Form and Input Values Below:", SwingConstants.CENTER);
        this.inputLabel.setFont(Welcome.GREAT_FONT);
        this.inputLabel.setForeground(Welcome.TINTED_WHITE);
        this.rectangleImage = new JLabel(RECTANGULAR_IMAGE);
        this.polarImage = new JLabel(POLAR_IMAGE);

        //create textFields and make sure user cant type in it yet
        this.aField = new JTextField(5);
        this.bField = new JTextField(5);
        this.radiusField = new JTextField(5);
        this.thetaField = new JTextField(5);
        this.powerRectangularField = new JTextField(5);
        this.powerPolarField = new JTextField(5);

        //create Panels
        this.rectangularPanel = new JPanel(new FlowLayout());
        this.rectangularPanel.add(rectangularLabel);
        this.rectangularPanel.add(powerRectangularField);
        this.rectangularPanel.add(openParenthesis2);
        this.rectangularPanel.add(aField);
        this.rectangularPanel.add(plusLabel);
        this.rectangularPanel.add(bField);
        this.rectangularPanel.add(iLabel);
        this.rectangularPanel.add(closeParenthesis2);
        this.rectangularPanel.setBackground(Welcome.ORANGE);

        //construct polar panel
        this.polarPanel = new JPanel(new FlowLayout());
        this.polarPanel.add(polarLabel);
        this.polarPanel.add(powerPolarField);
        this.polarPanel.add(equalsLabel);
        this.polarPanel.add(radiusField);
        this.polarPanel.add(openParenthesis);
        this.polarPanel.add(cosLabel);
        this.polarPanel.add(thetaField);
        this.polarPanel.add(polarPlus);
        this.polarPanel.add(iPolarLabel);
        this.polarPanel.add(sinLabel);
        this.polarPanel.add(closeParenthesis);
        this.polarPanel.setBackground(Welcome.ORANGE);

        //construct form panel
        this.formPanel = new JPanel(new FlowLayout());
        this.formPanel.add(formBox);
        this.formPanel.add(chooseButton);
        this.formPanel.setBackground(Welcome.ORANGE);

        //construct button panel
        this.buttonPanel = new JPanel(new FlowLayout());
        this.buttonPanel.add(returnButton);
        this.buttonPanel.setBackground(Welcome.DARK_BLUE);

        //construct angle panel
        this.anglePanel = new JPanel(new FlowLayout());
        this.anglePanel.add(angleLabel);
        this.anglePanel.add(radianButton);
        this.anglePanel.add(degreeButton);
        this.anglePanel.setBackground(Welcome.ORANGE);

        //construct polar Image panel
        this.polarImagePanel = new JPanel(new FlowLayout());
        this.polarImagePanel.add(polarImage);
        this.polarImagePanel.setBackground(Welcome.ORANGE);

        //construct rectangular image panel
        this.rectangleImagePanel = new JPanel(new FlowLayout());
        this.rectangleImagePanel.add(rectangleImage);
        this.rectangleImagePanel.setBackground(Welcome.ORANGE);

        //create Box
        inputBox = Box.createVerticalBox();
        inputBox.add(formPanel);

        this.add(inputBox, BorderLayout.CENTER);
        this.add(inputLabel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //declare variables
        String command = e.getActionCommand();
        int index = formBox.getSelectedIndex();
        int power = 0;
        int inputNumber;
        double radius = 0;
        double theta = 0;
        double a = 0;
        double b = 0;
        Object[] cosSolutions;
        Object[] sinSolutions;
        double[] angles;
        double radiusToPower;
        boolean inputStatus = false;

        int solutionNo;
        String rectForm;
        String polarForm;
        String dbName = "imaginary";
        String tableName = "RectangularForm";
        String[] tableHeaders =
        {
            "InputNo", "RectForm", "PolarForm"
        };
        String dbQuery = "INSERT INTO " + tableName + " VALUES (?,?,?)";

        //when help menu is clicked, it takes them to help frame
        if (command.equals("Confused? Click Here"))
        {
            new Help();
        }
        //it selects the form from the menu box, and displays the labels accordingly
        if (command.equals("Choose Form"))
        {
            if (index == 0)//Rectangular form
            {
                this.inputBox.add(rectangleImagePanel);
                this.inputBox.add(anglePanel);
                this.inputBox.add(rectangularPanel);
            }
            if (index == 1)//polar form
            {
                this.inputBox.add(polarImagePanel);
                this.inputBox.add(anglePanel);
                this.inputBox.add(polarPanel);
            }
            //allow user to calculate now and not allow them to select the form anymore
            this.buttonPanel.add(calculateButton);
            chooseButton.setVisible(false);
        }
        //when return button is clicked, it opens welcome frame again and gets rid of this one
        if (command.equals("Return"))
        {
            this.dispose();
            new Welcome();
        }
        //sends the values to the Calculation frame, receives the calculation, and sends to Output
        if (command.equals("Calculate!"))
        {
            if (radianButton.isSelected())
            {
                angleRadian = true;
            }
            else if (degreeButton.isSelected())
            {
                angleRadian = false;
            }
            else
            {
                new Warning("Input an Angle Mode!");
            }

            if (index == 0)//read rectangular form textfields and convert to polar form
            {
                try
                {
                    //scan power for an integer
                    power = Integer.parseInt(powerRectangularField.getText());
                    inputStatus = true;
                    a = Double.parseDouble(aField.getText());
                    b = Double.parseDouble(bField.getText());

                    //calculate from the input
                    ComplexRootsCalc rectObj = new ComplexRootsCalc();
                    rectObj.RectangularComplexRoots(a, b, power, angleRadian);
                    cosSolutions = rectObj.getCosSolutions();
                    sinSolutions = rectObj.getSinSolutions();
                    angles = rectObj.getAngles();
                    radiusToPower = rectObj.getRadiusToPower();

                    //connect to db
                    JavaDb objDb = new JavaDb(dbName);
                    //get inputNumber of previous Row
                    myData = objDb.getData(tableName, tableHeaders);
                    int previousRow = myData.size();
                    inputNumber = Integer.parseInt(myData.get(previousRow).get(0));
                    Connection myDbConn = objDb.getDbConn();

                    //power determines the number of solutions per input, so we have power number of databse entries for each input
                    for (int i = 0; i < power; i++)
                    {
                        rectForm = cosSolutions[i] + " + " + sinSolutions[i];
                        polarForm = radiusToPower + "(cos" + angles[i] + " + isin" + angles[i] + ")";

                        try //inserting the data
                        {
                            //prepare statement
                            PreparedStatement ps = myDbConn.prepareStatement(dbQuery); //blank check
                            //enter data into query
                            ps.setInt(1, inputNumber);
                            ps.setString(2, rectForm);
                            ps.setString(3, polarForm);

                            //execute the query
                            ps.executeUpdate();
                            Output outputObj = new Output(dbName, tableName, tableHeaders);
                        }
                        catch (SQLException se)
                        {
                            System.out.println("Error inserting data");
                        }
                    }
                }

                //specific exceptions
                catch (NumberFormatException nfe2)
                {
                    if (inputStatus)
                    {
                        new Warning("Input a Double!!!");
                    }
                    else
                    {
                        new Warning("Input an Integer!!");
                    }
                }
                //general exceptions
                catch (Exception ge2)
                {
                    new Warning("Something's Wrong!!");
                }
            }
        }
        else if (index == 1)//read polar textfields and calculate
        {
            try
            {
                //scan power for an integer
                power = Integer.parseInt(powerPolarField.getText());
                inputStatus = true;
                radius = Double.parseDouble(radiusField.getText());
                theta = Double.parseDouble(thetaField.getText());

                if (radius >= 0)
                {
                    ComplexRootsCalc polarObj = new ComplexRootsCalc(radius, theta, power, angleRadian);
                    cosSolutions = polarObj.getCosSolutions();
                    sinSolutions = polarObj.getSinSolutions();
                    angles = polarObj.getAngles();
                    radiusToPower = polarObj.getRadiusToPower();

                    //connect to db
                    JavaDb objDb2 = new JavaDb(dbName);
                    //get inputNumber of previous Row
                    myData = objDb2.getData(tableName, tableHeaders);
                    int previousRow = myData.size();
                    inputNumber = Integer.parseInt(myData.get(previousRow).get(0));

                    Connection myDbConn = objDb2.getDbConn();

                    //power determines the number of solutions per input, so we have power number of databse entries for each input
                    for (int i = 0; i < power; i++)
                    {
                        rectForm = cosSolutions[i] + " + " + sinSolutions[i];
                        polarForm = radiusToPower + "(cos" + angles[i] + " + isin" + angles[i] + ")";

                        try //inserting the data
                        {
                            //prepare statement
                            PreparedStatement ps = myDbConn.prepareStatement(dbQuery); //blank check
                            //enter data into query
                            ps.setInt(1, inputNumber);
                            ps.setString(2, rectForm);
                            ps.setString(3, polarForm);

                            //execute the query
                            ps.executeUpdate();
                            Output outputObj2 = new Output(dbName, tableName, tableHeaders);
                        }
                        catch (SQLException se)
                        {
                            System.out.println("Error inserting data");
                        }
                    }
                }
                else
                {
                    new Warning("Radius must be positive!");
                }
            }

            //specific messages for exceptions
            catch (NumberFormatException nfe2)
            {
                if (inputStatus)
                {
                    new Warning("Input a Double!!!");
                }
                else
                {
                    new Warning("Input an Integer!!");
                }
            }
            //general exceptions
            catch (Exception ge2)
            {
                new Warning("Something's Wrong!!");
            }

        }
        this.validate();
        this.repaint();
    }

//main method
    public static void main(String[] args)
    {
        new Input();
    }

}
