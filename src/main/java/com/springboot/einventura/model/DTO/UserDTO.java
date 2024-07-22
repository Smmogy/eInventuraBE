package com.springboot.einventura.model.DTO;

import com.springboot.einventura.model.bean.Inventura;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private List<Integer> idInventuras;
}
