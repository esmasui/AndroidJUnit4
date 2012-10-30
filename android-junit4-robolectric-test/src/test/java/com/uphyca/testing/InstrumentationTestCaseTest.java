package com.uphyca.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;

import com.uphyca.testing.robolectric.MainActivity;
import com.xtremelabs.robolectric.res.RobolectricPackageManager;

@RunWith(AndroidTestRunner.class)
public class InstrumentationTestCaseTest extends InstrumentationTestCase {

    @SuppressWarnings("serial")
    private static final class Expected extends RuntimeException {
    }

    private static final String TEST_PACKAGE_NAME = "com.uphyca.testing.robolectric.test";

    private Context mContext;
    private Instrumentation mInstr;

    @Before
    public void setUp() {
        mInstr = getInstrumentation();
        mContext = mInstr.getTargetContext();

        prepareForResolveActivity();
    }

    @Test
    public void assertPreconditins() {
        assertNotNull(getInstrumentation());
        assertNotNull(getInstrumentation().getTargetContext());
        assertNotNull(getInstrumentation().getContext());
    }

    private void prepareForResolveActivity() {
        Intent intent = new Intent(Intent.ACTION_MAIN).setClassName(TEST_PACKAGE_NAME, MainActivity.class.getName());
        RobolectricPackageManager rpm = RobolectricPackageManager.class.cast(mContext.getPackageManager());

        ResolveInfo info = new ResolveInfo();

        IntentFilter filter = new IntentFilter(Intent.ACTION_MAIN);
        info.filter = filter;

        ActivityInfo activityInfo = new ActivityInfo();
        info.activityInfo = activityInfo;

        activityInfo.packageName = TEST_PACKAGE_NAME;
        activityInfo.name = MainActivity.class.getName();

        ApplicationInfo applicationInfo = new ApplicationInfo();
        activityInfo.applicationInfo = applicationInfo;

        applicationInfo.packageName = TEST_PACKAGE_NAME;

        rpm.addResolveInfoForIntent(intent, Arrays.asList(info));
    }

    @Test
    public void assertThatPrepareForResolveActivity() throws NameNotFoundException {

        Intent intent = new Intent(Intent.ACTION_MAIN).setClassName(TEST_PACKAGE_NAME, MainActivity.class.getName());
        PackageManager pm = mContext.getPackageManager();
        ComponentName component = intent.getComponent();

        assertNotNull(component);

        ActivityInfo activityInfo = pm.getActivityInfo(component, 0);

        // Robolectric doesn't implements PackageManager.getActivityInfo()
        assertNull(activityInfo);
    }

    @Test
    public void shouldLaunchActivity() {
        Activity activity = launchActivity(TEST_PACKAGE_NAME, MainActivity.class, null);
        assertNotNull(activity);
    }

    @Test(expected = Expected.class)
    public void shouldRunTestOnUiThread() throws Throwable {
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Notify that the task invoked.
                throw new Expected();
            }
        });
    }
}
