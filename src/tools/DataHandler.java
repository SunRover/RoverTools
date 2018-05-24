package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataHandler {

	List<DataSource> sources;
	List<DataReciever> recievers;
	Map<Integer, ArrayList<DataReciever>> typemap;
	Set<String> availabletypes;
	
	public DataHandler() {
		sources = new ArrayList<DataSource>();
		recievers = new ArrayList<DataReciever>();
		availabletypes = new HashSet<String>(); 
		typemap = new HashMap<String, ArrayList<DataReciever>>();
	}
	
	/*Add a source of some type of data*/
	public void addSource(DataSource ds) {
		sources.add(ds);
		for (String type : ds.getOfferedDataTypes()) {
			availabletypes.add(type);
			typemap.put(type, new ArrayList<DataReciever>());
		}
	}
	
	/*Add a receiver with some data reqs. True if data available, else false*/
	public boolean addReciever(DataReciever dr) {
		for (String type : dr.getDataTypes()) {
			if (!availabletypes.contains(type)) {
				return false;
			}
		}
		
		for (String type : dr.getDataTypes()) {
			typemap.get(type).add(dr);
		}
		
		recievers.add(dr);
		
		return true;
	}
	
	public void pushData(String type, Object data) {
		for (DataReciever rec : typemap.get(type)) {
			//System.out.println("DH: sent data");
			rec.recieveData(type, data);
		}
	}
}
