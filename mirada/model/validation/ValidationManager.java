// =======================================================================================
// Module : ValidationManager.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================


package mirada.model.validation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.swing.text.Document;

import mirada.model.DataField;
import mirada.ui.document.NumberDocument;
import mirada.ui.document.UpperCaseDocument;

/**
 * Provides routines that are intended to be used for data presence and data validation 
 */
public class ValidationManager
{
    private Map<DataField, DataPolicy> validationMap = new LinkedHashMap<>();
    
    // ----------------------------------------------------------------------------------
    public ValidationManager()
    {
        this.validationMap = buildValidationPolicy();
    }
    
    // ----------------------------------------------------------------------------------
    public DataPolicy getPolicy(DataField field)
    {
        return validationMap.get(field);
    }
    
    // ----------------------------------------------------------------------------------
    public void setData(DataField field, String data)
    {
        validationMap.get(field).setData(data);
    }

    // ----------------------------------------------------------------------------------
    /**
     * Builds a map where that associated the description of data fields with a compatible
     * data policy
     * 
     * @return map
     */
    private static Map<DataField, DataPolicy> buildValidationPolicy()
    {
        Map<DataField, DataPolicy> validationMap = new LinkedHashMap<>();
        
        for (DataField field : DataField.values())
        {
            DataPolicy policyModel = new DataPolicy(field);
            validationMap.put(field, policyModel);
            
            switch (field)
            {
                case GIVEN_NAME: 
                    policyModel.setValidator(new MinimumLengthValidator(1));
                    break;
                
                case ADDRESS_LINE_1:
                case ADDRESS_LINE_2:
                    policyModel.setValidator(new MinimumLengthValidator(4));
                    break;
                    
                case SURNAME   : 
                case TOWN:
                case COUNTRY:
                case COUNTY:
                    policyModel.setValidator(new MinimumLengthValidator(2));
                    break; 
                     
                case POSTAL_CODE:
                    policyModel.setValidator(new PostalCodeFormatValidator());
                    policyModel.setDocumentController(new UpperCaseDocument());
                    break;
                    
                case EMAIL_ADDRESS:
                    policyModel.setValidator(new EmailAddressFormatValidator());
                    break;

                case PHONE_NUMBER:
                    policyModel.setValidator(new PhoneNumberFormatValidator());
                    policyModel.setDocumentController(new NumberDocument());
                    break;
            }
        }
        
        return validationMap;
    }
    
    // ----------------------------------------------------------------------------------
    /**
     * Checks to see that mandatory data is present and is not blank across the elements
     * maintained in this class
     * 
     * @return A null value where there is nothing to report (all is OK), else an informative
     *         message that describes the fields at fault and what is to be fixed
     */
    public String validateData()
    {
        String emptyFieldsReport = validateMandatoryDataIsPresent();
        String badFormatReport = validateDataFormats();
        
        if (! emptyFieldsReport.isEmpty() || ! badFormatReport.isEmpty())
        {
            StringJoiner finalReport = new StringJoiner("\n");
            
            for (String report : new String[] { emptyFieldsReport, badFormatReport })
            {
                if (! report.isEmpty())
                {
                    finalReport.add(report);
                }
            }
            
            return finalReport.toString();
        }
        
        return null;
    }
    
    // ----------------------------------------------------------------------------------
    /**
     * Checks to see that mandatory data is defined
     * 
     * @return An informative message in relation to missing mandatory data
     */
    private String validateDataFormats()
    {
        StringJoiner report = new StringJoiner("\n");
        
        for (DataPolicy policy : validationMap.values())
        {
            DataCheckable validator = policy.getValidator();
            String data = policy.getData();
                            
            if (data != null && ! data.isEmpty() && ! validator.validate(data))
            {
                String reason = String.format("- %s: %s", policy.getField().describe(),
                                                          validator.getReason());
                report.add(reason);
            }
        }
        
        return report.length() > 0 ? "Validation Errors\n" + report.toString() : "";
    }
    
    // ----------------------------------------------------------------------------------
    /**
     * Checks to see that data, if defined, is in a format that is deemed to be correct
     * 
     * @return An informative message in relation to data that carries an incorrect format
     */
    private String validateMandatoryDataIsPresent()
    {
        StringJoiner report = new StringJoiner("\n");
        
        for (DataPolicy policy : validationMap.values())
        {
            DataField dataChecker = policy.getField();
            
            if (dataChecker.isMandatory())
            {
                String data = policy.getData();
                
                if (data != null && data.trim().length() == 0)
                {
                    String message = String.format("- %s", dataChecker.describe());
                    report.add(message);
                }
            }
        }
    
        return report.length() > 0 ? "Missing mandatory data\n" + report.toString() : "";
    }
    
    // ----------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------
    public static class DataPolicy
    {
        private DataField field;
        private Document documentController;
        private DataCheckable validator;
        
        private String data; // This is filled at runtime 

        // ----------------------------------------------------------------------------------
        public DataPolicy(DataField field)
        {
            this.field = field;
        }

        // ----------------------------------------------------------------------------------
        public DataField getField()
        {
            return field;
        }

        // ----------------------------------------------------------------------------------
        public void setField(DataField fiels)
        {
            this.field = fiels;
        }

        // ----------------------------------------------------------------------------------
        public Document getDocumentController()
        {
            return documentController;
        }

        // ----------------------------------------------------------------------------------
        public void setDocumentController(Document documentController)
        {
            this.documentController = documentController;
        }

        // ----------------------------------------------------------------------------------
        public DataCheckable getValidator()
        {
            return validator;
        }

        // ----------------------------------------------------------------------------------
        public void setValidator(DataCheckable validator)
        {
            this.validator = validator;
        }

        // ----------------------------------------------------------------------------------
        public String getData()
        {
            return data;
        }

        // ----------------------------------------------------------------------------------
        public void setData(String data)
        {
            this.data = data;
        }
    }
}
