import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

class KumpulanPulau{
    private int jumlahPulau;
    private List<Pulau> kumpulanPulau;
    private int indeksPulauPertama;

    public KumpulanPulau(int _jumlahPulau){
        setJumlahPulau(_jumlahPulau);
        kumpulanPulau = new ArrayList<Pulau> ();
        for (int i = 0; i < jumlahPulau; i++){
            kumpulanPulau.add(new Pulau(i + 1)); 
        }
    }

    public List<Pulau> getKumpulanPulau(){
        return kumpulanPulau;
    }

    public Pulau getPulau(int _indeks){
        return kumpulanPulau.get(_indeks);
    }

    public int getJumlahPulau(){
        return jumlahPulau;
    }

    public int getIndeksPulauPertama(){
        return indeksPulauPertama;
    }

    public void setJumlahPulau(int _jumlahPulau){
        jumlahPulau = _jumlahPulau;
    }

    public void setIndeksPulauPertama(int _indeksPulauPertama){
        indeksPulauPertama = _indeksPulauPertama;
    }

    public static List<Pulau> cariLoneIslands(KumpulanPulau _kumpulanPulau){
        if (_kumpulanPulau.getJumlahPulau() > 1){
            Boolean[] visitedIslands = new Boolean[_kumpulanPulau.getJumlahPulau()];
            for (int i = 0; i < visitedIslands.length; i++){
                visitedIslands[i] = false;
            }
            LoneIslandRecursion loneIslandRecursion = new LoneIslandRecursion(_kumpulanPulau.getKumpulanPulau().get(_kumpulanPulau.getIndeksPulauPertama()), visitedIslands);
            Thread recursionThread = new Thread(
                null,
                loneIslandRecursion,
                "RecursiveThread",
                52428800
            );
            recursionThread.start();
            try {
                recursionThread.join();
            } catch (InterruptedException _interruptedException){
                _interruptedException.printStackTrace();
            }
            loneIslandRecursion.printRoutes();
            return loneIslandRecursion.getLonelyIslands();
        }else {
            return _kumpulanPulau.getKumpulanPulau();
        }
    }
}

class LoneIslandRecursion implements Runnable{
    private List<Pulau> loneIslands;
    private Pulau startIsland;
    private Boolean[] visitedIslands;
    private List<List<Integer>> listRute;


    LoneIslandRecursion(Pulau _startIsland, Boolean[] _visitedIslands){
        startIsland = _startIsland;
        visitedIslands = _visitedIslands;
        loneIslands = new ArrayList<Pulau>();
        listRute = new ArrayList<List<Integer>>();
    }

    public void run(){
        loneIslands = new ArrayList<>(new HashSet<>(cariLoneIslandsRekursi(startIsland, visitedIslands, new ArrayList<Integer>())));
    }
    
    public List<Pulau> getLonelyIslands(){
        return loneIslands;
    }

    public void printRoutes(){
        for (int i = 0; i < listRute.size(); i++){
            for (int j = 0; j < listRute.get(i).size(); j++){
                System.out.print(listRute.get(i).get(j));
                if (j < listRute.get(i).size() - 1){
                    System.out.print("->");
                }
            }
            System.out.println();
        }
    }

    private List<Pulau> cariLoneIslandsRekursi(Pulau _pulau, Boolean[] _visitedIslands, List<Integer> _currentRoute){
        List<Integer> currentRoute = new ArrayList<Integer>(_currentRoute);
        currentRoute.add(_pulau.getIndeks());
        Boolean[] visitedIslands = _visitedIslands.clone();
        visitedIslands[_pulau.getIndeks() - 1] = true;
        if (!_pulau.isEdge()){
            List<Pulau> returnValue = new ArrayList<Pulau>();
            for (int i = 0; i < _pulau.getConnectedToSize(); i++){
                if (!visitedIslands[_pulau.getConnectedToIsland(i).getIndeks() - 1]){
                    returnValue.addAll(cariLoneIslandsRekursi(_pulau.getConnectedToIsland(i), visitedIslands, currentRoute));
                }
            }
            if (returnValue.isEmpty()){
                returnValue.add(_pulau);
                listRute.add(new ArrayList<Integer>(currentRoute));
            }
            return returnValue;
        }else {
            List<Pulau> returnValue = new ArrayList<Pulau>();
            returnValue.add(_pulau);
            listRute.add(new ArrayList<Integer>(currentRoute));
            return returnValue;
        }
    }
}