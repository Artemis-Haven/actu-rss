package ca.usherbrooke.ift232.actuRSS.bdd;

import java.util.Calendar;

public class DatabaseUtil {

	public String ConvertCalendarToString(Calendar date) {
		StringBuffer dateToReturn = new StringBuffer();
		
		/* Partie Date  **************************************/
		
		dateToReturn.append(date.get(Calendar.YEAR));
		dateToReturn.append("-");
		String month = null;
	      int mo = date.get(Calendar.MONTH) + 1;
	      if(mo < 10) {
	        month = "0" + mo;
	      }
	      else {
	        month = "" + mo;
	      }
	      dateToReturn.append(month);      
	      
	      dateToReturn.append("-");
	      
	      String day = null;
	      int dt = date.get(Calendar.DATE);
	      if(dt < 10) {
	        day = "0" + dt;
	      }
	      else {
	        day = "" + dt;
	      }
	      dateToReturn.append(day);
	      dateToReturn.append(" ");
	      /* Partie Heure ************************************/
	      dateToReturn.append(date.get(Calendar.HOUR_OF_DAY));
	      dateToReturn.append(":");
	      dateToReturn.append(date.get(Calendar.MINUTE));
	      dateToReturn.append(":");
	      dateToReturn.append(date.get(Calendar.SECOND));
		
		return dateToReturn.toString();
	}
	
	public Calendar convertStringToCalendar(String date)
	{
		//YYYY-MM-DD HH:MM:SS.SSS
		Calendar calendar = Calendar.getInstance();
		
		int year = Integer.parseInt(date.substring(0, 3));
		int month = Integer.parseInt(date.substring(4, 5));
		int day = Integer.parseInt(date.substring(6, 7));
		int hourOfDay = Integer.parseInt(date.substring(9, 10));
		int minute = Integer.parseInt(date.substring(11, 12));
		int second = Integer.parseInt(date.substring(13, 14));
		
 		calendar.set(year, month, day, hourOfDay, minute, second);
		
		return calendar;
	}
}
