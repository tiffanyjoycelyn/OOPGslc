package model;

public class User extends Model{
    private String NIM;
    private int IDTeam;

    public User(String name, String NIM, int IDTeam) {
        super(name);
        this.NIM = NIM;
        this.IDTeam = IDTeam;
    }
    

    @Override
    public String toString() {
        return NIM + "," + name + "," + IDTeam;
    }
}
