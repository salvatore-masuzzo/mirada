// =======================================================================================
// Module : NumberDocument.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================


package mirada.ui.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * This class prevents the insertion of non numeric characters into a text editor against 
 * which an instance of this document is registered
 */
@SuppressWarnings("serial")
public class NumberDocument extends PlainDocument
{
    @Override
    public void insertString(int offset, String str, AttributeSet a) 
    throws BadLocationException
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (! Character.isDigit(str.charAt(i)))
            {
                return;
            }
        }

        super.insertString(offset, str, a);
    }
}
