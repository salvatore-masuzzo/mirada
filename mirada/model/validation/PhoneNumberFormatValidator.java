// =======================================================================================
// Module : PhoneNumberFormatValidator.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================


package mirada.model.validation;

import java.util.regex.Pattern;

/**
 * TODO - 
 */
public class PhoneNumberFormatValidator extends AbstractValidator 
{
    // Only a format such as "+44 7788 123456" or "+44 7788 1234567890" is catered for here
    private Pattern pattern = Pattern.compile("\\+[1-9]{2}[ ]*[1-9][0-9]{3}[ ]*[0-9]{6,10}");
    
    // ----------------------------------------------------------------------------------
    /**
     * Checks to see that a phone number is expressed correctly 
     *  
     * @param data {@inheritDoc}
     * 
     * @return {@inheritDoc}
     *
     * @see mirada.model.validation.AbstractValidator#runValidaton(java.lang.String)
     */
    @Override
    protected String runValidaton(String data)
    {
        if (! pattern.matcher(data).matches())
        {
            return "Incorrect phone number format";
        }
        
        return null;
    }
}
