// =======================================================================================
// Module : MinimumLengthValidator.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================


package mirada.model.validation;

/**
 * Checks to see that a minimum data is in force
 */
public class MinimumLengthValidator extends AbstractValidator
{
    private int minLength;
    
    // ----------------------------------------------------------------------------------
    public MinimumLengthValidator(int minLength)
    {
        this.minLength = minLength;
    }

    // ----------------------------------------------------------------------------------
    /**
     * As per responsibility of this class
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
        if (data == null || data.trim().length() < minLength)
        {
            return "Insufficient data length: Expected min " + minLength;
        }
        
        return null;
    }
}
