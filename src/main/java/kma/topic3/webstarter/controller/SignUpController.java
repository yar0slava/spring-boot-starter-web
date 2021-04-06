package kma.topic3.webstarter.controller;

import kma.topic3.webstarter.database.PermissionRepository;
import kma.topic3.webstarter.database.UserRepository;
import kma.topic3.webstarter.model.entities.PermissionEntity;
import kma.topic3.webstarter.model.entities.UserEntity;
import kma.topic3.webstarter.model.security.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    //admin password
    //yar0slava password

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(
            @RequestBody final UserEntity user
    ) {
        PermissionEntity permUser = new PermissionEntity();
        permUser.setPermission(Permission.USER);
        permUser.setId(1);
//        permissionRepository.save(permUser);

        user.setPermissions(List.of(permUser));
        UserEntity userEntity = userRepository.save(user);
        System.out.println("Saved user: " + user);

        return "redirect:/login";
    }
}
