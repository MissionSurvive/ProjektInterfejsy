package com.example.foodapp

import android.content.ContentValues
import android.content.Context
import android.database.CursorWindow
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.Blob



class DBHandler
// creating a constructor for our database handler.
    (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    // below method is for creating a database by running a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // on below line we are creating an sqlite query and we are
        // setting our column names along with their data types.
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FOOD_IMAGE + " BLOB,"
                + FOOD_NAME + " TEXT,"
                + SMALL_PRICE + " INTEGER,"
                + SMALL_KCAL + " INTEGER,"
                + SMALL_PORTION + " INTEGER,"
                + MEDIUM_PRICE + " INTEGER,"
                + MEDIUM_KCAL + " INTEGER,"
                + MEDIUM_PORTION + " INTEGER,"
                + BIG_PRICE + " INTEGER,"
                + BIG_KCAL + " INTEGER,"
                + BIG_PORTION + " INTEGER,"
                + KETCHUP + " INTEGER,"
                + GARLIC + " INTEGER,"
                + CATEGORY + " TEXT)")
        // at last we are calling a exec sql method to execute above sql query
        db.execSQL(query)
    }
    // this method is use to add new course to our sqlite database.
    fun addNewFood(
        image: ByteArray?,
        foodName: String,
        smallPrice: Int,
        smallKcal: Int,
        smallPortion: Int,
        mediumPrice: Int,
        mediumKcal: Int,
        mediumPortion: Int,
        bigPrice: Int,
        bigKcal: Int,
        bigPortion: Int,
        isKetchup: Int,
        isGarlicSauce: Int,
        foodCategory: String
    ) {
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        val db = this.writableDatabase
        // on below line we are creating a
        // variable for content values.
        val values = ContentValues()
        // on below line we are passing all values
        // along with its key and value pair.
        values.put(FOOD_IMAGE, image)
        values.put(FOOD_NAME, foodName)
        values.put(SMALL_PRICE, smallPrice)
        values.put(SMALL_KCAL, smallKcal)
        values.put(SMALL_PORTION, smallPortion)
        values.put(MEDIUM_PRICE, mediumPrice)
        values.put(MEDIUM_KCAL, mediumKcal)
        values.put(MEDIUM_PORTION, mediumPortion)
        values.put(BIG_PRICE, bigPrice)
        values.put(BIG_KCAL, bigKcal)
        values.put(BIG_PORTION, bigPortion)
        values.put(KETCHUP, isKetchup)
        values.put(GARLIC, isGarlicSauce)
        values.put(CATEGORY, foodCategory)
        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values)
        // at last we are closing our
        // database after adding database.
        db.close()
    }



    fun getAllFoods(category: String): List<Foods> {
        val foodList = mutableListOf<Foods>()
        val db = readableDatabase
        try {
            // Increase the CursorWindow size using reflection
            val field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
            field.isAccessible = true
            field.set(null, 10 * 1024 * 1024) // Set the new size to 100MB
        } catch (e: Exception) {
            // Handle the exception
            e.printStackTrace()
        }
        val projection = arrayOf(FOOD_IMAGE, FOOD_NAME)
        val selection = "${CATEGORY} = ?"
        val selectionArgs = arrayOf(category.toString())
        val cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null)

        while (cursor.moveToNext()) {
            val image = cursor.getBlob(cursor.getColumnIndexOrThrow(FOOD_IMAGE))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(FOOD_NAME))
            val food = Foods(image, name)
            foodList.add(food)

            for(food in foodList) {
                println("Food Name: ${food.name}")
            }
        }
        cursor.close()
        db.close()
        return foodList
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    companion object {
        // creating a constant variables for our database.
        // below variable is for our database name.
        private const val DB_NAME = "FoodDB"

        // below int is our database version
        private const val DB_VERSION = 1

        // below variable is for our table name.
        private const val TABLE_NAME = "FOODS"

        // below variable is for our id column.
        private const val ID_COL = "ID"

        private const val FOOD_IMAGE = "IMAGE"

        private const val FOOD_NAME = "NAME"

        private const val SMALL_PRICE = "SPRICE"

        private const val SMALL_KCAL = "SKCAL"

        private const val SMALL_PORTION = "SPORTION"

        private const val MEDIUM_PRICE = "MPRICE"

        private const val MEDIUM_KCAL = "MKCAL"

        private const val MEDIUM_PORTION = "MPORTION"

        private const val BIG_PRICE = "BPRICE"

        private const val BIG_KCAL = "BKCAL"

        private const val BIG_PORTION = "BPORTION"

        private const val KETCHUP = "KETCHUP"

        private const val GARLIC = "GARLIC"

        private const val CATEGORY = "CATEGORY"
    }
}