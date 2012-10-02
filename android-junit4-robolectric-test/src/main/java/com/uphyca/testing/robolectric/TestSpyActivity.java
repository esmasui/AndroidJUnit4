package com.uphyca.testing.robolectric;

import android.app.Activity;
import android.os.Bundle;

public class TestSpyActivity extends Activity {

    private boolean onCreateCalled;
    private boolean onStartCalled;
    private boolean onResumeCalled;
    private boolean onPauseCalled;
    private boolean onStopCalled;
    private boolean onDestroyCalled;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateCalled = true;
    }

    @Override
    public void onStart() {
        super.onStart();
        onStartCalled = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        onResumeCalled = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        onPauseCalled = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        onStopCalled = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onDestroyCalled = true;
    }

    /**
     * @return the onCreateCalled
     */
    public boolean isOnCreateCalled() {
        return onCreateCalled;
    }

    /**
     * @return the onStartCalled
     */
    public boolean isOnStartCalled() {
        return onStartCalled;
    }

    /**
     * @return the onResumeCalled
     */
    public boolean isOnResumeCalled() {
        return onResumeCalled;
    }

    /**
     * @return the onPauseCalled
     */
    public boolean isOnPauseCalled() {
        return onPauseCalled;
    }

    /**
     * @return the onStopCalled
     */
    public boolean isOnStopCalled() {
        return onStopCalled;
    }

    /**
     * @return the onDestroyCalled
     */
    public boolean isOnDestroyCalled() {
        return onDestroyCalled;
    }

}
