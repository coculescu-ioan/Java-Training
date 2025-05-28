
public class TruckExpense extends Truck implements Payable, Comparable<TruckExpense> {

	private static final long serialVersionUID = 1L;
	private double fuel_consumption;
	private double drivers_wage;
	private Prices prices;
	
	public TruckExpense(
			int truck_id, 
			String insurance_company, 
			double distance, 
			double fuel_consumption,
			double drivers_wage, 
			Prices prices
	) {
		super(truck_id, insurance_company, distance);
		this.fuel_consumption = fuel_consumption;
		this.drivers_wage = drivers_wage;
		this.prices = prices;
	}

	
	public double getFuel_consumption() {
		return fuel_consumption;
	}


	public void setFuel_consumption(double fuel_consumption) {
		this.fuel_consumption = fuel_consumption;
	}


	public double getDrivers_wage() {
		return drivers_wage;
	}


	public void setDrivers_wage(double drivers_wage) {
		this.drivers_wage = drivers_wage;
	}


	public Prices getPrices() {
		return prices;
	}


	public void setPrices(Prices prices) {
		this.prices = prices;
	}


	@Override
	public String toString() {
		return "TruckExpense ( ID: " + getTruck_id() + " )"
				+ "\n[fuel_consumption=" + fuel_consumption 
				+ ", drivers_wage=" + drivers_wage 
				+ ", " + prices + "]"
				+ "\nTOTAL: " + getTotal();
	}

	@Override
	public double getTotal() {
		double total = 0;
		total += fuel_consumption * prices.getFuel_cost();
		total += drivers_wage;
		total += prices.getInsurance_cost();
		total += prices.getMaintenance_cost();
		total /= getDistance();
		return total;
	}


	@Override
	public int compareTo(TruckExpense o) {
		return Double.compare(this.getTotal(), o.getTotal());
	}
}
