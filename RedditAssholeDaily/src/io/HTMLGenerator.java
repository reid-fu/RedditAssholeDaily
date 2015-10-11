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

    public HTMLGenerator(Map<String, Double> userNames) {
        this.rep = userNames;
    }

    public void generateHTML() throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
                "output.html")));
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
