package sp51.spotpass.com.spotpass.ui.view.Dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import sp51.spotpass.com.spotpass.R;

/**
 * @Time : 2018/5/18 no 14:27
 * @USER : vvguoliang
 * @File : DialogBuilder.java
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
public class NickNameDialogBuilder {

    private Context context;
    private int themeResId;
    private View layout;
    private boolean cancelable = true;
    private boolean editext_text = true;
    private CharSequence title, message, cancelText, sureText;//除了message的所有文本，不写则Gone。
    private View.OnClickListener sureClickListener, cancelClickListener;
    private TextWatcher textWatcher;

    public NickNameDialogBuilder(Context context) {
        this(context, R.style.CustomDialog);
    }

    @SuppressLint("InflateParams")
    public NickNameDialogBuilder(Context context, int themeResId) {
        this(context, themeResId, ((LayoutInflater) Objects.requireNonNull(context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE))).inflate(R.layout.custom_nikename_dialog_layout, null));
    }

    //自定义layout用这个
    public NickNameDialogBuilder(Context context, int themeResId, View layout) {
        this.context = context;
        this.themeResId = themeResId;
        this.layout = layout;
    }

    //能否返回键取消
    public NickNameDialogBuilder setCancelable(Boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public NickNameDialogBuilder title(CharSequence title) {
        this.title = title;
        return this;
    }

    public NickNameDialogBuilder message(CharSequence message) {
        this.message = message;
        return this;
    }

    public NickNameDialogBuilder messageeditext_text(boolean editext_text) {
        this.editext_text = editext_text;
        return this;
    }

    //取消
    public NickNameDialogBuilder cancelText(CharSequence str) {
        this.cancelText = str;
        return this;
    }

    //确定按钮文本 确定
    public NickNameDialogBuilder sureText(CharSequence str) {
        this.sureText = str;
        return this;
    }

    public NickNameDialogBuilder setSureOnClickListener(View.OnClickListener listener) {
        this.sureClickListener = listener;
        return this;
    }

    public NickNameDialogBuilder setCancelOnClickListener(View.OnClickListener listener) {
        this.cancelClickListener = listener;
        return this;
    }

    public NickNameDialogBuilder setTextWatcherr(TextWatcher textWatcher) {
        this.textWatcher = textWatcher;
        return this;
    }

    public Dialog build() {
        final Dialog dialog = new Dialog(context, themeResId);
        dialog.setCancelable(cancelable);
        dialog.addContentView(layout, new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        //设置显不显示
        setText(title, R.id.title);
        setText(cancelText, R.id.cancel);
        setText(sureText, R.id.confirm);
        if (editext_text) {
            //一行居中
            layout.findViewById(R.id.title).setVisibility(View.GONE);
            layout.findViewById(R.id.title_ext).setVisibility(View.GONE);
            final EditText textView = layout.findViewById(R.id.message);
            textView.setVisibility(View.VISIBLE);
            textView.getViewTreeObserver().addOnPreDrawListener(() -> {
                if (textView.getLineCount() == 1) {
                    textView.setGravity(Gravity.CENTER);
                }
                return true;
            });
            if (textWatcher != null) {
                textView.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        textWatcher.beforeTextChanged(s, start, count, after);
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        textWatcher.onTextChanged(s, start, before, count);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        textWatcher.afterTextChanged(s);
                    }
                });
            }
        } else {
            layout.findViewById(R.id.message).setVisibility(View.GONE);
            layout.findViewById(R.id.title).setVisibility(View.VISIBLE);
            //一行居中
            final TextView textView = layout.findViewById(R.id.title_ext);
            textView.setText(message);
            textView.setVisibility(View.GONE);
            textView.getViewTreeObserver().addOnPreDrawListener(() -> {
                if (textView.getLineCount() == 1) {
                    textView.setGravity(Gravity.CENTER);
                }
                return true;
            });
        }
        layout.findViewById(R.id.cancel_image).setOnClickListener(v -> dialog.dismiss());
        //设置点击监听  确定
        if (sureClickListener != null) {
            layout.findViewById(R.id.confirm).setOnClickListener(view -> {
                sureClickListener.onClick(view);
                dialog.dismiss();
            });
        }
        //设置点击监听  取消
        if (cancelClickListener != null) {
            layout.findViewById(R.id.cancel).setOnClickListener(view -> {
                dialog.dismiss();
            });
        }

        dialog.setCancelable(false); //触摸屏幕与点Back键都失效。也无法监听onCancel
        dialog.setCanceledOnTouchOutside(false);// 触摸屏幕Dialog不消失，但点击Back键可取消
        //设置宽度
        WindowManager.LayoutParams params = Objects.requireNonNull(dialog.getWindow()).getAttributes();
        params.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.80);
        dialog.getWindow().setAttributes(params);
        return dialog;
    }

    private void setText(CharSequence text, int id) {
        if (isValid(text)) {
            TextView textView = layout.findViewById(id);
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
        }
    }

    private boolean isValid(CharSequence text) {
        return text != null && !"".equals(text.toString().trim());
    }
}
