public class Problem_3_maverickFinancialPlanner {

		private double portfolio_amount;
			
		public void calculate_balance (int number_of_shares, double closing_price ) {
			int index;
			final double [] rate={0.0085,0.0135,0.02,0.025,0.0275,0.035};
			final double [] fee={50.0,50.0,0.0,0.0,0.0,0.0};
			if (number_of_shares <= 50) 
				index=0;
			else
				if (number_of_shares < 300)
					index=1;
				else
					if (number_of_shares <= 750)
						index=2;
					else
						if (number_of_shares < 1_000)
							index=3;
						else
							if (number_of_shares <= 2_000)
								index=4;
							else 
								index=5;
			portfolio_amount=number_of_shares*closing_price*(1+rate[index])-fee[index];
		}

		public double getPortfolio_amount() {
			return portfolio_amount;
		}

		public void setPortfolio_amount(double portfolio_amount) {
			this.portfolio_amount = portfolio_amount;
		}
}