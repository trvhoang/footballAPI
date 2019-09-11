package features.jira;

public class transitionIssue {
    private transition transition;

    public transitionIssue(){

    }

    public transitionIssue(transition transition) {
        this.transition = transition;
    }

    public transition getTransition() {
        return transition;
    }

    public void setTransition(transition transition) {
        this.transition = transition;
    }
}