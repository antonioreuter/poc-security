package poc.security.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import poc.security.sec.models.Organization;
import poc.security.sec.models.Privilege;
import poc.security.sec.models.User;
import poc.security.sec.repositories.OrganizationRepository;
import poc.security.sec.repositories.PrivilegeRepository;
import poc.security.sec.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by 310280812 on 3/28/2017.
 */
@Component
public class SetupData {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @PostConstruct
    public void init() {
        initOrganizations();
        initPrivileges();
        initUsers();
    }

    private void initUsers() {
        final Privilege privilege1 = privilegeRepository.findByName("philips.hsdp.scale.create");
        final Privilege privilege2 = privilegeRepository.findByName("philips.hsdp.scale.read");
        //
        final User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("123");
        user1.setPrivileges(new HashSet<Privilege>(Arrays.asList(privilege1)));
        user1.setOrganization(organizationRepository.findByName("FirstOrg"));
        userRepository.save(user1);
        //
        final User user2 = new User();
        user2.setUsername("tom");
        user2.setPassword("111");
        user2.setPrivileges(new HashSet<Privilege>(Arrays.asList(privilege1, privilege2)));
        user2.setOrganization(organizationRepository.findByName("SecondOrg"));
        userRepository.save(user2);
    }

    private void initOrganizations() {
        final Organization org1 =  Organization.builder().name("FirstOrg").build();
        organizationRepository.save(org1);
        //
        final Organization org2 = Organization.builder().name("SecondOrg").build();
        organizationRepository.save(org2);
    }

    private void initPrivileges() {
        final Privilege privilege1 = Privilege.builder().name("philips.hsdp.scale.create").build();
        privilegeRepository.save(privilege1);
        //
        final Privilege privilege2 = Privilege.builder().name("philips.hsdp.scale.read").build();
        privilegeRepository.save(privilege2);
    }
}