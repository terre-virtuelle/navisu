package bzh.terrevirtuelle.navisu.app.guiagent.utilities;

import bzh.terrevirtuelle.navisu.core.util.Checker;

import java.util.ResourceBundle;

/**
 * NaVisu
 *
 * @author tibus
 * @date 12/11/2013 19:17
 */
public class Translator {

    protected static final String BUNDLE_LOCATION = "i18n.translations";

    protected I18nLangEnum lang = I18nLangEnum.getDefault();
    protected ResourceBundle bundle;

    private static Translator instance = null;

    protected Translator() {
        this.bundle = ResourceBundle.getBundle(BUNDLE_LOCATION, lang.getLocale());
    }

    protected void _setLang(I18nLangEnum lang) {

        Checker.notNull(lang, "Lang must not be null.");

        this.lang = lang;
        this.bundle = ResourceBundle.getBundle(BUNDLE_LOCATION, lang.getLocale());
    }

    protected String _tr(String key) {

        Checker.notNull(key, "Key must not be null.");

        return this.bundle.getString(key);
    }

    public static void setLang(I18nLangEnum lang) {

        if(instance == null) {
            instance = new Translator();
        }

        instance._setLang(lang);
    }

    public static String tr(String key) {

        if(instance == null) {
            instance = new Translator();
        }

        return instance._tr(key);
    }
}



