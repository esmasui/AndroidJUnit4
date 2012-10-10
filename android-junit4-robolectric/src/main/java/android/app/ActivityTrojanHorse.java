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
package android.app;

import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.IBinder;

import com.uphyca.testing.DelegateFactory;
import com.uphyca.testing.DelegateFactory.DeclaredIn;

public class ActivityTrojanHorse {

    private static interface IActivity {
        void attach(android.content.Context context,
                    /* ActivityThread */@DeclaredIn("android.app.ActivityThread") Object aThread,
                    android.app.Instrumentation instr,
                    android.os.IBinder token,
                    android.app.Application application,
                    android.content.Intent intent,
                    android.content.pm.ActivityInfo info,
                    java.lang.CharSequence title,
                    android.app.Activity parent,
                    java.lang.String id,
                    // pre ICS java.lang.Object
                    // after ICS
                    // android/app/Activity.java#NonConfigurationInstances
                    @DeclaredIn("android.app.Activity$NonConfigurationInstances") java.lang.Object lastNonConfigurationInstances,
                    android.content.res.Configuration config);
    }

    public static Activity callAttach(Instrumentation instrumentation,
                                      Activity activity,
                                      Context context,
                                      IBinder token,
                                      Application application,
                                      Intent intent,
                                      ActivityInfo info,
                                      CharSequence title,
                                      Activity parent,
                                      String id,
                                      Object lastNonConfigurationInstance) throws InstantiationException,
                                                                          IllegalAccessException,
                                                                          IllegalArgumentException,
                                                                          InvocationTargetException {
        IActivity invoker = DelegateFactory.create(IActivity.class, activity);
        invoker.attach(context, null, instrumentation, token, application, intent, info, title, parent, id, lastNonConfigurationInstance, new Configuration());
        return activity;
    }

}
