package com.github.pozo.question2;

import com.github.pozo.Scenario;

class Scenario2 implements Scenario {
    public String input() {
        return "4\n" +
                "Simon\n" +
                "Claudiu\n" +
                "Sarah Claudiu\n" +
                "Sarah Paul\n" +
                "Claudiu Simon\n";
    }

    public String output() {
        return "Claudiu";
    }
}
