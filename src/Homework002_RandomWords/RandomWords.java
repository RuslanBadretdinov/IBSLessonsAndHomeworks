package Homework002_RandomWords;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomWords {
    private TreeMap<String, Integer> randomWords = new TreeMap<>();
    private Integer randomWordsCount = 0;

    private final Path startDir = Paths.get(System.getProperty("user.dir")).getRoot();
    private Path neededFilePath;

    private List<Path> potentialPaths = new ArrayList<>();
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        RandomWords randomWords = new RandomWords();
        try {
            if (randomWords.definePath()) {
                randomWords.readRandomWordsToMap(randomWords.neededFilePath);
                randomWords.printAllWordsAlphabetSortDefault(randomWords.randomWords);
                randomWords.getMostPopularWords(randomWords.randomWords);

                randomWords.printAllWordsAlphabetSortDefault(randomWords.convertAllWordsAlphabetSortDefaultToLowerCase());
                randomWords.getMostPopularWords(randomWords.convertAllWordsAlphabetSortDefaultToLowerCase());
            } else {
                System.out.println("Путь определён не был. Программа завершилась. Если файл не был пересён в корень: " + randomWords.startDir.toString() + " , то его нужно перенести");
            }
            randomWords.br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readRandomWordsToMap(Path path) {
        try(BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            Pattern pattern = Pattern.compile("\\b[a-zA-Zа-яА-Я0-9]+((-)?([a-zA-Zа-яА-Я0-9]+))*\\b"); //учитывает слово: мать-и-мачеха, иван-да-марья и т.д наверное.
            Matcher matcher;
            while ( (line = br.readLine() ) != null) {
                matcher = pattern.matcher(line);
                while(matcher.find()) {
                    if (randomWords.containsKey(matcher.group())) {
                        randomWords.put(matcher.group(), randomWords.get(matcher.group()) + 1);
                    } else {
                        randomWords.put(matcher.group(), 1);
                    }
                    randomWordsCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAllWordsAlphabetSortDefault(TreeMap<String, Integer> randomWords) {
        if (randomWords.isEmpty()) {
            System.out.println("\n\tВ данном файле не было искомых слов");
        } else {
            System.out.println("\n\tВывод статистики слов из файла, отсортированных в алфавитном порядке:");
            System.out.println(randomWords);
        }
    }

    public void getMostPopularWords(TreeMap<String, Integer> randomWords) {
        LinkedHashMap<String, Float> mostPopularWords = new LinkedHashMap<>();
        int maxCount = 0;
        if (!randomWords.isEmpty()) {
            for (Map.Entry<String, Integer> entry : randomWords.entrySet())
            {
                if (maxCount < entry.getValue()) {
                    mostPopularWords.clear();
                    maxCount = entry.getValue();
                    mostPopularWords.put(entry.getKey(), maxCount*100f/randomWordsCount);
                } else if (maxCount == entry.getValue()) mostPopularWords.put(entry.getKey(), maxCount*100f/randomWordsCount);
            }
            System.out.println("\n\tВывод слов с максимальной частотой в процентах:");
            System.out.println(mostPopularWords);
        } else {
            System.out.println("\n\tВ искомом файле отсутствуют слова");
        }
    }

    public boolean definePath() throws IOException{
        if (!this.isAbsolutePath()) {
            return definePotentialPaths() && choiceNeededPotentialPath();
        } else {
            return true;
        }
    }

    public boolean choiceNeededPotentialPath() throws  IOException{
        if (potentialPaths.size() == 1) {
            System.out.println("Был определён один путь:\t"+potentialPaths.get(0));
            neededFilePath = potentialPaths.get(0);
            return true;
        } else {
            if (potentialPaths.size() < 11) {
                System.out.println("Было найдено 10 и менее абсолютных путей, на основании введённого пути.");
                System.out.println("\tЕсли нужный путь имеется среди предложенных, то введите нужный, либо введите абсолютный путь:");
                for (int i = 0; i < potentialPaths.size(); i++) {
                    System.out.println("\t" + i + "\t" + potentialPaths.get(i).toAbsolutePath().toString());
                }
                if (this.isAbsolutePath() || potentialPaths.contains(neededFilePath)) {
                    return true;
                }
            }
        }
        System.out.println("Было найдено более 10 абсолютных путей");
        neededFilePath = null;
        return false;
    }

    public boolean definePotentialPaths() throws IOException{
        FileVisitor<Path> fileVisitor = new FileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (file.toString().contains(neededFilePath.toString()) &&
                        (file.toString().length()-file.toString().lastIndexOf(neededFilePath.toString()) == neededFilePath.toString().length()) ) {
                    potentialPaths.add(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(startDir, fileVisitor);
        return potentialPaths.size() > 0;
    }

    public boolean isAbsolutePath() throws IOException{
        this.neededFilePath = readPath();
        if (this.neededFilePath.isAbsolute() && Files.exists(this.neededFilePath) && Files.isRegularFile(this.neededFilePath)) {
            System.out.println("Был введён существующий путь к файлу");
            return true;
        }
        System.out.println("Был введён относительный, либо несуществующий путь к файлу");
        return false;
        //  src/Homework002_RandomWords/Random_Words.txt
        //  G:\Users\qwerty\IdeaProjects\IBSLessonsAndHomeworks\src\Homework002_RandomWords\Random_Words.txt
    }

    public Path readPath() throws IOException{
        String pathName;
        pathName = br.readLine();
        if (pathName.equals("")) {
            throw new IOException("В адрес было введено пустое поле: \"\"");
        }
        return Path.of(pathName);
    }

    private TreeMap<String, Integer> convertAllWordsAlphabetSortDefaultToLowerCase() {
        TreeMap<String, Integer> randomWordsToLowerCase = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : randomWords.entrySet()) {
            if (randomWordsToLowerCase.containsKey(entry.getKey().toLowerCase(Locale.getDefault()))) {
                randomWordsToLowerCase.put(entry.getKey().toLowerCase(), randomWordsToLowerCase.get(entry.getKey().toLowerCase())+entry.getValue());
            } else {
                randomWordsToLowerCase.put(entry.getKey().toLowerCase(), entry.getValue());
            }
        }
        System.out.println("\n---------------------------------------------------------------------------------" +
                "\nСледующая операция не будет учитывать регистр при подсчёте выборки \"toLowerCase\""+
                "\n---------------------------------------------------------------------------------");
        return randomWordsToLowerCase;
    }
}
