package be.heh.kotlindbsql.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(UserRecord::class)], version = 1)
abstract class MyDB : RoomDatabase()
{
    abstract fun userDao(): UserDao
}