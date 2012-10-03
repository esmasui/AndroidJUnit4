package com.uphyca.testing.proxy.android.app;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import com.uphyca.testing.DelegateFactory;
import com.uphyca.testing.DelegateFactory.DeclaredIn;

@SuppressLint("NewApi")
public interface ActivityProxy {

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getIntent()
     */
    Intent getIntent();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#setIntent(android.content.Intent)
     */
    void setIntent(Intent newIntent);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getWindowManager()
     */
    WindowManager getWindowManager();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getWindow()
     */
    Window getWindow();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getLoaderManager()
     */
    LoaderManager getLoaderManager();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getCurrentFocus()
     */
    View getCurrentFocus();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    void onCreate(Bundle savedInstanceState);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onRestoreInstanceState(android.os.Bundle)
     */
    void onRestoreInstanceState(Bundle savedInstanceState);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPostCreate(android.os.Bundle)
     */
    void onPostCreate(Bundle savedInstanceState);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onStart()
     */
    void onStart();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onRestart()
     */
    void onRestart();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onResume()
     */
    void onResume();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPostResume()
     */
    void onPostResume();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onNewIntent(android.content.Intent)
     */
    void onNewIntent(Intent intent);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
     */
    void onSaveInstanceState(Bundle outState);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPause()
     */
    void onPause();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onUserLeaveHint()
     */
    void onUserLeaveHint();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateThumbnail(android.graphics.Bitmap,
     * android.graphics.Canvas)
     */
    boolean onCreateThumbnail(Bitmap outBitmap,
                              Canvas canvas);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateDescription()
     */
    CharSequence onCreateDescription();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onStop()
     */
    void onStop();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onDestroy()
     */
    void onDestroy();

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#onConfigurationChanged(android.content.res.Configuration
     * )
     */
    void onConfigurationChanged(Configuration newConfig);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getChangingConfigurations()
     */
    int getChangingConfigurations();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getLastNonConfigurationInstance()
     */
    @Deprecated
    Object getLastNonConfigurationInstance();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onRetainNonConfigurationInstance()
     */
    Object onRetainNonConfigurationInstance();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onLowMemory()
     */
    void onLowMemory();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onTrimMemory(int)
     */
    void onTrimMemory(int level);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getFragmentManager()
     */
    FragmentManager getFragmentManager();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onAttachFragment(android.app.Fragment)
     */
    void onAttachFragment(Fragment fragment);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startManagingCursor(android.database.Cursor)
     */
    @Deprecated
    void startManagingCursor(Cursor c);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#stopManagingCursor(android.database.Cursor)
     */
    @Deprecated
    void stopManagingCursor(Cursor c);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#findViewById(int)
     */
    View findViewById(int id);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getActionBar()
     */
    ActionBar getActionBar();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#setContentView(int)
     */
    void setContentView(int layoutResID);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#setContentView(android.view.View)
     */
    void setContentView(View view);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#setContentView(android.view.View,
     * android.view.ViewGroup.LayoutParams)
     */
    void setContentView(View view,
                        LayoutParams params);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#addContentView(android.view.View,
     * android.view.ViewGroup.LayoutParams)
     */
    void addContentView(View view,
                        LayoutParams params);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#setFinishOnTouchOutside(boolean)
     */
    void setFinishOnTouchOutside(boolean finish);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
     */
    boolean onKeyDown(int keyCode,
                      KeyEvent event);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onKeyLongPress(int, android.view.KeyEvent)
     */
    boolean onKeyLongPress(int keyCode,
                           KeyEvent event);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onKeyUp(int, android.view.KeyEvent)
     */
    boolean onKeyUp(int keyCode,
                    KeyEvent event);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onKeyMultiple(int, int, android.view.KeyEvent)
     */
    boolean onKeyMultiple(int keyCode,
                          int repeatCount,
                          KeyEvent event);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onBackPressed()
     */
    void onBackPressed();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onKeyShortcut(int, android.view.KeyEvent)
     */
    boolean onKeyShortcut(int keyCode,
                          KeyEvent event);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
     */
    boolean onTouchEvent(MotionEvent event);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onTrackballEvent(android.view.MotionEvent)
     */
    boolean onTrackballEvent(MotionEvent event);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onGenericMotionEvent(android.view.MotionEvent)
     */
    boolean onGenericMotionEvent(MotionEvent event);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onUserInteraction()
     */
    void onUserInteraction();

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#onWindowAttributesChanged(android.view.WindowManager
     * .LayoutParams)
     */
    void onWindowAttributesChanged(android.view.WindowManager.LayoutParams params);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onContentChanged()
     */
    void onContentChanged();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onWindowFocusChanged(boolean)
     */
    void onWindowFocusChanged(boolean hasFocus);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onAttachedToWindow()
     */
    void onAttachedToWindow();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onDetachedFromWindow()
     */
    void onDetachedFromWindow();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#hasWindowFocus()
     */
    boolean hasWindowFocus();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#dispatchKeyEvent(android.view.KeyEvent)
     */
    boolean dispatchKeyEvent(KeyEvent event);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#dispatchKeyShortcutEvent(android.view.KeyEvent)
     */
    boolean dispatchKeyShortcutEvent(KeyEvent event);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#dispatchTouchEvent(android.view.MotionEvent)
     */
    boolean dispatchTouchEvent(MotionEvent ev);

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#dispatchTrackballEvent(android.view.MotionEvent)
     */
    boolean dispatchTrackballEvent(MotionEvent ev);

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#dispatchGenericMotionEvent(android.view.MotionEvent)
     */
    boolean dispatchGenericMotionEvent(MotionEvent ev);

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#dispatchPopulateAccessibilityEvent(android.view.
     * accessibility.AccessibilityEvent)
     */
    boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreatePanelView(int)
     */
    View onCreatePanelView(int featureId);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreatePanelMenu(int, android.view.Menu)
     */
    boolean onCreatePanelMenu(int featureId,
                              Menu menu);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPreparePanel(int, android.view.View,
     * android.view.Menu)
     */
    boolean onPreparePanel(int featureId,
                           View view,
                           Menu menu);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onMenuOpened(int, android.view.Menu)
     */
    boolean onMenuOpened(int featureId,
                         Menu menu);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
     */
    boolean onMenuItemSelected(int featureId,
                               MenuItem item);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPanelClosed(int, android.view.Menu)
     */
    void onPanelClosed(int featureId,
                       Menu menu);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#invalidateOptionsMenu()
     */
    void invalidateOptionsMenu();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    boolean onCreateOptionsMenu(Menu menu);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
     */
    boolean onPrepareOptionsMenu(Menu menu);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    boolean onOptionsItemSelected(MenuItem item);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onNavigateUp()
     */
    boolean onNavigateUp();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onNavigateUpFromChild(android.app.Activity)
     */
    boolean onNavigateUpFromChild(Activity child);

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#onCreateNavigateUpTaskStack(android.app.TaskStackBuilder
     * )
     */
    void onCreateNavigateUpTaskStack(TaskStackBuilder builder);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPrepareNavigateUpTaskStack(android.app.
     * TaskStackBuilder)
     */
    void onPrepareNavigateUpTaskStack(TaskStackBuilder builder);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onOptionsMenuClosed(android.view.Menu)
     */
    void onOptionsMenuClosed(Menu menu);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#openOptionsMenu()
     */
    void openOptionsMenu();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#closeOptionsMenu()
     */
    void closeOptionsMenu();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu,
     * android.view.View, android.view.ContextMenu.ContextMenuInfo)
     */
    void onCreateContextMenu(ContextMenu menu,
                             View v,
                             ContextMenuInfo menuInfo);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#registerForContextMenu(android.view.View)
     */
    void registerForContextMenu(View view);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#unregisterForContextMenu(android.view.View)
     */
    void unregisterForContextMenu(View view);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#openContextMenu(android.view.View)
     */
    void openContextMenu(View view);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#closeContextMenu()
     */
    void closeContextMenu();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
     */
    boolean onContextItemSelected(MenuItem item);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onContextMenuClosed(android.view.Menu)
     */
    void onContextMenuClosed(Menu menu);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateDialog(int)
     */
    @Deprecated
    Dialog onCreateDialog(int id);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateDialog(int, android.os.Bundle)
     */
    @Deprecated
    Dialog onCreateDialog(int id,
                          Bundle args);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPrepareDialog(int, android.app.Dialog)
     */
    @Deprecated
    void onPrepareDialog(int id,
                         Dialog dialog);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPrepareDialog(int, android.app.Dialog,
     * android.os.Bundle)
     */
    @Deprecated
    void onPrepareDialog(int id,
                         Dialog dialog,
                         Bundle args);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onSearchRequested()
     */
    boolean onSearchRequested();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startSearch(java.lang.String, boolean,
     * android.os.Bundle, boolean)
     */
    void startSearch(String initialQuery,
                     boolean selectInitialQuery,
                     Bundle appSearchData,
                     boolean globalSearch);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#triggerSearch(java.lang.String,
     * android.os.Bundle)
     */
    void triggerSearch(String query,
                       Bundle appSearchData);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#takeKeyEvents(boolean)
     */
    void takeKeyEvents(boolean get);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getLayoutInflater()
     */
    LayoutInflater getLayoutInflater();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getMenuInflater()
     */
    MenuInflater getMenuInflater();

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#onApplyThemeResource(android.content.res.Resources
     * .Theme, int, boolean)
     */
    void onApplyThemeResource(Theme theme,
                              int resid,
                              boolean first);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startActivityForResult(android.content.Intent,
     * int)
     */
    void startActivityForResult(Intent intent,
                                int requestCode);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startActivityForResult(android.content.Intent,
     * int, android.os.Bundle)
     */
    void startActivityForResult(Intent intent,
                                int requestCode,
                                Bundle options);

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#startIntentSenderForResult(android.content.IntentSender
     * , int, android.content.Intent, int, int, int)
     */
    void startIntentSenderForResult(IntentSender intent,
                                    int requestCode,
                                    Intent fillInIntent,
                                    int flagsMask,
                                    int flagsValues,
                                    int extraFlags) throws SendIntentException;

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#startIntentSenderForResult(android.content.IntentSender
     * , int, android.content.Intent, int, int, int, android.os.Bundle)
     */
    void startIntentSenderForResult(IntentSender intent,
                                    int requestCode,
                                    Intent fillInIntent,
                                    int flagsMask,
                                    int flagsValues,
                                    int extraFlags,
                                    Bundle options) throws SendIntentException;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startActivity(android.content.Intent)
     */
    void startActivity(Intent intent);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startActivity(android.content.Intent,
     * android.os.Bundle)
     */
    void startActivity(Intent intent,
                       Bundle options);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startActivities(android.content.Intent[])
     */
    void startActivities(Intent[] intents);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startActivities(android.content.Intent[],
     * android.os.Bundle)
     */
    void startActivities(Intent[] intents,
                         Bundle options);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startIntentSender(android.content.IntentSender,
     * android.content.Intent, int, int, int)
     */
    void startIntentSender(IntentSender intent,
                           Intent fillInIntent,
                           int flagsMask,
                           int flagsValues,
                           int extraFlags) throws SendIntentException;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startIntentSender(android.content.IntentSender,
     * android.content.Intent, int, int, int, android.os.Bundle)
     */
    void startIntentSender(IntentSender intent,
                           Intent fillInIntent,
                           int flagsMask,
                           int flagsValues,
                           int extraFlags,
                           Bundle options) throws SendIntentException;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startActivityIfNeeded(android.content.Intent,
     * int)
     */
    boolean startActivityIfNeeded(Intent intent,
                                  int requestCode);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startActivityIfNeeded(android.content.Intent,
     * int, android.os.Bundle)
     */
    boolean startActivityIfNeeded(Intent intent,
                                  int requestCode,
                                  Bundle options);

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#startNextMatchingActivity(android.content.Intent)
     */
    boolean startNextMatchingActivity(Intent intent);

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#startNextMatchingActivity(android.content.Intent,
     * android.os.Bundle)
     */
    boolean startNextMatchingActivity(Intent intent,
                                      Bundle options);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startActivityFromChild(android.app.Activity,
     * android.content.Intent, int)
     */
    void startActivityFromChild(Activity child,
                                Intent intent,
                                int requestCode);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startActivityFromChild(android.app.Activity,
     * android.content.Intent, int, android.os.Bundle)
     */
    void startActivityFromChild(Activity child,
                                Intent intent,
                                int requestCode,
                                Bundle options);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startActivityFromFragment(android.app.Fragment,
     * android.content.Intent, int)
     */
    void startActivityFromFragment(Fragment fragment,
                                   Intent intent,
                                   int requestCode);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#startActivityFromFragment(android.app.Fragment,
     * android.content.Intent, int, android.os.Bundle)
     */
    void startActivityFromFragment(Fragment fragment,
                                   Intent intent,
                                   int requestCode,
                                   Bundle options);

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#startIntentSenderFromChild(android.app.Activity,
     * android.content.IntentSender, int, android.content.Intent, int, int, int)
     */
    void startIntentSenderFromChild(Activity child,
                                    IntentSender intent,
                                    int requestCode,
                                    Intent fillInIntent,
                                    int flagsMask,
                                    int flagsValues,
                                    int extraFlags) throws SendIntentException;

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#startIntentSenderFromChild(android.app.Activity,
     * android.content.IntentSender, int, android.content.Intent, int, int, int,
     * android.os.Bundle)
     */
    void startIntentSenderFromChild(Activity child,
                                    IntentSender intent,
                                    int requestCode,
                                    Intent fillInIntent,
                                    int flagsMask,
                                    int flagsValues,
                                    int extraFlags,
                                    Bundle options) throws SendIntentException;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#overridePendingTransition(int, int)
     */
    void overridePendingTransition(int enterAnim,
                                   int exitAnim);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getCallingPackage()
     */
    String getCallingPackage();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getCallingActivity()
     */
    ComponentName getCallingActivity();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#setVisible(boolean)
     */
    void setVisible(boolean visible);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#isFinishing()
     */
    boolean isFinishing();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#isChangingConfigurations()
     */
    boolean isChangingConfigurations();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#recreate()
     */
    void recreate();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#finish()
     */
    void finish();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#finishAffinity()
     */
    void finishAffinity();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#finishFromChild(android.app.Activity)
     */
    void finishFromChild(Activity child);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#finishActivity(int)
     */
    void finishActivity(int requestCode);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#finishActivityFromChild(android.app.Activity,
     * int)
     */
    void finishActivityFromChild(Activity child,
                                 int requestCode);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onActivityResult(int, int,
     * android.content.Intent)
     */
    void onActivityResult(int requestCode,
                          int resultCode,
                          Intent data);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#createPendingResult(int,
     * android.content.Intent, int)
     */
    PendingIntent createPendingResult(int requestCode,
                                      Intent data,
                                      int flags);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#setRequestedOrientation(int)
     */
    void setRequestedOrientation(int requestedOrientation);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getRequestedOrientation()
     */
    int getRequestedOrientation();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getTaskId()
     */
    int getTaskId();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#isTaskRoot()
     */
    boolean isTaskRoot();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#moveTaskToBack(boolean)
     */
    boolean moveTaskToBack(boolean nonRoot);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getLocalClassName()
     */
    String getLocalClassName();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getComponentName()
     */
    ComponentName getComponentName();

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getPreferences(int)
     */
    SharedPreferences getPreferences(int mode);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getSystemService(java.lang.String)
     */
    Object getSystemService(String name);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#setTitle(java.lang.CharSequence)
     */
    void setTitle(CharSequence title);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#setTitle(int)
     */
    void setTitle(int titleId);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#setTitleColor(int)
     */
    void setTitleColor(int textColor);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onTitleChanged(java.lang.CharSequence, int)
     */
    void onTitleChanged(CharSequence title,
                        int color);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onChildTitleChanged(android.app.Activity,
     * java.lang.CharSequence)
     */
    void onChildTitleChanged(Activity childActivity,
                             CharSequence title);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateView(java.lang.String,
     * android.content.Context, android.util.AttributeSet)
     */
    View onCreateView(String name,
                      Context context,
                      AttributeSet attrs);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateView(android.view.View,
     * java.lang.String, android.content.Context, android.util.AttributeSet)
     */
    View onCreateView(View parent,
                      String name,
                      Context context,
                      AttributeSet attrs);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#dump(java.lang.String, java.io.FileDescriptor,
     * java.io.PrintWriter, java.lang.String[])
     */
    void dump(String prefix,
              FileDescriptor fd,
              PrintWriter writer,
              String[] args);

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#startActionMode(android.view.ActionMode.Callback)
     */
    ActionMode startActionMode(Callback callback);

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.app.Activity#onWindowStartingActionMode(android.view.ActionMode
     * .Callback)
     */
    ActionMode onWindowStartingActionMode(Callback callback);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onActionModeStarted(android.view.ActionMode)
     */
    void onActionModeStarted(ActionMode mode);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onActionModeFinished(android.view.ActionMode)
     */
    void onActionModeFinished(ActionMode mode);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#shouldUpRecreateTask(android.content.Intent)
     */
    boolean shouldUpRecreateTask(Intent targetIntent);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#navigateUpTo(android.content.Intent)
     */
    boolean navigateUpTo(Intent upIntent);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#navigateUpToFromChild(android.app.Activity,
     * android.content.Intent)
     */
    boolean navigateUpToFromChild(Activity child,
                                  Intent upIntent);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#getParentActivityIntent()
     */
    Intent getParentActivityIntent();

    void performCreate(Bundle icicle);

    void performDestroy();

    void performRestoreInstanceState(Bundle savedInstanceState);

    @DelegateFactory.Parameter
    void setMResumed(boolean b);

    void performSaveInstanceState(Bundle outState);

    void performPause();

    void performUserLeaving();

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
