public class Problem_5_calcY {

public double calcY (double x) {
	double y;
	if (x<-2.0)
		y=2;
	else
		if (x<0.0)
			y=-x;
		else
			if (x<4.0)
				y=-x*x+4*x;
			else
				if (x<6.0)
					y=x-4;
				else
					y=2.0;
	return y;
	}
}