package dao;

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

}
