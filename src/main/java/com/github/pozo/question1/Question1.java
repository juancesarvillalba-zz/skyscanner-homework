package com.github.pozo.question1;

import com.github.pozo.Scenario;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class Question1 {
    public static void main(String[] args) {
        Scenario scenario = new Scenario1();
        String solution = solution(scenario);

        assertEquals(scenario.output(), solution);

        scenario = new Scenario2();
        solution = solution(scenario);

        assertEquals(scenario.output(), solution);
    }

    private static String solution(Scenario scenario) {
        Scanner in = new Scanner(scenario.input());
        HashMap<String, Integer> cities = new HashMap<>();

        // skip first line
        in.hasNextLine();
        in.nextLine();

        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (cities.containsKey(line)) {
                Integer currentValue = cities.get(line);
                cities.put(line, currentValue + 1);
            } else {
                cities.put(line, 1);
            }
        }
        Optional<Map.Entry<String, Integer>> mostFrequentCity = cities.entrySet()
                .stream()
                .max((entry1, entry2) ->
                        Integer.compare(entry1.getValue(), entry2.getValue())
                );

        if (mostFrequentCity.isPresent()) {
            return mostFrequentCity.get().getKey();
        } else {
            return "";
        }
    }
}
