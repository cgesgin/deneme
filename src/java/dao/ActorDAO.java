 
package dao;

import entity.Actor;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnecter;

 
public class ActorDAO extends DBConnecter {

    public void create(Actor actor) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("insert into actor (first_name,last_name) values ('" + actor.getFirstName()+ "','"+actor.getLastName()+"')");
        } catch (Exception ex) {
            System.out.println("create" + ex.getMessage());;
        }
    }

    public List<Actor> read() {
        List<Actor> list = new ArrayList<Actor>();
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from actor order by actor_id asc");
            while (rs.next()) {
                Actor actor = new Actor(rs.getInt("actor_id"),rs.getString("first_name"), rs.getString("last_name"), rs.getDate("last_update"));
                list.add(actor);
            }
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());;
        }
        return list;
    }

    public void update(Actor actor) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("update actor set first_name='" + actor.getFirstName() + "',last_name='"+actor.getLastName()+"' where actor_id=" + actor.getActorId());
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());;
        }
    }

    public void delete(Actor actor) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("delete from actor where actor_id=" + actor.getActorId());
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());;
        }
    }

}
