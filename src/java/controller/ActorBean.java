package controller;

import dao.ActorDAO;
import entity.Actor;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ActorBean implements Serializable {

    private ActorDAO actorDAO;
    private Actor actor;
    private int page;
    private int pageSize;
    private int pageCount;

    public ActorBean() {
        this.page = 1;
        this.pageSize = 5;
    }

    public String create() {
        this.getActorDAO().create(actor);
        this.actor = null;
        return "/actor/list";
    }

    public List<Actor> getRead() {
        return this.getActorDAO().read(this.page, this.pageSize);
    }

    public String updateFrom(Actor actor) {
        this.actor = actor;
        return "/actor/update";
    }

    public String update() {
        this.getActorDAO().update(actor);
        this.actor = null;
        return "/actor/list";
    }

    public void delete(Actor actor) {
        this.getActorDAO().delete(actor);
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

    public ActorDAO getActorDAO() {
        if (this.actorDAO == null) {
            this.actorDAO = new ActorDAO();
        }
        return actorDAO;
    }

    public void setActorDAO(ActorDAO actorDAO) {
        this.actorDAO = actorDAO;
    }

    public Actor getActor() {
        if (this.actor == null) {
            this.actor = new Actor();
        }
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
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
        return pageCount = (int) Math.ceil(this.getActorDAO().count() / (double) this.pageSize);
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
