package ro.scoalainformala.hello.service.dto;

public class UserDTO {

    private long id;
    private String name;
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String city;
    private String adress;
    private String phoneNumber;

    public UserDTO(){}

    public UserDTO(long id, String username, String firstName, String lastName, String city, String adress, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }

    public UserDTO(long id, String name, String username, String password,
                   String confirmPassword, String firstName,
                   String lastName, String city, String adress, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }

    public UserDTO(long id, String name, String username, String password, String confirmPassword) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
