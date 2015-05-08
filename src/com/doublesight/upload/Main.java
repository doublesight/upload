package com.doublesight.upload;

import com.doublesight.upload.db.DatabaseSingleton;

public final class Main {

    public static void main(final String... args) {
        initializeDatabase();
    }

    private static void initializeDatabase() {
        DatabaseSingleton.getInstance();
    }
}
