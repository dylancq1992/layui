package com.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 日期转换工具类
 *
 */
public class DateUtil {
	
	public static final String YYYY_MM_DD_HH24_MI_SS_DATE_FORMAT = "yyyy-MM-dd HH24:mi:ss";
     
	/**
     * 把miniui-datepicker的时间转为yyyyMMdd
     * @author HZG
     * @date 2017年5月25日
     */
    static public String getDateFromDatepicker(String d) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = sdf.parse(d);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        String date2 = sdf2.format(date);
        return date2;
    }
   static public String getYyyyMMdd(String d) throws ParseException{
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Date date = sdf.parse(d);
       SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
       String date2 = sdf2.format(date);
       return date2;
   }
	/**
	 * 获取日期的"yyyy-MM-dd"格式值
	 * @param date 日期值
	 */
	static public String getDateStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
    
	static public String getDateStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(date);
	}

	static public String getDateStrCompact(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		return str;
	}


	static public String getDateTimeStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	static public String getDateTimeStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.format(date);
	}

	public static String getCurDateStr(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}
	
	public static String getDateStr(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		if(date==null) date = new Date();
		return format.format(date);
	}


	static public Date parseDate(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(s);
	}

	static public Date parseDateC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.parse(s);
	}


	static public Date parseDateTime(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.parse(s);
	}

	static public Date parseDateTimeC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.parse(s);
	}


	static public Date parseTime(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.parse(s);
	}

	static public Date parseTimeC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss秒");
		return format.parse(s);
	}

	static public int yearOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(0, 4));
	}

	static public int monthOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(5, 7));
	}

	static public int dayOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(8, 10));
	}
	static public int hoursOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(s);
		return Integer.parseInt(d.substring(11, 13));
	}

	static public int minuteOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(s);
		return Integer.parseInt(d.substring(14, 16));
	}
	
	static public int secondeOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(s);
		return Integer.parseInt(d.substring(17, 19));
	}
	
	
	static public String getDateTimeStr(java.sql.Date date, double time) throws ParseException {
		int year;
		year = yearOfDate(date);
		int month = monthOfDate(date);
		int day = dayOfDate(date);
		String dateStr = year + "-" + month + "-" + day;
		Double d = new Double(time);
		String timeStr = String.valueOf(d.intValue()) + ":00:00";

		return dateStr + " " + timeStr;
	
	}


	static public int diffDateM(Date sd, Date ed) throws ParseException {
		return (yearOfDate(ed) - yearOfDate(sd)) * 12 + monthOfDate(ed)
				- monthOfDate(sd) ;
	}

	static public int diffDateD(Date sd, Date ed) throws ParseException {
		return Math.round((ed.getTime() - sd.getTime()) / 86400000) + 1;
	}

	static public int diffDateM(int sym, int eym) throws ParseException {
		return (Math.round(eym / 100) - Math.round(sym / 100)) * 12
				+ (eym % 100 - sym % 100) + 1;
	}

	static public java.sql.Date getNextMonthFirstDate(java.sql.Date date)
			throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.set(Calendar.DATE, 1);
		return new java.sql.Date(scalendar.getTime().getTime());
	}

	static public java.sql.Date getFrontDateByDayCount(java.sql.Date date,
			int dayCount) throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.DATE, -dayCount);
		return new java.sql.Date(scalendar.getTime().getTime());
	}

	static public Date getFirstDay(String year, String month)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	static public Date getFirstDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	static public Date getLastDay(String year, String month)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	static public Date getLastDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	static public String getTodayStr() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		return getDateStr(calendar.getTime());
	}

	static public Date getToday() throws ParseException {
		return new Date(System.currentTimeMillis());
	}

	static public String getTodayAndTime() {
		return new Timestamp(System.currentTimeMillis()).toString();
	}

	static public String getTodayC() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		return getDateStrC(calendar.getTime());
	}

	static public int getThisYearMonth() throws ParseException {
		Date today = Calendar.getInstance().getTime();
		return yearOfDate(today)* 100 + monthOfDate(today);
	}

	static public int getYearMonth(Date date) throws ParseException {
		return yearOfDate(date) * 100 + monthOfDate(date);
	}


	static public long getDistinceMonth(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long monthCount = 0;
		try {
			java.util.Date d1 = d.parse(beforedate);
			java.util.Date d2 = d.parse(afterdate);

			monthCount = ((yearOfDate(d2) - yearOfDate(d1)) * 12 + monthOfDate(d2)
					- monthOfDate(d1));
			// dayCount = (d2.getTime()-d1.getTime())/(30*24*60*60*1000);

		} catch (ParseException e) {
			System.out.println("Date parse error!");
			// throw e;
		}
		return monthCount;
	}


	static public long getDistinceDay(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long dayCount = 0;
		try {
			java.util.Date d1 = d.parse(beforedate);
			java.util.Date d2 = d.parse(afterdate);

			dayCount = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);

		} catch (ParseException e) {
			System.out.println("Date parse error!");
			// throw e;
		}
		return dayCount;
	}


	static public long getDistinceDay(Date beforedate, Date afterdate)
			throws ParseException {
		long dayCount = 0;

		try {
			dayCount = (afterdate.getTime() - beforedate.getTime())
					/ (24 * 60 * 60 * 1000);

		} catch (Exception e) {
			// System.out.println("Date parse error!");
			// // throw e;
		}
		return dayCount;
	}

	static public long getDistinceDay(java.sql.Date beforedate,
			java.sql.Date afterdate) throws ParseException {
		long dayCount = 0;

		try {
			dayCount = (afterdate.getTime() - beforedate.getTime())
					/ (24 * 60 * 60 * 1000);

		} catch (Exception e) {
			// System.out.println("Date parse error!");
			// // throw e;
		}
		return dayCount;
	}


	static public long getDistinceDay(String beforedate) throws ParseException {
		return getDistinceDay(beforedate, getTodayStr());
	}


	static public long getDistinceTime(String beforeDateTime,
			String afterDateTime) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long timeCount = 0;
		try {
			java.util.Date d1 = d.parse(beforeDateTime);
			java.util.Date d2 = d.parse(afterDateTime);

			timeCount = (d2.getTime() - d1.getTime()) / (60 * 60 * 1000);

		} catch (ParseException e) {
			System.out.println("Date parse error!");
			throw e;
		}
		return timeCount;
	}

	
	static public long getDistinceTime(String beforeDateTime)
			throws ParseException {
		return getDistinceTime(beforeDateTime, new Timestamp(System
				.currentTimeMillis())+"");
	}


	static public long getDistinceMinute(String beforeDateTime,
			String afterDateTime) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timeCount = 0;
		try {
			java.util.Date d1 = d.parse(beforeDateTime);
			java.util.Date d2 = d.parse(afterDateTime);

			timeCount = (d2.getTime() - d1.getTime()) / (60 * 1000);

		} catch (ParseException e) {
			System.out.println("Date parse error!");
			throw e;
		}
		return timeCount;
	}


	static public long getDistinceMinute(String afterDateTime)
			throws ParseException {
		return getDistinceMinute(new Timestamp(System.currentTimeMillis())
				+"", afterDateTime);
	}


	static public boolean isOvertime(String beforeDateTime, String timeCount) {
		boolean exceed = false;
		try {
			long count1 = Long.parseLong(timeCount);
			long count2 = getDistinceTime(beforeDateTime);
			if (count1 < count2) {
				exceed = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return exceed;
	}

	static public String getTimestamStr(Timestamp timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(timestamp);
	}

	static public String getTimeStr(Time time) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(time);
	}

	
	static public boolean isBeforeCheckDate(String checkdate,
			java.util.Date auditDate) throws ParseException {
		java.util.Date cd;
		try {
			cd = new java.util.Date(parseDate(checkdate).getTime());

		} catch (ParseException ex) {
			System.out.println(ex);
			return false;
		}
		return isBeforeCheckDate(cd, auditDate);
	}

	static private boolean isBeforeCheckDate(java.util.Date checkdate,
			java.util.Date auditDate) throws ParseException {
		return auditDate.before(checkdate);
	}

	static public java.sql.Date getNextMonthDate(java.sql.Date date)
			throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		return new java.sql.Date(scalendar.getTime().getTime());
	}

	static public String format(Date date, String formatText) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(formatText);
		return format.format(date);
	}

	static public int getDaysOfMonth(Date startdate, Date enddate, String month)
			throws Exception {
		int startmonth = monthOfDate(startdate);
		int endmonth = monthOfDate(enddate);
		int m = Integer.parseInt(month);
		int day = dayOfDate(getLastDay(String.valueOf(yearOfDate(startdate)), month));
		if ((startmonth < m) && (m < endmonth)) {
			return day;
		} else if (m == startmonth) {
			return day - dayOfDate(startdate);
		} else if (m == endmonth) {
			return dayOfDate(enddate);
		}
		return 0;
	}

	   /**
     * Base ISO 8601 Date format yyyyMMdd i.e., 20021225 for the 25th day of December in the year 2002
     */
    public static final String ISO_DATE_FORMAT = "yyyyMMdd";

    /**
     * Expanded ISO 8601 Date format yyyy-MM-dd i.e., 2002-12-25 for the 25th day of December in the year 2002
     */
    public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd hh:mm:ss
     */
    public static String DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

    /**
     * Default lenient setting for getDate.
     */
    private static boolean LENIENT_DATE = false;

    /**
     * ��ʱ����
     * @param JD
     * @return
     */
    protected static final float normalizedJulian(float JD)
    {

        float f = Math.round(JD + 0.5f) - 0.5f;

        return f;
    }

    /**
     * ����ֵת�������ڸ�ʽ<br>
     * ��ʱ����
     * Returns the Date from a julian. The Julian date will be converted to noon GMT,
     * such that it matches the nearest half-integer (i.e., a julian date of 1.4 gets
     * changed to 1.5, and 0.9 gets changed to 0.5.)
     *
     * @param JD the Julian date
     * @return the Gregorian date
     */
    public static final Date toDate(float JD)
    {

        /* To convert a Julian Day Number to a Gregorian date, assume that it is for 0 hours, Greenwich time (so
         * that it ends in 0.5). Do the following calculations, again dropping the fractional part of all
         * multiplicatons and divisions. Note: This method will not give dates accurately on the
         * Gregorian Proleptic Calendar, i.e., the calendar you get by extending the Gregorian
         * calendar backwards to years earlier than 1582. using the Gregorian leap year
         * rules. In particular, the method fails if Y<400. */
        float Z = (normalizedJulian(JD)) + 0.5f;
        float W = (int) ( (Z - 1867216.25f) / 36524.25f);
        float X = (int) (W / 4f);
        float A = Z + 1 + W - X;
        float B = A + 1524;
        float C = (int) ( (B - 122.1) / 365.25);
        float D = (int) (365.25f * C);
        float E = (int) ( (B - D) / 30.6001);
        float F = (int) (30.6001f * E);
        int day = (int) (B - D - F);
        int month = (int) (E - 1);

        if (month > 12)
        {
            month = month - 12;
        }

        int year = (int) (C - 4715); //(if Month is January or February) or C-4716 (otherwise)

        if (month > 2)
        {
            year--;
        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1); // damn 0 offsets
        c.set(Calendar.DATE, day);

        return c.getTime();
    }

    /**
     * Returns the days between two dates. Positive values indicate that
     * the second date is after the first, and negative values indicate, well,
     * the opposite. Relying on specific times is problematic.
     *
     * @param early the "first date"
     * @param late the "second date"
     * @return the days between the two dates
     */
    public static final int daysBetween(Date early, Date late)
    {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(early);
        c2.setTime(late);

        return daysBetween(c1, c2);
    }

    /**
     * Returns the days between two dates. Positive values indicate that
     * the second date is after the first, and negative values indicate, well,
     * the opposite.
     *
     * @param early
     * @param late
     * @return the days between two dates.
     */
    public static final int daysBetween(Calendar early, Calendar late)
    {

        return (int) (toJulian(late) - toJulian(early));
    }

    /**
     * Return a Julian date based on the input parameter. This is
     * based from calculations found at
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day Calculations
     * (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     * @param c a calendar instance
     * @return the julian day number
     */
    public static final float toJulian(Calendar c)
    {

        int Y = c.get(Calendar.YEAR);
        int M = c.get(Calendar.MONTH);
        int D = c.get(Calendar.DATE);
        int A = Y / 100;
        int B = A / 4;
        int C = 2 - A + B;
        float E = (int) (365.25f * (Y + 4716));
        float F = (int) (30.6001f * (M + 1));
        float JD = C + D + E + F - 1524.5f;

        return JD;
    }

    /**
     * ��ʱ����
     * Return a Julian date based on the input parameter. This is
     * based from calculations found at
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day Calculations
     * (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     * @param date
     * @return the julian day number
     */
    public static final float toJulian(Date date)
    {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return toJulian(c);
    }

    /**
     * �������
     * @param isoString �����ַ�
     * @param fmt ��ʽ
     * @param field ��/��/�� Calendar.YEAR/Calendar.MONTH/Calendar.DATE
     * @param amount �����
     * @return
     * @throws ParseException
     */
    public static final String dateIncrease(String isoString, String fmt, int field, int amount)
    {

        try
        {
            Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
                "GMT"));
            cal.setTime(stringToDate(isoString, fmt, true));
            cal.add(field, amount);

            return dateToString(cal.getTime(), fmt);

        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /**
     * Time Field Rolling function.
     * Rolls (up/down) a single unit of time on the given time field.
     *
     * @param isoString
     * @param field the time field.
     * @param up Indicates if rolling up or rolling down the field value.
     * @param expanded use formating char's
     * @exception ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, String fmt, int field, boolean up)
        throws ParseException
    {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
            "GMT"));
        cal.setTime(stringToDate(isoString, fmt));
        cal.roll(field, up);

        return dateToString(cal.getTime(), fmt);
    }

    /**
     * Time Field Rolling function.
     * Rolls (up/down) a single unit of time on the given time field.
     *
     * @param isoString
     * @param field the time field.
     * @param up Indicates if rolling up or rolling down the field value.
     * @exception ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, int field, boolean up)
        throws ParseException
    {

        return roll(isoString, DATETIME_PATTERN, field, up);
    }

    /**
     * �ַ�ת��Ϊ����java.util.Date
     * @param dateText �ַ�
     * @param format ���ڸ�ʽ
     * @param lenient ����Խ���־
     * @return
     */
    public static Date stringToDate(String dateText, String format, boolean lenient)
    {

        if (dateText == null)
        {

            return null;
        }

        DateFormat df = null;

        try
        {

            if (format == null)
            {
                df = new SimpleDateFormat();
            }
            else
            {
                df = new SimpleDateFormat(format);
            }

            // setLenient avoids allowing dates like 9/32/2001
            // which would otherwise parse to 10/2/2001
            df.setLenient(false);

            return df.parse(dateText);
        }
        catch (ParseException e)
        {

            return null;
        }
    }

    /**
     * �ַ�ת��Ϊ����java.util.Date
     * @param dateText �ַ�
     * @param format ���ڸ�ʽ
     * @return
     */
    public static Date stringToDate(String dateString, String format)
    {

        return stringToDate(dateString, format, LENIENT_DATE);
    }

    /**
     * �ַ�ת��Ϊ����java.util.Date
     * @param dateText �ַ�
     */
    public static Date stringToDate(String dateString)
    {

        return stringToDate(dateString, ISO_EXPANDED_DATE_FORMAT, LENIENT_DATE);
    }

    /** ���ʱ�������ʱ���ַ�
     * @return ����ʱ���ַ�
     * @param pattern ʱ���ַ���ʽ
     * @param date ʱ���
     */
    public static String dateToString(Date date, String pattern)
    {

        if (date == null)
        {

            return null;
        }

        try
        {

            SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
            sfDate.setLenient(false);

            return sfDate.format(date);
        }
        catch (Exception e)
        {

            return null;
        }
    }

    /**
     * ���ʱ�������ʱ���ַ� yyyy-MM-dd
     * @param date
     * @return
     */
    public static String dateToString(Date date)
    {
        return dateToString(date, ISO_EXPANDED_DATE_FORMAT);
    }

    /** ���ص�ǰʱ��
     * @return ���ص�ǰʱ��
     */
    public static Date getCurrentDateTime()
    {
        java.util.Calendar calNow = java.util.Calendar.getInstance();
        java.util.Date dtNow = calNow.getTime();

        return dtNow;
    }

    /**
     * ���ص�ǰ�����ַ�
     * @param pattern �����ַ���ʽ
     * @return
     */
    public static String getCurrentDateString(String pattern)
    {
        return dateToString(getCurrentDateTime(), pattern);
    }

    /**
     * ���ص�ǰ�����ַ� yyyy-MM-dd
     * @return
     */
    public static String getCurrentDateString()
    {
        return dateToString(getCurrentDateTime(), ISO_EXPANDED_DATE_FORMAT);
    }

    /**
     * ���ص�ǰ����+ʱ���ַ� yyyy-MM-dd hh:mm:ss
     * @param date
     * @return
     */
    public static String dateToStringWithTime(Date date)
    {

        return dateToString(date, DATETIME_PATTERN);
    }

    /**
     * �������-�������
     * @param date
     * @param days
     * @return java.util.Date
     */
    public static Date dateIncreaseByDay(Date date, int days)
    {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
            "GMT"));
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    /**
     * �������-�������
     * @param date
     * @param days
     * @return java.util.Date
     */
    public static Date dateIncreaseByMonth(Date date, int mnt)
    {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
            "GMT"));
        cal.setTime(date);
        cal.add(Calendar.MONTH, mnt);

        return cal.getTime();
    }
    /**
     * �������-�������
     * @param date
     * @param mnt
     * @return java.util.Date
     */
    public static Date dateIncreaseByYear(Date date, int mnt)
    {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
            "GMT"));
        cal.setTime(date);
        cal.add(Calendar.YEAR, mnt);

        return cal.getTime();
    }
    /**
     * �������
     * @param date �����ַ� yyyy-MM-dd
     * @param days
     * @return �����ַ� yyyy-MM-dd
     */
    public static String dateIncreaseByDay(String date, int days)
    {
        return dateIncreaseByDay(date, ISO_DATE_FORMAT, days);
    }

    /**
     * �������
     * @param date �����ַ�
     * @param fmt ���ڸ�ʽ
     * @param days
     * @return
     */
    public static String dateIncreaseByDay(String date, String fmt, int days)
    {
        return dateIncrease(date, fmt, Calendar.DATE, days);
    }

    /**
     * �����ַ��ʽת��
     * @param src �����ַ�
     * @param srcfmt Դ���ڸ�ʽ
     * @param desfmt Ŀ�����ڸ�ʽ
     * @return
     */
    public static String stringToString(String src, String srcfmt, String desfmt)
    {
        return dateToString(stringToDate(src, srcfmt), desfmt);
    }
	
	public static void main(String[] args) {
		try {
			Date td = new Date();
			Date od = parseDate("2007-10-22");
//			System.out.println(yearOfDate(td));
//			System.out.println(monthOfDate(td));
//			System.out.println(dayOfDate(td));
//			System.out.println(diffDateM(td,od));
//			System.out.println(diffDateD(td,od));
//			System.out.println(diffDateM(2007,2008));
//			System.out.println(getDateStr(getFirstDay(2007,12)));
//			System.out.println(getDateStr(getLastDay(2007,11)));
//			System.out.println(getThisYearMonth());
//			System.out.println(getYearMonth(td));
//			System.out.println(getDistinceMonth("2007-10-11","2007-12-08"));
//			System.out.println(getDistinceDay("2007-10-31","2007-11-1"));
//			System.out.println(getDaysOfMonth(td,od,"11"));
//			System.out.println(isBeforeCheckDate(td,od));
//			System.out.println(hoursOfDate(td) + ":" + minuteOfDate(td) + ":" + secondeOfDate(td));
			String s = format(td,"yyyy年MM月dd日");
			System.out.println("s=" + s);
			System.out.println(getTodayStr());
			String a = "2017-05-25T23:36:05";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
}
