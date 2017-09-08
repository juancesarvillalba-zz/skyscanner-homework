package com.github.pozo.question2;

import com.github.pozo.Scenario;

public class Scenario3 implements Scenario {
    public String input() {
        return "5\n" +
                "Gareth\n" +
                "Alex\n" +
                "June Alex\n" +
                "June Qing\n" +
                "Qing Paul\n" +
                "Qing Gareth\n";
    }

    public String output() {
        return "June";
    }
}
