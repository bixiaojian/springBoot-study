package com.pancm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @{PACKAGE_NAME :
 * @{Description :
 * @{Author :Li Jia Ze
 * @{DATE :2017/9/28 10:12
 */
public class DateUtil {

  /**
   * 缺省日期格式 yyyy/MM/dd
   */
  public static final String DEFAULT_DATE_FMT = "yyyy/MM/dd";
  /**
   * 缺省日期格式 yyyy-MM-dd
   */
  public static final String DEFAULT_DATE_FMT_2 = "yyyy-MM-dd";
  /**
   * 缺省时间格式 yyyy/MM/dd HH:mm:ss
   */
  public static final String DEFAULT_TIME_FMT = "yyyy/MM/dd HH:mm:ss";
  /**
   * 缺省时间格式 yyyy-MM-dd HH:mm:ss
   */
  public static final String SQL_TIME_FMT = "yyyy-MM-dd HH:mm:ss";
  /**
   * 当天的最小时间 yyyy-MM-dd 00:00:00
   **/
  public static final String SQL_TIME_FMT_MIN = "yyyy-MM-dd 00:00:00";
  /**
   * 当天的最大时间 yyyy-MM-dd 23:59:59
   **/
  public static final String SQL_TIME_FMT_MAX = "yyyy-MM-dd 23:59:59";
  /**
   * 页面上到小时的日期格式 yyyy-MM-dd HH
   */
  public static final String YYMMDDHHFMT = "yyyy-MM-dd HH";
  /**
   * 页面上到小时分钟的日期格式 yyyy-MM-dd HH:mm
   */
  public static final String YYMMDDHHMMFMT = "yyyy-MM-dd HH:mm";
  /**
   * 页面上到天的日期格式 yyyy-MM-dd
   */
  public static final String YYYYMMDDFMT = DEFAULT_DATE_FMT_2;
  /**
   * 日期格式 MMDD
   */
  public static final String MMDDFMT = "MM-dd";
  /**
   * 日期时间格式 yyyyMMddHHmmss
   */
  public static final String YYYYMMDDHHmmss = "yyyyMMddHHmmss";
  public static final String YYYYMMDDHHmmssSSS = "yyyyMMddHHmmssSSS";
  /**
   * 日期格式 yyyyMMddHHmm
   */
  public static final String YYYYMMDDHHmm = "yyyyMMddHHmm";
  /**
   * 日期格式 yyyyMMddHH
   */
  public static final String YYYYMMDDHH = "yyyyMMddHH";
  /**
   * 日期格式 yyyyMMdd
   */
  public static final String YYYYMMDD = "yyyyMMdd";
  /**
   * 日期格式 yyyyMM
   */
  public static final String YYYYMM = "yyyyMM";
  /**
   * 日期格式 yyyy
   */
  public static final String YYYY = "yyyy";
  /**
   * 日期时间格式 HHmmss
   */
  public static final String HHmmss = "HHmmss";
  /**
   * 北京时区
   */
  public static final TimeZone timeZoneBeijing = TimeZone.getTimeZone("Asia/Shanghai");
  /**
   * 一小时的毫秒数
   */
  public static final long HOUR_MICRO_SECONDS = 3600L * 1000L;
  /**
   * 整年天数
   */
  public static final long DAYS_OF_YEAR = 365;
  public static final String CHINESE_DATE_FMT = "yyyy年MM月dd日 HH时mm分";
  public static final String CHINESE_DATE_FMT2 = "yyyy年MM月dd日 HH:mm";
  public static final String CHINESEALL_DATE_FMT = "yyyy年MM月dd日 HH时mm分ss秒";
  private final static Logger log = LoggerFactory.getLogger(DateUtil.class);
  /**
   * 全部时区名字
   */
  private static final List<String> TimeZoneIds = Arrays.asList(TimeZone.getAvailableIDs());
  /**
   * 自定义时区缓存
   */
  private static final Map<String, TimeZone> TimeZoneCache = new HashMap<>();

  private DateUtil() {

  }

  /**
   * 转换日期毫秒数为缺省日期格式字符串
   */
  public static String Date2String(long date) {
    return Date2String(new Date(date), DEFAULT_DATE_FMT, null);
  }

  /**
   * 转换日期毫秒数为缺省日期格式字符串
   */
  public static String Date2String(long date, TimeZone timeZone) {
    return Date2String(new Date(date), DEFAULT_DATE_FMT, timeZone);
  }

  /**
   * 转换日期为缺 省日期格式字符串
   */
  public static String Date2String(Date date) {
    return Date2String(date, DEFAULT_DATE_FMT, null);
  }

  /**
   * 转换日期为缺省日期格式字符串
   */
  public static String Date2String(Date date, TimeZone timeZone) {
    return Date2String(date, DEFAULT_DATE_FMT, timeZone);
  }

  /**
   * 返回整形类型的当前时间 格式为 YYYYMMDDHHmmssSSS
   */
  public static Long nowTimeWithLong() {
    return Long.parseLong(Date2String(new Date(System.currentTimeMillis()), YYYYMMDDHHmmssSSS));
  }

  /**
   * 转换日期毫秒数为缺省日期格式字符串
   */
  public static String Time2String(long date) {
    return Date2String(new Date(date), DEFAULT_TIME_FMT, null);
  }

  /**
   * 转换日期毫秒数为缺省日期格式字符串
   */
  public static String Time2StringFormat(long date) {
    return Date2String(new Date(date), YYYYMMDD, null);
  }

  /**
   * 转换日期毫秒数为缺省日期格式字符串
   */
  public static String Time2StringFormat(long date, String format) {
    return Date2String(new Date(date), format, null);
  }

  /**
   * 转换日期毫秒数为缺省日期格式字符串
   */
  public static String Time2String(long date, TimeZone timeZone) {
    return Date2String(new Date(date), DEFAULT_TIME_FMT, timeZone);
  }

  /**
   * 转换日期为缺省日期格式字符串
   */
  public static String Time2String(Date date) {
    return Date2String(date, DEFAULT_TIME_FMT, null);
  }

  /**
   * 转换日期为缺省日期格式字符串
   */
  public static String Time2String(Date date, TimeZone timeZone) {
    return Date2String(date, DEFAULT_TIME_FMT, timeZone);
  }

  /**
   * 转换日期为指定格式字符串
   */
  public static String Date2String(Date date, String format) {
    return Date2String(date, format, null);

  }

  /**
   * 转换日期为指定格式字符串
   */
  public static String Date2String(Date date, String format, TimeZone timeZone) {
    if (date == null || format == null)
      return null;
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    if (timeZone != null)
      sdf.setTimeZone(timeZone);
    return sdf.format(date);

  }

  /**
   * 解析日期时间字符串,支持 yyMMdd,yyyyMMdd, yyyy-MM-dd, yyyy/MM/dd, yyyyMMddHHmm, yyyyMMddHHmmss,
   * yyyyMMddHHmmssSSS, yyyy-MM-dd HH:mm:ss, yyyy-MM-dd HH:mm:ss.SSS 格式, 其它方式结果不保证正确
   *
   * @return date
   */
  public static Date String2Date(String str) {
    return String2Date(str, (TimeZone) null);
  }

  /**
   * 解析日期时间字符串,支持 yyMMdd,yyyyMMdd, yyyy-MM-dd, yyyy/MM/dd, yyyyMMddHHmm, yyyyMMddHHmmss,
   * yyyyMMddHHmmssSSS, yyyy-MM-dd HH:mm:ss, yyyy-MM-dd HH:mm:ss.SSS 格式, 其它方式结果不保证正确
   *
   * @return date
   */
  public static Date String2Date(String strDate, TimeZone timeZone) {
    if (strDate == null)
      return null;
    String str = strDate.trim();
    if (str.length() == 6)
      return String2Date(str, "yyMMdd", timeZone);
    if (str.length() == 8)
      return String2Date(str, "yyyyMMdd", timeZone);
    if (str.length() == 10) {
      if (str.indexOf('-') != -1)
        return String2Date(str, DEFAULT_DATE_FMT_2, timeZone);

      if (str.indexOf('/') != -1)
        return String2Date(str, "yyyy/MM/dd", timeZone);
    }
    if (str.length() == 12)
      return String2Date(str, "yyyyMMddHHmm", timeZone);
    if (str.length() == 14)
      return String2Date(str, "yyyyMMddHHmmss", timeZone);
    if (str.length() == 17)
      return String2Date(str, "yyyyMMddHHmmssSSS", timeZone);
    if (str.length() == 19) {
      if (str.indexOf('-') != -1)
        return String2Date(str, SQL_TIME_FMT, timeZone);
      if (str.indexOf('/') != -1)
        return String2Date(str, DEFAULT_TIME_FMT, timeZone);
    }
    if (str.length() == 23) {
      if (str.indexOf('-') != -1)
        return String2Date(str, "yyyy-MM-dd HH:mm:ss.SSS", timeZone);
      if (str.indexOf('/') != -1)
        return String2Date(str, "yyyy/MM/dd HH:mm:ss.SSS", timeZone);
    }
    if (str.length() == 21) {
      if (str.indexOf('-') != -1)
        return String2Date(str, SQL_TIME_FMT, timeZone);
      if (str.indexOf('/') != -1)
        return String2Date(str, DEFAULT_TIME_FMT, timeZone);
    }
    try {
      return new SimpleDateFormat().parse(str);
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
      return null;
      // throw new BfwRuntimeException("validation.date.parse_error");
    }
  }

  /**
   * 按指定方式解析日期时间
   */
  public static Date String2Date(String str, String format) {
    return String2Date(str, format, null);
  }

  /**
   * 按指定方式解析日期时间
   */
  public static Date String2Date(String str, String format, TimeZone timeZone) {
    if (StringUtils.isEmpty(str)) {
      return null;
    }

    String strFormat = DEFAULT_DATE_FMT;

    if (format != null) {
      strFormat = format;
    }
    SimpleDateFormat fmt = new SimpleDateFormat(strFormat);
    if (timeZone != null)
      fmt.setTimeZone(timeZone);
    try {
      return fmt.parse(str);
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
      return null;// throw new
      // BfwRuntimeException("validation.date.parse_error",
      // e);
    }
  }

  /**
   * 比较两个日期是否是一天(不考虑时间)
   */
  public static boolean isDateEqual(Date date1, Date date2) {
    return truncDate(date1).equals(truncDate(date2));
  }

  /**
   * 比较date1是否是date2后的一个日期,或相等
   */
  public static boolean isDateAfter(Date date1, Date date2) {
    return truncDate(date1).equals(truncDate(date2)) || truncDate(date1).after(truncDate(date2));

  }

  /**
   * 比较两个日期是否是一天(不考虑时间)
   */
  public static boolean isDateEqual(Date date1, Date date2, TimeZone timeZone) {
    return truncDate(date1, timeZone).equals(truncDate(date2, timeZone));
  }

  /**
   * 返回某日零时整
   */
  public static Date truncDate(Date date) {
    return truncDate(date, Calendar.DATE);
  }

  /**
   * 返回某日零时整
   */
  public static Date truncDate(Date date, TimeZone timeZone) {
    return truncDate(date, Calendar.DATE, timeZone);
  }

  /**
   * 日期时间取整，支持年、月、周、日、时、分、秒
   *
   * @return date
   */
  public static Date truncDate(Date date, int mode) {
    return truncDate(date, mode, null);
  }

  /**
   * 日期时间取整，支持年、月、周、日、时、分、秒
   *
   * @return date
   */
  public static Date truncDate(Date date, int mode, TimeZone timeZone) {
    if (date == null)
      return null;
    Calendar cal = (timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone));
    cal.setTime(date);
    switch (mode) {
      case Calendar.YEAR:
        cal.clear(Calendar.MONTH);
      case Calendar.MONTH:
      case Calendar.WEEK_OF_MONTH:
        if (mode == Calendar.MONTH)
          cal.set(Calendar.DAY_OF_MONTH, 1);
        else
          cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
      case Calendar.DATE:
        cal.set(Calendar.HOUR_OF_DAY, 0);
      case Calendar.HOUR:
        cal.clear(Calendar.MINUTE);
      case Calendar.MINUTE:
        cal.clear(Calendar.SECOND);
      case Calendar.SECOND:
        cal.clear(Calendar.MILLISECOND);
        break;
      default:
        throw new IllegalArgumentException();
    }
    return cal.getTime();
  }

  /**
   * 循环调整时间
   */
  public static Date rollDate(Date date, int field, int amount) {
    return rollDate(date, field, amount, null);
  }

  /**
   * 循环调整时间
   */
  public static Date rollDate(Date date, int field, int amount, TimeZone timeZone) {
    Calendar cal = (timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone));
    cal.setTime(date);
    cal.roll(field, amount);
    return cal.getTime();
  }

  /**
   * 调整时间
   */
  public static Date addDate(Date date, int field, int amount) {
    return addDate(date, field, amount, null);
  }

  /**
   * 调整时间
   */
  public static Date addDate(Date date, int field, int amount, TimeZone timeZone) {
    Calendar cal = (timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone));
    cal.setTime(date);
    cal.add(field, amount);
    return cal.getTime();
  }

  /**
   * 比较2个同时区时间先后，注意:时间的格式必须在String2Date支持的格式范围内
   *
   * @param date1 时间1
   * @param date2 时间2
   * @return 如果时间1等于时间2，返回0，如果时间1小于时间2，返回负值，如果时间1大于时间2，返回正值
   */
  public static int compare(String date1, String date2) {
    return String2Date(date1).compareTo(String2Date(date2));
  }

  /**
   * 比较2个时间先后
   *
   * @param date1 时间1
   * @param date2 时间2
   * @return 如果时间1等于时间2，返回0，如果时间1小于时间2，返回负值，如果时间1大于时间2，返回正值
   */
  public static int compare(Date date1, Date date2) {
    return date1.compareTo(date2);
  }

  /**
   * 查询时区
   */
  public static TimeZone findTimeZone(int timediff) {
    String[] ids =
        TimeZone.getAvailableIDs(timediff * 60000
            + TimeZone.getDefault().getOffset(System.currentTimeMillis()));
    if (ids == null)
      return new SimpleTimeZone(timediff * 60000, "UDT");
    return TimeZone.getTimeZone(ids[0]);
  }

  /**
   * 根据时区名字取得时区 如果非java已知标准名字，则必须为 GMT[+-]hh:mm 格式
   */
  public static TimeZone getTimeZone(String id) {
    if (id == null) {
      return null;
    }

    if (TimeZoneIds.contains(id)) {
      return TimeZone.getTimeZone(id);
    }

    if (TimeZoneCache.containsKey(id)) {
      return TimeZoneCache.get(id);
    }

    Pattern p = Pattern.compile("^GMT[+-](0[0-9]|1[01]):([0-5][0-9])$");
    Matcher m = p.matcher("id");
    if (!m.matches())
      return null;
    int hh = Integer.parseInt(id.substring(4, 6));
    int mm = Integer.parseInt(id.substring(7));
    int sign = (id.charAt(3) == '-' ? -1 : 1);
    TimeZone tz = new SimpleTimeZone((hh * 60 + mm) * 60000 * sign, id);
    TimeZoneCache.put(id, tz);
    return tz;
  }

  public static Timestamp string2TimeStamp(Object millions, Object nanos) {
    try {
      Timestamp res = new Timestamp(Long.parseLong((String) millions));
      res.setNanos(Integer.parseInt((String) nanos));

      return res;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }

  }

  /**
   * 把Date转为Timestamp
   */
  public static Timestamp date2Timestamp(Date adate) {
    return new Timestamp(adate.getTime());
  }

  /**
   * <p>
   * 把用户当地时间转成网银时间。
   * </p>
   *
   * @param date 待转换的时间。
   * @param dest 用户所在时区。
   * @return 转换后的时间。
   */
  public static Date transformDateFrom(Date date, TimeZone dest) {

    int offset = dest.getOffset(date.getTime()) - timeZoneBeijing.getOffset(date.getTime());
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(date.getTime() - offset);
    return cal.getTime();
  }

  /**
   * <p>
   * 把网银时间转成用户当地时间。
   * </p>
   *
   * @param date 待转换的时间。
   * @param dest 用户所在时区。
   * @return 转换后的时间。
   */
  public static Date transformDateInto(Date date, TimeZone dest) {
    long offset = (long) dest.getOffset(date.getTime()) - timeZoneBeijing.getOffset(date.getTime());
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(date.getTime() + offset);
    return cal.getTime();
  }

  /**
   * 校验起始日期和结束日期的合法性
   * <p>
   * 例如：起始日期距当前日期不超过12个月，起始结束日期间隔不超过3个月，调用<br>
   * validateDateRange(startDate, endDate, currentDate, 3, 12)
   *
   * @param startDate 起始日期
   * @param endDate 结束日期
   * @param currentDate 当前日期
   * @param maxInterval 起始日期和结束日期的最大距离（单位为月）
   * @param amount 起始日期和当前日期的最大距离（单位为月）
   */
  public static boolean validateDateRange(Date startDate, Date endDate, Date currentDate,
      int maxInterval, int amount) {
    if (startDate.after(endDate))
      return false;

    if (currentDate.after(addDate(startDate, Calendar.MONTH, amount)))
      return false;

    if (endDate.after(addDate(startDate, Calendar.MONTH, maxInterval)))
      return false;

    return true;
  }

  /**
   * 比较2个同时区时间先后，注意:时间的格式必须在String2Date支持的格式范围内 yuef lyj
   *
   * @param date1 时间1
   * @param date2 时间2
   * @return 如果时间1等于时间2，返回0，如果时间1小于时间2，返回负值，如果时间1大于时间2，返回正值
   */
  public static int compareMonth(String date1, String date2, TimeZone timeZone) {
    Date start = String2Date(date1);
    // Date end = String2Date(date2);
    // if (date == null)
    // return null;
    Calendar cal = (timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone));
    cal.setTime(start);
    // int ss = cal.get(Calendar.MONTH)+1;
    return String2Date(date1).compareTo(String2Date(date2));
  }

  /**
   * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式
   * @param date2 被比较的时间 为空(null)则为当前时间
   * @param stype 返回值类型 0为多少天，1为多少个月，2为多少年
   */
  public static int compareDate(String date1, String date2, int stype) {
    int n = 0;

    // String[] u = {"天","月","年"};
    String formatStyle = stype == 1 ? "yyyy-MM" : DEFAULT_DATE_FMT_2;

    // date2 = date2==null?DateTest.getCurrentDate():date2;

    DateFormat df = new SimpleDateFormat(formatStyle);
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    try {
      c1.setTime(df.parse(date1));
      c2.setTime(df.parse(date2));
    } catch (Exception e3) {
      log.error(e3.getMessage(), e3);
    }
    // List list = new ArrayList();
    while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
      // list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
      n++;
      if (stype == 1) {
        c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
      } else {
        c1.add(Calendar.DATE, 1); // 比较天数，日期+1
      }
    }

    n = n - 1;

    if (stype == 2) {
      n = n / 365;
    }

    // System.out.println(date1+" -- "+date2+" 相差多少"+u[stype]+":"+n);
    return n;
  }

  /**
   * 转换成sqldate
   */
  public static java.sql.Date toSqlDate(Date date) {
    if (date == null) {
      return null;
    }
    return new java.sql.Date(date.getTime());
  }

  /**
   * 转换成utildate
   */
  public static Date toUtildate(java.sql.Date date) {
    if (date == null) {
      return null;
    }
    return new Date(date.getTime());
  }

  /**
   * 获取两日期之间相隔的小时数(不足一小时算一小时)
   */
  public static int getHoursBetween(Date start, Date end) {
    long s = start.getTime();
    long e = end.getTime();
    long k = e - s;
    if (k % HOUR_MICRO_SECONDS == 0) {
      return (int) (k / HOUR_MICRO_SECONDS);
    } else {
      return (int) (k / HOUR_MICRO_SECONDS) + 1;
    }
  }

  /**
   * 固定类产品进入操作期专用 获取两日期之间相隔的小时数(不足一小时舍去)
   */
  public static int getOperFundHours(Date start, Date end) {
    long s = start.getTime();
    long e = end.getTime();
    long k = e - s;

    return (int) (k / HOUR_MICRO_SECONDS);
  }

  /**
   * 获取当天的最小时间 2012-11-14-->2012-11-14 00:00:00
   */
  public static Date getMinTimeofDay(String str, String format) {
    Date date = String2Date(str, format);
    return getMinTimeofDay(date);
  }

  /**
   * 获取当天的最小时间 2012-11-14-->2012-11-14 00:00:00
   */
  public static Date getMinTimeofDay(Date date) {
    if (date == null) {
      return null;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(date.getTime());
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  /**
   * 取得days天后的日期
   */
  public static Date getDateNextdays(int days) {
    return new Date(System.currentTimeMillis() + (HOUR_MICRO_SECONDS * 24l * days));
  }

  /**
   * 取得days天后的日期
   */
  public static Date getDateNextdays(Date date, int days) {
    return new Date(date.getTime() + (HOUR_MICRO_SECONDS * 24l * days));
  }

  /**
   * 获取当天的最大时间 2012-11-14-->2012-11-14 23:59:59
   */
  public static Date getMaxTimeofDay(String str, String format) {
    Date date = String2Date(str, format);
    return getMaxTimeofDay(date);
  }

  /**
   * 获取当天的最大时间 2012-11-14-->2012-11-14 23:59:59
   */
  public static Date getMaxTimeofDay(Date date) {
    if (date == null) {
      return null;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(date.getTime());
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
  }

  /**
   * 获取当月最大的天 2012-11-14-->2012-11-14 23:59:59
   */
  public static Date getMaxDayOfMonth(Date date) {
    if (date == null) {
      return null;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(date.getTime());
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
  }

  /**
   * 获取前 month 个月的最大时间和最小时间
   *
   * @return Map
   * @author
   */
  public static Map<String, String> getDateMap(int month) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SQL_TIME_FMT);
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - month);
    Date date = calendar.getTime();
    // 得到前month个月的最大时间
    calendar.setTimeInMillis(date.getTime());
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    // 得到前month个月的最小时间
    Calendar calendar1 = Calendar.getInstance();
    calendar1.setTimeInMillis(date.getTime());
    calendar1.set(Calendar.DAY_OF_MONTH, calendar1.getActualMinimum(Calendar.DAY_OF_MONTH));
    calendar1.set(Calendar.HOUR_OF_DAY, 0);
    calendar1.set(Calendar.MINUTE, 0);
    calendar1.set(Calendar.SECOND, 0);
    calendar1.set(Calendar.MILLISECOND, 0);
    Map<String, String> map = new HashMap<>();
    map.put("endDate", simpleDateFormat.format(calendar.getTime()));
    map.put("startDate", simpleDateFormat.format(calendar1.getTime()));
    return map;
  }

  /**
   * 判断当前日是不是周六或周日
   *
   * @author
   */
  public static boolean isWeekend(Date date) throws ParseException {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
        || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;

  }

  // 获取一个月后当天的最大时间 wuyue 2012-11-14 23:59:59
  public static Date getNextMothDate(int month) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SQL_TIME_FMT);
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
    Date date = calendar.getTime();
    // 得到下month个月的最大时间
    calendar.setTimeInMillis(date.getTime());
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    return simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));
  }

  // 根据当前时间获取days天前的时间
  public static Date getLastDays(int days) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FMT_2);
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -days);
    return simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));
  }

  // 根据传入的日期获取days天前的时间
  public static Date getLastDays(Date date, int days) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FMT_2);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, -days);
    return simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));
  }

  // 比较两个日期的相差天数，不满一天算一天
  public static int daysOfTwo(Date fDate, Date oDate) {

    Calendar aCalendar = Calendar.getInstance();
    aCalendar.setTime(fDate);
    int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
    aCalendar.setTime(oDate);
    int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
    if (day2 == day1) {
      return 1;
    } else {
      return day2 - day1;
    }
  }

  /**
   * 判断当前日是不是周六
   *
   * @author
   */
  public static boolean isWeekSAT(Date date) throws ParseException {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;

  }

  /**
   * 判断当前日是不是周一
   *
   * @author
   */
  public static boolean isWeekMONDAY(Date date) throws ParseException {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
  }

  /**
   * 判断当前日是不是周日
   *
   * @author
   */
  public static boolean isWeekSUN(Date date) throws ParseException {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
  }

  /**
   * 获取当前时间的天数，不足24小时算一天
   */
  public static int getHorsToDay(int hours) {
    if (hours == 0 || hours % 24 != 0) {
      return (hours / 24) + 1;
    } else {
      return hours / 24;
    }
  }

  /**
   * 获取指定时间加上指定小时的时间
   */
  public static Date dateAddHours(Date planDate, int hours) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(planDate);
    cal.add(Calendar.HOUR_OF_DAY, hours);

    return cal.getTime();
  }



  /**
   * 日期字符串格式化
   *
   * @author: Gao Peng
   * @date: 2016年3月23日 上午10:17:19
   * @param: @param str
   * @param: @return
   * @return: String
   */
  public static String formatString2String(String str, String format) {
    Date d1 = String2Date(str);
    return Date2String(d1, format);
  }

  /**
   * 计算得到MongoDB存储的日期，（默认情况下mongo中存储的是标准的时间，中国时间是东八区，存在mongo中少8小时，所以增加8小时）
   * http://www.iteye.com/problems/88507
   *
   * @date: 2016年5月4日 上午9:26:23
   * @param: @param date
   * @param: @return
   * @return: Date
   */
  public static Date getMongoDate(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat(SQL_TIME_FMT);
    Calendar ca = Calendar.getInstance();
    ca.setTime(date);
    ca.add(Calendar.HOUR_OF_DAY, 8);
    return String2Date(sdf.format(ca.getTime()));
  }

  /**
   * @author GaoPeng
   */
  public static Date getMongoDateSub(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat(SQL_TIME_FMT);
    Calendar ca = Calendar.getInstance();
    ca.setTime(date);
    ca.add(Calendar.HOUR_OF_DAY, -8);
    return String2Date(sdf.format(ca.getTime()));
  }

  /**
   * 格式化结果为 *年* 天
   *
   * @author GaoPeng
   */
  public static String formatNYR(String startDate) {
    SimpleDateFormat dfs = new SimpleDateFormat(DEFAULT_DATE_FMT_2);
    long between = 0;
    try {
      Date begin = dfs.parse(startDate);
      Date end = dfs.parse(Date2String(new Date(), DEFAULT_DATE_FMT_2));
      between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
    } catch (Exception ex) {
      log.error("formatNYR error:", ex);
    }
    long day = (between / (24 * 60 * 60 * 1000));
    long year = day / 365;
    day = day - year * 365;
    return year + "年" + day + "天";
  }

  public static void main(String[] args) {
    // int i = daysBetween(20170101, 20170505);
    // System.out.println(i);
    // long time = 1490716800000L;
    // String s = Time2StringFormat(time, YYYYMMDDHHmmssSSS);
    // Date date = new Date(1490684400000L);
    boolean b = dayCompare("2017-04-02", "2017-02-20");
    System.out.println(b);
  }

  /**
   * 时间格式：yyyy-MM-dd 时间段查询方法 ParamArr[0][0] 起始时间 ParamArr[1][0] 结束时间
   */
  public static HashMap<String, Object> getTime(HashMap<String, Object> paramMap,
      String[][] ParamArr) {
    Date preDateTime = getMinTimeofDay(ParamArr[0][1], DEFAULT_DATE_FMT_2);
    Date afterDateTime = getMaxTimeofDay(ParamArr[1][1], DEFAULT_DATE_FMT_2);
    if (!StringUtils.isEmpty(ParamArr[0][1]) && !StringUtils.isEmpty(ParamArr[1][1])) {
      paramMap.put(ParamArr[0][0], preDateTime);
      paramMap.put(ParamArr[1][0], afterDateTime);
    } else {
      if (!StringUtils.isEmpty(ParamArr[0][1])) {
        paramMap.put(ParamArr[0][0], preDateTime);
      } else {
        paramMap.put(ParamArr[0][0], String2Date("1970-01-01"));
      }
      if (!StringUtils.isEmpty(ParamArr[1][1])) {
        paramMap.put(ParamArr[1][0], afterDateTime);
      } else {
        paramMap.put(ParamArr[1][0], new Date());
      }
    }
    return paramMap;
  }

  /**
   * 计算两个日期之间相差的天数
   *
   * @param smdate 较小的时间
   * @param bdate 较大的时间
   * @return 相差天数
   * @Author:Li Jia Ze
   * @Date:2017年11月23日 09:59:59
   */
  public static int daysBetween(Date smdate, Date bdate) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      smdate = sdf.parse(sdf.format(smdate));
      bdate = sdf.parse(sdf.format(bdate));
      Calendar cal = Calendar.getInstance();
      cal.setTime(smdate);
      long time1 = cal.getTimeInMillis();
      cal.setTime(bdate);
      long time2 = cal.getTimeInMillis();
      long between_days = (time2 - time1) / (1000 * 3600 * 24);

      return Integer.parseInt(String.valueOf(between_days));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return 0;
  }


  /**
   * 字符串的日期格式的计算
   *
   * @param smdate 较小的时间
   * @param bdate 较大的时间
   * @return 相差天数
   * @Author:Li Jia Ze
   * @Date:2017年11月23日 09:59:59
   */
  public static int daysBetween(Integer smdate, Integer bdate) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      Calendar cal = Calendar.getInstance();
      cal.setTime(sdf.parse(DateUtil.formatString2String(String.valueOf(smdate),
          DateUtil.DEFAULT_DATE_FMT_2)));
      long time1 = cal.getTimeInMillis();
      cal.setTime(sdf.parse(DateUtil.formatString2String(String.valueOf(bdate),
          DateUtil.DEFAULT_DATE_FMT_2)));
      long time2 = cal.getTimeInMillis();
      long between_days = (time2 - time1) / (1000 * 3600 * 24);

      return Integer.parseInt(String.valueOf(between_days));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * 指定日期加上天数后的日期
   *
   * @param num 为增加的天数
   * @param newDate 创建时间
   * @Author:Li Jia Ze
   * @Date:2017年11月23日 09:59:59
   */
  public static String plusDay(int num, Integer newDate) {
    try {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date currdate =
          format
              .parse(DateUtil.formatString2String(String.valueOf(newDate), DateUtil.SQL_TIME_FMT));
      log.info("现在的日期是：{}", currdate);
      Calendar ca = Calendar.getInstance();
      ca.add(Calendar.DAY_OF_YEAR, num);// num为增加的天数，可以改变的
      currdate = ca.getTime();
      String enddate = format.format(currdate);
      log.info("增加天数以后的日期：{}", enddate);
      return enddate;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 字符串的日期格式大小比较
   *
   * @param firstDate 较小的时间
   * @param secondDate 较大的时间
   * @Author:Li Jia Ze
   * @Date:2017年11月23日 09:59:59
   */
  public static boolean dayCompare(String firstDate, String secondDate) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      Date currdate = sdf.parse(firstDate);
      Date lastdate = sdf.parse(secondDate);
      if (currdate.before(lastdate)) {
        // 表示bt小于et
        return true;
      } else {
        // 反之
        return false;
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return false;
  }
}
