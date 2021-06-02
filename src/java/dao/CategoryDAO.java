package dao;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import entity.Category;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnecter;

public class CategoryDAO extends DBConnecter {

    public void create(Category category) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("insert into category (name) values ('" + category.getName() + "')");
        } catch (Exception ex) {
            System.out.println("create" + ex.getMessage());;
        }
    }

    public List<Category> read() {
        List<Category> list = new ArrayList<Category>();
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from category order by category_id asc");
            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("name"), rs.getDate("last_update"));
                list.add(category);
            }
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());;
        }
        return list;
    }

    public void update(Category category) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("update category set name='" + category.getName() + "' where category_id=" + category.getCategoryId());
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());;
        }
    }

    public void delete(Category category) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("delete from category where category_id=" + category.getCategoryId());
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());;
        }
    }

    public List<Category> findCategories(int film_id) {

        List<Category> list = new ArrayList<>();
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select c.category_id, c.name ,c.last_update from film f \n"
                    + " inner join film_category fc on f.film_id=fc.film_id\n"
                    + " inner join category c on c.category_id=fc.category_id where f.film_id=" + film_id);
            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("name"), rs.getDate("last_update"));
                list.add(category);                
            }
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
        return list;
    }
}
