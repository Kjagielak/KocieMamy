package pl.kociemamy;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Kociatko {
    private String id;
    private String imie;
    private String rasa;
    private LocalDate dataUrodzenia;
    private int waga;
    private int wysokosc;
    private Kociamama mama;
    //Kociatka(id, imie, rasa, dataUrodzenia, waga, wysokosc)


    public Kociatko(String id, String imie, String rasa, LocalDate dataUrodzenia, int waga, int wysokosc, Kociamama mama) {
        this.id = id;
        this.imie = imie;
        this.rasa = rasa;
        this.dataUrodzenia = dataUrodzenia;
        this.waga = waga;
        this.wysokosc = wysokosc;
        this.mama = mama;
    }


    public static double sredniaWagaKociatUrodzonychWPodanymMiesiacu(List<Kociatko> kociatka, LocalDate miesiac) {
        List<Kociatko> kocitaUrodzoneWPodanymMiesiacu = znajdzKociatkaUrodzoneWPodanymMiesiacu(kociatka, miesiac);
        double sumaWagKociatek = 0;
        double liczbaKociatek = kocitaUrodzoneWPodanymMiesiacu.size();
        for (Kociatko kociatko : kocitaUrodzoneWPodanymMiesiacu) {
            sumaWagKociatek += kociatko.getWaga();
        }
        return sumaWagKociatek / liczbaKociatek;
    }

    public static List<Kociatko> znajdzKociatkaUrodzoneWPodanymMiesiacu(List<Kociatko> kociatka, LocalDate miesiac) {
        List<Kociatko> kociatkaUrodzoneWPodanymOkresie = new ArrayList<>();
        for (Kociatko kociatko : kociatka) {
            if (kociatko.czyWPodanymMiesiacu(miesiac)) {
                kociatkaUrodzoneWPodanymOkresie.add(kociatko);
            }
        }
        return kociatkaUrodzoneWPodanymOkresie;
    }

    public boolean czyWPodanymMiesiacu(LocalDate miesiac) {
        return this.getDataUrodzenia().getMonth() == miesiac.getMonth();
    }

    public boolean czyPomiedzy(LocalDate dataPoczatkowa, LocalDate dataKoncowa) {
        return this.getDataUrodzenia().isAfter(dataPoczatkowa) && this.getDataUrodzenia().isBefore(dataKoncowa);
    }

    public static List<Kociatko> znajdzKociatkaPonizejOkreslonejWagi(List<Kociatko> kociatka, int waga) {
        List<Kociatko> kociatkaPonizejWagi = new ArrayList<>();
        for (Kociatko kociatko : kociatka) {
            if (kociatko.getWaga() < waga) {
                kociatkaPonizejWagi.add(kociatko);
            }
        }
        return kociatkaPonizejWagi;
    }

    public String getId() {
        return id;
    }


    public String getImie() {
        return imie;
    }


    public String getRasa() {
        return rasa;
    }


    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }


    public int getWaga() {
        return waga;
    }


    public int getWysokosc() {
        return wysokosc;
    }

    public Kociamama getMama() {
        return mama;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kociatko kociatko = (Kociatko) o;
        return waga == kociatko.waga && wysokosc == kociatko.wysokosc && Objects.equals(id, kociatko.id) && Objects.equals(imie, kociatko.imie) && Objects.equals(rasa, kociatko.rasa) && Objects.equals(dataUrodzenia, kociatko.dataUrodzenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imie, rasa, dataUrodzenia, waga, wysokosc);
    }

    @Override
    public String toString() {
        return "Kaciatko{" +
                "id='" + id + '\'' +
                ", imie='" + imie + '\'' +
                ", mama=" + mama +
                '}';
    }
}
