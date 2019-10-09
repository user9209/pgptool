package org.pgptool.gui.ui.reencrypt;

import static org.pgptool.gui.app.Messages.text;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import org.pgptool.gui.app.MessageSeverity;
import org.pgptool.gui.encryption.api.KeyRingService;
import org.pgptool.gui.ui.tools.UiUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;

import ru.skarpushin.swingpm.EXPORT.base.LocalizedActionEx;

/**
 * This PM doesn't have its own view. It is rather director/orchestrator of the
 * whole process
 * 
 * @author sergeyk
 *
 */
public class ReEncryptRootPm {
	@Autowired
	private KeyRingService keyRingService;
	private ReEncryptRootHost host;

	public ReEncryptRootPm(ReEncryptRootHost host) {
		Preconditions.checkState(host != null, "ReEncryptRootPm requires no-null host");
		this.host = host;
	}

	public final Action reEncryptEntryPoint = new LocalizedActionEx("action.reEncrypt", this) {
		private static final long serialVersionUID = -4287339800729275242L;

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (!doWeHaveKeysToDecryptWith()) {
				return;
			}
		}
	};

	private boolean doWeHaveKeysToDecryptWith() {
		if (isThereAKeyForDecryption()) {
			return true;
		}
		UiUtils.messageBox(text("phrase.noKeysForDecryption"), text("term.attention"), MessageSeverity.WARNING);
		host.getActionToOpenCertificatesList().actionPerformed(null);
		return isThereAKeyForDecryption();
	}

	private boolean isThereAKeyForDecryption() {
		return keyRingService.readKeys().stream().anyMatch(x -> x.getKeyData().isCanBeUsedForDecryption());
	}
}
