package org.football.form.validator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.football.form.RegisterForm;
import org.football.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegisterFormValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(final Class<?> clazz) {
		return clazz.equals(RegisterForm.class);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		final RegisterForm form = (RegisterForm) target;
		validateNonEmptyFields(errors, form);
		validatePasswords(errors, form);
		validateEmail(errors, form);
	}

	private void validatePasswords(final Errors errors, final RegisterForm form) {
		if (StringUtils.isBlank(form.getPassword()) || StringUtils.isBlank(form.getPasswordRepeated())) {
			return;
		}

		if (!form.getPassword().equals(form.getPasswordRepeated())) {
			errors.reject("password.no_match", "Passwords do not match");
		}
	}

	private void validateEmail(final Errors errors, final RegisterForm form) {
		if (StringUtils.isBlank(form.getEmail())) {
			return;
		}

		final String email = form.getEmail();
		final EmailValidator emailValidator = EmailValidator.getInstance(false);

		if (!emailValidator.isValid(email)) {
			errors.reject("email.invalid", "Not a valid email");
		}

		if (userService.getUserByEmail(email).isPresent()) {
			errors.reject("email.exists", "User with this email already exists");
		}
	}

	private void validateNonEmptyFields(final Errors errors, final RegisterForm form) {
		if (StringUtils.isBlank(form.getEmail())) {
			errors.reject("email.blank", "Blank email");
		}

		if (StringUtils.isBlank(form.getName())) {
			errors.reject("name.blank", "Blank name");
		}

		if (StringUtils.isBlank(form.getPassword())) {
			errors.reject("password.blank", "Blank password");
		}

		if (StringUtils.isBlank(form.getPasswordRepeated())) {
			errors.reject("passwordRepeated.blank", "Blank password repeated");
		}
	}
}
