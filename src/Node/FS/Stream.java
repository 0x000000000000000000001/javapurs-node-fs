    // Port of Node/FS/Stream.js over the eager streams of Node.Stream.
    private static __M$Node_Stream.ReadableStream __readable(String path) {
        __M$Node_Stream.ReadableStream stream = new __M$Node_Stream.ReadableStream();
        try {
            stream.data = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(path));
        } catch (java.io.IOException failure) {
            throw new RuntimeException(failure);
        }
        return stream;
    }

    private static __M$Node_Stream.ReadableStream __readableFd(Object fd) {
        __M$Node_Stream.ReadableStream stream = new __M$Node_Stream.ReadableStream();
        try {
            java.nio.channels.FileChannel channel = ((__M$Node_FS_Sync.OpenFile) fd).channel;
            java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate((int) channel.size());
            channel.read(buffer, 0);
            stream.data = buffer.array();
        } catch (java.io.IOException failure) {
            throw new RuntimeException(failure);
        }
        return stream;
    }

    private static __M$Node_Stream.WritableStream __writable(String path) {
        __M$Node_Stream.WritableStream stream = new __M$Node_Stream.WritableStream();
        stream.path = path;
        return stream;
    }

    public static Object createReadStreamImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Supplier<Object>) () -> __readable((String) path);

    public static Object createReadStreamOptsImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (options) ->
            (java.util.function.Supplier<Object>) () -> __readable((String) path);

    public static Object fdCreateReadStreamImpl = (java.util.function.Function<Object, Object>) (fd) ->
        (java.util.function.Supplier<Object>) () -> __readableFd(fd);

    public static Object fdCreateReadStreamOptsImpl = (java.util.function.Function<Object, Object>) (fd) ->
        (java.util.function.Function<Object, Object>) (options) ->
            (java.util.function.Supplier<Object>) () -> __readableFd(fd);

    public static Object createWriteStreamImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Supplier<Object>) () -> __writable((String) path);

    public static Object createWriteStreamOptsImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (options) ->
            (java.util.function.Supplier<Object>) () -> __writable((String) path);

    public static Object fdCreateWriteStreamImpl = (java.util.function.Function<Object, Object>) (fd) ->
        (java.util.function.Supplier<Object>) () -> new __M$Node_Stream.WritableStream();

    public static Object fdCreateWriteStreamOptsImpl = (java.util.function.Function<Object, Object>) (fd) ->
        (java.util.function.Function<Object, Object>) (options) ->
            (java.util.function.Supplier<Object>) () -> new __M$Node_Stream.WritableStream();
