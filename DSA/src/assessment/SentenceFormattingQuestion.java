package assessment;

import java.util.ArrayList;
import java.util.List;

public class SentenceFormattingQuestion {
    static void main() {
        String[][] paragraphs = {
    {"A", "B", "C", "D"},
    {"E", "F"}
};
      String[] aligns = {"LEFT", "RIGHT"};

      int width = 3;
      solution(paragraphs, aligns, width);
    }
    public static String[] solution(String[][] paragraphs, String[] aligns, int width) {
        List<String> res = new ArrayList<>();
        res.add("*".repeat(width + 2));
        for (int i = 0; i < paragraphs.length; i++) {
           String[] paragraph = paragraphs[i];
           String alignment = aligns[i];
           StringBuilder line = new StringBuilder();
            for (int j = 0; j < paragraph.length; j++) {
                String word = paragraph[j];
                if(j == 0){
                    line.append(word);
                }
                else if(line.length() + 1 + word.length() <= width){
                    line.append(" ").append(word);
                }
                else{
                    // format current line and go to next line
                    if(alignment.equals("LEFT")){
                      res.add("*" + " ".repeat(width - line.length()) + line + "*");
                    }
                    else res.add("*" + line + " ".repeat(width - line.length()) +  "*");

                    // go to next line and insert the word
                    line = new StringBuilder();
                    line.append(word);
                }
                if(line.length() == width){
                    res.add("*" + line + "*");
                    line = new StringBuilder();
                }
            }
        }
        res.add("*".repeat(width + 2));
        System.out.println(res);
        return res.toArray(new String[0]);
    }
}
