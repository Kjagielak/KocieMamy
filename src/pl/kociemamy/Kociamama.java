package pl.kociemamy;

import java.time.LocalDate;
import java.util.*;

public class Kociamama {
    private String id;
    private String imie;
    private String rasa;
    private LocalDate dataUrodzenia;
    private int waga;
    private int wysokosc;
    private List<Kociatko> kociatka = new ArrayList<>();

    //KocieMamy(id, imie, rasa, dataUrodzenia, waga, wysokosc)


    public Kociamama(String id, String imie, String rasa, LocalDate dataUrodzenia, int waga, int wysokosc) {
        this.id = id;
        this.imie = imie;
        this.rasa = rasa;
        this.dataUrodzenia = dataUrodzenia;
        this.waga = waga;
        this.wysokosc = wysokosc;
    }

    public static void podajImieIWageNajciezszejKociejMamy(List<Kociamama> kocieMamy) {
        Map<Kociamama, Integer> kociaMamaIJejWaga = new HashMap<>();
        for (Kociamama kociamama : kocieMamy) {
            kociaMamaIJejWaga.put(kociamama, kociamama.waga);
        }
        Kociamama najciezszaKociaMama = Collections.max(kociaMamaIJejWaga.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println(najciezszaKociaMama.getImie() + " " + najciezszaKociaMama.getWaga());
    }

    public static Kociamama znajdzKociaMameKtoraMialaNajwiecejKociatWPodanymOkresie(List<Kociamama> kocieMamy, LocalDate dataPoczatkowa, LocalDate dataKoncowa) {
        Map<Kociamama, Integer> kociaMamaIIloscKociat = new HashMap<>();
        for (Kociamama kociamama : kocieMamy) {
            for (Kociatko kociatko : kociamama.getKociatka()) {
                if (kociatko.czyPomiedzy(dataPoczatkowa, dataKoncowa)) {
                    if (!kociaMamaIIloscKociat.containsKey(kociamama)) {
                        kociaMamaIIloscKociat.put(kociamama, 1);
                    } else {
                        int liczbaKociat = kociaMamaIIloscKociat.get(kociamama);
                        kociaMamaIIloscKociat.put(kociamama, liczbaKociat + 1);
                    }
                }
            }
        }
        return Collections.max(kociaMamaIIloscKociat.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static List<Kociamama> znajdzMamyKtoreUrodzilyBliznieta(List<Kociamama> kociemamy) {
        List<Kociamama> kocieMamyZBliznietami = new ArrayList<>();
        for (Kociamama kociamama : kociemamy) {
            if (kociamama.czyUrodzilaBliznieta()) {
                kocieMamyZBliznietami.add(kociamama);
            }
        }
        return kocieMamyZBliznietami;
    }

    public boolean czyMaPrzynajmniejDwojkeSzczeniat() {
        return this.getKociatka().size() >= 2;
    }

    public boolean czyUrodzilaBliznieta() {
        Set<LocalDate> datyUrodzenia = new HashSet<>();
        if (czyMaPrzynajmniejDwojkeSzczeniat()) {
            for (Kociatko kociatko : this.getKociatka()) {
                datyUrodzenia.add(kociatko.getDataUrodzenia());
            }
            return datyUrodzenia.size() < this.getKociatka().size();
        }
        return false;
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

    public List<Kociatko> getKociatka() {
        return kociatka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kociamama kociamama = (Kociamama) o;
        return waga == kociamama.waga && wysokosc == kociamama.wysokosc && Objects.equals(id, kociamama.id) && Objects.equals(imie, kociamama.imie) && Objects.equals(rasa, kociamama.rasa) && Objects.equals(dataUrodzenia, kociamama.dataUrodzenia) && Objects.equals(kociatka, kociamama.kociatka);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imie, rasa, dataUrodzenia, waga, wysokosc, kociatka);
    }

    @Override
    public String toString() {
        return "Kociamama{" +
                "id='" + id + '\'' +
                ", imie='" + imie + '\'' +
                '}';
    }
}
