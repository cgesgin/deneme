 
package dao;


import entity.Documents;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List; 
import util.DBConnecter;

public class DocumentsDAO extends DBConnecter{
        
    public List<Documents> findAll(){
        List<Documents> list=new ArrayList<>();
        try {       
            PreparedStatement pst=this.connect().prepareStatement("select * from documents");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {
               Documents d=new Documents();
               d.setId(rs.getInt("id"));
               d.setFileName(rs.getString("name"));
               d.setFilePath(rs.getString("path"));
               d.setFileType(rs.getString("type"));
               list.add(d);
            }
            
        } catch (SQLException ex) {
            System.out.println("dao.DocumentsDAO.findAll()"+ex.getErrorCode());
        }
        return list;
    }
    
     public void insert(Documents documents){
        try {       
            PreparedStatement pst=this.connect().prepareStatement("insert into documents (name,path,type) values(?,?,?)");
            
            pst.setString(1,documents.getFileName());
            pst.setString(2,documents.getFilePath());
            pst.setString(3,documents.getFileType()); 
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("dao.DocumentsDAO.findAll()"+ex.getErrorCode());
        }
    }

    
    
     
}
