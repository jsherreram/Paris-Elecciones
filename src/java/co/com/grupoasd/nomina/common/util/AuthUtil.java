package co.com.grupoasd.nomina.common.util;

import co.com.grupoasd.nomina.common.model.Acceso;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.List;
import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;
import net.oauth.jsontoken.crypto.HmacSHA256Verifier;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;
import net.oauth.jsontoken.discovery.VerifierProviders;
import org.joda.time.DateTime;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;

public class AuthUtil {

    private static final String AUDIENCE = "NotReallyImportant";

    private static final String ISSUER = "YourCompanyOrAppNameHere";

    private static final String SIGNING_KEY = "LongAndHardToGuessValueWithSpecialCharacters@^($%*$%";

    private static final String TOKEN_IDENTITY_ID = "identityId";
    
    public static final String TOKEN_KEY = "APP-TOKEN";

    /**
     * Creates a json web token which is a digitally signed token that contains
     * a payload (e.g. userId to identify the user). The signing key is secret.
     * That ensures that the token is authentic and has not been modified. Using
     * a jwt eliminates the need to store authentication session information in
     * a database.
     *
     * @param userId
     * @param durationDays
     * @return
     */
    public static String createJsonWebToken(String identityId, Long durationDays) {
        //Current time and signing algorithm
        Calendar cal = Calendar.getInstance();
        HmacSHA256Signer signer;
        try {
            signer = new HmacSHA256Signer(ISSUER, null, SIGNING_KEY.getBytes());
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        //Configure JSON token
        JsonToken token = new net.oauth.jsontoken.JsonToken(signer);
        token.setAudience(AUDIENCE);
        token.setIssuedAt(new org.joda.time.Instant(cal.getTimeInMillis()));
        token.setExpiration(new org.joda.time.Instant(cal.getTimeInMillis() + 1000L * 60L * 60L * 24L * durationDays));

        //Configure request object, which provides information of the item
        JsonObject request = new JsonObject();
        request.addProperty(TOKEN_IDENTITY_ID, identityId);

        JsonObject payload = token.getPayloadAsJsonObject();
        payload.add("info", request);

        try {
            return token.serializeAndSign();
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifies a json web token's validity and extracts the user id and other
     * information from it.
     *
     * @param token
     * @return
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    public static Acceso verifyToken(String token) throws SignatureException {
        try {
            final Verifier hmacVerifier = new HmacSHA256Verifier(SIGNING_KEY.getBytes());

            VerifierProvider hmacLocator = new VerifierProvider() {

                @Override
                public List<Verifier> findVerifier(String id, String key) {
                    return Lists.newArrayList(hmacVerifier);
                }
            };
            VerifierProviders locators = new VerifierProviders();
            locators.setVerifierProvider(SignatureAlgorithm.HS256, hmacLocator);
            net.oauth.jsontoken.Checker checker = new net.oauth.jsontoken.Checker() {

                @Override
                public void check(JsonObject payload) throws SignatureException {
                    // don't throw - allow anything
                }

            };
            //Ignore Audience does not mean that the Signature is ignored
            JsonTokenParser parser = new JsonTokenParser(locators,
                    checker);
            JsonToken jt;
            jt = parser.verifyAndDeserialize(token);
            JsonObject payload = jt.getPayloadAsJsonObject();
            Acceso acc = new Acceso();
            String issuer = payload.getAsJsonPrimitive("iss").getAsString();
            String userIdString = payload.getAsJsonObject("info").getAsJsonPrimitive(TOKEN_IDENTITY_ID).getAsString();
            if (issuer.equals(ISSUER) && userIdString != null && !userIdString.trim().isEmpty()) {
                acc.setIdentityId(userIdString);
                acc.setPermitido(true);
                acc.setIssued((new DateTime(payload.getAsJsonPrimitive("iat").getAsLong()).toString()));
                acc.setExpires((new DateTime(payload.getAsJsonPrimitive("exp").getAsLong()).toString()));
                return acc;
            } else {
                return null;
            }
        } catch (InvalidKeyException e1) {
            throw new RuntimeException(e1);
        }
    }
    
}
