package com.pbarthuel.bodywellbeing.data.vendors.local.room.article

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pbarthuel.bodywellbeing.data.vendors.local.room.article.entities.ArticleEntity

@Database(
    version = 2,
    entities = [(ArticleEntity::class)]
)
abstract class ArticlesDb : RoomDatabase() {
    abstract fun ArticleDao(): ArticleDao
}