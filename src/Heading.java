public enum Heading implements Rotatable<Heading> {
    N, E, S, W;

    public Heading rotateClockwise() {
        if (this == W ) { return N; }
        return Heading.values()[ordinal() + 1];
    }

    public Heading rotateAnticlockwise() {
        if (this == N) { return W; }
        return Heading.values()[ordinal() - 1];
    }
}
