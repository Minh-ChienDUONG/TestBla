package Model;

public class Mower {
    private int id;
    private int nextIndexOrder;
    private String instruction;
    private boolean isGoodOrder;
    private Position currentPosition;
    private Position nextPosition;

    public Mower(int id, int nextIndexOrder, String instruction, Position currentPosition, Position nextPosition) {
        this.id = id;
        this.nextIndexOrder = nextIndexOrder;
        this.instruction = instruction;
        this.isGoodOrder = true;
        this.currentPosition = currentPosition;
        this.nextPosition = nextPosition;
    }

    public int getId() {
        return id;
    }

    public int getNextIndexOrder() {
        return this.nextIndexOrder;
    }

    public void setNextIndexOrder(int nextIndexOrder) {
        this.nextIndexOrder = nextIndexOrder;
    }

    public String getInstruction() {
        return instruction;
    }

    public boolean isGoodOrder() {
        return isGoodOrder;
    }

    public void setGoodOrder(boolean goodOrder) {
        isGoodOrder = goodOrder;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Position getNextPosition() {
        return nextPosition;
    }

}
