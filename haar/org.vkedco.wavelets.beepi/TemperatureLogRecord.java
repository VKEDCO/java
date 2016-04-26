package org.vkedco.wavelets.beepi;

/**
 *
 * @author vladimir kulyukin
 */
public class TemperatureLogRecord {
    
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMins;
    private int mSecs;
    private double mCelciusTemp;
    
    public TemperatureLogRecord(int year, int month, int day, int hour, int mins, int secs, double t) {
        mYear           = year;
        mMonth          = month;
        mDay            = day;
        mHour           = hour;
        mMins           = mins;
        mSecs           = secs;
        mCelciusTemp    = t;
    }
    
    public int getYear()            { return mYear;     }
    public int getMonth()           { return mMonth;    }
    public int getDay()             { return mDay;      }
    public int getHour()            { return mHour;     }
    public int getMins()            { return mMins;     }
    public int getSecs()            { return mSecs;     }
    public double getCelciusTemp()  {    return mCelciusTemp; }
    
    public void setYear(int y)              { mYear = y;    }
    public void setMonth(int m)             { mMonth = m;   }
    public void setDay(int d)               { mDay = d;     }
    public void setHour(int h)              { mHour = h;    }
    public void setMins(int m)              { mMins = m;    }
    public void setSecs(int s)              { mSecs = s;    }
    public void setCelciusTemp(double t)    { mCelciusTemp = t; }
    
    public static TemperatureLogRecord toTemperatureLogRecord(String str) {
        final String[] splits           = str.split(" ");
        final String date_time          = splits[0];
        final String celcius_temp       = splits[1];
        final String[] year_date        = date_time.split("_");
        final String[] year_month_day   = year_date[0].split("-");
        final String[] hour_mins_secs   = year_date[1].split("-");
        final int year                  = Integer.parseInt(year_month_day[0]);
        final int month                 = Integer.parseInt(year_month_day[1]);
        final int day                   = Integer.parseInt(year_month_day[2]);
        final int hour                  = Integer.parseInt(hour_mins_secs[0]);
        final int mins                  = Integer.parseInt(hour_mins_secs[1]);
        final int secs                  = Integer.parseInt(hour_mins_secs[2]);
        final double ctemp              = Double.parseDouble(celcius_temp);
        
        return new TemperatureLogRecord(year, month, day, hour, mins, secs, ctemp);
    }
    
    public String toString() {
        return mYear + "-" + mMonth + "-" + mDay + "_" + mHour + "-" + mMins + "-" + mSecs + " " + mCelciusTemp;
    }
    
    public static void main(String[] args) {
        System.out.println(TemperatureLogRecord.toTemperatureLogRecord("2015-04-01_02-35-51 27.187").toString());
    }
    
}
