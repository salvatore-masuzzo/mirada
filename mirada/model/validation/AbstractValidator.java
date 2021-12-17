// =======================================================================================
// Module : AbstractValidator.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================


package mirada.model.validation;

/**
 * Base class for data validation implementations
 */
public abstract class AbstractValidator implements DataCheckable
{
    private String reason;
    
    // ----------------------------------------------------------------------------------
    /**
     * Runs validation in line with the logic of the implementation class 
     * 
     * @param data Data to validate
     * 
     * @return A string describing the reason where validation fails
     */
    protected abstract String runValidaton(String data);
    
    // ----------------------------------------------------------------------------------
    @Override
    public String getReason()
    {
        return reason;
    }

    // ----------------------------------------------------------------------------------
    /**
     * {@inheritDoc} 
     * 
     * @see mirada.model.validation.DataCheckable#validate(String)
     */
    @Override
    public boolean validate(String data)
    {
        this.reason = runValidaton(data);
        
        return this.reason == null;
    }
}
