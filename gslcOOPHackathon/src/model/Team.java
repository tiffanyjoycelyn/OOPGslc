package model;

public class Team extends Model{
    private int ID;
    public Team(int ID, String name) {
        super(name);
        this.ID = ID;
    }

    @Override
    public String toString() {
        return ID + "," + name;
    }
}
