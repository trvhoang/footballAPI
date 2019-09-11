package features.jira;

public class issueField {

    private issueProject project;
    private String description;
    private String summary;
    private issueType issuetype;

    public issueField(){

    }

    public issueField(issueProject project, String description, String summary, issueType issuetype) {
        this.project = project;
        this.description = description;
        this.summary = summary;
        this.issuetype = issuetype;
    }

    public issueProject getProject() {
        return project;
    }

    public void setProject(issueProject project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public issueType getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(issueType issuetype) {
        this.issuetype = issuetype;
    }
}
