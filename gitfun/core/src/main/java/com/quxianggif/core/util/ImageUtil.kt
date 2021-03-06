package com.quxianggif.core.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.bumptech.glide.gifdecoder.GifDecoder
import com.bumptech.glide.gifdecoder.GifHeaderParser
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser
import com.quxianggif.core.GifFun
import com.quxianggif.core.extension.logDebug
import com.quxianggif.core.extension.logWarn
import java.io.*
import java.lang.Exception

/**
 * 图片工具类，提供一些应用在图片上的工具方法
 * @author xuehj
 * @since 2019-04-23
 */
object ImageUtil {
    private val TAG = "ImageUtil"

    /**
     * 获取传入图片的类型
     * @param imagePath 图片的路径
     * @return 以枚举格式返回图片的类型，如果传入的图片格式有问题，或者图片不存在，一律返回null
     */
    fun getImageType(imagePath: String) : ImageHeaderParser.ImageType? {
        var file = File(imagePath)
        var fis : FileInputStream? = null
        try {
            fis = FileInputStream(file)
            return ImageHeaderParser(fis).type
        } catch (e: Exception) {
            logWarn(TAG, e.message.toString(), e)
        } finally {
            try {
                fis?.close()
            } catch (e: IOException) {
                logWarn(TAG, e.message.toString(), e)
            }
        }
        return null
    }

    /**
     * 判断传入的图片是否是一张GIF图片
     * @param imagePath 图片的路径
     * @return 如果是GIF图片返回true，否则返回false
     */
    fun isGif(imagePath: String): Boolean {
        var imageType = getImageType(imagePath)
        return imageType != null && imageType == ImageHeaderParser.ImageType.GIF
    }

    /**
     * 判断GIF图片的格式是否完全正确
     * @param imagePath 图片路径
     * @return GIF图片的格式完全正确返回true，否则返回false
     */
    fun isGifValid(imagePath: String): Boolean {
        return try {
            var gifHeader = GifHeaderParser().setData(getGifBytes(imagePath)).parseHeader()
            gifHeader.status == GifDecoder.STATUS_OK
        } catch (e: Exception) {
            false
        }
    }

    /**
     * 获取传入图片的大小
     * @param imagePath 图片的路径
     * @return 返回图片的大小，以字节为单位
     */
    fun getImageSize(imagePath: String): Long {
        val file = File(imagePath)
        return if (file.exists()) {
            file.length()
        } else 0
    }

    /**
     * 将Bitmap对象存储到本地缓存文件夹下面
     * @param bitmap 要存储的Bitmap对象
     * @return 存储后的文件路径
     */
    fun saveBitmapAsFile(bitmap: Bitmap): String? {
        var filePath = GifFun.getContext().cacheDir.path + "/" + System.currentTimeMillis() + ".jpg"
        var file = File(filePath)
        if (file.exists()) {
            file.delete()
        }
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fos)
            fos.flush()
            return file.path
        } catch (e: Exception) {
            logWarn(TAG, e.message.toString(), e)
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                logWarn(TAG, e.message.toString(), e)
            }
        }
        return null
    }

    fun insertImageToSystem(context: Context, imagePath: String): Uri? {
        var uri: Uri? = null
        try {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DATA, imagePath)
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/gif")
            val cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    arrayOf(MediaStore.Images.Media._ID), "${MediaStore.Images.Media.DATA} = ?",
                    arrayOf(imagePath), null)
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                uri = Uri.parse("content://media/external/images/media/$id")
                logDebug("image id is $id")
            } else {
                uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            }
            cursor?.close()
        } catch (e: Exception) {
            logWarn(TAG, e.message, e)
        }
        return uri
    }

    private fun getGifBytes(imagePath: String): ByteArray {
        var fis: FileInputStream? = null
        var bufferSize = 16384
        var buffer = ByteArrayOutputStream(bufferSize)
        try {
            val file = File(imagePath)
            fis = FileInputStream(file)
            val data = ByteArray(bufferSize)
            var bytes = fis.read(data)
            while (bytes >= 0) {
                buffer.write(data, 0, bytes)
                bytes = fis.read(data)
            }
        } catch (e: Exception) {
            logWarn(TAG, e.message.toString(), e)
        } finally {
            try {
                if (fis != null) {
                    fis.close()
                }
            } catch (e: IOException) {
                logWarn(TAG, e.message.toString(), e)
            }
        }
        return buffer.toByteArray()
    }
}