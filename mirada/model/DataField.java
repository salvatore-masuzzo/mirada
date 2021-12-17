// =======================================================================================
// Module : DataField.java
// Project: Mirada
// Author : Salvatore Masuzzo
// =======================================================================================


package mirada.model;

import java.util.StringJoiner;

/**
 * Provides elements that describe data fields with applicable constraints 
 */
public enum DataField
{
    GIVEN_NAME      (true),
    SURNAME         (true),
    ADDRESS_LINE_1  (true),
    ADDRESS_LINE_2  (false),
    TOWN            (true),
    COUNTY          (false),
    COUNTRY         (true),
    POSTAL_CODE     (true),
    EMAIL_ADDRESS   (true),
    PHONE_NUMBER    (false);
    
    private final boolean mandatory;
    
    // ----------------------------------------------------------------------------------
    /**
     * Stores arguments
     *  
     * @param mandatory false defines that accompanying data can optionally exist
     */
    private DataField(boolean mandatory)
    {
        this.mandatory = mandatory;
    }

    // ----------------------------------------------------------------------------------
    public boolean isMandatory()
    {
        return mandatory;
    }

    // ----------------------------------------------------------------------------------
    /**
     * Returns a String representation of an enumeration element, e.g., EMAIL_ADDRESS is
     * transformed to "Email Address"
     */
    public String describe()
    {
        StringJoiner sj = new StringJoiner(" ");
        
        for (String token : super.name().split("_"))
        {
            sj.add(token.charAt(0) + token.substring(1).toLowerCase());
        }

        return sj.toString();
    }
}
