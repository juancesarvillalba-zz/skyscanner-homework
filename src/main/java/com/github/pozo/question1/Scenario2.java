package com.github.pozo.question1;

import com.github.pozo.Scenario;

class Scenario2 implements Scenario {
    public String input() {
        return "5\n" +
                "Singapore\n" +
                "Bangkok\n" +
                "Singapore\n" +
                "Bangkok\n" +
                "Singapore\n";
    }

    public String output() {
        return "Singapore";
    }
}
