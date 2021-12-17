// =======================================================================================
// Module : EmailAddressFormatValidator.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================


package mirada.model.validation;

import java.util.regex.Pattern;

/**
 * TODO - 
 */
public class EmailAddressFormatValidator extends AbstractValidator
{
    Pattern pattern = Pattern.compile("[a-z0-9]{1,}\\.*[a-z0-9]*@[a-z]{1,}\\.[a-z]{1,}");

    // ----------------------------------------------------------------------------------
    /**
     * Ensures that am email address has carries a correct format
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
        if (! pattern.matcher(data.toLowerCase()).matches())
        {
            return "Bad email format";
        }
        
        return null;
    }
}
