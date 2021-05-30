 
package controller;
 
import dao.ActorDAO;
import dao.ActorDAO;
import entity.Actor;
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

    public ActorBean() {
    }

    public String create() {
        this.getActorDAO().create(actor);
        this.actor = null;
        return "/actor/list";
    }

    public List<Actor> getRead() {
        return this.getActorDAO().read();
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

}
