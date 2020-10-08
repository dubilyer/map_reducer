package matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.regex.Pattern;

public class ContainsRegex extends TypeSafeMatcher<String> {
    String regex;

    public ContainsRegex(String regex) {
        this.regex = regex;
    }

    @Override
    protected boolean matchesSafely(String s) {
        return Pattern.compile(regex).asMatchPredicate().test(s);
    }

    @Override
    public void describeTo(Description description) {
        System.out.println(description);
    }

    public static Matcher<String> containsRegex(String regex){
        return new ContainsRegex(regex);
    }
}
