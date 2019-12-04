package view.main;

import java.util.Locale;

public class Language {
	
	private static final Language INSTANCE = new Language();
	private Language() {}
	private Locale locale = new Locale("en", "GB");
	
	public static Language getInstance() {
		return INSTANCE;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
}
