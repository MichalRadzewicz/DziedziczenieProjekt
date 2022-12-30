package zad1;

import java.util.Random;

public class s21148_p02 {

    public static void main(String[] args) {
        PojazdMechaniczny[] tab = utworzTablice();
        System.out.println("PRZED SORTOWANIEM: ");
        wypiszPojazdy(tab);

        posortujTablice(tab);
        System.out.println("PO SORTOWANIU: ");
        wypiszPojazdy(tab);

    }

    private static void posortujTablice(PojazdMechaniczny[] tab) {
        for (int i = 0; i < tab.length - 1; i++) {
            for (int j = 0; j < tab.length - i - 1; j++ ) {
                if(czyZamienic(tab[j], tab[j + 1])) {
                    zamien(tab, j, j + 1);
                }
            }
        }
    }

    private static void zamien(PojazdMechaniczny[] tab, int index1, int index2) {
        PojazdMechaniczny pomocniczna = tab[index1];
        tab[index1] = tab[index2];
        tab[index2] = pomocniczna;
    }

    private static  boolean czyZamienic(PojazdMechaniczny poj1, PojazdMechaniczny poj2) {
        return porownaj(poj1, poj2) > 0;
    }

    private static  int porownaj(PojazdMechaniczny poj1, PojazdMechaniczny poj2) {
        int porownanieSilnika = porownajSilniki(poj1.getSilnik(), poj2.getSilnik());
        if(porownanieSilnika == 0) {
            int porownanieSiedzen = porownajIloscSiedzen(poj1, poj2);
            return porownanieSiedzen == 0? porownanieMarek(poj1, poj2): porownanieSiedzen;
        }
        return porownanieSilnika;

    }

    private static  int porownanieMarek(PojazdMechaniczny poj1, PojazdMechaniczny poj2) {
        if(poj1 instanceof Samochod && poj2 instanceof Samochod) {
            Samochod pierwszy = (Samochod) poj1;
            Samochod drugi = (Samochod) poj2;
            return pierwszy.getMarka().compareTo(drugi.getMarka());
        }
        return 0;
    }


    private static  int porownajIloscSiedzen(PojazdMechaniczny poj1, PojazdMechaniczny poj2) {
        if(poj1 instanceof Samochod && poj2 instanceof Samochod) {
            Samochod pierwszy = (Samochod) poj1;
            Samochod drugi = (Samochod) poj2;
            return drugi.getIloscMiejsc() - pierwszy.getIloscMiejsc();
        }
        return 0;
    }

    private static  int porownajSilniki(Silnik silnik1, Silnik silnik2) {
        return silnik1.getMoc() == silnik2.getMoc()? (int) (silnik2.getPojemnosc() - silnik1.getPojemnosc()) : silnik2.getMoc() - silnik1.getMoc();
    }

    private static  void wypiszPojazdy(PojazdMechaniczny[] tablica) {
        for (PojazdMechaniczny pojazdMechaniczny : tablica) {
            System.out.println(pojazdMechaniczny);
        }
    }

    private static  PojazdMechaniczny[] utworzTablice() {
        PojazdMechaniczny[] tablica = new PojazdMechaniczny[100];
        for(int i = 0; i < tablica.length; i++) {
            tablica[i] = wylosujSamochod();
        }
        return tablica;
    }

    private static  PojazdMechaniczny wylosujSamochod() {
        String[] MARKI = new String[]{
                "Toyota", "Honda", "Chevrolet", "Ford", "Mercedes", "Lexus", "Subaru", "Nissan", "Porsche", "Volvo"
        };
        Random random = new Random();
        int iloscMiejsc = random.nextInt(7 - 2) + 2;
        boolean electric = random.nextBoolean();
        String marka = MARKI[random.nextInt(MARKI.length)];

        long pojemnosc = random.nextInt(1800 - 900) + 900;
        int moc = random.nextInt(550 - 30) + 30;
        Silnik silnik = new Silnik(moc, pojemnosc);

        return new Samochod(iloscMiejsc, silnik, electric, marka);
    }
}

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

class PojazdMechaniczny {
    private int iloscMiejsc;
    private Silnik silnik;

    public PojazdMechaniczny(int iloscMiejsc, Silnik silnik) {
        this.iloscMiejsc = iloscMiejsc;
        this.silnik = silnik;
    }

    public int getIloscMiejsc() {
        return iloscMiejsc;
    }

    public Silnik getSilnik() {
        return silnik;
    }

    @Override
    public String toString() {
        return "ilosc posiadanych miejsc: " + this.iloscMiejsc + ", parametry silnika: { " + silnik.toString() + " }";
    }
}

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