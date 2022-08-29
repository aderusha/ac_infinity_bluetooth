package com.eternal.framework.binding.viewadapter.edittext;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.TransformationMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.eternal.framework.binding.command.BindingCommand;

public class ViewAdapter {
    public static void requestFocusCommand(EditText editText, Boolean bool) {
        if (bool.booleanValue()) {
            editText.setSelection(editText.getText().length());
            editText.requestFocus();
            ((InputMethodManager) editText.getContext().getSystemService("input_method")).showSoftInput(editText, 1);
        }
        editText.setFocusableInTouchMode(bool.booleanValue());
    }

    public static void setError(EditText editText, String str) {
        editText.setError(str);
    }

    public static void addTextChangedListener(EditText editText, final BindingCommand<String> bindingCommand) {
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                BindingCommand bindingCommand = BindingCommand.this;
                if (bindingCommand != null) {
                    bindingCommand.execute(charSequence.toString());
                }
            }
        });
    }

    public static void setTransform(EditText editText, TransformationMethod transformationMethod) {
        editText.setTransformationMethod(transformationMethod);
    }
}
