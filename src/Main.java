import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws Exception {
        String c = "0123456";
        System.out.println(c.substring(0,2));
//        these ones are for testing:
//        Parser parser = new Parser("(5+8or10*11+6)/(32-18)and6>=17and13");
//        Parser parser = new Parser("1+(26-98)/15+777<28");
//        Parser parser = new Parser("(5+8or10*11+6)/(32-18)and6>=17and13");
//        System.out.println("Insert your expression. Without spaces!");
        ArrayList<String> input = readString();
//        input = input.replaceAll(" ", "");
        for (String string:input) {
            string = string.replaceAll("-", "+-");
            string = string.replaceAll("\\(\\+-", "(-");
            string = string.replaceAll("<\\+-", "<-");
            string = string.replaceAll(">\\+-", ">-");
            Parser parser = new Parser(string);
            Expression expressionTree = parser.parse();
            expressionTree.print();
            writeString(expressionTree.calculate()+"\n");
        }
    }

    /**
     * reads two strings from the file
     * @return array of strings
     */
    private static ArrayList<String> readString() {
        ArrayList<String> result = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            String line;
            while (sc.hasNextLine()){
                result.add(sc.nextLine());
            }
        } catch (FileNotFoundException ex) {
            return null;
        }
        return result;
    }
    /**
     * writes a string to the file
     * @param s
     */
    private static void writeString(String s) {
        try {
            try (Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("../output.txt", true), "ascii"))) {
                writer.write(s);
                writer.close();
            }
        } catch (IOException ex) {
        }
    }
}

