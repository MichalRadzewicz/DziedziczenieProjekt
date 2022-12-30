package zad1;

class Silnik {
    private int moc;
    private long pojemnosc;

    public Silnik(int moc, long pojemnosc) {
        this.moc = moc;
        this.pojemnosc = pojemnosc;
    }

    public int getMoc() {
        return moc;
    }

    public long getPojemnosc() {
        return pojemnosc;
    }

    @Override
    public String toString() {
        return "moc: " + this.moc + " koni, pojemnosc: " + this.pojemnosc + " cm3";
    }
}
