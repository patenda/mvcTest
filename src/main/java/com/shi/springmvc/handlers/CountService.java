package com.shi.springmvc.handlers;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.couchbase.client.CouchbaseClient;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.spy.memcached.internal.OperationFuture;

@Component
public class CountService {
	
	public static final int DIVIDED_TO=2;
	public static final String KEY = "testkey";  
	public static final int EXP_TIME = 0;  

	/**
	 * 1.get the balance count
	 * 2.save the data in couchbase
	 * @param jsonStr
	 * @return
	 */
	public String getCount(String jsonStr){

		JSONObject obj=(JSONObject)JSONValue.parse(jsonStr);
		JSONArray jArray = (JSONArray )(obj.get("info"));
		List<PoolInfo> in = new ArrayList<PoolInfo>();
		for(int i=0;i<jArray.size();i++) {
			JSONObject tmp = (JSONObject)jArray.get(i);
			PoolInfo tP = new PoolInfo();
			tP.setName((String)tmp.get("name"));
			tP.setValue(Integer.parseInt(""+tmp.get("value")));
			in.add(tP);
		}
		List<PoolInfo> o = this.getBalancedArray(in);
		JSONObject t = new JSONObject();
		t.put("info", o);
		this.saveData(t.toJSONString());
		
        return "success";
    }
	
	/**
	 * get the balance count
	 * @param list
	 * @return
	 */
	public List<PoolInfo> getBalancedArray(List<PoolInfo> list) {
	
		if (list.size()<=DIVIDED_TO) {
			return list;
		}
		Collections.sort(list);
		int average = this.getAverage(list);
		List<PoolInfo> r = new ArrayList<PoolInfo>();
		List<PoolInfo> temp = new ArrayList<PoolInfo>();
		for(int i=0;i<list.size();i++) {
			int tempValue = ((PoolInfo)list.get(i)).getValue();
			if (tempValue<average) {
				temp.add(list.get(i));
			}else {
				PoolInfo nP = sumTempList(temp);
				r.add(nP);
				temp = new ArrayList<PoolInfo>();
				temp.add(list.get(i));
			}
		}
		PoolInfo nP = sumTempList(temp);
		r.add(nP);
		return r;
	}

	private PoolInfo sumTempList(List<PoolInfo> temp) {
		PoolInfo nP = new PoolInfo();
		String tN = "";
		int tV =0;
		for(int j=0;j<temp.size();j++) {
			PoolInfo tP = (PoolInfo)temp.get(j);
			tN = tN+tP.getName() +"&";
			tV = tV + tP.getValue();
		}
		nP.setName(tN.substring(0,tN.length()-1));
		nP.setValue(tV);
		return nP;
	}
	
	private int getAverage(List<PoolInfo> list) {
		int sum = 0;
		for(int i=0;i<list.size();i++) {
			sum = sum+((PoolInfo)list.get(i)).getValue();
		}
		return sum/DIVIDED_TO;
	}
	
	/**
	 * 
	 * @param str
	 * @return 0:success; 1:fail
	 */
	public String saveData(String str) {
		List<URI> uris = new LinkedList<URI>();  
		uris.add(URI.create("http://127.0.0.1:8091/pools"));  
		CouchbaseClient client = null; 
	    try {  
	        client = new CouchbaseClient(uris, "default", "");  
	      } catch (IOException e) {  
	        System.err.println("IOException connecting to Couchbase: " + e.getMessage());  
	        return "1";
	      } 
	    OperationFuture<Boolean> setOp = client.set(KEY, EXP_TIME, str);  
	    try {  
	        if (setOp.get().booleanValue()) {  
	          System.out.println("Set Succeeded");  
	        } else {  
	          System.err.println("Set failed: " + setOp.getStatus().getMessage());  
	          return "1";
	        }  
	      } catch (InterruptedException e) {  
	        System.err.println("InterruptedException while doing set: " + e.getMessage());  
	        return "1";
	      } catch (ExecutionException e) {  
	        System.err.println("ExecutionException while doing set: " + e.getMessage());  
	        return "1";
	      }  
	      System.out.println("save success");  
	      client.shutdown(3, TimeUnit.SECONDS);  
	      return "0"; 
	}
}
