package org.football.form.validator;

import java.util.Iterator;
import java.util.List;

import org.football.form.PredictionForm;
import org.football.form.PredictionFormEntry;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PredictionFormValidator implements Validator {

	@Override
	public boolean supports(final Class<?> aClass) {
		return PredictionForm.class.equals(aClass);
	}

	@Override
	public void validate(final Object object, final Errors errors) {
		final PredictionForm form = (PredictionForm) object;
		final List<PredictionFormEntry> predictions = form.getPredictions();
		int i = 0;
		final Iterator<PredictionFormEntry> iterator = predictions.iterator();
		while (iterator.hasNext()) {
			final PredictionFormEntry prediction = iterator.next();

			i++;
		}
	}
}
