/*******************************************************************************
 *     Copyright 2009 Michael Elman (http://tarlogonjava.blogspot.com)
 *
 *     Licensed under the Apache License, Version 2.0 (the "License"); 
 *     you may not use this file except in compliance with the License. 
 *     You may obtain a copy of the License at 
 *     
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *     
 *     Unless required by applicable law or agreed to in writing,
 *     software distributed under the License is distributed on an
 *     "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *     KIND, either express or implied.  See the License for the
 *     specific language governing permissions and limitations
 *     under the License.
 *******************************************************************************/
package tarlog.encoder.tool.encoders;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import tarlog.encoder.tool.api.AbstractEncoder;


public class MD5Encoder extends AbstractEncoder {
    
    @Override
    public Object encode(byte[] source) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}