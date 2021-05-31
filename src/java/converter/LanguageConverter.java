package converter;

import dao.LanguageDAO;
import entity.Language;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "lanConverter")
public class LanguageConverter implements Converter {

    private LanguageDAO languageDAO;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
        return this.getLanguageDAO().getById(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object o) {
        Language l=(Language) o;
        return String.valueOf(l.getLanguageId());
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
}
