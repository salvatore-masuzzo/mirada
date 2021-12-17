// =======================================================================================
// Module : FormPanel.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================

package mirada.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import mirada.model.DataField;
import mirada.model.validation.ValidationManager;
import mirada.model.validation.ValidationManager.DataPolicy;
import mirada.ui.factory.EditorFactory;

/**
 * UI form implementation - because it's a panel, it can be freely added to any client
 * code requiring its support
 */
@SuppressWarnings("serial")
public class FormPanel extends JPanel
{
    private Map<DataField, JTextField> editorMap = new HashMap<>();
    private ValidationManager validationMan = new ValidationManager();
    
    private JButton checkBtn = new JButton("Check FormPanel");
    
    // ----------------------------------------------------------------------------------
    /**
     * Fully constructs the user interface and makes it visible on screen 
     */
    public FormPanel()
    {
        build();
        defineBeahaviour();
    }
    
    // ----------------------------------------------------------------------------------
    private void build()
    {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        super.add(mainPanel);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        mainPanel.add(buildEditorPanel(), BorderLayout.NORTH);
        mainPanel.add(buildControlPanel(), BorderLayout.SOUTH);
    }
    
    // ----------------------------------------------------------------------------------
    private JComponent buildEditorPanel()
    {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 6));
        
        for (DataField field : DataField.values())
        {
            DataPolicy policy = validationMan.getPolicy(field);
            
            panel.add(new JLabel(field.describe(), JLabel.RIGHT));
            JTextField editor = EditorFactory.buildTextEditor(field, policy);
            panel.add(editor);
            
            // Keep a reference so we can easily run data entry validation when needed 
            this.editorMap.put(field, editor);
        }
        
        return panel;
    }
    
    // ----------------------------------------------------------------------------------
    private JComponent buildControlPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(this.checkBtn);
        return panel;
    }
    
    // ----------------------------------------------------------------------------------
    private void defineBeahaviour()
    {
        this.checkBtn.addActionListener(this::validateInput);
    }
    
    // ----------------------------------------------------------------------------------
    /**
     * Checks to see that mandatory input is present  
     * 
     * @param event As provided by AWT
     */
    private void validateInput(ActionEvent event)
    {
        extractDataFromEditors(); 
                        
        String report = validationMan.validateData();
        
        if (report != null)
        {
            JOptionPane.showMessageDialog(this, report, "Data Status", 
                                          JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "FormPanel OK", "Data Status", 
                                          JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // ----------------------------------------------------------------------------------
    /**
     * Extracts data chunks from the editors and places them into the corresponding
     * {@link DataPolicy} instances as maintained by {@link ValidationManager}. This
     * is to be performed towards data validation
     */
    private void extractDataFromEditors()
    {
        for (Entry<DataField, JTextField> entry : editorMap.entrySet())
        {
            DataField field = entry.getKey();
            String data = entry.getValue().getText();
            
            validationMan.setData(field, data);
        }
    }
}
