package com.ruoyi.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * 时间工具类
 * 
 * @author ruoyi
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
    private static final String[] INPUT_FORMATS = {
            "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd",
            "yyyy/MM/dd HH:mm:ss",
            "yyyy年MM月dd日",
            "yyyy年MM月dd日 HH时mm分ss秒",
            "yyyyMMdd", // 新增：支持纯数字格式（如20251203）
            "yyyyMMddHHmmss" // 新增：支持纯数字带时分秒（如20251203153000）
    };
    /**
     * 获取当前Date型日期
     * 
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * 
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算相差天数
     */
    public static int differentDaysByMillisecond(Date date1, Date date2)
    {
        return Math.abs((int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24)));
    }
    /**
     * 从LocalDateTime获取年月（yyyy-MM）
     * @param date 输入的时间
     * @return 年月字符串（如2025-11）
     */
    public static String getYearMonth(Date date) {
        if (date == null) {
            return null; // 或抛异常，根据业务需求处理
        }
        // 步骤：Date -> Instant -> ZonedDateTime -> LocalDate -> 格式化
        LocalDate localDate = date.toInstant() // Date转Instant（UTC时间戳）
                .atZone(ZoneId.systemDefault()) // 关联时区，转换为ZonedDateTime
                .toLocalDate(); // 提取本地日期（忽略时分秒）
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }
    public static String getYearMonth1(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format((java.util.Date) date);
    }
    /**
     * 从LocalDateTime获取年月（yyyy-MM）
     * @param date 输入的时间
     * @return 年月字符串（如2025-11）
     */
    public static String getYear(Date date) {
        if (date == null) {
            return null; // 或抛异常，根据业务需求处理
        }
        // 步骤：Date -> Instant -> ZonedDateTime -> LocalDate -> 格式化
        LocalDate localDate = date.toInstant() // Date转Instant（UTC时间戳）
                .atZone(ZoneId.systemDefault()) // 关联时区，转换为ZonedDateTime
                .toLocalDate(); // 提取本地日期（忽略时分秒）
        return localDate.format(DateTimeFormatter.ofPattern("yyyy"));
    }
    /**
     * 计算时间差
     *
     * @param endDate 最后时间
     * @param startTime 开始时间
     * @return 时间差（天/小时/分钟）
     */
    public static String timeDistance(Date endDate, Date startTime)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startTime.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 增加 LocalDateTime ==> Date
     */
    public static Date toDate(LocalDateTime temporalAccessor)
    {
        ZonedDateTime zdt = temporalAccessor.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 增加 LocalDate ==> Date
     */
    public static Date toDate(LocalDate temporalAccessor)
    {
        LocalDateTime localDateTime = LocalDateTime.of(temporalAccessor, LocalTime.of(0, 0, 0));
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    public static String joinDateRange(Date startTime, Date endTime) {
        return DateUtils.parseDateToStr(YYYY_MM_DD, startTime) + " 至 " + DateUtils.parseDateToStr(YYYY_MM_DD, endTime);
    }
    public static String returnDateRange(Date startTime) {
        return DateUtils.parseDateToStr(YYYY_MM_DD, startTime) ;
    }
    public static String returnDateDay(Date startTime) {
        return DateUtils.parseDateToStr(YYYY_MM, startTime) ;
    }
    public static String returnDateDay(String startTime) {
        // 1. 校验非空（极简必要校验）
        if (startTime == null || startTime.trim().isEmpty()) return null;

        try {
            // 2. 原生SimpleDateFormat：默认使用服务器时区（无需额外配置）
            // 输入格式支持常见的「日期」或「日期+时分秒」格式
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            // 兼容纯日期格式（如 2025-12-03），解析时忽略多余字段
            inputFormat.setLenient(true);

            // 3. 解析输入字符串为Date → 格式化为「年-月-日」
            Date date = inputFormat.parse(startTime.trim());
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return outputFormat.format(date);

        } catch (Exception e) {
            // 解析失败直接返回null（极简处理，无需复杂日志）
            return null;
        }
    }
    /**
     * 将 Date 类型的日期年份减 1，其他字段（月、日、时、分、秒）保持不变
     * @param statsDate 原始日期（可 null，null 时返回 null）
     * @return 年份减 1 后的 Date 对象
     */
    public static Date minusOneYear(Date statsDate) {
        // 处理 null 输入
        if (statsDate == null) {
            return null;
        }

        // 使用 Calendar 类操作日期（兼容所有 Java 版本）
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(statsDate); // 将 Date 转换为 Calendar

        // 年份减 1（Calendar.YEAR 表示年份字段）
        calendar.add(Calendar.YEAR, -1);

        // 转换回 Date 类型并返回
        return calendar.getTime();
    }
    /**
     * 方法1：Java 8+ 推荐实现（简洁、线程安全）
     * @param statsDate 传入的日期（核心用其年份）
     * @return 该年份最后一天的字符串（格式：yyyy-MM-dd）
     */
    public static Date getYearLastDay(Date statsDate) {
        // 1. 空值处理：若传入 null，默认用当前日期的年份
        if (statsDate == null) {
            statsDate = new Date();
        }

        // 2. Date -> LocalDate：提取年份（指定时区避免偏差）
        LocalDate localDate = statsDate.toInstant()
                .atZone(ZoneId.systemDefault()) // 系统默认时区，也可指定 ZoneId.of("Asia/Shanghai")
                .toLocalDate();

        // 3. 构造该年份最后一天的 LocalDateTime（日期：12-31，时间：00:00:00）
        LocalDateTime yearLastDayTime = LocalDateTime.of(
                localDate.getYear(), 12, 31, 0, 0, 0
        );

        // 4. LocalDateTime -> Date：转回 Date 类型返回
        return Date.from(
                yearLastDayTime.atZone(ZoneId.systemDefault())
                        .toInstant()
        );
    }



    /**
     * 获取指定时间的当月第一天
     *
     * @param date 指定日期
     * @return 当月第一天的Date对象
     */
    public static Date getFirstDayOfMonth(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDate firstDay = localDateTime.toLocalDate().withDayOfMonth(1);
        return toDate(firstDay.atStartOfDay());
    }
    public static LocalDate parseFlexibleDate(String s) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("record_date 不能为空");
        }
        s = s.trim();
        // 优先尝试常见模式
        List<DateTimeFormatter> formats = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy-M-d")
        );
        for (DateTimeFormatter f : formats) {
            try { return LocalDate.parse(s, f); } catch (Exception ignore) {}
        }
        // 失败就抛错
        throw new IllegalArgumentException("record_date 格式不正确，应为 yyyy-MM-dd");
    }

    /**
     * 获取指定时间的当月最后一天
     *
     * @param date 指定日期
     * @return 当月最后一天的Date对象
     */
    public static Date getLastDayOfMonth(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDate lastDay = localDateTime.toLocalDate().withDayOfMonth(localDateTime.toLocalDate().lengthOfMonth());
        return toDate(lastDay.atTime(LocalTime.MAX));
    }
    /**
     * 从 "年月日" 格式的字符串中提取日
     * @param dateStr 日期字符串，例如 "2023-10-05" 或 "2023年10月05日"
     * @param pattern 日期格式，例如 "yyyy-MM-dd" 或 "yyyy年MM月dd日"
     * @return 日 (1-31)
     */
    public static int getDayFromString(String dateStr, String pattern) {
        // 解析字符串为 LocalDate
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        // 提取日
        return date.getDayOfMonth();
    }

    /**
     *
     * 解析 "yyyy-MM" 格式的年月字符串，返回该月份的天数
     *
     * @param yearMonthStr 年月字符串，格式必须为 "yyyy-MM"（如 "2025-11"）
     * @return 该月份的天数（如 30、31、28、29）
     * @throws IllegalArgumentException 如果输入格式不正确或月份无效
     */
    public static int getDaysInMonth(String yearMonthStr) {
        // 验证输入是否为 null 或空字符串
        if (yearMonthStr == null || yearMonthStr.trim().isEmpty()) {
            throw new IllegalArgumentException("输入不能为空，请提供有效的年月字符串（格式：yyyy-MM）");
        }

        try {
            // 解析字符串为 YearMonth 对象（自动处理格式验证）
            YearMonth yearMonth = YearMonth.parse(yearMonthStr);
            // 返回该月份的天数（自动处理平年/闰年）
            return yearMonth.lengthOfMonth();
        } catch (DateTimeParseException e) {
            // 捕获格式解析异常，返回友好提示
            throw new IllegalArgumentException(
                    "输入格式错误，请严格按照 \"yyyy-MM\" 格式输入（如 2025-11）",
                    e
            );
        }
    }
}
