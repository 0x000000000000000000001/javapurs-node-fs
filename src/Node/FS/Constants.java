    // Node's fs constants.
    public static Object f_OK = 0;
    public static Object r_OK = 4;
    public static Object w_OK = 2;
    public static Object x_OK = 1;
    public static Object copyFile_EXCL = 1;
    public static Object copyFile_FICLONE = 2;
    public static Object copyFile_FICLONE_FORCE = 4;

    public static Object appendCopyMode = (java.util.function.Function<Object, Object>) (l) ->
        (java.util.function.Function<Object, Object>) (r) ->
            ((Number) l).intValue() | ((Number) r).intValue();
