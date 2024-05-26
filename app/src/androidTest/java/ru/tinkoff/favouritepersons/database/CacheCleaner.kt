package ru.tinkoff.favouritepersons.database

import java.io.File

object CacheCleaner {
    fun deleteDir(dir: File): Boolean {
        if (dir.isDirectory) {
            val children = dir.list()
            if (children != null) {
                for (child in children) {
                    val success = deleteDir(File(dir, child))
                    if (!success) {
                        return false
                    }
                }
            }
        }
        // The directory is now empty so delete it
        return dir.delete()
    }
}