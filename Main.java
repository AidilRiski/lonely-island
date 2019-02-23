import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Main{
    public static KumpulanPulau kumpulanPulau;
    public static void main(String args[]){
        Scanner inputStream = new Scanner(System.in);
        String fileName = inputStream.nextLine();
        if (!readFile(fileName)){
            System.out.println("Something wrong happened while reading file.");
        }else {
            System.out.println("File is successfully read.");
        }

        for (int i = 0; i < kumpulanPulau.jumlahPulau; i++){
            if (!kumpulanPulau.getPulau(i).isOutlier()){
                kumpulanPulau.getPulau(i).printInfo();
            }
        }

        inputStream.close();
    }

    private static String sanitasiSpasi(String _string){
        StringBuilder _stringBuilder = new StringBuilder();

        for (int i = 0; i < _string.length(); i++){
            if (_string.charAt(i) == ' '){
                if (_stringBuilder.length() > 0){
                    if (_stringBuilder.charAt(_stringBuilder.length() - 1) != ' '){
                        _stringBuilder.append(_string.charAt(i));
                    }
                }
            }else {
                _stringBuilder.append(_string.charAt(i));
            }
        }

        return _stringBuilder.toString();
    }

    private static boolean readFile(String fileName){
        boolean isValid = true;
        try {

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            if (bufferedReader.ready()){
                String firstLine = sanitasiSpasi(bufferedReader.readLine());

                int jumlahPulau = Integer.parseInt(firstLine.toString().split(" ")[0]);
                int jumlahJembatan = Integer.parseInt(firstLine.toString().split(" ")[1]);
                int indeksPulauPertama = Integer.parseInt(firstLine.toString().split(" ")[2]);

                System.out.println("Jumlah Pulau: " + jumlahPulau);
                System.out.println("Jumlah Jembatan: " + jumlahJembatan);
                System.out.println("Kamu mulai di pulau ke-" + indeksPulauPertama + ".");

                isValid = indeksPulauPertama > 0 && indeksPulauPertama <= jumlahPulau;

                if (!isValid){
                    System.out.println("Indeks pulau mulai tidak valid!");
                }else {
                    kumpulanPulau = new KumpulanPulau(jumlahPulau);

                    int linesRead = 0;
                    String line = bufferedReader.readLine();
                    while (linesRead < jumlahJembatan && line != null){
                        line = sanitasiSpasi(line);
                        int fromID = Integer.parseInt(line.split(" ")[0]) - 1;
                        int toID = Integer.parseInt(line.split(" ")[1]) - 1;
                        kumpulanPulau.getPulau(fromID).addConnectionToward(kumpulanPulau.getPulau(toID));
                        linesRead++;
                        line = bufferedReader.readLine();
                    }

                    isValid = linesRead == jumlahJembatan;
                    if (!isValid){
                        System.out.println("Jumlah jembatan tidak sesuai!");
                    }
                }

                bufferedReader.close();
                fileReader.close();
            }
        } catch (IOException _ioException){
            _ioException.printStackTrace();
        }

        return isValid;
    }
}