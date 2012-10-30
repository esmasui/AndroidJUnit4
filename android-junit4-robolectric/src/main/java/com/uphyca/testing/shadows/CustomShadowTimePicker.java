package com.uphyca.testing.shadows;

import android.widget.TimePicker;

import com.xtremelabs.robolectric.internal.Implementation;
import com.xtremelabs.robolectric.internal.Implements;
import com.xtremelabs.robolectric.internal.RealObject;
import com.xtremelabs.robolectric.shadows.ShadowView;

@Implements(TimePicker.class)
public class CustomShadowTimePicker extends ShadowView {

    @RealObject
    protected TimePicker realTimePicker;
    private Integer mCurrentHour;
    private Integer mCurrentMinute;

    @Implementation
    public Integer getCurrentHour() {
        return mCurrentHour;
    }

    @Implementation
    public Integer getCurrentMinute() {
        return mCurrentMinute;
    }

    @Implementation
    public void setCurrentHour(Integer currentHour) {
        mCurrentHour = currentHour;
    }

    @Implementation
    public void setCurrentMinute(Integer currentMinute) {
        mCurrentMinute = currentMinute;
    }

}
