package com.example.rick.imtpmd.Database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by juliarijsbergen on 19-06-17.
 */

public class DatabaseInfo {

    public class CourseTables {
        //Naam van de tabel
        public static final String user = "user";
    }

    public class CourseColumn {
        public static final String vak_name = "vak_name";
        public static final String user_id = "user_id";
        public static final String grade = "grade";
        public static final String passed = "passed";
    }

}
