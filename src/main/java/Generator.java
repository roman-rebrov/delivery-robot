import java.util.Random;

 class Generator {
    public static String generateRoute(String letters, int length){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append(letters.charAt(random.nextInt(letters.length())));
        }
        return builder.toString();
    }
}
