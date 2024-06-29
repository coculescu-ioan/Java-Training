import java.io.Serializable;

public class Prices implements Serializable{

	private static final long serialVersionUID = 1L;
	private double fuel_cost;
	private double maintenance_cost;
	private double insurance_cost;
	
	public Prices(double fuel_cost, double maintenance_cost, double insurance_cost) {
		super();
		this.fuel_cost = fuel_cost;
		this.maintenance_cost = maintenance_cost;
		this.insurance_cost = insurance_cost;
	}
	
	@Override
	public String toString() {
		return "Prices [fuel_cost=" + fuel_cost 
				+ ", maintenance_cost=" + maintenance_cost 
				+ ", insurance_cost=" + insurance_cost + "]";
	}

	public double getFuel_cost() {
		return fuel_cost;
	}
	public void setFuel_cost(double fuel_cost) {
		this.fuel_cost = fuel_cost;
	}
	public double getMaintenance_cost() {
		return maintenance_cost;
	}
	public void setMaintenance_cost(double maintenance_cost) {
		this.maintenance_cost = maintenance_cost;
	}
	public double getInsurance_cost() {
		return insurance_cost;
	}
	public void setInsurance_cost(double insurance_cost) {
		this.insurance_cost = insurance_cost;
	}
	
}
