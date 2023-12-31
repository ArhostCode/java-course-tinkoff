package edu.hw3.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public final class BracketClusterer {

    private static final Pattern BRACKET_PATTERN = Pattern.compile("[()]*");

    private BracketClusterer() {
    }

    public static List<String> clusterize(String original) {
        if (original == null) {
            throw new IllegalArgumentException("Original must be not null");
        }
        if (!BRACKET_PATTERN.matcher(original).matches()) {
            throw new IllegalArgumentException("Original must contains only () bracket");
        }

        int openedBracketCount = 0;
        List<String> result = new ArrayList<>();
        StringBuilder currentCluster = new StringBuilder();

        for (char c : original.toCharArray()) {
            currentCluster.append(c);
            if (c == '(') {
                openedBracketCount++;
            } else {
                openedBracketCount--;
            }
            if (openedBracketCount == 0) {
                result.add(currentCluster.toString());
                currentCluster = new StringBuilder();
            } else if (openedBracketCount < 0) {
                return Collections.emptyList(); // Not valid situation
            }
        }
        if (openedBracketCount > 0) {
            return Collections.emptyList(); // Not valid situation
        }
        return result;
    }

}
