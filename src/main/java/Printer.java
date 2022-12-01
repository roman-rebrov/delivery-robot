import java.util.Map;

class Printer {
    public static void sizeToFreqPrint(Map<Integer, Integer> sizeToFreq){
        synchronized (sizeToFreq) {
            final int maxKey = LetterHandler.maxValue(sizeToFreq);

            System.out.println(
                    "Самое частое количество: "
                            + maxKey + " = "
                            + sizeToFreq.remove(maxKey)
                            + "\r\n"
                            + "Другие размеры:"
            );
            for (int key : sizeToFreq.keySet()) {
                System.out.println(" - " + key + "(" + sizeToFreq.get(key) + "раз)");
            }
        }
    }
}
