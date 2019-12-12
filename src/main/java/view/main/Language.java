package view.main;

import java.util.Locale;
/**
 * Singleton class for tracking the locale language setting in the program.
 * @author Ville
 *
 */
public class Language {
	/**
	 * Singleton instance of the language.
	 */
	private static final Language INSTANCE = new Language();
	/**
	 * Initializes the Language object.
	 */
	private Language() {}
	/**
	 * Sets the starting locale to english.
	 */
	private Locale locale = new Locale("en", "GB");
	/**
	 * For accessing the singleton instance.
	 * @return Instance of this singleton.
	 */
	public static Language getInstance() {
		return INSTANCE;
	}
	/**
	 * For getting the current locale of the language singleton.
	 * @return The current locale of the language singleton.
	 */
	public Locale getLocale() {
		return locale;
	}
	/**
	 * For setting the locale of the language singleton.
	 * @param locale The new locale for the language singleton.
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
}
