package matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IsSorted extends TypeSafeMatcher<List<String>> {

    private final Comparator<? super String> comparator;

    public IsSorted(Comparator<? super String> comparator) {
        this.comparator = comparator;
    }

    @Override
    protected boolean matchesSafely(List<String> strings) {
        return strings.stream().sorted(comparator).collect(Collectors.toList()).equals(strings);
    }

    @Override
    public void describeTo(Description description) {
        System.out.println(description);
    }

    public static Matcher<List<String>> isSortedAccordingTo(Comparator<? super String> comparator){
        return new IsSorted(comparator);
    }
}
