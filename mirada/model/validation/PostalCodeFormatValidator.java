// =======================================================================================
// Module : PostalCodeFormatValidator.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================


package mirada.model.validation;

import java.util.regex.Pattern;

/**
 * TODO - 
 */
public class PostalCodeFormatValidator extends AbstractValidator
{
    // We are only catering for a small subset of UK only postcode formats here
    
    private Pattern pattern = Pattern.compile("[A-Z]{1,2}[0-9]{1,2} [0-9]{1,2}[A-Z]{1,2}");

    
    // ----------------------------------------------------------------------------------
    /**
     * Checks to see that the format of a postal code is compliant
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
            return "Incorrect format";
        }
        
        return null;
    }
}
