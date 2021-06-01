package com.example.flynparkmelb.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.flynparkmelb.model.History
import com.example.flynparkmelb.model.User
import java.util.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // create table sql query
    private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_FIRSTNAME + " TEXT,"+ COLUMN_USER_LASTNAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")")

    private val CREATE_HISTORY_TABLE = ("CREATE TABLE " + TABLE_HISTORY + "("
            + COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_HISTORY_USERID + " INTEGER,"
            + COLUMN_HISTORY_DATE + " TEXT,"+ COLUMN_HISTORY_TIME+ " TEXT,"
            + COLUMN_HISTORY_LON + " DOUBLE," + COLUMN_HISTORY_LAT + " DOUBLE" + ")")

    // drop table sql query
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"
    private val DROP_HISTORY_TABLE = "DROP TABLE IF EXISTS $TABLE_HISTORY"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
        db.execSQL(CREATE_HISTORY_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE)
        db.execSQL(DROP_HISTORY_TABLE)
        // Create tables again
        onCreate(db)
    }
    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    fun getAllUser(): List<User> {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID, COLUMN_USER_EMAIL, COLUMN_USER_NAME, COLUMN_USER_FIRSTNAME, COLUMN_USER_LASTNAME, COLUMN_USER_PASSWORD)
        // sorting orders
        val sortOrder = "$COLUMN_USER_NAME ASC"
        val userList = ArrayList<User>()
        val db = this.readableDatabase
        // query the user table
        val cursor = db.query(TABLE_USER, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder)         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val user = User(id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)).toInt(),
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)),
                    firstname = cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRSTNAME)),
                    lastname = cursor.getString(cursor.getColumnIndex(COLUMN_USER_LASTNAME)),
                    email = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)),
                    password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)))
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }
    /**
     * This method is to create user record
     *
     * @param user
     */
    fun addUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_FIRSTNAME, user.firstname)
        values.put(COLUMN_USER_LASTNAME, user.lastname)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        // Inserting Row
        db.insert(TABLE_USER, null, values)
        db.close()
    }
    /**
     * This method to update user record
     *
     * @param user
     */
    fun updateUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_FIRSTNAME, user.firstname)
        values.put(COLUMN_USER_LASTNAME, user.lastname)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        // updating row
        db.update(TABLE_USER, values, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString()))
        db.close()
    }
    /**
     * This method is to delete user record
     *
     * @param user
     */
    fun deleteUser(user: User) {
        val db = this.writableDatabase
        // delete user record by id
        db.delete(TABLE_USER, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString()))
        db.close()
    }
    /**
     * This method to check user exist or not
     *
     * @param username
     * @return true/false
     */
    fun checkUser(username: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_USER_NAME = ?"
        // selection argument
        val selectionArgs = arrayOf(username)
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_username = 'xxx';
         */
        val cursor = db.query(TABLE_USER, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }
    /**
     * This method to check user exist or not
     *
     * @param username
     * @param password
     * @return true/false
     */
    fun checkUser(username: String, password: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_USER_NAME = ? AND $COLUMN_USER_PASSWORD = ?"
        // selection arguments
        val selectionArgs = arrayOf(username, password)
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        val cursor = db.query(TABLE_USER, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0)
            return true
        return false
    }

    /**
     * This method is to fetch all user history and return the list of user history records by user ID
     *
     * @return list
     */
    fun getAllUserHistory(user_id: Int): List<History> {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_HISTORY_ID, COLUMN_HISTORY_USERID, COLUMN_HISTORY_DATE, COLUMN_HISTORY_TIME, COLUMN_HISTORY_LON, COLUMN_HISTORY_LAT)
        // sorting orders
        val sortOrder = "$COLUMN_HISTORY_DATE ASC"
        val userHistoryList = ArrayList<History>()
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_HISTORY_USERID = ?"
        // selection arguments
        val selectionArgs = arrayOf(user_id.toString())
        // query the user table
        val cursor = db.query(TABLE_HISTORY, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder)         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val user_history = History(id = cursor.getString(cursor.getColumnIndex(COLUMN_HISTORY_ID)).toInt(),
                    user_id = cursor.getString(cursor.getColumnIndex(COLUMN_HISTORY_USERID)).toInt(),
                    date = cursor.getString(cursor.getColumnIndex(COLUMN_HISTORY_DATE)),
                    time = cursor.getString(cursor.getColumnIndex(COLUMN_HISTORY_TIME)),
                    lat = cursor.getString(cursor.getColumnIndex(COLUMN_HISTORY_LAT)).toDouble(),
                    lon = cursor.getString(cursor.getColumnIndex(COLUMN_HISTORY_LON)).toDouble())
                userHistoryList.add(user_history)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userHistoryList
    }
    /**
     * This method is to create user history record
     *
     * @param history
     */
    fun addUser(history: History) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_HISTORY_USERID, history.user_id)
        values.put(COLUMN_HISTORY_DATE, history.date)
        values.put(COLUMN_HISTORY_TIME, history.time)
        values.put(COLUMN_HISTORY_LAT, history.lat)
        values.put(COLUMN_HISTORY_LON, history.lon)
        // Inserting Row
        db.insert(TABLE_HISTORY, null, values)
        db.close()
    }
    /**
     * This method to update user history record
     *
     * @param history
     */
    fun updateUserHistory(history: History) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_HISTORY_USERID, history.user_id)
        values.put(COLUMN_HISTORY_DATE, history.date)
        values.put(COLUMN_HISTORY_TIME, history.time)
        values.put(COLUMN_HISTORY_LAT, history.lat)
        values.put(COLUMN_HISTORY_LON, history.lon)
        // updating row
        db.update(TABLE_HISTORY, values, "$COLUMN_HISTORY_ID = ?",
            arrayOf(history.id.toString()))
        db.close()
    }
    /**
     * This method is to delete userhistory record
     *
     * @param history
     */
    fun deleteUserHistory(history: History) {
        val db = this.writableDatabase
        // delete user record by id
        db.delete(TABLE_HISTORY, "$COLUMN_HISTORY_ID = ?",
            arrayOf(history.id.toString()))
        db.close()
    }

    companion object {
        // Database Version
        private val DATABASE_VERSION = 1
        // Database Name
        private val DATABASE_NAME = "UserManager.db"
        // User table name
        private val TABLE_USER = "user"
        private val TABLE_HISTORY = "history"
        // User Table Columns names
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_FIRSTNAME = "user_firstname"
        private val COLUMN_USER_LASTNAME = "user_lastname"
        private val COLUMN_USER_EMAIL = "user_email"
        private val COLUMN_USER_PASSWORD = "user_password"

        // History Table Columns names
        private val COLUMN_HISTORY_ID = "history_id"
        private val COLUMN_HISTORY_USERID = "historyuser_id"
        private val COLUMN_HISTORY_DATE = "history_date"
        private val COLUMN_HISTORY_TIME = "history_time"
        private val COLUMN_HISTORY_LON = "history_lon"
        private val COLUMN_HISTORY_LAT = "history_lat"
    }
}