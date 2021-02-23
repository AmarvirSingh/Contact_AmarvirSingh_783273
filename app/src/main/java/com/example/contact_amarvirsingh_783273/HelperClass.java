package com.example.contact_amarvirsingh_783273;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HelperClass extends SQLiteOpenHelper {


    private final String tableName = "phoneBook";
    private final String fName = "f_name";
    private final String lName = "l_name";
    private final String Email = "email";
    private final String Phone = "phone";
    private final String Address = "address";
    private final String Id = "id";


    public HelperClass(@Nullable Context context) {
        super(context, "Contact_Database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + fName + " TEXT NOT NULL  , " + lName + " TEXT NOT NULL," + Email + " TEXT NOT NULL," + Phone + " TEXT NOT NULL," + Address + " TEXT NOT NULL," + Id + " INTEGER NOT NULL CONSTRAINT contact_pk PRIMARY KEY AUTOINCREMENT);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }


    public long insertContact(String first, String last, String add, String number, String mail) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(fName, first);
        cv.put(lName, last);
        cv.put(Email, mail);
        cv.put(Phone, number);
        cv.put(Address, add);

        long result = db.insert(tableName, null, cv);

        return result;
    }

    public long deleteContact(int id) {
        SQLiteDatabase db = getWritableDatabase();
        long res = db.delete(tableName, Id + " = ?", new String[]{String.valueOf(id)});

        return res;


    }


    public ArrayList<ContactModelClass> getAllContacts() {
        SQLiteDatabase data = getReadableDatabase();
        ArrayList<ContactModelClass> allContact = new ArrayList<>();

        Cursor cursor = null;

        cursor = data.rawQuery("SELECT * FROM " + tableName, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String fName = cursor.getString(0);
                String lName = cursor.getString(1);
                String Email = cursor.getString(2);
                String Phone = cursor.getString(3);
                String Address = cursor.getString(4);
                int Id = cursor.getInt(5);

                ContactModelClass model = new ContactModelClass(Id, fName, lName, Address, Email, Phone);
                allContact.add(model);

            }
        }
        cursor.close();

        return allContact;
    }

    public ArrayList<ContactModelClass> getOneContact(int id) {
        SQLiteDatabase data = getReadableDatabase();
        ArrayList<ContactModelClass> contact = new ArrayList<>();


        Cursor cursor = null;

        ContactModelClass model = new ContactModelClass();
        cursor = data.rawQuery("SELECT * FROM " + tableName + " WHERE " + Id + " = ? ;", new String[]{String.valueOf(id)});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String fName = cursor.getString(0);
                String lName = cursor.getString(1);
                String Email = cursor.getString(2);
                String Phone = cursor.getString(3);
                String Address = cursor.getString(4);
                int Id = cursor.getInt(5);

                model = new ContactModelClass(Id, fName, lName, Address, Email, Phone);
                contact.add(model);
            }
        }
        cursor.close();

        return contact;

    }


    public long updateData(int ID, String Fname, String Lname, String email, String phone, String address) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(fName, Fname);
        values.put(lName, Lname);
        values.put(Email, email);
        values.put(Phone, phone);
        values.put(Address, address);


        long result = database.update(tableName, values, Id + " = ? ", new String[]{String.valueOf(ID)});

        return result;

    }
}
