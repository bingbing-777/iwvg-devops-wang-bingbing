package es.upm.miw.devops.model;

public class User {

    private Long id;
    private String firstName;
    private String familyName;
    private String email;
    private String identity;
    private String address;
    private String city;
    private String province;
    private String postalCode;

    public User(Long id, String firstName, String familyName, String email,
                String identity, String address, String city,
                String province, String postalCode) {
        this.id = id;
        this.firstName = firstName;
        this.familyName = familyName;
        this.email = email;
        this.identity = identity;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }

    public String getIdentity() {
        return identity;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public boolean isBillable() {
        return firstName != null && !firstName.isBlank()
                && familyName != null && !familyName.isBlank()
                && email != null && !email.isBlank()
                && identity != null && !identity.isBlank()
                && address != null && !address.isBlank()
                && city != null && !city.isBlank()
                && province != null && !province.isBlank()
                && postalCode != null && !postalCode.isBlank();
    }
}
