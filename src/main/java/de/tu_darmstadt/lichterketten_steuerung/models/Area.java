package de.tu_darmstadt.lichterketten_steuerung.models;

import java.util.List;

public record Area(int id, String name) {
    public Area {
        if (name == null || name.isEmpty())
            name = "Area " + id;
    }
}
