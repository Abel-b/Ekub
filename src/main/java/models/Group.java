package models;

public class Group {


    private int id;
    private String groupname;
    private int groupsize;
    private int grouppayment;
    private String groupround;

    public Group(String groupname, int groupsize, int grouppayment, String groupround) {
        this.groupname = groupname;
        this.groupsize = groupsize;
        this.grouppayment = grouppayment;
        this.groupround = groupround;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;

    }

    public int getGroupsize() {
        return groupsize;
    }

    public void setGroupsize(int groupsize) {
        this.groupsize = groupsize;
    }

    public int getGrouppayment() {
        return grouppayment;
    }

    public void setGrouppayment(int grouppayment) {
        this.grouppayment = grouppayment;
    }

    public String getGroupround() {
        return groupround;
    }

    public void setGroupround(String groupround) {
        this.groupround = groupround;
    }
}
