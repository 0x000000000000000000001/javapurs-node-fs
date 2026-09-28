    // Port of Node/FS/Async.js. Operations run on the calling thread and the
    // callback is invoked inline; the JVM already has a file system layer, so
    // the asynchronous bookkeeping of Node is not needed.
    private static void __callback(Object callbackObj, Object error, Object value) {
        java.util.function.Function<Object, Object> callback = (java.util.function.Function<Object, Object>) callbackObj;
        Object effect = ((java.util.function.Function<Object, Object>) callback.apply(error)).apply(value);
        ((java.util.function.Supplier<Object>) effect).get();
    }

    private static void __ok(Object callback, Object value) { __callback(callback, null, value); }

    private static void __fail(Object callback, Throwable failure) {
        __callback(callback, __M$Node_FS_Sync.__failure(failure), null);
    }

    public static Object accessImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (mode) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.access((String) path, ((Number) mode).intValue()); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object copyFileImpl = (java.util.function.Function<Object, Object>) (from) ->
        (java.util.function.Function<Object, Object>) (to) ->
        (java.util.function.Function<Object, Object>) (mode) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.copyFile((String) from, (String) to, ((Number) mode).intValue()); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object mkdtempImpl = (java.util.function.Function<Object, Object>) (prefix) ->
        (java.util.function.Function<Object, Object>) (options) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __ok(callback, __M$Node_FS_Sync.mkdtemp((String) prefix)); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object renameImpl = (java.util.function.Function<Object, Object>) (from) ->
        (java.util.function.Function<Object, Object>) (to) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.rename((String) from, (String) to); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object truncateImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (length) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.truncate((String) path, ((Number) length).longValue()); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object chownImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (uid) ->
        (java.util.function.Function<Object, Object>) (gid) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> { __ok(callback, null); return null; };

    public static Object chmodImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (mode) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.chmod((String) path, (String) mode); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object statImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __ok(callback, __M$Node_FS_Stats.__stats((String) path, true)); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object lstatImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __ok(callback, __M$Node_FS_Stats.__stats((String) path, false)); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object linkImpl = (java.util.function.Function<Object, Object>) (from) ->
        (java.util.function.Function<Object, Object>) (to) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.link((String) from, (String) to); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object symlinkImpl = (java.util.function.Function<Object, Object>) (target) ->
        (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (type) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.symlink((String) target, (String) path, (String) type); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object readlinkImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __ok(callback, __M$Node_FS_Sync.readlink((String) path)); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object realpathImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (cache) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __ok(callback, __M$Node_FS_Sync.realpath((String) path)); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object unlinkImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.unlink((String) path); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object rmdirImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (options) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.rm((String) path, true, false); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object rmImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (optionsObj) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try {
                    java.util.Map<String, Object> options = (java.util.Map<String, Object>) optionsObj;
                    __M$Node_FS_Sync.rm((String) path, Boolean.TRUE.equals(options.get("recursive")), Boolean.TRUE.equals(options.get("force")));
                    __ok(callback, null);
                } catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object mkdirImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (optionsObj) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try {
                    java.util.Map<String, Object> options = (java.util.Map<String, Object>) optionsObj;
                    __M$Node_FS_Sync.mkdir((String) path, Boolean.TRUE.equals(options.get("recursive")));
                    __ok(callback, null);
                } catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object readdirImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __ok(callback, __M$Node_FS_Sync.readdir((String) path)); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object utimesImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (atime) ->
        (java.util.function.Function<Object, Object>) (mtime) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.utimes((String) path, ((Number) atime).longValue(), ((Number) mtime).longValue()); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object readFileImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (options) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __ok(callback, __M$Node_FS_Sync.readFile((String) path, options)); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object writeFileImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (data) ->
        (java.util.function.Function<Object, Object>) (options) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.writeFile((String) path, data, options, false); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object appendFileImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (data) ->
        (java.util.function.Function<Object, Object>) (options) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.writeFile((String) path, data, options, true); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object openImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (flags) ->
        (java.util.function.Function<Object, Object>) (mode) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __ok(callback, __M$Node_FS_Sync.open((String) path, (String) flags, mode)); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object readImpl = (java.util.function.Function<Object, Object>) (fd) ->
        (java.util.function.Function<Object, Object>) (buffer) ->
        (java.util.function.Function<Object, Object>) (offset) ->
        (java.util.function.Function<Object, Object>) (length) ->
        (java.util.function.Function<Object, Object>) (position) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __ok(callback, __M$Node_FS_Sync.read(fd, buffer, ((Number) offset).intValue(), ((Number) length).intValue(), position)); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object writeImpl = (java.util.function.Function<Object, Object>) (fd) ->
        (java.util.function.Function<Object, Object>) (buffer) ->
        (java.util.function.Function<Object, Object>) (offset) ->
        (java.util.function.Function<Object, Object>) (length) ->
        (java.util.function.Function<Object, Object>) (position) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __ok(callback, __M$Node_FS_Sync.write(fd, buffer, ((Number) offset).intValue(), ((Number) length).intValue(), position)); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };

    public static Object closeImpl = (java.util.function.Function<Object, Object>) (fd) ->
        (java.util.function.Function<Object, Object>) (callback) ->
            (java.util.function.Supplier<Object>) () -> {
                try { __M$Node_FS_Sync.close(fd); __ok(callback, null); }
                catch (Throwable failure) { __fail(callback, failure); }
                return null;
            };
