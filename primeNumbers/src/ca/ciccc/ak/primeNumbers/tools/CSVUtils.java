package ca.ciccc.ak.primeNumbers.tools;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVUtils {

    private static final char DEFAULT_SEPARATOR = ',';
    
    
    
    public static void writeLine(Writer w, List<?> values, boolean append) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ', append);
    }

    public static void writeLine(Writer w, List<?> values, char separators, boolean append) throws IOException {
        writeLine(w, values, separators, ' ', append);
    }

    //https://tools.ietf.org/html/rfc4180
    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    public static void writeLine(Writer w, List<?> values, char separators, char customQuote, boolean append) throws IOException {

        //default customQuote is empty
    	
        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (Object value : values) {
            if (append) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value.toString()));
            } else {
                sb.append(customQuote).append(followCVSformat(value.toString())).append(customQuote);
            }

            append = true;
        }
        //sb.append("\n");
        w.append(sb.toString());


    }

}