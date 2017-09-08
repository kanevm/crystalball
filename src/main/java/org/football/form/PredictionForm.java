package org.football.form;

import java.util.ArrayList;
import java.util.List;

public class PredictionForm {
	private List<PredictionFormEntry> predictions = new ArrayList<>();

	public List<PredictionFormEntry> getPredictions() {
		return predictions;
	}

	public void setPredictions(final List<PredictionFormEntry> predictions) {
		this.predictions = predictions;
	}
}
