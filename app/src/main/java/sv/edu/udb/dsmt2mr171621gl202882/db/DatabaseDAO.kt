package sv.edu.udb.dsmt2mr171621gl202882.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DatabaseDAO {
    @Insert
    suspend fun saveHistory(history: HistoryEntity)

    @Insert
    suspend fun saveProduct(product: ProductEntity)

    @Query("SELECT * FROM product ORDER BY name ASC")
    suspend fun getProducts() : List<ProductEntity>
}