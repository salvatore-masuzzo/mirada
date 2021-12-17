// =======================================================================================
// Module : DataCheckable.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================


package mirada.model.validation;

/**
 * Contract specification for classes that require the running of a validation process
 * while setting a description message if that validation fails 
 */
public interface DataCheckable
{
    // ----------------------------------------------------------------------------------
    /**
     * Checks to see that the argument is in a correct format
     * 
     * @param data Data to check
     * 
     * @return return true if the argument is correct
     */
    public boolean validate(String data);
    
    // ----------------------------------------------------------------------------------
    /**
     * Returns a string that describes a possible reason for the validate method to have 
     * returned false
     * 
     * @return String
     */
    public String getReason();
}
