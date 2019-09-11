package features.jira;

public class issueProject {
    private String key;

    public issueProject(){

    }

    public issueProject(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
