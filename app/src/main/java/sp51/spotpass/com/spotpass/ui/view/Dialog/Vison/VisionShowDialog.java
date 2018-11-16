package sp51.spotpass.com.spotpass.ui.view.Dialog.Vison;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import sp51.spotpass.com.spotpass.MainActivity;
import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.activity.WebViewActivity;

/**
 * @Time : 2018/6/17 no 16:22
 * @USER : vvguoliang
 * @File : VisionShowDialog.java
 * @Software: Android Studio
 * code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 * ***┏┓   ┏ ┓
 * **┏┛┻━━━┛ ┻┓
 * **┃        ┃
 * **┃ ┳┛  ┗┳ ┃
 * **┃    ┻   ┃
 * **┗━┓    ┏━┛
 * ****┃    ┗━━━┓
 * ****┃ 神兽保佑 ┣┓
 * ****┃ 永无BUG！┏┛
 * ****┗┓┓┏━┳┓┏┛┏┛
 * ******┃┫┫  ┃┫┫
 * ******┗┻┛  ┗┻┛
 */
public class VisionShowDialog {

    private Context context;

    private CommonProgressDialog pBar;

    // 下载存储的文件名
    private static final String DOWNLOAD_NAME = "51投资";

    public VisionShowDialog(Context context) {
        this.context = context;
    }

    /**
     * 升级系统
     *
     * @param content
     * @param url
     */
    public void ShowDialog(String newversion, String content, final String url, int type) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);

//        if (type == 1) {
//            new android.app.AlertDialog.Builder(context)
//                    .setTitle("版本更新v" + newversion)
//                    .setMessage(content)
//                    .setPositiveButton("更新", (dialog, which) -> {
//                        dialog.dismiss();
//                        pBar = new CommonProgressDialog(context);
//                        pBar.setCanceledOnTouchOutside(false);
//                        pBar.setTitle("正在下载");
//                        pBar.setCustomTitle(LayoutInflater.from(context).inflate(
//                                R.layout.title_dialog, null));
//                        pBar.setMessage("正在下载");
//                        pBar.setIndeterminate(true);
//                        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                        pBar.setCancelable(true);
//                        // downFile(URLData.DOWNLOAD_URL);
//                        final DownloadTask downloadTask = new DownloadTask(context);
//                        downloadTask.execute(url);
//                        pBar.setOnCancelListener(dialog1 -> downloadTask.cancel(true));
//                    })
//                    .setCancelable(false)
//                    .setOnKeyListener((dialog, keyCode, event) -> keyCode == KeyEvent.KEYCODE_SEARCH)
//                    .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
//                    .show();
//        } else {
//            new android.app.AlertDialog.Builder(context)
//                    .setTitle("版本更新v" + newversion)
//                    .setMessage(content)
//                    .setPositiveButton("更新", (dialog, which) -> {
//                        dialog.dismiss();
//                        pBar = new CommonProgressDialog(context);
//                        pBar.setCanceledOnTouchOutside(false);
//                        pBar.setTitle("正在下载");
//                        pBar.setCustomTitle(LayoutInflater.from(context).inflate(
//                                R.layout.title_dialog, null));
//                        pBar.setMessage("正在下载");
//                        pBar.setIndeterminate(true);
//                        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                        pBar.setCancelable(true);
//                        // downFile(URLData.DOWNLOAD_URL);
//                        final DownloadTask downloadTask = new DownloadTask(context);
//                        downloadTask.execute(url);
//                        pBar.setOnCancelListener(dialog1 -> downloadTask.cancel(true));
//                    })
//                    .setCancelable(false)
//                    .setOnKeyListener((dialog, keyCode, event) -> keyCode == KeyEvent.KEYCODE_SEARCH)
//                    .show();
//        }
    }


    /**
     * 下载应用
     *
     * @author Administrator
     */
    @SuppressLint("StaticFieldLeak")
    class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            File file = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                // expect HTTP 200 OK, so we don't mistakenly save error
                // report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP "
                            + connection.getResponseCode() + " "
                            + connection.getResponseMessage();
                }
                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    file = new File(Environment.getExternalStorageDirectory(),
                            DOWNLOAD_NAME);

                    if (!file.exists()) {
                        // 判断父文件夹是否存在
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                    }

                } else {
                    Toast.makeText(context, "sd卡未挂载", Toast.LENGTH_LONG).show();
                }
                input = connection.getInputStream();
                output = new FileOutputStream(file);
                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);

                }
            } catch (Exception e) {
                System.out.println(e.toString());
                return e.toString();

            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }
                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context
                    .getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
            mWakeLock.acquire();
            pBar.show();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            pBar.setIndeterminate(false);
            pBar.setMax(100);
            pBar.setProgress(progress[0]);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            pBar.dismiss();
            if (result != null) {

//                // 申请多个权限。大神的界面
                AndPermission.with((Activity) context)
                        .requestCode(REQUEST_CODE_PERMISSION_SD)
                        .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                        .rationale((requestCode, rationale) -> {
                                    // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                                    AndPermission.rationaleDialog(context, rationale).show();
                                }
                        )
                        .send();
            } else {
                update();
            }
        }
    }

    public static final int REQUEST_CODE_PERMISSION_SD = 101;

    public static final int REQUEST_CODE_SETTING = 300;
    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            // 这里使用自定义对话框，如果不想自定义，用AndPermission默认对话框：
            // AndPermission.rationaleDialog(Context, Rationale).show();

            // 自定义对话框。
            AlertDialog.build(context)
                    .setTitle(R.string.title_dialog)
                    .setMessage(R.string.message_permission_rationale)
                    .setPositiveButton(R.string.btn_dialog_yes_permission, (dialog, which) -> {
                        dialog.cancel();
                        rationale.resume();
                    })

                    .setNegativeButton(R.string.btn_dialog_no_permission, (dialog, which) -> {
                        dialog.cancel();
                        rationale.cancel();
                    })
                    .show();
        }
    };
    //----------------------------------SD权限----------------------------------//


    @PermissionYes(REQUEST_CODE_PERMISSION_SD)
    private void getMultiYes(List<String> grantedPermissions) {
        Toast.makeText(context, R.string.message_post_succeed, Toast.LENGTH_SHORT).show();
    }

    @PermissionNo(REQUEST_CODE_PERMISSION_SD)
    private void getMultiNo(List<String> deniedPermissions) {
        Toast.makeText(context, R.string.message_post_failed, Toast.LENGTH_SHORT).show();

        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission((Activity) context, deniedPermissions)) {
            AndPermission.defaultSettingDialog((Activity) context, REQUEST_CODE_SETTING)
                    .setTitle(R.string.title_dialog)
                    .setMessage(R.string.message_permission_failed)
                    .setPositiveButton(R.string.btn_dialog_yes_permission)
                    .setNegativeButton(R.string.btn_dialog_no_permission, null)
                    .show();

            // 更多自定dialog，请看上面。
        }
    }

    private void update() {
        //安装应用
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(new File(Environment
//                        .getExternalStorageDirectory(), DOWNLOAD_NAME)),
//                "application/vnd.android.package-archive");
//        context.startActivity(intent);

        File apkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "com.spotpass.apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", apkFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

}
