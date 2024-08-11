package be.heh.kotlindbsql.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM UserTable")
    fun get(): List<UserRecord>
    @Query("SELECT * FROM UserTable WHERE login = :login")
    fun get(login: String): UserRecord
    @Insert
    fun insertUser(vararg listCategories: UserRecord)
    @Update
    fun updatePersonne(task: UserRecord)
    @Delete
    fun deletePersonne(task: UserRecord)
}