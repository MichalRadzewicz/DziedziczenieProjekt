package zad1;

class Samochod extends PojazdMechaniczny {
    private boolean electric;
    private String marka;

    public Samochod(int iloscMiejsc, Silnik silnik, boolean electric, String marka) {
        super(iloscMiejsc, silnik);
        this.electric = electric;
        this.marka = marka;
    }


    public String getMarka() {
        return marka;
    }

    @Override
    public String toString() {
        return " marka: " + this.marka + ", " + super.toString() + ", jest elektryczny: " + (this.electric? "tak" : "nie");
    }
}
