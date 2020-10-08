package mocks;

import java.util.ArrayList;
import java.util.logging.Logger;

public class FakeLogger extends Logger {
    private final ArrayList<String> entries = new ArrayList<>();

    public FakeLogger() {
        super("fake", null);
    }

    public ArrayList<String> getEntries() {
        return entries;
    }

    @Override
    public void info(String msg) {
        entries.add(msg);
    }


}
