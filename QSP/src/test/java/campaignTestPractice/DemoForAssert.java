package campaignTestPractice;

import org.junit.Assert;
import org.testng.annotations.Test;

public class DemoForAssert {
	@Test
	public void test() {
		
		System.out.println("Hi");
		String s="text";
		Assert.assertNotNull(s);
//		Assert.assertNull(s);
	
	}
	
}
