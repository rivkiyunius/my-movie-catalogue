package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.source.local.room.CreditsMovieDao
import com.example.data.source.local.room.DetailMovieDao
import com.example.data.source.local.room.DiscoverMovieDao
import com.example.data.source.local.room.FavoriteMovieDao
import com.example.data.source.local.room.MovieDatabase
import com.example.data.source.local.room.NowPlayingMovieDao
import com.example.data.source.local.room.PopularMovieDao
import com.example.data.source.local.room.SearchMovieDao
import com.example.data.source.local.room.TopRatedMovieDao
import com.example.data.source.local.room.UpcomingMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("testdb".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(context, MovieDatabase::class.java, "Movie.db")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideDiscoverMovieDao(database: MovieDatabase): DiscoverMovieDao =
        database.discoverMovieDao()

    @Provides
    fun providePopularMovieDao(database: MovieDatabase): PopularMovieDao =
        database.popularMovieDao()

    @Provides
    fun provideNowPlayingMovieDao(database: MovieDatabase): NowPlayingMovieDao =
        database.nowPlayingMovieDao()

    @Provides
    fun provideUpcomingMovieDao(database: MovieDatabase): UpcomingMovieDao =
        database.upcomingMovieDao()

    @Provides
    fun provideTopRatedMovieDao(database: MovieDatabase): TopRatedMovieDao =
        database.topRatedMovieDao()

    @Provides
    fun provideDetailMovieDao(database: MovieDatabase): DetailMovieDao =
        database.detailMovieDao()

    @Provides
    fun provideSearchMovieDao(database: MovieDatabase): SearchMovieDao =
        database.searchMovieDao()

    @Provides
    fun provideCreditsMovieDao(database: MovieDatabase): CreditsMovieDao =
        database.creditsMovieDao()

    @Provides
    fun provideFavoriteMovieDao(database: MovieDatabase): FavoriteMovieDao =
        database.favoriteMovieDao()
}