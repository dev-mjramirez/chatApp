package mj.ramirez.chatapp.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import androidx.core.content.ContextCompat;

import java.util.Objects;

import mj.ramirez.chatapp.R;

public class DialogUtils {

    private Dialog dialog;

    public Dialog showProgress(Context context) {
        dialog = new Dialog(context,android.R.style.Theme_Translucent_NoTitleBar);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context,R.color.transparent)));
        dialog.setContentView(R.layout.dialog_progress); // fill layout
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        return dialog;
    }
}
