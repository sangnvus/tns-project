package vn.co.taxinet.bean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseBean implements Serializable {
	protected final static String bundleName = "vn.co.taxinet.bean.i18n.MessageResources";
	private final static Logger log = LogManager.getLogger(BaseBean.class);

	/**
	 * 
	 * @param resourceBundleName
	 * @param resourceBundleKey
	 * @return
	 * @throws MissingResourceException
	 */
	public String getResourceBundleString(String resourceBundleKey) throws MissingResourceException {
		log.entry();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Locale locale = facesContext.getViewRoot().getLocale();
		ResourceBundle bundle = getResourceBundle(locale); 
		log.debug("Bundle Name is {} ", bundle);
		log.exit();
		return bundle.getString(resourceBundleKey);
	}

	/**
	 * 
	 * @param msgKey
	 */
	public void addMessage(String msgKey, Object ...arguments) {
		log.entry();
		log.debug("Params({}}", msgKey);
		FacesContext fc = FacesContext.getCurrentInstance();
		String pattern = getResourceBundleString(msgKey);
		log.debug(pattern);
		String message = MessageFormat.format(pattern, arguments);
		FacesMessage fm = new FacesMessage(message);
		fm.setSeverity(FacesMessage.SEVERITY_ERROR);		
		fc.addMessage(msgKey, fm);
		log.exit();
	}

	private String locale = "en";

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String changeLocale(String locale) {
		// Change the locale attribute
		this.locale = locale; 
		// Change the locale of the view
		FacesContext.getCurrentInstance().getViewRoot()
				.setLocale(new Locale(this.locale));
		return "";
	}
	// - See more at:
	// http://www.javabeat.net/jsf-2-loadbundle/#sthash.jd5g6Cmq.dpuf
	private ResourceBundle getResourceBundle(Locale locale){
		FacesContext context = FacesContext.getCurrentInstance();	
		return ResourceBundle.getBundle(context.getApplication().getMessageBundle(), locale);

	}	
	public void handleException(Throwable exception) {
        String message = "";
     //   if (exception instanceof I18nException) {
      //      I18nException i18nException = (I18nException) exception;
            Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();            
            String pattern = getResourceBundle(locale).getString("");//i18nException.getBundleKey());
            //message = MessageFormat.format(pattern, i18nException.getArguments());
        //} else {
            ///message = "An unexpected error occured !";
//        FacesMessage facesMessage = new FacesMessage(message);
  //      FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
