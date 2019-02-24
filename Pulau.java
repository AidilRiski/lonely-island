import java.util.ArrayList;
import java.util.List;

class Pulau {
    private List<Pulau> connectedTo;
    private List<Pulau> connectedFrom;
    private int indeks;
    
    public Pulau(int _indeks){
        connectedTo = new ArrayList<Pulau>();
        connectedFrom = new ArrayList<Pulau>();
        indeks = _indeks;
        //System.out.println("Island is " + indeks + " created.");
    }

    public void addConnectionToward(Pulau _pulauTujuan){
        connectedTo.add(_pulauTujuan);
        _pulauTujuan.addConnectionFrom(this);
    }

    public void addConnectionFrom(Pulau _pulauDari){
        connectedFrom.add(_pulauDari);
    }

    public Pulau getConnectedToIsland(int _indeks){
        return connectedTo.get(_indeks);
    }

    public Pulau getConnectedFromIsland(int _indeks){
        return connectedFrom.get(_indeks);
    }

    public int getConnectedToSize(){
        return connectedTo.size();
    }

    public int getConnectedFromSize(){
        return connectedFrom.size();
    }

    public int getIndeks(){
        return indeks;
    }

    public boolean isOutlier(){
        return connectedFrom.isEmpty() && connectedTo.isEmpty();
    }

    public boolean isEdge(){
        return connectedTo.isEmpty();
    }

    public boolean isEdgeReversed(){
        return connectedFrom.isEmpty();
    }

    public void printInfo(){
        System.out.println("Island " + indeks + " is connected to:");
        if (connectedTo.isEmpty()){
            System.out.println("NONE");
        }else {
            for (int i = 0; i < connectedTo.size(); i++){
                System.out.println("--Island " + connectedTo.get(i).indeks);
            }
        }

        System.out.println("Island " + indeks + " is connected from:");
        if (connectedFrom.isEmpty()){
            System.out.println("NONE");
        }else {
            for (int i = 0; i < connectedFrom.size(); i++){
                System.out.println("--Island " + connectedFrom.get(i).indeks);
            }
        }
    }
}