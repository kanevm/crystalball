package org.football.form.validator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.football.form.GameForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class GameFormValidator implements Validator {

	@Override
	public boolean supports(final Class<?> clazz) {
		return clazz.equals(GameForm.class);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		final GameForm form = (GameForm) target;
		validateNonEmptyFields(errors, form);
	}

	private void validateNonEmptyFields(final Errors errors, final GameForm form) {
		if (StringUtils.isBlank(form.getCompetitionId())) {
			errors.reject("competitionId.blank", "Blank competitionId");
		}

		if (CollectionUtils.isEmpty(form.getUserIds())) {
			errors.reject("userIds.empty", "Empty userIds");
		}
	}
}
