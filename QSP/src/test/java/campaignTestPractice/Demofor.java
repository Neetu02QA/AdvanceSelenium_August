package campaignTestPractice;

import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//import junit.framework.Assert;don take this 
import org.testng.asserts.SoftAssert;
public class Demofor {

	@Test
	public void test() {
		
		System.out.println("Hi");
		
		Assert.assertTrue("Selenium class".contains("Selenium"));
		Assert.assertTrue("Selenium class".contains("Java"));
		Assert.assertFalse("Selenium class".contains("Java"));//test case will pass
		Assert.assertFalse("Selenium class".contains("Selenium"));//test case will fail
		
		/*
		SoftAssert soft=new SoftAssert();
		soft.assertEquals("HDFC", "HFDC");
//		Assert.assertEquals("HDFC","HFDC");
		System.out.println("Bye");
		
		
		soft.assertTrue(false);
		System.out.println("sssss");
		soft.assertAll();//its mandatory because u will nt able to see result///if i wil nt give assertAll() the excdption wil be thr 
		//assertion will fail bc of expected n actual is nt equal its failing
		
//		/*void junit.framework.Assert.assertEquals(String expected, String actual)
//		Asserts that two Strings are equal.
//
//		Parameters:
//		expected
//		actual */
		
//		if ("hdfc".equals("hdfc"))
//			System.out.println("same");
//		else
//			System.out.println("Not same");
	}
}
//using object reference 
//class name

//WebDriver Bonganria dependencies change in binary file 

//don use if else for validation 
//it will check for very importtant check point/critical point it will move to next line
//very simple check point which is nt very important 
//minor major blocker critical? only minor among four 

//expected should be equal to actual one 
//Hard assert - static method 
//Soft assert - nonstatic method 