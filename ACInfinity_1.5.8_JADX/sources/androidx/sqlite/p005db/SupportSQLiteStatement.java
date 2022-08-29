package androidx.sqlite.p005db;

/* renamed from: androidx.sqlite.db.SupportSQLiteStatement */
public interface SupportSQLiteStatement extends SupportSQLiteProgram {
    void execute();

    long executeInsert();

    int executeUpdateDelete();

    long simpleQueryForLong();

    String simpleQueryForString();
}
