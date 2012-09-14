package com.uphyca.testing.android.suitebuilder;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;

/**
 * Filters.
 */
abstract class Filters {

    public static Filter union(final Filter first, final Filter second) {
        if (first == second || first == Filter.ALL) {
            return first;
        }
        if (second == Filter.ALL) {
            return second;
        }

        return new Filter() {
            @Override
            public boolean shouldRun(Description description) {
                return first.shouldRun(description) || second.shouldRun(description);
            }

            @Override
            public String describe() {
                return first.describe() + " or " + second.describe();
            }
        };
    }
    
    
    /**
     * FIXME  Ensure child descriptions
     * @param desiredDescription
     * @return
     */
    public static Filter matchSuiteDescription(final Description desiredDescription) {
        return new Filter() {
            @Override
            public boolean shouldRun(Description description) {
                if (description.isTest())
                    return desiredDescription.getTestClass() == description.getTestClass();
                    
                // explicitly check if any children want to run
                for (Description each : description.getChildren())
                    if (shouldRun(each))
                        return true;
                return false;                   
            }

            @Override
            public String describe() {
                return String.format("Suite %s", desiredDescription.getClassName());
            }
        };
    }
}
