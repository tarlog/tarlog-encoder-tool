// (C) Copyright 2003-2008 Hewlett-Packard Development Company, L.P.
package tarlog.encodertool.encoders;

import org.apache.commons.codec.binary.Base64;

import tarlog.encoder.tool.AbstractEncoder;
import tarlog.encoder.tool.Utils;


/**
 *
 */
public class FromBase64Encoder extends AbstractEncoder {

    @Override
    public Object encode(String source) {
        try {
            return Base64.decodeBase64(source.getBytes("UTF-8"));
        } catch (Exception e) {
            Utils.showException(shell, e);
            return null;
        }
    }
    
    @Override
    public Object encode(byte[] source) {
        return Base64.decodeBase64(source);
    }

    @Override
    public String getName() {
        return "From Base64";
    }

}
