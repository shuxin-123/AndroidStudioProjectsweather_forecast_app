package com.animee.forecast.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
    private UserDbHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new UserDbHelper(context);
    }

    // 添加用户（注册）
    public long addUser(String account, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserDbHelper.COLUMN_ACCOUNT, account);
        values.put(UserDbHelper.COLUMN_PASSWORD, password);

        // 插入数据，返回行ID，如果失败返回-1
        long result = db.insert(UserDbHelper.TABLE_USERS, null, values);
        db.close();
        return result;
    }

    // 验证用户（登录）
    public boolean validateUser(String account, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {UserDbHelper.COLUMN_ID};
        String selection = UserDbHelper.COLUMN_ACCOUNT + " = ? AND " +
                UserDbHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {account, password};

        Cursor cursor = db.query(
                UserDbHelper.TABLE_USERS,
                columns,
                selection,
                selectionArgs,
                null, null, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }

    // 检查账号是否已存在
    public boolean isAccountExists(String account) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {UserDbHelper.COLUMN_ID};
        String selection = UserDbHelper.COLUMN_ACCOUNT + " = ?";
        String[] selectionArgs = {account};

        Cursor cursor = db.query(
                UserDbHelper.TABLE_USERS,
                columns,
                selection,
                selectionArgs,
                null, null, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }
    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}
