public class Problem_1_setWarnings {

	private boolean brakes,redLight,yellowLight,greenLight,buzzer;
	
	public void setWarnings (double distance) {
		redLight=yellowLight=greenLight=buzzer=brakes=false;
		if (distance >= 200.0)
			greenLight=true;
		else
			if (distance > 75.0)
				yellowLight=true;
			else
				if (distance >= 25.0) {
					redLight=true;
					yellowLight=true;}
				else {
					brakes=true;
					redLight=true;
					yellowLight=true;
					buzzer=true;}
		}

	public boolean isBrakes() {
		return brakes;
	}

	public void setBrakes(boolean brakes) {
		this.brakes = brakes;
	}

	public boolean isRedLight() {
		return redLight;
	}

	public void setRedLight(boolean redLight) {
		this.redLight = redLight;
	}

	public boolean isYellowLight() {
		return yellowLight;
	}

	public void setYellowLight(boolean yellowLight) {
		this.yellowLight = yellowLight;
	}

	public boolean isGreenLight() {
		return greenLight;
	}

	public void setGreenLight(boolean greenLight) {
		this.greenLight = greenLight;
	}

	public boolean isBuzzer() {
		return buzzer;
	}

	public void setBuzzer(boolean buzzer) {
		this.buzzer = buzzer;
	}

}
