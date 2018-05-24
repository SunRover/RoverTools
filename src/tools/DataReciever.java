/*DataReciever: Registered to DataHandler, gets distributed requested data*/

package tools;

import java.util.Map;

public interface DataReciever {
	
	public String[] getDataTypes();
	
	public void recieveData(String type, Object data);

}
