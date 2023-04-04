package io.pantheon.flare

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.annotation.RestrictTo

@RestrictTo(RestrictTo.Scope.LIBRARY)
class FlareContextProvider : ContentProvider() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        var flareContext: Context? = null
    }


    override fun onCreate(): Boolean {
        flareContext = context
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? { return null }

    override fun getType(uri: Uri): String? { return null }

    override fun insert(uri: Uri, values: ContentValues?): Uri? { return null }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int { return 0 }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int { return 0 }
}
