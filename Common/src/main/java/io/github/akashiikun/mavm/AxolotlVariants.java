package io.github.akashiikun.mavm;

public enum AxolotlVariants {

    LUCIA(true),
    WILDER(true),
    GOLDEN(true),
    GREEN(true),
    SKULK(false),
    BLACK(true),
    RED(true),
    GLOWXOLOTL(false),
    WHITE(true),
    CYANIDE(true);

    public boolean natural;
    public String name;
    private AxolotlVariants(boolean natural) {
        this.natural = natural;
        this.name = name();
    }
}