package com.accountingservices.lisa.models;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 3, max = 50, message = "Обязательное поле")
    private String name;


    @Size(max = 511, message = "Описание не больше 511 символов")
    private String problem;


    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-]" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Укажите, пожалуйста, корректный email")
    private String email;


    @Pattern(regexp = "(^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$)|(^\\s*$)", message = "Укажите, пожалуйста, корректный номер")
    private String phoneNumber;
//|(^\s*$)


    @NotNull(message = "Выберите вид организации")
    private String organizationType;

    private Date placedAt = new Date();


    @Override
    public String toString() {
        return "\nНовая заявка!\n" +
                "\nИмя - " + name + '\n' +
                "\nПроблема - " + problem + '\n' +
                "\nПочта - " + email + '\n' +
                "\nНомер телефона - " + phoneNumber + '\n' +
                "\nВид организации - " + organizationType;
    }
}
