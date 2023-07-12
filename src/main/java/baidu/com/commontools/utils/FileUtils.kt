package baidu.com.commontools.utils

import okio.buffer
import okio.sink
import okio.source
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

@Throws(FileNotFoundException::class, IOException::class)
fun copyFile(sourceFile: File?, targetFile: File?) {
    if (sourceFile == null || targetFile == null) {
        return
    }

    sourceFile.source().use { source ->
        targetFile.sink().buffer().use {
            it.writeAll(source)
        }
    }
}

@Throws(FileNotFoundException::class, IOException::class)
fun readFileAsString(file: File?): String? {
    if (file == null) {
        return null
    }

    file.source().use { source ->
        source.buffer().use {
            return it.readByteString().utf8()
        }
    }
}

@Throws(FileNotFoundException::class, IOException::class)
fun readFileAndReadAsLine(dir: String): MutableList<String> {
    val result = mutableListOf<String>()
    File(dir).source().use { source ->
        source.buffer().use {
            while (true) {
                val line = it.readUtf8Line() ?: break
                result.add(line)
            }
        }
    }
    return result
}

@Throws(FileNotFoundException::class, IOException::class)
fun writeFile(file: File?, content: String?) {
    if (file == null || content == null) {
        return
    }

    file.sink().use { sink ->
        sink.buffer().writeUtf8(content)
    }
}