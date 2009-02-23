package tarlog.encoder.tool.api;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;

import org.eclipse.jface.dialogs.Dialog;

import tarlog.ui.swt.ddialog.api.fields.InputField;
import tarlog.ui.swt.ddialog.api.fields.InputTextField;
import tarlog.ui.swt.ddialog.api.fields.Validator;

public abstract class KeyStoreAwareEncoder extends AbstractEncoder implements
    Validator {

    protected KeyStore keystore;

    @InputField(name = "Key Store File", required = true, order = -300)
    private File       file;

    @InputField(name = "Key Store Type", order = -200)
    @InputTextField(values = { "JKS", "PKCS12" }, validateNotEmpty = true)
    private String     type = "JKS";

    @InputField(name = "Key Store Password", order = -100)
    @InputTextField(password = true)
    private String     password;

    public String isValid() {
        try {
            keystore = KeyStore.getInstance(type);
        } catch (KeyStoreException e) {
            return e.getMessage();
        }
        try {
            keystore.load(new FileInputStream(file), password.toCharArray());
        } catch (Exception e) {
            return e.getMessage();
        }

        return null;
    }

    @Override
    protected int beforeEncode() {
        int inputStatus = super.beforeEncode();
        if (inputStatus != Dialog.OK) {
            return inputStatus;
        }
        try {
            keystore = KeyStore.getInstance(type);
        } catch (KeyStoreException e) {
            showException(e);
            return Dialog.CANCEL;
        }

        try {
            keystore.load(new FileInputStream(file), password.toCharArray());
        } catch (Exception e) {
            showException(e);
            return Dialog.CANCEL;
        }
        return inputStatus;
    }
}
