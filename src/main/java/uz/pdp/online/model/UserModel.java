package uz.pdp.online.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserModel {
    private long id;
    private String ownLang;
    private String toLang;

    private String langQuery;
}
