/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khatristeamdb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author divyak
 */
public class Insert extends JFrame implements ActionListener
{

    private JLabel numberLabel;
    private JTextField numberField;
    private JLabel rectangularLabel;
    private JTextField rectangularField;
    private JLabel polarLabel;
    private JTextField polarField;
    private JButton insertButton;
    private JButton doneButton;
    private JPanel dataPanel;
    private JPanel donePanel;

    Output gFrame; //global frame of type class ButtonsWdisplay, it is global, it is an attribute
    //objects are ADRESSES pointing to a copy of the class with certain values, attributes and properties

    public Insert(Output pFrame) //object as parameter, parameters a local, cannot use in other methodsl but we can use global frame to do that
    {
        //constructing the frame
        super("Insert");
        this.setBounds(250, 350, 850, 200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.YELLOW);
        this.setLayout(new BorderLayout());

        this.gFrame = pFrame;

        //construct text fields
        this.numberLabel = new JLabel("InputNumber:");
        this.numberLabel.setFont(Welcome.SMALL_FONT);
        this.numberField = new JTextField(15);
        this.rectangularLabel = new JLabel("  Rectangular Form:");
        this.rectangularLabel.setFont(Welcome.SMALL_FONT);
        this.rectangularField = new JTextField(15);
        this.polarLabel = new JLabel("Polar Form:");
        this.polarLabel.setFont(Welcome.SMALL_FONT);
        this.polarField = new JTextField(15);

        //construct control buttons
        this.insertButton = new JButton("Insert");
        insertButton.addActionListener(this);
        this.doneButton = new JButton("Done");
        doneButton.addActionListener(this);

        //construct panels add components
        this.dataPanel = new JPanel(new FlowLayout());
        dataPanel.setBackground(Welcome.DARK_BLUE);
        dataPanel.add(numberLabel);
        dataPanel.add(numberField);
        dataPanel.add(rectangularLabel);
        dataPanel.add(rectangularField);
        dataPanel.add(polarLabel);
        dataPanel.add(polarField);
        dataPanel.add(insertButton);

        this.donePanel = new JPanel();
        donePanel.setBackground(Welcome.BRIGHT_PINK);
        donePanel.add(doneButton);

        //add components to frame
        this.add(dataPanel, BorderLayout.CENTER);
        this.add(donePanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        String dbName = "imaginary";
        String tableName = "RectangularForm";
        String[] tableHeaders =
        {
            "InputNo", "RectForm", "PolarForm"
        };
        
        String dbQuery = "INSERT INTO RectangularForm VALUES (?,?,?)";

        //connect to db
        JavaDb objDb = new JavaDb(dbName);
        Connection myDbConn = objDb.getDbConn();//we made the Dbconn when making the object, so now we access it, get connection

        //data to be enteres
        int inputNumber;
        String rectForm;
        String polarForm;

        if (command.equals("Insert"))
        {
            inputNumber = Integer.parseInt(numberField.getText());
            rectForm = rectangularField.getText();
            polarForm = polarField.getText();
            numberField.setText("");
            rectangularField.setText("");
            polarField.setText("");
            try //inserting the data
            {
                //prepare statement
                PreparedStatement ps = myDbConn.prepareStatement(dbQuery); //blank check
                //enter data into query
                ps.setInt(1, inputNumber); //first  question mark replaced by that variable
                ps.setString(2, rectForm);
                ps.setString(3, polarForm);

                //execute the query
                ps.executeUpdate();
                //System.out.println("Data inserted successfully");

                //dispose old frame and bring new one
                gFrame.dispose();
                gFrame = new Output(dbName, tableName, tableHeaders);
                //to make sure we see it, we bring it to the front
                this.toFront();
            }
            catch (SQLException se)
            {
                System.out.println("Error inserting data");
            }
        }

        else if (command.equals("Done"))
        {
            objDb.closeDbConn();//close connection
            this.dispose();
        }
    }

    public static void main(String[] args)
    {
        String dbName = "imaginary";
        String tableName = "RectangularForm";
        String[] tableHeaders =
        {
            "InputNo", "RectForm", "PolarForm"
        };
        Output outputObj = new Output(dbName, tableName, tableHeaders);
        new Insert(outputObj);
    }
}
