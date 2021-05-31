package controller;

import dao.FilmDAO;
import entity.Film;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class FilmBean implements Serializable {

    private FilmDAO filmDAO;
    private Film film;
    private int page;
    private int pageSize;
    private int pageCount;

    public FilmBean() {
        this.page = 1;
        this.pageSize = 5;
    }

    public String create() {
        this.getFilmDAO().create(film);
        this.film = null;
        return "/film/list";
    }

    public List<Film> getRead() {
        return this.getFilmDAO().read(this.page, this.pageSize);
    }

    public String updateFrom(Film film) {
        this.film = film;
        return "/film/update";
    }

    public String update() {
        this.getFilmDAO().update(film);
        this.film = null;
        return "/film/list";
    }

    public void delete(Film film) {
        this.getFilmDAO().delete(film);
    }

    public void nextPage() {
        if (this.page == this.getPageCount()) {
            this.page=1;
        } else {
            this.page++;
        }
    }

    public void previusPage() {
        if (this.page==1) {
            this.page=this.getPageCount();
        } else {
            this.page--;
        }
    }

    public FilmDAO getFilmDAO() {
        if (this.filmDAO == null) {
            this.filmDAO = new FilmDAO();
        }
        return filmDAO;
    }

    public void setFilmDAO(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    public Film getFilm() {
        if (this.film == null) {
            this.film = new Film();
        }
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount = (int) Math.ceil(this.getFilmDAO().count() / (double) this.pageSize);
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
