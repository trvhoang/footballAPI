package features.jira;

public class commentIssue {
    private String body;

    public commentIssue(){

    }

    public commentIssue(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
