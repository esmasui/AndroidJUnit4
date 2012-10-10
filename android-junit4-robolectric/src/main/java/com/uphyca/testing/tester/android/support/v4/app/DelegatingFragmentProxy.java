package com.uphyca.testing.tester.android.support.v4.app;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.uphyca.testing.DelegateFactory;
import com.uphyca.testing.proxy.android.support.v4.app.FragmentProxy;

public class DelegatingFragmentProxy implements FragmentProxy {

    public static final FragmentProxy create(Fragment fragment) {
        return new DelegatingFragmentProxy(fragment);
    }

    private final Fragment mFragment;
    private final FragmentProxy mProxy;

    private DelegatingFragmentProxy(Fragment fragment) {
        mFragment = fragment;
        mProxy = DelegateFactory.create(FragmentProxy.class, mFragment);
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#getId()
     */
    public final int getId() {
        return mFragment.getId();
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#getTag()
     */
    public final String getTag() {
        return mFragment.getTag();
    }

    /**
     * @param args
     * @see android.support.v4.app.Fragment#setArguments(android.os.Bundle)
     */
    public void setArguments(Bundle args) {
        mFragment.setArguments(args);
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#getArguments()
     */
    public final Bundle getArguments() {
        return mFragment.getArguments();
    }

    /**
     * @param state
     * @see android.support.v4.app.Fragment#setInitialSavedState(android.support.v4.app.Fragment.SavedState)
     */
    public void setInitialSavedState(SavedState state) {
        mFragment.setInitialSavedState(state);
    }

    /**
     * @param fragment
     * @param requestCode
     * @see android.support.v4.app.Fragment#setTargetFragment(android.support.v4.app.Fragment, int)
     */
    public void setTargetFragment(Fragment fragment,
                                  int requestCode) {
        mFragment.setTargetFragment(fragment, requestCode);
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#getTargetFragment()
     */
    public final Fragment getTargetFragment() {
        return mFragment.getTargetFragment();
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#getTargetRequestCode()
     */
    public final int getTargetRequestCode() {
        return mFragment.getTargetRequestCode();
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#getActivity()
     */
    public final FragmentActivity getActivity() {
        return mFragment.getActivity();
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#getResources()
     */
    public final Resources getResources() {
        return mFragment.getResources();
    }

    /**
     * @param resId
     * @return
     * @see android.support.v4.app.Fragment#getText(int)
     */
    public final CharSequence getText(int resId) {
        return mFragment.getText(resId);
    }

    /**
     * @param resId
     * @return
     * @see android.support.v4.app.Fragment#getString(int)
     */
    public final String getString(int resId) {
        return mFragment.getString(resId);
    }

    /**
     * @param resId
     * @param formatArgs
     * @return
     * @see android.support.v4.app.Fragment#getString(int, java.lang.Object[])
     */
    public final String getString(int resId,
                                  Object... formatArgs) {
        return mFragment.getString(resId, formatArgs);
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#getFragmentManager()
     */
    public final FragmentManager getFragmentManager() {
        return mFragment.getFragmentManager();
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#isAdded()
     */
    public final boolean isAdded() {
        return mFragment.isAdded();
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#isDetached()
     */
    public final boolean isDetached() {
        return mFragment.isDetached();
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#isRemoving()
     */
    public final boolean isRemoving() {
        return mFragment.isRemoving();
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#isInLayout()
     */
    public final boolean isInLayout() {
        return mFragment.isInLayout();
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#isResumed()
     */
    public final boolean isResumed() {
        return mFragment.isResumed();
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#isVisible()
     */
    public final boolean isVisible() {
        return mFragment.isVisible();
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#isHidden()
     */
    public final boolean isHidden() {
        return mFragment.isHidden();
    }

    /**
     * @param hidden
     * @see android.support.v4.app.Fragment#onHiddenChanged(boolean)
     */
    public void onHiddenChanged(boolean hidden) {
        mFragment.onHiddenChanged(hidden);
    }

    /**
     * @param retain
     * @see android.support.v4.app.Fragment#setRetainInstance(boolean)
     */
    public void setRetainInstance(boolean retain) {
        mFragment.setRetainInstance(retain);
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#getRetainInstance()
     */
    public final boolean getRetainInstance() {
        return mFragment.getRetainInstance();
    }

    /**
     * @param hasMenu
     * @see android.support.v4.app.Fragment#setHasOptionsMenu(boolean)
     */
    public void setHasOptionsMenu(boolean hasMenu) {
        mFragment.setHasOptionsMenu(hasMenu);
    }

    /**
     * @param menuVisible
     * @see android.support.v4.app.Fragment#setMenuVisibility(boolean)
     */
    public void setMenuVisibility(boolean menuVisible) {
        mFragment.setMenuVisibility(menuVisible);
    }

    /**
     * @param isVisibleToUser
     * @see android.support.v4.app.Fragment#setUserVisibleHint(boolean)
     */
    public void setUserVisibleHint(boolean isVisibleToUser) {
        mFragment.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#getUserVisibleHint()
     */
    public boolean getUserVisibleHint() {
        return mFragment.getUserVisibleHint();
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#getLoaderManager()
     */
    public LoaderManager getLoaderManager() {
        return mFragment.getLoaderManager();
    }

    /**
     * @param intent
     * @see android.support.v4.app.Fragment#startActivity(android.content.Intent)
     */
    public void startActivity(Intent intent) {
        mFragment.startActivity(intent);
    }

    /**
     * @param intent
     * @param requestCode
     * @see android.support.v4.app.Fragment#startActivityForResult(android.content.Intent, int)
     */
    public void startActivityForResult(Intent intent,
                                       int requestCode) {
        mFragment.startActivityForResult(intent, requestCode);
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     * @see android.support.v4.app.Fragment#onActivityResult(int, int, android.content.Intent)
     */
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {
        mFragment.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * @param savedInstanceState
     * @return
     * @see android.support.v4.app.Fragment#getLayoutInflater(android.os.Bundle)
     */
    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        return mFragment.getLayoutInflater(savedInstanceState);
    }

    /**
     * @param activity
     * @param attrs
     * @param savedInstanceState
     * @see android.support.v4.app.Fragment#onInflate(android.app.Activity, android.util.AttributeSet, android.os.Bundle)
     */
    public void onInflate(Activity activity,
                          AttributeSet attrs,
                          Bundle savedInstanceState) {
        mFragment.onInflate(activity, attrs, savedInstanceState);
    }

    /**
     * @param activity
     * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
     */
    public void onAttach(Activity activity) {
        mFragment.onAttach(activity);
    }

    /**
     * @param transit
     * @param enter
     * @param nextAnim
     * @return
     * @see android.support.v4.app.Fragment#onCreateAnimation(int, boolean, int)
     */
    public Animation onCreateAnimation(int transit,
                                       boolean enter,
                                       int nextAnim) {
        return mFragment.onCreateAnimation(transit, enter, nextAnim);
    }

    /**
     * @param savedInstanceState
     * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
     */
    public void onCreate(Bundle savedInstanceState) {
        mFragment.onCreate(savedInstanceState);
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return mFragment.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * @param view
     * @param savedInstanceState
     * @see android.support.v4.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
     */
    public void onViewCreated(View view,
                              Bundle savedInstanceState) {
        mFragment.onViewCreated(view, savedInstanceState);
    }

    /**
     * @return
     * @see android.support.v4.app.Fragment#getView()
     */
    public View getView() {
        return mFragment.getView();
    }

    /**
     * @param savedInstanceState
     * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
     */
    public void onActivityCreated(Bundle savedInstanceState) {
        mFragment.onActivityCreated(savedInstanceState);
    }

    /**
     * 
     * @see android.support.v4.app.Fragment#onStart()
     */
    public void onStart() {
        mFragment.onStart();
    }

    /**
     * 
     * @see android.support.v4.app.Fragment#onResume()
     */
    public void onResume() {
        mFragment.onResume();
    }

    /**
     * @param outState
     * @see android.support.v4.app.Fragment#onSaveInstanceState(android.os.Bundle)
     */
    public void onSaveInstanceState(Bundle outState) {
        mFragment.onSaveInstanceState(outState);
    }

    /**
     * @param newConfig
     * @see android.support.v4.app.Fragment#onConfigurationChanged(android.content.res.Configuration)
     */
    public void onConfigurationChanged(Configuration newConfig) {
        mFragment.onConfigurationChanged(newConfig);
    }

    /**
     * 
     * @see android.support.v4.app.Fragment#onPause()
     */
    public void onPause() {
        mFragment.onPause();
    }

    /**
     * 
     * @see android.support.v4.app.Fragment#onStop()
     */
    public void onStop() {
        mFragment.onStop();
    }

    /**
     * 
     * @see android.support.v4.app.Fragment#onLowMemory()
     */
    public void onLowMemory() {
        mFragment.onLowMemory();
    }

    /**
     * 
     * @see android.support.v4.app.Fragment#onDestroyView()
     */
    public void onDestroyView() {
        mFragment.onDestroyView();
    }

    /**
     * 
     * @see android.support.v4.app.Fragment#onDestroy()
     */
    public void onDestroy() {
        mFragment.onDestroy();
    }

    /**
     * 
     * @see android.support.v4.app.Fragment#onDetach()
     */
    public void onDetach() {
        mFragment.onDetach();
    }

    /**
     * @param menu
     * @param inflater
     * @see android.support.v4.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
     */
    public void onCreateOptionsMenu(Menu menu,
                                    MenuInflater inflater) {
        mFragment.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * @param menu
     * @see android.support.v4.app.Fragment#onPrepareOptionsMenu(android.view.Menu)
     */
    public void onPrepareOptionsMenu(Menu menu) {
        mFragment.onPrepareOptionsMenu(menu);
    }

    /**
     * 
     * @see android.support.v4.app.Fragment#onDestroyOptionsMenu()
     */
    public void onDestroyOptionsMenu() {
        mFragment.onDestroyOptionsMenu();
    }

    /**
     * @param item
     * @return
     * @see android.support.v4.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        return mFragment.onOptionsItemSelected(item);
    }

    /**
     * @param menu
     * @see android.support.v4.app.Fragment#onOptionsMenuClosed(android.view.Menu)
     */
    public void onOptionsMenuClosed(Menu menu) {
        mFragment.onOptionsMenuClosed(menu);
    }

    /**
     * @param menu
     * @param v
     * @param menuInfo
     * @see android.support.v4.app.Fragment#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
     */
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenuInfo menuInfo) {
        mFragment.onCreateContextMenu(menu, v, menuInfo);
    }

    /**
     * @param view
     * @see android.support.v4.app.Fragment#registerForContextMenu(android.view.View)
     */
    public void registerForContextMenu(View view) {
        mFragment.registerForContextMenu(view);
    }

    /**
     * @param view
     * @see android.support.v4.app.Fragment#unregisterForContextMenu(android.view.View)
     */
    public void unregisterForContextMenu(View view) {
        mFragment.unregisterForContextMenu(view);
    }

    /**
     * @param item
     * @return
     * @see android.support.v4.app.Fragment#onContextItemSelected(android.view.MenuItem)
     */
    public boolean onContextItemSelected(MenuItem item) {
        return mFragment.onContextItemSelected(item);
    }

    /**
     * @param prefix
     * @param fd
     * @param writer
     * @param args
     * @see android.support.v4.app.Fragment#dump(java.lang.String, java.io.FileDescriptor, java.io.PrintWriter, java.lang.String[])
     */
    public void dump(String prefix,
                     FileDescriptor fd,
                     PrintWriter writer,
                     String[] args) {
        mFragment.dump(prefix, fd, writer, args);
    }

}
