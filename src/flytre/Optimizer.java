package flytre;

import flytre.io.Reader;
import flytre.io.Writer;
import flytre.selector.Selector;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Optimizer {

    public static void optimizeFile(File f) {
        Writer.setFileContents(f, optimize(Reader.readFile(f.getAbsolutePath())));
    }

    public static void optimizeDirectory(String path) {
        try {
            Files.find(Paths.get(path), 50, (p, bfa) -> bfa.isRegularFile() && p.getFileName().toString().matches(".*\\.mcfunction")).forEach(p -> optimizeFile(new File(String.valueOf(p))));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String optimize(String input) {

        Pattern p = Pattern.compile("(@[aeprs]\\[.*?])");
        Matcher matcher = p.matcher(input);

        return matcher.replaceAll(Selector::optimizeSelector);
    }

    public static void main(String[] args) {
        optimizeDirectory(JOptionPane.showInputDialog("Enter the absolute file path to your directory"));
        JOptionPane.showMessageDialog(null,"Optimization Complete!");
    }


}
