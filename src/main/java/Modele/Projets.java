package Modele;

import java.util.Date;

public class Projets {
    protected int id;

    protected String projectName;

    protected Date projectDateStart;

    protected Date projectDateEnd;

    protected String createur;

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public Projets(int id, String projectName,String createur) {
        this.id = id;
        this.projectName = projectName;
        this.projectDateStart = projectDateStart;
        this.projectDateEnd = projectDateEnd;
        this.createur = createur;
    }

    public int getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getProjectDateStart() {
        return projectDateStart;
    }

    public void setProjectDateStart(Date projectDateStart) {
        this.projectDateStart = projectDateStart;
    }

    public Date getProjectDateEnd() {
        return projectDateEnd;
    }

    public void setProjectDateEnd(Date projectDateEnd) {
        this.projectDateEnd = projectDateEnd;
    }
}
