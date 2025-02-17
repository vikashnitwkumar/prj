package QueryParam7.src.main.java.org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contractualEmployee")
public class ContractualEmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    //Implement APIs here...
    @GetMapping("/isOnboarded/email/{email}")
    public ResponseEntity<?> checkContractualEmployeeOnboardingStatus(
        @PathVariable String email,
        @RequestParam(required = false, value = "empId") Long id
    ){
      return new ResponseEntity<>(employeeService.isOnboarded(id, email), HttpStatus.OK);
    }

    @GetMapping("/permissions")
    public ResponseEntity<?> getPermissions(
            @RequestParam(required = true, name = "roles") List<String> roles
    ){

        return new ResponseEntity<>(
                employeeService.getPermissionsBasedOnRoles(roles),
                 HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<?> isContractualEmployee(
         @RequestParam (name = "email" , required = false) String email,
         @RequestParam (name = "password" , required = false) String password,
         @RequestParam (name = "name" , required = false) String name
    ){
        return new ResponseEntity<>(
                employeeService.isIdentityProvided(email, password, name),
                HttpStatus.OK
        );
    }
}