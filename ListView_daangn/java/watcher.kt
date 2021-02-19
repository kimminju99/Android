package com.example.listview_daangn

import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat


class watcher(var wonEt: EditText):TextWatcher {

    private val df: DecimalFormat = DecimalFormat("###,###")
    private var result:String=""


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }


    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // StackOverflow를 막기위해
        if (!s.toString().equals(result)) {
            // 에딧텍스트의 값을 변환하여, result에 저장.
            result = df.format((s.toString().replace(",", "")).toLong());
            wonEt.setText(result);    // 결과 텍스트 셋팅.
            wonEt.setSelection(result.length);     // 커서를 제일 끝으로 보냄.
        }
    }

    override fun afterTextChanged(s: Editable?) {

    }
}