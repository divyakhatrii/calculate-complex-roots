/*3/25/20 Divya Khatri
This class displays the comlex roots of the complex numbers that were inputted previously by the user
 */
package khatristeamdb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.JTableHeader;

public class Output extends JFrame implements ActionListener
{

    //declare variables
    private final String[] COLUMN_HEADER =
    {
        "Input #", "Rectangular Form", "Polar Form"
    };
    private Object[][] data;
    private JTable rootsTable;
    private JScrollPane paneFul;
    private JTableHeader header;
    private JButton returnButton;
    private JPanel buttonPanel;
    private ArrayList<ArrayList<String>> dataList;

    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private String[] tableHeaders;
    
    public Output(String dbName, String tableName, String[] tableHeaders)
    {
        //construct frame
        super("Output Roots");
        this.setBounds(100, 100, 1250, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Welcome.DARK_BLUE);
        this.setLayout(new BorderLayout());
        
        this.tableHeaders = tableHeaders;
        
        //retrieve data and convert into 2d array
        JavaDb objDb = new JavaDb(dbName);
        dataList = objDb.getData(tableName, tableHeaders);
        if (dataList.size() == 0)
        {
            data = new Object[0][0];
        }
        else
        {
            data = objDb.to2dArray(dataList);
        }
        objDb.closeDbConn();//close the connection so other frames do not access
        
        //construct table
        rootsTable = new JTable(data, COLUMN_HEADER);
        rootsTable.setFont(Welcome.SMALL_FONT);
        rootsTable.setBackground(Welcome.DARK_BLUE);
        rootsTable.setGridColor(Welcome.TURQUOISE);
        rootsTable.setForeground(Welcome.TINTED_WHITE);
        rootsTable.setRowHeight(35);

        //format header
        header = rootsTable.getTableHeader();
        header.setFont(Welcome.MEDIUM_FONT);
        header.setBackground(Color.YELLOW);
        header.setForeground(Color.ORANGE);

        //construct scroll pane
        paneFul = new JScrollPane();
        paneFul.getViewport().add(rootsTable);
        rootsTable.setFillsViewportHeight(true);

        //create all buttons button
        this.returnButton = new JButton("Return");
        this.returnButton.addActionListener(this);
        this.returnButton.setFont(Welcome.SMOLL_FONT);
        this.insertButton = new JButton("Insert");
        this.insertButton.addActionListener(this);
        this.insertButton.setFont(Welcome.SMALL_FONT);
        this.deleteButton = new JButton("Delete");
        this.deleteButton.addActionListener(this);
        this.deleteButton.setFont(Welcome.SMOLL_FONT);
        this.updateButton = new JButton("Update");
        this.updateButton.addActionListener(this);
        this.updateButton.setFont(Welcome.SMOLL_FONT);
        
        //addButtons to panel
        this.buttonPanel = new JPanel(new FlowLayout());
        this.buttonPanel.add(insertButton);
        this.buttonPanel.add(updateButton);
        this.buttonPanel.add(deleteButton);
        this.buttonPanel.add(returnButton);
        this.buttonPanel.setBackground(Welcome.TINTED_WHITE);

        //add to frame
        this.add(paneFul, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if (command.equals("Return"))
        {
            this.dispose();
        }
         if (command.equals("Insert"))
        {
            new Insert(this);
        }
        if (command.equals("Delete"))
        {
            new Delete(this, tableHeaders);
        }
        if (command.equals("Update"))
        {
            new Update(this, tableHeaders);
        }
        this.validate();
        this.repaint();
    }

    //main method
    public static void main(String[] args)
    {
        int power = 3;
        double radius = 1;
        double theta = 180;
        boolean angleRadian = false;
        String dbName = "imaginary";
        String tableName = "RectangularForm";
        String[] tableHeaders =
        {
            "InputNo", "RectForm", "PolarForm"
        };

        ComplexRootsCalc polarObj = new ComplexRootsCalc(radius, theta, power, false);
        Object[] cosSolutions = polarObj.getCosSolutions();
        Object[] sinSolutions = polarObj.getSinSolutions();
        double[] angles = polarObj.getAngles();
        double radiusToPower = polarObj.getRadiusToPower();
        Output outputObj = new Output(dbName, tableName, tableHeaders);
    }

}
