import java.util.HashMap;
import java.util.Map;

class Storage {
    private static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static Map<Integer, Integer> getSizeToFreq() {
        return sizeToFreq;
    }
}
