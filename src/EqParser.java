import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.*;
import java.util.stream.Collectors;

public class EqParser {
    //StringBuffer buffer = new StringBuffer();
    private String pttrn = "";
    private String parsedEqStringInsallah = "";
    EqParser(final String eqfile) throws IOException {
        List<String> lines;
        Pattern eqPattern = Pattern.compile("(GraphicEQ:) ((([0-9]*) (-[0-9]*.[0-9]);?\\u0020?)*)");

        try (final BufferedReader stream = new BufferedReader(new InputStreamReader(new FileInputStream(eqfile)))) {
            lines = stream.lines().collect(Collectors.toList());
        }
        Matcher matchedEQ = eqPattern.matcher(lines.toString());
        if (matchedEQ.find( )) {
            pttrn = matchedEQ.group(2);
        }
    }
    public String parseEqString(){
        String parsedEqStringInsallah = "";
        List<Map.Entry<Integer, Double>> list =
                new LinkedList<Map.Entry<Integer, Double>>
                        ((Collection<? extends Map.Entry<Integer, Double>>) EQList().entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return (o1.getKey().compareTo(o2.getKey()));
            }
        });

       for (Map.Entry<Integer,Double> i : list){
           parsedEqStringInsallah += (i.getKey() > 1000 ? i.getKey()/1000.0 + "k" : i.getKey()) + "=" + i.getValue()  + ",";
       }
       return parsedEqStringInsallah;
    }

    public Map<Integer,Double> EQList(){
        String[] eqArray = pttrn.split(";\\u0020");
        Map<Integer,Double> result = new HashMap<>();
        for (int i = 0; i < eqArray.length; i++){
            result.put(Integer.parseInt(eqArray[i].split("\\u0020")[0]),
                    Double.parseDouble(eqArray[i].split("\\u0020")[1]));
        }
        return result;
    }

}
