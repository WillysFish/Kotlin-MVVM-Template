package com.willy.kotlin_mvvm_template.data.repositories

import com.willy.kotlin_mvvm_template.data.db.daos.LiveTourDao
import com.willy.kotlin_mvvm_template.data.db.entities.LiveTourEntity

/**
 * Created by Willy on 2019/11/7.
 */
class LiveTourRepository private constructor(private val liveTourDao: LiveTourDao) {

    fun getLiveTours() = liveTourDao.getLiveTours()

    fun addLiveTour(entity: LiveTourEntity) = liveTourDao.addLiveTour(entity)

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: LiveTourRepository? = null

        fun getInstance(liveTourDao: LiveTourDao) =
            instance
                ?: synchronized(this) {
                instance
                    ?: LiveTourRepository(
                        liveTourDao
                    ).also { instance = it }
            }
    }
}