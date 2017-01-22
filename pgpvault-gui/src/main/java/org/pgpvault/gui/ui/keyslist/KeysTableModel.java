package org.pgpvault.gui.ui.keyslist;

import org.pgpvault.gui.app.Messages;
import org.pgpvault.gui.encryption.api.dto.Key;
import org.pgpvault.gui.encryption.api.dto.KeyData;
import org.pgpvault.gui.encryption.api.dto.KeyInfo;
import org.pgpvault.gui.ui.tools.KeyInfoRendering;

import ru.skarpushin.swingpm.modelprops.table.LightweightTableModel;

public class KeysTableModel implements LightweightTableModel<Key<KeyData>> {
	public static final int COLUMN_USER = 0;
	public static final int COLUMN_KEY_ID = 1;
	public static final int COLUMN_ALGORITHM = 2;
	public static final int COLUMN_KEY_TYPE = 3;
	public static final int COLUMN_CREATED_ON = 4;
	public static final int COLUMN_EXPIRES_AT = 5;

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case COLUMN_USER:
			return Messages.get("term.user");
		case COLUMN_KEY_ID:
			return Messages.get("term.keyId");
		case COLUMN_KEY_TYPE:
			return Messages.get("term.keyType");
		case COLUMN_ALGORITHM:
			return Messages.get("term.keyAlgorithm");
		case COLUMN_CREATED_ON:
			return Messages.get("term.createdOn");
		case COLUMN_EXPIRES_AT:
			return Messages.get("term.expiresAt");
		default:
			throw new IllegalArgumentException("Wrong column index: " + columnIndex);
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public Object getValueAt(Key<KeyData> r, int columnIndex) {
		if (r == null) {
			return "";
		}

		KeyInfo info = r.getKeyInfo();

		switch (columnIndex) {
		case COLUMN_USER:
			return info.getUser();
		case COLUMN_KEY_ID:
			return info.getKeyId();
		case COLUMN_KEY_TYPE:
			return KeyInfoRendering.keyTypeToString(info);
		case COLUMN_ALGORITHM:
			return info.getKeyAlgorithm();
		case COLUMN_CREATED_ON:
			return KeyInfoRendering.dateToString(info.getCreatedOn());
		case COLUMN_EXPIRES_AT:
			return KeyInfoRendering.dateToString(info.getExpiresAt());
		default:
			throw new IllegalArgumentException("Wrong column index: " + columnIndex);
		}
	}
}
