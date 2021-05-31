package controller;

import dao.LanguageDAO;
import entity.Language;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LanguageBean implements Serializable {

    private LanguageDAO languageDAO;
    private Language language;

    public LanguageBean() {
    }

    public String create() {
        this.getLanguageDAO().create(language);
        this.language = null;
        return "/language/list";
    }

    public List<Language> getRead() {
        return this.getLanguageDAO().read();
    }

    public String updateFrom(Language language) {
        this.language = language;
        return "/language/update";
    }

    public String update() {
        this.getLanguageDAO().update(language);
        this.language = null;
        return "/language/list";
    }

    public void delete(Language language) {
        this.getLanguageDAO().delete(language);
    }

    public LanguageDAO getLanguageDAO() {
        if (this.languageDAO == null) {
            this.languageDAO = new LanguageDAO();
        }
        return languageDAO;
    }

    public void setLanguageDAO(LanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }

    public Language getLanguage() {
        if (this.language == null) {
            this.language = new Language();
        }
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

}
