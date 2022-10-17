package com.pbarthuel.bodywellbeing.data.vendors.local.room.article

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pbarthuel.bodywellbeing.app.model.article.Article
import com.pbarthuel.bodywellbeing.data.vendors.local.room.article.entities.ArticleEntity
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.exercise.entities.CondenseExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM ArticleEntity")
    fun getAllExercises(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM ArticleEntity WHERE id == :articleId")
    fun getArticleFromId(articleId: String): Flow<ArticleEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createExercise(articleEntity: ArticleEntity)

    @Query("UPDATE ArticleEntity SET is_favorite = :isFavorite WHERE id == :articleId")
    suspend fun updateIsFavorite(articleId: String, isFavorite: Boolean)

    @Query("SELECT * FROM ArticleEntity WHERE is_favorite == 1")
    fun getFavoritesArticles(): Flow<List<ArticleEntity>>
}