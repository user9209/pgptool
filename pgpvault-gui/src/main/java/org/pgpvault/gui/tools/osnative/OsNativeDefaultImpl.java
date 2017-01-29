package org.pgpvault.gui.tools.osnative;

public class OsNativeDefaultImpl implements OsNative {
	@Override
	public String[] getCommandLineArguments(String[] fallBackTo) {
		return fallBackTo;
	}
}
