
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int SIZE = 5, // aloitustalukon koko
                            DEFINCR = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int incr;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] tbl;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        tbl = new int[SIZE];
        for (int i = 0; i < tbl.length; i++) {
            tbl[i] = 0;
        }
        alkioidenLkm = 0;
        this.incr = DEFINCR;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        tbl = new int[kapasiteetti];
        for (int i = 0; i < tbl.length; i++) {
            tbl[i] = 0;
        }
        alkioidenLkm = 0;
        this.incr = DEFINCR;

    }
    
    
    public IntJoukko(int size, int increment) {
        if (size < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (increment > 0) {
            this.incr = increment;
        }
        tbl = new int[size];
        tbl = nollaaTaulukko(tbl);
        alkioidenLkm = 0;
        this.incr = increment;

    }
    
    public int[] nollaaTaulukko(int[] taulukko) {
        for(int i : taulukko) {
            i = 0;
        }
        return taulukko;
    }

    public boolean lisaa(int luku) {

        int eiOle = 0;
        if (alkioidenLkm == 0) {
            tbl[0] = luku;
            alkioidenLkm++;
            return true;
        } else {
        }
        if (!kuuluu(luku)) {
            tbl[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % tbl.length == 0) {
                int[] taulukkoOld = new int[tbl.length];
                taulukkoOld = tbl;
                kopioiTaulukko(tbl, taulukkoOld);
                tbl = new int[alkioidenLkm + incr];
                kopioiTaulukko(taulukkoOld, tbl);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        int on = 0;
        for (int i : tbl) {
            if (luku == i) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = -1;
        int apu;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == tbl[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                tbl[kohta] = 0;
                break;
            }
        }
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = tbl[j];
                tbl[j] = tbl[j + 1];
                tbl[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }


        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int alkioita() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + tbl[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += tbl[i];
                tuotos += ", ";
            }
            tuotos += tbl[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = tbl[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
 
        return z;
    }
        
}