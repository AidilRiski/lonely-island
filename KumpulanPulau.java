class KumpulanPulau{
    public int jumlahPulau;
    public Pulau[] kumpulanPulau;

    public KumpulanPulau(int _jumlahPulau){
        jumlahPulau = _jumlahPulau;
        kumpulanPulau = new Pulau[jumlahPulau];
        for (int i = 0; i < jumlahPulau; i++){
            kumpulanPulau[i] = new Pulau(i + 1);
        }
    }

    public Pulau getPulau(int _indeks){
        return kumpulanPulau[_indeks];
    }
}