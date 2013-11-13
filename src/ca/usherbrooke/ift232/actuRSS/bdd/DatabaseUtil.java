package ca.usherbrooke.ift232.actuRSS.bdd;

import java.util.Calendar;

public class DatabaseUtil {

	public String ConvertCalendarToString(Calendar date) {
		StringBuffer dateToReturn = new StringBuffer();
		
		/* Partie Date  **************************************/
		
		dateToReturn.append(date.get(Calendar.YEAR));
		
		dateToReturn.append("-");

		dateToReturn.append(this.GetNormalForm(date.get(Calendar.MONTH)));      

		dateToReturn.append("-");
		
		dateToReturn.append(this.GetNormalForm(date.get(Calendar.DATE)));
		
		dateToReturn.append(" ");
		
		/* Partie Heure ************************************/
		dateToReturn.append(this.GetNormalForm(date.get(Calendar.HOUR_OF_DAY)));
		dateToReturn.append(":");
		dateToReturn.append(this.GetNormalForm(date.get(Calendar.MINUTE)));
		dateToReturn.append(":");
		dateToReturn.append(this.GetNormalForm(date.get(Calendar.SECOND)));

		return dateToReturn.toString();
	}
	
	private String GetNormalForm(int value) {
		String toReturn = null;
		if(value < 10) {
			toReturn = "0" + value;
		}
		else {
			toReturn = "" + value;
		}
		return toReturn;
	}

	public Calendar convertStringToCalendar(String date)
	{
		//YYYY-MM-DD HH:MM:SS.SSS
		Calendar calendar = Calendar.getInstance();
		
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		int hourOfDay = Integer.parseInt(date.substring(11, 13));
		int minute = Integer.parseInt(date.substring(14, 16));
		int second = Integer.parseInt(date.substring(17, 19));
		
 		calendar.set(year, month, day, hourOfDay, minute, second);
		
		return calendar;
	}
}
