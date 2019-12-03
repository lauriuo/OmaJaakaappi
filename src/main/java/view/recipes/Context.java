package view.recipes;

public class Context {
    private final static Context instance = new Context();
    public static Context getInstance() {
        return instance;
    }
    private int id;
    public void setId(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }
}