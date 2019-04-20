package ProjektBus.Server.utils;

import com.kosprov.jargon2.api.Jargon2;

import static com.kosprov.jargon2.api.Jargon2.*;

public class ProjektUtils {

    private static byte[] GENERATED_SALT = "A1Rcb_a8765pOr92".getBytes();
    private static int MEMORY_COST = 65536;
    private static int TIME_COST = 3;
    private static int PARALLELISM = 4;
    private static int SALT_LENGTH = 16;
    private static int HASH_LENGTH = 16;

    // Configure the hasher
    private static Jargon2.Hasher HASHER = jargon2Hasher()
            .type(Jargon2.Type.ARGON2d)   // Data-dependent hashing
            .memoryCost(MEMORY_COST)      // 64MB memory cost
            .timeCost(TIME_COST)          // 3 passes through memory
            .parallelism(PARALLELISM)     // use 4 lanes and 4 threads
            .saltLength(SALT_LENGTH)      // 16 random bytes salt
            .hashLength(HASH_LENGTH);     // 16 bytes output hash

    // Configure the verifier
    private static Jargon2.Verifier VERIFIER = jargon2Verifier()
            .type(Jargon2.Type.ARGON2d)
            .memoryCost(MEMORY_COST)
            .timeCost(TIME_COST)
            .parallelism(PARALLELISM);

    /**
     * Calculate an Argon2 encoded hash. The password is cleared before this method returns.
     *
     * @param password The password to be hashed
     * @return The encoded Argon2 hash
     */
    public static String passwordEncode(String password) {
        char[] passwordArray = password.toCharArray();
        try (Jargon2.ByteArray passwordByteArray = toByteArray(passwordArray).clearSource()) {
            return HASHER.salt(GENERATED_SALT).password(passwordByteArray).encodedHash();
        } catch (Exception e) {
            throw new RuntimeException("Error during password hashing", e);
        }
    }

    /**
     * Verify that the password matches with the encoded hash. The password is cleared before
     * this method returns.
     *
     * @param encodedHash The encoded hash
     * @param password The password to be verified
     * @return <tt>true</tt> if the password matches with the encoded hash
     */
    public static boolean passwordVerify(String encodedHash, String password) {
        char[] passwordArray = password.toCharArray();
        try (ByteArray passwordByteArray = toByteArray(passwordArray).clearSource()) {
            return VERIFIER.salt(GENERATED_SALT).hash(encodedHash).password(passwordByteArray).verifyEncoded();
        } catch (Exception e) {
            throw new RuntimeException("Error during password verification", e);
        }
    }
}
