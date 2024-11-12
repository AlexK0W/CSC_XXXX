package MVBE;

public class Reviewer {
    private int reveiwerID;
    private String name;

    public Reviewer(int reveiwerID, String name) {
        this.reveiwerID = reveiwerID;
        this.name = name;
    }

    public int getReveiwerID() {
        return reveiwerID;
    }

    public void setReveiwerID(int reveiwerID) {
        this.reveiwerID = reveiwerID;
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
                "reveiwerID=" + reveiwerID +
                ", name='" + name + '\'' +
                '}';
    }
}
