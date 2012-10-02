/*
 * Copyright (C) 2012 uPhyca Inc.
 * 
 * Base on previous work by
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uphyca.testing;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.uphyca.testing.adapter.ActivityAdapter;
import com.uphyca.testing.delegate.IActivity;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.shadows.ShadowApplication;

public class RobolectricInstrumentation extends Instrumentation {

    private static final class ActivityWaiter {
        public final Intent intent;
        public Activity activity;

        public ActivityWaiter(Intent _intent) {
            intent = _intent;
        }
    }

    private static IActivity delegate(Activity activity) {
        return ActivityAdapter.create(activity);
    }

    private Context mAppContext;
    private final Object mSync = new Object();
    private List<ActivityWaiter> mWaitingActivities;

    public RobolectricInstrumentation() {
        ShadowApplication application = Robolectric.getShadowApplication();
        mAppContext = application.getApplicationContext();
    }

    private final void validateNotAppThread() {
        //noop.
    }

    /* (non-Javadoc)
     * @see android.app.Instrumentation#getTargetContext()
     */
    public Context getTargetContext() {
        return mAppContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnCreate(android.app.Activity,
     * android.os.Bundle)
     */
    @Override
    public void callActivityOnCreate(Activity activity,
                                     Bundle icicle) {
        delegate(activity).performCreate(icicle);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnDestroy(android.app.Activity)
     */
    @Override
    public void callActivityOnDestroy(Activity activity) {
        delegate(activity).performDestroy();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnRestoreInstanceState(android
     * .app.Activity, android.os.Bundle)
     */
    @Override
    public void callActivityOnRestoreInstanceState(Activity activity,
                                                   Bundle savedInstanceState) {
        delegate(activity).performRestoreInstanceState(savedInstanceState);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnPostCreate(android.app.Activity
     * , android.os.Bundle)
     */
    @Override
    public void callActivityOnPostCreate(Activity activity,
                                         Bundle icicle) {
        delegate(activity).onPostCreate(icicle);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnNewIntent(android.app.Activity,
     * android.content.Intent)
     */
    @Override
    public void callActivityOnNewIntent(Activity activity,
                                        Intent intent) {
        delegate(activity).onNewIntent(intent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnStart(android.app.Activity)
     */
    @Override
    public void callActivityOnStart(Activity activity) {
        delegate(activity).onStart();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnRestart(android.app.Activity)
     */
    @Override
    public void callActivityOnRestart(Activity activity) {
        delegate(activity).onRestart();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnResume(android.app.Activity)
     */
    @Override
    public void callActivityOnResume(Activity activity) {
        IActivity delegate = delegate(activity);
        delegate.setMResumed(true);
        delegate.onResume();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#callActivityOnStop(android.app.Activity)
     */
    @Override
    public void callActivityOnStop(Activity activity) {
        delegate(activity).onStop();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnSaveInstanceState(android.app
     * .Activity, android.os.Bundle)
     */
    @Override
    public void callActivityOnSaveInstanceState(Activity activity,
                                                Bundle outState) {
        delegate(activity).performSaveInstanceState(outState);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnPause(android.app.Activity)
     */
    @Override
    public void callActivityOnPause(Activity activity) {
        delegate(activity).performPause();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnUserLeaving(android.app.Activity
     * )
     */
    @Override
    public void callActivityOnUserLeaving(Activity activity) {
        delegate(activity).performUserLeaving();
    }

    @Override
    public Activity startActivitySync(Intent intent) {
        validateNotAppThread();

        synchronized (mSync) {
            intent = new Intent(intent);

            ActivityInfo ai = intent.resolveActivityInfo(getTargetContext().getPackageManager(), 0);
            if (ai == null) {
                throw new RuntimeException("Unable to resolve activity for: " + intent);
            }
            //            String myProc = mThread.getProcessName();
            //            if (!ai.processName.equals(myProc)) {
            //                // todo: if this intent is ambiguous, look here to see if
            //                // there is a single match that is in our package.
            //                throw new RuntimeException("Intent in process " + myProc + " resolved to different process " + ai.processName + ": " + intent);
            //            }

            intent.setComponent(new ComponentName(ai.applicationInfo.packageName,
                                                  ai.name));
            final ActivityWaiter aw = new ActivityWaiter(intent);

            if (mWaitingActivities == null) {
                mWaitingActivities = new ArrayList();
            }
            mWaitingActivities.add(aw);

            getTargetContext().startActivity(intent);

            do {
                try {
                    mSync.wait();
                } catch (InterruptedException e) {
                }
            } while (mWaitingActivities.contains(aw));

            return aw.activity;
        }
    }

    @Override
    public void waitForIdleSync() {
        //noop.
    }

    @Override
    public void runOnMainSync(Runnable runner) {
        validateNotAppThread();
        runner.run();
    }
}
