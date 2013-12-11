package ca.usherbrooke.ift232.actuRSS.bdd;

import java.util.Calendar;

/**
 * Cette classe contient des méthodes utilitaires pour
 * faciliter la gestion des dates entre le Modele et la BdD
 * 
 * @author Vincent Chataignier, Matthieu Poupineau
 */
public class DatabaseUtil {

        /**
         * Tranforme un objet Calendar en chaine de caractères
         * 
         * @param date la date à transformer
         * @return la date sous forme de chaine de caractères
         */
        public static String ConvertCalendarToString(Calendar date) {
               
                StringBuffer dateToReturn = new StringBuffer();
               
                /* Partie Date  **************************************/
               
                dateToReturn.append(date.get(Calendar.YEAR));
               
                dateToReturn.append("-");

                dateToReturn.append(GetNormalForm(date.get(Calendar.MONTH)));      

                dateToReturn.append("-");
               
                dateToReturn.append(GetNormalForm(date.get(Calendar.DATE)));
               
                dateToReturn.append(" ");
               
                /* Partie Heure ************************************/
                dateToReturn.append(GetNormalForm(date.get(Calendar.HOUR_OF_DAY)));
                dateToReturn.append(":");
                dateToReturn.append(GetNormalForm(date.get(Calendar.MINUTE)));
                dateToReturn.append(":");
                dateToReturn.append(GetNormalForm(date.get(Calendar.SECOND)));

                return dateToReturn.toString();
        }
       
        /**
         * Transforme un entier entre 0 et 99 en chaine de caractères
         * et rajoute un zéro au début s'il est inférieur à 10.
         * 
         * @param value un entier positif et inférieur à 100
         * @return une chaine de caractère contenant l'entier sous
         * la forme de deux caractères
         */
        private static String GetNormalForm(int value) {
                String toReturn = null;
                if(value < 10) {
                        toReturn = "0" + value;
                }
                else {
                        toReturn = "" + value;
                }
                return toReturn;
        }

        /**
         * Tranforme une chaine de caractères en objet Calendar
         * 
         * @param date la date sous forme de chaine de caractères
         * @return la date sous forme d'objet Calendar
         */
        public static Calendar convertStringToCalendar(String date)
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
