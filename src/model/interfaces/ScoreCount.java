package model.interfaces;

import javax.swing.JLabel;

public interface ScoreCount {
	public JLabel getLabel();
	public void setLabel(JLabel label);
	public int getScore();
	public void setScore(int score);
	public void setLabelText(String labelText);
}
