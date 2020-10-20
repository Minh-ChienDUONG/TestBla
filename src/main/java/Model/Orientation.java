package Model;

import java.io.Serializable;

public enum Orientation{
    N("N"),E("E"),S("S"),W("W");
    private String name;

    Orientation(String name) {
        this.name = name;
    }
}
