package br.com.technexus.main;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class ForcaBruta {
    public static void main(String[] args) {
        String encryptedB64 = "U2FsdGVkX189CvKETNa+k2wHIMpbCwNk7HKB3nBRzOD9bBaPt2nFMCdElvKoRfTmmqVv41Trh37ORXFWRVNOX3" +
                "vpgPHULkkaoyh9DfmzrGBXkGnu/SJfQkGuU08zbgMQNSwCGTwoIHkUMzRFQELN0Q==";

        byte[] all = Base64.getDecoder().decode(encryptedB64.replaceAll("\\s", ""));
        byte[] salt = Arrays.copyOfRange(all, 8, 16);
        byte[] cipherText = Arrays.copyOfRange(all, 16, all.length);
        String charset = "abcdefghijklmnopqrstuvwxyz0123456789";

        System.out.println("Iniciando força bruta para padrão lamXXX...");
        long inicio = System.currentTimeMillis();

        for (char c1 : charset.toCharArray()) {
            for (char c2 : charset.toCharArray()) {
                for (char c3 : charset.toCharArray()) {
                    String senha = "lam" + c1 + c2 + c3;
                    try {
                        byte[] keyAndIv = derivarChaveIv(senha, salt, 1000);
                        byte[] key = Arrays.copyOfRange(keyAndIv, 0, 32);
                        byte[] iv = Arrays.copyOfRange(keyAndIv, 32, 48);

                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
                        String resultado = new String(cipher.doFinal(cipherText), StandardCharsets.UTF_8);

                        if (resultado.contains("http")) {
                            System.out.println("Senha encontrada: " + senha);
                            System.out.println("URL revelada: " + resultado.trim());
                            System.out.println("Tempo: " + (System.currentTimeMillis() - inicio) + "ms");
                            return;
                        }
                    } catch (Exception e) {
                        // Tentativa inválida, continua.
                    }
                }
            }
        }
        System.out.println("Senha não encontrada.");
    }

    private static byte[] derivarChaveIv(String senha, byte[] salt, int iteracoes) throws Exception {
        KeySpec spec = new PBEKeySpec(senha.toCharArray(), salt, iteracoes, 48 * 8);
        return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
    }
}
