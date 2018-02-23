package shopping.cart;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Project File Name: ShoppingCart.java
 * Project Date: 12/10/17
 * @author Damian Ayres
 */
//extend JFrame for GUI class
public class ShoppingCart extends JFrame 
{
    //constants for JFrame width and height
    private final int WIDTH     = 700;
    private final int HEIGHT    = 320;
    
    //initialize local variables
    private String  nextLine;
    private double     price;
    private File    sourceFile  = new File("BookPrices.txt");       
    private double     subtotal;
    private double     taxes;
    private double     total;

    
    //GUI components
    private JButton[] buttonArray =
        {   new JButton ("Add"),
            new JButton ("Remove"),
            new JButton ("Clear"),
            new JButton ("Checkout")
        };
    private JPanel buttonPanel  = new JPanel();
    private JPanel checkout     = new JPanel();
    private JPanel products     = new JPanel();
    private JLabel  forSale     = new JLabel("Select Books:");
    private JLabel  boughtItems = new JLabel("Shopping Cart:");
    private JScrollPane rightSP = new JScrollPane();
    private JScrollPane leftSP  = new JScrollPane();    
    
    //actionListener
    private ActionListener buttonListener = new ButtonListener();
    
    //build the lists
    private DefaultListModel selectionList = new DefaultListModel();
    private DefaultListModel basketList    = new DefaultListModel();
    private JList   selection   = new JList(selectionList);
    private JList   basket      = new JList(basketList);
    
    /*
        CONSTRUCTOR
        Throws IOException added for the scanner to read sourceFile.
    */
    public ShoppingCart() throws IOException
    {
        //build the GUI
        scanBooks();
        buildPanel();        

    }
    
    //scans the text file for the books we need
    public void scanBooks() throws IOException
    {
        //scan file for books and add to the list model
        Scanner inputFile   = new Scanner(sourceFile);
        while (inputFile.hasNextLine()){
            nextLine = inputFile.nextLine();
            selectionList.addElement(nextLine);
        }
    }    
    
    //assemble the GUI elements
    public void buildPanel()
    {
        //initialize the window
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setResizable(false);        
        setLayout(new FlowLayout());
        
        //build the left menu (selections)
        selection.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        leftSP = new JScrollPane(selection);
        //build the right menu (the checkout basket)
        basket.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        rightSP = new JScrollPane(basket);
        
        //add lists to the scrollpanes
        checkout.add(rightSP);
        products.add(leftSP);
        
        //add action listeners to the buttons and add the buttons to the panel
        for (int i = 0; i < buttonArray.length; i++){
            buttonArray[i].addActionListener(buttonListener);
            buttonPanel.add(buttonArray[i]);
        }
        
        //add the components to the window and set to visible
        add(forSale);
        add(selection);
        add(boughtItems);
        add(checkout);
        add(buttonPanel);
        setVisible(true);
    }    
    
    //Action Listener
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                //clear button
                if (e.getActionCommand().equals("Clear")){
                    subtotal = 0;
                    basketList.clear();

                }
                //add button
                else if (e.getActionCommand().equals("Add")){
                    basketList.addElement(selection.getSelectedValue());
                    nextLine = selection.getSelectedValue().toString();
                    price = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
                    subtotal += price * .01;
                }
                //remove button
                else if (e.getActionCommand().equals("Remove")){
                    nextLine = basket.getSelectedValue().toString();
                    price = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
                    subtotal -= price * .01;
                    basketList.removeElement(basket.getSelectedValue());
                  
                }
                //checkout button
                else if (e.getActionCommand().equals("Checkout")){
                    taxes = subtotal * 0.06;
                    total = subtotal + taxes;
                    JOptionPane.showMessageDialog(null, "Subtotal: $" + subtotal +
                            "\nTaxes: $" + taxes + "\nTotal: $" + total);
                }
            }
            //triggers when pressing add or remove with nothing selected
            catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Nothing selected");
            }
        }
    }
    
}
