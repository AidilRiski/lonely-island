import java.util.ArrayList;
import java.util.List;

class Pulau {
    public List<Pulau> connectedTo;
    public List<Pulau> connectedFrom;
    public int indeks;
    
    public Pulau(int _indeks){
        connectedTo = new ArrayList<Pulau>();
        connectedFrom = new ArrayList<Pulau>();
        indeks = _indeks;
        System.out.println("Island is " + indeks + " created.");
    }

    public void addConnectionToward(Pulau _pulauTujuan){
        connectedTo.add(_pulauTujuan);
        _pulauTujuan.addConnectionFrom(this);
    }

    public void addConnectionFrom(Pulau _pulauDari){
        connectedFrom.add(_pulauDari);
    }
}