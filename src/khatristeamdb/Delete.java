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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author divyak
 */
public class Delete extends JFrame implements ActionListener
{

    //declaring variables
    private JButton deleteButton;
    private JComboBox<String> whereBox;
    private JTextField conditionField;
    private JLabel deleteLabel;
    private JLabel isLabel;
    private JPanel deletePanel;
    private JButton doneButton;
    private JPanel donePanel;

    private String[] tableHeaders;
    Output gFrame; //global frame of type class ButtonsWdisplay, it is global, it is an attribute
    //objects are ADRESSES pointing to a copy of the class with certain values, attributes and properties

    public Delete(Output pFrame, String[] tableHeaders) //object as parameter, parameters a local, cannot use in other methodsl but we can use global frame to do that
    {
        //constructing the frame
        super("Delete");
        this.setBounds(250, 350, 950, 200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.ORANGE);
        this.setLayout(new BorderLayout());

        this.tableHeaders = tableHeaders;
        this.gFrame = pFrame;

        //construct labels
        this.deleteLabel = new JLabel("Delete row where ");
        this.deleteLabel.setFont(Welcome.SMALL_FONT);
        this.isLabel = new JLabel(" is ");
        this.isLabel.setFont(Welcome.SMALL_FONT);

        //construct textField
        this.conditionField = new JTextField(20);

        //construct Buttons
        this.deleteButton = new JButton("Delete");
        this.deleteButton.addActionListener(this);
        this.deleteButton.setFont(Welcome.SMALL_FONT);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        this.doneButton.setFont(Welcome.SMALL_FONT);

        //construct comboBox
        this.whereBox = new JComboBox<>(tableHeaders);
        this.whereBox.setFont(Welcome.SMALL_FONT);

        //construct and add stuff to panels
        this.deletePanel = new JPanel(new FlowLayout());
        this.deletePanel.setBackground(Welcome.DARK_BLUE);
        this.deletePanel.add(deleteLabel);
        this.deletePanel.add(whereBox);
        this.deletePanel.add(isLabel);
        this.deletePanel.add(conditionField);
        this.deletePanel.add(deleteButton);
        this.donePanel = new JPanel();
        this.donePanel.setBackground(Welcome.TINTED_WHITE);
        this.donePanel.add(doneButton);

        //add everything to the frame
        this.add(deletePanel, BorderLayout.CENTER);
        this.add(donePanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        String dbName = "Khatrii";//must be same name as db name we are testing 
        String tableName = "RectangularForm";
        String[] columnNames =
        {
            "Radius", "nthRoot", "Angle"
        };
        //connect to db
        JavaDb objDb = new JavaDb(dbName);
        Connection myDbConn = objDb.getDbConn();//we made the Dbconn when making the object, so now we access it, get connection

        //variables to be added instead of ? 
        String whereColumn;
        int condition;

        if (command.equals("Delete"))
        {
            //get the select Column based on the selected value
            whereColumn = tableHeaders[whereBox.getSelectedIndex()];
            condition = Integer.parseInt(conditionField.getText());
            conditionField.setText("");
            String dbQuery = "DELETE FROM " + tableName + " WHERE " + whereColumn + " = ?";

            try //inserting the data
            {
                //prepare statement
                PreparedStatement ps = myDbConn.prepareStatement(dbQuery); //blank check
                //enter data into query
                ps.setInt(1, condition);

                //execute the query
                ps.executeUpdate();
                System.out.println("Data deleted successfully");

                //dispose old frame and bring new one
                gFrame.dispose();
                gFrame = new Output(dbName, tableName, columnNames);
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
            //close connection and dispose frame
            objDb.closeDbConn();
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

        Output mainObj = new Output(dbName, tableName, tableHeaders);
        new Delete(mainObj, tableHeaders);
    }
}
