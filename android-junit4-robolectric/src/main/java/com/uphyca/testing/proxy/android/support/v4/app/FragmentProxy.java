package com.uphyca.testing.proxy.android.support.v4.app;

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

public interface FragmentProxy {

    /**
     * @return
     * @see android.support.v4.app.Fragment#hashCode()
     */
    int hashCode();

    /**
     * @return
     * @see android.support.v4.app.Fragment#getId()
     */
    int getId();

    /**
     * @return
     * @see android.support.v4.app.Fragment#getTag()
     */
    String getTag();

    /**
     * @return
     * @see android.support.v4.app.Fragment#getArguments()
     */
    Bundle getArguments();

    /**
     * @param state
     * @see android.support.v4.app.Fragment#setInitialSavedState(android.support.v4.app.Fragment.SavedState)
     */
    void setInitialSavedState(SavedState state);

    /**
     * @return
     * @see android.support.v4.app.Fragment#getTargetFragment()
     */
    Fragment getTargetFragment();

    /**
     * @return
     * @see android.support.v4.app.Fragment#getTargetRequestCode()
     */
    int getTargetRequestCode();

    /**
     * @return
     * @see android.support.v4.app.Fragment#getActivity()
     */
    FragmentActivity getActivity();

    /**
     * @return
     * @see android.support.v4.app.Fragment#getResources()
     */
    Resources getResources();

    /**
     * @param resId
     * @return
     * @see android.support.v4.app.Fragment#getText(int)
     */
    CharSequence getText(int resId);

    /**
     * @param resId
     * @return
     * @see android.support.v4.app.Fragment#getString(int)
     */
    String getString(int resId);

    /**
     * @param resId
     * @param formatArgs
     * @return
     * @see android.support.v4.app.Fragment#getString(int, java.lang.Object[])
     */
    String getString(int resId,
                     Object... formatArgs);

    /**
     * @return
     * @see android.support.v4.app.Fragment#getFragmentManager()
     */
    FragmentManager getFragmentManager();

    /**
     * @return
     * @see android.support.v4.app.Fragment#isAdded()
     */
    boolean isAdded();

    /**
     * @return
     * @see android.support.v4.app.Fragment#isDetached()
     */
    boolean isDetached();

    /**
     * @return
     * @see android.support.v4.app.Fragment#isRemoving()
     */
    boolean isRemoving();

    /**
     * @return
     * @see android.support.v4.app.Fragment#isInLayout()
     */
    boolean isInLayout();

    /**
     * @return
     * @see android.support.v4.app.Fragment#isResumed()
     */
    boolean isResumed();

    /**
     * @return
     * @see android.support.v4.app.Fragment#isVisible()
     */
    boolean isVisible();

    /**
     * @return
     * @see android.support.v4.app.Fragment#isHidden()
     */
    boolean isHidden();

    /**
     * @return
     * @see android.support.v4.app.Fragment#getRetainInstance()
     */
    boolean getRetainInstance();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#setArguments(android.os.Bundle)
     */
    void setArguments(Bundle args);

//    /* (non-Javadoc)
//     * @see android.support.v4.app.Fragment#setInitialSavedState(android.support.v4.app.Fragment.SavedState)
//     */
//    void setInitialSavedState(SavedState state);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#setTargetFragment(android.support.v4.app.Fragment, int)
     */
    void setTargetFragment(Fragment fragment,
                           int requestCode);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onHiddenChanged(boolean)
     */
    public void onHiddenChanged(boolean hidden);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#setRetainInstance(boolean)
     */
    void setRetainInstance(boolean retain);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#setHasOptionsMenu(boolean)
     */
    void setHasOptionsMenu(boolean hasMenu);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#setMenuVisibility(boolean)
     */
    void setMenuVisibility(boolean menuVisible);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#setUserVisibleHint(boolean)
     */
    void setUserVisibleHint(boolean isVisibleToUser);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#getUserVisibleHint()
     */
    boolean getUserVisibleHint();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#getLoaderManager()
     */
    LoaderManager getLoaderManager();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#startActivity(android.content.Intent)
     */
    void startActivity(Intent intent);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#startActivityForResult(android.content.Intent, int)
     */
    void startActivityForResult(Intent intent,
                                int requestCode);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onActivityResult(int, int, android.content.Intent)
     */
    void onActivityResult(int requestCode,
                          int resultCode,
                          Intent data);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#getLayoutInflater(android.os.Bundle)
     */
    LayoutInflater getLayoutInflater(Bundle savedInstanceState);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onInflate(android.app.Activity, android.util.AttributeSet, android.os.Bundle)
     */
    void onInflate(Activity activity,
                   AttributeSet attrs,
                   Bundle savedInstanceState);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
     */
    void onAttach(Activity activity);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateAnimation(int, boolean, int)
     */
    Animation onCreateAnimation(int transit,
                                boolean enter,
                                int nextAnim);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
     */
    void onCreate(Bundle savedInstanceState);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
    View onCreateView(LayoutInflater inflater,
                      ViewGroup container,
                      Bundle savedInstanceState);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
     */
    void onViewCreated(View view,
                       Bundle savedInstanceState);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#getView()
     */
    View getView();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
     */
    void onActivityCreated(Bundle savedInstanceState);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onStart()
     */
    void onStart();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onResume()
     */
    void onResume();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onSaveInstanceState(android.os.Bundle)
     */
    void onSaveInstanceState(Bundle outState);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onConfigurationChanged(android.content.res.Configuration)
     */
    void onConfigurationChanged(Configuration newConfig);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onPause()
     */
    void onPause();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onStop()
     */
    void onStop();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onLowMemory()
     */
    void onLowMemory();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onDestroyView()
     */
    void onDestroyView();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onDestroy()
     */
    void onDestroy();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onDetach()
     */
    void onDetach();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
     */
    void onCreateOptionsMenu(Menu menu,
                             MenuInflater inflater);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onPrepareOptionsMenu(android.view.Menu)
     */
    void onPrepareOptionsMenu(Menu menu);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onDestroyOptionsMenu()
     */
    void onDestroyOptionsMenu();

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
     */
    boolean onOptionsItemSelected(MenuItem item);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onOptionsMenuClosed(android.view.Menu)
     */
    void onOptionsMenuClosed(Menu menu);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
     */
    void onCreateContextMenu(ContextMenu menu,
                             View v,
                             ContextMenuInfo menuInfo);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#registerForContextMenu(android.view.View)
     */
    void registerForContextMenu(View view);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#unregisterForContextMenu(android.view.View)
     */
    void unregisterForContextMenu(View view);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onContextItemSelected(android.view.MenuItem)
     */
    boolean onContextItemSelected(MenuItem item);

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#dump(java.lang.String, java.io.FileDescriptor, java.io.PrintWriter, java.lang.String[])
     */
    void dump(String prefix,
              FileDescriptor fd,
              PrintWriter writer,
              String[] args);
}
