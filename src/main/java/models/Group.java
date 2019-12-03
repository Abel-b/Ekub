package models;

public class Group {

    private int id;
    private String groupname;
    public final int groupsize;
    public final int grouppayment;
    public final String groupround;

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
}
