package org.pgpvault.gui.tools.osnative;

public class OsNativeFactory {
	private static OsNative INSTANCE;
	private static Object SYNC = new Object();

	public static OsNative build() {
		if (INSTANCE != null) {
			return INSTANCE;
		}

		synchronized (SYNC) {
			if (INSTANCE != null) {
				return INSTANCE;
			}

			INSTANCE = resolveInstanceForCurrentOs();
			return INSTANCE;
		}
	}

	private static OsNative resolveInstanceForCurrentOs() {
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			return new OsNativeWindowsImpl();
		}
		return new OsNativeDefaultImpl();
	}
}
