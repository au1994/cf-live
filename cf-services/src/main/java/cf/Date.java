package cf;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Created by Armaan on 2/17/2016.
 */
public class Date {
    String Date, Month, Year;

    Date(String date, String month, String year){
        this.Date = date;
        this.Month = month;
        this.Year = year;
    }

    public DBObject toDBObject(){
        return new BasicDBObject("Date", this.getDate())
                .append("Month", this.getMonth())
                .append("Year", this.getYear());
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getMonth() {

        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getDate() {

        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
