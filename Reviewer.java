package MVBE;

public class Reviewer {
    private int reviewerID;
    private String name;

    public Reviewer(int reviewerID, String name) {
        this.reviewerID = reviewerID;
        this.name = name;
    }

    public int getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(int reviewerID) {
        this.reviewerID = reviewerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Reviewer{" +
                "reviewerID=" + reviewerID +
                ", name='" + name + '\'' +
                '}';
    }
}
