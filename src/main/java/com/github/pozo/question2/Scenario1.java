package com.github.pozo.question2;

import com.github.pozo.Scenario;

class Scenario1 implements Scenario {
    public final String input() {
        return "6\n" +
                "Hilary\n" +
                "James\n" +
                "Sarah Fred\n" +
                "Sarah Paul\n" +
                "Fred Hilary\n" +
                "Fred Jenny\n" +
                "Jenny James\n";
    }

    public final String output() {
        return "Fred";
    }
}
