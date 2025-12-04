package de.tu_darmstadt.lichterketten_steuerung.models;

public record StringLight(int id, boolean isOn, Mode mode, String color) {
    public enum  Mode {
        SOLID,
        BLINKING,
        PATTERN,
    }
}
