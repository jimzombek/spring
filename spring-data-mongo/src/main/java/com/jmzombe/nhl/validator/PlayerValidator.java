package com.jmzombe.nhl.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jmzombe.nhl.model.Player;

/**
 * Validates Player instances before saving to the data store.
 */
public class PlayerValidator implements Validator {
    /**
     * Ensures that this class only validates Player objects.
     *
     * @param aClass the class to be validated
     * @return true if the specified class is Player
     */
    @Override
    public boolean supports(Class<?> aClass) {
    	return Player.class.isAssignableFrom(aClass);
    }

    /**
     * Validates Player objects.  Player has a Position field, so this method
     * creates a PositionValidator and invokes it.  The field path has to be adjusted
     * to look in this object, so it is adjusted, then returned after validation.
     *
     * @param o the object to be validated
     * @param errors any errors that are encountered in validation
     */
    @Override
    public void validate(Object o, Errors errors) {
    	//super(o, errors);
    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "player field", "player.field.required");
    	
    	// below is old code
    	Player player = Player.class.cast(o);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "Player name cannot be empty");
        if (player.getPosition() != null) {
            //Position position = athlete.getPosition();
            //errors.pushNestedPath("position");
            //ValidationUtils.invokeValidator(new PositionValidator(), position, errors);
            errors.popNestedPath();
        }
    }
}

