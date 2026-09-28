    // Port of Node/FS/Sync.js. The plain helpers below are also used by the
    // callback API in Node.FS.Async; errors surface as Java exceptions.
    public static final class OpenFile {
        public final java.nio.channels.FileChannel channel;
        public final boolean append;
        public OpenFile(java.nio.channels.FileChannel channel, boolean append) {
            this.channel = channel;
            this.append = append;
        }
    }

    public static java.nio.file.Path __path(String path) {
        return java.nio.file.Paths.get(path);
    }

    public static RuntimeException __failure(Throwable failure) {
        return failure instanceof RuntimeException ? (RuntimeException) failure : new RuntimeException(failure);
    }

    public static String __encoding(Object options) {
        if (options instanceof java.util.Map) {
            Object encoding = ((java.util.Map<String, Object>) options).get("encoding");
            if (encoding instanceof String) return (String) encoding;
        }
        return null;
    }

    public static byte[] __bytes(Object data, String encoding) {
        if (data instanceof __M$Node_Buffer.NodeBuffer) {
            return __M$Node_Buffer.__window((__M$Node_Buffer.NodeBuffer) data);
        }
        if (data instanceof String) {
            return __M$Node_Buffer.__decode((String) data, encoding == null ? "utf8" : encoding);
        }
        throw new RuntimeException("Unsupported file data");
    }

    public static void rename(String from, String to) {
        try {
            java.nio.file.Files.move(__path(from), __path(to), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static void truncate(String path, long length) {
        try (java.nio.channels.FileChannel channel = java.nio.channels.FileChannel.open(
                __path(path), java.nio.file.StandardOpenOption.WRITE)) {
            if (channel.size() > length) channel.truncate(length);
            else if (channel.size() < length) {
                channel.position(length - 1);
                channel.write(java.nio.ByteBuffer.wrap(new byte[]{0}));
            }
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static void chmod(String path, String mode) {
        try {
            int bits = Integer.parseInt(mode, 8);
            java.util.Set<java.nio.file.attribute.PosixFilePermission> permissions = java.util.EnumSet.noneOf(
                java.nio.file.attribute.PosixFilePermission.class);
            if ((bits & 0400) != 0) permissions.add(java.nio.file.attribute.PosixFilePermission.OWNER_READ);
            if ((bits & 0200) != 0) permissions.add(java.nio.file.attribute.PosixFilePermission.OWNER_WRITE);
            if ((bits & 0100) != 0) permissions.add(java.nio.file.attribute.PosixFilePermission.OWNER_EXECUTE);
            if ((bits & 0040) != 0) permissions.add(java.nio.file.attribute.PosixFilePermission.GROUP_READ);
            if ((bits & 0020) != 0) permissions.add(java.nio.file.attribute.PosixFilePermission.GROUP_WRITE);
            if ((bits & 0010) != 0) permissions.add(java.nio.file.attribute.PosixFilePermission.GROUP_EXECUTE);
            if ((bits & 0004) != 0) permissions.add(java.nio.file.attribute.PosixFilePermission.OTHERS_READ);
            if ((bits & 0002) != 0) permissions.add(java.nio.file.attribute.PosixFilePermission.OTHERS_WRITE);
            if ((bits & 0001) != 0) permissions.add(java.nio.file.attribute.PosixFilePermission.OTHERS_EXECUTE);
            java.nio.file.Files.setPosixFilePermissions(__path(path), permissions);
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static void mkdir(String path, boolean recursive) {
        try {
            if (recursive) java.nio.file.Files.createDirectories(__path(path));
            else java.nio.file.Files.createDirectory(__path(path));
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static void rm(String path, boolean recursive, boolean force) {
        java.nio.file.Path file = __path(path);
        try {
            if (!java.nio.file.Files.exists(file, java.nio.file.LinkOption.NOFOLLOW_LINKS)) {
                if (force) return;
                throw new java.io.IOException("ENOENT: no such file or directory, rm '" + path + "'");
            }
            if (java.nio.file.Files.isDirectory(file, java.nio.file.LinkOption.NOFOLLOW_LINKS) && recursive) {
                java.nio.file.Files.walk(file)
                    .sorted(java.util.Comparator.reverseOrder())
                    .forEach(entry -> {
                        try { java.nio.file.Files.deleteIfExists(entry); }
                        catch (java.io.IOException failure) { throw __failure(failure); }
                    });
            } else {
                java.nio.file.Files.delete(file);
            }
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static Object[] readdir(String path) {
        try {
            return java.nio.file.Files.list(__path(path))
                .map(entry -> (Object) entry.getFileName().toString())
                .sorted()
                .toArray();
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static Object readFile(String path, Object options) {
        try {
            byte[] bytes = java.nio.file.Files.readAllBytes(__path(path));
            String encoding = __encoding(options);
            return encoding == null ? __M$Node_Buffer.__wrap(bytes) : __M$Node_Buffer.__encode(bytes, encoding);
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static void writeFile(String path, Object data, Object options, boolean append) {
        try {
            byte[] bytes = __bytes(data, __encoding(options));
            java.nio.file.Path file = __path(path);
            if (append && java.nio.file.Files.exists(file)) {
                java.nio.file.Files.write(file, bytes, java.nio.file.StandardOpenOption.APPEND);
            } else {
                java.nio.file.Files.write(file, bytes, java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.TRUNCATE_EXISTING, java.nio.file.StandardOpenOption.WRITE);
            }
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static void unlink(String path) {
        try {
            java.nio.file.Files.delete(__path(path));
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static void copyFile(String from, String to, int mode) {
        try {
            if ((mode & 1) != 0) {
                java.nio.file.Files.copy(__path(from), __path(to));
            } else {
                java.nio.file.Files.copy(__path(from), __path(to), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static void link(String from, String to) {
        try {
            java.nio.file.Files.createLink(__path(to), __path(from));
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static void symlink(String target, String path, String type) {
        try {
            java.nio.file.Files.createSymbolicLink(__path(path), __path(target));
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static String readlink(String path) {
        try {
            return java.nio.file.Files.readSymbolicLink(__path(path)).toString();
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static String realpath(String path) {
        try {
            return __path(path).toRealPath().toString();
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static String mkdtemp(String prefix) {
        try {
            java.nio.file.Path prefixPath = __path(prefix);
            java.nio.file.Path parent = prefixPath.getParent() != null ? prefixPath.getParent() : __path(".");
            String name = prefixPath.getFileName() != null ? prefixPath.getFileName().toString() : "tmp";
            return java.nio.file.Files.createTempDirectory(parent, name).toString();
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static void utimes(String path, long atime, long mtime) {
        try {
            java.nio.file.Files.setLastModifiedTime(__path(path),
                java.nio.file.attribute.FileTime.fromMillis(mtime * 1000L));
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static Object access(String path, int mode) {
        java.nio.file.Path file = __path(path);
        if (!java.nio.file.Files.exists(file)) {
            throw new RuntimeException("ENOENT: no such file or directory, access '" + path + "'");
        }
        if ((mode & 2) != 0 && !java.nio.file.Files.isWritable(file)) {
            throw new RuntimeException("EACCES: permission denied, access '" + path + "'");
        }
        if ((mode & 1) != 0 && !java.nio.file.Files.isExecutable(file)) {
            throw new RuntimeException("EACCES: permission denied, access '" + path + "'");
        }
        if ((mode & 4) != 0 && !java.nio.file.Files.isReadable(file)) {
            throw new RuntimeException("EACCES: permission denied, access '" + path + "'");
        }
        return null;
    }

    public static OpenFile open(String path, String flags, Object mode) {
        try {
            boolean read = flags.contains("+") || flags.startsWith("r");
            boolean write = flags.startsWith("w") || flags.startsWith("a") || flags.contains("+");
            boolean append = flags.startsWith("a");
            java.util.List<java.nio.file.OpenOption> options = new java.util.ArrayList<>();
            if (read) options.add(java.nio.file.StandardOpenOption.READ);
            if (write) options.add(java.nio.file.StandardOpenOption.WRITE);
            if (flags.startsWith("w")) {
                options.add(java.nio.file.StandardOpenOption.CREATE);
                if (flags.startsWith("wx")) options.add(java.nio.file.StandardOpenOption.CREATE_NEW);
                else options.add(java.nio.file.StandardOpenOption.TRUNCATE_EXISTING);
            }
            if (flags.startsWith("a")) {
                options.add(java.nio.file.StandardOpenOption.CREATE);
                if (flags.startsWith("ax")) options.add(java.nio.file.StandardOpenOption.CREATE_NEW);
            }
            if (options.isEmpty()) options.add(java.nio.file.StandardOpenOption.READ);
            return new OpenFile(java.nio.channels.FileChannel.open(__path(path),
                options.toArray(new java.nio.file.OpenOption[0])), append);
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static int read(Object fdObj, Object bufferObj, int offset, int length, Object position) {
        OpenFile fd = (OpenFile) fdObj;
        __M$Node_Buffer.NodeBuffer buffer = (__M$Node_Buffer.NodeBuffer) bufferObj;
        try {
            java.nio.ByteBuffer view = java.nio.ByteBuffer.wrap(buffer.array, buffer.offset + offset, length);
            int count = position == null ? fd.channel.read(view) : fd.channel.read(view, ((Number) position).longValue());
            return Math.max(0, count);
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static int write(Object fdObj, Object bufferObj, int offset, int length, Object position) {
        OpenFile fd = (OpenFile) fdObj;
        __M$Node_Buffer.NodeBuffer buffer = (__M$Node_Buffer.NodeBuffer) bufferObj;
        try {
            java.nio.ByteBuffer view = java.nio.ByteBuffer.wrap(buffer.array, buffer.offset + offset, length);
            long at = position != null ? ((Number) position).longValue() : fd.append ? fd.channel.size() : fd.channel.position();
            int count = fd.channel.write(view, at);
            if (position == null) fd.channel.position(at + count);
            return count;
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static void fsync(Object fd) {
        try {
            ((OpenFile) fd).channel.force(true);
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static void close(Object fd) {
        try {
            ((OpenFile) fd).channel.close();
        } catch (java.io.IOException failure) { throw __failure(failure); }
    }

    public static Object accessImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (mode) ->
            (java.util.function.Supplier<Object>) () -> access((String) path, ((Number) mode).intValue());

    public static Object copyFileImpl = (java.util.function.Function<Object, Object>) (from) ->
        (java.util.function.Function<Object, Object>) (to) ->
        (java.util.function.Function<Object, Object>) (mode) ->
            (java.util.function.Supplier<Object>) () -> { copyFile((String) from, (String) to, ((Number) mode).intValue()); return null; };

    public static Object mkdtempImpl = (java.util.function.Function<Object, Object>) (prefix) ->
        (java.util.function.Function<Object, Object>) (options) ->
            (java.util.function.Supplier<Object>) () -> mkdtemp((String) prefix);

    public static Object renameSyncImpl = (java.util.function.Function<Object, Object>) (from) ->
        (java.util.function.Function<Object, Object>) (to) ->
            (java.util.function.Supplier<Object>) () -> { rename((String) from, (String) to); return null; };

    public static Object truncateSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (length) ->
            (java.util.function.Supplier<Object>) () -> { truncate((String) path, ((Number) length).longValue()); return null; };

    public static Object chownSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (uid) ->
        (java.util.function.Function<Object, Object>) (gid) ->
            (java.util.function.Supplier<Object>) () -> null;

    public static Object chmodSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (mode) ->
            (java.util.function.Supplier<Object>) () -> { chmod((String) path, (String) mode); return null; };

    public static Object statSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Supplier<Object>) () -> __M$Node_FS_Stats.__stats((String) path, true);

    public static Object lstatSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Supplier<Object>) () -> __M$Node_FS_Stats.__stats((String) path, false);

    public static Object linkSyncImpl = (java.util.function.Function<Object, Object>) (from) ->
        (java.util.function.Function<Object, Object>) (to) ->
            (java.util.function.Supplier<Object>) () -> { link((String) from, (String) to); return null; };

    public static Object symlinkSyncImpl = (java.util.function.Function<Object, Object>) (target) ->
        (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (type) ->
            (java.util.function.Supplier<Object>) () -> { symlink((String) target, (String) path, (String) type); return null; };

    public static Object readlinkSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Supplier<Object>) () -> readlink((String) path);

    public static Object realpathSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (cache) ->
            (java.util.function.Supplier<Object>) () -> realpath((String) path);

    public static Object unlinkSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Supplier<Object>) () -> { unlink((String) path); return null; };

    public static Object rmdirSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (options) ->
            (java.util.function.Supplier<Object>) () -> { rm((String) path, true, false); return null; };

    public static Object rmSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (optionsObj) ->
            (java.util.function.Supplier<Object>) () -> {
                java.util.Map<String, Object> options = (java.util.Map<String, Object>) optionsObj;
                rm((String) path, Boolean.TRUE.equals(options.get("recursive")), Boolean.TRUE.equals(options.get("force")));
                return null;
            };

    public static Object mkdirSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (optionsObj) ->
            (java.util.function.Supplier<Object>) () -> {
                java.util.Map<String, Object> options = (java.util.Map<String, Object>) optionsObj;
                mkdir((String) path, Boolean.TRUE.equals(options.get("recursive")));
                return null;
            };

    public static Object readdirSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Supplier<Object>) () -> readdir((String) path);

    public static Object utimesSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (atime) ->
        (java.util.function.Function<Object, Object>) (mtime) ->
            (java.util.function.Supplier<Object>) () -> {
                utimes((String) path, ((Number) atime).longValue(), ((Number) mtime).longValue());
                return null;
            };

    public static Object readFileSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (options) ->
            (java.util.function.Supplier<Object>) () -> readFile((String) path, options);

    public static Object writeFileSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (data) ->
        (java.util.function.Function<Object, Object>) (options) ->
            (java.util.function.Supplier<Object>) () -> { writeFile((String) path, data, options, false); return null; };

    public static Object appendFileSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (data) ->
        (java.util.function.Function<Object, Object>) (options) ->
            (java.util.function.Supplier<Object>) () -> { writeFile((String) path, data, options, true); return null; };

    public static Object existsSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Supplier<Object>) () -> java.nio.file.Files.exists(__path((String) path));

    public static Object openSyncImpl = (java.util.function.Function<Object, Object>) (path) ->
        (java.util.function.Function<Object, Object>) (flags) ->
        (java.util.function.Function<Object, Object>) (mode) ->
            (java.util.function.Supplier<Object>) () -> open((String) path, (String) flags, mode);

    public static Object readSyncImpl = (java.util.function.Function<Object, Object>) (fd) ->
        (java.util.function.Function<Object, Object>) (buffer) ->
        (java.util.function.Function<Object, Object>) (offset) ->
        (java.util.function.Function<Object, Object>) (length) ->
        (java.util.function.Function<Object, Object>) (position) ->
            (java.util.function.Supplier<Object>) () -> read(fd, buffer, ((Number) offset).intValue(), ((Number) length).intValue(), position);

    public static Object writeSyncImpl = (java.util.function.Function<Object, Object>) (fd) ->
        (java.util.function.Function<Object, Object>) (buffer) ->
        (java.util.function.Function<Object, Object>) (offset) ->
        (java.util.function.Function<Object, Object>) (length) ->
        (java.util.function.Function<Object, Object>) (position) ->
            (java.util.function.Supplier<Object>) () -> write(fd, buffer, ((Number) offset).intValue(), ((Number) length).intValue(), position);

    public static Object fsyncSyncImpl = (java.util.function.Function<Object, Object>) (fd) ->
        (java.util.function.Supplier<Object>) () -> { fsync(fd); return null; };

    public static Object closeSyncImpl = (java.util.function.Function<Object, Object>) (fd) ->
        (java.util.function.Supplier<Object>) () -> { close(fd); return null; };
