import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

class ConcurrentApp {

    private final String letters = "RLRFR";
    private final int letterLength = 100;
    private final int threadNumber = 100;
    private final ExecutorService threads = Executors.newFixedThreadPool(threadNumber);
    private final List<Future> taskList = new ArrayList<>();

    public void run() {

        for (int i = 0; i < threadNumber; i++) {
            final Future task = threads.submit(() -> {

                final String generatedLetters = Generator.generateRoute(this.letters, this.letterLength);
                final int letterCounted = LetterHandler.letterCount(generatedLetters);
                int value = 1;

                Map<Integer, Integer> sizeToFreq = Storage.getSizeToFreq();
                synchronized (sizeToFreq) {
                    if (sizeToFreq.containsKey(letterCounted)) {
                        value = sizeToFreq.get(letterCounted);
                        sizeToFreq.put(letterCounted, ++value);
                    } else {
                        sizeToFreq.put(letterCounted, value);
                    }
                }
            });
            taskList.add(task);
        }

        // ********************************************************
        for (Future t : taskList) {
            try {
                t.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // ********************************************************
        Map<Integer, Integer> sizeToFreq = Storage.getSizeToFreq();
        Printer.sizeToFreqPrint(sizeToFreq);

        threads.shutdown();

    }
}
