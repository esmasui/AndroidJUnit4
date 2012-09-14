package com.uphyca.testing.junit.internal.builders;

import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.internal.builders.AnnotatedBuilder;
import org.junit.internal.builders.JUnit3Builder;

/**
 * Modified version of
 * org.junit.internal.builders.AllDefaultPossibilitiesBuilder.
 */
public class AndroidAllDefaultPossibilitiesBuilder extends AllDefaultPossibilitiesBuilder {

    public AndroidAllDefaultPossibilitiesBuilder(boolean canUseSuiteMethod) {
        super(canUseSuiteMethod);
    }

    @Override
    protected AnnotatedBuilder annotatedBuilder() {
        return new AndroidAnnotatedBuilder(this);
    }

    @Override
    protected JUnit3Builder junit3Builder() {
        return new AndroidJUnit3Builder();
    }
}
