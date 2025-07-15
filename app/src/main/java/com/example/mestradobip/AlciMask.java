package com.example.mestradobip;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class AlciMask {

    public static TextWatcher aplicarMascara(final EditText ediTxt, final String CriaMascara) {
        return new TextWatcher() {
            boolean atualizado;
            String antigo = "";

            @Override
            public void afterTextChanged(final Editable s) {}

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {}

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                final String str = AlciMask.removerMascara(s.toString());
                String mascara = "";
                if (atualizado) {
                    antigo = str;
                    atualizado = false;
                    return;
                }
                int i = 0;
                for (final char m : CriaMascara.toCharArray()) {
                    if (m != '#' && str.length() > antigo.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (final Exception e) {
                        break;
                    }
                    i++;
                }
                atualizado = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
        };
    }

    public static String removerMascara(final String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]","").replaceAll("[:]", "").replaceAll("[)]", "");
    }

}
