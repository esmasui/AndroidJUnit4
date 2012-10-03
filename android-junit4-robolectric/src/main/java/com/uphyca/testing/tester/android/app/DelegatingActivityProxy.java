package com.uphyca.testing.tester.android.app;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.Instrumentation;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
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
import com.uphyca.testing.proxy.android.app.ActivityProxy;

public class DelegatingActivityProxy implements ActivityProxy {

    public static final ActivityProxy create(Activity activity) {
        return new DelegatingActivityProxy(activity);
    }

    private final Activity mActivity;
    private final ActivityProxy mProxy;

    private DelegatingActivityProxy(Activity activity) {
        mActivity = activity;
        mProxy = DelegateFactory.create(ActivityProxy.class, mActivity);
    }

    /**
     * @param resid
     * @see android.view.ContextThemeWrapper#setTheme(int)
     */
    public void setTheme(int resid) {
        mActivity.setTheme(resid);
    }

    /**
     * @return
     * @see android.view.ContextThemeWrapper#getTheme()
     */
    public Theme getTheme() {
        return mActivity.getTheme();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getBaseContext()
     */
    public Context getBaseContext() {
        return mActivity.getBaseContext();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getAssets()
     */
    public AssetManager getAssets() {
        return mActivity.getAssets();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getResources()
     */
    public Resources getResources() {
        return mActivity.getResources();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getPackageManager()
     */
    public PackageManager getPackageManager() {
        return mActivity.getPackageManager();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getContentResolver()
     */
    public ContentResolver getContentResolver() {
        return mActivity.getContentResolver();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getMainLooper()
     */
    public Looper getMainLooper() {
        return mActivity.getMainLooper();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getApplicationContext()
     */
    public Context getApplicationContext() {
        return mActivity.getApplicationContext();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getClassLoader()
     */
    public ClassLoader getClassLoader() {
        return mActivity.getClassLoader();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getPackageName()
     */
    public String getPackageName() {
        return mActivity.getPackageName();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getApplicationInfo()
     */
    public ApplicationInfo getApplicationInfo() {
        return mActivity.getApplicationInfo();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getPackageResourcePath()
     */
    public String getPackageResourcePath() {
        return mActivity.getPackageResourcePath();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getPackageCodePath()
     */
    public String getPackageCodePath() {
        return mActivity.getPackageCodePath();
    }

    /**
     * @param name
     * @param mode
     * @return
     * @see android.content.ContextWrapper#getSharedPreferences(java.lang.String,
     *      int)
     */
    public SharedPreferences getSharedPreferences(String name,
                                                  int mode) {
        return mActivity.getSharedPreferences(name, mode);
    }

    /**
     * @param name
     * @return
     * @throws FileNotFoundException
     * @see android.content.ContextWrapper#openFileInput(java.lang.String)
     */
    public FileInputStream openFileInput(String name) throws FileNotFoundException {
        return mActivity.openFileInput(name);
    }

    /**
     * @param name
     * @param mode
     * @return
     * @throws FileNotFoundException
     * @see android.content.ContextWrapper#openFileOutput(java.lang.String, int)
     */
    public FileOutputStream openFileOutput(String name,
                                           int mode) throws FileNotFoundException {
        return mActivity.openFileOutput(name, mode);
    }

    /**
     * @param name
     * @return
     * @see android.content.ContextWrapper#deleteFile(java.lang.String)
     */
    public boolean deleteFile(String name) {
        return mActivity.deleteFile(name);
    }

    /**
     * @param name
     * @return
     * @see android.content.ContextWrapper#getFileStreamPath(java.lang.String)
     */
    public File getFileStreamPath(String name) {
        return mActivity.getFileStreamPath(name);
    }

    /**
     * @return
     * @see android.content.ContextWrapper#fileList()
     */
    public String[] fileList() {
        return mActivity.fileList();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getFilesDir()
     */
    public File getFilesDir() {
        return mActivity.getFilesDir();
    }

    /**
     * @param type
     * @return
     * @see android.content.ContextWrapper#getExternalFilesDir(java.lang.String)
     */
    public File getExternalFilesDir(String type) {
        return mActivity.getExternalFilesDir(type);
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getObbDir()
     */
    public File getObbDir() {
        return mActivity.getObbDir();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getCacheDir()
     */
    public File getCacheDir() {
        return mActivity.getCacheDir();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getExternalCacheDir()
     */
    public File getExternalCacheDir() {
        return mActivity.getExternalCacheDir();
    }

    /**
     * @param name
     * @param mode
     * @return
     * @see android.content.ContextWrapper#getDir(java.lang.String, int)
     */
    public File getDir(String name,
                       int mode) {
        return mActivity.getDir(name, mode);
    }

    /**
     * @param name
     * @param mode
     * @param factory
     * @return
     * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String,
     *      int, android.database.sqlite.SQLiteDatabase.CursorFactory)
     */
    public SQLiteDatabase openOrCreateDatabase(String name,
                                               int mode,
                                               CursorFactory factory) {
        return mActivity.openOrCreateDatabase(name, mode, factory);
    }

    /**
     * @param name
     * @param mode
     * @param factory
     * @param errorHandler
     * @return
     * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String,
     *      int, android.database.sqlite.SQLiteDatabase.CursorFactory,
     *      android.database.DatabaseErrorHandler)
     */
    public SQLiteDatabase openOrCreateDatabase(String name,
                                               int mode,
                                               CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        return mActivity.openOrCreateDatabase(name, mode, factory, errorHandler);
    }

    /**
     * @param name
     * @return
     * @see android.content.ContextWrapper#deleteDatabase(java.lang.String)
     */
    public boolean deleteDatabase(String name) {
        return mActivity.deleteDatabase(name);
    }

    /**
     * @param name
     * @return
     * @see android.content.ContextWrapper#getDatabasePath(java.lang.String)
     */
    public File getDatabasePath(String name) {
        return mActivity.getDatabasePath(name);
    }

    /**
     * @return
     * @see android.content.ContextWrapper#databaseList()
     */
    public String[] databaseList() {
        return mActivity.databaseList();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getWallpaper()
     */
    public Drawable getWallpaper() {
        return mActivity.getWallpaper();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#peekWallpaper()
     */
    public Drawable peekWallpaper() {
        return mActivity.peekWallpaper();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getWallpaperDesiredMinimumWidth()
     */
    public int getWallpaperDesiredMinimumWidth() {
        return mActivity.getWallpaperDesiredMinimumWidth();
    }

    /**
     * @return
     * @see android.content.ContextWrapper#getWallpaperDesiredMinimumHeight()
     */
    public int getWallpaperDesiredMinimumHeight() {
        return mActivity.getWallpaperDesiredMinimumHeight();
    }

    /**
     * @param bitmap
     * @throws IOException
     * @see android.content.ContextWrapper#setWallpaper(android.graphics.Bitmap)
     */
    public void setWallpaper(Bitmap bitmap) throws IOException {
        mActivity.setWallpaper(bitmap);
    }

    /**
     * @param data
     * @throws IOException
     * @see android.content.ContextWrapper#setWallpaper(java.io.InputStream)
     */
    public void setWallpaper(InputStream data) throws IOException {
        mActivity.setWallpaper(data);
    }

    /**
     * @param o
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        return mActivity.equals(o);
    }

    /**
     * @throws IOException
     * @see android.content.ContextWrapper#clearWallpaper()
     */
    public void clearWallpaper() throws IOException {
        mActivity.clearWallpaper();
    }

    /**
     * @param intent
     * @see android.content.ContextWrapper#sendBroadcast(android.content.Intent)
     */
    public void sendBroadcast(Intent intent) {
        mActivity.sendBroadcast(intent);
    }

    /**
     * @param intent
     * @param receiverPermission
     * @see android.content.ContextWrapper#sendBroadcast(android.content.Intent,
     *      java.lang.String)
     */
    public void sendBroadcast(Intent intent,
                              String receiverPermission) {
        mActivity.sendBroadcast(intent, receiverPermission);
    }

    /**
     * @param intent
     * @param receiverPermission
     * @see android.content.ContextWrapper#sendOrderedBroadcast(android.content.Intent,
     *      java.lang.String)
     */
    public void sendOrderedBroadcast(Intent intent,
                                     String receiverPermission) {
        mActivity.sendOrderedBroadcast(intent, receiverPermission);
    }

    /**
     * @param intent
     * @param receiverPermission
     * @param resultReceiver
     * @param scheduler
     * @param initialCode
     * @param initialData
     * @param initialExtras
     * @see android.content.ContextWrapper#sendOrderedBroadcast(android.content.Intent,
     *      java.lang.String, android.content.BroadcastReceiver,
     *      android.os.Handler, int, java.lang.String, android.os.Bundle)
     */
    public void sendOrderedBroadcast(Intent intent,
                                     String receiverPermission,
                                     BroadcastReceiver resultReceiver,
                                     Handler scheduler,
                                     int initialCode,
                                     String initialData,
                                     Bundle initialExtras) {
        mActivity.sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    /**
     * @param intent
     * @see android.content.ContextWrapper#sendStickyBroadcast(android.content.Intent)
     */
    public void sendStickyBroadcast(Intent intent) {
        mActivity.sendStickyBroadcast(intent);
    }

    /**
     * @param intent
     * @param resultReceiver
     * @param scheduler
     * @param initialCode
     * @param initialData
     * @param initialExtras
     * @see android.content.ContextWrapper#sendStickyOrderedBroadcast(android.content.Intent,
     *      android.content.BroadcastReceiver, android.os.Handler, int,
     *      java.lang.String, android.os.Bundle)
     */
    public void sendStickyOrderedBroadcast(Intent intent,
                                           BroadcastReceiver resultReceiver,
                                           Handler scheduler,
                                           int initialCode,
                                           String initialData,
                                           Bundle initialExtras) {
        mActivity.sendStickyOrderedBroadcast(intent, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    /**
     * @param intent
     * @see android.content.ContextWrapper#removeStickyBroadcast(android.content.Intent)
     */
    public void removeStickyBroadcast(Intent intent) {
        mActivity.removeStickyBroadcast(intent);
    }

    /**
     * @param receiver
     * @param filter
     * @return
     * @see android.content.ContextWrapper#registerReceiver(android.content.BroadcastReceiver,
     *      android.content.IntentFilter)
     */
    public Intent registerReceiver(BroadcastReceiver receiver,
                                   IntentFilter filter) {
        return mActivity.registerReceiver(receiver, filter);
    }

    /**
     * @param receiver
     * @param filter
     * @param broadcastPermission
     * @param scheduler
     * @return
     * @see android.content.ContextWrapper#registerReceiver(android.content.BroadcastReceiver,
     *      android.content.IntentFilter, java.lang.String, android.os.Handler)
     */
    public Intent registerReceiver(BroadcastReceiver receiver,
                                   IntentFilter filter,
                                   String broadcastPermission,
                                   Handler scheduler) {
        return mActivity.registerReceiver(receiver, filter, broadcastPermission, scheduler);
    }

    /**
     * @param receiver
     * @see android.content.ContextWrapper#unregisterReceiver(android.content.BroadcastReceiver)
     */
    public void unregisterReceiver(BroadcastReceiver receiver) {
        mActivity.unregisterReceiver(receiver);
    }

    /**
     * @param service
     * @return
     * @see android.content.ContextWrapper#startService(android.content.Intent)
     */
    public ComponentName startService(Intent service) {
        return mActivity.startService(service);
    }

    /**
     * @param name
     * @return
     * @see android.content.ContextWrapper#stopService(android.content.Intent)
     */
    public boolean stopService(Intent name) {
        return mActivity.stopService(name);
    }

    /**
     * @param service
     * @param conn
     * @param flags
     * @return
     * @see android.content.ContextWrapper#bindService(android.content.Intent,
     *      android.content.ServiceConnection, int)
     */
    public boolean bindService(Intent service,
                               ServiceConnection conn,
                               int flags) {
        return mActivity.bindService(service, conn, flags);
    }

    /**
     * @return
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return mActivity.hashCode();
    }

    /**
     * @param conn
     * @see android.content.ContextWrapper#unbindService(android.content.ServiceConnection)
     */
    public void unbindService(ServiceConnection conn) {
        mActivity.unbindService(conn);
    }

    /**
     * @param className
     * @param profileFile
     * @param arguments
     * @return
     * @see android.content.ContextWrapper#startInstrumentation(android.content.ComponentName,
     *      java.lang.String, android.os.Bundle)
     */
    public boolean startInstrumentation(ComponentName className,
                                        String profileFile,
                                        Bundle arguments) {
        return mActivity.startInstrumentation(className, profileFile, arguments);
    }

    /**
     * @param permission
     * @param pid
     * @param uid
     * @return
     * @see android.content.ContextWrapper#checkPermission(java.lang.String,
     *      int, int)
     */
    public int checkPermission(String permission,
                               int pid,
                               int uid) {
        return mActivity.checkPermission(permission, pid, uid);
    }

    /**
     * @param callback
     * @see android.content.Context#registerComponentCallbacks(android.content.ComponentCallbacks)
     */
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        mActivity.registerComponentCallbacks(callback);
    }

    /**
     * @param permission
     * @return
     * @see android.content.ContextWrapper#checkCallingPermission(java.lang.String)
     */
    public int checkCallingPermission(String permission) {
        return mActivity.checkCallingPermission(permission);
    }

    /**
     * @param permission
     * @return
     * @see android.content.ContextWrapper#checkCallingOrSelfPermission(java.lang.String)
     */
    public int checkCallingOrSelfPermission(String permission) {
        return mActivity.checkCallingOrSelfPermission(permission);
    }

    /**
     * @param permission
     * @param pid
     * @param uid
     * @param message
     * @see android.content.ContextWrapper#enforcePermission(java.lang.String,
     *      int, int, java.lang.String)
     */
    public void enforcePermission(String permission,
                                  int pid,
                                  int uid,
                                  String message) {
        mActivity.enforcePermission(permission, pid, uid, message);
    }

    /**
     * @param permission
     * @param message
     * @see android.content.ContextWrapper#enforceCallingPermission(java.lang.String,
     *      java.lang.String)
     */
    public void enforceCallingPermission(String permission,
                                         String message) {
        mActivity.enforceCallingPermission(permission, message);
    }

    /**
     * @param permission
     * @param message
     * @see android.content.ContextWrapper#enforceCallingOrSelfPermission(java.lang.String,
     *      java.lang.String)
     */
    public void enforceCallingOrSelfPermission(String permission,
                                               String message) {
        mActivity.enforceCallingOrSelfPermission(permission, message);
    }

    /**
     * @param callback
     * @see android.content.Context#unregisterComponentCallbacks(android.content.ComponentCallbacks)
     */
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        mActivity.unregisterComponentCallbacks(callback);
    }

    /**
     * @param toPackage
     * @param uri
     * @param modeFlags
     * @see android.content.ContextWrapper#grantUriPermission(java.lang.String,
     *      android.net.Uri, int)
     */
    public void grantUriPermission(String toPackage,
                                   Uri uri,
                                   int modeFlags) {
        mActivity.grantUriPermission(toPackage, uri, modeFlags);
    }

    /**
     * @param resId
     * @return
     * @see android.content.Context#getText(int)
     */
    public final CharSequence getText(int resId) {
        return mActivity.getText(resId);
    }

    /**
     * @param uri
     * @param modeFlags
     * @see android.content.ContextWrapper#revokeUriPermission(android.net.Uri,
     *      int)
     */
    public void revokeUriPermission(Uri uri,
                                    int modeFlags) {
        mActivity.revokeUriPermission(uri, modeFlags);
    }

    /**
     * @param uri
     * @param pid
     * @param uid
     * @param modeFlags
     * @return
     * @see android.content.ContextWrapper#checkUriPermission(android.net.Uri,
     *      int, int, int)
     */
    public int checkUriPermission(Uri uri,
                                  int pid,
                                  int uid,
                                  int modeFlags) {
        return mActivity.checkUriPermission(uri, pid, uid, modeFlags);
    }

    /**
     * @param resId
     * @return
     * @see android.content.Context#getString(int)
     */
    public final String getString(int resId) {
        return mActivity.getString(resId);
    }

    /**
     * @param uri
     * @param modeFlags
     * @return
     * @see android.content.ContextWrapper#checkCallingUriPermission(android.net.Uri,
     *      int)
     */
    public int checkCallingUriPermission(Uri uri,
                                         int modeFlags) {
        return mActivity.checkCallingUriPermission(uri, modeFlags);
    }

    /**
     * @param uri
     * @param modeFlags
     * @return
     * @see android.content.ContextWrapper#checkCallingOrSelfUriPermission(android.net.Uri,
     *      int)
     */
    public int checkCallingOrSelfUriPermission(Uri uri,
                                               int modeFlags) {
        return mActivity.checkCallingOrSelfUriPermission(uri, modeFlags);
    }

    /**
     * @param resId
     * @param formatArgs
     * @return
     * @see android.content.Context#getString(int, java.lang.Object[])
     */
    public final String getString(int resId,
                                  Object... formatArgs) {
        return mActivity.getString(resId, formatArgs);
    }

    /**
     * @param uri
     * @param readPermission
     * @param writePermission
     * @param pid
     * @param uid
     * @param modeFlags
     * @return
     * @see android.content.ContextWrapper#checkUriPermission(android.net.Uri,
     *      java.lang.String, java.lang.String, int, int, int)
     */
    public int checkUriPermission(Uri uri,
                                  String readPermission,
                                  String writePermission,
                                  int pid,
                                  int uid,
                                  int modeFlags) {
        return mActivity.checkUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags);
    }

    /**
     * @param uri
     * @param pid
     * @param uid
     * @param modeFlags
     * @param message
     * @see android.content.ContextWrapper#enforceUriPermission(android.net.Uri,
     *      int, int, int, java.lang.String)
     */
    public void enforceUriPermission(Uri uri,
                                     int pid,
                                     int uid,
                                     int modeFlags,
                                     String message) {
        mActivity.enforceUriPermission(uri, pid, uid, modeFlags, message);
    }

    /**
     * @param uri
     * @param modeFlags
     * @param message
     * @see android.content.ContextWrapper#enforceCallingUriPermission(android.net.Uri,
     *      int, java.lang.String)
     */
    public void enforceCallingUriPermission(Uri uri,
                                            int modeFlags,
                                            String message) {
        mActivity.enforceCallingUriPermission(uri, modeFlags, message);
    }

    /**
     * @param uri
     * @param modeFlags
     * @param message
     * @see android.content.ContextWrapper#enforceCallingOrSelfUriPermission(android.net.Uri,
     *      int, java.lang.String)
     */
    public void enforceCallingOrSelfUriPermission(Uri uri,
                                                  int modeFlags,
                                                  String message) {
        mActivity.enforceCallingOrSelfUriPermission(uri, modeFlags, message);
    }

    /**
     * @return
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return mActivity.toString();
    }

    /**
     * @param uri
     * @param readPermission
     * @param writePermission
     * @param pid
     * @param uid
     * @param modeFlags
     * @param message
     * @see android.content.ContextWrapper#enforceUriPermission(android.net.Uri,
     *      java.lang.String, java.lang.String, int, int, int, java.lang.String)
     */
    public void enforceUriPermission(Uri uri,
                                     String readPermission,
                                     String writePermission,
                                     int pid,
                                     int uid,
                                     int modeFlags,
                                     String message) {
        mActivity.enforceUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags, message);
    }

    /**
     * @param attrs
     * @return
     * @see android.content.Context#obtainStyledAttributes(int[])
     */
    public final TypedArray obtainStyledAttributes(int[] attrs) {
        return mActivity.obtainStyledAttributes(attrs);
    }

    /**
     * @param packageName
     * @param flags
     * @return
     * @throws NameNotFoundException
     * @see android.content.ContextWrapper#createPackageContext(java.lang.String,
     *      int)
     */
    public Context createPackageContext(String packageName,
                                        int flags) throws NameNotFoundException {
        return mActivity.createPackageContext(packageName, flags);
    }

    /**
     * @return
     * @see android.content.ContextWrapper#isRestricted()
     */
    public boolean isRestricted() {
        return mActivity.isRestricted();
    }

    /**
     * @param resid
     * @param attrs
     * @return
     * @throws NotFoundException
     * @see android.content.Context#obtainStyledAttributes(int, int[])
     */
    public final TypedArray obtainStyledAttributes(int resid,
                                                   int[] attrs) throws NotFoundException {
        return mActivity.obtainStyledAttributes(resid, attrs);
    }

    /**
     * @param set
     * @param attrs
     * @return
     * @see android.content.Context#obtainStyledAttributes(android.util.AttributeSet,
     *      int[])
     */
    public final TypedArray obtainStyledAttributes(AttributeSet set,
                                                   int[] attrs) {
        return mActivity.obtainStyledAttributes(set, attrs);
    }

    /**
     * @param set
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     * @return
     * @see android.content.Context#obtainStyledAttributes(android.util.AttributeSet,
     *      int[], int, int)
     */
    public final TypedArray obtainStyledAttributes(AttributeSet set,
                                                   int[] attrs,
                                                   int defStyleAttr,
                                                   int defStyleRes) {
        return mActivity.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * @return
     * @see android.app.Activity#getIntent()
     */
    public Intent getIntent() {
        return mActivity.getIntent();
    }

    /**
     * @param newIntent
     * @see android.app.Activity#setIntent(android.content.Intent)
     */
    public void setIntent(Intent newIntent) {
        mActivity.setIntent(newIntent);
    }

    /**
     * @return
     * @see android.app.Activity#getApplication()
     */
    public final Application getApplication() {
        return mActivity.getApplication();
    }

    /**
     * @return
     * @see android.app.Activity#isChild()
     */
    public final boolean isChild() {
        return mActivity.isChild();
    }

    /**
     * @return
     * @see android.app.Activity#getParent()
     */
    public final Activity getParent() {
        return mActivity.getParent();
    }

    /**
     * @return
     * @see android.app.Activity#getWindowManager()
     */
    public WindowManager getWindowManager() {
        return mActivity.getWindowManager();
    }

    /**
     * @return
     * @see android.app.Activity#getWindow()
     */
    public Window getWindow() {
        return mActivity.getWindow();
    }

    /**
     * @return
     * @see android.app.Activity#getLoaderManager()
     */
    public LoaderManager getLoaderManager() {
        return mActivity.getLoaderManager();
    }

    /**
     * @return
     * @see android.app.Activity#getCurrentFocus()
     */
    public View getCurrentFocus() {
        return mActivity.getCurrentFocus();
    }

    /**
     * @param outBitmap
     * @param canvas
     * @return
     * @see android.app.Activity#onCreateThumbnail(android.graphics.Bitmap,
     *      android.graphics.Canvas)
     */
    public boolean onCreateThumbnail(Bitmap outBitmap,
                                     Canvas canvas) {
        return mActivity.onCreateThumbnail(outBitmap, canvas);
    }

    /**
     * @return
     * @see android.app.Activity#onCreateDescription()
     */
    public CharSequence onCreateDescription() {
        return mActivity.onCreateDescription();
    }

    /**
     * @param newConfig
     * @see android.app.Activity#onConfigurationChanged(android.content.res.Configuration)
     */
    public void onConfigurationChanged(Configuration newConfig) {
        mActivity.onConfigurationChanged(newConfig);
    }

    /**
     * @return
     * @see android.app.Activity#getChangingConfigurations()
     */
    public int getChangingConfigurations() {
        return mActivity.getChangingConfigurations();
    }

    /**
     * @return
     * @deprecated
     * @see android.app.Activity#getLastNonConfigurationInstance()
     */
    public Object getLastNonConfigurationInstance() {
        return mActivity.getLastNonConfigurationInstance();
    }

    /**
     * @return
     * @deprecated
     * @see android.app.Activity#onRetainNonConfigurationInstance()
     */
    public Object onRetainNonConfigurationInstance() {
        return mActivity.onRetainNonConfigurationInstance();
    }

    /**
     * 
     * @see android.app.Activity#onLowMemory()
     */
    public void onLowMemory() {
        mActivity.onLowMemory();
    }

    /**
     * @param level
     * @see android.app.Activity#onTrimMemory(int)
     */
    public void onTrimMemory(int level) {
        mActivity.onTrimMemory(level);
    }

    /**
     * @return
     * @see android.app.Activity#getFragmentManager()
     */
    public FragmentManager getFragmentManager() {
        return mActivity.getFragmentManager();
    }

    /**
     * @param fragment
     * @see android.app.Activity#onAttachFragment(android.app.Fragment)
     */
    public void onAttachFragment(Fragment fragment) {
        mActivity.onAttachFragment(fragment);
    }

    /**
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     * @deprecated
     * @see android.app.Activity#managedQuery(android.net.Uri,
     *      java.lang.String[], java.lang.String, java.lang.String[],
     *      java.lang.String)
     */
    public final Cursor managedQuery(Uri uri,
                                     String[] projection,
                                     String selection,
                                     String[] selectionArgs,
                                     String sortOrder) {
        return mActivity.managedQuery(uri, projection, selection, selectionArgs, sortOrder);
    }

    /**
     * @param c
     * @deprecated
     * @see android.app.Activity#startManagingCursor(android.database.Cursor)
     */
    public void startManagingCursor(Cursor c) {
        mActivity.startManagingCursor(c);
    }

    /**
     * @param c
     * @deprecated
     * @see android.app.Activity#stopManagingCursor(android.database.Cursor)
     */
    public void stopManagingCursor(Cursor c) {
        mActivity.stopManagingCursor(c);
    }

    /**
     * @param id
     * @return
     * @see android.app.Activity#findViewById(int)
     */
    public View findViewById(int id) {
        return mActivity.findViewById(id);
    }

    /**
     * @return
     * @see android.app.Activity#getActionBar()
     */
    public ActionBar getActionBar() {
        return mActivity.getActionBar();
    }

    /**
     * @param layoutResID
     * @see android.app.Activity#setContentView(int)
     */
    public void setContentView(int layoutResID) {
        mActivity.setContentView(layoutResID);
    }

    /**
     * @param view
     * @see android.app.Activity#setContentView(android.view.View)
     */
    public void setContentView(View view) {
        mActivity.setContentView(view);
    }

    /**
     * @param view
     * @param params
     * @see android.app.Activity#setContentView(android.view.View,
     *      android.view.ViewGroup.LayoutParams)
     */
    public void setContentView(View view,
                               LayoutParams params) {
        mActivity.setContentView(view, params);
    }

    /**
     * @param view
     * @param params
     * @see android.app.Activity#addContentView(android.view.View,
     *      android.view.ViewGroup.LayoutParams)
     */
    public void addContentView(View view,
                               LayoutParams params) {
        mActivity.addContentView(view, params);
    }

    /**
     * @param finish
     * @see android.app.Activity#setFinishOnTouchOutside(boolean)
     */
    public void setFinishOnTouchOutside(boolean finish) {
        mActivity.setFinishOnTouchOutside(finish);
    }

    /**
     * @param mode
     * @see android.app.Activity#setDefaultKeyMode(int)
     */
    public final void setDefaultKeyMode(int mode) {
        mActivity.setDefaultKeyMode(mode);
    }

    /**
     * @param keyCode
     * @param event
     * @return
     * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
     */
    public boolean onKeyDown(int keyCode,
                             KeyEvent event) {
        return mActivity.onKeyDown(keyCode, event);
    }

    /**
     * @param keyCode
     * @param event
     * @return
     * @see android.app.Activity#onKeyLongPress(int, android.view.KeyEvent)
     */
    public boolean onKeyLongPress(int keyCode,
                                  KeyEvent event) {
        return mActivity.onKeyLongPress(keyCode, event);
    }

    /**
     * @param keyCode
     * @param event
     * @return
     * @see android.app.Activity#onKeyUp(int, android.view.KeyEvent)
     */
    public boolean onKeyUp(int keyCode,
                           KeyEvent event) {
        return mActivity.onKeyUp(keyCode, event);
    }

    /**
     * @param keyCode
     * @param repeatCount
     * @param event
     * @return
     * @see android.app.Activity#onKeyMultiple(int, int, android.view.KeyEvent)
     */
    public boolean onKeyMultiple(int keyCode,
                                 int repeatCount,
                                 KeyEvent event) {
        return mActivity.onKeyMultiple(keyCode, repeatCount, event);
    }

    /**
     * 
     * @see android.app.Activity#onBackPressed()
     */
    public void onBackPressed() {
        mActivity.onBackPressed();
    }

    /**
     * @param keyCode
     * @param event
     * @return
     * @see android.app.Activity#onKeyShortcut(int, android.view.KeyEvent)
     */
    public boolean onKeyShortcut(int keyCode,
                                 KeyEvent event) {
        return mActivity.onKeyShortcut(keyCode, event);
    }

    /**
     * @param event
     * @return
     * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
     */
    public boolean onTouchEvent(MotionEvent event) {
        return mActivity.onTouchEvent(event);
    }

    /**
     * @param event
     * @return
     * @see android.app.Activity#onTrackballEvent(android.view.MotionEvent)
     */
    public boolean onTrackballEvent(MotionEvent event) {
        return mActivity.onTrackballEvent(event);
    }

    /**
     * @param event
     * @return
     * @see android.app.Activity#onGenericMotionEvent(android.view.MotionEvent)
     */
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mActivity.onGenericMotionEvent(event);
    }

    /**
     * 
     * @see android.app.Activity#onUserInteraction()
     */
    public void onUserInteraction() {
        mActivity.onUserInteraction();
    }

    /**
     * @param params
     * @see android.app.Activity#onWindowAttributesChanged(android.view.WindowManager.LayoutParams)
     */
    public void onWindowAttributesChanged(android.view.WindowManager.LayoutParams params) {
        mActivity.onWindowAttributesChanged(params);
    }

    /**
     * 
     * @see android.app.Activity#onContentChanged()
     */
    public void onContentChanged() {
        mActivity.onContentChanged();
    }

    /**
     * @param hasFocus
     * @see android.app.Activity#onWindowFocusChanged(boolean)
     */
    public void onWindowFocusChanged(boolean hasFocus) {
        mActivity.onWindowFocusChanged(hasFocus);
    }

    /**
     * 
     * @see android.app.Activity#onAttachedToWindow()
     */
    public void onAttachedToWindow() {
        mActivity.onAttachedToWindow();
    }

    /**
     * 
     * @see android.app.Activity#onDetachedFromWindow()
     */
    public void onDetachedFromWindow() {
        mActivity.onDetachedFromWindow();
    }

    /**
     * @return
     * @see android.app.Activity#hasWindowFocus()
     */
    public boolean hasWindowFocus() {
        return mActivity.hasWindowFocus();
    }

    /**
     * @param event
     * @return
     * @see android.app.Activity#dispatchKeyEvent(android.view.KeyEvent)
     */
    public boolean dispatchKeyEvent(KeyEvent event) {
        return mActivity.dispatchKeyEvent(event);
    }

    /**
     * @param event
     * @return
     * @see android.app.Activity#dispatchKeyShortcutEvent(android.view.KeyEvent)
     */
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return mActivity.dispatchKeyShortcutEvent(event);
    }

    /**
     * @param ev
     * @return
     * @see android.app.Activity#dispatchTouchEvent(android.view.MotionEvent)
     */
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mActivity.dispatchTouchEvent(ev);
    }

    /**
     * @param ev
     * @return
     * @see android.app.Activity#dispatchTrackballEvent(android.view.MotionEvent)
     */
    public boolean dispatchTrackballEvent(MotionEvent ev) {
        return mActivity.dispatchTrackballEvent(ev);
    }

    /**
     * @param ev
     * @return
     * @see android.app.Activity#dispatchGenericMotionEvent(android.view.MotionEvent)
     */
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        return mActivity.dispatchGenericMotionEvent(ev);
    }

    /**
     * @param event
     * @return
     * @see android.app.Activity#dispatchPopulateAccessibilityEvent(android.view.accessibility.AccessibilityEvent)
     */
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return mActivity.dispatchPopulateAccessibilityEvent(event);
    }

    /**
     * @param featureId
     * @return
     * @see android.app.Activity#onCreatePanelView(int)
     */
    public View onCreatePanelView(int featureId) {
        return mActivity.onCreatePanelView(featureId);
    }

    /**
     * @param featureId
     * @param menu
     * @return
     * @see android.app.Activity#onCreatePanelMenu(int, android.view.Menu)
     */
    public boolean onCreatePanelMenu(int featureId,
                                     Menu menu) {
        return mActivity.onCreatePanelMenu(featureId, menu);
    }

    /**
     * @param featureId
     * @param view
     * @param menu
     * @return
     * @see android.app.Activity#onPreparePanel(int, android.view.View,
     *      android.view.Menu)
     */
    public boolean onPreparePanel(int featureId,
                                  View view,
                                  Menu menu) {
        return mActivity.onPreparePanel(featureId, view, menu);
    }

    /**
     * @param featureId
     * @param menu
     * @return
     * @see android.app.Activity#onMenuOpened(int, android.view.Menu)
     */
    public boolean onMenuOpened(int featureId,
                                Menu menu) {
        return mActivity.onMenuOpened(featureId, menu);
    }

    /**
     * @param featureId
     * @param item
     * @return
     * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
     */
    public boolean onMenuItemSelected(int featureId,
                                      MenuItem item) {
        return mActivity.onMenuItemSelected(featureId, item);
    }

    /**
     * @param featureId
     * @param menu
     * @see android.app.Activity#onPanelClosed(int, android.view.Menu)
     */
    public void onPanelClosed(int featureId,
                              Menu menu) {
        mActivity.onPanelClosed(featureId, menu);
    }

    /**
     * 
     * @see android.app.Activity#invalidateOptionsMenu()
     */
    public void invalidateOptionsMenu() {
        mActivity.invalidateOptionsMenu();
    }

    /**
     * @param menu
     * @return
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        return mActivity.onCreateOptionsMenu(menu);
    }

    /**
     * @param menu
     * @return
     * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
     */
    public boolean onPrepareOptionsMenu(Menu menu) {
        return mActivity.onPrepareOptionsMenu(menu);
    }

    /**
     * @param item
     * @return
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        return mActivity.onOptionsItemSelected(item);
    }

    /**
     * @return
     * @see android.app.Activity#onNavigateUp()
     */
    public boolean onNavigateUp() {
        return mActivity.onNavigateUp();
    }

    /**
     * @param child
     * @return
     * @see android.app.Activity#onNavigateUpFromChild(android.app.Activity)
     */
    public boolean onNavigateUpFromChild(Activity child) {
        return mActivity.onNavigateUpFromChild(child);
    }

    /**
     * @param builder
     * @see android.app.Activity#onCreateNavigateUpTaskStack(android.app.TaskStackBuilder)
     */
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        mActivity.onCreateNavigateUpTaskStack(builder);
    }

    /**
     * @param builder
     * @see android.app.Activity#onPrepareNavigateUpTaskStack(android.app.TaskStackBuilder)
     */
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
        mActivity.onPrepareNavigateUpTaskStack(builder);
    }

    /**
     * @param menu
     * @see android.app.Activity#onOptionsMenuClosed(android.view.Menu)
     */
    public void onOptionsMenuClosed(Menu menu) {
        mActivity.onOptionsMenuClosed(menu);
    }

    /**
     * 
     * @see android.app.Activity#openOptionsMenu()
     */
    public void openOptionsMenu() {
        mActivity.openOptionsMenu();
    }

    /**
     * 
     * @see android.app.Activity#closeOptionsMenu()
     */
    public void closeOptionsMenu() {
        mActivity.closeOptionsMenu();
    }

    /**
     * @param menu
     * @param v
     * @param menuInfo
     * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu,
     *      android.view.View, android.view.ContextMenu.ContextMenuInfo)
     */
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenuInfo menuInfo) {
        mActivity.onCreateContextMenu(menu, v, menuInfo);
    }

    /**
     * @param view
     * @see android.app.Activity#registerForContextMenu(android.view.View)
     */
    public void registerForContextMenu(View view) {
        mActivity.registerForContextMenu(view);
    }

    /**
     * @param view
     * @see android.app.Activity#unregisterForContextMenu(android.view.View)
     */
    public void unregisterForContextMenu(View view) {
        mActivity.unregisterForContextMenu(view);
    }

    /**
     * @param view
     * @see android.app.Activity#openContextMenu(android.view.View)
     */
    public void openContextMenu(View view) {
        mActivity.openContextMenu(view);
    }

    /**
     * 
     * @see android.app.Activity#closeContextMenu()
     */
    public void closeContextMenu() {
        mActivity.closeContextMenu();
    }

    /**
     * @param item
     * @return
     * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
     */
    public boolean onContextItemSelected(MenuItem item) {
        return mActivity.onContextItemSelected(item);
    }

    /**
     * @param menu
     * @see android.app.Activity#onContextMenuClosed(android.view.Menu)
     */
    public void onContextMenuClosed(Menu menu) {
        mActivity.onContextMenuClosed(menu);
    }

    /**
     * @param id
     * @deprecated
     * @see android.app.Activity#showDialog(int)
     */
    public final void showDialog(int id) {
        mActivity.showDialog(id);
    }

    /**
     * @param id
     * @param args
     * @return
     * @deprecated
     * @see android.app.Activity#showDialog(int, android.os.Bundle)
     */
    public final boolean showDialog(int id,
                                    Bundle args) {
        return mActivity.showDialog(id, args);
    }

    /**
     * @param id
     * @deprecated
     * @see android.app.Activity#dismissDialog(int)
     */
    public final void dismissDialog(int id) {
        mActivity.dismissDialog(id);
    }

    /**
     * @param id
     * @deprecated
     * @see android.app.Activity#removeDialog(int)
     */
    public final void removeDialog(int id) {
        mActivity.removeDialog(id);
    }

    /**
     * @return
     * @see android.app.Activity#onSearchRequested()
     */
    public boolean onSearchRequested() {
        return mActivity.onSearchRequested();
    }

    /**
     * @param initialQuery
     * @param selectInitialQuery
     * @param appSearchData
     * @param globalSearch
     * @see android.app.Activity#startSearch(java.lang.String, boolean,
     *      android.os.Bundle, boolean)
     */
    public void startSearch(String initialQuery,
                            boolean selectInitialQuery,
                            Bundle appSearchData,
                            boolean globalSearch) {
        mActivity.startSearch(initialQuery, selectInitialQuery, appSearchData, globalSearch);
    }

    /**
     * @param query
     * @param appSearchData
     * @see android.app.Activity#triggerSearch(java.lang.String,
     *      android.os.Bundle)
     */
    public void triggerSearch(String query,
                              Bundle appSearchData) {
        mActivity.triggerSearch(query, appSearchData);
    }

    /**
     * @param get
     * @see android.app.Activity#takeKeyEvents(boolean)
     */
    public void takeKeyEvents(boolean get) {
        mActivity.takeKeyEvents(get);
    }

    /**
     * @param featureId
     * @return
     * @see android.app.Activity#requestWindowFeature(int)
     */
    public final boolean requestWindowFeature(int featureId) {
        return mActivity.requestWindowFeature(featureId);
    }

    /**
     * @param featureId
     * @param resId
     * @see android.app.Activity#setFeatureDrawableResource(int, int)
     */
    public final void setFeatureDrawableResource(int featureId,
                                                 int resId) {
        mActivity.setFeatureDrawableResource(featureId, resId);
    }

    /**
     * @param featureId
     * @param uri
     * @see android.app.Activity#setFeatureDrawableUri(int, android.net.Uri)
     */
    public final void setFeatureDrawableUri(int featureId,
                                            Uri uri) {
        mActivity.setFeatureDrawableUri(featureId, uri);
    }

    /**
     * @param featureId
     * @param drawable
     * @see android.app.Activity#setFeatureDrawable(int,
     *      android.graphics.drawable.Drawable)
     */
    public final void setFeatureDrawable(int featureId,
                                         Drawable drawable) {
        mActivity.setFeatureDrawable(featureId, drawable);
    }

    /**
     * @param featureId
     * @param alpha
     * @see android.app.Activity#setFeatureDrawableAlpha(int, int)
     */
    public final void setFeatureDrawableAlpha(int featureId,
                                              int alpha) {
        mActivity.setFeatureDrawableAlpha(featureId, alpha);
    }

    /**
     * @return
     * @see android.app.Activity#getLayoutInflater()
     */
    public LayoutInflater getLayoutInflater() {
        return mActivity.getLayoutInflater();
    }

    /**
     * @return
     * @see android.app.Activity#getMenuInflater()
     */
    public MenuInflater getMenuInflater() {
        return mActivity.getMenuInflater();
    }

    /**
     * @param intent
     * @param requestCode
     * @see android.app.Activity#startActivityForResult(android.content.Intent,
     *      int)
     */
    public void startActivityForResult(Intent intent,
                                       int requestCode) {
        mActivity.startActivityForResult(intent, requestCode);
    }

    /**
     * @param intent
     * @param requestCode
     * @param options
     * @see android.app.Activity#startActivityForResult(android.content.Intent,
     *      int, android.os.Bundle)
     */
    public void startActivityForResult(Intent intent,
                                       int requestCode,
                                       Bundle options) {
        mActivity.startActivityForResult(intent, requestCode, options);
    }

    /**
     * @param intent
     * @param requestCode
     * @param fillInIntent
     * @param flagsMask
     * @param flagsValues
     * @param extraFlags
     * @throws SendIntentException
     * @see android.app.Activity#startIntentSenderForResult(android.content.IntentSender,
     *      int, android.content.Intent, int, int, int)
     */
    public void startIntentSenderForResult(IntentSender intent,
                                           int requestCode,
                                           Intent fillInIntent,
                                           int flagsMask,
                                           int flagsValues,
                                           int extraFlags) throws SendIntentException {
        mActivity.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
    }

    /**
     * @param intent
     * @param requestCode
     * @param fillInIntent
     * @param flagsMask
     * @param flagsValues
     * @param extraFlags
     * @param options
     * @throws SendIntentException
     * @see android.app.Activity#startIntentSenderForResult(android.content.IntentSender,
     *      int, android.content.Intent, int, int, int, android.os.Bundle)
     */
    public void startIntentSenderForResult(IntentSender intent,
                                           int requestCode,
                                           Intent fillInIntent,
                                           int flagsMask,
                                           int flagsValues,
                                           int extraFlags,
                                           Bundle options) throws SendIntentException {
        mActivity.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    /**
     * @param intent
     * @see android.app.Activity#startActivity(android.content.Intent)
     */
    public void startActivity(Intent intent) {
        mActivity.startActivity(intent);
    }

    /**
     * @param intent
     * @param options
     * @see android.app.Activity#startActivity(android.content.Intent,
     *      android.os.Bundle)
     */
    public void startActivity(Intent intent,
                              Bundle options) {
        mActivity.startActivity(intent, options);
    }

    /**
     * @param intents
     * @see android.app.Activity#startActivities(android.content.Intent[])
     */
    public void startActivities(Intent[] intents) {
        mActivity.startActivities(intents);
    }

    /**
     * @param intents
     * @param options
     * @see android.app.Activity#startActivities(android.content.Intent[],
     *      android.os.Bundle)
     */
    public void startActivities(Intent[] intents,
                                Bundle options) {
        mActivity.startActivities(intents, options);
    }

    /**
     * @param intent
     * @param fillInIntent
     * @param flagsMask
     * @param flagsValues
     * @param extraFlags
     * @throws SendIntentException
     * @see android.app.Activity#startIntentSender(android.content.IntentSender,
     *      android.content.Intent, int, int, int)
     */
    public void startIntentSender(IntentSender intent,
                                  Intent fillInIntent,
                                  int flagsMask,
                                  int flagsValues,
                                  int extraFlags) throws SendIntentException {
        mActivity.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags);
    }

    /**
     * @param intent
     * @param fillInIntent
     * @param flagsMask
     * @param flagsValues
     * @param extraFlags
     * @param options
     * @throws SendIntentException
     * @see android.app.Activity#startIntentSender(android.content.IntentSender,
     *      android.content.Intent, int, int, int, android.os.Bundle)
     */
    public void startIntentSender(IntentSender intent,
                                  Intent fillInIntent,
                                  int flagsMask,
                                  int flagsValues,
                                  int extraFlags,
                                  Bundle options) throws SendIntentException {
        mActivity.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    /**
     * @param intent
     * @param requestCode
     * @return
     * @see android.app.Activity#startActivityIfNeeded(android.content.Intent,
     *      int)
     */
    public boolean startActivityIfNeeded(Intent intent,
                                         int requestCode) {
        return mActivity.startActivityIfNeeded(intent, requestCode);
    }

    /**
     * @param intent
     * @param requestCode
     * @param options
     * @return
     * @see android.app.Activity#startActivityIfNeeded(android.content.Intent,
     *      int, android.os.Bundle)
     */
    public boolean startActivityIfNeeded(Intent intent,
                                         int requestCode,
                                         Bundle options) {
        return mActivity.startActivityIfNeeded(intent, requestCode, options);
    }

    /**
     * @param intent
     * @return
     * @see android.app.Activity#startNextMatchingActivity(android.content.Intent)
     */
    public boolean startNextMatchingActivity(Intent intent) {
        return mActivity.startNextMatchingActivity(intent);
    }

    /**
     * @param intent
     * @param options
     * @return
     * @see android.app.Activity#startNextMatchingActivity(android.content.Intent,
     *      android.os.Bundle)
     */
    public boolean startNextMatchingActivity(Intent intent,
                                             Bundle options) {
        return mActivity.startNextMatchingActivity(intent, options);
    }

    /**
     * @param child
     * @param intent
     * @param requestCode
     * @see android.app.Activity#startActivityFromChild(android.app.Activity,
     *      android.content.Intent, int)
     */
    public void startActivityFromChild(Activity child,
                                       Intent intent,
                                       int requestCode) {
        mActivity.startActivityFromChild(child, intent, requestCode);
    }

    /**
     * @param child
     * @param intent
     * @param requestCode
     * @param options
     * @see android.app.Activity#startActivityFromChild(android.app.Activity,
     *      android.content.Intent, int, android.os.Bundle)
     */
    public void startActivityFromChild(Activity child,
                                       Intent intent,
                                       int requestCode,
                                       Bundle options) {
        mActivity.startActivityFromChild(child, intent, requestCode, options);
    }

    /**
     * @param fragment
     * @param intent
     * @param requestCode
     * @see android.app.Activity#startActivityFromFragment(android.app.Fragment,
     *      android.content.Intent, int)
     */
    public void startActivityFromFragment(Fragment fragment,
                                          Intent intent,
                                          int requestCode) {
        mActivity.startActivityFromFragment(fragment, intent, requestCode);
    }

    /**
     * @param fragment
     * @param intent
     * @param requestCode
     * @param options
     * @see android.app.Activity#startActivityFromFragment(android.app.Fragment,
     *      android.content.Intent, int, android.os.Bundle)
     */
    public void startActivityFromFragment(Fragment fragment,
                                          Intent intent,
                                          int requestCode,
                                          Bundle options) {
        mActivity.startActivityFromFragment(fragment, intent, requestCode, options);
    }

    /**
     * @param child
     * @param intent
     * @param requestCode
     * @param fillInIntent
     * @param flagsMask
     * @param flagsValues
     * @param extraFlags
     * @throws SendIntentException
     * @see android.app.Activity#startIntentSenderFromChild(android.app.Activity,
     *      android.content.IntentSender, int, android.content.Intent, int, int,
     *      int)
     */
    public void startIntentSenderFromChild(Activity child,
                                           IntentSender intent,
                                           int requestCode,
                                           Intent fillInIntent,
                                           int flagsMask,
                                           int flagsValues,
                                           int extraFlags) throws SendIntentException {
        mActivity.startIntentSenderFromChild(child, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
    }

    /**
     * @param child
     * @param intent
     * @param requestCode
     * @param fillInIntent
     * @param flagsMask
     * @param flagsValues
     * @param extraFlags
     * @param options
     * @throws SendIntentException
     * @see android.app.Activity#startIntentSenderFromChild(android.app.Activity,
     *      android.content.IntentSender, int, android.content.Intent, int, int,
     *      int, android.os.Bundle)
     */
    public void startIntentSenderFromChild(Activity child,
                                           IntentSender intent,
                                           int requestCode,
                                           Intent fillInIntent,
                                           int flagsMask,
                                           int flagsValues,
                                           int extraFlags,
                                           Bundle options) throws SendIntentException {
        mActivity.startIntentSenderFromChild(child, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    /**
     * @param enterAnim
     * @param exitAnim
     * @see android.app.Activity#overridePendingTransition(int, int)
     */
    public void overridePendingTransition(int enterAnim,
                                          int exitAnim) {
        mActivity.overridePendingTransition(enterAnim, exitAnim);
    }

    /**
     * @param resultCode
     * @see android.app.Activity#setResult(int)
     */
    public final void setResult(int resultCode) {
        mActivity.setResult(resultCode);
    }

    /**
     * @param resultCode
     * @param data
     * @see android.app.Activity#setResult(int, android.content.Intent)
     */
    public final void setResult(int resultCode,
                                Intent data) {
        mActivity.setResult(resultCode, data);
    }

    /**
     * @return
     * @see android.app.Activity#getCallingPackage()
     */
    public String getCallingPackage() {
        return mActivity.getCallingPackage();
    }

    /**
     * @return
     * @see android.app.Activity#getCallingActivity()
     */
    public ComponentName getCallingActivity() {
        return mActivity.getCallingActivity();
    }

    /**
     * @param visible
     * @see android.app.Activity#setVisible(boolean)
     */
    public void setVisible(boolean visible) {
        mActivity.setVisible(visible);
    }

    /**
     * @return
     * @see android.app.Activity#isFinishing()
     */
    public boolean isFinishing() {
        return mActivity.isFinishing();
    }

    /**
     * @return
     * @see android.app.Activity#isChangingConfigurations()
     */
    public boolean isChangingConfigurations() {
        return mActivity.isChangingConfigurations();
    }

    /**
     * 
     * @see android.app.Activity#recreate()
     */
    public void recreate() {
        mActivity.recreate();
    }

    /**
     * 
     * @see android.app.Activity#finish()
     */
    public void finish() {
        mActivity.finish();
    }

    /**
     * 
     * @see android.app.Activity#finishAffinity()
     */
    public void finishAffinity() {
        mActivity.finishAffinity();
    }

    /**
     * @param child
     * @see android.app.Activity#finishFromChild(android.app.Activity)
     */
    public void finishFromChild(Activity child) {
        mActivity.finishFromChild(child);
    }

    /**
     * @param requestCode
     * @see android.app.Activity#finishActivity(int)
     */
    public void finishActivity(int requestCode) {
        mActivity.finishActivity(requestCode);
    }

    /**
     * @param child
     * @param requestCode
     * @see android.app.Activity#finishActivityFromChild(android.app.Activity,
     *      int)
     */
    public void finishActivityFromChild(Activity child,
                                        int requestCode) {
        mActivity.finishActivityFromChild(child, requestCode);
    }

    /**
     * @param requestCode
     * @param data
     * @param flags
     * @return
     * @see android.app.Activity#createPendingResult(int,
     *      android.content.Intent, int)
     */
    public PendingIntent createPendingResult(int requestCode,
                                             Intent data,
                                             int flags) {
        return mActivity.createPendingResult(requestCode, data, flags);
    }

    /**
     * @param requestedOrientation
     * @see android.app.Activity#setRequestedOrientation(int)
     */
    public void setRequestedOrientation(int requestedOrientation) {
        mActivity.setRequestedOrientation(requestedOrientation);
    }

    /**
     * @return
     * @see android.app.Activity#getRequestedOrientation()
     */
    public int getRequestedOrientation() {
        return mActivity.getRequestedOrientation();
    }

    /**
     * @return
     * @see android.app.Activity#getTaskId()
     */
    public int getTaskId() {
        return mActivity.getTaskId();
    }

    /**
     * @return
     * @see android.app.Activity#isTaskRoot()
     */
    public boolean isTaskRoot() {
        return mActivity.isTaskRoot();
    }

    /**
     * @param nonRoot
     * @return
     * @see android.app.Activity#moveTaskToBack(boolean)
     */
    public boolean moveTaskToBack(boolean nonRoot) {
        return mActivity.moveTaskToBack(nonRoot);
    }

    /**
     * @return
     * @see android.app.Activity#getLocalClassName()
     */
    public String getLocalClassName() {
        return mActivity.getLocalClassName();
    }

    /**
     * @return
     * @see android.app.Activity#getComponentName()
     */
    public ComponentName getComponentName() {
        return mActivity.getComponentName();
    }

    /**
     * @param mode
     * @return
     * @see android.app.Activity#getPreferences(int)
     */
    public SharedPreferences getPreferences(int mode) {
        return mActivity.getPreferences(mode);
    }

    /**
     * @param name
     * @return
     * @see android.app.Activity#getSystemService(java.lang.String)
     */
    public Object getSystemService(String name) {
        return mActivity.getSystemService(name);
    }

    /**
     * @param title
     * @see android.app.Activity#setTitle(java.lang.CharSequence)
     */
    public void setTitle(CharSequence title) {
        mActivity.setTitle(title);
    }

    /**
     * @param titleId
     * @see android.app.Activity#setTitle(int)
     */
    public void setTitle(int titleId) {
        mActivity.setTitle(titleId);
    }

    /**
     * @param textColor
     * @see android.app.Activity#setTitleColor(int)
     */
    public void setTitleColor(int textColor) {
        mActivity.setTitleColor(textColor);
    }

    /**
     * @return
     * @see android.app.Activity#getTitle()
     */
    public final CharSequence getTitle() {
        return mActivity.getTitle();
    }

    /**
     * @return
     * @see android.app.Activity#getTitleColor()
     */
    public final int getTitleColor() {
        return mActivity.getTitleColor();
    }

    /**
     * @param visible
     * @see android.app.Activity#setProgressBarVisibility(boolean)
     */
    public final void setProgressBarVisibility(boolean visible) {
        mActivity.setProgressBarVisibility(visible);
    }

    /**
     * @param visible
     * @see android.app.Activity#setProgressBarIndeterminateVisibility(boolean)
     */
    public final void setProgressBarIndeterminateVisibility(boolean visible) {
        mActivity.setProgressBarIndeterminateVisibility(visible);
    }

    /**
     * @param indeterminate
     * @see android.app.Activity#setProgressBarIndeterminate(boolean)
     */
    public final void setProgressBarIndeterminate(boolean indeterminate) {
        mActivity.setProgressBarIndeterminate(indeterminate);
    }

    /**
     * @param progress
     * @see android.app.Activity#setProgress(int)
     */
    public final void setProgress(int progress) {
        mActivity.setProgress(progress);
    }

    /**
     * @param secondaryProgress
     * @see android.app.Activity#setSecondaryProgress(int)
     */
    public final void setSecondaryProgress(int secondaryProgress) {
        mActivity.setSecondaryProgress(secondaryProgress);
    }

    /**
     * @param streamType
     * @see android.app.Activity#setVolumeControlStream(int)
     */
    public final void setVolumeControlStream(int streamType) {
        mActivity.setVolumeControlStream(streamType);
    }

    /**
     * @return
     * @see android.app.Activity#getVolumeControlStream()
     */
    public final int getVolumeControlStream() {
        return mActivity.getVolumeControlStream();
    }

    /**
     * @param action
     * @see android.app.Activity#runOnUiThread(java.lang.Runnable)
     */
    public final void runOnUiThread(Runnable action) {
        mActivity.runOnUiThread(action);
    }

    /**
     * @param name
     * @param context
     * @param attrs
     * @return
     * @see android.app.Activity#onCreateView(java.lang.String,
     *      android.content.Context, android.util.AttributeSet)
     */
    public View onCreateView(String name,
                             Context context,
                             AttributeSet attrs) {
        return mActivity.onCreateView(name, context, attrs);
    }

    /**
     * @param parent
     * @param name
     * @param context
     * @param attrs
     * @return
     * @see android.app.Activity#onCreateView(android.view.View,
     *      java.lang.String, android.content.Context,
     *      android.util.AttributeSet)
     */
    public View onCreateView(View parent,
                             String name,
                             Context context,
                             AttributeSet attrs) {
        return mActivity.onCreateView(parent, name, context, attrs);
    }

    /**
     * @param prefix
     * @param fd
     * @param writer
     * @param args
     * @see android.app.Activity#dump(java.lang.String, java.io.FileDescriptor,
     *      java.io.PrintWriter, java.lang.String[])
     */
    public void dump(String prefix,
                     FileDescriptor fd,
                     PrintWriter writer,
                     String[] args) {
        mActivity.dump(prefix, fd, writer, args);
    }

    /**
     * @param callback
     * @return
     * @see android.app.Activity#startActionMode(android.view.ActionMode.Callback)
     */
    public ActionMode startActionMode(Callback callback) {
        return mActivity.startActionMode(callback);
    }

    /**
     * @param callback
     * @return
     * @see android.app.Activity#onWindowStartingActionMode(android.view.ActionMode.Callback)
     */
    public ActionMode onWindowStartingActionMode(Callback callback) {
        return mActivity.onWindowStartingActionMode(callback);
    }

    /**
     * @param mode
     * @see android.app.Activity#onActionModeStarted(android.view.ActionMode)
     */
    public void onActionModeStarted(ActionMode mode) {
        mActivity.onActionModeStarted(mode);
    }

    /**
     * @param mode
     * @see android.app.Activity#onActionModeFinished(android.view.ActionMode)
     */
    public void onActionModeFinished(ActionMode mode) {
        mActivity.onActionModeFinished(mode);
    }

    /**
     * @param targetIntent
     * @return
     * @see android.app.Activity#shouldUpRecreateTask(android.content.Intent)
     */
    public boolean shouldUpRecreateTask(Intent targetIntent) {
        return mActivity.shouldUpRecreateTask(targetIntent);
    }

    /**
     * @param upIntent
     * @return
     * @see android.app.Activity#navigateUpTo(android.content.Intent)
     */
    public boolean navigateUpTo(Intent upIntent) {
        return mActivity.navigateUpTo(upIntent);
    }

    /**
     * @param child
     * @param upIntent
     * @return
     * @see android.app.Activity#navigateUpToFromChild(android.app.Activity,
     *      android.content.Intent)
     */
    public boolean navigateUpToFromChild(Activity child,
                                         Intent upIntent) {
        return mActivity.navigateUpToFromChild(child, upIntent);
    }

    /**
     * @return
     * @see android.app.Activity#getParentActivityIntent()
     */
    public Intent getParentActivityIntent() {
        return mActivity.getParentActivityIntent();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mProxy.onCreate(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mProxy.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        mProxy.onPostCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        mProxy.onStart();
    }

    @Override
    public void onRestart() {
        mProxy.onRestart();
    }

    @Override
    public void onResume() {
        mProxy.onResume();
    }

    @Override
    public void onPostResume() {
        mProxy.onPostResume();
    }

    @Override
    public void onNewIntent(Intent intent) {
        mProxy.onNewIntent(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mProxy.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        mProxy.onPause();
    }

    @Override
    public void onUserLeaveHint() {
        mProxy.onUserLeaveHint();
    }

    @Override
    public void onStop() {
        mProxy.onStop();
    }

    @Override
    public void onDestroy() {
        mProxy.onDestroy();
    }

    @Override
    @Deprecated
    public Dialog onCreateDialog(int id) {
        return mProxy.onCreateDialog(id);
    }

    @Override
    @Deprecated
    public Dialog onCreateDialog(int id,
                                 Bundle args) {
        return mProxy.onCreateDialog(id, args);
    }

    @Override
    @Deprecated
    public void onPrepareDialog(int id,
                                Dialog dialog) {
        mProxy.onPrepareDialog(id, dialog);
    }

    @Override
    @Deprecated
    public void onPrepareDialog(int id,
                                Dialog dialog,
                                Bundle args) {
        mProxy.onPrepareDialog(id, dialog, args);
    }

    @Override
    public void onApplyThemeResource(Theme theme,
                                     int resid,
                                     boolean first) {
        mProxy.onApplyThemeResource(theme, resid, first);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {
        mProxy.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onTitleChanged(CharSequence title,
                               int color) {
        mProxy.onTitleChanged(title, color);
    }

    @Override
    public void onChildTitleChanged(Activity childActivity,
                                    CharSequence title) {
        mProxy.onChildTitleChanged(childActivity, title);
    }

    @Override
    public void performCreate(Bundle savedInstanceState) {
        onCreate(savedInstanceState);
    }

    @Override
    public void performDestroy() {
        onDestroy();
    }

    @Override
    public void performRestoreInstanceState(Bundle savedInstanceState) {
        onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void setMResumed(boolean b) {
    }

    @Override
    public void performSaveInstanceState(Bundle outState) {
        onSaveInstanceState(outState);
    }

    @Override
    public void performPause() {
        onPause();
    }

    @Override
    public void performUserLeaving() {
        onUserInteraction();
        onUserLeaveHint();
    }

    @Override
    public void attach(Context context,
                       @DeclaredIn("android.app.ActivityThread") Object aThread,
                       Instrumentation instr,
                       IBinder token,
                       Application application,
                       Intent intent,
                       ActivityInfo info,
                       CharSequence title,
                       Activity parent,
                       String id,
                       @DeclaredIn("android.app.Activity$NonConfigurationInstances") Object lastNonConfigurationInstances,
                       Configuration config) {
        mProxy.attach(context, aThread, instr, token, application, intent, info, title, parent, id, lastNonConfigurationInstances, config);
    }
}
