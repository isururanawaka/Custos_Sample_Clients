import org.apache.custos.clients.CustosClientProvider;
import org.apache.custos.tenant.management.service.CreateTenantResponse;
import org.apache.custos.tenant.manamgement.client.AdminTenantRegistrationClient;
import org.apache.custos.tenant.profile.service.Tenant;

import javax.net.ssl.SSLException;

public class TenantRegistrationTest {


    public static void main(String[] args) throws SSLException {

        CustosClientProvider provider = new CustosClientProvider.Builder()
                .setServerHost("custos.scigap.org")
                .setServerPort(32036)
                .setCertFilePath("/cert.pem")
                .build();
        AdminTenantRegistrationClient registrationClient = provider
                .getAdminTenantRegistrationClient();


        Tenant tenant = Tenant.newBuilder()
                .setClientName("HTRC")
                .setRequesterEmail("shliyana@indiana.edu")
                .setAdminUsername("htrc-admin")
                .setAdminFirstName("HTRC")
                .setAdminLastName("D2I")
                .setAdminEmail("sharc@indiana.edu")
                .addContacts("+18652445882")
                .addRedirectUris("https://idp.htrc.indiana.edu/playground2/*")
                .setScope("openid profile email org.cilogon.userinfo")
                .setDomain("idp.htrc.indiana.edu")
                .setAdminPassword("1234")
                .setClientUri("https://idp.htrc.indiana.edu/playground2")
                .setLogoUri("https://idp.htrc.indiana.edu/playground2")
                .setApplicationType("web")
                .setComment("HTRC gateway registration")
                .build();

        CreateTenantResponse response = registrationClient.createAdminTenant(tenant);

        System.out.println("Custos ClientId " + response.getClientId());
        System.out.println("Custos Client Secret " + response.getClientSecret());
    }
}
