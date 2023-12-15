package connection;
import model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Connection {
    private static Connection instance;

    private Connection(){}

    public static Connection getConnectionInstance() {
        //pake singleton
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    //buka sekaligus baca file csv
    public List<String[]> readCSV(String fileName){
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    //buka sekaligus tulis ke file csv
    public void writeCSV(List<Model> dataList, String fileName){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName), true))){
            for(Model data : dataList){
                String line = data.toString();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
