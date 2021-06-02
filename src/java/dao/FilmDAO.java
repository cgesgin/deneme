package dao;

import entity.Category;
import entity.Film;
import entity.Language;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnecter;

public class FilmDAO extends DBConnecter {

    private LanguageDAO languageDAO;
    private CategoryDAO categoryDAO;

    public void create(Film film) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("insert into film (title,description,release_year,language_id,length) values ('" + film.getTitle() + "','" + film.getDescription() + "', " + film.getReleaseYear() + "," + film.getLanguage().getLanguageId() + "," + film.getLength() + " )");
        } catch (Exception ex) {
            System.out.println("create" + ex.getMessage());;
        }
    }

    public List<Film> read(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        List<Film> list = new ArrayList<Film>();
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from film order by film_id asc limit " + pageSize + " offset " + start);
            while (rs.next()) {
                Language language = this.getLanguageDAO().getById(rs.getInt("language_id"));
                Film film = new Film(rs.getInt("film_id"), rs.getString("title"), rs.getString("description"), rs.getInt("release_year"), language, rs.getInt("length"), rs.getDate("last_update"));
                film.setCategoryList(this.getCategoryDAO().findCategories(rs.getInt("film_id")));
                list.add(film);
            }
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());;
        }
        return list;
    }

    public int count() {
        int count = 0;
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select count(*) as c from film ");
            rs.next();
            count = rs.getInt("c");
        } catch (Exception ex) {
            System.out.println("count() method " + ex.getMessage());;
        }
        return count;
    }

    public void update(Film film) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("update film set title='" + film.getTitle() + "',description='" + film.getDescription() + "' ,release_year=" + film.getReleaseYear() + ",length=" + film.getLength() + " ,language_id=" + film.getLanguage().getLanguageId() + "  where film_id=" + film.getFilmId());
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());;
        }
    }

    public void delete(Film film) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("delete from film where film_id=" + film.getFilmId());
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());;
        }
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

    public CategoryDAO getCategoryDAO() {
        if (this.categoryDAO == null) {
            this.categoryDAO = new CategoryDAO();
        }
        return categoryDAO;
    }
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
    
}
