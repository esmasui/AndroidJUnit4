package com.uphyca.testing;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

import com.uphyca.testing.adapter.ActivityAdapter;
import com.uphyca.testing.delegate.IActivity;

public class RobolectricInstrumentation extends Instrumentation {

    private static IActivity delegate(Activity activity) {
        return ActivityAdapter.create(activity);
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
}
