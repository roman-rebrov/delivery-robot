import java.util.Map;
import java.util.concurrent.*;

class ConcurrentApp {

    private final String letters = "RLRFR";
    private final int letterLength = 1000;
    private final int threadNumber = 1000;
    private final ExecutorService threads = Executors.newFixedThreadPool(threadNumber);

    public void run() {

        Map<Integer, Integer> sizeToFreq = Storage.getSizeToFreq();

        Thread printer = new Thread(() -> {

            while (!Thread.interrupted()) {

                if (threads.isShutdown()) {
                    Thread.currentThread().interrupt();
                } else {

                    synchronized (sizeToFreq) {

                        try {

                            sizeToFreq.wait();
                            Printer.sizeToFreqPrint(sizeToFreq);


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        printer.start();


        for (int i = 0; i < threadNumber; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            threads.submit(() -> {
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }

                final String generatedLetters = Generator.generateRoute(this.letters, this.letterLength);
                final int letterCounted = LetterHandler.letterCount(generatedLetters);
                int value = 1;

                synchronized (sizeToFreq) {
                    if (sizeToFreq.containsKey(letterCounted)) {
                        value = sizeToFreq.get(letterCounted);
                        sizeToFreq.put(letterCounted, ++value);
                    } else {
                        sizeToFreq.put(letterCounted, value);
                    }
                    sizeToFreq.notify();

                }

            });

        }

        threads.shutdown();

    }
}