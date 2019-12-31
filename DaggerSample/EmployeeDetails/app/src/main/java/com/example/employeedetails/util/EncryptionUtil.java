package com.example.employeedetails.util;


import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utils class to do encrypt/Decrypt a String and Image
 * <p>
 * This class use Symmetric AES algorithm
 * </p>
 *
 * @author Muthu.krishnan
 */
public class EncryptionUtil {

    private static final String ENCRYPT_KEY_NAME = "AES";
    private static final String ENCRYPT_ALGO = "AES/CBC/PKCS7Padding";
    private static final String CHARSET = "UTF-8";
    private static final String MESSAGE_DIGEST_ALGORITHM = "SHA-256";

    private static final byte[] IV =
            new byte[]{(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A, (byte) 0x8E,
                    0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A};

    /**
     * To encrypt a string using given password
     *
     * @param plainData as String
     * @param password  as String
     * @return encrypted and {@link Base64} encoded String
     * @throws Exception
     */
    public static String encrypt(String plainData, String password) throws Exception {

        Cipher c = getCipher(password, true);

        byte[] cipherText = c.doFinal(plainData.getBytes(CHARSET));

        return Base64.encodeToString(cipherText, Base64.NO_WRAP);
    }

    /**
     * To decrypt a string using given password
     *
     * @param cipherData encrypted and {@link Base64} encoded String
     * @param password   as String
     * @return plain data
     * @throws Exception
     */
    public static String decrypt(String cipherData, String password) throws Exception {

        Cipher c = getCipher(password, false);

        byte[] decodedCipherText = Base64.decode(cipherData, Base64.NO_WRAP);

        byte[] decryptedBytes = c.doFinal(decodedCipherText);

        return new String(decryptedBytes, CHARSET);
    }

    /**
     * To get the {@link Cipher} object using the given password
     *
     * @param password password to create the key
     * @param isEnc    if encryption -> true, false otherwise
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     */
    private static Cipher getCipher(String password, boolean isEnc) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException {
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV);

        Cipher c = Cipher.getInstance(ENCRYPT_ALGO);

        SecretKeySpec key = new SecretKeySpec(getKey(password), ENCRYPT_KEY_NAME);

        c.init(isEnc ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, key, paramSpec);

        return c;
    }

    private static byte[] getKey(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        final MessageDigest digest = MessageDigest.getInstance(MESSAGE_DIGEST_ALGORITHM);
        byte[] bytes = password.getBytes(CHARSET);

        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();

        return key;
    }
}
