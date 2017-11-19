public class Problem_4_isPrimeShipper {

public boolean isPrimeShipper (int numItems, int yearsCust, double total, double shippingCost) {
	
	boolean a=false;
	if (total>50_000.00)
		if (shippingCost<50.00)
			if (numItems>8)
				if (yearsCust>10)
					a=true;
	return a;
	}
}
