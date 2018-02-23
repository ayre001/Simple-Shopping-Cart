package shopping.cart;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Project File Name: Main.java
 * Project Date: 12/10/17
 * @author Damian Ayres
 * 
 * Project Scope: 
 * Create an application that works like a shopping cart system for a book store. 
 * In this book’s source code, available at www.pearsonhighered.com/gaddis, you 
 * will find a file named BookPrices.txt. This file contains the names and prices 
 * of various books, formatted in the following fashion:
 * 
 * I Did It Your Way, 11.95 
 * The History of Scotland, 14.50 
 * Learn Calculus in One Day, 29.95 
 * Feel the Stress, 18.50
 * 
 * Each line in the file contains the name of a book, followed by a comma, followed 
 * by the book’s retail price. When your application begins execution, it should 
 * read the contents of the file and store the book titles in a list component. 
 * The user should be able to select a title from the list and add it to a shopping 
 * cart, which is simply another list component. The application should have buttons 
 * or menu items that allow the user to remove items from the shopping cart, clear 
 * the shopping cart of all selections, and check out. When the user checks out, 
 * the application should calculate and display the subtotal of all the books in 
 * the shopping cart, the sales tax (which is 6 percent of the subtotal), and the total.
 * 
 */
public class Main{
    public static void main(String[] args) throws IOException{
        ShoppingCart myCart = new ShoppingCart();
    }
}
