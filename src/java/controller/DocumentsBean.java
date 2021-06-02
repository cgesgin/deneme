 
package controller;

import dao.DocumentsDAO;
import entity.Documents;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@SessionScoped
public class DocumentsBean implements Serializable{
    
    private Documents documents;
    private List<Documents> list;
    private DocumentsDAO documentsDAO;
    
    private final String uploadTo="/Users/User/Documents/NetBeansProjects/JSF_CRUD/upload/";
    
    private Part doc;

    public void upload(){
    
        try {
            InputStream input=doc.getInputStream();
            File f=new File(uploadTo+doc.getSubmittedFileName());
            Files.copy(input,f.toPath());
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
    }

    public String getUploadTo() {
        return uploadTo;
    }
    
    public void setDoc(Part doc) {
        this.doc = doc;
    }

    public Part getDoc() {
        return doc;
    }

    public Documents getDocuments() {
        if (this.documents==null) {
            this.documents=new Documents();
        }
        return documents;
    }

    public void setDocuments(Documents documents) {
        this.documents = documents;
    }

    public List<Documents> getList() {
        this.list=this.getDocumentsDAO().findAll();
        return list;
    }

    public void setList(List<Documents> list) {
        this.list = list;
    }

    public DocumentsDAO getDocumentsDAO() {
        if (this.documentsDAO==null) {
            this.documentsDAO=new DocumentsDAO();
        }
        return documentsDAO;
    }

    public void setDocumentsDAO(DocumentsDAO documentsDAO) {
        this.documentsDAO = documentsDAO;
    }
    
    
}
