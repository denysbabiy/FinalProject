package com.epam.finalproject.web.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class PrettyDateTimeFormatTag extends TagSupport {
    private Date value;
    private String locale;


    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.println(formatting(value,locale));

        } catch (Exception ex) {
            throw new JspException("IO problems");
        }
        return SKIP_BODY;
    }


    public static String formatting(Date date, String lang) {
        SimpleDateFormat dateFormat = null;
        if (lang.equals("ua")) {
            dateFormat = new SimpleDateFormat("dd MMMM HH:mm", myDateFormatSymbols);
            return dateFormat.format(date);

        }
        dateFormat = new SimpleDateFormat("dd MMMM hh:mm a", Locale.ENGLISH);
        return dateFormat.format(date);

    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {
        @Override
        public String[] getMonths() {
            return new String[]{"січня", "лютого", "березня", "квітня", "травня", "червня",
                    "липня", "серпня", "вересня", "жовтня", "листопада", "грудня"};
        }
    };

}
