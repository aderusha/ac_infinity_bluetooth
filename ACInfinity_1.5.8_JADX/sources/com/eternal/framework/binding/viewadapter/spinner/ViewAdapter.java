package com.eternal.framework.binding.viewadapter.spinner;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.eternal.framework.binding.command.BindingCommand;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewAdapter {
    public static void onItemSelectedCommand(Spinner spinner, final List<IKeyAndValue> list, String str, int i, int i2, final BindingCommand<IKeyAndValue> bindingCommand) {
        Objects.requireNonNull(list, "this itemDatas parameter is null");
        ArrayList arrayList = new ArrayList();
        for (IKeyAndValue key : list) {
            arrayList.add(key.getKey());
        }
        if (i == 0) {
            i = 17367048;
        }
        if (i2 == 0) {
            i2 = 17367049;
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(spinner.getContext(), i, arrayList);
        arrayAdapter.setDropDownViewResource(i2);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                bindingCommand.execute((IKeyAndValue) list.get(i));
            }
        });
        if (!TextUtils.isEmpty(str)) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                if (str.equals(list.get(i3).getValue())) {
                    spinner.setSelection(i3);
                    return;
                }
            }
        }
    }
}
