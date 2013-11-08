package bzh.terrevirtuelle.navisu.app.guiagent.i18n;

import java.util.Locale;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 12:49
 */
public enum I18nLangEnum {

    FRENCH(Locale.FRENCH),
    ENGLISH(Locale.ENGLISH);

    Locale locale;

    I18nLangEnum(Locale locale) {

        this.locale = locale;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public static I18nLangEnum getDefault() {
        return ENGLISH;
    }

    public static I18nLangEnum fromLocale(Locale locale) {

        I18nLangEnum result = null;

        if(locale.equals(Locale.FRENCH)) {
            result = FRENCH;
        }
        else if(locale.equals(Locale.ENGLISH)) {
            result = ENGLISH;
        }

        return result;
    }
}
