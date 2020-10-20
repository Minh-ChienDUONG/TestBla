package Model;

public enum Action {
    L('L'),R('R'),F('F');
    private char name;

    Action(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }
}
