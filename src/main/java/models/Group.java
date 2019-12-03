package models;

public class Group {
    private int id;
    private String groupName;
    public  final int groupSize = 5;
    public  final int groupPayment=500;
    public  final int groupRound = 5;

    public Group(String groupName) {
        this.groupName=groupName;
    }
}
