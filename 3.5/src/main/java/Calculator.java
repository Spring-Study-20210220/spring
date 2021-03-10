import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public Integer calcSum(String filepath) throws IOException {
        return lineReadTemplate(filepath, 0, ((line, value) -> value + Integer.parseInt(line)));
    }

    public Integer calcMultiply(String filePath) throws IOException {
        return lineReadTemplate(filePath, 1, (line, value) -> value * Integer.parseInt(line));
    }

    public String concatenate(String filePath) throws IOException {
        return lineReadTemplate(filePath, "", ((line, value) -> value+line));
    }

    private <T> T lineReadTemplate(String filePath, T initVal, LineCallback<T> callback) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            T result = initVal;
            String line = null;
            while((line = br.readLine()) != null) {
                result = callback.doSomethingWithLine(line, result);
            }
            return result;
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
