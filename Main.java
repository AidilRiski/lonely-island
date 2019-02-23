import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Main{
    public static KumpulanPulau kumpulanPulau;
    public static void main(String args[]){
        Scanner inputStream = new Scanner(System.in);
        String fileName = inputStream.nextLine();
        readFile(fileName);

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

                bufferedReader.close();
                fileReader.close();

                int jumlahPulau = Integer.parseInt(firstLine.toString().split(" ")[0]);
                int jumlahJembatan = Integer.parseInt(firstLine.toString().split(" ")[1]);
                int indeksPulauPertama = Integer.parseInt(firstLine.toString().split(" ")[2]);

                System.out.println("Jumlah Pulau: " + jumlahPulau);
                System.out.println("Jumlah Jembatan: " + jumlahJembatan);
                System.out.println("Kamu mulai di pulau ke-" + indeksPulauPertama + ".");

                isValid = indeksPulauPertama > 0 && indeksPulauPertama <= jumlahPulau;

                if (isValid){
                    kumpulanPulau = new KumpulanPulau(jumlahPulau);
                    
                }else {
                    System.out.println("File tidak valid!");
                }

            }
        } catch (IOException _ioException){
            _ioException.printStackTrace();
        }

        return isValid;
    }
}