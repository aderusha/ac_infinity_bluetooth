package okhttp3.internal.tls;

import javax.security.auth.x500.X500Principal;

final class DistinguishedNameParser {
    private int beg;
    private char[] chars;
    private int cur;

    /* renamed from: dn */
    private final String f1218dn;
    private int end;
    private final int length;
    private int pos;

    DistinguishedNameParser(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.f1218dn = name;
        this.length = name.length();
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0015 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String nextAT() {
        /*
            r7 = this;
        L_0x0000:
            int r0 = r7.pos
            int r1 = r7.length
            r2 = 32
            if (r0 >= r1) goto L_0x0013
            char[] r3 = r7.chars
            char r3 = r3[r0]
            if (r3 != r2) goto L_0x0013
            int r0 = r0 + 1
            r7.pos = r0
            goto L_0x0000
        L_0x0013:
            if (r0 != r1) goto L_0x0017
            r0 = 0
            return r0
        L_0x0017:
            r7.beg = r0
            int r0 = r0 + 1
            r7.pos = r0
        L_0x001d:
            int r0 = r7.pos
            int r1 = r7.length
            r3 = 61
            if (r0 >= r1) goto L_0x0034
            char[] r4 = r7.chars
            char r5 = r4[r0]
            if (r5 == r3) goto L_0x0034
            char r4 = r4[r0]
            if (r4 == r2) goto L_0x0034
            int r0 = r0 + 1
            r7.pos = r0
            goto L_0x001d
        L_0x0034:
            java.lang.String r4 = "Unexpected end of DN: "
            if (r0 >= r1) goto L_0x00d9
            r7.end = r0
            char[] r1 = r7.chars
            char r0 = r1[r0]
            if (r0 != r2) goto L_0x0075
        L_0x0040:
            int r0 = r7.pos
            int r1 = r7.length
            if (r0 >= r1) goto L_0x0055
            char[] r5 = r7.chars
            char r6 = r5[r0]
            if (r6 == r3) goto L_0x0055
            char r5 = r5[r0]
            if (r5 != r2) goto L_0x0055
            int r0 = r0 + 1
            r7.pos = r0
            goto L_0x0040
        L_0x0055:
            char[] r5 = r7.chars
            char r5 = r5[r0]
            if (r5 != r3) goto L_0x005e
            if (r0 == r1) goto L_0x005e
            goto L_0x0075
        L_0x005e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r4)
            java.lang.String r2 = r7.f1218dn
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0075:
            int r0 = r7.pos
            int r0 = r0 + 1
            r7.pos = r0
        L_0x007b:
            int r0 = r7.pos
            int r1 = r7.length
            if (r0 >= r1) goto L_0x008c
            char[] r1 = r7.chars
            char r1 = r1[r0]
            if (r1 != r2) goto L_0x008c
            int r0 = r0 + 1
            r7.pos = r0
            goto L_0x007b
        L_0x008c:
            int r0 = r7.end
            int r1 = r7.beg
            int r2 = r0 - r1
            r3 = 4
            if (r2 <= r3) goto L_0x00ce
            char[] r2 = r7.chars
            int r4 = r1 + 3
            char r4 = r2[r4]
            r5 = 46
            if (r4 != r5) goto L_0x00ce
            char r4 = r2[r1]
            r5 = 79
            if (r4 == r5) goto L_0x00ab
            char r4 = r2[r1]
            r5 = 111(0x6f, float:1.56E-43)
            if (r4 != r5) goto L_0x00ce
        L_0x00ab:
            int r4 = r1 + 1
            char r4 = r2[r4]
            r5 = 73
            if (r4 == r5) goto L_0x00bb
            int r4 = r1 + 1
            char r4 = r2[r4]
            r5 = 105(0x69, float:1.47E-43)
            if (r4 != r5) goto L_0x00ce
        L_0x00bb:
            int r4 = r1 + 2
            char r4 = r2[r4]
            r5 = 68
            if (r4 == r5) goto L_0x00cb
            int r4 = r1 + 2
            char r2 = r2[r4]
            r4 = 100
            if (r2 != r4) goto L_0x00ce
        L_0x00cb:
            int r1 = r1 + r3
            r7.beg = r1
        L_0x00ce:
            java.lang.String r1 = new java.lang.String
            char[] r2 = r7.chars
            int r3 = r7.beg
            int r0 = r0 - r3
            r1.<init>(r2, r3, r0)
            return r1
        L_0x00d9:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r4)
            java.lang.String r2 = r7.f1218dn
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.DistinguishedNameParser.nextAT():java.lang.String");
    }

    private String quotedAV() {
        int i = this.pos + 1;
        this.pos = i;
        this.beg = i;
        this.end = i;
        while (true) {
            int i2 = this.pos;
            if (i2 != this.length) {
                char[] cArr = this.chars;
                if (cArr[i2] == '\"') {
                    this.pos = i2 + 1;
                    while (true) {
                        int i3 = this.pos;
                        if (i3 >= this.length || this.chars[i3] != ' ') {
                            char[] cArr2 = this.chars;
                            int i4 = this.beg;
                        } else {
                            this.pos = i3 + 1;
                        }
                    }
                    char[] cArr22 = this.chars;
                    int i42 = this.beg;
                    return new String(cArr22, i42, this.end - i42);
                }
                if (cArr[i2] == '\\') {
                    cArr[this.end] = getEscaped();
                } else {
                    cArr[this.end] = cArr[i2];
                }
                this.pos++;
                this.end++;
            } else {
                throw new IllegalStateException("Unexpected end of DN: " + this.f1218dn);
            }
        }
    }

    private String hexAV() {
        int i;
        int i2 = this.pos;
        if (i2 + 4 < this.length) {
            this.beg = i2;
            this.pos = i2 + 1;
            while (true) {
                i = this.pos;
                if (i == this.length) {
                    break;
                }
                char[] cArr = this.chars;
                if (cArr[i] == '+' || cArr[i] == ',' || cArr[i] == ';') {
                    break;
                } else if (cArr[i] == ' ') {
                    this.end = i;
                    this.pos = i + 1;
                    while (true) {
                        int i3 = this.pos;
                        if (i3 >= this.length || this.chars[i3] != ' ') {
                            break;
                        }
                        this.pos = i3 + 1;
                    }
                } else {
                    if (cArr[i] >= 'A' && cArr[i] <= 'F') {
                        cArr[i] = (char) (cArr[i] + ' ');
                    }
                    this.pos = i + 1;
                }
            }
            this.end = i;
            int i4 = this.end;
            int i5 = this.beg;
            int i6 = i4 - i5;
            if (i6 < 5 || (i6 & 1) == 0) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f1218dn);
            }
            int i7 = i6 / 2;
            byte[] bArr = new byte[i7];
            int i8 = i5 + 1;
            for (int i9 = 0; i9 < i7; i9++) {
                bArr[i9] = (byte) getByte(i8);
                i8 += 2;
            }
            return new String(this.chars, this.beg, i6);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f1218dn);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0095, code lost:
        r1 = r8.chars;
        r2 = r8.beg;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a1, code lost:
        return new java.lang.String(r1, r2, r8.cur - r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String escapedAV() {
        /*
            r8 = this;
            int r0 = r8.pos
            r8.beg = r0
            r8.end = r0
        L_0x0006:
            int r0 = r8.pos
            int r1 = r8.length
            if (r0 < r1) goto L_0x0019
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.chars
            int r2 = r8.beg
            int r3 = r8.end
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L_0x0019:
            char[] r1 = r8.chars
            char r2 = r1[r0]
            r3 = 44
            r4 = 43
            r5 = 59
            r6 = 32
            if (r2 == r6) goto L_0x005e
            if (r2 == r5) goto L_0x0053
            r5 = 92
            if (r2 == r5) goto L_0x0040
            if (r2 == r4) goto L_0x0053
            if (r2 == r3) goto L_0x0053
            int r2 = r8.end
            int r3 = r2 + 1
            r8.end = r3
            char r3 = r1[r0]
            r1[r2] = r3
            int r0 = r0 + 1
            r8.pos = r0
            goto L_0x0006
        L_0x0040:
            int r0 = r8.end
            int r2 = r0 + 1
            r8.end = r2
            char r2 = r8.getEscaped()
            r1[r0] = r2
            int r0 = r8.pos
            int r0 = r0 + 1
            r8.pos = r0
            goto L_0x0006
        L_0x0053:
            java.lang.String r0 = new java.lang.String
            int r2 = r8.beg
            int r3 = r8.end
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L_0x005e:
            int r2 = r8.end
            r8.cur = r2
            int r0 = r0 + 1
            r8.pos = r0
            int r0 = r2 + 1
            r8.end = r0
            r1[r2] = r6
        L_0x006c:
            int r0 = r8.pos
            int r1 = r8.length
            if (r0 >= r1) goto L_0x0085
            char[] r2 = r8.chars
            char r7 = r2[r0]
            if (r7 != r6) goto L_0x0085
            int r1 = r8.end
            int r7 = r1 + 1
            r8.end = r7
            r2[r1] = r6
            int r0 = r0 + 1
            r8.pos = r0
            goto L_0x006c
        L_0x0085:
            if (r0 == r1) goto L_0x0095
            char[] r1 = r8.chars
            char r2 = r1[r0]
            if (r2 == r3) goto L_0x0095
            char r2 = r1[r0]
            if (r2 == r4) goto L_0x0095
            char r0 = r1[r0]
            if (r0 != r5) goto L_0x0006
        L_0x0095:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.chars
            int r2 = r8.beg
            int r3 = r8.cur
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.DistinguishedNameParser.escapedAV():java.lang.String");
    }

    private char getEscaped() {
        int i = this.pos + 1;
        this.pos = i;
        if (i != this.length) {
            char[] cArr = this.chars;
            char c = cArr[i];
            if (!(c == ' ' || c == '%' || c == '\\' || c == '_' || c == '\"' || c == '#')) {
                switch (c) {
                    case '*':
                    case '+':
                    case ',':
                        break;
                    default:
                        switch (c) {
                            case ';':
                            case '<':
                            case '=':
                            case '>':
                                break;
                            default:
                                return getUTF8();
                        }
                }
            }
            return cArr[i];
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f1218dn);
    }

    private char getUTF8() {
        int i;
        int i2;
        int i3 = getByte(this.pos);
        this.pos++;
        if (i3 < 128) {
            return (char) i3;
        }
        if (i3 < 192 || i3 > 247) {
            return '?';
        }
        if (i3 <= 223) {
            i2 = i3 & 31;
            i = 1;
        } else if (i3 <= 239) {
            i = 2;
            i2 = i3 & 15;
        } else {
            i = 3;
            i2 = i3 & 7;
        }
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = this.pos + 1;
            this.pos = i5;
            if (i5 == this.length || this.chars[i5] != '\\') {
                return '?';
            }
            int i6 = i5 + 1;
            this.pos = i6;
            int i7 = getByte(i6);
            this.pos++;
            if ((i7 & 192) != 128) {
                return '?';
            }
            i2 = (i2 << 6) + (i7 & 63);
        }
        return (char) i2;
    }

    private int getByte(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 < this.length) {
            char[] cArr = this.chars;
            char c = cArr[i];
            if (c >= '0' && c <= '9') {
                i2 = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                i2 = c - 'W';
            } else if (c < 'A' || c > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f1218dn);
            } else {
                i2 = c - '7';
            }
            char c2 = cArr[i4];
            if (c2 >= '0' && c2 <= '9') {
                i3 = c2 - '0';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i3 = c2 - 'W';
            } else if (c2 < 'A' || c2 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f1218dn);
            } else {
                i3 = c2 - '7';
            }
            return (i2 << 4) + i3;
        }
        throw new IllegalStateException("Malformed DN: " + this.f1218dn);
    }

    public String findMostSpecific(String str) {
        String str2;
        this.pos = 0;
        this.beg = 0;
        this.end = 0;
        this.cur = 0;
        this.chars = this.f1218dn.toCharArray();
        String nextAT = nextAT();
        if (nextAT == null) {
            return null;
        }
        do {
            int i = this.pos;
            if (i == this.length) {
                return null;
            }
            char c = this.chars[i];
            if (c != '\"') {
                str2 = c != '#' ? (c == '+' || c == ',' || c == ';') ? "" : escapedAV() : hexAV();
            } else {
                str2 = quotedAV();
            }
            if (str.equalsIgnoreCase(nextAT)) {
                return str2;
            }
            int i2 = this.pos;
            if (i2 >= this.length) {
                return null;
            }
            char[] cArr = this.chars;
            if (cArr[i2] == ',' || cArr[i2] == ';' || cArr[i2] == '+') {
                this.pos = i2 + 1;
                nextAT = nextAT();
            } else {
                throw new IllegalStateException("Malformed DN: " + this.f1218dn);
            }
        } while (nextAT != null);
        throw new IllegalStateException("Malformed DN: " + this.f1218dn);
    }
}
