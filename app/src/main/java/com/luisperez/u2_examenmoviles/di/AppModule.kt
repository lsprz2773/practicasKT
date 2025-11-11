package com.luisperez.u2_examenmoviles.di

import android.content.Context
import androidx.room.Room
import com.luisperez.u2_examenmoviles.room.PersonDatabase
import com.luisperez.u2_examenmoviles.room.PersonDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{
    @Provides
    @Singleton
    fun providePersonDatabase(@ApplicationContext context: Context): PersonDatabase{
        return Room.databaseBuilder(
            context,
            PersonDatabase::class.java,
            "person_database"
        ).fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun providePersonDao(database: PersonDatabase): PersonDatabaseDao{
        return database.personDao()
    }
}