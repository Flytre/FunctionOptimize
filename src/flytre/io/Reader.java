package flytre.io;

import java.io.*;
import java.util.Scanner;

public class Reader {

    public static String promptFile() {
        System.out.println("Enter File Path");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static String readFile(String filepath) {
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(filepath));) {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.getProperty("line.separator"));
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
