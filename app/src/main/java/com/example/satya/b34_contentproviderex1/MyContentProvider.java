package com.example.satya.b34_contentproviderex1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    private MyHelper myHelper ;
    //uri to table mapping logic

    private  static UriMatcher uriMatcher = new UriMatcher(-1) ;//-1 - will show something like 404 error,if user write anything for wrng msg
    static
    {
        uriMatcher.addURI("com.techpalle.B34","studenttable",1);
    }

    public class MyHelper extends SQLiteOpenHelper{

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, name, factory, version, errorHandler);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table student(_id integer primary key, sname text, ssub text);");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
    return  0;
    }

    @Override
    public String getType(Uri uri) {
     return  null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri))
        {
            case 1:
                //means clients are asking to insert into student table
                SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
                sqLiteDatabase.insert("student",null,values);
                break;
            default:
                //means invalid table
                break;
        }
        return null;
       }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        myHelper = new MyHelper(getContext(), "techpalle.db", null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri))
        {
            case 1:
                //means clients is requesting to read from student table
                Cursor c = null;
                SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
                c= sqLiteDatabase.query("student",null,null,null,null,null,null);
                return  c;
        }
        return  null;
        }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }
}
