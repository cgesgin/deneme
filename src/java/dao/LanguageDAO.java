package dao;

import entity.Language;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnecter;

public class LanguageDAO extends DBConnecter {

    public void create(Language language) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("insert into language (name) values ('" + language.getName() + "')");
        } catch (Exception ex) {
            System.out.println("create" + ex.getMessage());;
        }
    }

    public List<Language> read() {
        List<Language> list = new ArrayList<Language>();
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from language order by language_id asc");
            while (rs.next()) {
                Language language = new Language(rs.getInt("language_id"), rs.getString("name"), rs.getDate("last_update"));
                list.add(language);
            }
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());;
        }
        return list;
    }

    public void update(Language language) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("update language set name='" + language.getName() + "' where language_id=" + language.getLanguageId());
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());;
        }
    }

    public void delete(Language language) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("delete from language where language_id=" + language.getLanguageId());
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());;
        }
    }

}
