package com.example.DoAnJava.Validator;

import com.example.DoAnJava.Validator.annotation.VaLidUserId;
import com.example.DoAnJava.entity.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator implements ConstraintValidator<VaLidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context){
        if(user == null)
            return true;
        return user.getId() != null;
    }
}
