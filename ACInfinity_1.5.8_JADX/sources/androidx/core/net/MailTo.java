package androidx.core.net;

import android.net.Uri;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.text.Typography;

public final class MailTo {
    private static final String BCC = "bcc";
    private static final String BODY = "body";

    /* renamed from: CC */
    private static final String f33CC = "cc";
    private static final String MAILTO = "mailto";
    public static final String MAILTO_SCHEME = "mailto:";
    private static final String SUBJECT = "subject";

    /* renamed from: TO */
    private static final String f34TO = "to";
    private HashMap<String, String> mHeaders = new HashMap<>();

    private MailTo() {
    }

    public static boolean isMailTo(String str) {
        return str != null && str.startsWith(MAILTO_SCHEME);
    }

    public static boolean isMailTo(Uri uri) {
        return uri != null && MAILTO.equals(uri.getScheme());
    }

    public static MailTo parse(String str) throws ParseException {
        String str2;
        String str3;
        Preconditions.checkNotNull(str);
        if (isMailTo(str)) {
            int indexOf = str.indexOf(35);
            if (indexOf != -1) {
                str = str.substring(0, indexOf);
            }
            int indexOf2 = str.indexOf(63);
            if (indexOf2 == -1) {
                str3 = Uri.decode(str.substring(7));
                str2 = null;
            } else {
                str3 = Uri.decode(str.substring(7, indexOf2));
                str2 = str.substring(indexOf2 + 1);
            }
            MailTo mailTo = new MailTo();
            if (str2 != null) {
                for (String split : str2.split("&")) {
                    String[] split2 = split.split("=", 2);
                    if (split2.length != 0) {
                        mailTo.mHeaders.put(Uri.decode(split2[0]).toLowerCase(Locale.ROOT), split2.length > 1 ? Uri.decode(split2[1]) : null);
                    }
                }
            }
            String to = mailTo.getTo();
            if (to != null) {
                str3 = str3 + ", " + to;
            }
            mailTo.mHeaders.put(f34TO, str3);
            return mailTo;
        }
        throw new ParseException("Not a mailto scheme");
    }

    public static MailTo parse(Uri uri) throws ParseException {
        return parse(uri.toString());
    }

    public String getTo() {
        return this.mHeaders.get(f34TO);
    }

    public String getCc() {
        return this.mHeaders.get(f33CC);
    }

    public String getBcc() {
        return this.mHeaders.get(BCC);
    }

    public String getSubject() {
        return this.mHeaders.get(SUBJECT);
    }

    public String getBody() {
        return this.mHeaders.get(BODY);
    }

    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(MAILTO_SCHEME);
        sb.append('?');
        for (Map.Entry next : this.mHeaders.entrySet()) {
            sb.append(Uri.encode((String) next.getKey()));
            sb.append('=');
            sb.append(Uri.encode((String) next.getValue()));
            sb.append(Typography.amp);
        }
        return sb.toString();
    }
}
