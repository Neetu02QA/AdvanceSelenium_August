package JavaUtilitiesProgram;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import io.opentelemetry.sdk.metrics.data.Data;

public class JavaUtilitiesPrograme {
	public int getRandomNumber() {
		Random rd = new Random();
		int randomNumber = rd.nextInt(1000);
		return randomNumber;
	}
	
	public String getCurrentDate() {
	
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = sim.format(date);
		return currentDate;
	}
	
	public String getRequiredDate(int exDate) {
		Date date =new Date();
		
		
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		
		cal.add(Calendar.DAY_OF_MONTH, exDate);
		String expectedDate = sim.format(cal.getTime());
		return expectedDate;
	}
	
	
}
