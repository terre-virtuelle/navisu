package bzh.terrevirtuelle.navisu.app.guiagent.i18n.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.i18n.I18n;
import bzh.terrevirtuelle.navisu.app.guiagent.i18n.I18nLangEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.i18n.I18nServices;
import bzh.terrevirtuelle.navisu.core.utility.Checker;
import org.capcaval.c3.component.ComponentState;

import java.util.ResourceBundle;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 12:47
 */
public class I18nImpl implements I18n, I18nServices, ComponentState {

    protected I18nLangEnum lang = I18nLangEnum.getDefault();

    protected ResourceBundle bundle;

    @Override
    public void componentInitiated() {

        this.bundle = ResourceBundle.getBundle("i18n.translations", lang.getLocale());
    }

    @Override
    public String tr(String key) {
        return this.bundle.getString(key);
    }

    @Override
    public void setLang(I18nLangEnum lang) {

        Checker.notNull(lang, "Lang is null.");

        this.lang = lang;

        this.bundle = ResourceBundle.getBundle("i18n.translations", lang.getLocale());
        //TODO: send event 'langChanged'
    }

    @Override
    public I18nLangEnum getLang() {
        return this.lang;
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }
}
