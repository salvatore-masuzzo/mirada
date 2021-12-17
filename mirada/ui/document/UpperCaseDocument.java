// =======================================================================================
// Module : UpperCaseDocument.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================


package mirada.ui.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * This class ensures that all input is converted to upper case
 */
@SuppressWarnings("serial")
public class UpperCaseDocument extends PlainDocument
{
    // ----------------------------------------------------------------------------------
    /**
     * Creates a new <code>UpperCaseDocument</code> instance 
     */
    public UpperCaseDocument()
    {
        super();
    }

    // ----------------------------------------------------------------------------------
    /**
     * Converts <code>str</code> to upper case before invoking same method in super class
     *
     * @param offs As supplied by Swing
     * @param str As supplied by Swing
     * @param a As supplied by Swing
     *
     * @exception BadLocationException if an error occurs in super class
     */
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException 
    {
        if (str != null) 
        {
            super.insertString(offs, str.toUpperCase(), a);
        }
    }
}
