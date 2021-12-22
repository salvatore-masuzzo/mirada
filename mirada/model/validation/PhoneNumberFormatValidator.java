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
    // Only some format such as "+44 7788 123456" or "0044 7788 123456" are catered for here
    private Pattern pattern = Pattern.compile("((\\\\+)|(00))[1-9]{2}[ ]*[1-9][0-9]{3}[ ]*[0-9]{6,10}");
    
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
