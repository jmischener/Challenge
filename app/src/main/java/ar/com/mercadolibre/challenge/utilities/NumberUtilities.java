package ar.com.mercadolibre.challenge.utilities;

public class NumberUtilities {

    public static String setFormat(String number) {
        try {
            String ent = getInt(number);
            String dec = getDecimal(number);
            String ret = "";

            if (ent.length() > 3) {
                int i;
                for (i = ent.length() - 3; i > 0; i = i - 3) {
                    ret = "." + ent.substring(i, i + 3) + ret;
                }
                ret = ent.substring(0, i + 3) + ret;
            } else {
                ret = ent;
            }

            if (!dec.equals("0")) {
                ret += "," + dec;
            }
            return ret;
        }
        catch(Exception ignored) { return number; }
    }

    public static String getDecimal(String number) {
        try {
            int pos = number.indexOf('.');
            if (pos >= 0) {
                return number.substring(pos + 1);
            }
            return number;
        }
        catch(Exception ignored) { return number; }
    }

    public static String getInt(String number) {
        try {
            int pos = number.indexOf('.');
            if (pos >= 0) {
                return number.substring(0, pos);
            }
            return "0";
        }
        catch(Exception ignored) { return "0"; }
    }
}
