package controller;

import dao.CategoryDAO;
import entity.Category;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class CategoryBean implements Serializable {

    private CategoryDAO categoryDAO;
    private Category category;

    public CategoryBean() {
    }

    public String create() {
        this.getCategoryDAO().create(category);
        this.category = null;
        return "/category/list";
    }

    public List<Category> getRead() {
        return this.getCategoryDAO().read();
    }

    public String updateFrom(Category category) {
        this.category = category;
        return "/category/update";
    }

    public String update() {
        this.getCategoryDAO().update(category);
        this.category = null;
        return "/category/list";
    }

    public String deleteFrom(Category category) {
        this.category=category;
        return "/category/delete";
    }

    public String delete() {
        this.getCategoryDAO().delete(category);
        this.category=null;
        return "/category/list";
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

    public Category getCategory() {
        if (this.category == null) {
            this.category = new Category();
        }
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
