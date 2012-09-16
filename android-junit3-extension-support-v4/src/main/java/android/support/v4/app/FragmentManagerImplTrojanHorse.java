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

import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class FragmentManagerImplTrojanHorse {

    private FragmentManagerImpl _delegate;

    private static void verify(FragmentManager fm) {
        if (fm instanceof FragmentManagerImpl) {
            return;
        }
        throw new IllegalArgumentException();
    }

    public static FragmentManagerImplTrojanHorse create(FragmentManager delegate) {
        verify(delegate);
        return new FragmentManagerImplTrojanHorse((FragmentManagerImpl) delegate);
    }

    private FragmentManagerImplTrojanHorse(FragmentManagerImpl delegate) {
        _delegate = delegate;
    }

    /**
     * @param fragment
     * @param moveToStateNow
     * @see android.support.v4.app.FragmentManagerImpl#addFragment(android.support.v4.app.Fragment, boolean)
     */
    public void addFragment(Fragment fragment,
                            boolean moveToStateNow) {
        _delegate.addFragment(fragment,
                              moveToStateNow);
    }

    /**
     * @param listener
     * @see android.support.v4.app.FragmentManagerImpl#addOnBackStackChangedListener(android.support.v4.app.FragmentManager.OnBackStackChangedListener)
     */
    public void addOnBackStackChangedListener(OnBackStackChangedListener listener) {
        _delegate.addOnBackStackChangedListener(listener);
    }

    /**
     * @param arg0
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#allocBackStackIndex(android.support.v4.app.BackStackRecord)
     */
    public int allocBackStackIndex(BackStackRecord arg0) {
        return _delegate.allocBackStackIndex(arg0);
    }

    /**
     * @param activity
     * @see android.support.v4.app.FragmentManagerImpl#attachActivity(android.support.v4.app.FragmentActivity)
     */
    public void attachActivity(FragmentActivity activity) {
        _delegate.attachActivity(activity);
    }

    /**
     * @param fragment
     * @param transition
     * @param transitionStyle
     * @see android.support.v4.app.FragmentManagerImpl#attachFragment(android.support.v4.app.Fragment, int, int)
     */
    public void attachFragment(Fragment fragment,
                               int transition,
                               int transitionStyle) {
        _delegate.attachFragment(fragment,
                                 transition,
                                 transitionStyle);
    }

    /**
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#beginTransaction()
     */
    public FragmentTransaction beginTransaction() {
        return _delegate.beginTransaction();
    }

    /**
     * @param fragment
     * @param transition
     * @param transitionStyle
     * @see android.support.v4.app.FragmentManagerImpl#detachFragment(android.support.v4.app.Fragment, int, int)
     */
    public void detachFragment(Fragment fragment,
                               int transition,
                               int transitionStyle) {
        _delegate.detachFragment(fragment,
                                 transition,
                                 transitionStyle);
    }

    /**
     * 
     * @see android.support.v4.app.FragmentManagerImpl#dispatchActivityCreated()
     */
    public void dispatchActivityCreated() {
        _delegate.dispatchActivityCreated();
    }

    /**
     * @param arg0
     * @see android.support.v4.app.FragmentManagerImpl#dispatchConfigurationChanged(android.content.res.Configuration)
     */
    public void dispatchConfigurationChanged(Configuration arg0) {
        _delegate.dispatchConfigurationChanged(arg0);
    }

    /**
     * @param arg0
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#dispatchContextItemSelected(android.view.MenuItem)
     */
    public boolean dispatchContextItemSelected(MenuItem arg0) {
        return _delegate.dispatchContextItemSelected(arg0);
    }

    /**
     * 
     * @see android.support.v4.app.FragmentManagerImpl#dispatchCreate()
     */
    public void dispatchCreate() {
        _delegate.dispatchCreate();
    }

    /**
     * @param arg0
     * @param arg1
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#dispatchCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
     */
    public boolean dispatchCreateOptionsMenu(Menu arg0,
                                             MenuInflater arg1) {
        return _delegate.dispatchCreateOptionsMenu(arg0,
                                                   arg1);
    }

    /**
     * 
     * @see android.support.v4.app.FragmentManagerImpl#dispatchDestroy()
     */
    public void dispatchDestroy() {
        _delegate.dispatchDestroy();
    }

    /**
     * 
     * @see android.support.v4.app.FragmentManagerImpl#dispatchLowMemory()
     */
    public void dispatchLowMemory() {
        _delegate.dispatchLowMemory();
    }

    /**
     * @param arg0
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#dispatchOptionsItemSelected(android.view.MenuItem)
     */
    public boolean dispatchOptionsItemSelected(MenuItem arg0) {
        return _delegate.dispatchOptionsItemSelected(arg0);
    }

    /**
     * @param arg0
     * @see android.support.v4.app.FragmentManagerImpl#dispatchOptionsMenuClosed(android.view.Menu)
     */
    public void dispatchOptionsMenuClosed(Menu arg0) {
        _delegate.dispatchOptionsMenuClosed(arg0);
    }

    /**
     * 
     * @see android.support.v4.app.FragmentManagerImpl#dispatchPause()
     */
    public void dispatchPause() {
        _delegate.dispatchPause();
    }

    /**
     * @param arg0
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#dispatchPrepareOptionsMenu(android.view.Menu)
     */
    public boolean dispatchPrepareOptionsMenu(Menu arg0) {
        return _delegate.dispatchPrepareOptionsMenu(arg0);
    }

    /**
     * 
     * @see android.support.v4.app.FragmentManagerImpl#dispatchReallyStop()
     */
    public void dispatchReallyStop() {
        _delegate.dispatchReallyStop();
    }

    /**
     * 
     * @see android.support.v4.app.FragmentManagerImpl#dispatchResume()
     */
    public void dispatchResume() {
        _delegate.dispatchResume();
    }

    /**
     * 
     * @see android.support.v4.app.FragmentManagerImpl#dispatchStart()
     */
    public void dispatchStart() {
        _delegate.dispatchStart();
    }

    /**
     * 
     * @see android.support.v4.app.FragmentManagerImpl#dispatchStop()
     */
    public void dispatchStop() {
        _delegate.dispatchStop();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @see android.support.v4.app.FragmentManagerImpl#dump(java.lang.String, java.io.FileDescriptor, java.io.PrintWriter, java.lang.String[])
     */
    public void dump(String arg0,
                     FileDescriptor arg1,
                     PrintWriter arg2,
                     String[] arg3) {
        _delegate.dump(arg0,
                       arg1,
                       arg2,
                       arg3);
    }

    /**
     * @param action
     * @param allowStateLoss
     * @see android.support.v4.app.FragmentManagerImpl#enqueueAction(java.lang.Runnable, boolean)
     */
    public void enqueueAction(Runnable action,
                              boolean allowStateLoss) {
        _delegate.enqueueAction(action,
                                allowStateLoss);
    }

    /**
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#execPendingActions()
     */
    public boolean execPendingActions() {
        return _delegate.execPendingActions();
    }

    /**
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#executePendingTransactions()
     */
    public boolean executePendingTransactions() {
        return _delegate.executePendingTransactions();
    }

    /**
     * @param arg0
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#findFragmentById(int)
     */
    public Fragment findFragmentById(int arg0) {
        return _delegate.findFragmentById(arg0);
    }

    /**
     * @param arg0
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#findFragmentByTag(java.lang.String)
     */
    public Fragment findFragmentByTag(String arg0) {
        return _delegate.findFragmentByTag(arg0);
    }

    /**
     * @param arg0
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#findFragmentByWho(java.lang.String)
     */
    public Fragment findFragmentByWho(String arg0) {
        return _delegate.findFragmentByWho(arg0);
    }

    /**
     * @param index
     * @see android.support.v4.app.FragmentManagerImpl#freeBackStackIndex(int)
     */
    public void freeBackStackIndex(int index) {
        _delegate.freeBackStackIndex(index);
    }

    /**
     * @param index
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#getBackStackEntryAt(int)
     */
    public BackStackEntry getBackStackEntryAt(int index) {
        return _delegate.getBackStackEntryAt(index);
    }

    /**
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#getBackStackEntryCount()
     */
    public int getBackStackEntryCount() {
        return _delegate.getBackStackEntryCount();
    }

    /**
     * @param bundle
     * @param key
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#getFragment(android.os.Bundle, java.lang.String)
     */
    public Fragment getFragment(Bundle bundle,
                                String key) {
        return _delegate.getFragment(bundle,
                                     key);
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @see android.support.v4.app.FragmentManagerImpl#hideFragment(android.support.v4.app.Fragment, int, int)
     */
    public void hideFragment(Fragment arg0,
                             int arg1,
                             int arg2) {
        _delegate.hideFragment(arg0,
                               arg1,
                               arg2);
    }

    /**
     * 
     * @see android.support.v4.app.FragmentManagerImpl#noteStateNotSaved()
     */
    public void noteStateNotSaved() {
        _delegate.noteStateNotSaved();
    }

    /**
     * @return
     * @deprecated
     * @see android.support.v4.app.FragmentManager#openTransaction()
     */
    public FragmentTransaction openTransaction() {
        return _delegate.openTransaction();
    }

    /**
     * @param f
     * @see android.support.v4.app.FragmentManagerImpl#performPendingDeferredStart(android.support.v4.app.Fragment)
     */
    public void performPendingDeferredStart(Fragment f) {
        _delegate.performPendingDeferredStart(f);
    }

    /**
     * 
     * @see android.support.v4.app.FragmentManagerImpl#popBackStack()
     */
    public void popBackStack() {
        _delegate.popBackStack();
    }

    /**
     * @param id
     * @param flags
     * @see android.support.v4.app.FragmentManagerImpl#popBackStack(int, int)
     */
    public void popBackStack(int id,
                             int flags) {
        _delegate.popBackStack(id,
                               flags);
    }

    /**
     * @param name
     * @param flags
     * @see android.support.v4.app.FragmentManagerImpl#popBackStack(java.lang.String, int)
     */
    public void popBackStack(String name,
                             int flags) {
        _delegate.popBackStack(name,
                               flags);
    }

    /**
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#popBackStackImmediate()
     */
    public boolean popBackStackImmediate() {
        return _delegate.popBackStackImmediate();
    }

    /**
     * @param id
     * @param flags
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#popBackStackImmediate(int, int)
     */
    public boolean popBackStackImmediate(int id,
                                         int flags) {
        return _delegate.popBackStackImmediate(id,
                                               flags);
    }

    /**
     * @param name
     * @param flags
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#popBackStackImmediate(java.lang.String, int)
     */
    public boolean popBackStackImmediate(String name,
                                         int flags) {
        return _delegate.popBackStackImmediate(name,
                                               flags);
    }

    /**
     * @param bundle
     * @param key
     * @param fragment
     * @see android.support.v4.app.FragmentManagerImpl#putFragment(android.os.Bundle, java.lang.String, android.support.v4.app.Fragment)
     */
    public void putFragment(Bundle bundle,
                            String key,
                            Fragment fragment) {
        _delegate.putFragment(bundle,
                              key,
                              fragment);
    }

    /**
     * @param fragment
     * @param transition
     * @param transitionStyle
     * @see android.support.v4.app.FragmentManagerImpl#removeFragment(android.support.v4.app.Fragment, int, int)
     */
    public void removeFragment(Fragment fragment,
                               int transition,
                               int transitionStyle) {
        _delegate.removeFragment(fragment,
                                 transition,
                                 transitionStyle);
    }

    /**
     * @param listener
     * @see android.support.v4.app.FragmentManagerImpl#removeOnBackStackChangedListener(android.support.v4.app.FragmentManager.OnBackStackChangedListener)
     */
    public void removeOnBackStackChangedListener(OnBackStackChangedListener listener) {
        _delegate.removeOnBackStackChangedListener(listener);
    }

    /**
     * @param arg0
     * @return
     * @see android.support.v4.app.FragmentManagerImpl#saveFragmentInstanceState(android.support.v4.app.Fragment)
     */
    public SavedState saveFragmentInstanceState(Fragment arg0) {
        return _delegate.saveFragmentInstanceState(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     * @see android.support.v4.app.FragmentManagerImpl#setBackStackIndex(int, android.support.v4.app.BackStackRecord)
     */
    public void setBackStackIndex(int arg0,
                                  BackStackRecord arg1) {
        _delegate.setBackStackIndex(arg0,
                                    arg1);
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @see android.support.v4.app.FragmentManagerImpl#showFragment(android.support.v4.app.Fragment, int, int)
     */
    public void showFragment(Fragment arg0,
                             int arg1,
                             int arg2) {
        _delegate.showFragment(arg0,
                               arg1,
                               arg2);
    }

}
