//package android.airpolind.airpollutionindex.utils;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.NetworkInterface;
//import java.net.SocketException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.Locale;
//import java.util.regex.Pattern;
//
//import android.accounts.Account;
//import android.accounts.AccountManager;
//import android.annotation.SuppressLint;
//import android.app.ActivityManager;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageManager;
//import android.content.res.Configuration;
//import android.database.Cursor;
//import android.net.Uri;
//import android.net.wifi.WifiInfo;
//import android.net.wifi.WifiManager;
//import android.os.Build;
//import android.provider.Browser;
//import android.provider.Settings.Secure;
//import android.telephony.TelephonyManager;
//import android.text.format.Formatter;
//import android.util.Patterns;
//
//
//
//public class Information {
//
//    private Context mContext;
//
//    public Information(Context context) {
//        mContext = context;
//    }
//
//    @SuppressLint("NewApi")
//    public List<App> getExternalApps() {
//        List<App> appList = new ArrayList<App>();
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences("PREFS_PRIVATE", Context.MODE_PRIVATE);
//        boolean first = sharedPreferences.getBoolean("firstTime", false);
//        PackageManager packageManager = mContext.getPackageManager();
//        List<ApplicationInfo> list = mContext.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
//        for (ApplicationInfo info : list) {
//            try {
//                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
//                    App app = new App();
//                    long lastTime = Utils.getDateFilter(mContext);
//                    long installedTime = mContext.getPackageManager().getPackageInfo(info.packageName, 0).firstInstallTime;
//                    if (first == false && lastTime > 0 && installedTime > lastTime) {
//                        app.setName(info.loadLabel(packageManager).toString());
//                        appList.add(app);
//                    } else if (first == true) {
//                        app.setName(info.loadLabel(packageManager).toString());
//                        appList.add(app);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return appList;
//    }
//
//    public String getForegroundApp() {
//        return ((ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1).get(0).topActivity.getPackageName();
//    }
//
//    public List<WebSite> GetUrlHistory() {
//        List<WebSite> webSiteList = new ArrayList<WebSite>();
//        String[] projection = new String[]{Browser.BookmarkColumns.TITLE, Browser.BookmarkColumns.URL, Browser.BookmarkColumns.DATE,
//                Browser.BookmarkColumns.VISITS};
//
//        Cursor cursor = mContext.getContentResolver().query(Browser.BOOKMARKS_URI, projection, Browser.BookmarkColumns.DATE + ">?",
//                new String[]{"" + Utils.getDateFilter(mContext)}, "date DESC");
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//
//            do {
//                WebSite webSite = new WebSite();
//                webSite.lastVisit = Utils.getDate(new Date(cursor.getLong(cursor.getColumnIndex(Browser.BookmarkColumns.DATE))));
//                webSite.count = cursor.getString(cursor.getColumnIndex(Browser.BookmarkColumns.VISITS));
//                webSite.url = cursor.getString(cursor.getColumnIndex(Browser.BookmarkColumns.URL));
//                webSite.title = cursor.getString(cursor.getColumnIndex(Browser.BookmarkColumns.TITLE));
//                webSiteList.add(webSite);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return chromeUrlHistory(webSiteList);
//    }
//
//    public List<WebSite> chromeUrlHistory(List<WebSite> webSiteList) {
//        Uri CHROME_BOOKMARKS_URI = Uri.parse("content://com.android.chrome.browser/bookmarks");
//
//        String[] projection = new String[]{Browser.BookmarkColumns.TITLE, Browser.BookmarkColumns.URL, Browser.BookmarkColumns.DATE,
//                Browser.BookmarkColumns.VISITS};
//
//        Cursor cursor = mContext.getContentResolver().query(CHROME_BOOKMARKS_URI, projection, Browser.BookmarkColumns.DATE + ">?",
//                new String[]{"" + Utils.getDateFilter(mContext)}, "date DESC");
//
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//
//            do {
//                WebSite webSite = new WebSite();
//                webSite.lastVisit = Utils.getDate(new Date(cursor.getLong(cursor.getColumnIndex(Browser.BookmarkColumns.DATE))));
//                webSite.count = cursor.getString(cursor.getColumnIndex(Browser.BookmarkColumns.VISITS));
//                webSite.url = cursor.getString(cursor.getColumnIndex(Browser.BookmarkColumns.URL));
//                webSite.title = cursor.getString(cursor.getColumnIndex(Browser.BookmarkColumns.TITLE));
//                webSiteList.add(webSite);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return webSiteList;
//    }
//
//    public String getDeviceId() {
//        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        return telephonyManager.getDeviceId();// This IMEI number
//    }
//
//    public String getCountryCode() {
//        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        final String simCountry = telephonyManager.getSimCountryIso();
//        return simCountry.toLowerCase(Locale.US);
//    }
//
//    public String getDeviceLanguage() {
//        return Locale.getDefault().getLanguage();
//    }
//
//    public String getLocalIpAddress() {
//        try {
//            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
//                NetworkInterface intf = en.nextElement();
//                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
//                    InetAddress inetAddress = enumIpAddr.nextElement();
//                    if (!inetAddress.isLoopbackAddress()) {
//                        return Formatter.formatIpAddress(inetAddress.hashCode());
//                    }
//                }
//            }
//        } catch (SocketException ex) {
//
//        }
//        return Constants.IP_ADRRESS;
//    }
//
//    public String getMacAddress() {
//        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
//        if (wifiManager.isWifiEnabled()) {
//            WifiInfo wInfo = wifiManager.getConnectionInfo();
//            return wInfo.getMacAddress();
//        }
//        return Constants.WIFI_DISABLED;
//    }
//
//    public String getDeviceName() {
//        return Build.PRODUCT;
//    }
//
//    public String getDeviceModel() {
//        return Build.MODEL;
//    }
//
//    public String getDeviceBrandName() {
//        return Build.MANUFACTURER;
//    }
//
//    public String getDeviceVersion() {
//        return Build.VERSION.RELEASE;
//    }
//
//    public String getCarrier() {
//        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        return telephonyManager.getNetworkOperatorName();
//
//    }
//
//    public String getAndroidId() {
//        return Secure.ANDROID_ID;
//    }
//
//    public String getSimNumber() {
//        // this is SIM number so should use this in place of IMSI number.
//        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        return telephonyManager.getSimSerialNumber();
//    }
//
//    public String getSubscriberId() {
//        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        return telephonyManager.getSubscriberId();
//        // IMSI number store in SIM (sometimes could be 6 digits instead of 14-15)
//    }
//
//    public String getGAId() {
//        try {
//            Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(mContext);
//            return adInfo.getId();
//        } catch (IllegalStateException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (GooglePlayServicesRepairableException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (GooglePlayServicesNotAvailableException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return Constants.NO_GAID_AVALIABLE;
//    }
//
//    public String getDeivceType() {
//        if ((mContext.getResources().getConfiguration().screenLayout
//                & Configuration.SCREENLAYOUT_SIZE_MASK)
//                >= Configuration.SCREENLAYOUT_SIZE_LARGE) {
//            return Constants.TABLET;
//        } else {
//            return Constants.MOBILE;
//        }
//    }
//
//
//    public String getOS() {
//        return Constants.OS;
//    }
//
//    public String getPrimaryEmailId() {
//        String possibleEmail = new String();
//        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
//        Account[] accounts = AccountManager.get(mContext).getAccounts();
//        for (Account account : accounts) {
//            if (emailPattern.matcher(account.name).matches()) {
//                possibleEmail = account.name;
//            }
//        }
//        return possibleEmail;
//    }
//
//}
