package androidpro.com.kfeedreader

import android.net.Uri

data class Item(val titulo: String, val autor: String, val data: Long, val link: Uri, val imagem: String)