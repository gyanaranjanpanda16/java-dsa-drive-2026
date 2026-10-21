package questions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import questions.support.Check;

/** Execute every published question's checks and compare its actual demonstration output. */
public final class QuestionBankTest {
    private QuestionBankTest() { }

    public static void main(String[] args) throws Exception {
        Set<String> ids = new HashSet<>();
        Set<String> names = new HashSet<>();
        int completed = 0;
        for (String line : Files.readAllLines(Path.of("question-bank/index.tsv"), StandardCharsets.UTF_8)) {
            if (line.isBlank() || line.startsWith("#")) continue;
            String[] fields = line.split("\t", -1);
            Check.that(fields.length == 3, "Each index row needs an ID, class, and encoded output");
            Check.that(ids.add(fields[0]), "Duplicate question ID: " + fields[0]);
            Check.that(names.add(fields[1]), "Duplicate question class: " + fields[1]);
            Class<?> question = Class.forName(fields[1]);
            try {
                long before = Check.count();
                question.getMethod("selfTest").invoke(null);
                Check.that(Check.count() - before >= 3, fields[1] + " needs at least three independent checks");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                PrintStream original = System.out;
                try (PrintStream captured = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
                    System.setOut(captured);
                    question.getMethod("main", String[].class).invoke(null, (Object) new String[0]);
                } finally {
                    System.setOut(original);
                }
                String expected = new String(Base64.getDecoder().decode(fields[2]), StandardCharsets.UTF_8);
                String actual = bytes.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
                Check.that(actual.equals(expected), fields[1] + " sample output differs from the published answer");
            } catch (InvocationTargetException failure) {
                throw new AssertionError("Question " + fields[0] + " failed: " + fields[1], failure.getCause());
            }
            completed++;
        }
        Check.that(completed == 160, "This partial edition must contain exactly 160 published questions");
        System.out.println("QuestionBankTest: " + completed + " question programs verified; " + Check.count() + " checks passed.");
    }
}
