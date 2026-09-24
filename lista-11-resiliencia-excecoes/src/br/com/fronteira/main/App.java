package br.com.fronteira.main;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class App {
    public static void main(String[] args) {
        String encryptedB64 = "U2FsdGVkX1/Jz86x4/Ydu3FZFw5pSo86xHG1MwpCFX/" +
                "Dnn9uCMDd3xLNn61XZouvQy6G2FIhyQXAQwvTWn3/01JGIoIh5RN4NXgs+kdpcf6afHmSMvCZ" +
                "u0EiiiXlVpB2EQGIKDLIAU9c1aQx6bzEgQ==";

        byte[] all = Base64.getDecoder().decode(encryptedB64.replaceAll("\\s", ""));
        byte[] salt = Arrays.copyOfRange(all, 8, 16);
        byte[] cipherText = Arrays.copyOfRange(all, 16, all.length);

        String charset = "abcdefghijklmnopqrstuvwxyz0123456789";
        System.out.println("Iniciando ataque de força bruta no link da NexusTech...");
        long startTime = System.currentTimeMillis();

        for (char c1 : charset.toCharArray()) {
            for (char c2 : charset.toCharArray()) {
                String testPass = "jav" + c1 + c2;
                try {
                    byte[] keyAndIv = derivarChaveIv(testPass, salt, 1000);
                    byte[] key = Arrays.copyOfRange(keyAndIv, 0, 32);
                    byte[] iv = Arrays.copyOfRange(keyAndIv, 32, 48);

                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
                    String result = new String(cipher.doFinal(cipherText), StandardCharsets.UTF_8);

                    if (result.contains("http")) {
                        long endTime = System.currentTimeMillis();
                        System.out.println("\nSUCESSO! A criptografia foi quebrada!");
                        System.out.println("Senha encontrada: " + testPass);
                        System.out.println("Link revelado: " + result.trim());
                        System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");
                        return;
                    }
                } catch (Exception e) {
                    // Senha incorreta: padding inválido. Continua a busca.
                }
            }
        }
        System.out.println("\nForça bruta concluída. Senha não encontrada.");
    }

    private static byte[] derivarChaveIv(String senha, byte[] salt, int iteracoes) throws Exception {
        KeySpec spec = new PBEKeySpec(senha.toCharArray(), salt, iteracoes, 48 * 8);
        return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
    }
}
