package com.even.recyclerviewdemo.beans;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.even.commonrv.adapter.BaseViewHolder;
import com.even.commonrv.bean.BaseListPagerBean;
import com.even.recyclerviewdemo.R;

/**
 * @author Even on 2019/3/21
 * Emial: emailtopan@163.com
 * 可输入Item
 */
public class InputBean extends BaseListPagerBean {
    private String inputText;

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    @Override
    public void cover(BaseViewHolder holder, int position) {
        EditText editText = holder.getView(R.id.edWeight);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setInputText(s.toString());
            }
        });

    }

    @Override
    public int getContentViewId() {
        return R.layout.item_input;
    }
}
