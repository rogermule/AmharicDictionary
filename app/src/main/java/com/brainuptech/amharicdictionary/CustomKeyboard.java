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
import android.text.Editable;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;


class CustomKeyboard {

    private KeyboardView mKeyboardView;
    private Activity     mHostActivity;
    public static String[] AMHARIC_WORDS= {"ሀ","ለ","ሐ","መ","ሠ","ረ",
            "ሰ","ሸ","ቀ","በ","ቨ","ተ","ቸ","ኀ","ነ","ኘ","አ","ከ","ኸ","ወ","ዐ","ዘ","ዠ","የ","ደ","ጀ","ገ","ጠ","ጨ","ጰ","ጸ","ፀ","ፈ","ፐ",":"};

    public static String[] AMHARIC_WORDS_0= {"ሀ","ሁ","ሂ","ሃ","ሄ","ህ","ሆ"};
    public static String[] AMHARIC_WORDS_1= {"ለ","ሉ","ሊ","ላ","ሌ","ል","ሎ"};
    public static String[] AMHARIC_WORDS_2= {"ሐ","ሑ","ሒ","ሓ","ሔ","ሕ","ሖ"};
    public static String[] AMHARIC_WORDS_3= {"መ","ሙ","ሚ","ማ","ሜ","ም","ሞ"};
    public static String[] AMHARIC_WORDS_4= {"ሠ","ሡ","ሢ","ሣ","ሤ","ሥ","ሦ"};
    public static String[] AMHARIC_WORDS_5= {"ረ","ሩ","ሪ","ራ","ሬ","ር","ሮ"};
    public static String[] AMHARIC_WORDS_6= {"ሰ","ሱ","ሲ","ሳ","ሴ","ስ","ሶ"};
    public static String[] AMHARIC_WORDS_7= {"ሸ","ሹ","ሺ","ሻ","ሼ","ሽ","ሾ"};
    public static String[] AMHARIC_WORDS_8= {"ቀ","ቁ","ቂ","ቃ","ቄ","ቅ","ቆ"};
    public static String[] AMHARIC_WORDS_9= {"በ","ቡ","ቢ","ባ","ቤ","ብ","ቦ"};
    public static String[] AMHARIC_WORDS_10= {"ቨ","ቩ","ቪ","ቫ","ቬ","ቭ","ቮ"};
    public static String[] AMHARIC_WORDS_11= {"ተ","ቱ","ቲ","ታ","ቴ","ት","ቶ"};
    public static String[] AMHARIC_WORDS_12= {"ቸ","ቹ","ቺ","ቻ","ቼ","ች","ቾ"};
    public static String[] AMHARIC_WORDS_13= {"ኀ","ኁ","ኂ","ኃ","ኄ","ኅ","ኆ"};
    public static String[] AMHARIC_WORDS_14= {"ነ","ኑ","ኒ","ና","ኔ","ን","ኖ"};
    public static String[] AMHARIC_WORDS_15= {"ኘ","ኙ","ኚ","ኛ","ኜ","ኝ","ኞ"};
    public static String[] AMHARIC_WORDS_16= {"አ","ኡ","ኢ","ኣ","ኤ","እ","ኦ"};
    public static String[] AMHARIC_WORDS_17= {"ከ","ኩ","ኪ","ካ","ኬ","ክ","ኮ"};
    public static String[] AMHARIC_WORDS_18= {"ኸ","ኹ","ኺ","ኻ","ኼ","ኽ","ኾ"};
    public static String[] AMHARIC_WORDS_19= {"ወ","ዉ","ዊ","ዋ","ዌ","ው","ዎ"};
    public static String[] AMHARIC_WORDS_20= {"ዐ","ዑ","ዒ","ዓ","ዔ","ዕ","ዖ"};
    public static String[] AMHARIC_WORDS_21= {"ዘ","ዙ","ዚ","ዛ","ዜ","ዝ","ዞ"};
    public static String[] AMHARIC_WORDS_22= {"ዠ","ዡ","ዢ","ዣ","ዤ","ዥ","ዦ"};
    public static String[] AMHARIC_WORDS_23= {"የ","ዩ","ዪ","ያ","ዬ","ይ","ዮ"};
    public static String[] AMHARIC_WORDS_24= {"ደ","ዱ","ዲ","ዳ","ዴ","ድ","ዶ"};
    public static String[] AMHARIC_WORDS_25= {"ጀ","ጁ","ጂ","ጃ","ጄ","ጅ","ጆ"};
    public static String[] AMHARIC_WORDS_26= {"ገ","ጉ","ጊ","ጋ","ጌ","ግ","ጎ"};
    public static String[] AMHARIC_WORDS_27= {"ጠ","ጡ","ጢ","ጣ","ጤ","ጥ","ጦ"};
    public static String[] AMHARIC_WORDS_28= {"ጨ","ጩ","ጪ","ጫ","ጬ","ጭ","ጮ"};
    public static String[] AMHARIC_WORDS_29= {"ጰ","ጱ","ጲ","ጳ","ጴ","ጵ","ጶ"};
    public static String[] AMHARIC_WORDS_30= {"ጸ","ጹ","ጺ","ጻ","ጼ","ጽ","ጾ"};
    public static String[] AMHARIC_WORDS_31= {"ፀ","ፁ","ፂ","ፃ","ፄ","ፅ","ፆ"};
    public static String[] AMHARIC_WORDS_32= {"ፈ","ፉ","ፊ","ፋ","ፌ","ፍ","ፎ"};
    public static String[] AMHARIC_WORDS_33= {"ፐ","ፑ","ፒ","ፓ","ፔ","ፕ","ፖ"};


    EditText edittext;
    public boolean main_key_first = false;
    private List<Keyboard.Key> keys;


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

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            // NOTE We can say '<Key android:codes="49,50" ... >' in the xml file; all codes come in keyCodes, the first in this list in primaryCode
            // Get the EditText and its Editable
/*            View focusCurrent = mHostActivity.getWindow().getCurrentFocus();
            if( focusCurrent==null || focusCurrent.getClass()!=EditText.class ) return;
            EditText edittext = (EditText) focusCurrent;
            Editable editable = edittext.getText();
            int start = edittext.getSelectionStart();*/

            if(!edittext.hasFocus()) return;
            Editable editable = edittext.getText();
            int start = edittext.getSelectionStart();
            // Apply the key to the edittext
            if( primaryCode==CodeCancel ) {
                hideCustomKeyboard();
            } else if( primaryCode==CodeDelete ) {
                if( editable!=null && start>0 ) editable.delete(start - 1, start);
            } else if( primaryCode==CodeClear ) {
                if( editable!=null ) editable.clear();
            } else if( primaryCode==CodeLeft ) {
                if( start>0 ) edittext.setSelection(start - 1);
            } else if( primaryCode==CodeRight ) {
                if (start < edittext.length()) edittext.setSelection(start + 1);
            } else if( primaryCode==CodeAllLeft ) {
                edittext.setSelection(0);
            } else if( primaryCode==CodeAllRight ) {
                edittext.setSelection(edittext.length());
            }

            //--------------- ሀ main --------
            else if(primaryCode == 1000){
                editable.insert(start, AMHARIC_WORDS[0]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_0[0];
                keys.get(1).label = AMHARIC_WORDS_0[1];
                keys.get(2).label = AMHARIC_WORDS_0[2];
                keys.get(3).label = AMHARIC_WORDS_0[3];
                keys.get(4).label = AMHARIC_WORDS_0[4];
                keys.get(5).label = AMHARIC_WORDS_0[5];
                keys.get(6).label = AMHARIC_WORDS_0[6];

                int[] cd1 = {1008};
                int[] cd12 = {1001};
                int[] cd13 = {1002};
                int[] cd14 = {1003};
                int[] cd15 = {1004};
                int[] cd16 = {1005};
                int[] cd17 = {1006};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }
            //--------------- ሀ sub --------

                else if(primaryCode == 1008){
                    if(main_key_first) {
                        if (editable != null && start > 0) editable.delete(start - 1, start);
                        editable.insert(start - 1, AMHARIC_WORDS_0[0]);
                        main_key_first = false;
                    }
                    else{
                        editable.insert(start,AMHARIC_WORDS_0[0]);
                    }
                }
                else if(primaryCode == 1001){
                    if(main_key_first) {
                        if (editable != null && start > 0) editable.delete(start - 1, start);
                        editable.insert(start - 1, AMHARIC_WORDS_0[1]);
                        main_key_first = false;
                    }
                    else{
                        editable.insert(start,AMHARIC_WORDS_0[1]);
                    }
                }
                else if(primaryCode == 1002){
                    if(main_key_first) {
                        if (editable != null && start > 0) editable.delete(start - 1, start);
                        editable.insert(start - 1, AMHARIC_WORDS_0[2]);
                        main_key_first = false;
                    }
                    else{
                        editable.insert(start,AMHARIC_WORDS_0[2]);
                    }
                }
                else if(primaryCode == 1003){
                    if(main_key_first) {
                        if (editable != null && start > 0) editable.delete(start - 1, start);
                        editable.insert(start - 1, AMHARIC_WORDS_0[3]);
                        main_key_first = false;
                    }
                    else{
                        editable.insert(start,AMHARIC_WORDS_0[3]);
                    }
                }
                else if(primaryCode == 1004){
                    if(main_key_first) {
                        if (editable != null && start > 0) editable.delete(start - 1, start);
                        editable.insert(start - 1, AMHARIC_WORDS_0[4]);
                        main_key_first = false;
                    }
                    else{
                        editable.insert(start,AMHARIC_WORDS_0[4]);
                    }
                }
                else if(primaryCode == 1005){
                    if(main_key_first) {
                        if (editable != null && start > 0) editable.delete(start - 1, start);
                        editable.insert(start - 1, AMHARIC_WORDS_0[5]);
                        main_key_first = false;
                    }
                    else{
                        editable.insert(start,AMHARIC_WORDS_0[5]);
                    }
                }
                else if(primaryCode == 1006){
                    if(main_key_first) {
                        if (editable != null && start > 0) editable.delete(start - 1, start);
                        editable.insert(start - 1, AMHARIC_WORDS_0[6]);
                        main_key_first = false;
                    }
                    else{
                        editable.insert(start,AMHARIC_WORDS_0[6]);
                    }
                }


            //----------- ለ main---------
            else if(primaryCode == 1010){
                editable.insert(start,AMHARIC_WORDS[1]);

                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_1[0];
                keys.get(1).label = AMHARIC_WORDS_1[1];
                keys.get(2).label = AMHARIC_WORDS_1[2];
                keys.get(3).label = AMHARIC_WORDS_1[3];
                keys.get(4).label = AMHARIC_WORDS_1[4];
                keys.get(5).label = AMHARIC_WORDS_1[5];
                keys.get(6).label = AMHARIC_WORDS_1[6];

                int[] cd1 = {1018};
                int[] cd12 = {1011};
                int[] cd13 = {1012};
                int[] cd14 = {1013};
                int[] cd15 = {1014};
                int[] cd16 = {1015};
                int[] cd17 = {1016};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }

            //----------- ለ sub---------

                    else if(primaryCode == 1018){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_1[0]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_1[0]);
                        }
                    }
                    else if(primaryCode == 1011){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_1[1]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_0[1]);
                        }
                    }
                    else if(primaryCode == 1012){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_1[2]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_1[2]);
                        }
                    }
                    else if(primaryCode == 1013){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_1[3]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_1[3]);
                        }
                    }
                    else if(primaryCode == 1014){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_1[4]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_1[4]);
                        }
                    }
                    else if(primaryCode == 1015){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_1[5]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_1[5]);
                        }
                    }
                    else if(primaryCode == 1016){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_1[6]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_1[6]);
                        }
                    }



            //----------- ሐ main---------
            else if(primaryCode == 1020){
                editable.insert(start,AMHARIC_WORDS[2]);

                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_2[0];
                keys.get(1).label = AMHARIC_WORDS_2[1];
                keys.get(2).label = AMHARIC_WORDS_2[2];
                keys.get(3).label = AMHARIC_WORDS_2[3];
                keys.get(4).label = AMHARIC_WORDS_2[4];
                keys.get(5).label = AMHARIC_WORDS_2[5];
                keys.get(6).label = AMHARIC_WORDS_2[6];

                int[] cd1 = {1028};
                int[] cd12 = {1021};
                int[] cd13 = {1022};
                int[] cd14 = {1023};
                int[] cd15 = {1024};
                int[] cd16 = {1025};
                int[] cd17 = {1026};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }

                    //--------------- ሐ sub --------

                    else if(primaryCode == 1028){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_2[0]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_2[0]);
                        }
                    }
                    else if(primaryCode == 1021){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_2[1]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_2[1]);
                        }
                    }
                    else if(primaryCode == 1022){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_2[2]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_2[2]);
                        }
                    }
                    else if(primaryCode == 1023){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_2[3]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_2[3]);
                        }
                    }
                    else if(primaryCode == 1024){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_2[4]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_2[4]);
                        }
                    }
                    else if(primaryCode == 1025){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_2[5]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_2[5]);
                        }
                    }
                    else if(primaryCode == 1026){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_2[6]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_2[6]);
                        }
                    }



            //----------- መ main---------

            else if(primaryCode == 1030){
                editable.insert(start,AMHARIC_WORDS[3]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_3[0];
                keys.get(1).label = AMHARIC_WORDS_3[1];
                keys.get(2).label = AMHARIC_WORDS_3[2];
                keys.get(3).label = AMHARIC_WORDS_3[3];
                keys.get(4).label = AMHARIC_WORDS_3[4];
                keys.get(5).label = AMHARIC_WORDS_3[5];
                keys.get(6).label = AMHARIC_WORDS_3[6];

                int[] cd1 = {1038};
                int[] cd12 = {1031};
                int[] cd13 = {1032};
                int[] cd14 = {1033};
                int[] cd15 = {1034};
                int[] cd16 = {1035};
                int[] cd17 = {1036};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }


                    //--------------- መ sub --------

                    else if(primaryCode == 1038){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_3[0]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_3[0]);
                        }
                    }
                    else if(primaryCode == 1031){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_3[1]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_3[1]);
                        }
                    }
                    else if(primaryCode == 1032){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_3[2]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_3[2]);
                        }
                    }
                    else if(primaryCode == 1033){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_3[3]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_3[3]);
                        }
                    }
                    else if(primaryCode == 1034){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_3[4]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_3[4]);
                        }
                    }
                    else if(primaryCode == 1035){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_3[5]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_3[5]);
                        }
                    }
                    else if(primaryCode == 1036){
                        if(main_key_first) {
                            if (editable != null && start > 0) editable.delete(start - 1, start);
                            editable.insert(start - 1, AMHARIC_WORDS_3[6]);
                            main_key_first = false;
                        }
                        else{
                            editable.insert(start,AMHARIC_WORDS_3[6]);
                        }
                    }



            //----------- ሠ main---------
            else if(primaryCode == 1040){
                editable.insert(start,AMHARIC_WORDS[4]);

                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_4[0];
                keys.get(1).label = AMHARIC_WORDS_4[1];
                keys.get(2).label = AMHARIC_WORDS_4[2];
                keys.get(3).label = AMHARIC_WORDS_4[3];
                keys.get(4).label = AMHARIC_WORDS_4[4];
                keys.get(5).label = AMHARIC_WORDS_4[5];
                keys.get(6).label = AMHARIC_WORDS_4[6];

                int[] cd1 = {1048};
                int[] cd12 = {1041};
                int[] cd13 = {1042};
                int[] cd14 = {1043};
                int[] cd15 = {1044};
                int[] cd16 = {1045};
                int[] cd17 = {1046};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;

            }


                        //--------------- ሠ sub --------

                        else if(primaryCode == 1048){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_4[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_4[0]);
                            }
                        }
                        else if(primaryCode == 1041){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_4[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_4[1]);
                            }
                        }
                        else if(primaryCode == 1042){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_4[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_4[2]);
                            }
                        }
                        else if(primaryCode == 1043){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_4[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_4[3]);
                            }
                        }
                        else if(primaryCode == 1044){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_4[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_4[4]);
                            }
                        }
                        else if(primaryCode == 1045){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_4[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_4[5]);
                            }
                        }
                        else if(primaryCode == 1046){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_4[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_4[6]);
                            }
                        }

            //----------- ረ main---------
            else if(primaryCode == 1050){
                editable.insert(start,AMHARIC_WORDS[5]);

                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_5[0];
                keys.get(1).label = AMHARIC_WORDS_5[1];
                keys.get(2).label = AMHARIC_WORDS_5[2];
                keys.get(3).label = AMHARIC_WORDS_5[3];
                keys.get(4).label = AMHARIC_WORDS_5[4];
                keys.get(5).label = AMHARIC_WORDS_5[5];
                keys.get(6).label = AMHARIC_WORDS_5[6];

                int[] cd1 = {1058};
                int[] cd12 = {1051};
                int[] cd13 = {1052};
                int[] cd14 = {1053};
                int[] cd15 = {1054};
                int[] cd16 = {1055};
                int[] cd17 = {1056};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;

            }


                        //--------------- ረ sub --------

                        else if(primaryCode == 1058){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_5[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_5[0]);
                            }
                        }
                        else if(primaryCode == 1051){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_5[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_5[1]);
                            }
                        }
                        else if(primaryCode == 1052){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_5[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_5[2]);
                            }
                        }
                        else if(primaryCode == 1053){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_5[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_5[3]);
                            }
                        }
                        else if(primaryCode == 1054){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_5[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_5[4]);
                            }
                        }
                        else if(primaryCode == 1055){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_5[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_5[5]);
                            }
                        }
                        else if(primaryCode == 1056){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_5[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_5[6]);
                            }
                        }



            //----------- ሰ main---------
            else if(primaryCode == 1060){
                editable.insert(start,AMHARIC_WORDS[6]);

                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_6[0];
                keys.get(1).label = AMHARIC_WORDS_6[1];
                keys.get(2).label = AMHARIC_WORDS_6[2];
                keys.get(3).label = AMHARIC_WORDS_6[3];
                keys.get(4).label = AMHARIC_WORDS_6[4];
                keys.get(5).label = AMHARIC_WORDS_6[5];
                keys.get(6).label = AMHARIC_WORDS_6[6];

                int[] cd1 = {1068};
                int[] cd12 = {1061};
                int[] cd13 = {1062};
                int[] cd14 = {1063};
                int[] cd15 = {1064};
                int[] cd16 = {1065};
                int[] cd17 = {1066};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;

            }



                        //--------------- ሰ sub --------

                        else if(primaryCode == 1068){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_6[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_6[0]);
                            }
                        }
                        else if(primaryCode == 1061){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_6[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_6[1]);
                            }
                        }
                        else if(primaryCode == 1062){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_6[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_6[2]);
                            }
                        }
                        else if(primaryCode == 1063){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_6[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_6[3]);
                            }
                        }
                        else if(primaryCode == 1064){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_6[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_6[4]);
                            }
                        }
                        else if(primaryCode == 1065){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_6[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_6[5]);
                            }
                        }
                        else if(primaryCode == 1066){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_6[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_6[6]);
                            }
                        }



            //----------- ሸ main---------
            else if(primaryCode == 1070){
                editable.insert(start,AMHARIC_WORDS[7]);

                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_7[0];
                keys.get(1).label = AMHARIC_WORDS_7[1];
                keys.get(2).label = AMHARIC_WORDS_7[2];
                keys.get(3).label = AMHARIC_WORDS_7[3];
                keys.get(4).label = AMHARIC_WORDS_7[4];
                keys.get(5).label = AMHARIC_WORDS_7[5];
                keys.get(6).label = AMHARIC_WORDS_7[6];

                int[] cd1 = {1078};
                int[] cd12 = {1071};
                int[] cd13 = {1072};
                int[] cd14 = {1073};
                int[] cd15 = {1074};
                int[] cd16 = {1075};
                int[] cd17 = {1076};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }


                            //--------------- ሸ sub --------

                            else if(primaryCode == 1078){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_7[0]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_7[0]);
                                }
                            }
                            else if(primaryCode == 1071){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_7[1]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_7[1]);
                                }
                            }
                            else if(primaryCode == 1072){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_7[2]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_7[2]);
                                }
                            }
                            else if(primaryCode == 1073){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_7[3]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_7[3]);
                                }
                            }
                            else if(primaryCode == 1074){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_7[4]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_7[4]);
                                }
                            }
                            else if(primaryCode == 1075){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_7[5]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_7[5]);
                                }
                            }
                            else if(primaryCode == 1076){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_7[6]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_7[6]);
                                }
                            }

            //----------- ቀ main---------
            else if(primaryCode == 1080){
                editable.insert(start,AMHARIC_WORDS[8]);

                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_8[0];
                keys.get(1).label = AMHARIC_WORDS_8[1];
                keys.get(2).label = AMHARIC_WORDS_8[2];
                keys.get(3).label = AMHARIC_WORDS_8[3];
                keys.get(4).label = AMHARIC_WORDS_8[4];
                keys.get(5).label = AMHARIC_WORDS_8[5];
                keys.get(6).label = AMHARIC_WORDS_8[6];

                int[] cd1 = {1088};
                int[] cd12 = {1081};
                int[] cd13 = {1082};
                int[] cd14 = {1083};
                int[] cd15 = {1084};
                int[] cd16 = {1085};
                int[] cd17 = {1086};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                            //--------------- ቀ sub --------

                            else if(primaryCode == 1088){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_8[0]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_8[0]);
                                }
                            }
                            else if(primaryCode == 1081){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_8[1]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_8[1]);
                                }
                            }
                            else if(primaryCode == 1082){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_8[2]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_8[2]);
                                }
                            }
                            else if(primaryCode == 1083){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_8[3]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_8[3]);
                                }
                            }
                            else if(primaryCode == 1084){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_8[4]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_8[4]);
                                }
                            }
                            else if(primaryCode == 1085){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_8[5]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_8[5]);
                                }
                            }
                            else if(primaryCode == 1086){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_8[6]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_8[6]);
                                }
                            }


            //----------- በ main---------
            else if(primaryCode == 1090){
                editable.insert(start,AMHARIC_WORDS[9]);

                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_9[0];
                keys.get(1).label = AMHARIC_WORDS_9[1];
                keys.get(2).label = AMHARIC_WORDS_9[2];
                keys.get(3).label = AMHARIC_WORDS_9[3];
                keys.get(4).label = AMHARIC_WORDS_9[4];
                keys.get(5).label = AMHARIC_WORDS_9[5];
                keys.get(6).label = AMHARIC_WORDS_9[6];

                int[] cd1 = {1098};
                int[] cd12 = {1091};
                int[] cd13 = {1092};
                int[] cd14 = {1093};
                int[] cd15 = {1094};
                int[] cd16 = {1095};
                int[] cd17 = {1096};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                            //--------------- በ sub --------

                            else if(primaryCode == 1098){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_9[0]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_9[0]);
                                }
                            }
                            else if(primaryCode == 1091){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_9[1]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_9[1]);
                                }
                            }
                            else if(primaryCode == 1092){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_9[2]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_9[2]);
                                }
                            }
                            else if(primaryCode == 1093){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_9[3]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_9[3]);
                                }
                            }
                            else if(primaryCode == 1094){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_9[4]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_9[4]);
                                }
                            }
                            else if(primaryCode == 1095){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_9[5]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_9[5]);
                                }
                            }
                            else if(primaryCode == 1096){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_9[6]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_9[6]);
                                }
                            }


            //----------- ቨ main---------
            else if(primaryCode == 1100){
                editable.insert(start,AMHARIC_WORDS[10]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_10[0];
                keys.get(1).label = AMHARIC_WORDS_10[1];
                keys.get(2).label = AMHARIC_WORDS_10[2];
                keys.get(3).label = AMHARIC_WORDS_10[3];
                keys.get(4).label = AMHARIC_WORDS_10[4];
                keys.get(5).label = AMHARIC_WORDS_10[5];
                keys.get(6).label = AMHARIC_WORDS_10[6];

                int[] cd1 = {1108};
                int[] cd12 = {1101};
                int[] cd13 = {1102};
                int[] cd14 = {1103};
                int[] cd15 = {1104};
                int[] cd16 = {1105};
                int[] cd17 = {1106};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }


                        //--------------- ቨ sub --------

                        else if(primaryCode == 1108){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_10[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_10[0]);
                            }
                        }
                        else if(primaryCode == 1101){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_10[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_10[1]);
                            }
                        }
                        else if(primaryCode == 1102){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_10[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_10[2]);
                            }
                        }
                        else if(primaryCode == 1103){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_10[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_10[3]);
                            }
                        }
                        else if(primaryCode == 1104){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_10[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_10[4]);
                            }
                        }
                        else if(primaryCode == 1105){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_10[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_10[5]);
                            }
                        }
                        else if(primaryCode == 1106){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_10[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_10[6]);
                            }
                        }

            //----------- ተ main---------
            else if(primaryCode == 1110){
                editable.insert(start,AMHARIC_WORDS[11]);

                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_11[0];
                keys.get(1).label = AMHARIC_WORDS_11[1];
                keys.get(2).label = AMHARIC_WORDS_11[2];
                keys.get(3).label = AMHARIC_WORDS_11[3];
                keys.get(4).label = AMHARIC_WORDS_11[4];
                keys.get(5).label = AMHARIC_WORDS_11[5];
                keys.get(6).label = AMHARIC_WORDS_11[6];

                int[] cd1 = {1118};
                int[] cd12 = {1111};
                int[] cd13 = {1112};
                int[] cd14 = {1113};
                int[] cd15 = {1114};
                int[] cd16 = {1115};
                int[] cd17 = {1116};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                        //--------------- ተ sub --------

                        else if(primaryCode == 1118){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_11[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_11[0]);
                            }
                        }
                        else if(primaryCode == 1111){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_11[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_11[1]);
                            }
                        }
                        else if(primaryCode == 1112){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_11[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_11[2]);
                            }
                        }
                        else if(primaryCode == 1113){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_11[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_11[3]);
                            }
                        }
                        else if(primaryCode == 1114){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_11[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_11[4]);
                            }
                        }
                        else if(primaryCode == 1115){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_11[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_11[5]);
                            }
                        }
                        else if(primaryCode == 1116){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_11[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_11[6]);
                            }
                        }



            //-----------  ቸ main---------
            else if(primaryCode == 1120){
                editable.insert(start,AMHARIC_WORDS[12]);

                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_12[0];
                keys.get(1).label = AMHARIC_WORDS_12[1];
                keys.get(2).label = AMHARIC_WORDS_12[2];
                keys.get(3).label = AMHARIC_WORDS_12[3];
                keys.get(4).label = AMHARIC_WORDS_12[4];
                keys.get(5).label = AMHARIC_WORDS_12[5];
                keys.get(6).label = AMHARIC_WORDS_12[6];

                int[] cd1 = {1128};
                int[] cd12 = {1121};
                int[] cd13 = {1122};
                int[] cd14 = {1123};
                int[] cd15 = {1124};
                int[] cd16 = {1125};
                int[] cd17 = {1126};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                                //--------------- ቸ sub --------

                                else if(primaryCode == 1128){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_12[0]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_12[0]);
                                    }
                                }
                                else if(primaryCode == 1121){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_12[1]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_12[1]);
                                    }
                                }
                                else if(primaryCode == 1122){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_12[2]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_12[2]);
                                    }
                                }
                                else if(primaryCode == 1123){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_12[3]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_12[3]);
                                    }
                                }
                                else if(primaryCode == 1124){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_12[4]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_12[4]);
                                    }
                                }
                                else if(primaryCode == 1125){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_12[5]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_12[5]);
                                    }
                                }
                                else if(primaryCode == 1126){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_12[6]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_12[6]);
                                    }
                                }

            //----------- ኀ main---------
            else if(primaryCode == 1130){
                editable.insert(start,AMHARIC_WORDS[13]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_13[0];
                keys.get(1).label = AMHARIC_WORDS_13[1];
                keys.get(2).label = AMHARIC_WORDS_13[2];
                keys.get(3).label = AMHARIC_WORDS_13[3];
                keys.get(4).label = AMHARIC_WORDS_13[4];
                keys.get(5).label = AMHARIC_WORDS_13[5];
                keys.get(6).label = AMHARIC_WORDS_13[6];

                int[] cd1 = {1138};
                int[] cd12 = {1131};
                int[] cd13 = {1132};
                int[] cd14 = {1133};
                int[] cd15 = {1134};
                int[] cd16 = {1135};
                int[] cd17 = {1136};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                        //--------------- ኀ sub --------

                        else if(primaryCode == 1138){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_13[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_13[0]);
                            }
                        }
                        else if(primaryCode == 1131){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_13[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_13[1]);
                            }
                        }
                        else if(primaryCode == 1132){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_13[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_13[2]);
                            }
                        }
                        else if(primaryCode == 1133){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_13[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_13[3]);
                            }
                        }
                        else if(primaryCode == 1134){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_13[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_13[4]);
                            }
                        }
                        else if(primaryCode == 1135){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_13[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_13[5]);
                            }
                        }
                        else if(primaryCode == 1136){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_13[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_13[6]);
                            }
                        }

            //----------- ነ main---------
            else if(primaryCode == 1140){
                editable.insert(start,AMHARIC_WORDS[14]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_14[0];
                keys.get(1).label = AMHARIC_WORDS_14[1];
                keys.get(2).label = AMHARIC_WORDS_14[2];
                keys.get(3).label = AMHARIC_WORDS_14[3];
                keys.get(4).label = AMHARIC_WORDS_14[4];
                keys.get(5).label = AMHARIC_WORDS_14[5];
                keys.get(6).label = AMHARIC_WORDS_14[6];

                int[] cd1 = {1148};
                int[] cd12 = {1141};
                int[] cd13 = {1142};
                int[] cd14 = {1143};
                int[] cd15 = {1144};
                int[] cd16 = {1145};
                int[] cd17 = {1146};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                        //--------------- ነ sub --------

                        else if(primaryCode == 1148){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_14[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_14[0]);
                            }
                        }
                        else if(primaryCode == 1141){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_14[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_14[1]);
                            }
                        }
                        else if(primaryCode == 1142){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_14[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_14[2]);
                            }
                        }
                        else if(primaryCode == 1143){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_14[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_14[3]);
                            }
                        }
                        else if(primaryCode == 1144){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_14[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_14[4]);
                            }
                        }
                        else if(primaryCode == 1145){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_14[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_14[5]);
                            }
                        }
                        else if(primaryCode == 1146){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_14[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_14[6]);
                            }
                        }

            //----------- ኘ main---------
            else if(primaryCode == 1150){
                editable.insert(start,AMHARIC_WORDS[15]);

                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_15[0];
                keys.get(1).label = AMHARIC_WORDS_15[1];
                keys.get(2).label = AMHARIC_WORDS_15[2];
                keys.get(3).label = AMHARIC_WORDS_15[3];
                keys.get(4).label = AMHARIC_WORDS_15[4];
                keys.get(5).label = AMHARIC_WORDS_15[5];
                keys.get(6).label = AMHARIC_WORDS_15[6];

                int[] cd1 = {1158};
                int[] cd12 = {1151};
                int[] cd13 = {1152};
                int[] cd14 = {1153};
                int[] cd15 = {1154};
                int[] cd16 = {1155};
                int[] cd17 = {1156};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }


                        //--------------- ኘ sub --------

                        else if(primaryCode == 1155){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_15[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_15[0]);
                            }
                        }
                        else if(primaryCode == 1151){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_15[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_15[1]);
                            }
                        }
                        else if(primaryCode == 1152){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_15[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_15[2]);
                            }
                        }
                        else if(primaryCode == 1153){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_15[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_15[3]);
                            }
                        }
                        else if(primaryCode == 1154){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_15[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_15[4]);
                            }
                        }
                        else if(primaryCode == 1155){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_15[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_15[5]);
                            }
                        }
                        else if(primaryCode == 1156){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_15[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_15[6]);
                            }
                        }



            //----------- አ main---------
            else if(primaryCode == 1160){
                editable.insert(start,AMHARIC_WORDS[16]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_16[0];
                keys.get(1).label = AMHARIC_WORDS_16[1];
                keys.get(2).label = AMHARIC_WORDS_16[2];
                keys.get(3).label = AMHARIC_WORDS_16[3];
                keys.get(4).label = AMHARIC_WORDS_16[4];
                keys.get(5).label = AMHARIC_WORDS_16[5];
                keys.get(6).label = AMHARIC_WORDS_16[6];

                int[] cd1 = {1168};
                int[] cd12 = {1161};
                int[] cd13 = {1162};
                int[] cd14 = {1163};
                int[] cd15 = {1164};
                int[] cd16 = {1165};
                int[] cd17 = {1166};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }





                            //--------------- አ sub --------

                            else if(primaryCode == 1168){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_16[0]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_16[0]);
                                }
                            }
                            else if(primaryCode == 1161){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_16[1]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_16[1]);
                                }
                            }
                            else if(primaryCode == 1162){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_16[2]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_16[2]);
                                }
                            }
                            else if(primaryCode == 1163){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_16[3]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_16[3]);
                                }
                            }
                            else if(primaryCode == 1164){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_16[4]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_16[4]);
                                }
                            }
                            else if(primaryCode == 1165){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_16[5]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_16[5]);
                                }
                            }
                            else if(primaryCode == 1166){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_16[6]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_16[6]);
                                }
                            }


            //----------- ከ main---------
            else if(primaryCode == 1170){
                editable.insert(start,AMHARIC_WORDS[17]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_17[0];
                keys.get(1).label = AMHARIC_WORDS_17[1];
                keys.get(2).label = AMHARIC_WORDS_17[2];
                keys.get(3).label = AMHARIC_WORDS_17[3];
                keys.get(4).label = AMHARIC_WORDS_17[4];
                keys.get(5).label = AMHARIC_WORDS_17[5];
                keys.get(6).label = AMHARIC_WORDS_17[6];

                int[] cd1 = {1178};
                int[] cd12 = {1171};
                int[] cd13 = {1172};
                int[] cd14 = {1173};
                int[] cd15 = {1174};
                int[] cd16 = {1175};
                int[] cd17 = {1176};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                        //--------------- ከ sub --------

                        else if(primaryCode == 1178){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_17[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_17[0]);
                            }
                        }
                        else if(primaryCode == 1171){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_17[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_17[1]);
                            }
                        }
                        else if(primaryCode == 1172){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_17[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_17[2]);
                            }
                        }
                        else if(primaryCode == 1173){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_17[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_17[3]);
                            }
                        }
                        else if(primaryCode == 1174){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_17[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_17[4]);
                            }
                        }
                        else if(primaryCode == 1175){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_17[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_17[5]);
                            }
                        }
                        else if(primaryCode == 1176){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_17[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_17[6]);
                            }
                        }



            //----------- ኸ main---------
            else if(primaryCode == 1180){
                editable.insert(start,AMHARIC_WORDS[18]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_18[0];
                keys.get(1).label = AMHARIC_WORDS_18[1];
                keys.get(2).label = AMHARIC_WORDS_18[2];
                keys.get(3).label = AMHARIC_WORDS_18[3];
                keys.get(4).label = AMHARIC_WORDS_18[4];
                keys.get(5).label = AMHARIC_WORDS_18[5];
                keys.get(6).label = AMHARIC_WORDS_18[6];

                int[] cd1 = {1188};
                int[] cd12 = {1181};
                int[] cd13 = {1182};
                int[] cd14 = {1183};
                int[] cd15 = {1184};
                int[] cd16 = {1185};
                int[] cd17 = {1186};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }


                        //--------------- ኸ sub --------

                        else if(primaryCode == 1188){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_18[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_18[0]);
                            }
                        }
                        else if(primaryCode == 1181){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_18[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_18[1]);
                            }
                        }
                        else if(primaryCode == 1182){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_18[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_18[2]);
                            }
                        }
                        else if(primaryCode == 1183){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_18[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_18[3]);
                            }
                        }
                        else if(primaryCode == 1184){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_18[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_18[4]);
                            }
                        }
                        else if(primaryCode == 1185){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_18[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_18[5]);
                            }
                        }
                        else if(primaryCode == 1186){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_18[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_18[6]);
                            }
                        }


            //----------- ወ main---------
            else if(primaryCode == 1190){
                editable.insert(start,AMHARIC_WORDS[19]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_19[0];
                keys.get(1).label = AMHARIC_WORDS_19[1];
                keys.get(2).label = AMHARIC_WORDS_19[2];
                keys.get(3).label = AMHARIC_WORDS_19[3];
                keys.get(4).label = AMHARIC_WORDS_19[4];
                keys.get(5).label = AMHARIC_WORDS_19[5];
                keys.get(6).label = AMHARIC_WORDS_19[6];

                int[] cd1 = {1198};
                int[] cd12 = {1191};
                int[] cd13 = {1192};
                int[] cd14 = {1193};
                int[] cd15 = {1194};
                int[] cd16 = {1195};
                int[] cd17 = {1196};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }


                        //--------------- ወ sub --------

                        else if(primaryCode == 1198){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_19[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_19[0]);
                            }
                        }
                        else if(primaryCode == 1191){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_19[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_19[1]);
                            }
                        }
                        else if(primaryCode == 1192){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_19[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_19[2]);
                            }
                        }
                        else if(primaryCode == 1193){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_19[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_19[3]);
                            }
                        }
                        else if(primaryCode == 1194){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_19[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_19[4]);
                            }
                        }
                        else if(primaryCode == 1195){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_19[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_19[5]);
                            }
                        }
                        else if(primaryCode == 1196){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_19[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_19[6]);
                            }
                        }


            //----------- ዐ main---------
            else if(primaryCode == 1200){
                editable.insert(start,AMHARIC_WORDS[20]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_20[0];
                keys.get(1).label = AMHARIC_WORDS_20[1];
                keys.get(2).label = AMHARIC_WORDS_20[2];
                keys.get(3).label = AMHARIC_WORDS_20[3];
                keys.get(4).label = AMHARIC_WORDS_20[4];
                keys.get(5).label = AMHARIC_WORDS_20[5];
                keys.get(6).label = AMHARIC_WORDS_20[6];

                int[] cd1 = {1208};
                int[] cd12 = {1201};
                int[] cd13 = {1202};
                int[] cd14 = {1203};
                int[] cd15 = {1204};
                int[] cd16 = {1205};
                int[] cd17 = {1206};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                                //--------------- ዐ sub --------

                                else if(primaryCode == 1208){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_20[0]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_20[0]);
                                    }
                                }
                                else if(primaryCode == 1201){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_20[1]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_20[1]);
                                    }
                                }
                                else if(primaryCode == 1202){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_20[2]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_20[2]);
                                    }
                                }
                                else if(primaryCode == 1203){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_20[3]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_20[3]);
                                    }
                                }
                                else if(primaryCode == 1204){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_20[4]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_20[4]);
                                    }
                                }
                                else if(primaryCode == 1205){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_20[5]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_20[5]);
                                    }
                                }
                                else if(primaryCode == 1206){
                                    if(main_key_first) {
                                        if (editable != null && start > 0) editable.delete(start - 1, start);
                                        editable.insert(start - 1, AMHARIC_WORDS_20[6]);
                                        main_key_first = false;
                                    }
                                    else{
                                        editable.insert(start,AMHARIC_WORDS_20[6]);
                                    }
                                }



            //----------- ዘ main---------
            else if(primaryCode == 1210){
                editable.insert(start,AMHARIC_WORDS[21]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_21[0];
                keys.get(1).label = AMHARIC_WORDS_21[1];
                keys.get(2).label = AMHARIC_WORDS_21[2];
                keys.get(3).label = AMHARIC_WORDS_21[3];
                keys.get(4).label = AMHARIC_WORDS_21[4];
                keys.get(5).label = AMHARIC_WORDS_21[5];
                keys.get(6).label = AMHARIC_WORDS_21[6];

                int[] cd1 = {1218};
                int[] cd12 = {1211};
                int[] cd13 = {1212};
                int[] cd14 = {1213};
                int[] cd15 = {1214};
                int[] cd16 = {1215};
                int[] cd17 = {1216};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                            //--------------- ዘ sub --------

                            else if(primaryCode == 1218){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_21[0]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_21[0]);
                                }
                            }
                            else if(primaryCode == 1211){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_21[1]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_21[1]);
                                }
                            }
                            else if(primaryCode == 1212){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_21[2]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_21[2]);
                                }
                            }
                            else if(primaryCode == 1213){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_21[3]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_21[3]);
                                }
                            }
                            else if(primaryCode == 1214){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_21[4]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_21[4]);
                                }
                            }
                            else if(primaryCode == 1215){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_21[5]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_21[5]);
                                }
                            }
                            else if(primaryCode == 1216){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_21[6]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_21[6]);
                                }
                            }


            //----------- ዠ main---------
            else if(primaryCode == 1220){
                editable.insert(start,AMHARIC_WORDS[22]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_22[0];
                keys.get(1).label = AMHARIC_WORDS_22[1];
                keys.get(2).label = AMHARIC_WORDS_22[2];
                keys.get(3).label = AMHARIC_WORDS_22[3];
                keys.get(4).label = AMHARIC_WORDS_22[4];
                keys.get(5).label = AMHARIC_WORDS_22[5];
                keys.get(6).label = AMHARIC_WORDS_22[6];

                int[] cd1 = {1228};
                int[] cd12 = {1221};
                int[] cd13 = {1222};
                int[] cd14 = {1223};
                int[] cd15 = {1224};
                int[] cd16 = {1225};
                int[] cd17 = {1226};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }




                            //--------------- ዠ sub --------

                            else if(primaryCode == 1228){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_22[0]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_22[0]);
                                }
                            }
                            else if(primaryCode == 1221){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_22[1]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_22[1]);
                                }
                            }
                            else if(primaryCode == 1222){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_22[2]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_22[2]);
                                }
                            }
                            else if(primaryCode == 1223){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_22[3]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_22[3]);
                                }
                            }
                            else if(primaryCode == 1224){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_22[4]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_22[4]);
                                }
                            }
                            else if(primaryCode == 1225){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_22[5]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_22[5]);
                                }
                            }
                            else if(primaryCode == 1226){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_22[6]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_22[6]);
                                }
                            }

            //----------- የ main---------
            else if(primaryCode == 1230){
                editable.insert(start,AMHARIC_WORDS[23]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_23[0];
                keys.get(1).label = AMHARIC_WORDS_23[1];
                keys.get(2).label = AMHARIC_WORDS_23[2];
                keys.get(3).label = AMHARIC_WORDS_23[3];
                keys.get(4).label = AMHARIC_WORDS_23[4];
                keys.get(5).label = AMHARIC_WORDS_23[5];
                keys.get(6).label = AMHARIC_WORDS_23[6];

                int[] cd1 = {1238};
                int[] cd12 = {1231};
                int[] cd13 = {1232};
                int[] cd14 = {1233};
                int[] cd15 = {1234};
                int[] cd16 = {1235};
                int[] cd17 = {1236};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }





                        //--------------- የ sub --------

                        else if(primaryCode == 1238){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_23[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_23[0]);
                            }
                        }
                        else if(primaryCode == 1231){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_23[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_23[1]);
                            }
                        }
                        else if(primaryCode == 1232){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_23[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_23[2]);
                            }
                        }
                        else if(primaryCode == 1233){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_23[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_23[3]);
                            }
                        }
                        else if(primaryCode == 1234){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_23[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_23[4]);
                            }
                        }
                        else if(primaryCode == 1235){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_23[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_23[5]);
                            }
                        }
                        else if(primaryCode == 1236){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_23[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_23[6]);
                            }
                        }


            //----------- ደ main---------
            else if(primaryCode == 1240){
                editable.insert(start,AMHARIC_WORDS[24]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_24[0];
                keys.get(1).label = AMHARIC_WORDS_24[1];
                keys.get(2).label = AMHARIC_WORDS_24[2];
                keys.get(3).label = AMHARIC_WORDS_24[3];
                keys.get(4).label = AMHARIC_WORDS_24[4];
                keys.get(5).label = AMHARIC_WORDS_24[5];
                keys.get(6).label = AMHARIC_WORDS_24[6];

                int[] cd1 = {1248};
                int[] cd12 = {1241};
                int[] cd13 = {1242};
                int[] cd14 = {1243};
                int[] cd15 = {1244};
                int[] cd16 = {1245};
                int[] cd17 = {1246};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }




                            //--------------- ደ sub --------

                            else if(primaryCode == 1248){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_24[0]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_24[0]);
                                }
                            }
                            else if(primaryCode == 1241){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_24[1]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_24[1]);
                                }
                            }
                            else if(primaryCode == 1242){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_24[2]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_24[2]);
                                }
                            }
                            else if(primaryCode == 1243){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_24[3]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_24[3]);
                                }
                            }
                            else if(primaryCode == 1244){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_24[4]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_24[4]);
                                }
                            }
                            else if(primaryCode == 1245){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_24[5]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_24[5]);
                                }
                            }
                            else if(primaryCode == 1246){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_24[6]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_24[6]);
                                }
                            }


            //----------- ጀ main---------
            else if(primaryCode == 1250){
                editable.insert(start,AMHARIC_WORDS[25]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_25[0];
                keys.get(1).label = AMHARIC_WORDS_25[1];
                keys.get(2).label = AMHARIC_WORDS_25[2];
                keys.get(3).label = AMHARIC_WORDS_25[3];
                keys.get(4).label = AMHARIC_WORDS_25[4];
                keys.get(5).label = AMHARIC_WORDS_25[5];
                keys.get(6).label = AMHARIC_WORDS_25[6];

                int[] cd1 = {1258};
                int[] cd12 = {1251};
                int[] cd13 = {1252};
                int[] cd14 = {1253};
                int[] cd15 = {1254};
                int[] cd16 = {1255};
                int[] cd17 = {1256};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }




                            //--------------- ጀ sub --------

                            else if(primaryCode == 1258){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_25[0]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_25[0]);
                                }
                            }
                            else if(primaryCode == 1251){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_25[1]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_25[1]);
                                }
                            }
                            else if(primaryCode == 1252){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_25[2]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_25[2]);
                                }
                            }
                            else if(primaryCode == 1253){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_25[3]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_25[3]);
                                }
                            }
                            else if(primaryCode == 1254){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_25[4]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_25[4]);
                                }
                            }
                            else if(primaryCode == 1255){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_25[5]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_25[5]);
                                }
                            }
                            else if(primaryCode == 1256){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_25[6]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_25[6]);
                                }
                            }


            //----------- ገ main---------
            else if(primaryCode == 1260){
                editable.insert(start,AMHARIC_WORDS[26]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_26[0];
                keys.get(1).label = AMHARIC_WORDS_26[1];
                keys.get(2).label = AMHARIC_WORDS_26[2];
                keys.get(3).label = AMHARIC_WORDS_26[3];
                keys.get(4).label = AMHARIC_WORDS_26[4];
                keys.get(5).label = AMHARIC_WORDS_26[5];
                keys.get(6).label = AMHARIC_WORDS_26[6];

                int[] cd1 = {1268};
                int[] cd12 = {1261};
                int[] cd13 = {1262};
                int[] cd14 = {1263};
                int[] cd15 = {1264};
                int[] cd16 = {1265};
                int[] cd17 = {1266};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }


                        //--------------- ገ sub --------

                        else if(primaryCode == 1268){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_26[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_26[0]);
                            }
                        }
                        else if(primaryCode == 1261){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_26[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_26[1]);
                            }
                        }
                        else if(primaryCode == 1262){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_26[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_26[2]);
                            }
                        }
                        else if(primaryCode == 1263){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_26[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_26[3]);
                            }
                        }
                        else if(primaryCode == 1264){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_26[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_26[4]);
                            }
                        }
                        else if(primaryCode == 1265){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_26[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_26[5]);
                            }
                        }
                        else if(primaryCode == 1266){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_26[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_26[6]);
                            }
                        }


            //----------- ጠ main---------
            else if(primaryCode == 1270){
                editable.insert(start,AMHARIC_WORDS[27]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_27[0];
                keys.get(1).label = AMHARIC_WORDS_27[1];
                keys.get(2).label = AMHARIC_WORDS_27[2];
                keys.get(3).label = AMHARIC_WORDS_27[3];
                keys.get(4).label = AMHARIC_WORDS_27[4];
                keys.get(5).label = AMHARIC_WORDS_27[5];
                keys.get(6).label = AMHARIC_WORDS_27[6];

                int[] cd1 = {1278};
                int[] cd12 = {1271};
                int[] cd13 = {1272};
                int[] cd14 = {1273};
                int[] cd15 = {1274};
                int[] cd16 = {1275};
                int[] cd17 = {1276};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }


                        //--------------- ጠ sub --------

                        else if(primaryCode == 1278){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_27[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_27[0]);
                            }
                        }
                        else if(primaryCode == 1271){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_27[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_27[1]);
                            }
                        }
                        else if(primaryCode == 1272){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_27[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_27[2]);
                            }
                        }
                        else if(primaryCode == 1273){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_27[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_27[3]);
                            }
                        }
                        else if(primaryCode == 1274){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_27[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_27[4]);
                            }
                        }
                        else if(primaryCode == 1275){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_27[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_27[5]);
                            }
                        }
                        else if(primaryCode == 1276){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_27[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_27[6]);
                            }
                        }

            //----------- ጨ main---------
            else if(primaryCode == 1280){
                editable.insert(start,AMHARIC_WORDS[28]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_28[0];
                keys.get(1).label = AMHARIC_WORDS_28[1];
                keys.get(2).label = AMHARIC_WORDS_28[2];
                keys.get(3).label = AMHARIC_WORDS_28[3];
                keys.get(4).label = AMHARIC_WORDS_28[4];
                keys.get(5).label = AMHARIC_WORDS_28[5];
                keys.get(6).label = AMHARIC_WORDS_28[6];

                int[] cd1 = {1288};
                int[] cd12 = {1281};
                int[] cd13 = {1282};
                int[] cd14 = {1283};
                int[] cd15 = {1284};
                int[] cd16 = {1285};
                int[] cd17 = {1286};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                            //--------------- ጨ sub --------

                            else if(primaryCode == 1288){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_28[0]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_28[0]);
                                }
                            }
                            else if(primaryCode == 1281){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_28[1]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_28[1]);
                                }
                            }
                            else if(primaryCode == 1282){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_28[2]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_28[2]);
                                }
                            }
                            else if(primaryCode == 1283){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_28[3]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_28[3]);
                                }
                            }
                            else if(primaryCode == 1284){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_28[4]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_28[4]);
                                }
                            }
                            else if(primaryCode == 1285){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_28[5]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_28[5]);
                                }
                            }
                            else if(primaryCode == 1286){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_28[6]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_28[6]);
                                }
                            }


            //----------- ጰ main---------
            else if(primaryCode == 1290){
                editable.insert(start,AMHARIC_WORDS[29]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_29[0];
                keys.get(1).label = AMHARIC_WORDS_29[1];
                keys.get(2).label = AMHARIC_WORDS_29[2];
                keys.get(3).label = AMHARIC_WORDS_29[3];
                keys.get(4).label = AMHARIC_WORDS_29[4];
                keys.get(5).label = AMHARIC_WORDS_29[5];
                keys.get(6).label = AMHARIC_WORDS_29[6];

                int[] cd1 = {1298};
                int[] cd12 = {1291};
                int[] cd13 = {1292};
                int[] cd14 = {1293};
                int[] cd15 = {1294};
                int[] cd16 = {1295};
                int[] cd17 = {1296};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                            //--------------- ጰ sub --------

                            else if(primaryCode == 1298){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_29[0]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_29[0]);
                                }
                            }
                            else if(primaryCode == 1291){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_29[1]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_29[1]);
                                }
                            }
                            else if(primaryCode == 1292){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_29[2]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_29[2]);
                                }
                            }
                            else if(primaryCode == 1293){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_29[3]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_29[3]);
                                }
                            }
                            else if(primaryCode == 1294){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_29[4]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_29[4]);
                                }
                            }
                            else if(primaryCode == 1295){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_29[5]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_29[5]);
                                }
                            }
                            else if(primaryCode == 1296){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_29[6]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_29[6]);
                                }
                            }


            //----------- ጸ main---------
            else if(primaryCode == 1300){
                editable.insert(start,AMHARIC_WORDS[30]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_30[0];
                keys.get(1).label = AMHARIC_WORDS_30[1];
                keys.get(2).label = AMHARIC_WORDS_30[2];
                keys.get(3).label = AMHARIC_WORDS_30[3];
                keys.get(4).label = AMHARIC_WORDS_30[4];
                keys.get(5).label = AMHARIC_WORDS_30[5];
                keys.get(6).label = AMHARIC_WORDS_30[6];

                int[] cd1 = {1308};
                int[] cd12 = {1301};
                int[] cd13 = {1302};
                int[] cd14 = {1303};
                int[] cd15 = {1304};
                int[] cd16 = {1305};
                int[] cd17 = {1306};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                        //--------------- ጸ sub --------

                        else if(primaryCode == 1308){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_30[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_30[0]);
                            }
                        }
                        else if(primaryCode == 1301){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_30[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_30[1]);
                            }
                        }
                        else if(primaryCode == 1302){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_30[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_30[2]);
                            }
                        }
                        else if(primaryCode == 1303){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_30[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_30[3]);
                            }
                        }
                        else if(primaryCode == 1304){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_30[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_30[4]);
                            }
                        }
                        else if(primaryCode == 1305){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_30[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_30[5]);
                            }
                        }
                        else if(primaryCode == 1306){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_30[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_30[6]);
                            }
                        }

            //----------- ፀ main---------
            else if(primaryCode == 1310){
                editable.insert(start,AMHARIC_WORDS[31]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_31[0];
                keys.get(1).label = AMHARIC_WORDS_31[1];
                keys.get(2).label = AMHARIC_WORDS_31[2];
                keys.get(3).label = AMHARIC_WORDS_31[3];
                keys.get(4).label = AMHARIC_WORDS_31[4];
                keys.get(5).label = AMHARIC_WORDS_31[5];
                keys.get(6).label = AMHARIC_WORDS_31[6];

                int[] cd1 = {1318};
                int[] cd12 = {1311};
                int[] cd13 = {1312};
                int[] cd14 = {1313};
                int[] cd15 = {1314};
                int[] cd16 = {1315};
                int[] cd17 = {1316};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                        //--------------- ፀ sub --------

                        else if(primaryCode == 1318){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_31[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_31[0]);
                            }
                        }
                        else if(primaryCode == 1311){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_31[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_31[1]);
                            }
                        }
                        else if(primaryCode == 1312){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_31[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_31[2]);
                            }
                        }
                        else if(primaryCode == 1313){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_31[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_31[3]);
                            }
                        }
                        else if(primaryCode == 1314){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_31[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_31[4]);
                            }
                        }
                        else if(primaryCode == 1315){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_31[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_31[5]);
                            }
                        }
                        else if(primaryCode == 1316){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_31[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_31[6]);
                            }
                        }

            //----------- ፈ main---------

            else if(primaryCode == 1320){
                editable.insert(start,AMHARIC_WORDS[32]);
                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_32[0];
                keys.get(1).label = AMHARIC_WORDS_32[1];
                keys.get(2).label = AMHARIC_WORDS_32[2];
                keys.get(3).label = AMHARIC_WORDS_32[3];
                keys.get(4).label = AMHARIC_WORDS_32[4];
                keys.get(5).label = AMHARIC_WORDS_32[5];
                keys.get(6).label = AMHARIC_WORDS_32[6];

                int[] cd1 = {1328};
                int[] cd12 = {1321};
                int[] cd13 = {1322};
                int[] cd14 = {1323};
                int[] cd15 = {1324};
                int[] cd16 = {1325};
                int[] cd17 = {1326};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }


                            //--------------- ፈ sub --------

                            else if(primaryCode == 1328){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_32[0]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_32[0]);
                                }
                            }
                            else if(primaryCode == 1321){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_32[1]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_32[1]);
                                }
                            }
                            else if(primaryCode == 1322){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_32[2]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_32[2]);
                                }
                            }
                            else if(primaryCode == 1323){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_32[3]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_32[3]);
                                }
                            }
                            else if(primaryCode == 1324){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_32[4]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_32[4]);
                                }
                            }
                            else if(primaryCode == 1325){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_32[5]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_32[5]);
                                }
                            }
                            else if(primaryCode == 1326){
                                if(main_key_first) {
                                    if (editable != null && start > 0) editable.delete(start - 1, start);
                                    editable.insert(start - 1, AMHARIC_WORDS_32[6]);
                                    main_key_first = false;
                                }
                                else{
                                    editable.insert(start,AMHARIC_WORDS_32[6]);
                                }
                            }


            //----------- ፐ main---------
            else if(primaryCode == 1330){
                editable.insert(start,AMHARIC_WORDS[33]);

                main_key_first = true;
                mKeyboardView.invalidateAllKeys();
                keys.get(0).label = AMHARIC_WORDS_33[0];
                keys.get(1).label = AMHARIC_WORDS_33[1];
                keys.get(2).label = AMHARIC_WORDS_33[2];
                keys.get(3).label = AMHARIC_WORDS_33[3];
                keys.get(4).label = AMHARIC_WORDS_33[4];
                keys.get(5).label = AMHARIC_WORDS_33[5];
                keys.get(6).label = AMHARIC_WORDS_33[6];

                int[] cd1 = {1338};
                int[] cd12 = {1331};
                int[] cd13 = {1332};
                int[] cd14 = {1333};
                int[] cd15 = {1334};
                int[] cd16 = {1335};
                int[] cd17 = {1336};

                keys.get(0).codes = cd1;
                keys.get(1).codes = cd12;
                keys.get(2).codes = cd13;
                keys.get(3).codes = cd14;
                keys.get(4).codes = cd15;
                keys.get(5).codes = cd16;
                keys.get(6).codes = cd17;
            }



                        //--------------- ፈ sub --------

                        else if(primaryCode == 1338){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_33[0]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_33[0]);
                            }
                        }
                        else if(primaryCode == 1331){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_33[1]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_33[1]);
                            }
                        }
                        else if(primaryCode == 1332){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_33[2]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_33[2]);
                            }
                        }
                        else if(primaryCode == 1333){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_33[3]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_33[3]);
                            }
                        }
                        else if(primaryCode == 1334){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_33[4]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_33[4]);
                            }
                        }
                        else if(primaryCode == 1335){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_33[5]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_33[5]);
                            }
                        }
                        else if(primaryCode == 1336){
                            if(main_key_first) {
                                if (editable != null && start > 0) editable.delete(start - 1, start);
                                editable.insert(start - 1, AMHARIC_WORDS_33[6]);
                                main_key_first = false;
                            }
                            else{
                                editable.insert(start,AMHARIC_WORDS_33[6]);
                            }
                        }


            //----------- Symbols main---------0
            else if(primaryCode == 1400){
                editable.insert(start,AMHARIC_WORDS[34]);
            }


            else {
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


    public CustomKeyboard(Activity host, View view, int viewid, int layoutid) {
        mHostActivity= host;
        mKeyboardView = (KeyboardView) view.findViewById(viewid);
        mKeyboardView.setKeyboard(new Keyboard(mHostActivity, layoutid));
        mKeyboardView.setPreviewEnabled(false); // NOTE Do not show the preview balloons
        mKeyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);
        // Hide the standard keyboard initially
        mHostActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        keys = mKeyboardView.getKeyboard().getKeys();



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


    public void registerEditText(EditText edittext) {
        this.edittext = edittext;
        edittext.setOnFocusChangeListener(new OnFocusChangeListener() {
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
                EditText edittext = (EditText) v;
                int inType = edittext.getInputType();       // Backup the input type
                edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
                edittext.onTouchEvent(event);               // Call native handler
                edittext.setInputType(inType);              // Restore input type
                return true; // Consume touch event
            }
        });
        // Disable spell check (hex strings look like words to Android)
        edittext.setInputType(edittext.getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }

}

