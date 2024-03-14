package pl.kociemamy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
KocieMamy(id, imie, rasa, dataUrodzenia, waga, wysokosc) mogą rodzić Kociątka(id, imie, rasa, dataUrodzenia, waga, wysokosc)
W pliku kociemamy.txt znajduje się lista kocich rodziców podana w formacie: id, imie, rasa, dataUrodzenia, waga, wysokosc
np.
1, Lusia, Brytyjska, 2016-04-15, 4, 55
2, Puszek, Syjamska, 2017-05-22, 6, 40
3, Burek, Maine Coon, 2016-03-11, 10, 70
4, Misiek, Ragdoll, 2016-06-18, 8, 65

W pliku kociatka.txt znajduje się lista kociąt podana w formacie: id, imie, rasa, dataUrodzenia, waga, wysokosc, mamaId
np.
1, Maluszek, Brytyjska, 2018-03-21, 2, 35, 1
2, Kuleczka, Syjamska, 2019-04-25, 3, 25, 2
3, Płateczek, Maine Coon, 2018-02-19, 5, 45, 3
4, Kłębek, Ragdoll, 2019-07-23, 4, 40, 4
5, Burasek, Maine Coon, 2019-08-05, 5, 50, 3
6, Lisek, Brytyjska, 2019-01-20, 2, 30, 1
7, Puchatek, Ragdoll, 2019-09-15, 6, 42, 4

-- Znajdź kocie mamy, ktore urodzily bliźnięta.  ok
-- Ile jest maluchów ważących poniżej 3 kg?  ok
-- Znajdź kocia mame, ktora miała najwięcej potomstwa od stycznia 2018 do stycznia 2019.  ok
-- Podaj imię i wagę najciężej kociej mamy.  ok
-- Podaj średnią wagę kociąt urodzonych w lipcu 2019.  ok
 */

        List<Kociamama> kocieMamy = wczytajKocieMamy("src\\pl\\kociemamy\\kociemamy.txt");
        List<Kociatko> kociatka = wczytajKociatka("src\\pl\\kociemamy\\kociatka.txt", kocieMamy);

        System.out.println("Bliźnięta urodziła/y: " + Kociamama.znajdzMamyKtoreUrodzilyBliznieta(kocieMamy));
        System.out.println("Kociatka ponizej okreslonej wagi: " + Kociatko.znajdzKociatkaPonizejOkreslonejWagi(kociatka, 3));
        LocalDate dataPoczatkowa = LocalDate.of(2018, 1, 1);
        LocalDate dataKoncowa = LocalDate.of(2019, 1, 1);
        System.out.println("Kocia mama, która miała najwięcej dzieci w podanym okresie: " + Kociamama.znajdzKociaMameKtoraMialaNajwiecejKociatWPodanymOkresie(kocieMamy, dataPoczatkowa, dataKoncowa));
        System.out.print("Najciezsza kocia mama, to: ");
        Kociamama.podajImieIWageNajciezszejKociejMamy(kocieMamy);
        LocalDate lipiec2019 = LocalDate.of(2019, 8, 1);
        System.out.println("Srednia waga kociat urodzonych w podanym miesiacu, to: " + Kociatko.sredniaWagaKociatUrodzonychWPodanymMiesiacu(kociatka, lipiec2019));

    }

    public static List<Kociamama> wczytajKocieMamy(String path) {
        List<Kociamama> kociemamy = new ArrayList<>();
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String linia = null;
            while ((linia = br.readLine()) != null) {
                String[] dane = linia.split(", ");
                String id = dane[0];
                String imie = dane[1];
                String rasa = dane[2];
                LocalDate dataUrodzenia = LocalDate.parse(dane[3]);
                int waga = Integer.parseInt(dane[4]);
                int wysokosc = Integer.parseInt(dane[5]);
                Kociamama kociamama = new Kociamama(id, imie, rasa, dataUrodzenia, waga, wysokosc);
                kociemamy.add(kociamama);
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return kociemamy;
    }

    public static List<Kociatko> wczytajKociatka(String path, List<Kociamama> kocieMamy) {
        List<Kociatko> kociatka = new ArrayList<>();
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] dane = line.split(", ");
                Kociamama kociamama = znajdzKociaMamePoID(kocieMamy, dane[6]);
                String id = dane[0];
                String imie = dane[1];
                String rasa = dane[2];
                LocalDate dataUrodzenia = LocalDate.parse(dane[3]);
                int waga = Integer.parseInt(dane[4]);
                int wysokosc = Integer.parseInt(dane[5]);
                Kociatko kociatko = new Kociatko(id, imie, rasa, dataUrodzenia, waga, wysokosc, kociamama);
                kociatka.add(kociatko);
                kociamama.getKociatka().add(kociatko);
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return kociatka;
    }

    public static Kociamama znajdzKociaMamePoID(List<Kociamama> kocieMamy, String id) {
        for (Kociamama kociamama : kocieMamy) {
            if (kociamama.getId().equals(id)) {
                return kociamama;
            }
        }
        return null;
    }


}
