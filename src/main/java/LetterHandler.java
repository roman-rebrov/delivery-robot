import java.util.Map;
import java.util.stream.Stream;

class LetterHandler {
    private static final char aChar = "R".charAt(0);

    public static int letterCount(String string){
        int count = 0;
        for(char c : string.toCharArray()){
            if (c == aChar){
                count++;
            }
        }
        return count;
    }

    public static int  maxValue(Map<Integer, Integer> store) {
        int count = 0, k = 0;

        for (Integer key: store.keySet()){
            int value = store.get(key);
            if (count < value){
                count = value;
                k = key;
            }
        }

        return k;
    }
}
