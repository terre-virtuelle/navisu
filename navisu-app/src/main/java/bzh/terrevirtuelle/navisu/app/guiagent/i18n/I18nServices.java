package bzh.terrevirtuelle.navisu.app.guiagent.i18n;

import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 12:46
 */
public interface I18nServices extends ComponentService {

    String tr(String key);

    void         setLang(I18nLangEnum lang);
    I18nLangEnum getLang();
}
