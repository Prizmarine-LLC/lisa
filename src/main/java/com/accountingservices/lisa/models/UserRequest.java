package com.accountingservices.lisa.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotNull;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Getter
@Setter
@Table(name = "user_requests")
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    @NotNull
    @Size(min = 3, max = 255, message = "Имя должно быть обязательно заполнено тремя и более символами")
    private String name;

    @Column(name = "problem")
    @Size(max = 255, message = "Описание не больше 255 символов")
    private String problem;

    @Column(name = "email")
    @NotNull
    @Size(max = 255)
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-]" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Почта должна быть обязательно и корректно заполнена")
    private String email;


    @Column(name = "phone_number", nullable = true)
    @Pattern(regexp = "(^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$)|(^\\s*$)", message = "Неправильный формат номера телефона")
    private String phoneNumber;
//|(^\s*$)

    @NotNull(message = "Выберите вид организации")
    private String organizationType;

    public UserRequest(String name, String problem, String email, String phoneNumber, String organizationType) {
        this.name = name;
        this.problem = problem;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.organizationType = organizationType;
    }

    public UserRequest() {
    }

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
