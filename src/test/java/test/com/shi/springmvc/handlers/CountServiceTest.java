package test.com.shi.springmvc.handlers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.shi.springmvc.handlers.CountService;
import com.shi.springmvc.handlers.PoolInfo;

public class CountServiceTest {

	
	//@Test
	public void testGetBalancedArray() {
		//fail("Not yet implemented");
		CountService s= new CountService();
		List<PoolInfo> in = new ArrayList<PoolInfo>();
		PoolInfo a = new PoolInfo();
		a.setName("POOLA");
		a.setValue(5);
		in.add(a);
		
		PoolInfo b = new PoolInfo();
		b.setName("POOLB");
		b.setValue(1000);
		in.add(b);
		
		PoolInfo c = new PoolInfo();
		c.setName("POOLC");
		c.setValue(500);
		in.add(c);
		
		List<PoolInfo> out = s.getBalancedArray(in);
		for(int i=0;i<out.size();i++) {
			PoolInfo t = (PoolInfo)out.get(i);
			System.out.println(t.getName() + "---" + t.getValue());
		}
	}
	
	//@Test
	public void testGetCount() {
		CountService s= new CountService();
		String jsonStr = "{\"info\":[{\"name\":\"poolA\",\"value\":\"5\"},{\"name\":\"poolB\",\"value\":\"500\"},{\"name\":\"poolC\",\"value\":\"1000\"}]}";
		s.getCount(jsonStr);
	}
	
	//@Test
	public void testSaveData() {
		CountService s= new CountService();
		String r = s.saveData("111");
	}

}
