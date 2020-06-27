import java.util.*;

public class Passenger {
	private int id;
	private String name;
	private String sex;
	private String Bus_start;
	private String Bus_end;
	private int driverId;
	private String driverName;

	
	public String getBus_start() {
		return Bus_start;
	}
	public void setBus_start(String bus_start) {
		Bus_start = bus_start;
	}
	public String getBus_end() {
		return Bus_end;
	}
	public void setBus_end(String bus_end) {
		Bus_end = bus_end;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public int getId() {
		return id;
	}
	public void setId(int string) {
		this.id = string;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	

}


