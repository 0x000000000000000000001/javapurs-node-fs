    // Port of Node/FS/Stats.js. The JVM exposes the basic attributes; the
    // device/inode/owner fields are not available and stay at zero.
    public static final class Stats {
        public double dev, ino, mode, nlink, uid, gid, rdev, size, blkSize, blocks;
        public double atimeMs, mtimeMs, ctimeMs, birthtimeMs;
        public boolean directory, file, symbolicLink, blockDevice, characterDevice, fifo, socket;
    }

    public static Stats __stats(String path, boolean followLinks) {
        try {
            java.nio.file.Path file = java.nio.file.Paths.get(path);
            java.nio.file.LinkOption[] links = followLinks
                ? new java.nio.file.LinkOption[0]
                : new java.nio.file.LinkOption[]{ java.nio.file.LinkOption.NOFOLLOW_LINKS };
            java.nio.file.attribute.BasicFileAttributes attrs =
                java.nio.file.Files.readAttributes(file, java.nio.file.attribute.BasicFileAttributes.class, links);
            Stats stats = new Stats();
            stats.size = attrs.size();
            stats.directory = attrs.isDirectory();
            stats.file = attrs.isRegularFile();
            stats.symbolicLink = attrs.isSymbolicLink();
            stats.atimeMs = attrs.lastAccessTime().toMillis();
            stats.mtimeMs = attrs.lastModifiedTime().toMillis();
            stats.ctimeMs = attrs.lastModifiedTime().toMillis();
            stats.birthtimeMs = attrs.creationTime().toMillis();
            stats.nlink = 1;
            stats.blocks = (double) (attrs.size() / 512);
            stats.blkSize = 4096;
            try {
                java.util.Set<java.nio.file.attribute.PosixFilePermission> permissions = java.nio.file.Files
                    .getPosixFilePermissions(file, links);
                int bits = 0;
                for (java.nio.file.attribute.PosixFilePermission permission : permissions) {
                    switch (permission) {
                        case OWNER_READ: bits |= 0400; break;
                        case OWNER_WRITE: bits |= 0200; break;
                        case OWNER_EXECUTE: bits |= 0100; break;
                        case GROUP_READ: bits |= 0040; break;
                        case GROUP_WRITE: bits |= 0020; break;
                        case GROUP_EXECUTE: bits |= 0010; break;
                        case OTHERS_READ: bits |= 0004; break;
                        case OTHERS_WRITE: bits |= 0002; break;
                        case OTHERS_EXECUTE: bits |= 0001; break;
                    }
                }
                stats.mode = bits;
            } catch (UnsupportedOperationException unsupported) { }
            stats.mode += stats.file ? 0100000 : stats.directory ? 0040000 : stats.symbolicLink ? 0120000 : 0;
            return stats;
        } catch (java.io.IOException failure) {
            throw new RuntimeException(failure);
        }
    }

    public static Object showStatsObj = (java.util.function.Function<Object, Object>) (statsObj) -> {
        Stats stats = (Stats) statsObj;
        return "Stats { dev: " + (long) stats.dev + ", mode: " + (long) stats.mode
            + ", size: " + (long) stats.size + ", atimeMs: " + stats.atimeMs
            + ", mtimeMs: " + stats.mtimeMs + ", ctimeMs: " + stats.ctimeMs + " }";
    };

    public static Object isBlockDeviceImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).blockDevice;
    public static Object isCharacterDeviceImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).characterDevice;
    public static Object isDirectoryImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).directory;
    public static Object isFIFOImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).fifo;
    public static Object isFileImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).file;
    public static Object isSocketImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).socket;
    public static Object isSymbolicLinkImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).symbolicLink;
    public static Object devImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).dev;
    public static Object inodeImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).ino;
    public static Object modeImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).mode;
    public static Object nlinkImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).nlink;
    public static Object uidImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).uid;
    public static Object gidImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).gid;
    public static Object rdevImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).rdev;
    public static Object sizeImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).size;
    public static Object blkSizeImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).blkSize;
    public static Object blocksImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).blocks;
    public static Object accessedTimeMsImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).atimeMs;
    public static Object modifiedTimeMsImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).mtimeMs;
    public static Object statusChangedTimeMsImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).ctimeMs;
    public static Object birthtimeMsImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).birthtimeMs;
    public static Object accessedTimeImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).atimeMs;
    public static Object modifiedTimeImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).mtimeMs;
    public static Object statusChangedTimeImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).ctimeMs;
    public static Object birthTimeImpl = (java.util.function.Function<Object, Object>) (stats) -> ((Stats) stats).birthtimeMs;
