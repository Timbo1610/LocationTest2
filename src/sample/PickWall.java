package sample;

public class PickWall {

    private String name;
    int height;

    public PickWall(String name,int height)
    {
        this.name = name;
        this.height = height;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
