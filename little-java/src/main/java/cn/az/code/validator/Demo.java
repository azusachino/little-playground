package cn.az.code.validator;

/**
 * @author az
 * @since 2021-04-19 22:54
 */
public class Demo {

    @DateFormatValidation
    private String date;

    @DateFormatValidation(format = "HH:mm:ss")
    private String time;

    @DateFormatValidation(format = "yyyy-MM-dd HH:mm:ss")
    private String dateTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
