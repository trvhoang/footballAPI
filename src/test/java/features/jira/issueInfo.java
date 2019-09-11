package features.jira;

public class issueInfo {
    private features.jira.issueField fields;

    public issueInfo(){

    }

    public issueInfo(issueField fields) {
        this.fields = fields;
    }

    public issueField getFields() {
        return fields;
    }

    public void setFields(issueField fields) {
        this.fields = fields;
    }
}
