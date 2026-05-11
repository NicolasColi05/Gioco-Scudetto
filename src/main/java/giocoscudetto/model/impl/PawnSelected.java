package giocoscudetto.model.impl;

public enum PawnSelected {
    RED(1), BLUE(2), GREEN(3), YELLOW(4);

    private final int id;

    /**
     * Construcotr to memorize the color selected by a club
     * @param id correspond to a specified color
     */
    private PawnSelected(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
