package dao;

import entity.Film;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnecter;

public class FilmDAO extends DBConnecter {

    public void create(Film film) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("insert into film (title,description,release_year,language_id,length) values ('" + film.getTitle()+ "','" + film.getDescription()+ "', "+film.getReleaseYear()+","+film.getLanguageId()+","+film.getLength()+" )");
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
                Film film = new Film(rs.getInt("film_id"), rs.getString("title"), rs.getString("description") ,rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("length"),rs.getDate("last_update"));
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
            st.executeUpdate("update film set title='" + film.getTitle() + "',description='" + film.getDescription()+"' ,release_year="+film.getReleaseYear()+",length="+film.getLength()+" ,language_id="+film.getLanguageId()+"  where film_id=" + film.getFilmId());
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

}
