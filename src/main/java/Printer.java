import java.util.Map;

class Printer {
    public static void sizeToFreqPrint(Map<Integer, Integer> sizeToFreq){
            final int maxKey = LetterHandler.maxValue(sizeToFreq);

            System.out.println(
                    "Самое частое количество: "
                            + maxKey + " = "
                            + sizeToFreq.get(maxKey)
            );

    }
}
