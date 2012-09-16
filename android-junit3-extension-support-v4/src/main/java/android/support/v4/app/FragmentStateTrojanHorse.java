/*
 * Copyright (C) 2012 uPhyca Inc.
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
package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcel;

public class FragmentStateTrojanHorse {

    private static final int DEACTIVATED = -1;
    private static final int FALSE = 0;
    private static final int OTHER = 1;

    private String mClassName;
    private int mIndex = DEACTIVATED;
    private boolean mFromLayout;
    private int mFragmentId;
    private int mContainerId;
    private String mTag;
    private boolean mRetainInstance;
    private boolean mDetached;
    private Bundle mArguments;
    private Bundle mSavedFragmentState;

    private static final int boolToInt(boolean b) {
        return b ? OTHER : FALSE;
    }

    /**
     * @param activity
     * @return
     * @see android.support.v4.app.FragmentState#instantiate(android.support.v4.app.FragmentActivity)
     */
    @SuppressWarnings("unchecked")
    public <T extends Fragment> T instantiate(FragmentActivity activity) {
        Parcel in = buildParcel();
        FragmentState fragmentState = FragmentState.CREATOR.createFromParcel(in);
        //FragmentState fragmentState = new FragmentState(in);
        in.recycle();
        return (T) fragmentState.instantiate(activity);
    }

    private Parcel buildParcel() {
        Parcel out = Parcel.obtain();
        out.writeString(mClassName);
        out.writeInt(mIndex);
        out.writeInt(boolToInt(mFromLayout));
        out.writeInt(mFragmentId);
        out.writeInt(mContainerId);
        out.writeString(mTag);
        out.writeInt(boolToInt(mRetainInstance));
        out.writeInt(boolToInt(mDetached));
        out.writeBundle(mArguments);
        out.writeBundle(mSavedFragmentState);
        out.setDataPosition(0);
        return out;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        mClassName = className;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        mIndex = index;
    }

    /**
     * @param fromLayout the fromLayout to set
     */
    public void setFromLayout(boolean fromLayout) {
        mFromLayout = fromLayout;
    }

    /**
     * @param fragmentId the fragmentId to set
     */
    public void setFragmentId(int fragmentId) {
        mFragmentId = fragmentId;
    }

    /**
     * @param containerId the containerId to set
     */
    public void setContainerId(int containerId) {
        mContainerId = containerId;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        mTag = tag;
    }

    /**
     * @param retainInstance the retainInstance to set
     */
    public void setRetainInstance(boolean retainInstance) {
        mRetainInstance = retainInstance;
    }

    /**
     * @param detached the detached to set
     */
    public void setDetached(boolean detached) {
        mDetached = detached;
    }

    /**
     * @param arguments the arguments to set
     */
    public void setArguments(Bundle arguments) {
        mArguments = arguments;
    }

    /**
     * @param savedFragmentState the savedFragmentState to set
     */
    public void setSavedFragmentState(Bundle savedFragmentState) {
        mSavedFragmentState = savedFragmentState;
    }
}
