/**
 * Copyright 2013 Maarten Pennings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * If you use this software in a product, an acknowledgment in the product
 * documentation would be appreciated but is not required.
 */

package com.brainuptech.amharicdictionary;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SearchView;


public class CustomKeyboard2 {

    private KeyboardView mKeyboardView;
    private Activity     mHostActivity;
    public static String[] AMHARIC_WORDS= {"ሀ","ለ","ሐ","መ","ሠ","ረ",
            "ሰ","ሸ","ቀ","በ","ቨ","ተ","ቸ","ኀ","ነ","ኘ","አ","ከ","ኸ","ወ","ዐ","ዘ","ዠ","የ","ደ","ጀ","ገ","ጠ","ጨ","ጰ","ጸ","ፀ","ፈ","ፐ",":"};

    private OnKeyboardActionListener mOnKeyboardActionListener = new OnKeyboardActionListener() {

        public final static int CodeDelete   = -5; // Keyboard.KEYCODE_DELETE
        public final static int CodeCancel   = -3; // Keyboard.KEYCODE_CANCEL
        public final static int CodePrev     = 55000;
        public final static int CodeAllLeft  = 55001;
        public final static int CodeLeft     = 55002;
        public final static int CodeRight    = 55003;
        public final static int CodeAllRight = 55004;
        public final static int CodeNext     = 55005;
        public final static int CodeClear    = 55006;

        @Override public void onKey(int primaryCode, int[] keyCodes) {
            // NOTE We can say '<Key android:codes="49,50" ... >' in the xml file; all codes come in keyCodes, the first in this list in primaryCode
            // Get the EditText and its Editable
            View focusCurrent = mHostActivity.getWindow().getCurrentFocus();
            if( focusCurrent==null || focusCurrent.getClass()!=EditText.class ) return;
            SearchView edittext = (SearchView) focusCurrent;
            Editable editable = (Editable) edittext.getQuery();
            int start = edittext.getQuery().length();

            // Apply the key to the edittext
            if( primaryCode==CodeCancel ) {
                hideCustomKeyboard();
            } else if( primaryCode==CodeDelete ) {
                if( editable!=null && start>0 ) editable.delete(start - 1, start);
            } else if( primaryCode==CodeClear ) {
                if( editable!=null ) editable.clear();
            } else if( primaryCode==CodeLeft ) {
//                if( start>0 ) edittext.setSelection(start - 1);
            } else if( primaryCode==CodeRight ) {
//                if (start < edittext.length()) edittext.setSelection(start + 1);
            } else if( primaryCode==CodeAllLeft ) {
//                edittext.setSelection(0);
            } else if( primaryCode==CodeAllRight ) {
//                edittext.setSelection(edittext.length());
            }
            else if(primaryCode == 1000){
                editable.insert(start,AMHARIC_WORDS[0]);
            }
            else if(primaryCode == 1010){
                editable.insert(start,AMHARIC_WORDS[1]);
            }
            else if(primaryCode == 1020){
                editable.insert(start,AMHARIC_WORDS[2]);
            }
            else if(primaryCode == 1030){
                editable.insert(start,AMHARIC_WORDS[3]);
            }
            else if(primaryCode == 1040){
                editable.insert(start,AMHARIC_WORDS[4]);
            }
            else if(primaryCode == 1050){
                editable.insert(start,AMHARIC_WORDS[5]);
            }
            else if(primaryCode == 1060){
                editable.insert(start,AMHARIC_WORDS[6]);
            }
            else if(primaryCode == 1070){
                editable.insert(start,AMHARIC_WORDS[7]);
            }
            else if(primaryCode == 1080){
                editable.insert(start,AMHARIC_WORDS[8]);
            }
            else if(primaryCode == 1090){
                editable.insert(start,AMHARIC_WORDS[9]);
            }
            else if(primaryCode == 1100){
                editable.insert(start,AMHARIC_WORDS[10]);
            }
            else if(primaryCode == 1110){
                editable.insert(start,AMHARIC_WORDS[11]);
            }
            else if(primaryCode == 1120){
                editable.insert(start,AMHARIC_WORDS[12]);
            }
            else if(primaryCode == 1130){
                editable.insert(start,AMHARIC_WORDS[13]);
            }
            else if(primaryCode == 1140){
                editable.insert(start,AMHARIC_WORDS[14]);
            }
            else if(primaryCode == 1150){
                editable.insert(start,AMHARIC_WORDS[15]);
            }
            else if(primaryCode == 1160){
                editable.insert(start,AMHARIC_WORDS[16]);
            }
            else if(primaryCode == 1170){
                editable.insert(start,AMHARIC_WORDS[17]);
            }
            else if(primaryCode == 1180){
                editable.insert(start,AMHARIC_WORDS[18]);
            }
            else if(primaryCode == 1190){
                editable.insert(start,AMHARIC_WORDS[19]);
            }
            else if(primaryCode == 1200){
                editable.insert(start,AMHARIC_WORDS[20]);
            }
            else if(primaryCode == 1210){
                editable.insert(start,AMHARIC_WORDS[21]);
            }
            else if(primaryCode == 1220){
                editable.insert(start,AMHARIC_WORDS[22]);
            }
            else if(primaryCode == 1230){
                editable.insert(start,AMHARIC_WORDS[23]);
            }
            else if(primaryCode == 1240){
                editable.insert(start,AMHARIC_WORDS[24]);
            }
            else if(primaryCode == 1250){
                editable.insert(start,AMHARIC_WORDS[25]);
            }
            else if(primaryCode == 1260){
                editable.insert(start,AMHARIC_WORDS[26]);
            }
            else if(primaryCode == 1270){
                editable.insert(start,AMHARIC_WORDS[27]);
            }
            else if(primaryCode == 1280){
                editable.insert(start,AMHARIC_WORDS[28]);
            }
            else if(primaryCode == 1290){
                editable.insert(start,AMHARIC_WORDS[29]);
            }
            else if(primaryCode == 1300){
                editable.insert(start,AMHARIC_WORDS[30]);
            }
            else if(primaryCode == 1310){
                editable.insert(start,AMHARIC_WORDS[31]);
            }
            else if(primaryCode == 1320){
                editable.insert(start,AMHARIC_WORDS[32]);
            }
            else if(primaryCode == 1330){
                editable.insert(start,AMHARIC_WORDS[33]);
            }
            else if(primaryCode == 1400){
                editable.insert(start,AMHARIC_WORDS[34]);
            }
            else { // insert character
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }

        @Override public void onPress(int arg0) {
        }

        @Override public void onRelease(int primaryCode) {
        }

        @Override public void onText(CharSequence text) {
        }

        @Override public void swipeDown() {
        }

        @Override public void swipeLeft() {
        }

        @Override public void swipeRight() {
        }

        @Override public void swipeUp() {
        }
    };


    public CustomKeyboard2(Activity host, int viewid, int layoutid) {
        mHostActivity= host;
        mKeyboardView= (KeyboardView)mHostActivity.findViewById(viewid);
        mKeyboardView.setKeyboard(new Keyboard(mHostActivity, layoutid));
        mKeyboardView.setPreviewEnabled(false); // NOTE Do not show the preview balloons
        mKeyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);
        // Hide the standard keyboard initially
        mHostActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public boolean isCustomKeyboardVisible() {
        return mKeyboardView.getVisibility() == View.VISIBLE;
    }

    public void showCustomKeyboard( View v ) {
        mKeyboardView.setVisibility(View.VISIBLE);
        mKeyboardView.setEnabled(true);
        if( v!=null ) ((InputMethodManager)mHostActivity.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void hideCustomKeyboard() {
        mKeyboardView.setVisibility(View.GONE);
        mKeyboardView.setEnabled(false);
    }


    public void registerEditText(SearchView edittext) {
        // Make the custom keyboard appear
        edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            // NOTE By setting the on focus listener, we can show the custom keyboard when the edit box gets focus, but also hide it when the edit box loses focus
            @Override public void onFocusChange(View v, boolean hasFocus) {
                if( hasFocus ) showCustomKeyboard(v); else hideCustomKeyboard();
            }
        });
        edittext.setOnClickListener(new OnClickListener() {
            // NOTE By setting the on click listener, we can show the custom keyboard again, by tapping on an edit box that already had focus (but that had the keyboard hidden).
            @Override public void onClick(View v) {
                showCustomKeyboard(v);
            }
        });
        // Disable standard keyboard hard way
        // NOTE There is also an easy way: 'edittext.setInputType(InputType.TYPE_NULL)' (but you will not have a cursor, and no 'edittext.setCursorVisible(true)' doesn't work )
        edittext.setOnTouchListener(new OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {
                SearchView edittext = (SearchView) v;
                int inType = 0;       // Backup the input type
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    inType = edittext.getInputType();
                }
                edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
                edittext.onTouchEvent(event);               // Call native handler
                edittext.setInputType(inType);              // Restore input type
                return true; // Consume touch event
            }
        });
        // Disable spell check (hex strings look like words to Android)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            edittext.setInputType(edittext.getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        }
    }

}

