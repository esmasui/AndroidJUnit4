package com.uphyca.testing.tester.android.app;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class StubInstrumentation extends Instrumentation {

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle arguments) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#start()
     */
    @Override
    public void start() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#onStart()
     */
    @Override
    public void onStart() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#onException(java.lang.Object,
     * java.lang.Throwable)
     */
    @Override
    public boolean onException(Object obj,
                               Throwable e) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#sendStatus(int, android.os.Bundle)
     */
    @Override
    public void sendStatus(int resultCode,
                           Bundle results) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#finish(int, android.os.Bundle)
     */
    @Override
    public void finish(int resultCode,
                       Bundle results) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#setAutomaticPerformanceSnapshots()
     */
    @Override
    public void setAutomaticPerformanceSnapshots() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#startPerformanceSnapshot()
     */
    @Override
    public void startPerformanceSnapshot() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#endPerformanceSnapshot()
     */
    @Override
    public void endPerformanceSnapshot() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#onDestroy()
     */
    @Override
    public void onDestroy() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#getContext()
     */
    @Override
    public Context getContext() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#getComponentName()
     */
    @Override
    public ComponentName getComponentName() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#getTargetContext()
     */
    @Override
    public Context getTargetContext() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#isProfiling()
     */
    @Override
    public boolean isProfiling() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#startProfiling()
     */
    @Override
    public void startProfiling() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#stopProfiling()
     */
    @Override
    public void stopProfiling() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#setInTouchMode(boolean)
     */
    @Override
    public void setInTouchMode(boolean inTouch) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#waitForIdle(java.lang.Runnable)
     */
    @Override
    public void waitForIdle(Runnable recipient) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#waitForIdleSync()
     */
    @Override
    public void waitForIdleSync() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#runOnMainSync(java.lang.Runnable)
     */
    @Override
    public void runOnMainSync(Runnable runner) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#startActivitySync(android.content.Intent)
     */
    @Override
    public Activity startActivitySync(Intent intent) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#addMonitor(android.app.Instrumentation.
     * ActivityMonitor)
     */
    @Override
    public void addMonitor(ActivityMonitor monitor) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#addMonitor(android.content.IntentFilter,
     * android.app.Instrumentation.ActivityResult, boolean)
     */
    @Override
    public ActivityMonitor addMonitor(IntentFilter filter,
                                      ActivityResult result,
                                      boolean block) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#addMonitor(java.lang.String,
     * android.app.Instrumentation.ActivityResult, boolean)
     */
    @Override
    public ActivityMonitor addMonitor(String cls,
                                      ActivityResult result,
                                      boolean block) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#checkMonitorHit(android.app.Instrumentation
     * .ActivityMonitor, int)
     */
    @Override
    public boolean checkMonitorHit(ActivityMonitor monitor,
                                   int minHits) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#waitForMonitor(android.app.Instrumentation
     * .ActivityMonitor)
     */
    @Override
    public Activity waitForMonitor(ActivityMonitor monitor) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#waitForMonitorWithTimeout(android.app.
     * Instrumentation.ActivityMonitor, long)
     */
    @Override
    public Activity waitForMonitorWithTimeout(ActivityMonitor monitor,
                                              long timeOut) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#removeMonitor(android.app.Instrumentation
     * .ActivityMonitor)
     */
    @Override
    public void removeMonitor(ActivityMonitor monitor) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#invokeMenuActionSync(android.app.Activity,
     * int, int)
     */
    @Override
    public boolean invokeMenuActionSync(Activity targetActivity,
                                        int id,
                                        int flag) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#invokeContextMenuAction(android.app.Activity,
     * int, int)
     */
    @Override
    public boolean invokeContextMenuAction(Activity targetActivity,
                                           int id,
                                           int flag) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#sendStringSync(java.lang.String)
     */
    @Override
    public void sendStringSync(String text) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#sendKeySync(android.view.KeyEvent)
     */
    @Override
    public void sendKeySync(KeyEvent event) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#sendKeyDownUpSync(int)
     */
    @Override
    public void sendKeyDownUpSync(int key) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#sendCharacterSync(int)
     */
    @Override
    public void sendCharacterSync(int keyCode) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#sendPointerSync(android.view.MotionEvent)
     */
    @Override
    public void sendPointerSync(MotionEvent event) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#sendTrackballEventSync(android.view.MotionEvent
     * )
     */
    @Override
    public void sendTrackballEventSync(MotionEvent event) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#newApplication(java.lang.ClassLoader,
     * java.lang.String, android.content.Context)
     */
    @Override
    public Application newApplication(ClassLoader cl,
                                      String className,
                                      Context context) throws InstantiationException,
                                                      IllegalAccessException,
                                                      ClassNotFoundException {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callApplicationOnCreate(android.app.Application
     * )
     */
    @Override
    public void callApplicationOnCreate(Application app) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#newActivity(java.lang.Class,
     * android.content.Context, android.os.IBinder, android.app.Application,
     * android.content.Intent, android.content.pm.ActivityInfo,
     * java.lang.CharSequence, android.app.Activity, java.lang.String,
     * java.lang.Object)
     */
    @Override
    public Activity newActivity(Class<?> clazz,
                                Context context,
                                IBinder token,
                                Application application,
                                Intent intent,
                                ActivityInfo info,
                                CharSequence title,
                                Activity parent,
                                String id,
                                Object lastNonConfigurationInstance) throws InstantiationException,
                                                                    IllegalAccessException {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#newActivity(java.lang.ClassLoader,
     * java.lang.String, android.content.Intent)
     */
    @Override
    public Activity newActivity(ClassLoader cl,
                                String className,
                                Intent intent) throws InstantiationException,
                                              IllegalAccessException,
                                              ClassNotFoundException {
        return null;
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
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnDestroy(android.app.Activity)
     */
    @Override
    public void callActivityOnDestroy(Activity activity) {
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
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnStart(android.app.Activity)
     */
    @Override
    public void callActivityOnStart(Activity activity) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnRestart(android.app.Activity)
     */
    @Override
    public void callActivityOnRestart(Activity activity) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnResume(android.app.Activity)
     */
    @Override
    public void callActivityOnResume(Activity activity) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#callActivityOnStop(android.app.Activity)
     */
    @Override
    public void callActivityOnStop(Activity activity) {
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
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Instrumentation#callActivityOnPause(android.app.Activity)
     */
    @Override
    public void callActivityOnPause(Activity activity) {
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
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#startAllocCounting()
     */
    @Override
    public void startAllocCounting() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#stopAllocCounting()
     */
    @Override
    public void stopAllocCounting() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#getAllocCounts()
     */
    @Override
    public Bundle getAllocCounts() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Instrumentation#getBinderCounts()
     */
    @Override
    public Bundle getBinderCounts() {
        return null;
    }
}
