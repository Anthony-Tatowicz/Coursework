public class Problem_2_GndCollWarnClass {

	int timer;
	boolean gndCollCaut, gndCollWarn, gndCollAlert,emerGD;
	
	public void gndCollWarning (boolean gearUp, double altfeet) {
		gndCollCaut=gndCollWarn=gndCollAlert=emerGD=false;
		if (gearUp)
			if (altfeet<=400)
				if (altfeet>200) 
					gndCollCaut=true;
				else
					if (altfeet>100)
						gndCollWarn=true;
					else {
						gndCollAlert=true;
						if (altfeet<=50)
							emerGD=true;}
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public boolean isGndCollCaut() {
		return gndCollCaut;
	}

	public void setGndCollCaut(boolean gndCollCaut) {
		this.gndCollCaut = gndCollCaut;
	}

	public boolean isGndCollWarn() {
		return gndCollWarn;
	}

	public void setGndCollWarn(boolean gndCollWarn) {
		this.gndCollWarn = gndCollWarn;
	}

	public boolean isGndCollAlert() {
		return gndCollAlert;
	}

	public void setGndCollAlert(boolean gndCollAlert) {
		this.gndCollAlert = gndCollAlert;
	}

	public boolean isEmerGD() {
		return emerGD;
	}

	public void setEmerGD(boolean emerGD) {
		this.emerGD = emerGD;
	}


}
