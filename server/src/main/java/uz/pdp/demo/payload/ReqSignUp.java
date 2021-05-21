/* Created by IntelliJ IDEA.
 User: Mirzaahmatov Aziz
Date: 3/27/2021
Time: 8:31 PM
 To change this template use File | Settings | File Templates.
*/
package uz.pdp.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqSignUp {
    private String username;
    private String password;
    private String phoneNumber;

}
