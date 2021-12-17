// =======================================================================================
// Module : EditorFactory.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================

package mirada.ui.factory;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.text.Document;

import mirada.model.DataField;
import mirada.model.validation.ValidationManager.DataPolicy;
import mirada.ui.document.DataPresenceListener;

/**
 * Factory class that carries knowledge in terms of building editor components armed with 
 * enriched characteristics, such as the automatic addition of specialised documents that
 * are able to control the data received as input   
 */
public final class EditorFactory
{
    private static final Color MANDATORY_FIELD_BG = Color.WHITE;
    private static final Color OPTIONAL_FIELD_BG  = new Color(230, 255, 230);
    
    // ----------------------------------------------------------------------------------
    /**
     * private constructor prevents instantiation of this class 
     */
    private EditorFactory()
    {
        // CNR
    }

    // ----------------------------------------------------------------------------------
    /**
     * Builds and configures an editor that is described by the argument
     * 
     * @param field A descriptor of the field to build and editor for
     * @param policy A model carrying data validation policy
     *
     * 
     * @return An text field editor component 
     */
    public static JTextField buildTextEditor(DataField field, DataPolicy policy)
    {
        JTextField editor = new JTextField(15);
        
        Document validator = policy.getDocumentController();
        
        if (validator != null)
        {
            editor.setDocument(validator);
        }
        
        if (field.isMandatory())
        {
            editor.setBackground(MANDATORY_FIELD_BG);
            editor.getDocument()
                  .addDocumentListener(new DataPresenceListener(editor, 
                                                                MANDATORY_FIELD_BG, 
                                                                OPTIONAL_FIELD_BG));
        }
        else
        {
            editor.setBackground(OPTIONAL_FIELD_BG);
        }
        
        editor.setMargin(new Insets(1, 3, 1, 3));

        return editor;
    }
}
