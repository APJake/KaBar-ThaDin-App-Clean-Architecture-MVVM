package com.apjake.kabarthadin.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apjake.kabarthadin.data.local.dao.NewsDao
import com.apjake.kabarthadin.data.local.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1
)
abstract class NewsDatabase: RoomDatabase() {
    abstract val dao: NewsDao
}