package com.example.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room.dao.BookDao
import com.example.room.dao.UserDao
import com.example.room.entity.Book
import com.example.room.entity.User

/**
 * 多个实体类之间用逗号隔开即可
 * AppDatabase类必须继承自RoomDatabase类，并且一定要使用abstract关键字将它声明成抽象类，
 * 然后提供相应的抽象方法，用于获取之前编写的Dao 的实例。我们只需要进行方法声明就可以了，具体的方法实现是由Room 在底层自动完成的
 */
/**升级数据库：① version +1 ；有新的表时需要在entities中添加**/
@Database(version = 3, entities = [User::class,Book::class])
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    /**升级数据库：有新的表时需要添加**/
    abstract fun bookDao(): BookDao

    companion object {

        /**升级数据库：②编写升级的SQL**/
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book (id integer primary key autoincrement not null," +
                                     "name text not null,pages integer not null)")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null default 'unknown'")
            }
        }

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            /**
             * databaseBuilder()方法接收3个参数，
             * * 第一个参数一定要使用applicationContext，而不能使用普通的context，否则容易出现内存泄漏的情况，关于applicationContext的详细内容我们将会在第14 章中学习。
             * * 第二个参数是AppDatabase的Class类型，
             * * 第三个参数是数据库名，这些都比较简单。
             * 最后调用build()方法完成构建，并将创建出来的实例赋值给instance变量，然后返回当前实例即可
             */
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,"app_database")
                /**升级数据库：③将升级的SQL添加**/
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build().apply {
                    instance = this
            }
        }
    }
}