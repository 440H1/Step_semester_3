import java.util.*;
class WordFrequencyReporter {
    void printFilteredWordFrequency(String text) {
        String t = text.toLowerCase().replaceAll("[^a-z ]", "");
        String[] w = t.split("\\s+");
        Set<String> stop = new HashSet<>(Arrays.asList("the","was","and","a","is"));
        Map<String,Integer> m = new HashMap<>();
        for (String x : w) {
            if (!stop.contains(x) && !x.isEmpty()) {
                m.put(x, m.getOrDefault(x, 0) + 1);
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<>(m.entrySet());
        list.sort((a,b) -> b.getValue().compareTo(a.getValue()));
        for (Map.Entry<String,Integer> e : list) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        new WordFrequencyReporter().printFilteredWordFrequency(s);
    }
}