package io;
import java.io.*;
import java.util.Map;

public class HTMLGenerator {
    private static final String TITLE = "Toxicity of Reddit Users";
    private static void outputHeader(PrintWriter fout) {
        fout.println("<html>");
        fout.println("<head>");
        fout.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"theme.css\">");
        fout.println("<title>" + TITLE + "</title>");
        fout.println("</head>");
        fout.println("<body>\n<h2>" + TITLE + "</h2>");
        fout.println("<table><th> Reddit Account </th> <th> Level Of Toxicity </th>");
    }

    Map<String, Double> rep;
    private String filePath;
    public HTMLGenerator(Map<String,Double> userNames, String filePath) {
        this.rep = userNames;
        this.filePath = filePath;
    }
    public void generateHTML() throws IOException {
    	System.out.println("generating HTML file");
        PrintWriter pw = new PrintWriter(filePath);
        outputHeader(pw);
        for (String str : this.rep.keySet()) {
            pw.println("<td>");
            pw.println("<a href = http://reddit.com/user/" + str + ">" + str
                    + "</a></td><td>");
            double val = this.rep.get(str);
            if (val < 0.25) {
                pw.print("HIGH");
            } else if (val > 0.25 && val < 0.10) {
                pw.print("MODERATE");
            } else {
                pw.print("LOW");
            }
            pw.println("</td>");
        }
        pw.println("</table></body></html>");
        pw.close();
    }

}
