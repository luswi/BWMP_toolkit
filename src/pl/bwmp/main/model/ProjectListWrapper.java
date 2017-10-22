package pl.bwmp.main.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 */

@XmlRootElement(name = "projects")
public class ProjectListWrapper {

    private List<Project> projects;

    @XmlElement(name = "project")

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects){
        this.projects = projects;
    }

}
