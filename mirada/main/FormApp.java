// =======================================================================================
// Module : FormApp.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================


package mirada.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import mirada.ui.FormPanel;

/**
 * Provides main method which instantiates this class as a UI application   
 */
@SuppressWarnings("serial")
public final class FormApp extends JFrame
{
    // ----------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        new FormApp();
    }

    // ----------------------------------------------------------------------------------
    /**
     * Private constructor to prevent instantiation of this class
     */
    private FormApp()
    {
        super.setTitle("Controlled form");
        super.add(new JScrollPane(new FormPanel()));

        super.pack();
        super.setSize(400, 450);
        centerOnScreen();
        
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
    
    // -------------------------------------------------------------------------
    /**
     * Centres a window in the middle of the screen in both the x and y directions
     */
    private void centerOnScreen()
    {
        Dimension targetSize = super.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xLocation = (screenSize.width - targetSize.width) / 2;
        int yLocation = (screenSize.height - targetSize.height) / 2;
        super.setBounds(xLocation, yLocation, targetSize.width, targetSize.height);
    }
}
