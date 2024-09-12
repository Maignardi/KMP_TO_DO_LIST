import com.android.identity.util.UUID

actual fun generateUUID(): String {
    return UUID.randomUUID().toString()
}