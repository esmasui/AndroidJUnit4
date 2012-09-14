package com.uphyca.testing;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

//TODO Use this class instead of Tester's initilization code.
class InjectInstrumentationRule implements TestRule {

    private InstrumentationSupport _test;

    public InjectInstrumentationRule(InstrumentationSupport test) {
        _test = test;
    }

    @Override
    public Statement apply(final Statement statement,
                           final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                _test.injectInstrumentation(Infrastructure.getInstrumentation());
                statement.evaluate();
            }
        };
    }
}
