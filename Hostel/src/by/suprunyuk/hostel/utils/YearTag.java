package by.suprunyuk.hostel.utils;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/** 
 * User tag for representation actual year number on JSP page in web application, e.g. 2017
 * 
 * @author Anton Suprunyuk
 */
@SuppressWarnings("serial")
public class YearTag extends TagSupport {

	/**
	 *  processing of the start tag. Yielding actual year and flushing it to output stream 
	 *  
	 *  @return SKIP_BODY constant to indicate than the tag has no body
	 *  @throws   A generic exception known to the JSP engine; uncaughtJspExceptions will result in an 
	 *  invocation of the error page machinery.
	 */
	@Override
	public int doStartTag() throws JspException {
		GregorianCalendar gc = new GregorianCalendar();
		String year = " <b> " + gc.get(Calendar.YEAR) + " </b>";
		try {
			JspWriter out = pageContext.getOut();
			out.write(year);
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
	/**
	 * doing nothing 
	 * 
	 * @return EVAL_PAGE constant it`s a signal to continue evaluating the page.
	 *  @throws   A generic exception known to the JSP engine; uncaughtJspExceptions will result in an 
	 *  invocation of the error page machinery.
	 */
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
