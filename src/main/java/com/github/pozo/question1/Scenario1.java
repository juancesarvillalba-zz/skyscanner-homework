package com.github.pozo.question1;

import com.github.pozo.Scenario;

class Scenario1 implements Scenario {
    public final String input() {
        return "6\n" +
                "Barcelona\n" +
                "Edinburgh\n" +
                "Barcelona\n" +
                "Miami\n" +
                "Miami\n" +
                "Barcelona\n";
    }

    public final String output() {
        return "Barcelona";
    }
}
