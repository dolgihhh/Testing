package models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DemoQaFormData {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String phoneNumber;
    private String birthDate;
    private String subjects;
    private List<String> hobbies;
    private String filePath;
    private String address;
    private String state;
    private String city;
}
