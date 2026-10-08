package GenericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public String fetchCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sim.format(date);
		return currentdate;
	}

	public String fetchDateAfterGivenNoOfDays(int days) {
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String date_givenDays = sim.format(cal.getTime());
		return date_givenDays;
	}

	public int generateRandomNumber() {
		Random r = new Random();
		int num = r.nextInt(1000);
		return num;
	}
}
