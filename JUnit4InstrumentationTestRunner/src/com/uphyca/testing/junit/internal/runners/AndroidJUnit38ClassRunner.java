package com.uphyca.testing.junit.internal.runners;

import junit.extensions.TestDecorator;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.junit.runner.Describable;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.manipulation.Sortable;
import org.junit.runner.manipulation.Sorter;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import android.app.Instrumentation;
import android.content.Context;
import android.os.PerformanceCollector.PerformanceResultsWriter;

import com.uphyca.testing.Infrastructure;

/**
 * Modified version of org.junit.internal.runners.JUnit38ClassRunner.
 */
public class AndroidJUnit38ClassRunner extends Runner implements Filterable, Sortable {
	private final class OldTestClassAdaptingListener implements
			TestListener {
		private final RunNotifier fNotifier;

		private OldTestClassAdaptingListener(RunNotifier notifier) {
			fNotifier= notifier;
		}

		public void endTest(Test test) {
			fNotifier.fireTestFinished(asDescription(test));
		}

		public void startTest(Test test) {
			fNotifier.fireTestStarted(asDescription(test));
		}

		// Implement junit.framework.TestListener
		public void addError(Test test, Throwable t) {
			Failure failure= new Failure(asDescription(test), t);
			fNotifier.fireTestFailure(failure);
		}

		private Description asDescription(Test test) {
			if (test instanceof Describable) {
				Describable facade= (Describable) test;
				return facade.getDescription();
			}
			return Description.createTestDescription(getEffectiveClass(test), getName(test));
		}

		private Class<? extends Test> getEffectiveClass(Test test) {
			return test.getClass();
		}

        //Set class name for suite to avoid display as "junit.framework.TestStuite.1"
		//this(TestSuite.createTest(klass.asSubclass(TestCase.class), klass.getName()));

		private String getName(Test test) {
            if (test instanceof TestCase)
                return ((TestCase) test).getName();
			else
			    return test.toString();
		}

		public void addFailure(Test test, AssertionFailedError t) {
			addError(test, t);
		}
	}

	private Test fTest;
	
	public AndroidJUnit38ClassRunner(final Class<?> klass) {
	    this(new TestSuite(klass.asSubclass(TestCase.class)));
	}

	public AndroidJUnit38ClassRunner(Test test) {
		super();
		setTest(test);
	}

	@Override
	public void run(RunNotifier notifier) {
	    //For Android
		AndroidTestResult result= new AndroidTestResult();
		
		result.addListener(createAdaptingListener(notifier));
		getTest().run(result);
	}

	public TestListener createAdaptingListener(final RunNotifier notifier) {
		return new OldTestClassAdaptingListener(notifier);
	}
	
	@Override
	public Description getDescription() {
		return makeDescription(getTest());
	}

	private Description makeDescription(Test test) {
		if (test instanceof TestCase) {
			TestCase tc= (TestCase) test;
			//FIXME When test constructed fail then tc.getClass() returns junit.framework.TestSuite.1 that created in TestSuite.warring()
			return Description.createTestDescription(tc.getClass(), tc.getName());
		} else if (test instanceof TestSuite) {
			TestSuite ts= (TestSuite) test;
			String name= ts.getName() == null ? createSuiteDescription(ts) : ts.getName();
			Description description= Description.createSuiteDescription(name);
			int n= ts.testCount();
			for (int i= 0; i < n; i++) {
				Description made= makeDescription(ts.testAt(i));
				description.addChild(made);
			}
			return description;
		} else if (test instanceof Describable) {
			Describable adapter= (Describable) test;
			return adapter.getDescription();
		} else if (test instanceof TestDecorator) {
			TestDecorator decorator= (TestDecorator) test;
			return makeDescription(decorator.getTest());
		} else {
			// This is the best we can do in this case
			return Description.createSuiteDescription(test.getClass());
		}
	}

	private static String createSuiteDescription(TestSuite ts) {
		int count= ts.countTestCases();
		String example = count == 0 ? "" : String.format(" [example: %s]", ts.testAt(0));
		return String.format("TestSuite with %s tests%s", count, example);
	}

	public void filter(Filter filter) throws NoTestsRemainException {
		if (getTest() instanceof Filterable) {
			Filterable adapter= (Filterable) getTest();
			adapter.filter(filter);
		} else if (getTest() instanceof TestSuite) {
			TestSuite suite= (TestSuite) getTest();
			TestSuite filtered= new TestSuite(suite.getName());
			int n= suite.testCount();
			for (int i= 0; i < n; i++) {
				Test test= suite.testAt(i);
				if (filter.shouldRun(makeDescription(test)))
					filtered.addTest(test);
			}
			setTest(filtered);
		}
	}

	public void sort(Sorter sorter) {
		if (getTest() instanceof Sortable) {
			Sortable adapter= (Sortable) getTest();
			adapter.sort(sorter);
		}
	}

	private void setTest(Test test) {
		fTest = test;
	}

	private Test getTest() {
		return fTest;
	}
	
	//For Android
	
	private static final class AndroidTestResult extends TestResult {
	    private final Context fContext;
	    private final Context fTestContext;
	    private final Instrumentation fInstrumentation;
	    private final PerformanceResultsWriter fPerformanceResultsWriter;
	    
	    public AndroidTestResult() {
	        fContext = Infrastructure.getContext();
	        fTestContext = Infrastructure.getTestContext();
	        fInstrumentation = Infrastructure.getInstrumentation();
	        fPerformanceResultsWriter = Infrastructure.getPerformanceResultsWriter();
        }
	    
	    @Override
	    protected void run(TestCase test) {
	        Infrastructure.setContextIfAndroidTestCase(test, fContext, fTestContext);
	        Infrastructure.setInstrumentationIfInstrumentationTestCase(test, fInstrumentation);
	        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(test, fPerformanceResultsWriter);
	        super.run(test);
	    }
	}
}
