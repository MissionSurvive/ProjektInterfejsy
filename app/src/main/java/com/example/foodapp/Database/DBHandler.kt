package com.example.foodapp.Database

import android.content.ContentValues
import android.content.Context
import android.database.CursorWindow
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


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

        val query2 = ("CREATE TABLE " + CART_TABLE_NAME + " ("
                + CART_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CART_PRICE + " INTEGER,"
                + CART_QUANTITY + " INTEGER,"
                + CART_SIZE + " TEXT,"
                + CART_KETCHUP + " INTEGER,"
                + CART_GARLIC + " INTEGER,"
                + FOOD_ID + " INTEGER,"
                + "FOREIGN KEY (" + FOOD_ID + ") REFERENCES " + TABLE_NAME + "(" + ID_COL + ")"
                + ")")
        db.execSQL(query2)
    }

    fun addToCart(
        cartPrice: Int,
        cartQuantity: Int,
        cartSize: String,
        isKetchup: Int,
        isGarlicSauce: Int,
        foodId: Int
    ) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(CART_PRICE, cartPrice)
        values.put(CART_QUANTITY, cartQuantity)
        values.put(CART_SIZE, cartSize)
        values.put(CART_KETCHUP, isKetchup)
        values.put(CART_GARLIC, isGarlicSauce)
        values.put(FOOD_ID, foodId)

        db.insert(CART_TABLE_NAME, null, values)
        db.close()
    }

    fun getCartContents(): List<Cart> {
        val cartList = mutableListOf<Cart>()
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
        val query = "SELECT c.*, f.$FOOD_IMAGE, f.$FOOD_NAME " +
                "FROM $CART_TABLE_NAME c " +
                "JOIN $TABLE_NAME f ON c.$FOOD_ID = f.$ID_COL"

        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val price = cursor.getInt(cursor.getColumnIndexOrThrow(CART_PRICE))
            val quantity = cursor.getInt(cursor.getColumnIndexOrThrow(CART_QUANTITY))
            val size = cursor.getString(cursor.getColumnIndexOrThrow(CART_SIZE))
            val isKetchup = cursor.getInt(cursor.getColumnIndexOrThrow(CART_KETCHUP))
            val isGarlic = cursor.getInt(cursor.getColumnIndexOrThrow(CART_GARLIC))
            val foodId = cursor.getInt(cursor.getColumnIndexOrThrow(FOOD_ID))
            val image = cursor.getBlob((cursor.getColumnIndexOrThrow(FOOD_IMAGE)))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(FOOD_NAME))
            val content = Cart(price, quantity, size, isKetchup, isGarlic, foodId, image, name)
            cartList.add(content)
        }
        cursor.close()
        db.close()
        return cartList
    }

    fun getCartTotalPrice(): Int {
        var totalPrice = 0
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
        val projection = arrayOf(CART_PRICE)
        val cursor = db.query(CART_TABLE_NAME, projection, null, null, null, null, null)

        while (cursor.moveToNext()) {
            totalPrice += cursor.getInt(cursor.getColumnIndexOrThrow(CART_PRICE))
        }
        cursor.close()
        db.close()
        return totalPrice
    }

    fun clearCart() {
        val db = writableDatabase
        db.delete(CART_TABLE_NAME, null, null)
        db.close()
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

    fun getOneFood(id: Int): List<OneFood> {
        val foodList = mutableListOf<OneFood>()
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
        val projection = arrayOf(ID_COL, FOOD_IMAGE, FOOD_NAME, SMALL_PRICE, SMALL_KCAL, SMALL_PORTION, MEDIUM_PRICE, MEDIUM_KCAL, MEDIUM_PORTION, BIG_PRICE, BIG_KCAL, BIG_PORTION, KETCHUP, GARLIC, CATEGORY)
        val selection = "$ID_COL = ?"
        val selectionArgs = arrayOf(id.toString())
        val cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COL))
            val image = cursor.getBlob(cursor.getColumnIndexOrThrow(FOOD_IMAGE))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(FOOD_NAME))
            val sprice = cursor.getInt(cursor.getColumnIndexOrThrow(SMALL_PRICE))
            val skcal = cursor.getInt(cursor.getColumnIndexOrThrow(SMALL_KCAL))
            val sportion = cursor.getInt(cursor.getColumnIndexOrThrow(SMALL_PORTION))
            val mprice = cursor.getInt(cursor.getColumnIndexOrThrow(MEDIUM_PRICE))
            val mkcal = cursor.getInt(cursor.getColumnIndexOrThrow(MEDIUM_KCAL))
            val mportion = cursor.getInt(cursor.getColumnIndexOrThrow(MEDIUM_PORTION))
            val bprice = cursor.getInt(cursor.getColumnIndexOrThrow(BIG_PRICE))
            val bkcal = cursor.getInt(cursor.getColumnIndexOrThrow(BIG_KCAL))
            val bportion = cursor.getInt(cursor.getColumnIndexOrThrow(BIG_PORTION))
            val isKetchup = cursor.getInt(cursor.getColumnIndexOrThrow(KETCHUP))
            val isGarlic = cursor.getInt(cursor.getColumnIndexOrThrow(GARLIC))
            val category = cursor.getString(cursor.getColumnIndexOrThrow(CATEGORY))

            val food = OneFood(id, image, name, sprice, skcal, sportion, mprice, mkcal, mportion, bprice, bkcal, bportion, isKetchup, isGarlic, category)
            foodList.add(food)
        }
        cursor.close()
        db.close()
        return foodList
    }

    fun updateOneFood(
        id: Int,
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
        foodCategory: String): Int
    {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(ID_COL, id)
            put(FOOD_IMAGE, image)
            put(FOOD_NAME, foodName)
            put(SMALL_PRICE, smallPrice)
            put(SMALL_KCAL, smallKcal)
            put(SMALL_PORTION, smallPortion)
            put(MEDIUM_PRICE, mediumPrice)
            put(MEDIUM_KCAL, mediumKcal)
            put(MEDIUM_PORTION, mediumPortion)
            put(BIG_PRICE, bigPrice)
            put(BIG_KCAL, bigKcal)
            put(BIG_PORTION, bigPortion)
            put(KETCHUP, isKetchup)
            put(GARLIC, isGarlicSauce)
            put(CATEGORY, foodCategory)
        }
        val selection = "$ID_COL = ?"
        val selectionArgs = arrayOf(id.toString())
        val result = db.update(TABLE_NAME, values, selection, selectionArgs)
        db.close()
        return result
    }

    fun deleteOneFood(id: Int): Int {
        val db = writableDatabase
        val selection = "$ID_COL = ?"
        val selectionArgs = arrayOf(id.toString())
        val deletedRows = db.delete(TABLE_NAME, selection, selectionArgs)
        db.close()
        return deletedRows
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
        val projection = arrayOf(ID_COL, FOOD_IMAGE, FOOD_NAME)
        val selection = "$CATEGORY = ?"
        val selectionArgs = arrayOf(category.toString())
        val cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null)

        while (cursor.moveToNext()) {
            val foodId = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COL))
            val image = cursor.getBlob(cursor.getColumnIndexOrThrow(FOOD_IMAGE))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(FOOD_NAME))
            val food = Foods(foodId, image, name)
            foodList.add(food)
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

        private const val CART_TABLE_NAME = "CART"

        private const val CART_ID_COL = "ID"

        private const val CART_PRICE = "TOTAL"

        private const val CART_QUANTITY = "FQUANTITY"

        private const val CART_SIZE = "FSIZE"

        private const val CART_KETCHUP = "KETCHUP"

        private const val CART_GARLIC = "GARLIC"

        private const val FOOD_ID = "FID"
    }
}