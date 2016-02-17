package baidu.com.commontools.utils;


import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


public class RsaUtils {

    public static class CipherException extends Exception {

        public CipherException(Throwable throwable) {
            super(throwable);
        }
    }

    private static final String ALGORITHM = "RSA";
    private static final String PROVIDER = "BC";
    private static final String TRANSFORMATION = "RSA/ECB/PKCS1PADDING";

    private static PublicKey getPublicKey(String pubKey) throws InvalidKeySpecException,
            NoSuchAlgorithmException, NoSuchProviderException {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM, PROVIDER);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(pubKey, Base64.DEFAULT));
        return keyFactory.generatePublic(keySpec);
    }

    private static PrivateKey getPrivateKey(String priKey) throws InvalidKeySpecException,
            NoSuchAlgorithmException, NoSuchProviderException {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM, PROVIDER);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(priKey, Base64.DEFAULT));
        return keyFactory.generatePrivate(keySpec);
    }

    public static byte[] encrypt(String content, InputStream inputStream) throws CipherException {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(readKey(inputStream)));
            return cipher.doFinal(content.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    public static byte[] encrypt(byte[] content, InputStream inputStream) throws CipherException {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(readKey(inputStream)));
            return cipher.doFinal(content);
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    public static byte[] encrypt(byte[] content, String pubKey) throws CipherException {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(pubKey));
            return cipher.doFinal(content);
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    public static byte[] encrypt(String content, String pubKey) throws CipherException {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(pubKey));
            return cipher.doFinal(content.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    @SuppressWarnings("unused")
    public static String encryptWithBase64(String content, InputStream inputStream) throws CipherException {
        try {
            return Base64.encodeToString(encrypt(content, inputStream), Base64.NO_WRAP);
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    @SuppressWarnings("unused")
    public static String encryptWithBase64(byte[] content, InputStream inputStream) throws CipherException {
        try {
            return Base64.encodeToString(encrypt(content, inputStream), Base64.NO_WRAP);
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    @SuppressWarnings("unused")
    public static String encryptWithBase64(String content, String pubKey) throws CipherException {
        try {
            return Base64.encodeToString(encrypt(content, pubKey), Base64.NO_WRAP);
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    @SuppressWarnings("unused")
    public static String encryptWithBase64(byte[] content, String pubKey) throws CipherException {
        try {
            return Base64.encodeToString(encrypt(content, pubKey), Base64.NO_WRAP);
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }


    public static byte[] decrypt(byte[] content, InputStream inputStream) throws CipherException {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(readKey(inputStream)));
            return cipher.doFinal(content);
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    @SuppressWarnings("unused")
    public static byte[] decrypt(String content, InputStream inputStream) throws CipherException {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(readKey(inputStream)));
            return cipher.doFinal(content.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    public static byte[] decrypt(byte[] content, String priKey) throws CipherException {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(priKey));
            return cipher.doFinal(content);
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    @SuppressWarnings("unused")
    public static byte[] decrypt(String content, String priKey) throws CipherException {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(priKey));
            return cipher.doFinal(content.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    @SuppressWarnings("unused")
    public static String decryptWithBase64(String content, InputStream inputStream) throws CipherException {
        try {
            byte[] decodeByte = Base64.decode(content, Base64.NO_WRAP);
            return new String(decrypt(decodeByte, inputStream));
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    @SuppressWarnings("unused")
    public static String decryptWithBase64(byte[] content, InputStream inputStream) throws CipherException {
        try {
            byte[] decodeByte = Base64.decode(content, Base64.NO_WRAP);
            return new String(decrypt(decodeByte, inputStream));
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    @SuppressWarnings("unused")
    public static String decryptWithBase64(String content, String priKey) throws CipherException {
        try {
            byte[] decodeByte = Base64.decode(content, Base64.NO_WRAP);
            return new String(decrypt(decodeByte, priKey));
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    @SuppressWarnings("unused")
    public static String decryptWithBase64(byte[] content, String priKey) throws CipherException {
        try {
            byte[] decodeByte = Base64.decode(content, Base64.NO_WRAP);
            return new String(decrypt(decodeByte, priKey));
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    /**
     * 读取密钥信息
     */
    public static String readKey(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null) {
            if (readLine.charAt(0) == '-') {
                continue;
            } else {
                sb.append(readLine);
                sb.append('\r');
            }
        }

        return sb.toString();
    }
}
