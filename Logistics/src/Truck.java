import java.io.Serializable;

public class Truck implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int truck_id;
	private String insurance_company;
	private double distance;
	
	public Truck(int truck_id, String insurance_company, double distance) {
		super();
		this.truck_id = truck_id;
		this.insurance_company = insurance_company;
		this.distance = distance;
	}

	public int getTruck_id() {
		return truck_id;
	}

	public void setTruck_id(int truck_id) throws Exception {
		if(truck_id < 0)
			throw new Exception("Id cannot be negative!");
		this.truck_id = truck_id;
	}

	public String getInsurance_company() {
		return insurance_company;
	}

	public void setInsurance_company(String insurance_company) throws Exception {
		if(insurance_company.isEmpty())
			throw new Exception("Insurance company name cannot be blank!");
		this.insurance_company = insurance_company;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
}
