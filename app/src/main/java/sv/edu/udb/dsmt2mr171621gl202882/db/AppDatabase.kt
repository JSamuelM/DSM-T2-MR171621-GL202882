package sv.edu.udb.dsmt2mr171621gl202882.db

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(entities = [
    ProductEntity::class,
    HistoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    val dbExecutor = Executors.newFixedThreadPool(3)

    abstract fun getDatabaseDao() : DatabaseDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "products_db"
                )
                    .addCallback(CALLBACK)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                for (i in 10 downTo 1 step 1) {
                    db.execSQL("INSERT INTO product (name, price) VALUES ('Producto ' + (i + 1), 9.99) ")
                }
            }
        }
    }
}