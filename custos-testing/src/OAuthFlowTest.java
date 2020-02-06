import org.apache.custos.clients.CustosClientProvider;
import org.apache.custos.identity.management.client.IdentityManagementClient;
import org.apache.custos.identity.management.service.AuthorizationResponse;
import org.apache.custos.identity.service.TokenResponse;

import javax.net.ssl.SSLException;

public class OAuthFlowTest {
    public static void main(String[] args) throws SSLException {


        CustosClientProvider provider = new CustosClientProvider.Builder()
                .setServerHost("custos.scigap.org")
                .setServerPort(32036)
                .setCertFilePath("/cert.pem")
                .build();

        IdentityManagementClient identityManagementClient = provider.getIdentityManagementClient("custos/pG2Jd1Toltxn88jSIiCT/10000019", "xZM2pjQu0VOzTa1nV1rbcFPCrtMDXNbFWFTlpWIX");

        AuthorizationResponse response = identityManagementClient.authorize("https://idp.htrc.indiana.edu/playground2/*", "code", "openid", "ssssds123123123123");

        System.out.println(response.getLoginURI());

        TokenResponse resp = identityManagementClient.getToken("22387b46-a6f2-463e-a930-1ec18e2e2c94.aead58d8-ec17-43c6-8def-12c73d9f5927.1a54fdf2-33e0-4e3b-aef9-644f7663c7f0",
                "https://idp.htrc.indiana.edu/playground2/*");
        System.out.println("access_token " + resp.getAccessToken());

        System.out.println("id_token " + resp.getIdToken());


    }
}
