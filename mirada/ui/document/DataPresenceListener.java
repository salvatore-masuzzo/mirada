// =======================================================================================
// Module : DataPresenceListener.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================


package mirada.ui.document;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Listens to data being edited in a text field and changes the background of the source
 * component depending on presence or absence of data 
 */
public class DataPresenceListener implements DocumentListener
{
    private final JTextField editor;
    private final Color hasNoDataColour;
    private final Color hasDataColour;
    
    // ----------------------------------------------------------------------------------
    public DataPresenceListener(JTextField editor, Color hasNoDataColour, Color hasDataColour)
    {
        this.editor = editor;
        this.hasNoDataColour = hasNoDataColour;
        this.hasDataColour = hasDataColour;
    }

    // ----------------------------------------------------------------------------------
    @Override
    public void insertUpdate(DocumentEvent event)
    {
        adjustBakground();
    }

    // ----------------------------------------------------------------------------------
    @Override
    public void removeUpdate(DocumentEvent event)
    {
        adjustBakground();
    }
    
    // ----------------------------------------------------------------------------------
    /**
     * Changes the background of the source component based on presence or absence of data
     */
    private void adjustBakground()
    {
        Color targetBg = editor.getText().trim().length() == 0 ? this.hasNoDataColour
                                                               : this.hasDataColour; 
        if (! editor.getBackground().equals(targetBg))
        {
            editor.setBackground(targetBg);
        }
    }

    // ----------------------------------------------------------------------------------
    /**
     * Empty implementation as this method does not appear to be ever invoked 
     *
     * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
     */
    @Override
    public void changedUpdate(DocumentEvent event)
    {
        // CNR
    }
}
