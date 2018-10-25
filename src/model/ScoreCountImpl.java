package model;

import javax.swing.JLabel;
import model.interfaces.ScoreCount;

public class ScoreCountImpl implements ScoreCount {
	JLabel label;
	int score;
	public ScoreCountImpl(JLabel label, int score) {
		super();
		this.label = label;
		this.score = score;
	}
	public void setLabelText(String labelText) {
		this.label.setText(labelText);;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
