package cn.az.code.stream;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Liz
 */
@Slf4j
public class SpliteratorDemos {

    private static final String SENTENCE = "Hello World Java TypeScript";

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Spliterator.OfInt sInt = Arrays.spliterator(arr, 2, 5);
        sInt.tryAdvance((Consumer<? super Integer>) i -> log.info(String.valueOf(i)));
        log.error(String.valueOf(countWordsIteratively(SENTENCE)));

        Stream<Character> s1 = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);

        log.warn("s1: " + WordCounter.countWords(s1));
        Spliterator<Character> spliterator = new WordCountSpliterator(SENTENCE);
        Stream<Character> s2 = StreamSupport.stream(spliterator, true);

        log.warn("s2: " + WordCounter.countWords(s2));
    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }
}
