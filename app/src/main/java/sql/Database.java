package sql;

/*
http://www.androidtutorialshub.com/android-login-and-register-with-sqlite-database-tutorial/
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.group1.telemedicine.Patient;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VS = 1;
    //Database Name
    private static final String DATABASE_NAME = "PatientManager.db";
    //Patient Table Name
    private static final String TABLE_PATIENT = "patient";
    //Patient Table/Columns Names
    private static final String COLUMN_PATIENT_ID = "PATIENT_id";
    private static final String COLUMN_PATIENT_NAME = "PATIENT_name";
    private static final String COLUMN_PATIENT_EMAIL = "PATIENT_email";
    private static final String COLUMN_PATIENT_PASSWORD = "PATIENT_password";
    private static final String COLUMN_PATIENT_GENDER = "PATIENT_gender";
    private static final String COLUMN_PATIENT_ADDRESS = "PATIENT_address";
    private static final String COLUMN_PATIENT_AGE = "PATIENT_age";
    private static final String LOG_TAG = "DatabaseCLASS";
    // SSN??
    //private static final String COLUMN_PATIENT_SSN = "PATIENT_ssn";

    // Create table sql query
    private String CREATE_PATIENT_TABLE = "CREATE TABLE " + TABLE_PATIENT + "("
            + COLUMN_PATIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PATIENT_NAME +
            " TEXT," + COLUMN_PATIENT_EMAIL + " TEXT," + COLUMN_PATIENT_PASSWORD + " TEXT," + COLUMN_PATIENT_GENDER
            + " TEXT," + COLUMN_PATIENT_ADDRESS + " TEXT," + COLUMN_PATIENT_AGE + " INTEGER" + ")";

    private String DROP_PATIENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PATIENT;

    //Constructor
    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VS);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_PATIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Drop PATIENT Table if it exists
        db.execSQL(DROP_PATIENT_TABLE);

        //Create tables again
        onCreate(db);
    }

    public void addPatient(Patient patient)
    {
        SQLiteDatabase patientDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT_NAME, patient.getPtName());
        values.put(COLUMN_PATIENT_EMAIL, patient.getPtEmail());
        values.put(COLUMN_PATIENT_PASSWORD, patient.getPtPassword());
        values.put(COLUMN_PATIENT_GENDER, patient.getPtGender());

        //Insert Row
        patientDB.insert(TABLE_PATIENT, null, values);
        patientDB.close();
    }

    public List<Patient> getAllPatients()
    {
        String [] columns = {
                COLUMN_PATIENT_ID,
                COLUMN_PATIENT_EMAIL,
                COLUMN_PATIENT_NAME,
                COLUMN_PATIENT_PASSWORD,
                COLUMN_PATIENT_GENDER};

        String sortOrder = COLUMN_PATIENT_NAME + "ASC";
        List<Patient> patientList = new ArrayList<Patient>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PATIENT,
                columns,null,null,null,null,sortOrder);

        if (cursor.moveToFirst())
        {
            do {
                Patient patient = new Patient();
                patient.setPtID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ID))));
                patient.setPtName(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_NAME)));
                patient.setPtEmail(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_EMAIL)));
                patient.setPtPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_PASSWORD)));
                patient.setPtAddress(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ADDRESS)));
                patient.setPtAge(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_AGE))));
                // Adding PATIENT record to list
                patientList.add(patient);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return patient list
        return  patientList;
    }

    // Update Patient DB
    public void updatePatient(Patient patient)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT_NAME, patient.getPtName());
        values.put(COLUMN_PATIENT_EMAIL, patient.getPtEmail());
        values.put(COLUMN_PATIENT_PASSWORD, patient.getPtPassword());
        //Maybe add others?

        // Update Row
        db.update(TABLE_PATIENT, values, COLUMN_PATIENT_ID + " = ?",
                new String[]{String.valueOf(patient.getPtID())});
        // Close DB
        db.close();
    }

    // Delete Patient from DB
    public void deletePatient(Patient patient)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete User Record by their ptID
        db.delete(TABLE_PATIENT, COLUMN_PATIENT_ID + " = ?",
                new String[]{String.valueOf(patient.getPtID())});
        db.close();
    }

    // Checks if user exists
    public boolean checkUserExist(String username)
    {
        String [] columns = {COLUMN_PATIENT_ID};
        SQLiteDatabase db = this.getReadableDatabase();

        // Selection criteria
        String selection = COLUMN_PATIENT_EMAIL + " = ?";

        // Selection Arguments
        String[] selectionArgs = {username};

        // Query patient with condition

        Cursor cursor = db.query(TABLE_PATIENT,
                columns, selection, selectionArgs, null, null, null);
        int cursorCounter = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCounter > 0)
        {
            return true;
        }else if (cursorCounter <= 0)
        {
            return false;
        }else
        {
            Log.e(LOG_TAG, "There was an error with the cursorCounter");
        }
        return false;
    }

    public boolean checkUserExist(String username, String password)
    {
        String [] columns = {COLUMN_PATIENT_ID};
        SQLiteDatabase db = this.getReadableDatabase();

        // Selection criteria
        String selection = COLUMN_PATIENT_NAME + " = ?" + " AND " + COLUMN_PATIENT_PASSWORD +" = ?";

        // Selection Arguments
        String[] selectionArgs = {username, password};

        // Query patient with condition

        Cursor cursor = db.query(TABLE_PATIENT,
                columns, selection, selectionArgs, null, null, null);
        int cursorCounter = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCounter > 0)
        {
            return true;
        }else if (cursorCounter <= 0)
        {
            return false;
        }else
        {
            Log.e(LOG_TAG, "There was an error with the cursorCounter");
        }
        return false;
    }

    // TODO
    /*
    ENCRYPT DATA (PASSWORD, SSN?)
     */
}
