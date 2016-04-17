package ua.kiev.home.sergiosiniy.cookyourself;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    private static final String COOK_DB_NAME="cook_it_yourself";
    private static final String RECIPES_TABLE ="recipes";
    private static final int DB_VERSION=1;
    private static final String DB_CREATE="create table "+RECIPES_TABLE
                +"(id integer primary key autoincrement,"
                + "recipe_name varchar(50) not null,"
                + "recipe_text text not null,"
                + "recipe_img blob);";

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, COOK_DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB log", "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}